package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

//первый шаг заказа "Для кого самокат"
public class OrderFirstStepPage {

    private WebDriver driver;
    private WebDriverWait wait;

    //поле Имя
    private By usernameField = By.xpath(".//input[@placeholder='* Имя']");
    //поле Фамилия
    private By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    //поле Адрес
    private By addressField = By.xpath(".//input[contains(@placeholder, '* Адрес')]");
    //поле Станция метро
    private By metroField = By.xpath(".//input[@placeholder='* Станция метро']");
    //поле Телефон
    private By phoneNumberField = By.xpath(".//input[contains(@placeholder, '* Телефон')]");
    //кнопка Далее
    private By nextStepButton = By.xpath(".//button[text()='Далее']");

    //конструктор
    public OrderFirstStepPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //методы
    public void setUsername(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys(username);
    }
    public void setSurname(String surname) {
        driver.findElement(surnameField).sendKeys(surname);
    }
    public void setAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }
    public void setPhone(String phoneNumber) {
        driver.findElement(phoneNumberField).sendKeys(phoneNumber);
    }
    public void setMetroStation(String stationName) {
        driver.findElement(metroField).click();
        By station = By.xpath(".//div[text()='" + stationName + "']");
        wait.until(ExpectedConditions.elementToBeClickable(station)).click();
    }
    public void clickNextStepButton() {
        wait.until(ExpectedConditions.elementToBeClickable(nextStepButton)).click();
    }

    //заполнение первого шага заказа
    public void setFirstStepOrder(String username, String surname, String address, String stationName, String phoneNumber) {
        setUsername(username);
        setSurname(surname);
        setAddress(address);
        setMetroStation(stationName);
        setPhone(phoneNumber);
        clickNextStepButton();
    }
}
