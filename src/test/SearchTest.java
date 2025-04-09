package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchPage;
import pages.ProductPage;
import pages.CartPage;
import utils.JsonUtils;
import pojos.testData;

import java.util.List;

public class SearchTest {

    private WebDriver driver;
    private HomePage homePage;
    private SearchPage searchPage;
    private ProductPage productPage;
    private CartPage cartPage;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        homePage = new HomePage(driver);
        searchPage = new SearchPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
    }

    @Test(dataProvider = "testData")
    public void testSearchFunctionality(TestData data) {
        homePage.navigateToHomePage("https://faoschwarz.com/search?q=cars");
        homePage.enterSearchTerm(data.getSearchTerm());
        homePage.clickSearchButton();
        Assert.assertTrue(searchPage.areSearchResultsDisplayed());

        searchPage.sortByPriceLowToHigh();
        searchPage.clickOnFirstProduct();

        productPage.increaseQuantity(data.getQuantity());
        productPage.addToCart();

        Assert.assertTrue(cartPage.isCartPopupDisplayed());
        Assert.assertEquals(cartPage.getCartItemCount(), String.valueOf(data.getQuantity()));
        Assert.assertEquals(cartPage.getSubtotal(), data.getExpectedSubtotal());
        Assert.assertTrue(cartPage.isChatIconDisplayed());
    }

    @DataProvider
    public Object[][] testData() {
        List<TestData> testDataList = JsonUtils.getTestData("test-data/test-data.json");
        Object[][] data = new Object[testDataList.size()][1];
        for (int i = 0; i < testDataList.size(); i++) {
            data[i][0] = testDataList.get(i);
        }
        return data;
    }
}
