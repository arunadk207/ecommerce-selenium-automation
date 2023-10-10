package com.ecommerce.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    private WebDriver webDriver;

    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    public boolean isTextElementPresent(String text) {
        try {
            WebElement webElement = webDriver.findElement(By.xpath(String.format("//*[text()='%s']", text)));
            return waitForElement(webElement);
        } catch (Exception e) {
            return false;
        }
    }


    public boolean isTextContains(String text) {
        try {
            WebElement webElement = webDriver.findElement(By.xpath(String.format("//*[contains(text(), '%s')]", text)));
            return waitForElement(webElement);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isImageElementPresent(String imagePath) {
        try {
            String format = String.format("//img[contains(@src, '%s')]", imagePath);
            WebElement webElement = webDriver.findElement(By.xpath(format));
            return waitForElement(webElement);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean waitForElement(WebElement webElement) {
        return waitForElement(webElement, 5);
    }

    public boolean waitForElement(WebElement webElement, int duration) {
        Wait<WebDriver> wait = new WebDriverWait(webDriver, Duration.ofSeconds(duration));
        return wait.until(d -> webElement.isDisplayed());
    }

    public boolean explicitWaitForElement(By xpath, String textToBePresent) {
        Wait<WebDriver> wait = new FluentWait<>(webDriver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(ElementNotInteractableException.class);

        WebElement webElement = wait.until(d -> {
            WebElement element = webDriver.findElement(xpath);
            return element;
        });

        Boolean until = wait.until(ExpectedConditions.textToBePresentInElement(webElement, textToBePresent));
        return until;
    }
}
