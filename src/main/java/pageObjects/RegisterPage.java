package pageObjects;

import common.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage {
    private WebDriver driver;

    // Constructor
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locator cho các thông báo lỗi
    private final By inputEmail = By.id("email");
    private By errorMessageAboveFormLocator = By.xpath("//div[@class='error-message-above-form']");
    private By errorMessagePasswordFieldLocator = By.xpath("//span[@id='password-error']");
    private By errorMessagePIDFieldLocator = By.xpath("//span[@id='pid-error']");
    private final By errorMessage = By.xpath("//p[@class='message error']");
    private final By confirmationMessage = By.xpath("//p['Registration Confirmed! You can now log in to the site.']");

    // Phương thức lấy thông báo lỗi ở phía trên biểu mẫu
    public String getErrorMessageAboveForm() {
        WebElement errorMessageAboveForm = driver.findElement(errorMessageAboveFormLocator);
        return errorMessageAboveForm.getText();
    }

    // Phương thức lấy thông báo lỗi bên cạnh trường password
    public String getErrorMessagePasswordField() {
        WebElement errorMessagePasswordField = driver.findElement(errorMessagePasswordFieldLocator);
        return errorMessagePasswordField.getText();
    }

    // Phương thức lấy thông báo lỗi bên cạnh trường PID
    public String getErrorMessagePIDField() {
        WebElement errorMessagePIDField = driver.findElement(errorMessagePIDFieldLocator);
        return errorMessagePIDField.getText();
    }

    public String getErrorMessage() {
        WebElement errorMessageElement = Constant.WEBDRIVER.findElement(errorMessage);
        return errorMessageElement.getText();
    }

    public String getConfirmationMessage() {
        return Constant.WEBDRIVER.findElement(confirmationMessage).getText();
    }

    public void enterEmailAddress(String mail) {
    }

    public void enterPassword(String s) {
    }

    public void enterConfirmPassword(String s) {
    }

    public void enterPID(String s) {
    }

    public void clickRegisterButton() {
    }

    public void enterEmail(String email) {
        WebElement emailField = Constant.WEBDRIVER.findElement(inputEmail);
        emailField.clear();
        emailField.sendKeys(email);
    }

}

