package com.nopcommerce.users;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopcommerce.PageGenerator;
import pageObjects.nopcommerce.user.*;

public class Level_13_Assert extends BaseTest {
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

        // Assert 01
        Assert.assertEquals(userRegisterPage.getRegisterTitle(), "REGISTER");


        //Thực hiện action tại Register Page
        userRegisterPage.clickGenderRadioButton();
        userRegisterPage.enterFirstNameTextbox(firstName);
        userRegisterPage.enterLastNameTextbox(lastName);
        userRegisterPage.enterEmailTextbox(emailAddress);
        userRegisterPage.enterPasswordTextbox(password);
        userRegisterPage.enterConfirmPasswordTextbox(password);
        userRegisterPage.clickRegisterButton();

        //Asert 02
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
        assertTrue(userHomePage.isMyAccountLinkDisplayed());

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


}
