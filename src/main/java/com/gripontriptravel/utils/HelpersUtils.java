package com.gripontriptravel.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HelpersUtils {

    protected WaitUtils wait;
    protected WebDriver driver;

    public HelpersUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }


    // ---------------- UNIVERSAL HELPERS ----------------
    public void safeClick(WebElement element) {
        wait.waitForVisibility(element);
        wait.waitForClickable(element);
        try {
            scrollIntoView(element);
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    public void scrollIntoView(WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block: 'center'});", element);
        } catch (Exception ignored) {}
    }

    public void type(WebElement element, String text) {
        wait.waitForVisibility(element);
        scrollIntoView(element);
        element.clear();
        element.sendKeys(text);
    }

    public void waitForPageReady() {
        wait.waitForPageLoad();
    }
}
