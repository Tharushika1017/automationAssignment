package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage {

    @FindBy(xpath = "//h1[@class='h2 product-single__title']")
    WebElement productNameHeader;
    @FindBy(name = "quantity")
    WebElement qtyInput;
    @FindBy(xpath = "//span[@class='product__price']")
    WebElement productPrice;
    @FindBy(id = "AddToCartText-")
    WebElement addToCartButton;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public boolean isProductNameHeaderDisplayed() {
        try {
            waitForPresent(productNameHeader);
            return isDisplayed((productNameHeader));

        } catch (Exception e) {
            System.out.println("Failed to load product: " + e.getMessage());

        }
        return false;
    }

    public void clearAndSendQty(int quantity) {
        qtyInput.click();
        qtyInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        qtyInput.sendKeys(Integer.toString(quantity));
    }

    public void clickAddToCartButton() {
        addToCartButton.click();
    }

    public double calculateTotal(int Quantity) {

        double price = Double.parseDouble(productPrice.getText().replace("$", ""));
        return price * Quantity;
    }

}
