package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

//ở đây dùng kế thừa các class trong BasePage bởi vì:
//- Homepage object sẽ chứa các action trong page đó. Mà BasePage lại có các hàm chứa các action đó để dùng chung rồi
// >> kế thừa BasePage để chỉ việc gọi ra và dùng thôi
//-
public class HomePageFactory extends BasePage {
    private WebDriver driver;

    //Hàm khởi tạo - Constructor function
    //Đặc tính:
    //1. Cùng tên với tên Class
    //2. Ko có kiểu trả vể (data type)
    //3. Chạy đầu tiên khi class này được gọi (new HomePageObject)
    //4. Có thể có tham số hoặc không
    //5. Ko tự define hàm hởi taạo thì JVM sẽ mặc định tạo ra 1 hàm khởi tạo rỗng (ko nhìn thấy)

    public HomePageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
// Locator bắt buộc phải là WebElement trong FindBy
    @FindBy(how = How.CLASS_NAME,using = "ico-register")
    private WebElement registerLink;

    @FindBy(className = "ico-account")
    private WebElement myAccountLink;

    @FindBy(className = "ico-login")
    private WebElement loginButton;


    public void clickToRegisterLink() {
        waitForElementClickable(driver, registerLink);
        clickToElement(driver, registerLink);
    }

    public void clickToLoginButton() {
        waitForElementClickable(driver, loginButton);
        clickToElement(driver, loginButton);
    }

    public boolean isMyAccountLinkDisplayed() {
        waitForElementVisible(driver, myAccountLink);
        return isElementDisplay(myAccountLink);
    }

    public void clickMyAccountLink() {
        waitForElementClickable(driver, myAccountLink);
        clickToElement(driver, myAccountLink);
    }



//    public void clickChangeLanguage() {
//        waitForElementClickable();
//    }
}
