package commons;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePage_Exercise {

    //1. Access modifier: public/ protected/ private/ default
    //2. Kiểu dữ liệu của hàm (Data Type): void/int/String/boolean...
    //3. Tên hàm: Đặt tên theo chức năng đang cần viết
    //- Convention tuân theo chuẩn của từng ngôn ngữ(Java)
    //- camelCase
    //4. Có tham số hay ko tuỳ thuộc vào chức năng cần viết
    //5. Kiểu dữ liệu trả về cho hàm:
    //-Nếu như có return data >> dữ liệu return phải khớp vs dữ liệu ở steps 2
    //- Nếu như có return thì nó phải ở steps cuối

    private WebDriver driver;
    private WebDriverWait wait;

    public void clickToElement() {
        //Tại steps 5: kiểu trả về của hàm click là void >> trùng với data type ở steps 2
        // với data type = void >> ko cần return dữ liệu trả về
        driver.findElement(By.cssSelector("")).click();
    }


    public String getElementText() {
        //Steps 5: data type của hàm getText là String >> đang khác với data type ban đầu của hàm (void)
        // >> update data type ban đầu từ void >> String
        // đồng thời return dữ liệu tại bước 5
        //Tất cả các data type # void thì đều phải return data trả về
        return driver.findElement(By.cssSelector("")).getText();
    }

    //Giá trị của hàm ko được fix cứng mà sẽ truyền từ bên ngoài thông quan các tham số
    public void sendKeys( String value) {
        driver.findElement(By.cssSelector("")).sendKeys(value);
    }

    // Hàm này ko có tham số vì isDisplayed() ko có tham số truyền vào
    public boolean isElementDisplay(){
        return driver.findElement(By.cssSelector("")).isDisplayed();
    }

    //======================
    // Bài tập:
    //Part 1
    //Viết các hàm:
    // getURL >> ko có return data
    public void getURL(String url) {
        driver.get(url);
    }
    // getTitle
    public String getTitle(){
        return driver.getTitle();
    }
    // getCurrentURL,
    public String getCurrentURL(){
        return driver.getCurrentUrl();
    }
    // getPageSource,
    public String getPageSource(){
        return driver.getPageSource();
    }
    // back,
    public void back() {
        driver.navigate().back();

    }
    // forward,
    public void forward() {
        driver.navigate().forward();
    }
    // refresh,
    public void refresh() {
        driver.navigate().refresh();
    }
    // Alert,
    public Alert alert(){
        return driver.switchTo().alert();
    }
    // waitAlertPresence,
    public void waitAlertPresence() throws InterruptedException {
        driver.switchTo().alert().wait(3);
    }
    // acceptAlert,
    public void acceptAlert(){
        driver.switchTo().alert().accept();
    }
    // cancelAlert,
    public void cancelAlert(){
        driver.switchTo().alert().dismiss();
    }
    // getTextAlert
    public String getTextAlert(){
        return driver.switchTo().alert().getText();
    }
    // sendkeyToAlert
    public void sendkeyToAlert(){
        driver.switchTo().alert().sendKeys("");
    }


    //============================
    //Part2
    public WebElement getElement(WebDriver driver, String locator) {
        return driver.findElement(By.xpath(locator));
    }

    public List<WebElement> getElements(WebDriver driver, String locator) {
        return driver.findElements(By.xpath(locator));
    }
    //1. getAttributeValue
    public String getAttributeValue(WebDriver driver,String locator, String attributeName) {
        return getElement(driver, locator).getDomAttribute(attributeName);
    }
    //2. getCssValue
    public String getCssValue(WebDriver driver,String locator, String cssName) {
        return getElement(driver, locator).getCssValue(cssName);
    }
    //3. gethexaColorFromRGBA
    public String gethexaColorFromRGBA(WebDriver driver, String locator) {
        return gethexaColorFromRGBA(driver, locator);
    }
    //4. getElementsSize(findElements)
    public int getElementsSize(WebDriver driver, String locator) {
        return getElements(driver, locator).size();
    }
    //5. checkTheCheckbox
    public void checkTheCheckbox(WebDriver driver, String locator) {
        getElement(driver, locator).click();
    }
    //6. UncheckTheCheckbox
    public void UncheckTheCheckbox(WebDriver driver, String locator){
        getElement(driver, locator).click();
    }
    //7. isControlDisplay
    public boolean isControlDisplay(WebDriver driver, String locator){
       return getElement(driver, locator).isDisplayed();
    }
    //8. isControlSelected
    public boolean isControlSelected(WebDriver driver, String locator){
        return getElement(driver, locator).isSelected();
    }
    //9. isControlEnable
    public boolean isControlEnable(WebDriver driver, String locator){
        return getElement(driver, locator).isEnabled();
    }
    //10. Frame/Ifame
    //10.1 switchToIframe/Frame
    public WebDriver switchToIframe(WebDriver driver, String locator) {
        return driver.switchTo().frame(locator);
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
    //11.3 rightClick
    public void rightClick(WebDriver driver, String locator){
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
    public void sendkeyToElement(WebDriver driver, String locator, String keyToSend){
        Actions action = new Actions(driver);
        action.sendKeys(getElement(driver,locator),keyToSend ).perform();
    }

}
