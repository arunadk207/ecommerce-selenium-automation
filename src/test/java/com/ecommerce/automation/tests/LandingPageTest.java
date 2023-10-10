package com.ecommerce.automation.tests;

import com.ecommerce.automation.pages.LandingPage;
import com.ecommerce.automation.utils.ExcelDataReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.URL;

public class LandingPageTest {

    private WebDriver webDriver;
    private URL uri;
    private LandingPage landingPage;

    @BeforeClass
    public void initialize() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeTest
    public void setUp() {
        webDriver = new ChromeDriver();
        landingPage = new LandingPage(webDriver);
    }

    @Test(dataProvider = "login-url")
    public void testTitle(String url) throws InterruptedException {
        webDriver.get(url);
        Assert.assertTrue(landingPage.isLogoPresent());
    }

    @AfterClass
    public void shutdown() {
        webDriver.quit();
    }

    @DataProvider(name = "login-url")
    public static String[][] urlProvider() {
        String sheetName = "URL";
        return ExcelDataReader.readData(sheetName);
    }
}
