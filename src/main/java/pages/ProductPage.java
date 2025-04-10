package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends BasePage {

    @FindBy(xpath = "//button[@id='add-to-cart']")
    WebElement addToCartButton;

    @FindBy(xpath = "//span[@id='cart-item-count']")
    WebElement cartItemCount;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void clickAddToCart() {
        addToCartButton.click();
    }

    public String getCartItemCount() {
        return cartItemCount.getText();
    }
}
