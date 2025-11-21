package com.gripontriptravel.pages;

import com.gripontriptravel.utils.HelpersUtils;
import com.gripontriptravel.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    protected WebDriver driver;
    protected WaitUtils wait;
    protected HelpersUtils help;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
        this.help = new HelpersUtils(driver);
        PageFactory.initElements(driver, this);
        help.waitForPageReady();
    }

    @FindBy(xpath = "//input[@id='login_form_email']")
    private WebElement loginNameField;

    @FindBy(xpath = "//input[@id='login_form_password']")
    private WebElement loginPasswordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginSigninBtn;


}
