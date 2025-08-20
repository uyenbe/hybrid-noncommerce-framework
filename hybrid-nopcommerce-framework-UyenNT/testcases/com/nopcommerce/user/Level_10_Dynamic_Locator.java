package com.nopcommerce.user;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopcommerce.PageGenerator;
import pageObjects.nopcommerce.user.*;

public class Level_10_Dynamic_Locator extends BaseTest {
    private WebDriver driver;
    private UserHomePO homePage;
    private UserLoginPO loginPage;
    private UserRegisterPO registerPage;
    private UserCustomerInforPO customerInforPage;
    private UserAddressPO addressPage;
    private UserOrderPO orderPage;
    private UserChangePasswordPO changePasswordPage;
    private UserRewardPointPO rewardPointPage;
    private String firstName, lastName, emailAddress, password;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName){
        driver = getBrowserDriver(browserName);
        homePage = PageGenerator.getHomePage(driver);
        firstName = "nguyen";
        lastName = "uyen";
        emailAddress = "UyenNT7" + generateRandomNumber() + "@gmail.com";
        password = "12345678";

    }
    @Test
    public void User_01_Register_Page(){
        registerPage = homePage.openUserRegisterPage();
        registerPage.clickUserGenderRadioButton();
        registerPage.enterUserFirstNameTextbox(firstName);
        registerPage.enterUserLastNameTextbox(lastName);
        registerPage.enterUserEmailTextbox(emailAddress);
        registerPage.enterUserPasswordTextbox(password);
        registerPage.enterUserConfirmPasswordTextbox(password);
        registerPage.clickUserRegisterButton();

        Assert.assertEquals(registerPage.getRegisterMessage(),"Your registration completed");

        homePage = registerPage.clickUserLogoutLink();

    }

    @Test
    public void User_02_Login_Page(){
        loginPage = homePage.openUserLoginPage();
        homePage = loginPage.loginToSystem(emailAddress, password);
        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());

    }

    @Test
    public void User_03_Cusstomer_Page(){
        customerInforPage = homePage.clickMyAccountLink();
        Assert.assertTrue(customerInforPage.isUserGenderMaleSelected());
        Assert.assertEquals(customerInforPage.getUserFirstNameTextboxValue(), firstName);
        Assert.assertEquals(customerInforPage.getUserLastNameTextboxValue(),lastName);
        Assert.assertEquals(customerInforPage.getUserEmailTextboxValue(), emailAddress);

    }

    @Test
    public void User_04_Page_Navigation(){
      //  customerInforPage = homePage.clickMyAccountLink();
        //Customer page sang order page
        //khi khởi tạo addressPage:
        // truyền vào tên page =  Addresses thì sẽ switch vào case gọi vào hàm getAddressPage của hàm openSidebar
        // Trong hàm getAddressPage trả về kiểu UserAddressPO
        // Vì hàm openSidebar đang được ép kiểu từ kiểu con sang chiều cha: hàm đang trả về kiểu dữ liệu chung nhất là UserSideBarPO
        // Nên tại đây phải ép lại kiểu từ cha > con để trả về đúng kiểu là UserAddressPO
        // Đây là cách tạo page ngay trên chính hàm openSidebar
        addressPage = (UserAddressPO) customerInforPage.openSideBarLinkByPageName("Addresses");

        // Address >> reward point
        rewardPointPage = (UserRewardPointPO) addressPage.openSideBarLinkByPageName("Reward points");

        //reward point >> orders
        orderPage = (UserOrderPO) rewardPointPage.openSideBarLinkByPageName("Orders");

        // order page >> Customer infor
        customerInforPage = (UserCustomerInforPO) orderPage.openSideBarLinkByPageName("Customer info");


    }
    @Test
    public void User_05_(){
        // đầu vào của TC_05 là kết thúc của TC_04
        // đây là cách tạo page trên chính TC.
        // Giống như khởi tạo homePage
        // sau khi click vào linkPage thì sẽ khởi tạo page tương ứng với link được click
        customerInforPage.openSidebarLinkByPageNames("Change password");
        changePasswordPage = PageGenerator.getChangePasswordPage(driver);

        changePasswordPage.openSidebarLinkByPageNames("Reward points");
        rewardPointPage = PageGenerator.getRewardPointPage(driver);
    }

}
