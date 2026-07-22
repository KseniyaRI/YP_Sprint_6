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

    //кнопка куки ("Да все привыкли")
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
        WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(cookieButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
    }

    //кликаем на Заказать в хэдере
    public void clickCreateOrderHeaderButton() {
        driver.findElement(headerOrderButton).click();
    }

    //кликаем на Заказать с прокруткой
    public void clickCreateOrderMiddleButton() {
        WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(middleOrderButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true); arguments[0].click();", button);
    }

    //раскрываем выпадашки Вопросов о важном
    public void clickQuestion(int index) {
        WebElement heading = wait.until(
            ExpectedConditions.presenceOfElementLocated(By.id("accordion__heading-" + index)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true); arguments[0].click();", heading);
    }

    //получаем текст в выпадашке
    public String getAnswerText(int index) {
        WebElement panel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accordion__panel-" + index)));
        return panel.getText();
    }
}
