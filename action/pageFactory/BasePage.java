package pageFactory;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {

        //1. Access modifier: public/ protected/ private/ default
        //2. Kiểu dữ liệu của hàm (Data Type): void/int/String/boolean...
        //3. Tên hàm: Đặt tên theo chức năng đang cần viết
        //- Convention tuân theo chuẩn của từng ngôn ngữ(Java)
        //- camelCase
        //4. Có tham số hay ko tuỳ thuộc vào chức năng cần viết
        //5. Kiểu dữ liệu trả về cho hàm:
        //-Nếu như có return data >> dữ liệu return phải khớp vs dữ liệu ở steps 2
        //- Nếu như có return thì nó phải ở steps cuối

        //Selenium web browser function
        //Common function - hàm dùng chung cho nhiều class khác
        // không truyền 1 dữ liệu cụ thể/riêng tư vào được
        // Tuân thủ theo tính đóng gói
        // Hàm static có thể truy cập trực tiếp từ phạm vi Class mà ko cần phải khởi tạo đối tượng từ class đó

        public void openPageURL(WebDriver driver, String url) {
            driver.get(url);
        }

        //gọi từ common function ra dùng:
        //openPageURL(driver, "https://xxx");

        //Đây ko phải làm common function
//        public void openPageURL(WebDriver driver) {
//            driver.get("");
//        }

        public String getPageTitle(WebDriver driver){
            return driver.getTitle();
        }

        public String getCurrentURL(WebDriver driver){
            return driver.getCurrentUrl();
        }

        public String getPageSource(WebDriver driver){
            return driver.getPageSource();
        }

        public void backToPage(WebDriver driver){
            driver.navigate().back();
        }

        public void forwardToPage(WebDriver driver){
            driver.navigate().forward();
        }

        public void refreshPage(WebDriver driver){
            driver.navigate().refresh();
        }

        // Alert

        public Alert waitAlertPresence(WebDriver driver){
            return new WebDriverWait(driver, Duration.ofSeconds(15)).
                    until(ExpectedConditions.alertIsPresent());
            // hàm này vừa wait vừa switch vào alert nên khi gọi hàm này ở các class khác thì ko cần gọi là switch nữa
        }
        public void acceptToAlert(WebDriver driver){
            // Hàm này chỉ accpet
            //driver.switchTo().alert().accept();

            //hàm gọi hàm
            waitAlertPresence(driver).accept();
        }

        public void cancelToAlert(WebDriver driver){
           // driver.switchTo().alert().dismiss();

            waitAlertPresence(driver).dismiss();
        }

        public void sendToAlert(WebDriver driver, String keysToSend){
           // driver.switchTo().alert().sendKeys(keysToSend);

            waitAlertPresence(driver).sendKeys(keysToSend);
        }

        public String getTextAlert(WebDriver driver){
           // return driver.switchTo().alert().getText();
            return waitAlertPresence(driver).getText();
        }

        // Windows
        public void switchToWindowByID(WebDriver driver, String parentID) {
            Set<String> allWindows = driver.getWindowHandles();
            for (String runWindow : allWindows) {
                if (!runWindow.equals(parentID)) {
                    driver.switchTo().window(runWindow);
                    break;
                }
            }
        }

    public void switchToWindowByTitle(WebDriver driver, String title) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
            driver.switchTo().window(runWindows);
            String currentWin = driver.getTitle();
            if (currentWin.equals(title)) {
                break;
            }
        }
    }

    public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
            if (!runWindows.equals(parentID)) {
                driver.switchTo().window(runWindows);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
    }

    public void sleepInSeconds(long timeInSeconds) {
            try {
                Thread.sleep(timeInSeconds*1000);
            }catch (InterruptedException e){
               throw new RuntimeException(e);
            }
    }


    //10.2 switchToDefaultContent
    public WebDriver switchToDefaultContent(WebDriver driver){
        return driver.switchTo().defaultContent();
    }

    public void changeLanguage(WebDriver driver) {

    }
    //11. User Action

    public void clickToElement(WebDriver driver, WebElement element) {
        element.click();
    }

    public void sendKeysToElement(WebElement element, String keysToSend) {
            element.clear();
        element.sendKeys(keysToSend);
    }

    public void selectItemInDropDown(WebElement element, String textItem) {
        new Select(element).selectByVisibleText(textItem);
    }

    public String getSelectedItemInDropDown(WebElement element) {
        return new Select(element).
                getFirstSelectedOption().getText();
    }
    public String getAttributeValue(WebElement element, String attributeName) {
        return element.getDomAttribute(attributeName);
    }

    public String getElementText(WebElement element) {
        return element.getText();
    }

    public boolean isElementDisplay(WebElement element) {
        return element.isDisplayed();
    }
    //8. isElementSelected
    public boolean isElementSelected(WebElement element) {
        return element.isSelected();
    }
    public void waitForElementVisible(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementClickable(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(element));
    }






    //================
    //Wait

}
