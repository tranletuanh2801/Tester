package testcases;

import common.Constant;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.*;

public class BookTicket {
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
    public void TC14() {
        System.out.println("TC14 - User can book 1 ticket at a time");

        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);
        BookTicketPage ticketBookingPage = homePage.gotoBookTicketPage();
        ticketBookingPage.bookTicket("11/16/2024","Huế","Sài Gòn","Soft bed with air conditioner",1);
        String actualMsg = ticketBookingPage.getSuccessMessage();
        String expectedMsg = "Ticket booked successfully!";
        Assert.assertEquals(actualMsg.trim(), expectedMsg.trim(), "Success message is not displayed as expected.");
        TicketInfor ticketInfor = ticketBookingPage.getTicketInfo();
        Assert.assertEquals(ticketInfor.getDepartDate(), "11/16/2024", "Depart Date is not correct.");
        Assert.assertEquals(ticketInfor.getDepartStation(), "Huế", "Depart Station is not correct.");
        Assert.assertEquals(ticketInfor.getArriveStation(), "Sài Gòn", "Arrive Station is not correct.");
        Assert.assertEquals(ticketInfor.getSeatType(), "Soft bed with air conditioner", "Seat Type is not correct.");
        Assert.assertEquals(ticketInfor.getAmount(), 1, "Ticket Amount is not correct.");
    }

    @Test
    public void TC15() {
        System.out.println("TC15 - User can open 'Book ticket' page by clicking on 'Book ticket' link in 'Train timetable' page");

        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        TimeTablePage timetablePage = homePage.gotoTimetablePage();
        BookTicketPage bookTicketPage = timetablePage.clickBookTicketLink("Sài Gòn", "Huế");
        String expectedDepartFrom = "Sài Gòn";
        String expectedArriveAt = "Huế";

        Assert.assertEquals(bookTicketPage.getSelectedDepartStation(), expectedDepartFrom, "Departure from unexpected value");
        Assert.assertEquals(bookTicketPage.getSelectedArriveStation(), expectedArriveAt, "Arrive at value is not as expected");
    }

    @Test
    public void TC16() {
        System.out.println("TC16 - User can cancel a ticket");

        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);
        BookTicketPage bookTicketPage = homePage.gotoTimetablePage().clickBookTicketLink("Huế", "Sài Gòn");
        bookTicketPage.getBtnBookTicket();
        MyTicketPage myTicketPage = bookTicketPage.gotoMyTicketsPage();

        Assert.assertTrue(myTicketPage.isMyTicketPageDisplayed(), "My Ticket page is not displayed.");
        myTicketPage.cancelTicket();
        Assert.assertTrue(myTicketPage.isTicketDisplayed(), "The canceled ticket is still displayed.");
    }
}
