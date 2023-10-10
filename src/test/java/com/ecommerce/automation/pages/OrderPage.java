package com.ecommerce.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderPage extends BasePage {

    private By myAccounts = By.xpath("//ul[@class='header links']/li/a[text()='My Account']");


    private By welcomeMenu = By.xpath("//button[@class='action switch' and @data-action='customer-menu-toggle']");
    private By ordersLink = By.xpath("//a[text()='My Orders']");

    private By latestOrders = By.xpath("//table[@id='my-orders-table']/tbody/tr/td[@data-th='Actions']/a[@class='action view']");
    private LoginPage loginPage;
    private WebDriver webDriver;

    public OrderPage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
        this.loginPage = new LoginPage(webDriver);
    }


    public void navigateAccountsPage() {
        this.webDriver.findElement(myAccounts).click();
    }

    public void navigateWelcomeMenu() {
        this.webDriver.findElement(welcomeMenu).click();
    }

    public void navigateToOrders() {
        this.webDriver.findElement(ordersLink).click();
    }

    public void viewLatestOrder() {
        this.webDriver.findElement(latestOrders).click();
    }

    public void signOut() {
        WebElement webElement = webDriver.findElement(By.xpath(String.format("//*[contains(text(), '%s')]", "Sign Out")));
        webElement.click();
    }
}
