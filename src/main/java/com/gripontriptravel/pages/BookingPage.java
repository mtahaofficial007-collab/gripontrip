package com.gripontriptravel.pages;

import com.gripontriptravel.utils.HelpersUtils;
import com.gripontriptravel.utils.WaitUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class BookingPage {

    protected WebDriver driver;
    protected WaitUtils wait;
    protected HelpersUtils help;

    public BookingPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
        PageFactory.initElements(driver, this);
        help.waitForPageReady();
    }


}
