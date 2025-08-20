package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.nopcommerce.PageGenerator;
import pageObjects.nopcommerce.user.UserAdressPO;
import pageObjects.nopcommerce.user.UserCustomerInforPO;
import pageObjects.nopcommerce.user.UserOrderPO;
import pageObjects.nopcommerce.user.UserRewardPointPO;
import pageUIs.nopcommerce.BasePageUIs;

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

        //Biến Global - toàn cục - sinh ra ở phạm vi Class
        private String fullName;

        public String getFullName() {// biến được sinh ra trong tham số của hàm

            //Biến local - cục bộ - được sinh ra trong thân hàm
            String fullName = null;

            //Biêns global nằm trong phạm vi khối lệnh

            for (int i = 0; i <10; i++){
                int n = 1;

                if (n >0){
                    int x = 10;
                }
            }
            return this.fullName;
        }
        //Selenium web browser function
        //Common function - hàm dùng chung cho nhiều class khác
        // không truyền 1 dữ liệu cụ thể/riêng tư vào được
        // Tuân thủ theo tính đóng gói
        // Hàm static có thể truy cập trực tiếp từ phạm vi Class mà ko cần phải khởi tạo đối tượng từ class đó
        public static BasePage getBasePage() {
            return new BasePage();
        }
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
            return new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).
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

    // Web Element: Hàm tương tác với các element
    //Với các hàm này ngoài tham số WebDriver còn có thêm tham số Locator

    public WebElement getElement(WebDriver driver, String locator) {
          // return driver.findElement(By.xpath(locator));
        return driver.findElement(getByLocator(locator));
    }

    // Truyen tham so vao loai gi se tra ve kieu By tuong ung

    public By getByXpath(String locator){
        return By.xpath(locator);
    }

    //Add thêm các hàm ép kiểu dữ liệu và phải cast sang kiểu mảng Object
    // - vì hàm có thể chứa 1 hoặc nhiều tham số
    // Hàm castParameter sẽ có 2 tham số là: locator và restParameters
    private String castParameter(String locator, String... restParameters) {
        return String.format(locator, (Object[]) restParameters);
    }

    // Truyền tham số vào loại gì sẽ trả về kiểu By tương ứng
    // String prefix: css/id/name/xpath/class
    // Ví dụ: css = button#login >> By.cssSelector("locator")
    // xpath=
    public By getByLocator(String prefixLocator){
        By by = null;
        if (prefixLocator.toLowerCase().startsWith("css")){
            by = By.cssSelector(prefixLocator.substring(4));
        }else if (prefixLocator.toLowerCase().startsWith("xpath")){
            by = By.xpath(prefixLocator.substring(6));
        }else if (prefixLocator.toLowerCase().startsWith("id")){
            by = By.id(prefixLocator.substring(3));
        }else if (prefixLocator.toLowerCase().startsWith("name")){
            by = By.name(prefixLocator.substring(5));
        }else if (prefixLocator.toLowerCase().startsWith("class")){
            by = By.className(prefixLocator.substring(6));
        }else if (prefixLocator.toLowerCase().startsWith("tagname")){
        by = By.tagName(prefixLocator.substring(8));
        }
        else{
            throw new RuntimeException("Prefix locator is not support");
        }
       // System.out.println(by);
        return by;
    }


    public void clickToElement(WebDriver driver, String locator) {
            getElement(driver, locator).click();
    }

    public void clickToElement(WebDriver driver, String locator, String... restParameter) {
        getElement(driver, castParameter(locator, restParameter)).click();
    }

    public void sendKeysToElement(WebDriver driver, String locator, String keysToSend) {
//           if (getElement(driver, locator).getText() != null) {
//               getElement(driver, locator).clear();
//           }else {
//               getElement(driver, locator).sendKeys(keysToSend);
//           }
        // cách viết trên chưa hợp lý, thay vì phải viết hàm if thì clear luôn sau đó sendKey
        getElement(driver, locator).clear();
        getElement(driver, locator).sendKeys(keysToSend);

    }

    public void sendKeysToElement(WebDriver driver, String locator, String keysToSend, String... restParameter) {
        // cách viết trên chưa hợp lý, thay vì phải viết hàm if thì clear luôn sau đó sendKey
        getElement(driver, castParameter(locator, restParameter)).clear();
        getElement(driver, castParameter(locator, restParameter)).sendKeys(keysToSend);

    }

    public String getElementText(WebDriver driver, String locator) {
         return getElement(driver, locator).getText();
    }
    public void changeLanguage(WebDriver driver) {
        String languageChange = driver.findElement(By.cssSelector("select#customerlanguage option[selected = 'selected']")).getText();
        if (languageChange.equals("VI")){
            driver.findElement(By.cssSelector("select#customerlanguage")).click();
            driver.findElement(By.xpath("//option[text()='EN']")).click();

        }

    }

    //Default droplist
    public void selectItemInDropDown(WebDriver driver, String locator, String textItem) {
            new Select(getElement(driver,locator)).selectByVisibleText(textItem);
    }

    public void selectItemInDropDown(WebDriver driver, String locator, String textItem, String... restParameter) {
            new Select(getElement(driver, castParameter(locator, restParameter))).selectByValue(textItem);
    }

    public String getSelectedItemInDropDown(WebDriver driver, String locator) {
           return new Select(getElement(driver,locator)).
                   getFirstSelectedOption().getText();
    }

    public boolean isDroplistMultiple(WebDriver driver, String locator){
        return new Select(getElement(driver,locator)).isMultiple();
    }

    public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {

         getElement(driver, parentLocator).click();
         sleepInSeconds(2);

        List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childItemLocator)));

        sleepInSeconds(2);
        for (WebElement item : allItems) {
            if (item.getText().trim().equals(expectedItem)) {

                item.click();
                break;
            }
        }
    }

    public void sleepInSeconds(long timeInSeconds) {
            try {
                Thread.sleep(timeInSeconds*1000);
            }catch (InterruptedException e){
               throw new RuntimeException(e);
            }
    }

    public String getAttributeValue(WebDriver driver,String locator, String attributeName) {
        return getElement(driver, locator).getDomAttribute(attributeName);
    }

    public String getAttributeValue(WebDriver driver,String locator, String attributeName, String... restParameter) {
        return getElement(driver, castParameter(locator, restParameter)).getDomAttribute(attributeName);
    }

    //2. getCssValue
    public String getCssValue(WebDriver driver,String locator, String cssName) {
        return getElement(driver, locator).getCssValue(cssName);
    }
    //3. gethexaColorFromRGBA
    public String getHexaColorFromRGBA(WebDriver driver, String locator) {
        return getElement(driver,locator ).getCssValue("background-color");
    }

    public String gethexaColorFromRGBA1( String rgba) {
        return Color.fromString(rgba).asHex().toUpperCase();
    }
    //4. getElementsSize(findElements)
    public List<WebElement> getListElements(WebDriver driver, String locator) {
        return driver.findElements(By.xpath(locator));
    }

    public int getElementsSize(WebDriver driver, String locator) {
        return getListElements(driver, locator).size();
    }

    //5. checkTheCheckbox
    public void checkTheCheckbox(WebDriver driver, String locator) {
        if (!getElement(driver, locator).isSelected()){
            getElement(driver, locator).click();
        }

    }
    //6. UncheckTheCheckbox
    public void UncheckTheCheckbox(WebDriver driver, String locator){
        if (getElement(driver, locator).isSelected()){
            getElement(driver, locator).click();
        }
    }
    //7. isElementDisplay
    public boolean isElementDisplay(WebDriver driver, String locator){
        return getElement(driver, locator).isDisplayed();
    }

    public boolean isElementDisplay(WebDriver driver, String locator, String... restParameter) {
        return getElement(driver, castParameter(locator, restParameter)).isDisplayed();
    }

    //8. isElementSelected
    public boolean isElementSelected(WebDriver driver, String locator){
        return getElement(driver, locator).isSelected();
    }

    public boolean isElementSelected(WebDriver driver, String locator, String... restParameter) {
        return getElement(driver, castParameter(locator, restParameter)).isSelected();
    }
    //9. isElementEnable
    public boolean isElementEnable(WebDriver driver, String locator){
        return getElement(driver, locator).isEnabled();
    }

    public boolean isElementEnable(WebDriver driver, String locator, String... restParameter){
        return getElement(driver, castParameter(locator, restParameter)).isEnabled();
    }

    //10.1 switchToIframe/Frame
    public WebDriver switchToIframe(WebDriver driver, String locator) {
        return driver.switchTo().frame(getElement(driver, locator));
    }
    //10.2 switchToDefaultContent
    public WebDriver switchToDefaultContent(WebDriver driver){
        return driver.switchTo().defaultContent();
    }
    //11. User Action

    //11.1 doubleClickToElement
    public void doubleClickToElement(WebDriver driver, String locator) {
        Actions action = new Actions(driver);
        action.doubleClick(getElement(driver,locator)).perform();
    }
    //11.2 hoverMouseToElement
    public void hoverMouseToElement(WebDriver driver, String locator){
        Actions action = new Actions(driver);
        action.moveToElement(getElement(driver, locator)).perform();
    }
    // Release mouse
    public void releaseLeftMouse(WebDriver driver){
        new Actions(driver).release();
    }
    //11.3 rightClick
    public void rightClick(WebDriver driver, String locator) {
        Actions action = new Actions(driver);
        action.contextClick(getElement(driver, locator)).perform();
    }
    //11.4 dragAndDrop
    public void dragAndDrop(WebDriver driver, String locator1, String locator2){
        Actions action = new Actions(driver);
        action.dragAndDrop(getElement(driver, locator1),getElement(driver, locator2)).perform();
    }
    //11.5 scrollToElement
    public void scrollToElement(WebDriver driver, String locator){
        Actions action = new Actions(driver);
        action.scrollToElement(getElement(driver, locator)).perform();
    }
    //11.6 sendkeyToElement
    public void pressKeyToElement(WebDriver driver, String locator, Keys keysToSend) {
        Actions action = new Actions(driver);
        action.sendKeys(getElement(driver,locator),keysToSend ).perform();
    }

    public void pressKeyToElement(WebDriver driver, String locator, Keys keysToSend, String... restParameter) {
        Actions action = new Actions(driver);
        action.sendKeys(getElement(driver,castParameter(locator, restParameter)),keysToSend ).perform();
    }

    //11.7 clickAndHold
    public void clickAndHold(WebDriver driver, String locator){
        Actions action = new Actions(driver);
        action.clickAndHold(getElement(driver,locator)).perform();
    }

    //11.8 clickByAction
    public void clickToElementByAction(WebDriver driver, String locator){
        Actions action = new Actions(driver);
        action.click(getElement(driver,locator)).perform();
    }

    //============
    //JS Executor
    public void hightlightElement(WebDriver driver, String locator) {
        WebElement element = getElement(driver, locator);
        String originalStyle = element.getAttribute("style");
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSeconds(2);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getElement(driver, locator));
        sleepInSeconds(3);
    }

    public void scrollToElementOnTopByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locator));
    }

    public void scrollToElementOnDownByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getElement(driver, locator));
    }

    public void scrollToBottomPageByJS(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void setAttributeInDOM(WebDriver driver, String locator, String attributeName, String attributeValue) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue + "');", getElement(driver, locator));
    }

    public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(driver, locator));
    }

    public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(driver, locator));
    }

    public String getAttributeInDOMByJS(WebDriver driver, String locator, String attributeName) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(driver, locator));
    }

    public String getElementValidationMessage(WebDriver driver, String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getElement(driver, locator));
    }

    public boolean isImageLoaded(WebDriver driver, String locator) {
        return (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete " +
                "&& typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
                getElement(driver, locator));
    }

    //================
    //Wait

    //1. waitForElementVisible
    public void waitForElementVisible(WebDriver driver, String locator){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
    }

    public void waitForElementVisible(WebDriver driver, String locator, String... resParameter){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(castParameter(locator, resParameter))));
    }

    //3. waitForElementSelected
    public void waitForElementSelected(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                .until(ExpectedConditions.elementToBeSelected(getByLocator(locator)));
    }

    public void waitForElementSelected(WebDriver driver, String locator, String... resParameter){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                .until(ExpectedConditions.elementToBeSelected(getByLocator(castParameter(locator, resParameter))));
    }
    //4. waitForElementInvisible
    public void waitForElementInvisible(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                .until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
    }

    public void waitForElementInvisible(WebDriver driver, String locator, String... resParameter){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                .until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(castParameter(locator, resParameter))));
    }

    //6. waitForElementPresence
    public void waitForElementPresence(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                .until(ExpectedConditions.presenceOfElementLocated(getByLocator(locator)));
    }

    public void waitForElementPresence(WebDriver driver, String locator, String... resParameter){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                .until(ExpectedConditions.presenceOfElementLocated(getByLocator(castParameter(locator, resParameter))));
    }
    //7. waitForListElementPresence
    public void waitForListElementPresence(WebDriver driver, String locator){
            new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                    .until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(locator)));
    }

    public void waitForListElementPresence(WebDriver driver, String locator, String... resParameter){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(castParameter(locator, resParameter))));
    }

    //8. waitForAlertPresence
    public void waitForAlertPresence(WebDriver driver){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                .until(ExpectedConditions.alertIsPresent());
    }


    //8. waitForElementClickable
    public void waitForElementClickable(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
    }

    public void waitForElementClickable(WebDriver driver, String locator, String... resParameter){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(getByLocator(castParameter(locator, resParameter))));
    }

    // Các hàm open các Page only use Level_07
    public UserRewardPointPO openRewardPointPage(WebDriver driver) {
        waitForElementClickable(driver, BasePageUIs.REWARD_POINT_LINK);
        clickToElement(driver, BasePageUIs.REWARD_POINT_LINK);
        return PageGenerator.getUserRewardPointPage(driver);
    }

    public UserCustomerInforPO openCustomerInforPage(WebDriver driver) {
        waitForElementClickable(driver, BasePageUIs.CUSTOMER_INFOR_LINK);
        clickToElement(driver, BasePageUIs.CUSTOMER_INFOR_LINK);
        return PageGenerator.getUserCustomerPage(driver);
    }
    public UserAdressPO openAddressPage(WebDriver driver) {
        waitForElementClickable(driver, BasePageUIs.ADDRESS_LINK);
        clickToElement(driver, BasePageUIs.ADDRESS_LINK);
        return PageGenerator.getUserAdressPage(driver);
    }
    public UserOrderPO openOrderPage(WebDriver driver) {
        waitForElementClickable(driver, BasePageUIs.ORDER_LINK);
        clickToElement(driver, BasePageUIs.ORDER_LINK);
        return PageGenerator.getUserOrderPage(driver);
    }

    public void openAdminSite(WebDriver driver, String adminUrl) {
            openPageURL(driver, adminUrl);
    }
}
