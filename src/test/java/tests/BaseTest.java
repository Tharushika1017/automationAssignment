package tests;

import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import pages.HomePage;
import utils.DriverManager;

public class BaseTest {
    WebDriver driver;
    HomePage homePage;
    private String url;

    @BeforeSuite
    @Parameters({"env.url", "browser", "timeout"})
    public void setup(String url, String browser, String timeout) {
        this.url = url;
        driver = DriverManager.setupDriver();  // Using WebDriverManager for automatic setup
        homePage = new HomePage(driver);
        driver.get(this.url);
    }

    @AfterSuite
    public void tearDown() {
        driver.quit();
    }
}
