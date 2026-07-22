package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pageobjects.OrderPage;
import static org.junit.jupiter.api.Assertions.assertTrue;
import pageobjects.MainPage;

public class OrderTest {
    
    private WebDriver driver;

    @BeforeEach
    void setUp() {
        String browser = System.getProperty("browser", "chrome");
        if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless");
            driver = new FirefoxDriver(options);
        } else {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
            driver = new ChromeDriver(options);
        }
        driver.get("https://qa-scooter.education-services.ru/");
    }    

    @ParameterizedTest
    @CsvSource({
        "Иван, Петров, ул. Воздушная 3, Черкизовская, 89819818070, 21, трое суток, black, Комментарий для курьера, header",
        "Мария, Сидорова, пр. Мира 10, Сокольники, 89990001122, 22, сутки, grey, Без комментариев, middle"})

    void checkCreateOrder(String username, String surname, String address, String stationName, 
        String phoneNumber, String day, String period, String color, String comment, String entryPoint) {
        MainPage mainpage = new MainPage(driver);
        OrderPage orderpage = new OrderPage(driver);

        mainpage.clickAcceptCookieButton();
        if (entryPoint.equals("header")) {
            mainpage.clickCreateOrderHeaderButton();
        } else {
            mainpage.clickCreateOrderMiddleButton();
        }

        orderpage.setFirstStepOrder(username, surname, address, stationName, phoneNumber);
        orderpage.setSecondStepOrder(day, period, color, comment);
        orderpage.clickConfirmationOfOrderButton();
        assertTrue(orderpage.isOrderConfirmed(), "Всплывающее окно 'Заказ оформлен' не появилось");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
