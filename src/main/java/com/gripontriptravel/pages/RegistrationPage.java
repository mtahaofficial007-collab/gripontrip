package com.gripontriptravel.pages;

import com.gripontriptravel.utils.HelpersUtils;
import com.gripontriptravel.utils.WaitUtils;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;


public class RegistrationPage {

    protected WebDriver driver;
    protected WaitUtils wait;
    protected HelpersUtils help;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
        this.help = new HelpersUtils(driver);
        PageFactory.initElements(driver, this);
        help.waitForPageReady();
    }

    @FindBy(xpath = "//a[@class='text-sm font-semibold transition-colors hover:underline']")
    private WebElement signInHereLink;

    @FindBy(xpath = "//input[@id='signup_name']")
    private WebElement nameField;

    @FindBy(xpath = "//input[@id='signup_email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@id='signup_phone']")
    private WebElement phoneField;

    @FindBy(xpath = "//input[@id='signup_password']")
    private WebElement passwordField;

    @FindBy(xpath = "//span[normalize-space()='User']")
    private WebElement userAccountBtn;

    @FindBy(xpath = "//span[normalize-space()='Hotel']")
    private WebElement hotelAccountBtn;

    @FindBy(xpath = "//div[3]//label[1]//span[2]")
    private WebElement agencyAccountBtn;

    @FindBy(xpath = "//span[normalize-space()='Create Account']")
    private WebElement createAccountBtn;

    @FindBy(xpath = "//a[@class='footer-link primary-link']")
    private WebElement createAccountLink;

    @FindBy(xpath = "//div[contains(text(),'Please enter your name!')]")
    private WebElement enterYourNameMsg;

    @FindBy(xpath = "//div[contains(text(),'Please enter your email!')]")
    private WebElement enterYourEmailMsg;

    @FindBy(xpath = "//div[contains(text(),'Please enter your phone number!')]")
    private WebElement enterYourPhoneMsg;

    @FindBy(xpath = "//div[contains(text(),'Please enter your password!')]")
    private WebElement enterYourPasswordMsg;

    @FindBy(xpath = "//div[contains(text(),'Please enter a valid email address!')]")
    private WebElement enterValidEmailMsg;

    @FindBy(xpath = "//div[contains(text(),'Password must be at least 6 characters.')]")
    private WebElement enterValidPasswordMsg;

    @FindBy(xpath = "//span[normalize-space()='Error: User already registered']")
    private WebElement userAlreadyExistsMsg;

    public LoginPage clickSignInHereLink(){
        help.safeClick(signInHereLink);
        return new LoginPage(driver);
    }

    public void createUserAccount() {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String username = "user" + timestamp;
        String email = "user" + timestamp + "@testmail.com";
        String phone = "92331" + timestamp.substring(7, 12);
        String password = "taha1234";
        help.type(nameField, username);
        help.type(emailField, email);
        help.type(phoneField, phone);
        help.type(passwordField, password);
        help.safeClick(userAccountBtn);
        help.safeClick(createAccountBtn);
    }

    public void createHotelAccount() {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String username = "hotel" + timestamp;
        String email = "hotel" + timestamp + "@testmail.com";
        String phone = "92331" + timestamp.substring(7, 12);
        String password = "taha1234";
        help.type(nameField, username);
        help.type(emailField, email);
        help.type(phoneField, phone);
        help.type(passwordField, password);
        help.safeClick(userAccountBtn);
        help.safeClick(createAccountBtn);
    }

    public void createAgencyAccount() {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String username = "agency" + timestamp;
        String email = "agency" + timestamp + "@testmail.com";
        String phone = "92331" + timestamp.substring(7, 12);
        String password = "taha1234";
        help.type(nameField, username);
        help.type(emailField, email);
        help.type(phoneField, phone);
        help.type(passwordField, password);
        help.safeClick(agencyAccountBtn);
        help.safeClick(createAccountBtn);
    }

    public void registerExistingUser(String username, String email, String phone, String password) {
        help.type(nameField, username);
        help.type(emailField, email);
        help.type(phoneField, phone);
        help.type(passwordField, password);
        help.safeClick(userAccountBtn);
        help.safeClick(createAccountBtn);
        wait.waitForVisibility(userAlreadyExistsMsg);
        Assert.assertTrue(userAlreadyExistsMsg.isDisplayed());
    }

    public void registerUserWithEmptyFields(String username, String email, String phone, String password) {
        help.type(nameField, username);
        help.type(emailField, email);
        help.type(phoneField, phone);
        help.type(passwordField, password);
        help.safeClick(userAccountBtn);
        help.safeClick(createAccountBtn);
        wait.waitForVisibility(enterYourNameMsg);
        Assert.assertTrue(enterYourNameMsg.isDisplayed());
        wait.waitForVisibility(enterYourEmailMsg);
        Assert.assertTrue(enterYourEmailMsg.isDisplayed());
        wait.waitForVisibility(enterYourPhoneMsg);
        Assert.assertTrue(enterYourPhoneMsg.isDisplayed());
        wait.waitForVisibility(enterYourPasswordMsg);
        Assert.assertTrue(enterYourPasswordMsg.isDisplayed());
    }

    public void registerUserWithWrongEmailFormat() {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String username = "user" + timestamp;
        String email = "user" + timestamp + "testmail.com";
        String phone = "92331" + timestamp.substring(7, 12);
        String password = "taha1234";
        help.type(nameField, username);
        help.type(emailField, email);
        help.type(phoneField, phone);
        help.type(passwordField, password);
        help.safeClick(userAccountBtn);
        help.safeClick(createAccountBtn);
        Assert.assertTrue(enterValidEmailMsg.isDisplayed());
    }

    public void registerUserWithShortPassword() {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String username = "user" + timestamp;
        String email = "user" + timestamp + "@testmail.com";
        String phone = "92331" + timestamp.substring(7, 12);
        String password = "tah";
        help.type(nameField, username);
        help.type(emailField, email);
        help.type(phoneField, phone);
        help.type(passwordField, password);
        help.safeClick(userAccountBtn);
        help.safeClick(createAccountBtn);
        Assert.assertTrue(enterValidPasswordMsg.isDisplayed());
    }



}
