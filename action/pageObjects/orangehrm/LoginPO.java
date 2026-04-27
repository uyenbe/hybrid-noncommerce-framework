package pageObjects.orangehrm;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.orangehrm.LoginPageUIs;

public class LoginPO extends BasePage {
    private WebDriver driver;
    public LoginPO (WebDriver driver) {
        this.driver = driver;
    }

    public void enterToUsername(String username) {
        waitForElementVisible(driver, LoginPageUIs.USER_NAME);
        sendKeysToElement(driver, LoginPageUIs.USER_NAME, username);
    }

    public void enterToPassword(String password) {
        waitForElementVisible(driver, LoginPageUIs.PASSWORD);
        sendKeysToElement(driver, LoginPageUIs.PASSWORD, password);
    }

    public DashboardPO clickToLoginButton() {
        waitForElementClickable(driver, LoginPageUIs.LOGIN_BUTTON);
        clickToElement(driver, LoginPageUIs.LOGIN_BUTTON);
      // waitAllLoadingIconInvisible(driver);
        return PageGenerator.getDashboardPage(driver);
    }
}
