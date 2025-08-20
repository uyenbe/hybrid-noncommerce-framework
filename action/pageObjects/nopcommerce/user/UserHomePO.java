package pageObjects.nopcommerce.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.nopcommerce.PageGenerator;
import pageUIs.nopcommerce.user.UserHomePageUI;

//ở đây dùng kế thừa các class trong BasePage bởi vì:
//- Homepage object sẽ chứa các action trong page đó. Mà BasePage lại có các hàm chứa các action đó để dùng chung rồi
// >> kế thừa BasePage để chỉ việc gọi ra và dùng thôi
//-
public class UserHomePO extends BasePage {
    private WebDriver driver;

    //Hàm khởi tạo - Constructor function
    //Đặc tính:
    //1. Cùng tên với tên Class
    //2. Ko có kiểu trả vể (data type)
    //3. Chạy đầu tiên  khi class này được gọi (new HomePageObject)
    //4. Có thể có tham số hoặc không
    //5. Ko tự define hàm hởi taạo thì JVM sẽ mặc định tạo ra 1 hàm khởi tạo rỗng (ko nhìn thấy)

    public UserHomePO(WebDriver driver) {
        this.driver = driver;
    }

    public UserRegisterPO openRegisterPage() {
        waitForElementClickable(driver, UserHomePageUI.REGISTER_LINK);
        clickToElement(driver, UserHomePageUI.REGISTER_LINK);
        return PageGenerator.getUserRegisterPage(driver);
    }

    public UserLoginPO openLoginPage() {
        waitForElementClickable(driver, UserHomePageUI.LOGIN_BUTTON);
        clickToElement(driver, UserHomePageUI.LOGIN_BUTTON);
        return PageGenerator.getUserLoginPage(driver);
    }

    public boolean isMyAccountLinkDisplayed() {
        // để return false mà ko phải return True để:
        // tránh TH quên ko implement hàm này thì sẽ trả về mặc định là false >> báo lỗi
        //Nếu để return true >> hàm này luôn trả về đúng >
        // >> nếu quên ko implement hàm thì sẽ ko báo lỗi và luôn pass
        waitForElementVisible(driver, UserHomePageUI.MY_ACCOUNT_LINK);
        //return false;
        return isElementDisplay(driver, UserHomePageUI.MY_ACCOUNT_LINK);
    }

    public UserCustomerInforPO openCustomerInforPage() {
        waitForElementClickable(driver, UserHomePageUI.MY_ACCOUNT_LINK);
        clickToElement(driver, UserHomePageUI.MY_ACCOUNT_LINK);
        return PageGenerator.getUserCustomerPage(driver);
    }

//    public void clickChangeLanguage() {
//        waitForElementClickable();
//    }
}
