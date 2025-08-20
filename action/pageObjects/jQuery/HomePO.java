package pageObjects.jQuery;

import commons.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pageUIs.jquery.HomePageUI;

public class HomePO extends BasePage {
    WebDriver driver;
    public HomePO(WebDriver driver) {
        this.driver = driver;
    }

    public void openPageByNumber(String pageNumber) {
        waitForElementClickable(driver, HomePageUI.DYNAMIC_PAGE_LINK, pageNumber);
        clickToElement(driver, HomePageUI.DYNAMIC_PAGE_LINK, pageNumber);
        sleepInSeconds(2);
    }

    public boolean isPageNumberActive(String pageNumber) {
        waitForElementVisible(driver, HomePageUI.DYNAMIC_PAGE_LINK, pageNumber);
        return getAttributeValue(driver, HomePageUI.DYNAMIC_PAGE_LINK,"class", pageNumber).endsWith("active");

    }

    public void enterToTextboxByHeaderName(String headerName, String valueToSendkey) {
        waitForElementVisible(driver, HomePageUI.DYNAMIC_TEXTBOX_BY_HEADER_NAME, headerName);
        sendKeysToElement(driver, HomePageUI.DYNAMIC_TEXTBOX_BY_HEADER_NAME, valueToSendkey, headerName);
        pressKeyToElement(driver, HomePageUI.DYNAMIC_TEXTBOX_BY_HEADER_NAME, Keys.ENTER, headerName);
    }

    public boolean isRowDataValueDisplayed(String females, String country, String males, String total) {
        waitForElementVisible(driver, HomePageUI.DYNAMIC_DATA_ROW, females, country, males, total);
        return isElementDisplay(driver, HomePageUI.DYNAMIC_DATA_ROW, females, country, males, total);
    }

    public void deleteRowByCountryName(String countryName) {
        waitForElementVisible(driver, HomePageUI.DYNAMIC_DELETE_BUTTON_BY_COUNTRY_NAME, countryName);
        clickToElement(driver, HomePageUI.DYNAMIC_DELETE_BUTTON_BY_COUNTRY_NAME, countryName);
        sleepInSeconds(2);
    }

    public void editRowByCountryName(String countryName) {
        waitForElementVisible(driver, HomePageUI.DYNAMIC_EDIT_BUTTON_BY_COUNTRY_NAME, countryName);
        clickToElement(driver, HomePageUI.DYNAMIC_EDIT_BUTTON_BY_COUNTRY_NAME, countryName);
        sleepInSeconds(2);
    }

    public void clickEditButton(String countryName) {
        waitForElementVisible(driver, HomePageUI.DYNAMIC_EDIT_BUTTON, countryName);
        clickToElement(driver, HomePageUI.DYNAMIC_EDIT_BUTTON, countryName);
        sleepInSeconds(5);
    }
}
