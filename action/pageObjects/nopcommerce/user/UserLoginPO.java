package pageObjects.nopcommerce.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.nopcommerce.PageGenerator;
import pageUIs.nopcommerce.user.UserLoginPageUI;

public class UserLoginPO extends BasePage {
    private WebDriver driver;
    public UserLoginPO(WebDriver driver) {
        this.driver = driver;
    }
    public void enterEmailTextbox(String emailAddress) {
        waitForElementVisible(driver, UserLoginPageUI.EMAIL_TEXTBOX);
        sendKeysToElement(driver, UserLoginPageUI.EMAIL_TEXTBOX, emailAddress);
    }

    public void enterPasswordTextbox(String password) {
        waitForElementVisible(driver, UserLoginPageUI.PASSWORD_TEXTBOX);
        sendKeysToElement(driver, UserLoginPageUI.PASSWORD_TEXTBOX, password);
    }

    public void clickLoginButton() {
        waitForElementClickable(driver, UserLoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, UserLoginPageUI.LOGIN_BUTTON);
    }

    public UserHomePO loginToSystem(String emailAddress, String password) {
        enterEmailTextbox(emailAddress);
        enterPasswordTextbox(password);
        clickLoginButton();
        return PageGenerator.getUserHomePage(driver);
    }
}
