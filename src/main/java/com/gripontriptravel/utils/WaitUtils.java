package com.gripontriptravel.utils;


import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class WaitUtils {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private static final int DEFAULT_TIMEOUT = 25;

    public WaitUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        this.wait.pollingEvery(Duration.ofMillis(300));
        this.wait.ignoring(NoSuchElementException.class);
        this.wait.ignoring(StaleElementReferenceException.class);
    }

    // ----------------------- VISIBILITY -----------------------
    public WebElement waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    public void waitForAllVisible(List<WebElement> elements) {
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    // ----------------------- CLICKABLE -----------------------
    public void waitForClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // ----------------------- SAFE CLICK -----------------------
    public void safeClick(WebElement element) {
        scrollIntoView(element);
        waitForVisibility(element);
        waitForClickable(element);

        for (int i = 0; i < 3; i++) {
            try {
                element.click();
                return;
            } catch (Exception e) {
                sleep();
            }
        }

        // fallback JS click
        jsClick(element);
    }

    private void jsClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    // ----------------------- TYPE INPUT -----------------------
    public void type(WebElement element, String text) {
        waitForVisibility(element);
        scrollIntoView(element);

        try {
            element.clear();
        } catch (Exception ignored) {}

        element.sendKeys(text);
    }

    // ----------------------- SCROLL -----------------------
    public void scrollIntoView(WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center'});", element);
        } catch (Exception ignored) {}
    }

    // ----------------------- STALE ELEMENT RETRY -----------------------
    public void retryUntilVisible(WebElement element) {
        for (int i = 0; i < 5; i++) {
            try {
                waitForVisibility(element);
                return;
            } catch (StaleElementReferenceException e) {
                sleep();
            }
        }
        waitForVisibility(element);
    }

    // ----------------------- PAGE LOAD -----------------------
    public void waitForPageLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait until DOM is interactive OR complete
        ExpectedCondition<Boolean> pageLoadCondition = drv ->
        {
            assert drv != null;
            return Objects.requireNonNull(((JavascriptExecutor) drv).executeScript("return document.readyState"))
                    .toString().matches("interactive|complete");
        };

        wait.until(pageLoadCondition);
    }



    // ----------------------- AJAX / FETCH WAIT -----------------------
    public void waitForAjax() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        ExpectedCondition<Boolean> ajaxComplete = drv ->
        {
            assert drv != null;
            return (Boolean) ((JavascriptExecutor) drv)
                    .executeScript("return window.jQuery != undefined && jQuery.active === 0");
        };

        try {
            wait.until(ajaxComplete);
        } catch (Exception e) {
            // If jQuery is not present, ignore; some pages may not use it
        }
    }


    // ----------------------- LOADER / SPINNER -----------------------
    public void waitForLoaderToDisappear(WebElement loader) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(15))
                    .until(ExpectedConditions.invisibilityOf(loader));
        } catch (Exception ignored) {}
    }

    // ----------------------- SLEEP -----------------------
    private void sleep() {
        try { Thread.sleep(300); }
        catch (InterruptedException ignored) {}
    }

    public void waitForUrlToBe(String expectedUrl) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(15))
                    .until(driver -> Objects.equals(driver.getCurrentUrl(), expectedUrl));
        } catch (TimeoutException e) {
            throw new AssertionError("Expected URL: " + expectedUrl + ", but got: " + driver.getCurrentUrl());
        }
    }
}
