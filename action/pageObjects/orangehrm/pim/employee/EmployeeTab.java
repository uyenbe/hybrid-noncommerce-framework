package pageObjects.orangehrm.pim.employee;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.orangehrm.PageGenerator;
import pageUIs.orangehrm.pim.employee.EmployeeTabUIs;

public class EmployeeTab extends BasePage {
    private WebDriver driver;
    public EmployeeTab(WebDriver driver) {
        this.driver = driver;
    }

    public PersonalDetailsPO openPersonalDetailsPage() {
        waitForElementClickable(driver, EmployeeTabUIs.PERSONAL_DETAIL_LINK);
        clickToElement(driver, EmployeeTabUIs.PERSONAL_DETAIL_LINK);
        waitAllLoadingIconInvisible(driver);
        return PageGenerator.getPersonalDetailsPage(driver);
    }
    public ContactDetailsPO openContactDetailsPage() {
        waitForElementClickable(driver, EmployeeTabUIs.CONTACT_DETAIL_LINK);
        clickToElement(driver, EmployeeTabUIs.CONTACT_DETAIL_LINK);
        return PageGenerator.getContactDetailsPage(driver);
    }

    public EmergencyContactsPO openEmergencyContactsPage() {
        waitForElementClickable(driver, EmployeeTabUIs.EMERGENCY_CONTACT_LINK);
        clickToElement(driver, EmployeeTabUIs.EMERGENCY_CONTACT_LINK);
        return PageGenerator.getEmergencyContactsPage(driver);
    }

}
