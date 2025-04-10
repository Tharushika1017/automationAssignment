package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    @FindBy(xpath = "//span[contains(text(),'Search')]/ancestor::a[@href='/search']")
    WebElement searchLinkIcon;
    @FindBy(xpath = "//form[@id='HeaderSearchForm']//input[@type='search']")
    WebElement searchBox;
    @FindBy(xpath = "//form[@id='HeaderSearchForm']//button[@type='submit']")
    WebElement searchButton;
    @FindBy(xpath = "//button[@aria-label='Close dialog']")
    WebElement popUpCloseButtom;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickSearchLinkIcon() {
        jsClick(searchLinkIcon);
    }

    public void searchFor(String keyword) {
        searchBox.sendKeys(keyword);
        searchButton.click();
    }

    public void closeInitialPopupIfPresent() {
        try {
            waitForPresent(popUpCloseButtom);
            if (isDisplayed((popUpCloseButtom))) {
                jsClick(popUpCloseButtom);
                System.out.println("Popup closed successfully.");
            } else {
                System.out.println("No popup found.");
            }

        } catch (Exception e) {
            System.out.println("Failed to close popup: " + e.getMessage());
        }
    }

}
