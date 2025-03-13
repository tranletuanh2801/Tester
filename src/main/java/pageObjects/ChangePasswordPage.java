package pageObjects;

import common.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ChangePasswordPage {
    public String getConfirmationMessage() {
        WebElement confirmationMessageElement = Constant.WEBDRIVER.findElement(By.xpath("//p[@class='message success']"));
        return confirmationMessageElement.getText();
    }

    public void enterCurrentPassword(String currentPassword) {
        WebElement currentPasswordField = Constant.WEBDRIVER.findElement(By.id("currentPassword"));
        currentPasswordField.clear();
        currentPasswordField.sendKeys(currentPassword);
    }

    public void enterNewPassword(String newPassword) {
        WebElement newPasswordField = Constant.WEBDRIVER.findElement(By.id("newPassword"));
        newPasswordField.clear();
        newPasswordField.sendKeys(newPassword);
    }

    public void enterConfirmPassword(String confirmPassword) {
        WebElement confirmPasswordField = Constant.WEBDRIVER.findElement(By.id("confirmPassword"));
        confirmPasswordField.clear();
        confirmPasswordField.sendKeys(confirmPassword);
    }

    public void clickChangePasswordButton() {
        WebElement changePasswordButton = Constant.WEBDRIVER.findElement(By.xpath("//input[@value='Change Password']"));
        changePasswordButton.click();
    }
}
