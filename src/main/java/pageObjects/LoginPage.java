package pageObjects;

import common.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage extends GeneralPage {
    private final By _txtUsername = By.xpath("//input[@id='username']");
    private final By _txtPassword = By.xpath("//input[@id='password']");
    private final By _btnLogin = By.xpath("//input[@value='login']");
    private final By _lblLoginErrorMsg = By.xpath("//p[@class='message error LoginForm']");
    private final By _lnkForgotPassword = By.xpath("//html/body/div[1]/div[2]/ul/li[3]/a");
    public WebElement getTxtUsername ()
    {
        return common.Constant.WEBDRIVER.findElement(_txtUsername);
    }

    public WebElement getTxtPassword ()
    {
        return Constant.WEBDRIVER.findElement(_txtPassword);
    }

    public WebElement getBtnLogin ()
    {
        return Constant.WEBDRIVER.findElement(_btnLogin);
    }

    public WebElement getLblLoginErrorMsg() {
        return Constant.WEBDRIVER.findElement(_lblLoginErrorMsg);
    }

    //Methods
    public HomePage login(String username, String password) {
        //Submit login credentials
        this.getTxtUsername().sendKeys(username);
        this.getTxtPassword().sendKeys(password);
        this.getBtnLogin().click();
        //Land on Home page
        return new HomePage();
    }

    public String getErrorMessage() {
        return this.getLblLoginErrorMsg().getText();
    }

    public WebElement getLnkForgotPassword(){
        return Constant.WEBDRIVER.findElement(_lnkForgotPassword);
    }

    public ForgotPasswordPage gotoForgotPasswordPage() {
        this.getLnkForgotPassword().click();
        return new ForgotPasswordPage();
    }
}