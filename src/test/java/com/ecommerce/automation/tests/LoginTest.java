package com.ecommerce.automation.tests;

import com.ecommerce.automation.pages.LandingPage;
import com.ecommerce.automation.pages.LoginPage;
import com.ecommerce.automation.utils.ExcelDataReader;
import com.ecommerce.automation.utils.ScreenshotUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;


public class LoginTest {

    private LoginPage loginPage;

    private LandingPage landingPage;

    private WebDriver webDriver;

    @BeforeClass
    public void initialize() {
        WebDriverManager.chromedriver().setup();
    }

    @Test(dataProvider = "url")
    public void setUp(String url) {
        webDriver = new ChromeDriver();
        loginPage = new LoginPage(webDriver);
        webDriver.get(url);
    }

    @Test(priority = 1)
    public void isSignOnLinkPresent() {
        boolean signOnLinkDisplayed = this.loginPage.isSignOnLinkDisplayed();
        Assert.assertTrue(signOnLinkDisplayed);
    }

    @Test(priority = 2)
    public void signOn() {
        this.loginPage.signOn();
    }

    @Test(dataProvider = "login", priority = 3)
    public void loginCustomer(String userName, String password) throws InterruptedException {
        this.loginPage.enterUserName(userName);
        this.loginPage.enterPassword(password);
        this.loginPage.clickOnSignIn();
    }

    @Test(priority = 4)
    public void customerLoginTitle() {
        boolean customerLoginTitleDisplayed = this.loginPage.isCustomerLoginTitleDisplayed();
        Assert.assertTrue(customerLoginTitleDisplayed);
        ScreenshotUtils.takeScreenshot(this.webDriver, "login-successful");
    }

    @DataProvider(name = "login")
    public static Object[][] getData() {
        String sheetName = "Login";
        return ExcelDataReader.readData(sheetName);
    }

    @DataProvider(name = "url")
    public static Object[][] getUrlData() {
        String sheetName = "URL";
        return ExcelDataReader.readData(sheetName);
    }

    @AfterClass
    public void quitDriver() {
        this.webDriver.quit();
    }
}
