package tests;


import com.gripontriptravel.base.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("User Management Module")
@Feature("Login")
public class LoginTests extends BaseTest {

    @Description("Login user with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Test(groups = {"smoke", "sanity", "regression"})
    public void testLoginValidUser() {
        home.clickLoginLink();
        var register = home.clickRegistrationPageLink();
        var login = register.clickSignInHereLink();
        login.loginValidUser();
    }

    @Description("Login user with invalid credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Test(groups = {"smoke", "sanity", "regression"})
    public void testLoginInValidUser() {
        home.clickLoginLink();
        var register = home.clickRegistrationPageLink();
        var login = register.clickSignInHereLink();
        login.loginInvalidUser();
    }

    @Description("Login user with invalid email format")
    @Severity(SeverityLevel.CRITICAL)
    @Test(groups = {"smoke", "sanity", "regression"})
    public void testLoginWithInvalidEmail() {
        home.clickLoginLink();
        var register = home.clickRegistrationPageLink();
        var login = register.clickSignInHereLink();
        login.loginWithInvalidEmailFormat();
    }

    @Description("Login user with empty fields")
    @Severity(SeverityLevel.CRITICAL)
    @Test(groups = {"smoke", "sanity", "regression"})
    public void testLoginWithEmptyFields() {
        home.clickLoginLink();
        var register = home.clickRegistrationPageLink();
        var login = register.clickSignInHereLink();
        login.loginWithEmptyFields();
    }






}
