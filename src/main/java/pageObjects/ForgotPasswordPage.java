package pageObjects;

import common.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ForgotPasswordPage {
    private final By _txtEmail = By.id("email"); // Thay đổi ID cho đúng
    private final By _btnSendInstructions = By.id("sendInstructions"); // Thay đổi ID cho đúng

    public WebElement  getTxtEmail() {
        return Constant.WEBDRIVER.findElement(_txtEmail);
    }

    public WebElement getBtnSendInstructions() {
        return Constant.WEBDRIVER.findElement(_btnSendInstructions);
    }

    public void enterEmailAddress(String username) {
    }

    public void clickSendInstructionButton() {
    }

    public void forgotPassword(String username) {
    }

    public void enterEmail(String email) {
        this.getTxtEmail().clear();
        this.getTxtEmail().sendKeys(email);
    }

    public void clickSendInstructionsButton() {
        this.getBtnSendInstructions().click();
    }
}
