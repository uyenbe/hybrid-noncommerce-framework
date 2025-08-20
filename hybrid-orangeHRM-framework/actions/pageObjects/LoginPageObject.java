package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.LoginPageUI;

public class LoginPageObject extends BasePage {
    private WebDriver driver;

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }
    public void enterToUsernameTextbox(String userName) {
        waitForElementVisible(driver, LoginPageUI.USERNAME);
        sendkeysToElement(driver, LoginPageUI.USERNAME, userName);
    }

    public void enterToPasswordTextbox(String password) {
        waitForElementVisible(driver, LoginPageUI.PASSWORD);
        sendkeysToElement(driver, LoginPageUI.PASSWORD, password);
    }

    public void clickToLoginButton() {
        waitForElementVisible(driver, LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
    }
}
