package com.ecommerce.automation.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class ScreenshotUtils {

    public static void takeScreenshot(WebDriver webDriver, String fileName) {
        File screenshotAs = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshotAs, new File("output/"+fileName+".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
