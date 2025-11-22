package com.gripontriptravel.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver initializeDriver(String browser) {

        WebDriver webDriver;

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();

                ChromeOptions options = new ChromeOptions();

                options.setPageLoadStrategy(PageLoadStrategy.EAGER);

// Chrome binary (only if not using default installation)
                options.setBinary("C:\\Users\\QCS\\AppData\\Local\\Google\\Chrome\\Application\\chrome.exe");

// Headless if on Jenkins
                if (System.getenv("JENKINS_HOME") != null ||
                        System.getProperty("headless", "false").equals("true")) {
                    options.addArguments("--headless=new");
                }

                // ---------------------------
                // STABLE OPTIONS FOR BOTH LOCAL & CI
                // ---------------------------
                options.addArguments("--remote-allow-origins=*");
                options.addArguments("--window-size=1920,1080");

                // ---------------------------
                // REMOVE HARD CODED BINARY (BREAKS CI)
                // ---------------------------
                // If you want custom binary, uncomment below:
                // options.setBinary(ConfigReader.getProperty("chromeBinary"));

                webDriver = new ChromeDriver(options);
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                webDriver = new FirefoxDriver();
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                webDriver = new EdgeDriver();
                break;

            default:
                throw new RuntimeException("Invalid browser: " + browser);
        }

        driver.set(webDriver);
        return getDriver();
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
