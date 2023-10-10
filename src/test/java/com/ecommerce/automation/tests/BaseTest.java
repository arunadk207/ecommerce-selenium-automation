package com.ecommerce.automation.tests;

import com.ecommerce.automation.pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {

    protected WebDriver webDriver;

    protected void init() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
    }

    protected void releaseDriver() {
        webDriver.quit();
    }
}
