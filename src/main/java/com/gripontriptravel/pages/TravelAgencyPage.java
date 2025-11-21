package com.gripontriptravel.pages;

import com.gripontriptravel.utils.HelpersUtils;
import com.gripontriptravel.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class TravelAgencyPage {

    protected WebDriver driver;
    protected WaitUtils wait;
    protected HelpersUtils help;

    public TravelAgencyPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
        this.help = new HelpersUtils(driver);
        PageFactory.initElements(driver, this);
    }

}
