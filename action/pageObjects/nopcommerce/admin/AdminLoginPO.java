package pageObjects.nopcommerce.admin;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.nopcommerce.PageGenerator;
import pageUIs.nopcommerce.admin.AdminLoginPageUI;

public class AdminLoginPO extends BasePage {
    WebDriver driver;
    public AdminLoginPO (WebDriver driver) {
        this.driver = driver;
    }

    public void enterToEmailTextbox(String emailAddress) {
        waitForElementVisible(driver, AdminLoginPageUI.EMAIL_TEXTBOX);
        sendKeysToElement(driver, AdminLoginPageUI.EMAIL_TEXTBOX, emailAddress);
    }

    public void enterToPasswordTextbox(String password) {
        waitForElementVisible(driver, AdminLoginPageUI.PASSWORD_TEXTBOX);
        sendKeysToElement(driver, AdminLoginPageUI.PASSWORD_TEXTBOX, password);
    }

    public AdminDashboardPO clickToLoginButton() {
        waitForElementClickable(driver, AdminLoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, AdminLoginPageUI.LOGIN_BUTTON);
        return PageGenerator.getAdminDashboardPage(driver);
    }
}
