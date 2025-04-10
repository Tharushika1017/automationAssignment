package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {


    @FindBy(xpath = "//div[@class='cart__item']//input")
    WebElement productItemCount;

    @FindBy(xpath = "//div[@data-subtotal]")
    WebElement subtotal;

    @FindBy(id = "gorgias-chat-messenger-button")
    WebElement chatIcon;
    @FindBy(id = "chat-button")
    WebElement iframe;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCartPopupDisplayed() {
        waitForPresent(productItemCount);
        return productItemCount.isDisplayed();
    }

    public int getCartItemCount() {
        waitForPresent(productItemCount);
        return Integer.parseInt(productItemCount.getAttribute("value"));
    }

    public double getSubtotal() {
        return Double.parseDouble(subtotal.getText().replace("$", ""));
    }

    public boolean isChatIconDisplayed() {
        driver.switchTo().frame(iframe);
        return chatIcon.isDisplayed();
    }
}
