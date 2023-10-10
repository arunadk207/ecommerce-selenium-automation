package com.ecommerce.automation.tests;

import com.ecommerce.automation.pages.LoginPage;
import com.ecommerce.automation.pages.OrderPage;
import com.ecommerce.automation.pages.SearchAndBrowse;
import com.ecommerce.automation.utils.ExcelDataReader;
import com.ecommerce.automation.utils.ScreenshotUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class OrderTest extends BaseTest {

    private LoginPage loginPage;

    private OrderPage orderPage;

    @BeforeClass
    public void initialize() {
        init();
        this.loginPage = new LoginPage(this.webDriver);
        this.orderPage = new OrderPage(this.webDriver);
    }

    @Test(dataProvider = "url", priority = 1)
    public void launchBrowser(String url) {
        this.webDriver.get(url);
    }


    @Test(dataProvider = "login", priority = 2)
    public void login(String userName, String password) {
        this.loginPage.signOn();
        this.loginPage.enterUserName(userName);
        this.loginPage.enterPassword(password);
        this.loginPage.clickOnSignIn();
    }

    @Test(priority = 3)
    public void navigateToAccounts() {
        this.orderPage.navigateWelcomeMenu();
        this.orderPage.navigateAccountsPage();
        this.orderPage.navigateToOrders();
        this.orderPage.viewLatestOrder();
    }

    @Test(priority = 4)
    public void singOut() {
        this.orderPage.navigateWelcomeMenu();
        this.orderPage.signOut();
        ScreenshotUtils.takeScreenshot(this.webDriver, "Sign-out");
    }


    @DataProvider(name = "login")
    public static Object[][] getLoginData() {
        String sheetName = "Login";
        return ExcelDataReader.readData(sheetName);
    }

    @DataProvider(name = "url")
    public static Object[][] getLoginUrl() {
        String sheetName = "URL";
        return ExcelDataReader.readData(sheetName);
    }
}
