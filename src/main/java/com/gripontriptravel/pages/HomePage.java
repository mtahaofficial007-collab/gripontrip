package com.gripontriptravel.pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.gripontriptravel.utils.WaitUtils;
import com.gripontriptravel.utils.HelpersUtils;

import java.io.File;


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

    @FindBy(xpath = "//*[@id=\"root\"]/nav")
    private WebElement nav;

    public ShopPage clickShopPageLink() {

        try {
            // 1️⃣ Wait for the parent navigation/menu to be visible
            wait.waitForVisibility(nav);

            // 2️⃣ Wait for the Shop link to exist in DOM
           WebElement link =  wait.waitForVisibility(shopPageLink);


            // 3️⃣ Scroll element into view
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", link);

            // 4️⃣ Wait until clickable
            wait.waitForClickable(link);

            // 5️⃣ Try normal click
            try {
                link.click();
            } catch (ElementClickInterceptedException e) {
                // fallback to JS click if normal click fails
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
            }

            return new ShopPage(driver);

        } catch (TimeoutException e) {
            // 6️⃣ Take a screenshot for debugging in headless CI
            try {
                File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(src, new File("headless_debug_" + System.currentTimeMillis() + ".png"));
                System.out.println("Screenshot saved due to timeout!");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            throw new RuntimeException("Failed to click Shop link in headless mode", e);
        }
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

    public LoginPage clickLoginLink(){
        help.safeClick(loginLink);
        return new LoginPage(driver);
    }


}



