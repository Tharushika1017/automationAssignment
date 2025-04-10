package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchPage extends BasePage {

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='kuDropdown kuDropSortBy']")
    WebElement sortDropdown;
    @FindBy(xpath = "//h1[text()='Search Results']")
    WebElement searchResultHeader;
    @FindBy(xpath = "//a[@data-section='productList']/b")
    WebElement productCount;
    //private By productPriceList = By.xpath("//span[@class='kuSalePrice kuSpecialPrice']");
    //@FindBy(xpath = "//div[contains(text(),'" + dropDownOptionName + "')]")
    @FindAll(@FindBy(how = How.XPATH, using = "//span[@class='kuSalePrice kuSpecialPrice']"))
    List<WebElement> productPriceList;
    @FindBy(xpath = "//div[@class='klevuLanding klevuTarget kuLEFTFilters kuSearchResultsPageContainer']")
    WebElement productContainer;
    @FindBy(xpath = "//div[contains(text(),'Price: Low to high')]")
    WebElement sortByOptionText;
    @FindBy(xpath = "//div[@class='kuPagination']/a[text()='>']")
    WebElement nextButton;
    @FindBy(xpath = "//div[@class='kuPagination']/a[text()='<<']")
    WebElement firstPageLeftButton;


    public boolean isSearchResultHeaderDisplayed() {
        if (searchResultHeader.isDisplayed()) {
            System.out.println("Heading is visible.");
            return true;
        } else {
            System.out.println("Heading is not visible.");
        }
        return false;
    }

    public int getProductCount() {
        String count = (productCount.getText());
        return Integer.parseInt(count);
    }


    public List<Double> getProductList() {
        List<Double> list = new ArrayList<>();
        for (WebElement p : productPriceList) {
            list.add(Double.valueOf(p.getText().replace("$", "")));
        }
        while (true) {

            if (isNextButtonDisplayed()) {
                jsClick(nextButton);
                // Wait for the page to load after clicking "Next"
                wait.until(ExpectedConditions.visibilityOf(productContainer));
                for (WebElement p : productPriceList) {
                    list.add(Double.valueOf(p.getText().replace("$", "")));
                }
            } else {
                // Break the loop if the "Next" button is not present
                break;
            }
        }
        waitForPresent(firstPageLeftButton);
        jsClick(firstPageLeftButton);
        return list;

    }

    public List<Double> sortList(List<Double> list) {
        Collections.sort(list);
        return list;
    }

    public void clickDropDownOptionByName(String dropDownOptionName) {
        sortDropdown.click();
        sortByOptionText.click();
    }

    public void selectSortOrder(String order) {
        sortDropdown.sendKeys(order);
    }

    public boolean isProductCountDisplayed() {
        try {
            waitForPresent(productCount);
            return isDisplayed((productCount));

        } catch (Exception e) {
            System.out.println("Failed to close popup: " + e.getMessage());

        }
        return false;
    }

    public boolean isNextButtonDisplayed() {
        try {
            driver.findElement(By.xpath("//div[@class='kuPagination']/a[text()='>']"));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
