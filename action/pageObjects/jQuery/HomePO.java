package pageObjects.jQuery;

import commons.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.jquery.HomePageUI;
import pageUIs.nopcommerce.BasePageUIs;

import java.util.ArrayList;
import java.util.List;

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

    public void clickToLoadDataButton() {
        waitForElementClickable(driver, HomePageUI.DYNAMIC_CLICK_LOAD_DATA_BUTTON);
        clickToElement(driver, HomePageUI.DYNAMIC_CLICK_LOAD_DATA_BUTTON);
    }

    public void enterToTextboxByIndex(String rowIndex, String columnName, String valueToSendkey) {
        // Từ column name làm sao để lấy ra được column index
        // Để xác định được column index thì dưak vào việc xác định số lượng các thẻ anh của column Name mà mình đang thao tác
        // Theo nguyên tắc: columIndex = Số lượng thẻ preceding-sibling (thẻ anh) + 1 (chính columnName đang thao tác)
        // Sau đó lấy ra số lượng các preceding-sibling bằng hàm getListElement
        int columnIndexNumber = getListElements(driver, HomePageUI.DYNAMIC_PRECEDING_SIBLING_COLUMN_NUMBER, columnName).size() + 1;

        // Convert nó qua dạng text(String)
        String columnIndex = String.valueOf(columnIndexNumber);

        // Truyền 2 giá trị: columnIndex và rowIndex vào locator để tương tác và sendkey
        sendKeysToElement(driver, HomePageUI.DYNAMIC_TEXTBOX_BY_ROW_AND_COLUMN_INDEX, valueToSendkey, rowIndex, columnIndex);

    }

    public void enterToDropByIndex(String rowIndex, String columnName, String valueToSelect) {
        // Từ column name làm sao để lấy ra được column index
        int columnIndexNumber = getListElements(driver, HomePageUI.DYNAMIC_PRECEDING_SIBLING_COLUMN_NUMBER, columnName).size() + 1;

        // Convert nó qua dạng text(String)
        String columnIndex = String.valueOf(columnIndexNumber);

        // Truyền 2 giá trị: columnIndex và rowIndex vào locator để tương tác và select
        selectItemInDropDown(driver, HomePageUI.DYNAMIC_DROPDOWN_BY_ROW_AND_COLUMN_INDEX, valueToSelect, rowIndex, columnIndex);

    }

    public void checkToCheckboxByIndex(String rowIndex, String columnName, boolean checkOrUncheck) {
        // Từ column name làm sao để lấy ra được column index
        int columnIndexNumber = getListElements(driver, HomePageUI.DYNAMIC_PRECEDING_SIBLING_COLUMN_NUMBER, columnName).size() + 1;

        // Convert nó qua dạng text(String)
        String columnIndex = String.valueOf(columnIndexNumber);

        // Kiểm tra giá trị của checkbox
        if (checkOrUncheck) {
            checkToCheckboxRadio(driver, HomePageUI.DYNAMIC_CHECKBOX_BY_ROW_AND_COLUMN_INDEX, rowIndex, columnIndex);
        }else {
            uncheckTheCheckbox(driver, HomePageUI.DYNAMIC_CHECKBOX_BY_ROW_AND_COLUMN_INDEX, rowIndex, columnIndex);
        }
    }

    public void clickToIconByIndex(String rowIndex, String iconName) {
        waitForElementClickable(driver, HomePageUI.DYNAMIC_ICON_BY_ROW_INDEX, rowIndex, iconName);
        //click to button
        clickToElement(driver, HomePageUI.DYNAMIC_ICON_BY_ROW_INDEX, rowIndex, iconName);


    }

    public List<String> getAllValueAtColumnName(String columnName) {
        // Từ column name làm sao để lấy ra được column index
        int columnIndexNumber = getListElements(driver, HomePageUI.DYNAMIC_PRECEDING_SIBLING_COLUMN_NUMBER_2, columnName).size() + 1;

        // Convert nó qua dạng text(String)
        String columnIndex = String.valueOf(columnIndexNumber);

        // Lấy ra list value trong column
        List<WebElement> allElementValeAtColumn = getListElements(driver, HomePageUI.ALL_VALUE_BY_COLUMN_INDEX, columnIndex);

        // Tạo biến để lưu tất cả text của list element khi chạy vòng for
        List<String> allTextValue = new ArrayList<String>();

        for (WebElement element : allElementValeAtColumn) {
            allTextValue.add(element.getText());
        }
        return allTextValue;
    }


    public boolean isFileLoadedByName(String fileName) {
        waitForElementVisible(driver, HomePageUI.FILE_LOADED_BY_FILE_NAME, fileName);
        return isElementDisplay(driver, HomePageUI.FILE_LOADED_BY_FILE_NAME, fileName);
    }

    public void clickToUploadButton(WebDriver driver) {
        // Đoạn comment này chỉ áp dụng trong TH upload 1 file,
        // nếu upload nhiều file cùng 1 lúc thì dùng đoạn code dưới
//        waitForElementClickable(driver, HomePageUI.UPLOAD_BUTTON);
//        clickToElement(driver, HomePageUI.UPLOAD_BUTTON);
        List<WebElement> startButton = getListElements(driver, HomePageUI.UPLOAD_BUTTON);
        for (WebElement button : startButton){
            button.click();
            sleepInSeconds(4);
        }
    }

    public boolean isFileUpLoadedSuccess(String fileName) {
        waitForElementVisible(driver, HomePageUI.FILE_LOADED_SUCCESS_BY_FILE_NAME, fileName);
        return isElementDisplay(driver, HomePageUI.FILE_LOADED_SUCCESS_BY_FILE_NAME, fileName);
    }
}
