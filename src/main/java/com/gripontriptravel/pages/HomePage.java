package com.gripontriptravel.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.gripontriptravel.utils.WaitUtils;
import com.gripontriptravel.utils.HelpersUtils;


public class HomePage {

    protected WebDriver driver;
    protected WaitUtils wait;
    protected HelpersUtils help;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
        this.help = new HelpersUtils(driver);
        PageFactory.initElements(driver, this);
        help.waitForPageReady();
    }


    @FindBy(xpath = "//span[normalize-space()='Home']")
    private WebElement homePageLink;

    @FindBy(xpath = "//span[normalize-space()='Shop']")
    private WebElement shopPageLink;

    @FindBy(xpath = "//span[normalize-space()='Booking']")
    private WebElement bookingPageLink;

    @FindBy(xpath = "//span[normalize-space()='Travel Agency']")
    private WebElement travelAgencyPageLink;

    @FindBy(xpath = "//span[normalize-space()='Rentals']")
    private WebElement rentalsPageLink;

    @FindBy(xpath = "//button[@aria-label='Login']//*[name()='svg']")
    private WebElement loginLink;

    @FindBy(xpath = "//a[normalize-space()='Sign in here']")
    private WebElement loginPageLink;

    @FindBy(xpath = "//a[normalize-space()='Create Account']")
    private WebElement signUpPageLink;

    @FindBy(xpath = "//input[@placeholder='Where to?']")
    private WebElement homeSearchField;

    @FindBy(xpath = "(//span[normalize-space()=\"Let's Go\"])[1]")
    private WebElement searchBtn;


    public ShopPage clickShopPageLink() {
        help.safeClick(shopPageLink);
        return new ShopPage(driver);
    }

    public BookingPage clickBookingPageLink(){
        help.safeClick(bookingPageLink);
        return new BookingPage(driver);
    }

    public TravelAgencyPage clickTravelAgencyPageLink(){
        help.safeClick(travelAgencyPageLink);
        return new TravelAgencyPage(driver);
    }

    public RentalsPage clickRentalsPageLink(){
        help.safeClick(rentalsPageLink);
        return new RentalsPage(driver);
    }

    public RegistrationPage clickRegistrationPageLink(){
        help.safeClick(signUpPageLink);
        return new RegistrationPage(driver);
    }

    public LoginPage clickLoginPageLink(){
        help.safeClick(loginPageLink);
        return new LoginPage(driver);
    }

    public void clickLoginLink(){
        help.safeClick(loginLink);
    }


}



