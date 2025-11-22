package tests;

import com.gripontriptravel.base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;


@Epic("Shop Module")
@Feature("User Shop Feature")
public class ShopTests extends BaseTest {


    @Description("Verify Shop Page Loads Successfully")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 1, groups = {"smoke", "sanity", "regression"})
    public void testVerifyShopPageLoads() {
        var shop = home.clickShopPageLink();
        shop.shopPageFeaturesVisible();
    }


    @Description("Verify Product Search Works")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 2, groups = {"smoke", "regression"})
    public void testVerifyProductSearchFunctionality() {
        var shop = home.clickShopPageLink();
        shop.shopPageFeaturesVisible();
        shop.searchAndVerifyProduct("Men's Trendy Sneakers");
    }


    @Description("Verify quick view button")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 3, groups = {"smoke", "sanity", "regression"})
    public void testVerifyQuickViewOption() {
        var shop = home.clickShopPageLink();
        shop.shopPageFeaturesVisible();
        shop.clickFirstProduct(0);
        shop.clickQuickViewBtn();
    }


    @Description("Verify open product button")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 4, groups = {"smoke", "sanity", "regression"})
    public void testVerifyOpenProductOption() {
        var shop = home.clickShopPageLink();
        shop.shopPageFeaturesVisible();
        shop.clickFirstProduct(0);
        shop.clickOpenProductBtn();
    }


    @Description("Verify Filter Product")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 5, groups = {"smoke", "sanity", "regression"})
    public void testVerifyProductSortFunctionality() {
        var shop = home.clickShopPageLink();
        shop.shopPageFeaturesVisible();
        shop.useFilterDropDown("Price Low-High");
        Assert.assertTrue(shop.verifyPricesAscending(), "Products are not sorted Low-High");
        shop.useFilterDropDown("Price High-Low");
        Assert.assertTrue(shop.verifyPricesDescending(), "Products are not sorted High-Low");
    }
}
