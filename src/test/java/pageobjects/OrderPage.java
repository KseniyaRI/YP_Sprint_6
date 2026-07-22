package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.TimeoutException;

public class OrderPage {

    private WebDriver driver;
    private WebDriverWait wait;
    
    //локаторы первой страницы заказа самоката
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
    
    //локаторы второй страницы заказа самоката
    //поле даты привоза самоката
    private By dateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //поле срока аренды
    private By rentalPeriodField = By.xpath(".//div[contains(@class,'Dropdown-control')]");
    //поле выбора цвета
    private By blackColorCheckbox = By.id("black");
    private By greyColorCheckbox = By.id("grey");
    //поле комментария – необязательные поля
    private By orderComment = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //кнопка непосредственного заказа
    private By createOrderButton = By.xpath(".//button[contains(@class,'Button_Middle') and text()='Заказать']");

    //pop-up подтверждения заказа самоката пользователем
    //кнопка подтверждения заказа ("Да")
    private By confirmationOfOrderButton = By.xpath(".//button[text()='Да']");
    
    //pop-up подтверждения заказа самоката системой
    //подтверждающий текст успешного заказа
    private By confirmationOfOrderPopUp = By.xpath(".//div[contains(text(), 'Заказ оформлен')]");

    //конструктор
    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //методы
    //метод заполнения первой страницы заказа
    public void setUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
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
        driver.findElement(nextStepButton).click();
    }
    public void setFirstStepOrder(String username, String surname, String address, String stationName, String phoneNumber) {
        setUsername(username);
        setSurname(surname);
        setAddress(address);
        setMetroStation(stationName);
        setPhone(phoneNumber);
        clickNextStepButton();
    }

    //метод заполнения второй страницы заказа
    public void setDeliveryDate(String day) {
        driver.findElement(dateField).click();
        By calendar = By.xpath(".//div[contains(@class,'react-datepicker__day') and text()='" + day + "']");
        wait.until(ExpectedConditions.elementToBeClickable(calendar)).click();
    }
    public void setRentalPeriod(String period) {
        driver.findElement(rentalPeriodField).click();
        By option = By.xpath(".//div[contains(@class,'Dropdown-option') and text()='" + period + "']");
        wait.until(ExpectedConditions.elementToBeClickable(option)).click();
    }
    public void setScooterColor(String color) {
        if (color.equalsIgnoreCase("black")) {
            driver.findElement(blackColorCheckbox).click();
        } else if (color.equalsIgnoreCase("grey")) {
            driver.findElement(greyColorCheckbox).click();
        }
    }
    public void setOrderComment(String comment) {
        driver.findElement(orderComment).sendKeys(comment);
    }
    public void clickCreateOrderButton() {
        wait.until(ExpectedConditions.elementToBeClickable(createOrderButton)).click();
    }
    public void setSecondStepOrder(String day, String period, String color, String comment) {
        setDeliveryDate(day);
        setRentalPeriod(period);
        setScooterColor(color);
        setOrderComment(comment);
        clickCreateOrderButton();
    }
    //метод отправки заполненного заказа
    public void clickConfirmationOfOrderButton() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmationOfOrderButton)).click();
    }

    public boolean isOrderConfirmed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationOfOrderPopUp));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
