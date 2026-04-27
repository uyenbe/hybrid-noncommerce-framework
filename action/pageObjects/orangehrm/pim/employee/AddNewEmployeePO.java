package pageObjects.orangehrm.pim.employee;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.orangehrm.PageGenerator;
import pageUIs.orangehrm.pim.employee.AddNewPageUIs;

public class AddNewEmployeePO extends BasePage {
    private WebDriver driver;
    public AddNewEmployeePO(WebDriver driver) {
        this.driver = driver;
    }

    public void enterToFirstNameTextbox(String firstName) {
        waitForElementVisible(driver, AddNewPageUIs.FIRSTNAME_TEXTBOX);
        sendKeysToElement(driver, AddNewPageUIs.FIRSTNAME_TEXTBOX, firstName);

    }

    public String getEmployeeID() {
        waitForElementVisible(driver, AddNewPageUIs.EMPLOYEE_ID);
        return  getDOMPropertiesAttributeValue(driver, AddNewPageUIs.EMPLOYEE_ID, "value");
    }

    public PersonalDetailsPO clickToSaveButtonAtEmployeeContainer() {
        waitForElementClickable(driver, AddNewPageUIs.SAVE_BUTTON_AT_ADD_EMPLOYEE_CONTAINER);
        clickToElement(driver, AddNewPageUIs.SAVE_BUTTON_AT_ADD_EMPLOYEE_CONTAINER);
        waitAllLoadingIconInvisible(driver);
        return PageGenerator.getPersonalDetailsPage(driver);
    }

    public void enterToLastNameTextbox(String lastName) {
        waitForElementVisible(driver, AddNewPageUIs.LASTNAME_TEXTBOX);
        sendKeysToElement(driver, AddNewPageUIs.LASTNAME_TEXTBOX, lastName);
    }
}
