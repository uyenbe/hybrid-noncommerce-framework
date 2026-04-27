package pageObjects.orangehrm.pim.employee;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.orangehrm.PageGenerator;
import pageUIs.orangehrm.pim.employee.EmployeeListPageUIs;

public class EmployeeListPO extends BasePage {
    private WebDriver driver;
    public EmployeeListPO(WebDriver driver) {
        this.driver = driver;
    }

    public AddNewEmployeePO clickAddNewEmployee() {
        waitForElementClickable(driver, EmployeeListPageUIs.ADD_EMPLOYEE_NAV_BUTTON);
        clickToElement(driver, EmployeeListPageUIs.ADD_EMPLOYEE_NAV_BUTTON);
       // waitAllLoadingIconInvisible(driver);
        return PageGenerator.getAddNewPage(driver);
    }
}
