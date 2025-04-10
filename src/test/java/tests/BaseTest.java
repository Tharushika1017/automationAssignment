package tests;

import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import pages.CartPage;
import pages.HomePage;
import pages.ProductPage;
import pages.SearchPage;
import pojos.Data;
import utils.ConfigReader;
import utils.DriverManager;
import utils.JsonReader;

import java.io.IOException;

public abstract class BaseTest {
    WebDriver driver;
    SearchPage searchPage;
    HomePage homePage;
    ProductPage productPage;
    CartPage cartPage;
    private String url;
    ConfigReader configReader;
    Data data;


    @BeforeClass
    @Parameters({"env.url", "browser", "timeout"})
    public void setup(String url, String browser, String timeout) throws IOException {
        this.url = url;
        driver = DriverManager.setupDriver();  // Using WebDriverManager for automatic setup
        homePage = new HomePage(driver);
        driver.get(this.url);
        searchPage = new SearchPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        configReader = new ConfigReader("src/main/resources/config.properties");

        data = JsonReader.getTestData(configReader.getProperty("jsonFilePath"), Data.class);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
