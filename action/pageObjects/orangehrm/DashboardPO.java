package pageObjects.orangehrm;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.orangehrm.pim.employee.EmployeeListPO;
import pageUIs.orangehrm.DashboardPageUIs;

public class DashboardPO extends BasePage {
    private WebDriver driver;
    public DashboardPO(WebDriver driver) {
        this.driver = driver;
    }

    public EmployeeListPO clickPIMPage() {
        waitForElementClickable(driver, DashboardPageUIs.PIM_LINK);
        clickToElement(driver, DashboardPageUIs.PIM_LINK);
         waitAllLoadingIconInvisible(driver); // làm chậm script nên comment lại
        return PageGenerator.getEmployeeListPage(driver);
    }
}
