package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected static WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }
    public static String getPageTitle() {
        return driver.getTitle();
    }
    protected WebElement waitForClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    protected WebElement waitForPresent(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
    protected boolean isDisplayed(WebElement element) {
        try {

            return waitForClickable(element).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
        protected void jsClick(WebElement element) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        }




}