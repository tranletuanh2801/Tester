package testcases;

import common.Constant;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;
import common.Utilities;


public class Register {

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Pre-condition");
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Post-condition");
        Constant.WEBDRIVER.quit();
    }

    @Test

    public void TC07() {
        System.out.println("TC07 - User can create new account");

        HomePage homePage = new HomePage();
        homePage.open();

        RegisterPage registerPage = homePage.gotoRegisterPage();

        String email = "neww@email.com";
        String password = "Password123";
        String confirmPassword = "Password123";
        String PID = "123456789";

        registerPage.enterEmail(email);
        registerPage.enterPassword(password);
        registerPage.enterConfirmPassword(confirmPassword);
        registerPage.enterPID(PID);
        registerPage.clickRegisterButton();

        String expectedMessage = "Thank you for registering your account";
        String actualMessage = registerPage.getConfirmationMessage();
        Assert.assertEquals(actualMessage, expectedMessage, "Confirmation message is not displayed as expected");
    }

    @Test
    public void TC08() {
        System.out.println("TC08 - User can't login with an account hasn't been activated");

        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();

        String unactivatedUsername = "unactivated_user@gmail.com";
        String password = "Password123";

        loginPage.login(unactivatedUsername, password);

        String actualErrorMsg = loginPage.getErrorMessage();
        String expectedErrorMsg = "Invalid username or password. Please try again.";

        Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Error message is not displayed as expected for unactivated account");
    }

    @Test

    public void TC09() {
        System.out.println("TC09 - User can change password");

        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login("moi123@gmail.com", "123456789");

        ChangePasswordPage changePasswordPage = homePage.gotoChangePasswordPage();

        String currentPassword = "123456789";
        String newPassword = "12345678";
        String confirmPassword = "12345678";

        changePasswordPage.enterCurrentPassword(currentPassword);
        changePasswordPage.enterNewPassword(newPassword);
        changePasswordPage.enterConfirmPassword(confirmPassword);
        changePasswordPage.clickChangePasswordButton();

        String actualMessage = changePasswordPage.getConfirmationMessage();
        String expectedMessage = "Your password has been updated";

        Assert.assertEquals(actualMessage, expectedMessage, "Confirmation message is not displayed as expected");
    }
    @Test
    public void TC10() {
        System.out.println("TC10 - User can't create account with 'Confirm password' not matching 'Password'");
        HomePage homePage = new HomePage();
        homePage.open();

        RegisterPage registerPage = homePage.gotoRegisterPage();

        String email = "newuser@gmail.com";
        String password = "Password123";
        String confirmPassword = "DifferentPassword";
        String PID = "123456789";

        registerPage.enterEmail(email);
        registerPage.enterPassword(password);
        registerPage.enterConfirmPassword(confirmPassword);
        registerPage.enterPID(PID);
        registerPage.clickRegisterButton();

        String actualErrorMessage = registerPage.getErrorMessage();
        String expectedErrorMessage = "There're errors in the form. Please correct the errors and try again.";

        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message is not displayed as expected when passwords do not match");
    }

    @Test
    public void TC11(){
        System.out.println("TC11 - User can't create account while password and PID fields are empty");

        HomePage homePage = new HomePage();
        homePage.open();

        RegisterPage registerPage = homePage.gotoRegisterPage();
        registerPage.enterEmailAddress("test@example.com");
        registerPage.enterPassword("");
        registerPage.enterConfirmPassword("");
        registerPage.enterPID("");
        registerPage.clickRegisterButton();

        String formErrorMessage = registerPage.getErrorMessageAboveForm();
        Assert.assertEquals(formErrorMessage, "There're errors in the form. Please correct the errors and try again.");

        String passwordErrorMessage = registerPage.getErrorMessagePasswordField();
        Assert.assertEquals(passwordErrorMessage, "Invalid password length.");

        String pidErrorMessage = registerPage.getErrorMessagePIDField();
        Assert.assertEquals(pidErrorMessage, "Invalid ID length.");
    }
}
