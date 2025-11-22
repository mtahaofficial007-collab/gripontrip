package tests;

import com.gripontriptravel.base.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;


@Epic("User Management Module")
@Feature("Registration")
public class RegistrationTests extends BaseTest {


    @Description("Registering new user")
    @Severity(SeverityLevel.CRITICAL)
    @Test(groups = {"smoke", "regression"})
    public void testRegisterNewUser() {
        home.clickLoginLink();
        var register = home.clickRegistrationPageLink();
        register.createUserAccount();
    }

    @Description("Registering hotel account")
    @Severity(SeverityLevel.CRITICAL)
    @Test(groups = {"smoke", "regression"})
    public void testRegisterHotelAccount() {
        home.clickLoginLink();
        var register = home.clickRegistrationPageLink();
        register.createHotelAccount();
    }

    @Description("Registering agency account")
    @Severity(SeverityLevel.CRITICAL)
    @Test(groups = {"smoke", "regression"})
    public void testRegisterAgencyAccount() {
        home.clickLoginLink();
        var register = home.clickRegistrationPageLink();
        register.createAgencyAccount();
    }

    @Description("Registering existing user")
    @Severity(SeverityLevel.CRITICAL)
    @Test(groups = {"sanity", "regression"})
    public void testRegisterExistingUser() {
        home.clickLoginLink();
        var register = home.clickRegistrationPageLink();
        register.registerExistingUser("Muhammad Taha", "m.tahaofficial007@gmail.com", "923315604180", "Taha1234");
    }

    @Description("Registering user with short password")
    @Severity(SeverityLevel.CRITICAL)
    @Test(groups = {"sanity", "regression"})
    public void testRegistrationWithShortPassword() {
        home.clickLoginLink();
        var register = home.clickRegistrationPageLink();
        register.registerUserWithShortPassword();
    }

    @Description("Registering user with wrong email format")
    @Severity(SeverityLevel.CRITICAL)
    @Test(groups = {"sanity", "regression"})
    public void testRegistrationWithWrongEmailFormat() {
        home.clickLoginLink();
        var register = home.clickRegistrationPageLink();
        register.registerUserWithWrongEmailFormat();
    }

    @Description("Registering user with empty fields")
    @Severity(SeverityLevel.CRITICAL)
    @Test( groups = {"sanity", "regression"})
    public void testRegisterUserWithEmptyFields() {
        home.clickLoginLink();
        var register = home.clickRegistrationPageLink();
        register.registerUserWithEmptyFields("", "", "", "");
    }


}
