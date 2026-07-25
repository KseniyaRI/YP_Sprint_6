package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

//поп-апы подтверждения заказа: "Хотите оформить заказ?" и "Заказ оформлен"
public class OrderConfirmationPage {

    private WebDriverWait wait;

    //кнопка подтверждения заказа пользователем ("Да")
    private By confirmationOfOrderButton = By.xpath(".//button[text()='Да']");

    //подтверждающий системный текст успешного заказа
    private By confirmationOfOrderPopUp = By.xpath(".//div[contains(text(), 'Заказ оформлен')]");

    //конструктор
    public OrderConfirmationPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //методы
    public void clickConfirmationOfOrderButton() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmationOfOrderButton)).click();
    }

    //появилось ли окно об успешном создании заказа
    public boolean isOrderConfirmed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationOfOrderPopUp));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
