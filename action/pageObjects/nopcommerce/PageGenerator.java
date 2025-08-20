package pageObjects.nopcommerce;

import org.openqa.selenium.WebDriver;
import pageObjects.nopcommerce.admin.AdminDashboardPO;
import pageObjects.nopcommerce.admin.AdminLoginPO;
import pageObjects.nopcommerce.user.*;

public class PageGenerator {
    public static UserHomePO getUserHomePage(WebDriver driver) {
        return new UserHomePO(driver);
    }

    public static UserLoginPO getUserLoginPage(WebDriver driver) {
        return new UserLoginPO(driver);
    }

    public static UserRegisterPO getUserRegisterPage(WebDriver driver) {
        return new UserRegisterPO(driver);
    }

    public static UserCustomerInforPO getUserCustomerPage(WebDriver driver) {
        return new UserCustomerInforPO(driver);
    }
    public static UserAdressPO getUserAdressPage(WebDriver driver) {
        return new UserAdressPO(driver);
    }

    public static UserRewardPointPO getUserRewardPointPage(WebDriver driver) {
        return new UserRewardPointPO(driver);
    }

    public static UserOrderPO getUserOrderPage(WebDriver driver) {
        return new UserOrderPO(driver);
    }

    public static AdminLoginPO getAdminLoginPage(WebDriver driver) {
        return new AdminLoginPO(driver);
    }

    public static AdminDashboardPO getAdminDashboardPage(WebDriver driver) {
        return new AdminDashboardPO(driver);
    }
}
