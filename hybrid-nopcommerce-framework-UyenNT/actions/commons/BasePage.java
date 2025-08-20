package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.nopcommerce.PageGenerator;
import pageObjects.nopcommerce.user.*;
import pageUIs.nopcommerce.BasePageUIs;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {
    //Các steps viết hàm:
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
    public void openPageURL(WebDriver driver, String url) {
        driver.get(url);
    }

    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public String getCurrentURL(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public String getPageSource(WebDriver driver) {
        return driver.getPageSource();
    }

    public void backTpPage(WebDriver driver) {
        driver.navigate().back();
    }

    public void forwardTpPage(WebDriver driver) {
        driver.navigate().forward();
    }

    public void refreshToPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    //Alert
    public Alert waitAlertPresent(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.alertIsPresent());
    }

    public void acceptAlertPresent(WebDriver driver) {
        waitAlertPresent(driver).accept();
    }

    public void cancelAlertPresent(WebDriver driver) {
        waitAlertPresent(driver).dismiss();
    }

    public void sendKeyToAlert(WebDriver driver, String keyToSend) {
        waitAlertPresent(driver).sendKeys(keyToSend);
    }

    public String getTextAlert(WebDriver driver) {
        return waitAlertPresent(driver).getText();
    }

    //Wndows
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
        for (String runWindow : allWindows) {
            driver.switchTo().window(runWindow);
            String currentWinTitle = driver.getTitle();
            if (!currentWinTitle.equals(title)) {
                driver.switchTo().window(runWindow);
                break;
            }
        }
    }

    public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            if (!runWindow.equals(parentID)) {
                driver.switchTo().window(runWindow);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
    }

    // Web Element: Hàm tương tác với các element

    public WebElement getElement(WebDriver driver, String locator) {
        return driver.findElement(getByLocator(locator));
    }

    public By getByXpath(String locator){
        return By.xpath(locator);
    }

    // hàm refactor locator dynamic
    public By getByLocator(String prefixLocator){
        By by = null;
        if (prefixLocator.toLowerCase().startsWith("css")){
            by = By.cssSelector(prefixLocator.substring(4));
        }else if (prefixLocator.toLowerCase().startsWith("xpath")){
            by = By.xpath(prefixLocator.substring(6));
        }else if (prefixLocator.toLowerCase().startsWith("id")){
            by = By.id(prefixLocator.substring(3));
        }else if (prefixLocator.toLowerCase().startsWith("class")){
            by = By.className(prefixLocator.substring(6));
        }else if (prefixLocator.toLowerCase().startsWith("name")){
            by = By.name(prefixLocator.substring(5));
        }else if (prefixLocator.toLowerCase().startsWith("tagname")){
            by = By.tagName(prefixLocator.substring(8));
        }else{
            throw new RuntimeException("Prefix locator is not supported: " + prefixLocator);
        }
        return by;
    }

    // Cast data type
    private String castParameter(String locator, String... parameter) {
        return String.format(locator, (Object[]) parameter);
    }



    public void clickToElement(WebDriver driver, String locator) {
        getElement(driver, locator).click();
    }

    public void clickToElement(WebDriver driver, String locator, String... parameter) {
        getElement(driver, castParameter(locator, parameter)).click();
    }

    public void sendKeyToElement(WebDriver driver, String locator, String keyToSend) {
        getElement(driver, locator).clear();
        getElement(driver, locator).sendKeys(keyToSend);
    }

    public void sendKeyToElement(WebDriver driver, String locator, String keyToSend, String... parameter) {
        getElement(driver, castParameter(locator, parameter)).clear();
        getElement(driver, castParameter(locator, parameter)).sendKeys(keyToSend);
    }

    public String getElementText(WebDriver driver, String locator) {
        return getElement(driver, locator).getText();
    }

    public String getAttributeValue(WebDriver driver, String locator, String attributeValue) {
        return getElement(driver, locator).getDomAttribute(attributeValue);
    }

    public String getAttributeValue(WebDriver driver, String locator, String attributeValue, String... parameter) {
        return getElement(driver, castParameter(locator, parameter)).getDomAttribute(attributeValue);
    }

    public String getCssValue(WebDriver driver, String locator, String cssName) {
        return getElement(driver, locator).getCssValue(cssName);
    }

    public String getHexaColorFromRGBA(WebDriver driver, String locator) {
        return getElement(driver, locator).getCssValue("background-color");
    }

    public String getHexaColorFromRGBA1(String rgba) {
        return Color.fromString(rgba).asHex().toUpperCase();
    }

    public List<WebElement> getListElements(WebDriver driver, String locator) {
        return driver.findElements(By.xpath(locator));
    }

    public int getElementSize(WebDriver driver, String locator) {
        return getListElements(driver, locator).size();
    }

    //Default dropdown list
    public void selectedItemInDropDown(WebDriver driver, String locator, String textItem) {
        new Select(getElement(driver, locator)).selectByVisibleText(textItem);
    }

    public String getSelectedItemInDropDown(WebDriver driver, String locator) {
        return new Select(getElement(driver, locator)).getFirstSelectedOption().getText();
    }

    public boolean isDroplistMultiple(WebDriver driver, String locator) {
        return new Select(getElement(driver, locator)).isMultiple();
    }

    // Custommer dropdown list
    public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
        getElement(driver, parentLocator).click();
        sleepInSeconds(2);
        List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childItemLocator)));
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
            Thread.sleep(timeInSeconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void checkedTheCheckbox(WebDriver driver, String locator) {
        if (!getElement(driver, locator).isSelected()) {
            getElement(driver, locator).click();
        }
    }

    public void uncheckedTheCheckbox(WebDriver driver, String locator) {
        if (getElement(driver, locator).isSelected()) {
            getElement(driver, locator).click();
        }
    }

    public boolean isElementDisplayed(WebDriver driver, String locator) {
        return getElement(driver, locator).isDisplayed();
    }

    public boolean isElementDisplayed(WebDriver driver, String locator, String... parameter) {
        return getElement(driver, castParameter(locator, parameter)).isDisplayed();
    }

    public boolean isElementSelected(WebDriver driver, String locator) {
        return getElement(driver, locator).isSelected();
    }

    public boolean isElementSelected(WebDriver driver, String locator, String... parameter) {
        return getElement(driver, castParameter(locator, parameter)).isSelected();
    }

    public boolean isElemementEnabled(WebDriver driver, String locator) {
        return getElement(driver, locator).isEnabled();
    }

    public boolean isElemementEnabled(WebDriver driver, String locator, String... parameter) {
        return getElement(driver, castParameter(locator, parameter)).isEnabled();
    }

    //Iframe/Frame
    public WebDriver switchToIframe(WebDriver driver, String locator) {
        return driver.switchTo().frame(getElement(driver, locator));
    }

    public WebDriver switchToDefaultContent(WebDriver driver) {
        return driver.switchTo().defaultContent();
    }

    //11. User Action
    public void doubleClickToElement(WebDriver driver, String locator) {
        Actions actions = new Actions(driver);
        actions.doubleClick(getElement(driver,locator)).perform();
    }
    public void hoverMouseToElement(WebDriver driver, String locator){
        Actions actions = new Actions(driver);
        actions.moveToElement(getElement(driver,locator)).perform();
    }
    public void releaseLeftMouse(WebDriver driver){
        new Actions(driver).release().perform();
    }
    public void rightClick(WebDriver driver, String locator){
        Actions actions = new Actions(driver);
        actions.contextClick(getElement(driver,locator)).perform();
    }
    public void dragAndDrop(WebDriver driver, String locator1, String locator2){
        Actions actions = new Actions(driver);
        actions.dragAndDrop(getElement(driver,locator1), getElement(driver,locator2)).perform();
    }
    public void scrollToElement(WebDriver driver, String locator){
        Actions actions = new Actions(driver);
        actions.scrollToElement(getElement(driver,locator)).perform();
    }
    public void presskeyToElement(WebDriver driver, String locator, Keys keyToSend){
        Actions actions = new Actions(driver);
        actions.sendKeys(getElement(driver,locator)).perform();
    }

    public void presskeyToElement(WebDriver driver, String locator, Keys keyToSend, String... parameter){
        Actions actions = new Actions(driver);
        actions.sendKeys(getElement(driver,castParameter(locator, parameter))).perform();
    }
    public void clickAndHold(WebDriver driver, String locator){
        Actions action = new Actions(driver);
        action.clickAndHold(getElement(driver,locator)).perform();
    }
    public void clickToElementByAction(WebDriver driver, String locator){
        Actions action = new Actions(driver);
        action.click(getElement(driver,locator)).perform();
    }

    // Wait

    public void waitForElementVisible(WebDriver driver, String locator){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
                wait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
    }

    public void waitForElementVisible(WebDriver driver, String locator, String... parameter){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(castParameter(locator, parameter))));
    }

    public void waitForElementSelected(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).
                until(ExpectedConditions.elementToBeSelected(getByLocator(locator)));
    }

    public void waitForElementSelected(WebDriver driver, String locator, String... parameter){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).
                until(ExpectedConditions.elementToBeSelected(getByLocator(castParameter(locator, parameter))));
    }

    public void waitForElementInvisible(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                .until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
    }

    public void waitForElementInvisible(WebDriver driver, String locator, String... parameter){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                .until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(castParameter(locator, parameter))));
    }

    public void waitForElementPresence(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                .until(ExpectedConditions.presenceOfElementLocated(getByLocator(locator)));
    }

    public void waitForElementPresence(WebDriver driver, String locator, String... parameter){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                .until(ExpectedConditions.presenceOfElementLocated(getByLocator(castParameter(locator, parameter))));
    }
    //7. waitForListElementPresence
    public void waitForListElementPresence(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(locator)));
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

    public void waitForElementClickable(WebDriver driver, String locator, String... parameter){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(getByLocator(castParameter(locator, parameter))));
    }


    //Js Executor
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

    // check
    public void clickToElementJS(WebDriver driver, String locator, String... dynamicValues) {
        WebElement element = getElement(driver, castParameter(locator, dynamicValues));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
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


    // Các hàm switch page
    public UserOrderPO openOrderPage(WebDriver driver){
        waitForElementClickable(driver, BasePageUIs.ORDER_LINK);
        clickToElement(driver, BasePageUIs.ORDER_LINK);
        return PageGenerator.getOrderPage(driver);
    }
    public UserAddressPO openAddressPage(WebDriver driver){
        waitForElementClickable(driver, BasePageUIs.ADDRESS_LINK);
        clickToElement(driver, BasePageUIs.ADDRESS_LINK);
        return PageGenerator.getAdressPage(driver);
    }
    public UserChangePasswordPO openChangePasswordPage(WebDriver driver){
        waitForElementClickable(driver, BasePageUIs.CHANGE_PASSWORD_LINK);
        clickToElement(driver, BasePageUIs.CHANGE_PASSWORD_LINK);
        return PageGenerator.getChangePasswordPage(driver);
    }
    public UserRewardPointPO openRewardPointPage(WebDriver driver){
        waitForElementClickable(driver, BasePageUIs.REWARD_POINT_LINK);
        clickToElement(driver, BasePageUIs.REWARD_POINT_LINK);
        return PageGenerator.getRewardPointPage(driver);
    }
    public UserCustomerInforPO openCustomerInforPage(WebDriver driver){
        waitForElementClickable(driver, BasePageUIs.CUSTOMER_INFOR_LINK);
        clickToElement(driver, BasePageUIs.CUSTOMER_INFOR_LINK);
        return PageGenerator.getCustomerInforPage(driver);
    }




}
