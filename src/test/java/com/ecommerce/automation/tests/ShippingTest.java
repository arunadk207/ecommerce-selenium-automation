package com.ecommerce.automation.tests;

import com.ecommerce.automation.pages.LoginPage;
import com.ecommerce.automation.pages.SearchAndBrowse;
import com.ecommerce.automation.pages.ShippingPage;
import com.ecommerce.automation.utils.ExcelDataReader;
import com.ecommerce.automation.utils.ScreenshotUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ShippingTest extends BaseTest {

    private LoginPage loginPage;
    private SearchAndBrowse searchAndBrowse;
    private ShippingPage shippingPage;

    @BeforeClass
    public void initialize() {
        init();
        this.loginPage = new LoginPage(this.webDriver);
        this.searchAndBrowse = new SearchAndBrowse(this.webDriver);
        this.shippingPage = new ShippingPage(webDriver);
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
    public void proceedToCheckout() {
        this.searchAndBrowse.waitForItemToBeAddedToCart("2");
        this.searchAndBrowse.clickOnViewCartIcon();
        this.searchAndBrowse.navigateToCheckoutPage();
    }

    @Test(dataProvider = "shipping-address", priority = 4)
    public void fillInShippingAddress(String... args) {
        String firstName = args[0];
        String lastName = args[1];
        String company = args[2];
        String address = args[3];
        String city = args[4];
        String state = args[5];
        String zipCode = args[6];
        String phoneNmber = args[7];
        if (!this.shippingPage.isNewShippingAddressButtonExist()) {
            this.shippingPage.enterCompany(company);
            this.shippingPage.enterAddress(address);
            this.shippingPage.enterCity(city);
            this.shippingPage.enterPostalCode(zipCode);
            this.shippingPage.enterPhoneNumber(phoneNmber);
            this.shippingPage.chooseState(state);
        }
        this.shippingPage.chooseShippingMethod("flat");
        this.shippingPage.goToNextPage();

    }

    @Test(priority = 5)
    public void clickOnApplyDiscount() {
        this.shippingPage.isPlaceOrderButtonPresent();
        // this.shippingPage.clickOnApplyDiscount();
    }

    @Test(priority = 6)
    public void applyDiscountCode() {
        // this.shippingPage.applyDiscountCode();
    }

    @Test(priority = 7)
    public void applyDiscount() {
        // this.shippingPage.applyDiscount();
    }

    @Test(priority = 8)
    public void placeOrder() throws InterruptedException {
        Thread.sleep(5000);
        this.shippingPage.placeOrder();
        String orderNumber = this.shippingPage.readOrderNumber();
        ScreenshotUtils.takeScreenshot(this.webDriver, "order-" + orderNumber);
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

    @DataProvider(name = "shipping-address")
    public static Object[][] getShippingAddress() {
        String sheetName = "shipping-address";
        return ExcelDataReader.readData(sheetName);
    }
}
