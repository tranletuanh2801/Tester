package pageObjects;

import common.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

import static common.Constant.WEBDRIVER;

public class GeneralPage {
    private final By tabLogin = By.xpath("//div[@id= 'menu']//a[@href = '/Account/Login.cshtml']");
    private final By tabLogout = By.xpath("//div[@id = 'menu']//a[@href = '/Account/Logout']");
    private final By lbWelcomeMessage = By.xpath("//div[@class = 'account']/strong");
    private final By lblErrorMessage = By.xpath("//div[@class='error message']");
    private final By tabTable = By.xpath("//div[@id= 'menu']//a[@href = '/Page/TrainTimeListPage.cshtml']");
    private final By registerLinkLocator = By.xpath("//a[@href='/Account/Register.cshtml']");

    public RegisterPage gotoRegisterPage() {
        WebElement registerLink = WEBDRIVER.findElement(registerLinkLocator);
        registerLink.click();
        return new RegisterPage(WEBDRIVER);
    }

    protected WebElement getTabLogin () {
        return WEBDRIVER.findElement(tabLogin);
    }

    protected WebElement getTabLogout () {
        return WEBDRIVER.findElement(tabLogout);
    }

    protected WebElement getLbWelcomeMessage () {
        return WEBDRIVER.findElement(lbWelcomeMessage);
    }

    protected WebElement getLblErrorMessage() {
        return WEBDRIVER.findElement(lblErrorMessage); // Phương thức tìm và lấy phần tử thông báo lỗi
    }

    public String getWelcomeMessage()
    {
        return this.getLbWelcomeMessage().getText();
    }

    public LoginPage gotoLoginPage()
    {
        this.getTabLogin().click();
        return new LoginPage();
    }

    public String getErrorMessage() {
        WebDriverWait wait = new WebDriverWait(WEBDRIVER, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(lblErrorMessage));
        return this.getLblErrorMessage().getText(); // Lấy nội dung thông báo lỗi
    }

}
