package com.ecommerce.automation.tests;

import com.ecommerce.automation.pages.LoginPage;
import com.ecommerce.automation.pages.SearchAndBrowse;
import com.ecommerce.automation.utils.ExcelDataReader;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SearchAndBrowseTest extends BaseTest {

    private SearchAndBrowse searchAndBrowse;
    private LoginPage loginPage;

    @BeforeClass
    public void initialize() {
        init();
        this.loginPage = new LoginPage(this.webDriver);
        this.searchAndBrowse = new SearchAndBrowse(this.webDriver);
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

    @Test(dataProvider = "selection", priority = 3)
    public void navigation(String userName, String category,
                           String subCategory, String metricRefinementFilter,
                           String climateRefinementFilter, String patternRefinementFilter,
                           String size, String color) throws InterruptedException {
        this.searchAndBrowse.chooseSales();
        this.searchAndBrowse.chooseTees();
        this.searchAndBrowse.selectMaterial();
        this.searchAndBrowse.selectHemp();
        this.searchAndBrowse.selectClimate();
        this.searchAndBrowse.selectIndoor();
        this.searchAndBrowse.selectPattern();
        this.searchAndBrowse.selectSolid();

        this.searchAndBrowse.chooseAddToCart();
        this.searchAndBrowse.selectSize("S");
        this.searchAndBrowse.selectColor("Blue");
        this.searchAndBrowse.addToCart();
        this.searchAndBrowse.waitForItemToBeAddedToCart("1");
        this.searchAndBrowse.clickOnViewCartIcon();

        this.searchAndBrowse.clickOnViewAndEditCart();

        this.searchAndBrowse.waterBottleAddToCart();

        this.searchAndBrowse.addToCart();
        this.webDriver.navigate().back();

        this.searchAndBrowse.proceedToCheckout();
    }

    @Test(dataProvider = "login")
    public void loginCustomer(String userName, String password) {
        System.out.println(userName);
        System.out.println(password);
    }

    @AfterClass
    public void quitDriver() {
        this.webDriver.quit();
    }


    @DataProvider(name = "selection")
    public static Object[][] getNavigationData() {
        String sheetName = "item-selection";
        return ExcelDataReader.readData(sheetName);
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
