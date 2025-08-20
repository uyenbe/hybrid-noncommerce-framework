package pageObjects.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import pageUIs.nopcommerce.user.UserCustomerInforPageUI;

public class UserCustomerInforPO extends UserSidebarPO {
    private WebDriver driver;

    public UserCustomerInforPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    public boolean isGenderMaleSelected() {
        waitForElementSelected(driver, UserCustomerInforPageUI.GENDER_MALE_RADIO);
        return isElementSelected(driver, UserCustomerInforPageUI.GENDER_MALE_RADIO);

    }

    public String getFirstNameTextboxValue() {
        waitForElementVisible(driver, UserCustomerInforPageUI.FIRST_NAME_TEXTBOX);
        return getAttributeValue(driver, UserCustomerInforPageUI.FIRST_NAME_TEXTBOX,"value");
    }

    public String getLastNameTextboxValue() {
        waitForElementVisible(driver, UserCustomerInforPageUI.LAST_NAME_TEXTBOX);
        return getAttributeValue(driver, UserCustomerInforPageUI.LAST_NAME_TEXTBOX,"value");
    }

    public String getEmailTextboxValue() {
        waitForElementVisible(driver, UserCustomerInforPageUI.EMAIL_TEXTBOX);
        return getAttributeValue(driver, UserCustomerInforPageUI.EMAIL_TEXTBOX,"value");
    }


}
