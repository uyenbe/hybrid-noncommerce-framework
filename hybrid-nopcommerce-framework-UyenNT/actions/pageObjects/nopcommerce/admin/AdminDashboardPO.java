package pageObjects.nopcommerce.admin;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.nopcommerce.admin.AdminDashboradPageUI;

public class AdminDashboardPO extends BasePage {
    private WebDriver driver;
    public AdminDashboardPO (WebDriver driver) {
        this.driver = driver;
    }

    public boolean isDashboardLinkDisplayed() {
        return isElementDisplayed(driver, AdminDashboradPageUI.DASHBOARD_LINKTEXT);
    }
}
