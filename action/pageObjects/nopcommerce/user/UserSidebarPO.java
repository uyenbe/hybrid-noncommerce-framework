package pageObjects.nopcommerce.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.nopcommerce.PageGenerator;
import pageUIs.nopcommerce.user.UserSidebarPageUIs;

public class UserSidebarPO extends BasePage {
    private WebDriver driver;
    public UserSidebarPO(WebDriver driver) {
        this.driver = driver;
    }
    public UserRewardPointPO openUserRewardPointPage() {
        waitForElementClickable(driver, UserSidebarPageUIs.REWARD_POINT_LINK);
        clickToElement(driver, UserSidebarPageUIs.REWARD_POINT_LINK);
        return PageGenerator.getUserRewardPointPage(driver);
    }

    public UserCustomerInforPO openUserCustomerInforPage() {
        waitForElementClickable(driver, UserSidebarPageUIs.CUSTOMER_INFOR_LINK);
        clickToElement(driver, UserSidebarPageUIs.CUSTOMER_INFOR_LINK);
        return PageGenerator.getUserCustomerPage(driver);
    }
    public UserAdressPO openUserAddressPage() {
        waitForElementClickable(driver, UserSidebarPageUIs.ADDRESS_LINK);
        clickToElement(driver, UserSidebarPageUIs.ADDRESS_LINK);
        return PageGenerator.getUserAdressPage(driver);
    }
    public UserOrderPO openUserOrderPage() {
        waitForElementClickable(driver, UserSidebarPageUIs.ORDER_LINK);
        clickToElement(driver, UserSidebarPageUIs.ORDER_LINK);
        return PageGenerator.getUserOrderPage(driver);
    }

    public UserSidebarPO openSidebarLinkByPageName(String pageName) {
        waitForElementClickable(driver, UserSidebarPageUIs.DYNAMIC_LINK_BY_PAGE_NAME,pageName);
        clickToElement(driver, UserSidebarPageUIs.DYNAMIC_LINK_BY_PAGE_NAME,pageName);

        //Chỗ này cần phải khởi tạo ra các page tương ứng với tên
        // Có thể dùng If-else hoặc Switch-case
        // Cách viết này phù hợp cho số lượng case ít.
        // Trong TH có số lượng page nhiều thì switch-case hoặc if-else quá dài >> ko tối ưu
        switch (pageName){
            case "Addresses":
                return PageGenerator.getUserAdressPage(driver);
            case "Reward points":
                return PageGenerator.getUserRewardPointPage(driver);
            case "Orders":
                return PageGenerator.getUserOrderPage(driver);
            case "Customer info":
                return PageGenerator.getUserCustomerPage(driver);
            default:
                throw new RuntimeException("Invalid page name: " + pageName);

        }
    }

    // Cách khác áp dụng khi có nhiều page cần switch qua lại
    public void openSideBarLinkByPageNames(String pageName) {
        waitForElementClickable(driver, UserSidebarPageUIs.DYNAMIC_LINK_BY_PAGE_NAME,pageName);
        clickToElement(driver, UserSidebarPageUIs.DYNAMIC_LINK_BY_PAGE_NAME,pageName);

    }
}
