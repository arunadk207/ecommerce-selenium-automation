package com.ecommerce.automation.pages;

import org.openqa.selenium.WebDriver;

public class LandingPage extends BasePage {

    private String logo = "/Magento/luma/en_US/images/logo.svg";

    public LandingPage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isLogoPresent() {
        return isImageElementPresent(logo);
    }
}
