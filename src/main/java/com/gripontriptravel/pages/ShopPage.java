package com.gripontriptravel.pages;

import com.gripontriptravel.utils.HelpersUtils;
import com.gripontriptravel.utils.WaitUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShopPage {

    protected WebDriver driver;
    protected WaitUtils wait;
    protected HelpersUtils help;

    public ShopPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
        this.help = new HelpersUtils(driver);
        PageFactory.initElements(driver, this);
    }


}
