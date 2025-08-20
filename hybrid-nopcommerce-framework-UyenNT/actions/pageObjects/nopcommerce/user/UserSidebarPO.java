package pageObjects.nopcommerce.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.nopcommerce.PageGenerator;
import pageUIs.nopcommerce.user.UserSidebarPageUIs;

public class UserSidebarPO extends BasePage {
    private WebDriver driver;
    public UserSidebarPO(WebDriver driver){
        this.driver = driver;
    }

    public UserAddressPO openUserAddressPO(){
        waitForElementClickable(driver, UserSidebarPageUIs.ADDRESS_LINK);
        clickToElement(driver, UserSidebarPageUIs.ADDRESS_LINK);
        return new UserAddressPO(driver);
    }
    public UserChangePasswordPO openUserChangePasswordPO(){
        waitForElementClickable(driver, UserSidebarPageUIs.CHANGE_PASSWORD_LINK);
        clickToElement(driver, UserSidebarPageUIs.CHANGE_PASSWORD_LINK);
        return new UserChangePasswordPO(driver);
    }
    public UserRewardPointPO openUserRewardPointPO(){
        waitForElementClickable(driver, UserSidebarPageUIs.REWARD_POINT_LINK);
        clickToElement(driver, UserSidebarPageUIs.REWARD_POINT_LINK);
        return new UserRewardPointPO(driver);
    }

    public UserOrderPO openUserOrderPO(){
        waitForElementClickable(driver, UserSidebarPageUIs.ORDER_LINK);
        clickToElement(driver, UserSidebarPageUIs.ORDER_LINK);
        return new UserOrderPO(driver);
    }

    public UserSidebarPO openSideBarLinkByPageName(String pageName){
        waitForElementClickable(driver, UserSidebarPageUIs.DYNAMIC_LINK_BY_PAGE_NAME, pageName);
        clickToElement(driver, UserSidebarPageUIs.DYNAMIC_LINK_BY_PAGE_NAME, pageName);

        //Khởi tạo ra đúng tên pageName cần open
        // để sử dụng tính năng ép kiểu của hàm ở tầng TCs
        //Trong hàm switch dưới đang đang trả về 4 page >> sẽ có 4 kiểu dữ liệu trả về
        // mà hàm openSidebar chỉ cho phép trả về 1 kiểu dữ liệu
        // >> cần tìm kiểu dữ liệu chung cho 4 kiểu trả về của hàm switch
        // >> ở đây kiểu chung nhất là UserSidebarPO vì cả 4 page trả về đều kế thừa UserSidebarPO
        // >> chính là chuyển từ kiểu con sang kiểu cha >> hay còn gọi là ép kiểu

        switch (pageName){
            case "Addresses":
                return PageGenerator.getAdressPage(driver);
            case "Reward points":
                return PageGenerator.getRewardPointPage(driver);
            case "Orders":
                return PageGenerator.getOrderPage(driver);
            case "Customer info":
                return PageGenerator.getCustomerInforPage(driver);
            default:
                throw new RuntimeException("Invalid page name: " + pageName);
        }
    }

    // TH Dynamic page có quá nhiều page thì dùng hàm trên chưa được tối ưu vì switch-case sẽ bị quá dài
    // Hàm trên chỉ phù hợp cho số lượng page ít
    // Cần tạo 1 hàm khác mà ko cần switch case
    public void openSidebarLinkByPageNames(String pageName){
        waitForElementClickable(driver, UserSidebarPageUIs.REWARD_POINT_LINK, pageName);
        clickToElement(driver, UserSidebarPageUIs.REWARD_POINT_LINK, pageName);

    }
}
