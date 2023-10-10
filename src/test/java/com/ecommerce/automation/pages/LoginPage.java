package com.ecommerce.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    private WebDriver webDriver;

    private By signOnLink = By.xpath("//a[contains(@href, 'login')]");
    private By userNameTextField = By.xpath("//input[@id='email']");
    private By passwordTextField = By.xpath("//input[@id='pass']");
    private By signInButton = By.xpath("//button[@id='send2']");
    private By customerLoginText = By.xpath("//*[text()='Customer Login']");

    private By welcomeUserName = By.xpath("//*[text()='Welcome, demo selenium!']");

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
    }

    public boolean isSignOnLinkDisplayed() {
        WebElement element = this.webDriver.findElement(signOnLink);
        return waitForElement(element);
    }

    public boolean isCustomerLoginTextPresent() {
        WebElement element = this.webDriver.findElement(customerLoginText);
        boolean b = waitForElement(element);
        return b;
    }

    public boolean isCustomerLoginTitleDisplayed() {
        WebElement element = this.webDriver.findElement(welcomeUserName);
        return waitForElement(element);
    }

    public void signOn() {
        WebElement element = this.webDriver.findElement(signOnLink);
        if (waitForElement(element)) {
            element.click();
        }
    }

    public void enterUserName(String userName) {
        WebElement element = this.webDriver.findElement(userNameTextField);
        if (waitForElement(element)) {
            element.sendKeys(userName);
        }
    }

    public void enterPassword(String password) {
        WebElement element = this.webDriver.findElement(passwordTextField);
        if (waitForElement(element)) {
            element.sendKeys(password);
        }
    }

    public void clickOnSignIn() {
        WebElement element = this.webDriver.findElement(signInButton);
        if (waitForElement(element)) {
            element.click();
        }
    }

}
