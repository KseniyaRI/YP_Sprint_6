package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

//второй шаг заказа самоката "Про аренду"
public class OrderSecondStepPage {

    private WebDriver driver;
    private WebDriverWait wait;

    //поле даты привоза самоката
    private By dateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //поле срока аренды
    private By rentalPeriodField = By.xpath(".//div[contains(@class,'Dropdown-control')]");
    //чекбоксы выбора цвета
    private By blackColorCheckbox = By.id("black");
    private By greyColorCheckbox = By.id("grey");
    //поле комментария
    private By orderComment = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //кнопка Заказать в форме
    private By createOrderButton = By.xpath(".//button[contains(@class,'Button_Middle') and text()='Заказать']");

    //конструктор
    public OrderSecondStepPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //методы
    //выбираем дату в календаре
    public void setDeliveryDate(LocalDate deliveryDate) {
        wait.until(ExpectedConditions.elementToBeClickable(dateField)).click();
        //у каждого дня в календаре есть подпись вида "Choose вторник, 26-е июля 2026 г."
        String dateLabel = deliveryDate.format(DateTimeFormatter.ofPattern("d-'е' MMMM yyyy", new Locale("ru")));
        By dayCell = By.xpath(".//div[contains(@aria-label, '" + dateLabel + "')]");
        wait.until(ExpectedConditions.elementToBeClickable(dayCell)).click();
    }

    public void setRentalPeriod(String period) {
        wait.until(ExpectedConditions.elementToBeClickable(rentalPeriodField)).click();
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

    //заполнение второго шага заказа
    public void setSecondStepOrder(LocalDate deliveryDate, String period, String color, String comment) {
        setDeliveryDate(deliveryDate);
        setRentalPeriod(period);
        setScooterColor(color);
        setOrderComment(comment);
        clickCreateOrderButton();
    }
}
