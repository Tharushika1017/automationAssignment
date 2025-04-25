package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage {
    @FindBy(xpath = "//span[contains(text(),'Search')]/ancestor::a[@href='/search']")
    WebElement searchLinkIcon;

    @FindBy(xpath = "//form[@id='HeaderSearchForm']//input[@type='search']")
    WebElement searchBox;
    @FindBy(xpath = "//form[@id='HeaderSearchForm']//button[@type='submit']")
    WebElement searchButton;
    @FindBy(xpath = "//button[@aria-label='Close dialog']")
    WebElement popUpCloseButtom;
    @FindBy(xpath = "//li/a[contains(text(),'Shop By Category')]")
    WebElement shopBySelection;
    @FindBy(xpath = "//div[@class='grid grid--center']//a[contains(text(),'Teddy Bears')]")
    WebElement category;
    @FindBy(xpath = "//span[contains(text(),'gund')]/../span[@class='kuFilterIcon']")
    WebElement narrowByOption;



    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickSearchLinkIcon() {
        jsClick(searchLinkIcon);
    }

    public void clickOnShopBySelection() {
        shopBySelection.click();
    }

    public void clickOnCategory() {
        category.click();
    }

    public void clickNarrowByOption() {
        waitForPresent(narrowByOption);
        jsClick(narrowByOption);
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
