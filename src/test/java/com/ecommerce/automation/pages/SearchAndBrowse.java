package com.ecommerce.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchAndBrowse extends BasePage {

    private By saleLink = By.xpath("//a[@id='ui-id-8']");
    private By teesLink = By.xpath("//a[text()='Tees']");

    private By proceedToCheckoutButton = By.xpath("//button[@id='top-cart-btn-checkout']");
    private WebDriver webDriver;

    public SearchAndBrowse(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
    }

    public void chooseSales() {
        this.webDriver.findElement(saleLink).click();
    }

    public void chooseTees() {
        this.webDriver.findElements(teesLink).get(0).click();
    }

    public void selectMaterial() {
        this.webDriver.findElement(By.xpath("//*[text()='Material']")).click();
    }

    public void selectHemp() {
        this.webDriver.findElement(By.xpath("//a[contains(text(), 'Hemp')]")).click();
    }

    public void selectPattern() {
        this.webDriver.findElement(By.xpath("//*[text()='Pattern']")).click();
    }

    public void selectSolid() {
        this.webDriver.findElement(By.xpath("//*[contains(text(), 'Solid')]")).click();
    }

    public void selectClimate() {
        this.webDriver.findElement(By.xpath("//*[text()='Climate']")).click();
    }

    public void selectIndoor() {
        this.webDriver.findElement(By.xpath("//*[contains(text(), 'Indoor')]")).click();
    }

    public void chooseAddToCart() {
        List<WebElement> elements = this.webDriver.findElements(By.xpath("//div[@data-container='product-grid']"));
        WebElement element = elements.get(0);
        element.click();
//        Actions actions = new Actions(this.webDriver);
//        actions.click(element);
    }

    public void selectSize(String size) {
        WebElement element = this.webDriver.findElement(By.xpath("//div[@class='swatch-option text' and text()='S']"));
        element.click();

    }

    public void selectColor(String color) {
        WebElement element = this.webDriver.findElement(By.xpath("//div[@class='swatch-option color' and @option-label='Blue']"));
        element.click();

    }

    public void addToCart() {
        WebElement element = this.webDriver.findElement(By.xpath("//button[@id='product-addtocart-button']"));
        element.click();
        System.out.println("AddToCart");

    }

    public void waitForItemToBeAddedToCart(String itemCount) {
        By xpath = By.xpath("//span[@class='counter-label']");
        explicitWaitForElement(xpath, itemCount);
    }

    public void clickOnViewCartIcon() {
        WebElement element = this.webDriver.findElement(By.xpath("//a[@class='action showcart']"));
        if (waitForElement(element)) {
            element.click();
            System.out.println("Cart is clicked");
        } else {
            System.out.println("Cart is not clicked");
        }
    }

    public void clickOnViewAndEditCart() {
        WebElement element = this.webDriver.findElement(By.xpath("//*[text()='View and Edit Cart']"));
        element.click();
    }

    public void waterBottleAddToCart() {
        WebElement element = this.webDriver.findElements(By.xpath("//div[@class='product-item-info ']")).get(0);
        element.click();
    }

    public void proceedToCheckout() {
        WebElement element = this.webDriver.findElement(By.xpath("//button[@data-role='proceed-to-checkout']"));
        if (waitForElement(element, 5)) {
            element.click();
        }
    }


    public void navigateToCheckoutPage() {
        this.webDriver.findElement(proceedToCheckoutButton).click();
    }

}
