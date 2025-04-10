package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {


    @FindBy(id = "cart-popup")
    private WebElement cartPopup;

    @FindBy(css = ".cart-item-count")
    private WebElement cartItemCount;

    @FindBy(css = ".cart-subtotal")
    private WebElement subtotal;

    @FindBy(id = "chat-icon")
    private WebElement chatIcon;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCartPopupDisplayed() {
        return cartPopup.isDisplayed();
    }

    public String getCartItemCount() {
        return cartItemCount.getText();
    }

    public String getSubtotal() {
        return subtotal.getText();
    }

    public boolean isChatIconDisplayed() {
        return chatIcon.isDisplayed();
    }
}
