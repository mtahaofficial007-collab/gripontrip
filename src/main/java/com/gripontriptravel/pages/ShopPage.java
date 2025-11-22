package com.gripontriptravel.pages;

import com.gripontriptravel.utils.HelpersUtils;
import com.gripontriptravel.utils.WaitUtils;
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


    @FindBy(xpath = "//select[contains(@class,'border border-gray-300 rounded-full text-sm')]")
    private WebElement sortByDropdown;

    @FindBy(xpath = "//input[contains(@placeholder,'Search...')]")
    private WebElement shopProductSearch;

    @FindBy(xpath = "//button[contains(@class,'px-2 py-2 rounded-full text-sm')]")
    private WebElement filterBtn;

    @FindBy(css = ".product-card")
    private List<WebElement> productCards;

    @FindBy(xpath = "//button[normalize-space()='Quick View']")
    private WebElement quickViewBtn;

    @FindBy(xpath = "//button[normalize-space()='Quick View']")
    private WebElement openProductBtn;

    @FindBy(css = ".product-card img")
    private List<WebElement> productImages;

    @FindBy(css = "div.product-card div.absolute.top-0 span.text-sm:not(.text-white)")
    private List<WebElement> productPrices;


    public void shopPageFeaturesVisible() {
        wait.waitForAllVisible(productCards);
        wait.waitForVisibility(sortByDropdown);
        wait.waitForVisibility(shopProductSearch);
        wait.waitForVisibility(filterBtn);
        Assert.assertFalse(productCards.isEmpty(), "No product cards are found");
        Assert.assertTrue(shopProductSearch.isDisplayed());
        Assert.assertTrue(sortByDropdown.isDisplayed());
        Assert.assertTrue(filterBtn.isDisplayed());
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.gripontrip.com/collection", "Navigated to other url");
    }

    public boolean searchAndVerifyProduct(String keyword) {
        keyword = keyword.toLowerCase();
        help.type(shopProductSearch, keyword);
        wait.waitForAllVisible(productImages);

        if (productImages.isEmpty()) {
            System.out.println("No products found for keyword: " + keyword);
            return false;
        }

        for (WebElement img : productImages) {
            String altText = img.getAttribute("alt").toLowerCase();
            if (!altText.contains(keyword)) {
                System.out.println("Mismatch found: " + altText);
                return false;
            }
        }
        return true;
    }

    public void clickFirstProduct(int index) {
        wait.waitForAllVisible(productCards);
        if (index < productCards.size()) {
            help.safeClick(productCards.get(index));
        } else {
            throw new IllegalArgumentException("Invalid product index: " + index);
        }
    }

    public void clickQuickViewBtn() {
        wait.waitForVisibility(quickViewBtn);
        Assert.assertTrue(quickViewBtn.isDisplayed(), "Quick view button is not displayed");
        help.safeClick(quickViewBtn);
    }

    public void clickOpenProductBtn() {
        wait.waitForVisibility(openProductBtn);
        Assert.assertTrue(openProductBtn.isDisplayed(), "Open product button is not displayed");
        help.safeClick(openProductBtn);
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.gripontrip.com/collection", "Navigated to other url");
    }

    public void useFilterDropDown(String value) {
        wait.waitForVisibility(sortByDropdown);
        Assert.assertTrue(sortByDropdown.isDisplayed(), "Filter dropdown not displayed");
        Select select = new Select(sortByDropdown);
        select.selectByVisibleText(value);
    }

    private List<Double> extractPrices() {
        List<Double> prices = new ArrayList<>();
        for (WebElement priceEl : productPrices) {
            String text = priceEl.getText().replaceAll("\\d+%\\s*OFF", "").trim();
            Matcher matcher = Pattern.compile("(\\d+[.,]?\\d*)").matcher(text);
            if (matcher.find()) {
                prices.add(Double.parseDouble(matcher.group(1).replace(",", "")));
            }
        }
        return prices;
    }

    public boolean verifyPricesAscending() {
        List<Double> prices = extractPrices();
        for (int i = 0; i < prices.size() - 1; i++) {
            if (prices.get(i) > prices.get(i + 1)) return false;
        }
        return true;
    }

    public boolean verifyPricesDescending() {
        List<Double> prices = extractPrices();
        for (int i = 0; i < prices.size() - 1; i++) {
            if (prices.get(i) < prices.get(i + 1)) return false;
        }
        return true;
    }


}
