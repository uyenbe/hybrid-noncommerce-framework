package pageObjects.nopcommerce.admin;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.nopcommerce.admin.AdminDashboardPageUI;

public class AdminDashboardPO extends BasePage {
    private WebDriver driver;
    public AdminDashboardPO(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isAdminDashboardPageDisplay() {
        waitForElementVisible(driver, AdminDashboardPageUI.DASHBOARD_LINKTEXT);
        return isElementDisplay(driver, AdminDashboardPageUI.DASHBOARD_LINKTEXT);
    }




}
