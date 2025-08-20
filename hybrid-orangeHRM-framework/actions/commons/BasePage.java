package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {
    public void openPageURL(WebDriver driver, String url) {
        driver.get(url);
    }
    public String getPageTitle(WebDriver driver){
        return driver.getTitle();
    }
    public String getCurrentUrl(WebDriver driver){
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
    public Alert waitAlertPresent(WebDriver driver){
       return new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.alertIsPresent());
    }
    public void acceptAlert(WebDriver driver){
        waitAlertPresent(driver).accept();
    }

    public void cancelAlert(WebDriver driver){
        waitAlertPresent(driver).dismiss();
    }
    public String getTextAlert(WebDriver driver){
        return waitAlertPresent(driver).getText();
    }
    public void sendkeyToAlert(WebDriver driver, String keys){
        waitAlertPresent(driver).sendKeys(keys);
    }
    public void switchToWindowByID(WebDriver driver, String parentID){
        Set<String>allWindows = driver.getWindowHandles();
        for(String runWindow : allWindows){
            if (!runWindow.equals(parentID)){
                driver.switchTo().window(runWindow);
                break;
            }
        }
    }
    public void switchToWindowByTitle(WebDriver driver, String title){
        Set<String> allWindows = driver.getWindowHandles();
        for(String runWindow : allWindows){
            driver.switchTo().window(runWindow);
            String currentWindow = driver.getTitle();
            if(currentWindow.equals(title)){
                break;
            }
        }
    }
    public void closeAllWindowsWithoutParent(WebDriver driver){}


    //webElement
    public WebElement getElement(WebDriver driver, String locator){
        return driver.findElement(By.xpath(locator));
    }
    public void clickToElement(WebDriver driver, String locator){
        getElement(driver,locator).click();
    }
    public void sendkeysToElement(WebDriver driver,String locator, String keysToSend){
        getElement(driver,locator).sendKeys(keysToSend);
    }
    public String getTextElement(WebDriver driver, String locator){
        return getElement(driver,locator).getText();
    }
    public String getAttributeValue(WebDriver driver, String locator, String attributeName){
        return getElement(driver,locator).getAttribute(attributeName);
    }
    public String getCssValue(WebDriver driver, String locator, String CssName){
        return getElement(driver,locator).getCssValue(CssName);
    }
    public String getHexaColorFromRGBA(WebDriver driver, String locator){
        return getElement(driver,locator).getCssValue("background-color");
    }
    public String gethexaColorFromRGBA1(String rgba1){
        return Color.fromString(rgba1).asHex().toUpperCase();
    }
    public List<WebElement> getListElement(WebDriver driver, String locator){
        return driver.findElements(By.xpath(locator));
    }
    public int getElementSize(WebDriver driver, String locator){
        return getListElement(driver,locator).size();
    }
    public void checkTheCheckbox(WebDriver driver, String locator){
        if (!getElement(driver,locator).isSelected()){
            getElement(driver,locator).click();
        }
    }
    public void uncheckTheCheckbox(WebDriver driver, String locator){
        if (getElement(driver,locator).isSelected()){
            getElement(driver,locator).click();
        }
    }
    public boolean isElementDisplayed(WebDriver driver, String locator){
        return getElement(driver,locator).isDisplayed();
    }
    public boolean isElementEnabled(WebDriver driver, String locator){
        return getElement(driver,locator).isEnabled();
    }
    public boolean isElementSelected(WebDriver driver, String locator){
        return getElement(driver,locator).isSelected();
    }

    //Frame/Iframe
    public WebDriver switchToIframe(WebDriver driver, String locator){
        return driver.switchTo().frame(getElement(driver,locator));
    }

    //Action

    //Wait
    public By getByXpath(String locator){
        return By.xpath(locator);
    }
    public void waitForElementSelected(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(30)).
                until(ExpectedConditions.elementToBeSelected(getByXpath(locator)));
    }

    public void waitForElementVisible(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
    }

    public void waitForElementInvisible(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
    }

    public void waitForElementPresent(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.presenceOfElementLocated(getByXpath(locator)));
    }

    public void waitForListElementPresent(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(30)).
                until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(locator)));
    }

    public void waitForElementClickable(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));
    }

    public void waitForAlertPresent(WebDriver driver){
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.alertIsPresent());
    }
    public void sleepInSeconds(long timeInSeconds) {
        try {
            Thread.sleep(timeInSeconds*1000);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }

    // JS
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


}
