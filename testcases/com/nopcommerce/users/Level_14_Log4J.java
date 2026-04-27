package com.nopcommerce.users;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopcommerce.PageGenerator;
import pageObjects.nopcommerce.user.*;

public class Level_14_Log4J extends BaseTest {
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
        // Cách viết log trên TC:
//        log.info("User_01_RegisterPage - STEP 01: Open Register Page");
        //Thực hiện các action trên HomePage
        userHomePage.sleepInSeconds(2);

        userRegisterPage = userHomePage.openRegisterPage(); // page B: RegisterPage

        //Thực hiện action tại Register Page
//        log.info("User_01_RegisterPage - STEP 02: Click to Male radio button");
        userRegisterPage.clickGenderRadioButton();

//        log.info("User_01_RegisterPage - STEP 03: Enter to FirstName textbox with value " + firstName);
        userRegisterPage.enterFirstNameTextbox(firstName);

//        log.info("User_01_RegisterPage - STEP 04: Enter to LastName textbox with value " + lastName);
        userRegisterPage.enterLastNameTextbox(lastName);

//        log.info("User_01_RegisterPage - STEP 05: Enter to Email textbox with value " + emailAddress);
        userRegisterPage.enterEmailTextbox(emailAddress);

//        log.info("User_01_RegisterPage - STEP 06: Enter to password textbox with value " + password);
        userRegisterPage.enterPasswordTextbox(password);

//        log.info("User_01_RegisterPage - STEP 07: Enter to password textbox with value " + password);
        userRegisterPage.enterConfirmPasswordTextbox(password);

//        log.info("User_01_RegisterPage - STEP 08: Click to Register button");
        userRegisterPage.clickRegisterButton();

        //Verify
//        log.info("User_01_RegisterPage - STEP 09: Verify Success message");
        //Assert.assertEquals(userRegisterPage.getRegisterMessage(),"Your registration completed...");
        Assert.assertEquals(userRegisterPage.getRegisterMessage(),"Your registration completed...");

//        log.info("User_01_RegisterPage - STEP 10: Click to Logout link");
        userHomePage = userRegisterPage.clickLogoutLink();


    }

    @Test
    public void User_02_LoginPage() {
        //uyennguyennt7@gmail.com.vn - 12345678
        userRegisterPage.sleepInSeconds(2);

//        userHomePage = userRegisterPage.clickLogoutLink();

        userLoginPage = userHomePage.openLoginPage();
        userLoginPage.sleepInSeconds(2);

        userHomePage = userLoginPage.loginToSystem(emailAddress,password);
        userHomePage.sleepInSeconds(2);

        //Verify My Account Link
//        Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());
        Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());

    }

    @Test
    public void User_03_CustomerInforPage() {

        userCustomerInforPage = userHomePage.openCustomerInforPage();
        userCustomerInforPage.sleepInSeconds(2);

//        Assert.assertTrue(userCustomerInforPage.isGenderMaleSelected());
//        Assert.assertEquals(userCustomerInforPage.getFirstNameTextboxValue(),firstName);
//        Assert.assertEquals(userCustomerInforPage.getLastNameTextboxValue(),lastName);
//        Assert.assertEquals(userCustomerInforPage.getEmailTextboxValue(),emailAddress);

        Assert.assertTrue(userCustomerInforPage.isGenderMaleSelected());
        Assert.assertEquals(userCustomerInforPage.getFirstNameTextboxValue(),firstName);
        Assert.assertEquals(userCustomerInforPage.getLastNameTextboxValue(),lastName);
        Assert.assertEquals(userCustomerInforPage.getEmailTextboxValue(),emailAddress);

    }

//    @Test
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

//    @Test
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
