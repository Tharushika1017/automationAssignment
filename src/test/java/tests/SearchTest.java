package tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.BasePage;
import pages.HomePage;
import pages.SearchPage;
import pojos.Product;
import pojos.SearchQuery;
import utils.JsonReader;

import java.io.IOException;
import java.util.List;

public class SearchTest extends BaseTest {
    SearchQuery searchQuery;
    Product product;
    //HomePage homePage;
    private SearchPage searchPage;

    @BeforeClass
    public void setUp() {
        searchPage = new SearchPage(driver);
    }

    @Test
    public void searchDataDrivenTest() throws IOException {
        SoftAssert softAssert = new SoftAssert();
        searchQuery = JsonReader.getTestData("src/main/resources/test-data.json", SearchQuery.class);
        product = JsonReader.getTestData("src/main/resources/test-data.json", Product.class);
        product = JsonReader.getTestData("src/main/resources/test-data.json", Product.class);
        homePage.closeInitialPopupIfPresent();
        softAssert.assertEquals("Iconic Toy Store For Kids of All Ages | FAO Schwarz", BasePage.getPageTitle());
        homePage.clickSearchLinkIcon();
        homePage.searchFor(searchQuery.getSearchQuery());
        softAssert.assertTrue(searchPage.isSearchResultHeaderDisplayed(), "Search Result header was not dispalyed");
        Assert.assertTrue(searchPage.isProductCountDisplayed(), "Initial count and sorted count is different");
        int productCount = searchPage.getProductCount();
        List<Double> initialDoubleSortedList = searchPage.sortList(searchPage.getProductList());
        searchPage.clickDropDownOptionByName("Price: Low to high");
        int SortedProductCount = searchPage.getProductCount();
        List<Double> doubleSortedList = searchPage.sortList(searchPage.getProductList());
        Assert.assertEquals(productCount, SortedProductCount, "Initial count and sorted count is different");
        Assert.assertEquals(initialDoubleSortedList, doubleSortedList, "Sorted list is different from initial list");

        //Response response = ApiUtils.getSearchResponse(searchQuery.getSearchQuery());
        // Assert.assertEquals(response.statusCode(), 200, "API response failed");
        //Assert.assertTrue(response.getBody().asString().contains(testData.getSearchTerm()), "Search term not found in API response");();
        // Assert.assertTrue(productCount > 0, "No products found for term: " + term);
    }

}

