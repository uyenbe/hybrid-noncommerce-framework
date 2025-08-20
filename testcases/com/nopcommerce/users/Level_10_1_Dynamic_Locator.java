package com.nopcommerce.users;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopcommerce.PageGenerator;
import pageObjects.nopcommerce.user.*;

public class Level_10_1_Dynamic_Locator extends BaseTest {
    private WebDriver driver;

    //Tạo Page Object
    private UserHomePO userHomePage;
    private UserLoginPO userLoginPage;
    private UserRegisterPO userRegisterPage;
    private UserCustomerInforPO userCustomerInforPage;
    private UserAdressPO userAdressPage;
    private UserOrderPO userOrderPage;
    private UserRewardPointPO userRewardPointPage;
    private String firstName, lastName, emailAddress, password;
    @Parameters ("browser")
    @BeforeClass
    public void BeforeClass(String browserName) {
        driver = getBrowserDriver(browserName);
       // Khởi tạo Home Page
        // Tại PageGenerator dùng hàm static để các hàm khác có thể truy cập trực tiếp thông qua class cha
        userHomePage = PageGenerator.getUserHomePage(driver);
        firstName = "uyen";
        lastName = "nguyen";
        emailAddress = "uyenNT7" + generateRandomNumber() + "@gmail.com";
        password = "12345678";

    }

    @Test
    public void User_01_RegisterPage() {
        //Thực hiện các action trên HomePage
        userHomePage.sleepInSeconds(2);

        userRegisterPage = userHomePage.openRegisterPage(); // page B: RegisterPage

        //Thực hiện action tại Register Page
        userRegisterPage.clickGenderRadioButton();
        userRegisterPage.enterFirstNameTextbox(firstName);
        userRegisterPage.enterLastNameTextbox(lastName);
        userRegisterPage.enterEmailTextbox(emailAddress);
        userRegisterPage.enterPasswordTextbox(password);
        userRegisterPage.enterConfirmPasswordTextbox(password);
        userRegisterPage.clickRegisterButton();

        //Verify
        Assert.assertEquals(userRegisterPage.getRegisterMessage(),"Your registration completed");

    }

    @Test
    public void User_02_LoginPage() {
        //uyennguyennt7@gmail.com.vn - 12345678
        userRegisterPage.sleepInSeconds(2);

        userHomePage = userRegisterPage.clickLogoutLink();

        userLoginPage = userHomePage.openLoginPage();
        userLoginPage.sleepInSeconds(2);

        userHomePage = userLoginPage.loginToSystem(emailAddress,password);
        userHomePage.sleepInSeconds(2);

        //Verify My Account Link
        Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());

    }

    @Test
    public void User_03_CustomerInforPage() {

        userCustomerInforPage = userHomePage.openCustomerInforPage();
        userCustomerInforPage.sleepInSeconds(2);

        Assert.assertTrue(userCustomerInforPage.isGenderMaleSelected());
        Assert.assertEquals(userCustomerInforPage.getFirstNameTextboxValue(),firstName);
        Assert.assertEquals(userCustomerInforPage.getLastNameTextboxValue(),lastName);
        Assert.assertEquals(userCustomerInforPage.getEmailTextboxValue(),emailAddress);

    }

    @Test
    public void User_04_Dynamic_Page() {
        //Đây là cách khởi tạo page trên chính hàm được ép kiểu trên TCs
        //Chuyển từ Customer Infor sang Address
        userAdressPage = (UserAdressPO) userCustomerInforPage.openSidebarLinkByPageName("Addresses");
        userAdressPage.sleepInSeconds(2);

        // Chuyển từ Address page >> Order page
        userOrderPage = (UserOrderPO) userAdressPage.openSidebarLinkByPageName("Orders");
        userOrderPage.sleepInSeconds(2);

        // Chuyển tiếp sang Rewards Point
        userRewardPointPage = (UserRewardPointPO) userOrderPage.openSidebarLinkByPageName("Reward points");

        // Chuyển sang Customer Infor
        userCustomerInforPage = (UserCustomerInforPO) userRewardPointPage.openSidebarLinkByPageName("Customer info");



    }

    @Test
    public void User_05_Dynamic_Page() {
        //output của TC_04 là input của TC_05
        // Cách này sẽ không khởi tạo page trong hàm
        // Trong TH có nhiều page thì sẽ dùng cách khởi tạo page ngay trên TCs
        userCustomerInforPage.openSideBarLinkByPageNames("Addresses");
        userAdressPage = PageGenerator.getUserAdressPage(driver);

        userAdressPage.openSideBarLinkByPageNames("Orders");
        userOrderPage = PageGenerator.getUserOrderPage(driver);

        userOrderPage.openSideBarLinkByPageNames("Reward points");
        userRewardPointPage = PageGenerator.getUserRewardPointPage(driver);

        userRewardPointPage.openSideBarLinkByPageNames("Customer info");
        userCustomerInforPage = PageGenerator.getUserCustomerPage(driver);

    }



}
