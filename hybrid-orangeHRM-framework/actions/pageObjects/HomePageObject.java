package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.HomePageUI;

public class HomePageObject extends BasePage {
    private WebDriver driver;
    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }
    public boolean isDashboardTextDisplayed() {
        waitForElementVisible(driver, HomePageUI.DASHBOARD_PAGE);
        return isElementDisplayed(driver, HomePageUI.DASHBOARD_PAGE);
    }

    public void clickMyInforMenu() {
        waitForElementClickable(driver, HomePageUI.MY_INFOR_PAGE);
        clickToElement(driver, HomePageUI.MY_INFOR_PAGE);
    }
}
