package pageObjects.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import pageUIs.nopcommerce.user.UserRegisterPageUI;

public class UserRegisterPO extends UserSidebarPO {
    private WebDriver driver;
    // map driver
    public UserRegisterPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void clickUserGenderRadioButton() {
        waitForElementClickable(driver, UserRegisterPageUI.USER_GENDER_MALE_RADIO);
        checkedTheCheckbox(driver, UserRegisterPageUI.USER_GENDER_MALE_RADIO);
    }

    public void enterUserFirstNameTextbox(String firstName) {
        waitForElementVisible(driver, UserRegisterPageUI.USER_FIRST_NAME_TEXTBOX);
        sendKeyToElement(driver, UserRegisterPageUI.USER_FIRST_NAME_TEXTBOX, firstName);
    }

    public void enterUserLastNameTextbox(String lastName) {
        waitForElementVisible(driver, UserRegisterPageUI.USER_LAST_NAME_TEXTBOX);
        sendKeyToElement(driver, UserRegisterPageUI.USER_LAST_NAME_TEXTBOX, lastName);
    }

    public void enterUserEmailTextbox(String emailAddress) {
        waitForElementVisible(driver, UserRegisterPageUI.USER_EMAIL_TEXTBOX);
        sendKeyToElement(driver, UserRegisterPageUI.USER_EMAIL_TEXTBOX, emailAddress);
    }

    public void enterUserPasswordTextbox(String password) {
        waitForElementVisible(driver, UserRegisterPageUI.USER_PASSWORD_TEXTBOX);
        sendKeyToElement(driver, UserRegisterPageUI.USER_PASSWORD_TEXTBOX, password);
    }

    public void enterUserConfirmPasswordTextbox(String password) {
        waitForElementVisible(driver, UserRegisterPageUI.USER_CONFIRM_PASSWORD_TEXTBOX);
        sendKeyToElement(driver, UserRegisterPageUI.USER_CONFIRM_PASSWORD_TEXTBOX, password);
    }

    public void clickUserRegisterButton() {
        waitForElementClickable(driver, UserRegisterPageUI.USER_REGISTER_BUTTON);
        clickToElement(driver, UserRegisterPageUI.USER_REGISTER_BUTTON);
    }

    public String getRegisterMessage() {
        waitForElementVisible(driver, UserRegisterPageUI.USER_REGISTER_SUCCESS_MESSAGE);
        return getElementText(driver, UserRegisterPageUI.USER_REGISTER_SUCCESS_MESSAGE);
    }

    public UserHomePO clickUserLogoutLink() {
        waitForElementClickable(driver, UserRegisterPageUI.USER_LOG_OUT_LINK);
        clickToElement(driver, UserRegisterPageUI.USER_LOG_OUT_LINK);
        return new UserHomePO(driver);
    }
}
