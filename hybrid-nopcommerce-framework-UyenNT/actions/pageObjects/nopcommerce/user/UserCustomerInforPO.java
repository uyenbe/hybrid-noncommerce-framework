package pageObjects.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import pageUIs.nopcommerce.user.UserCustomerInforPageUI;

public class UserCustomerInforPO extends UserSidebarPO {
    private WebDriver driver;

    public UserCustomerInforPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public boolean isUserGenderMaleSelected() {
        waitForElementVisible(driver, UserCustomerInforPageUI.GENDER_MALE_RADIO);
        return isElementSelected(driver, UserCustomerInforPageUI.GENDER_MALE_RADIO);
    }

    public String getUserFirstNameTextboxValue() {
        waitForElementVisible(driver, UserCustomerInforPageUI.FIRST_NAME_TEXTBOX);
        return getAttributeValue(driver, UserCustomerInforPageUI.FIRST_NAME_TEXTBOX, "value");
    }

    public String getUserLastNameTextboxValue() {
        waitForElementVisible(driver, UserCustomerInforPageUI.LAST_NAME_TEXTBOX);
        return getAttributeValue(driver, UserCustomerInforPageUI.LAST_NAME_TEXTBOX, "value");
    }

    public String getUserEmailTextboxValue() {
        waitForElementVisible(driver, UserCustomerInforPageUI.EMAIL_TEXTBOX);
        return getAttributeValue(driver, UserCustomerInforPageUI.EMAIL_TEXTBOX, "value");
    }

}
