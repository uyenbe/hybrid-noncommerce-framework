package com.nopcommerce.users;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopcommerce.PageGenerator;
import pageObjects.nopcommerce.user.*;

public class Level_08_Page_Navigation extends BaseTest {
    private WebDriver driver;

    //Tạo Page Object
    private UserHomePO homePage;
    private UserLoginPO loginPage;
    private UserRegisterPO registerPage;
    private UserCustomerInforPO customerInforPage;
    private UserAdressPO adressPage;
    private UserOrderPO orderPage;
    private UserRewardPointPO rewardPointPage;
    private String firstName, lastName, emailAddress, password;
    @Parameters ("browser")
    @BeforeClass
    public void BeforeClass(String browserName) {
        driver = getBrowserDriver(browserName);
       // Khởi tạo Home Page
        // Tại PageGenerator dùng hàm static để các hàm khác có thể truy cập trực tiếp thông qua class cha
        homePage = PageGenerator.getUserHomePage(driver);
        firstName = "uyen";
        lastName = "nguyen";
        emailAddress = "uyenNT7" + generateRandomNumber() + "@gmail.com";
        password = "12345678";

    }

    @Test
    public void User_01_RegisterPage() {
        //Thực hiện các action trên HomePage
        homePage.sleepInSeconds(2);

        registerPage = homePage.openRegisterPage(); // page B: RegisterPage

        //Thực hiện action tại Register Page
        registerPage.clickGenderRadioButton();
        registerPage.enterFirstNameTextbox(firstName);
        registerPage.enterLastNameTextbox(lastName);
        registerPage.enterEmailTextbox(emailAddress);
        registerPage.enterPasswordTextbox(password);
        registerPage.enterConfirmPasswordTextbox(password);
        registerPage.clickRegisterButton();

        //Verify
        Assert.assertEquals(registerPage.getRegisterMessage(),"Your registration completed");

    }

    @Test
    public void User_02_LoginPage() {
        //uyennguyennt7@gmail.com.vn - 12345678
        registerPage.sleepInSeconds(2);

        homePage = registerPage.clickLogoutLink();

        loginPage = homePage.openLoginPage();
        loginPage.sleepInSeconds(2);

        homePage = loginPage.loginToSystem(emailAddress,password);
        homePage.sleepInSeconds(2);

        //Verify My Account Link
        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());

    }

    @Test
    public void User_03_CustomerInforPage() {

        customerInforPage = homePage.openCustomerInforPage();
        customerInforPage.sleepInSeconds(2);

        Assert.assertTrue(customerInforPage.isGenderMaleSelected());
        Assert.assertEquals(customerInforPage.getFirstNameTextboxValue(),firstName);
        Assert.assertEquals(customerInforPage.getLastNameTextboxValue(),lastName);
        Assert.assertEquals(customerInforPage.getEmailTextboxValue(),emailAddress);

    }

    @Test
    public void User_04_Switch_Page() {
        //Dùng Page Navigation để nhóm các hàm của các page thuộc sidebar menu vào 1 class
        //Sau đó gọi các hàm từ class chung đó ra
        adressPage = customerInforPage.openUserAddressPage();
        //... add actions

        //Address >> Reward Point
        rewardPointPage = adressPage.openUserRewardPointPage();

        //Reward Point >> Order
        orderPage = rewardPointPage.openUserOrderPage();

        // Order >> Address
        adressPage = orderPage.openUserAddressPage();

        // Address >> Customer Infor
        customerInforPage = adressPage.openUserCustomerInforPage();


    }



}
