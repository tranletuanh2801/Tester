package testcases;

import common.Constant;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.*;
import common.Utilities;
import common.Constant;

import static org.testng.Assert.assertEquals;

public class LoginTest {

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Pre-condition");
        //System.setProperty("webdriver.chrome.driver", Utilities.getProjectPath()
        //+ "\\Executables\\chromedriver.exe");
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Post-condition");
        Constant.WEBDRIVER.quit();
    }

    @Test
    public void TC01() {
        System.out.println("TC01 - User can log into Railway with valid username and password");

        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        String actualMsg = loginPage.login(Constant.USERNAME, Constant.PASSWORD).getWelcomeMessage();
        String expectedMsg = "Welcome " + Constant.USERNAME;

        assertEquals(actualMsg, expectedMsg, "Welcome message is not displayed as expected");
    }

    @Test
    public void TC02() {
        System.out.println("TC02 - User can't login with blank 'Username' textbox");

        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login("", Constant.PASSWORD);
        String actualErrorMessage = loginPage.getErrorMessage();

        String expectedErrorMessage = "There was a problem with your login and/or errors exist in your form.";
        assertEquals(actualErrorMessage, expectedErrorMessage, "Error message is not displayed as expected.");
    }

    @Test
    public void TC03() {
        System.out.println("TC03 - User cannot log into Railway with invalid password");

        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, "invalid123");
        String actualErrorMessage = loginPage.getErrorMessage();
        System.out.println("Actual Error Message: " + actualErrorMessage);
        String expectedErrorMessage = "There was a problem with your login and/or errors exist in your form.";
        assertEquals(actualErrorMessage, expectedErrorMessage, "Error message for invalid password is not displayed as expected.");
    }

    @Test
    public void TC04() {
        System.out.println("TC04 - Verify that user is redirected to Login page after navigating to a protected page without login");

        HomePage homePage = new HomePage();
        homePage.open();
        BookTicketPage BookTicketPage = homePage.gotoBookTicketPage();

        String currentUrl = Constant.WEBDRIVER.getCurrentUrl();
        String expectedUrl = "http://railwayb1.somee.com/Account/Login.cshtml";
        Assert.assertTrue(currentUrl.startsWith(expectedUrl), "The user is not redirected to the login page as expected");
    }

    @Test
    public void TC05() {
        System.out.println("TC05 - System shows message when user enters wrong password several times");

        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        for (int i = 0; i < 4; i++) {
            loginPage.login(Constant.USERNAME, "wrongpassword");
        }
        String actualMsg = loginPage.getErrorMessage();
        String expectedMsg = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";
        assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected");
    }

    @Test
    public void TC06(){
        System.out.println("TC-6 - Additional pages display once user logged in");

        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        homePage = loginPage.login(Constant.USERNAME, Constant.PASSWORD);
        MyTicketPage myTicketPage = homePage.gotoMyTicketPage();

        Assert.assertNotNull(myTicketPage, "Failed to navigate to My ticket page.");
        ChangePasswordPage changePasswordPage = homePage.gotoChangePasswordPage();
        Assert.assertNotNull(changePasswordPage, "Failed to navigate to Change password page.");
    }
}

