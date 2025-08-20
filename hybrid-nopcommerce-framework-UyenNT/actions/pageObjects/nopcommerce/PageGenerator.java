package pageObjects.nopcommerce;

import org.openqa.selenium.WebDriver;
import pageObjects.nopcommerce.admin.AdminDashboardPO;
import pageObjects.nopcommerce.admin.AdminLoginPO;
import pageObjects.nopcommerce.user.*;

public class PageGenerator {
    public static UserHomePO getHomePage(WebDriver driver){
        return new UserHomePO(driver);
    }
    public static UserLoginPO getLoginPage(WebDriver driver){
        return new UserLoginPO(driver);
    }
    public static UserRegisterPO getRegisterPage(WebDriver driver){
        return new UserRegisterPO(driver);
    }
    public static UserCustomerInforPO getCustomerInforPage(WebDriver driver){
        return new UserCustomerInforPO(driver);
    }
    public static UserAddressPO getAdressPage(WebDriver driver){
        return new UserAddressPO(driver);
    }
    public static UserChangePasswordPO getChangePasswordPage(WebDriver driver){
        return new UserChangePasswordPO(driver);
    }
    public static UserOrderPO getOrderPage(WebDriver driver){
        return new UserOrderPO(driver);
    }
    public static UserRewardPointPO getRewardPointPage(WebDriver driver){
        return new UserRewardPointPO(driver);
    }

    public static AdminLoginPO getAdminLoginPage(WebDriver driver){
        return new AdminLoginPO(driver);
    }

    public static AdminDashboardPO getAdminDashboardPage(WebDriver driver){
        return new AdminDashboardPO(driver);
    }
}
