package com.gripontriptravel.base;


import com.gripontriptravel.utils.*;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class BaseTest {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    protected WaitUtils wait;

    public WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browserName) {

        ConfigReader.loadConfig();
        String browser = (browserName != null) ? browserName : ConfigReader.getProperty("browser");
        String url = ConfigReader.getProperty("baseURL");

        WebDriver driver = DriverFactory.initializeDriver(browser);
        driverThreadLocal.set(driver);

        driver.manage().window().maximize();
        driver.get(url);

        wait = new WaitUtils(driver);
        wait.waitForPageLoad();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {

        WebDriver driver = getDriver();

        if (driver != null) {

            if (result.getStatus() == ITestResult.FAILURE) {
                ScreenshotUtil.takeScreenshot(driver);
                ScreenshotUtil.attachTextLog("Failure: " + result.getThrowable());
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                ScreenshotUtil.takeScreenshot(driver);
            }

            driver.quit();
        }

        driverThreadLocal.remove();
    }
}

