package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.BasePage;

import java.util.List;

public class SearchTest extends BaseTest {
    @Test
    public void searchAndVerifyAddToCartPageDetails() {

        SoftAssert softAssert = new SoftAssert();
        homePage.closeInitialPopupIfPresent();
        softAssert.assertEquals(data.getHomePageTitle(), BasePage.getPageTitle());
        homePage.clickSearchLinkIcon();
        homePage.searchFor(data.getSearchQuery());
        Assert.assertTrue(searchPage.isSearchResultHeaderDisplayed(), data.getSearchResultHeader());
        int productCount = searchPage.getProductCount();
        List<Double> initialDoubleSortedList = searchPage.sortList(searchPage.getProductList());
        searchPage.clickFirstPageLeftButton();
        searchPage.clickDropDownOptionByName(data.getSortOrder());
        int SortedProductCount = searchPage.getProductCount();
        List<Double> doubleSortedList = searchPage.sortList(searchPage.getProductList());
        softAssert.assertEquals(productCount, SortedProductCount, data.getErrorCountMismatch());
        softAssert.assertEquals(initialDoubleSortedList, doubleSortedList, data.getErrorSortMismatch());
        searchPage.clickOnAProduct();
        Assert.assertTrue(productPage.isProductNameHeaderDisplayed());
        productPage.clearAndSendQty(data.getQuantity());
        double productTotal = productPage.calculateTotal(data.getQuantity());
        productPage.clickAddToCartButton();
        Assert.assertTrue(cartPage.isCartPopupDisplayed(), data.getSearchResultHeader());
        Assert.assertEquals(cartPage.getCartItemCount(), data.getQuantity());
        Assert.assertEquals(cartPage.getSubtotal(), productTotal);
        Assert.assertTrue(cartPage.isChatIconDisplayed());
    }

}

