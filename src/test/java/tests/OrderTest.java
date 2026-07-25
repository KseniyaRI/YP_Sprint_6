package tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pageobjects.MainPage;
import pageobjects.OrderConfirmationPage;
import pageobjects.OrderFirstStepPage;
import pageobjects.OrderSecondStepPage;

import java.time.LocalDate;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderTest extends BaseTest {

    private Faker faker = new Faker(new Locale("ru"));

    //наборы данных: станция метро, через сколько дней доставка, срок аренды, цвет, точка входа
    @ParameterizedTest
    @CsvSource({
        "Черкизовская, 1, трое суток, black, header",
        "Сокольники, 3, сутки, grey, middle"})
    void checkCreateOrder(String stationName, int daysFromToday, String period, String color, String entryPoint) {
        String username = faker.name().firstName();
        String surname = faker.name().lastName();
        String address = faker.numerify("улица Тестовая, дом ##, квартира ##");
        String phoneNumber = faker.numerify("89#########");
        String comment = faker.numerify("Позвонить за ## минут до доставки");
        LocalDate deliveryDate = LocalDate.now().plusDays(daysFromToday);

        MainPage mainPage = new MainPage(driver);
        mainPage.clickAcceptCookieButton();
        if (entryPoint.equals("header")) {
            mainPage.clickCreateOrderHeaderButton();
        } else {
            mainPage.clickCreateOrderMiddleButton();
        }

        OrderFirstStepPage firstStepPage = new OrderFirstStepPage(driver);
        firstStepPage.setFirstStepOrder(username, surname, address, stationName, phoneNumber);

        OrderSecondStepPage secondStepPage = new OrderSecondStepPage(driver);
        secondStepPage.setSecondStepOrder(deliveryDate, period, color, comment);

        OrderConfirmationPage confirmationPage = new OrderConfirmationPage(driver);
        confirmationPage.clickConfirmationOfOrderButton();
        assertTrue(confirmationPage.isOrderConfirmed(), "Всплывающее окно 'Заказ оформлен' не появилось");
    }
}
