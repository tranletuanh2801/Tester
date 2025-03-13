package pageObjects;
import common.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class HomePage extends GeneralPage {
    //locators
    private final By tabBookTicket = By.xpath("//a[@href='/Page/BookTicket.cshtml']");
    private final By tabMyTicket = By.xpath("//a[@href='/Page/ManageTicket.cshtml']");
    private final By tabChangePassword = By.xpath("//a[@href='/Account/ChangePassword.cshtml']");
    private final By tabTimetable = By.xpath("//a[@href='TrainTimeListPage.cshtml']");
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    public HomePage() {
    }

    //Elements
    //Methods
    public HomePage open() {
        Constant.WEBDRIVER.navigate().to(Constant.RAILWAY_URL);
        return this;
    }

    public BookTicketPage gotoBookTicketPage() {
        By tabBookTicket = By.xpath("//div[@id='menu']//a[@href='/Page/BookTicketPage.cshtml']");
        Constant.WEBDRIVER.findElement(tabBookTicket).click();
        return new BookTicketPage(); // Trả về đối tượng BookTicketPage
    }

    public MyTicketPage gotoMyTicketPage() {
        Constant.WEBDRIVER.findElement(tabMyTicket).click();
        return new MyTicketPage();
    }

    public ChangePasswordPage gotoChangePasswordPage() {
        Constant.WEBDRIVER.findElement(tabChangePassword).click();
        return new ChangePasswordPage();
    }

    public TimeTablePage gotoTimetablePage() {
        Constant.WEBDRIVER.findElement(tabTimetable).click();
        return new TimeTablePage();
    }
}



