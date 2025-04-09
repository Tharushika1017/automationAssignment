package tests;

import org.testng.annotations.AfterClass;
import pages.*;
import pojos.Product;
import pojos.SearchQuery;
import utils.*;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SearchTest {
    WebDriver driver;
    HomePage homePage;
    SearchPage searchPage;
    ProductPage productPage;

    // Load test data from JSON
    SearchQuery searchQuery;
    Product product;

    @BeforeClass
    public void setup() throws Exception {
        // Set up the WebDriver (use WebDriverManager or manual setup)
        System.setProperty("webdriver.chrome.driver", "path_to_chromedriver");
        driver = new ChromeDriver();
        driver.get("https://faoschwarz.com");

        // Initialize Page Objects
        homePage = new HomePage(driver);
        searchPage = new SearchPage(driver);
        productPage = new ProductPage(driver);

        // Load data from JSON
        searchQuery = JsonUtils.getTestData("src/test/resources/testdata.json", SearchQuery.class);
        product = JsonUtils.getTestData("src/test/resources/testdata.json", Product.class);
    }

    @Test
    public void testSearchFlow() {
        // Step 1: Navigate to the site
        Assert.assertTrue(driver.getTitle().contains("FAO Schwarz"));

        // Step 2: Click on Search
        homePage.searchFor(searchQuery.getSearchQuery());
        driver.get("https://faoschwarz.com");
        homePage.searchProduct(testData.getSearchTerm());
        Assert.assertTrue(searchPage.isSearchResultVisible(), "Search result not visible");

        Response response = ApiHelper.getSearchResponse(testData.getSearchTerm());
        Assert.assertEquals(response.statusCode(), 200, "API response failed");
        Assert.assertTrue(response.getBody().asString().contains(testData.getSearchTerm()), "Search term not found in API response");
    }
        // Step 3: Search for 'cars'
        searchPage.enterSearchQuery(searchQuery.getSearchQuery());
        searchPage.clickSearchButton();

        // Step 4: Sort by 'low to high' in price
        searchPage.selectSortOrder(searchQuery.getSortOrder());

        // Step 5: Click on a product
        productPage.clickAddToCart();

        // Step 6: Increase the number of items to 3
        // (You may simulate a cart action or check backend API for real-time data)
        Assert.assertEquals(productPage.getCartItemCount(), "3");

        // Step 7: Click on Add to Cart
        productPage.clickAddToCart();

        // Step 8: Validate the cart
        // (Assume there's a validation to ensure correct product and subtotal)
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
