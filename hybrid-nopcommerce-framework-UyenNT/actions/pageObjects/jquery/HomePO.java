package pageObjects.jquery;

import commons.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pageUIs.jquery.HomePageUI;

public class HomePO extends BasePage {
    private WebDriver driver;
    public HomePO (WebDriver driver) {
        this.driver = driver;
    }

    public void openPageByNumber(String pageNumber) {
        waitForElementClickable(driver, HomePageUI.DYNAMIC_PAGING_LINK, pageNumber);
        clickToElement(driver, HomePageUI.DYNAMIC_PAGING_LINK, pageNumber);
    }

    public boolean isPageByNumberActive(String pageNumber) {
        waitForElementVisible(driver, HomePageUI.DYNAMIC_PAGING_LINK,pageNumber );
        return getAttributeValue(driver, HomePageUI.DYNAMIC_PAGING_LINK, "class", pageNumber ).endsWith("active");
    }

    public void enterTextboxByHeaderName(String headerName, String value) {
        waitForElementVisible(driver, HomePageUI.DYNAMIC_TEXTBOX_BY_HEADER_NAME, headerName);
        sendKeyToElement(driver, HomePageUI.DYNAMIC_TEXTBOX_BY_HEADER_NAME, value, headerName);
        presskeyToElement(driver, HomePageUI.DYNAMIC_TEXTBOX_BY_HEADER_NAME, Keys.ENTER, headerName);
        sleepInSeconds(2);
    }

    public boolean isRowDataDisplayed(String females, String country, String males, String total) {
        waitForElementVisible(driver, HomePageUI.DYNAMIC_DATA_IN_ROW, females, country, males, total );
        return isElementDisplayed(driver, HomePageUI.DYNAMIC_DATA_IN_ROW, females, country, males, total );
    }

    public void clickEditButton(String countryName) {
        //waitForElementVisible(driver, HomePageUI.DYNAMIC_EDIT_BUTTON, countryName);
        waitForElementPresence(driver, HomePageUI.DYNAMIC_EDIT_BUTTON,countryName);
        waitForElementClickable(driver, HomePageUI.DYNAMIC_EDIT_BUTTON, countryName);
        clickToElement(driver, HomePageUI.DYNAMIC_EDIT_BUTTON, countryName);
        sleepInSeconds(5);
    }

    public void clickDeleteButton(String countryName) {
        waitForElementVisible(driver, HomePageUI.DYNAMIC_DELETE_BUTTON, countryName);
        clickToElement(driver, HomePageUI.DYNAMIC_DELETE_BUTTON, countryName);
        sleepInSeconds(3);
    }
    public void deleteRowByCountryName(String countryName) {
        waitForElementVisible(driver, HomePageUI.DYNAMIC_DELETE_BUTTON, countryName);
        clickToElement(driver, HomePageUI.DYNAMIC_DELETE_BUTTON, countryName);
        sleepInSeconds(2);
    }

    public void waitForSearchResult( String countryName) {
        waitForElementVisible(driver,HomePageUI.DYNAMIC_SEARCH_RESULT, countryName);
    }
    public void editRowByCountryName1 (String countryName) {
       clickToElementJS(driver, HomePageUI.DYNAMIC_EDIT_BUTTON, countryName);
        sleepInSeconds(3);
    }
}
