package com.ecommerce.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ShippingPage extends BasePage {

    private By company = By.xpath("//input[@name='company']");
    private By address = By.xpath("//input[@name='street[0]']");
    private By city = By.xpath("//input[@name='city']");
    private By postalCode = By.xpath("//input[@name='postcode']");
    private By phoneNumber = By.xpath("//input[@name='telephone']");

    private By flatRateShippingMethod = By.xpath("//input[@type='radio' and @value='flatrate_flatrate']");

    private By navigateToReviewAndPayment = By.xpath("//button[@data-role='opc-continue']");
    private WebDriver webDriver;

    public ShippingPage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
    }

    public void enterCompany(String companyName) {
        WebElement element = this.webDriver.findElement(company);
        if (waitForElement(element)) {
            element.sendKeys(companyName);
        }
    }

    public void enterAddress(String addressName) {
        this.webDriver.findElement(address).sendKeys(addressName);
    }

    public void enterCity(String cityName) {
        this.webDriver.findElement(city).sendKeys(cityName);
    }

    public void enterPostalCode(String zipCode) {
        System.out.println("ZIP: " + zipCode);
        this.webDriver.findElement(postalCode).sendKeys(zipCode);
    }

    public void enterPhoneNumber(String pNumber) {
        System.out.println("Phone: " + pNumber);
        this.webDriver.findElement(phoneNumber).sendKeys(pNumber);
    }

    public void chooseShippingMethod(String methodOfShipping) {
        System.out.println("Phone: " + methodOfShipping);
        this.webDriver.findElement(flatRateShippingMethod).click();
    }

    public void chooseState(String state) {
        WebElement element = this.webDriver.findElement(By.xpath("//select[@name='region_id']"));
        Select statesDropDown = new Select(element);
        statesDropDown.selectByIndex(1);
    }

    public void goToNextPage() {
        this.webDriver.findElement(navigateToReviewAndPayment).click();
    }

    public void clickOnApplyDiscount() {
        By xpath = By.xpath("//span[@id='block-discount-heading']");
        WebElement element = this.webDriver.findElement(xpath);
        // new WebDriverWait(this.webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(xpath)).click();

        new Actions(this.webDriver).moveToElement(element).click().perform();

    }

    public boolean isNewShippingAddressButtonExist() {
        By xpath = By.xpath("//button/span[text()='New Address']");
        return this.webDriver.findElement(xpath).isDisplayed();
    }

    public void isPlaceOrderButtonPresent() {
        By xpath = By.xpath("//button[@title='Place Order']");
        WebElement element = this.webDriver.findElement(xpath);
        if (waitForElement(element)) {
            System.out.println(element.isDisplayed());
        }
    }

    public void applyDiscountCode() {
        WebElement element = this.webDriver.findElement(By.xpath("//input[@id='discount-code']"));
        element.sendKeys("TEES20");
    }

    public void applyDiscount() {
        WebElement element = this.webDriver.findElement(By.xpath("//button[@value='Apply Discount']"));
        element.click();
    }

    public void placeOrder() {
        By xpath = By.xpath("//button[@title='Place Order']");
        WebElement element = this.webDriver.findElement(xpath);
        element.click();
    }

    public String readOrderNumber() {
        String text = "";
        WebElement element = this.webDriver.findElement(By.xpath("//a[@class='order-number']/strong"));
        if (waitForElement(element)) {

            text = element.getText();
        }
        return text;
    }
}
