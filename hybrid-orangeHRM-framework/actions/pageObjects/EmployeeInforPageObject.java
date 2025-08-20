package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.EmployeeInforPageUI;

import java.util.Date;

public class EmployeeInforPageObject extends BasePage {
    private WebDriver driver;
    public EmployeeInforPageObject(WebDriver driver) {
        this.driver = driver;
    }
    public boolean isEmployeeNameDisplayed() {
        return false;
    }

    public String getFirstNameTextboxValue() {
        waitForElementVisible(driver, EmployeeInforPageUI.FIRST_NAME_1);
        sleepInSeconds(5);
        return getAttributeInDOMByJS(driver, EmployeeInforPageUI.FIRST_NAME_1,"_value");
    }

    public String getMiddleNameTextboxValue() {
        waitForElementVisible(driver, EmployeeInforPageUI.MIDDLE_NAME);
        return getAttributeInDOMByJS(driver, EmployeeInforPageUI.MIDDLE_NAME,"value");
    }

    public String getLastNameTextboxValue() {
        waitForElementVisible(driver, EmployeeInforPageUI.LAST_NAME);
        return getAttributeInDOMByJS(driver, EmployeeInforPageUI.LAST_NAME, "value");
    }

    public String getEmployeeIDTextboxValue() {
        waitForElementVisible(driver, EmployeeInforPageUI.EMPLOYEE_ID);
        return getAttributeInDOMByJS(driver, EmployeeInforPageUI.EMPLOYEE_ID, "_value");
    }

    public String getOrtherIDTextboxValue() {
        waitForElementVisible(driver, EmployeeInforPageUI.OTHER_ID);
        return getAttributeInDOMByJS(driver, EmployeeInforPageUI.OTHER_ID,"value");
    }

    public boolean isGenderMaleSelected() {
        waitForElementVisible(driver, EmployeeInforPageUI.GENDER_MALE);
        return isElementSelected(driver, EmployeeInforPageUI.GENDER_MALE);
    }

    public String getLicenseExpiryDateValue() {
        waitForElementVisible(driver, EmployeeInforPageUI.LICENSE_EXPIRY_DATE);
        return getAttributeInDOMByJS(driver, EmployeeInforPageUI.LICENSE_EXPIRY_DATE,"value");
    }

    public String getMaritalStatusValue() {
        waitForElementVisible(driver, EmployeeInforPageUI.MARITAL_STATUS);
        return getTextElement(driver, EmployeeInforPageUI.MARITAL_STATUS);
    }

    public String getNationalityValue() {
        waitForElementVisible(driver, EmployeeInforPageUI.NATIONALITY);
        return getTextElement(driver, EmployeeInforPageUI.NATIONALITY);
    }
}
