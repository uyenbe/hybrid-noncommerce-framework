package pageObjects.nopcommerce.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.nopcommerce.PageGenerator;
import pageUIs.nopcommerce.user.UserRegisterPageUI;

public class UserRegisterPO extends BasePage {
    private WebDriver driver;

    public UserRegisterPO(WebDriver driver) {
        this.driver = driver;
    }

    public void clickGenderRadioButton() {
        waitForElementClickable(driver, UserRegisterPageUI.GENDER_MALE_RADIO);
        checkTheCheckbox(driver, UserRegisterPageUI.GENDER_MALE_RADIO);
    }

    public void enterFirstNameTextbox(String firstName) {
        waitForElementVisible(driver, UserRegisterPageUI.FIRST_NAME_TEXTBOX);
        sendKeysToElement(driver, UserRegisterPageUI.FIRST_NAME_TEXTBOX, firstName);
    }

    public void enterLastNameTextbox(String lastName) {
        waitForElementVisible(driver, UserRegisterPageUI.LAST_NAME_TEXTBOX);
        sendKeysToElement(driver, UserRegisterPageUI.LAST_NAME_TEXTBOX, lastName);
    }

    public void enterEmailTextbox(String emailAdress) {
        waitForElementVisible(driver, UserRegisterPageUI.EMAIL_TEXTBOX);
        sendKeysToElement(driver, UserRegisterPageUI.EMAIL_TEXTBOX, emailAdress);
    }

    public void enterPasswordTextbox(String password) {
        waitForElementVisible(driver, UserRegisterPageUI.PASSWORD_TEXTBOX);
        sendKeysToElement(driver, UserRegisterPageUI.PASSWORD_TEXTBOX, password);
    }

    public void enterConfirmPasswordTextbox(String password) {
        waitForElementVisible(driver, UserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
        sendKeysToElement(driver, UserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, password);
    }

    public void clickRegisterButton() {
        waitForElementClickable(driver, UserRegisterPageUI.REGISTER_BUTTON);
        clickToElement(driver, UserRegisterPageUI.REGISTER_BUTTON);
    }


    public String getRegisterMessage() {
        waitForElementVisible(driver, UserRegisterPageUI.REGISTER_SUCCESS_MESSAGE);
        return getElementText(driver, UserRegisterPageUI.REGISTER_SUCCESS_MESSAGE);
    }

    public UserHomePO clickLogoutLink() {
        waitForElementClickable(driver, UserRegisterPageUI.LOGOUT_LINK);
        clickToElement(driver, UserRegisterPageUI.LOGOUT_LINK);
        return PageGenerator.getUserHomePage(driver);



    }
}
