package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage {

    private WebDriver driver;
    private WebDriverWait wait;

    //кнопка куки ("да все привыкли")
    private By cookieButton = By.xpath(".//button[contains(text(), 'да все привыкли')]");
    //кнопка Заказать в хэдере
    private By headerOrderButton = By.xpath("(.//button[text()='Заказать'])[1]");
    //кнопка Заказать в середине страницы
    private By middleOrderButton = By.xpath("(.//button[text()='Заказать'])[2]");

    //конструктор
    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //методы
    //закрываем куки
    public void clickAcceptCookieButton() {
        wait.until(ExpectedConditions.elementToBeClickable(cookieButton)).click();
    }

    //кликаем на Заказать в хэдере
    public void clickCreateOrderHeaderButton() {
        wait.until(ExpectedConditions.elementToBeClickable(headerOrderButton)).click();
    }

    //кликаем на Заказать в середине страницы (с прокруткой)
    public void clickCreateOrderMiddleButton() {
        scrollTo(wait.until(ExpectedConditions.presenceOfElementLocated(middleOrderButton)));
        wait.until(ExpectedConditions.elementToBeClickable(middleOrderButton)).click();
    }

    //раскрываем выпадашки Вопросов о важном
    public void clickQuestion(int index) {
        By heading = By.id("accordion__heading-" + index);
        scrollTo(wait.until(ExpectedConditions.presenceOfElementLocated(heading)));
        wait.until(ExpectedConditions.elementToBeClickable(heading)).click();
    }

    //получаем текст в выпадашке
    public String getAnswerText(int index) {
        WebElement panel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accordion__panel-" + index)));
        return panel.getText();
    }

    //метод прокрутки страницы
    private void scrollTo(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }
}
