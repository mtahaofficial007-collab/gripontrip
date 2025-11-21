package com.gripontriptravel.pages;

import com.gripontriptravel.utils.HelpersUtils;
import com.gripontriptravel.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

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
    private WebElement loginEmailField;

    @FindBy(xpath = "//input[@id='login_form_password']")
    private WebElement loginPasswordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginSignInBtn;

    @FindBy(xpath = "//span[normalize-space()='Login successful!']")
    private WebElement loginSuccessfulMsg;

    @FindBy(xpath = "//div[@class='ant-alert-message']")
    private WebElement invalidCredentialsMsg;

    @FindBy(xpath = "//div[@class='ant-form-item-explain-error']")
    private WebElement invalidEmailMsg;

    @FindBy(xpath = "//div[contains(text(),'Please enter your email!')]")
    private WebElement enterEmailMsg;

    @FindBy(xpath = "//div[contains(text(),'Please enter your password!')]")
    private WebElement enterPasswordMsg;

    public void loginValidUser() {
        String email = "m.tahaofficial007@gmail.com";
        String password = "Taha1234";
        help.type(loginEmailField,email);
        help.type(loginPasswordField,password);
        help.safeClick(loginSignInBtn);
        wait.waitForVisibility(loginSuccessfulMsg);
        Assert.assertTrue(loginSuccessfulMsg.isDisplayed());
        wait.waitForUrlToBe("https://www.gripontrip.com/");
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.gripontrip.com/","Navigated to some other url");
    }

    public void loginInvalidUser(){
        String timestamp = String.valueOf(System.currentTimeMillis());
        String email = "user" + timestamp + "@testmail.com";
        String password = "taha1234";
        help.type(loginEmailField,email);
        help.type(loginPasswordField,password);
        help.safeClick(loginSignInBtn);
        wait.waitForVisibility(invalidCredentialsMsg);
        Assert.assertTrue(invalidCredentialsMsg.isDisplayed());
    }

    public void loginWithInvalidEmailFormat(){
        String timestamp = String.valueOf(System.currentTimeMillis());
        String email = "user" + timestamp + "testmail.com";
        String password = "taha1234";
        help.type(loginEmailField,email);
        help.type(loginPasswordField,password);
        help.safeClick(loginSignInBtn);
        wait.waitForVisibility(invalidEmailMsg);
        Assert.assertTrue(invalidEmailMsg.isDisplayed());
    }

    public void loginWithEmptyFields(){
        String email = "";
        String password = "";
        help.type(loginEmailField,email);
        help.type(loginPasswordField,password);
        help.safeClick(loginSignInBtn);
        wait.waitForVisibility(enterEmailMsg);
        Assert.assertTrue(enterEmailMsg.isDisplayed());
        wait.waitForVisibility(enterPasswordMsg);
        Assert.assertTrue(enterPasswordMsg.isDisplayed());
    }

}
