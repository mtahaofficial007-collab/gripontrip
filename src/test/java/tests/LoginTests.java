package tests;


import com.gripontriptravel.base.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("User Management Module")
@Feature("Login")
public class LoginTests extends BaseTest {

    @Description("Login user with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 1, groups = {"smoke", "sanity", "regression"})
    public void testLoginValidUser() {
        var login = home.clickLoginLink();
        login.loginValidUser();
    }

    @Description("Login user with invalid credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 2, groups = {"smoke", "sanity", "regression"})
    public void testLoginInValidUser() {
        var login = home.clickLoginLink();
        login.loginInvalidUser();
    }

    @Description("Login user with invalid email format")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 3, groups = {"smoke", "sanity", "regression"})
    public void testLoginWithInvalidEmail() {
        var login = home.clickLoginLink();
        login.loginWithInvalidEmailFormat();
    }

    @Description("Login user with empty fields")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 4, groups = {"smoke", "sanity", "regression"})
    public void testLoginWithEmptyFields() {
        var login = home.clickLoginLink();
        login.loginWithEmptyFields();
    }

}
