package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

//базовый класс для всех тестов: настройка браузера и адрес стенда в одном месте
public class BaseTest {

    //адрес тестового стенда
    protected static final String BASE_URL = "https://qa-scooter.education-services.ru/";

    protected WebDriver driver;

    //браузер выбирается параметром запуска: mvn test -Dbrowser=firefox
    @BeforeEach
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else {
            driver = new ChromeDriver();
        }
        driver.get(BASE_URL);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
