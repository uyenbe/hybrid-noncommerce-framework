package com.nopcommerce.user;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopcommerce.PageGenerator;
import pageObjects.nopcommerce.user.*;

public class Level_07_Switch_Page_Object_Exercise extends BaseTest {
    private static final Logger log = LoggerFactory.getLogger(Level_07_Switch_Page_Object_Exercise.class);
    // Khai báo driver
    private WebDriver driver;

    //Khai báo các page
    private UserHomePO homePage;
    private UserRegisterPO registerPage;
    private UserLoginPO loginPage;
    private UserCustomerInforPO customerInforPage;
    private UserAddressPO addressPage;
    private UserOrderPO orderPage;
    private UserChangePasswordPO changePasswordPage;
    private UserRewardPointPO rewardPointPage;

    //Khai báo các biến
    private String firstName, lastName, emailAdress, gender, password;

    @Parameters ("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        // Khởi tạo driver
        driver = getBrowserDriver(browserName);

        // Khởi tạo homepage
        homePage = PageGenerator.getHomePage(driver);

        //Khởi tạo giá trị các biến
        firstName = "nguyen";
        lastName = "uyen";
        emailAdress = "UyenNT7"+ generateRandomNumber() + "@gmail.com";
        gender = "male";
        password = "12345678";

    }
    @Test
    public void User_01_RegisterPage(){
        //Ẩn việc khởi tạo RegisterPage trong hàm openRegisterPage của class HomePageObject
        registerPage = homePage.openUserRegisterPage();
        registerPage.clickUserGenderRadioButton();
        registerPage.enterUserFirstNameTextbox(firstName);
        registerPage.enterUserLastNameTextbox(lastName);
        registerPage.enterUserEmailTextbox(emailAdress);
        registerPage.enterUserPasswordTextbox(password);
        registerPage.enterUserConfirmPasswordTextbox(password);
        registerPage.clickUserRegisterButton();

        // Verify
        Assert.assertEquals(registerPage.getRegisterMessage(),"Your registration completed");

    }

    @Test
    public void User_02_LoginPage(){
        homePage = registerPage.clickUserLogoutLink();
        loginPage = homePage.openUserLoginPage();
        loginPage.enterUserEmailTextbox(emailAdress);
        loginPage.enterUserPasswordTextbox(password);
        loginPage.clickUserToLoginButton();
        homePage = loginPage.openHomePage();
        //homePage = loginPage.loginToSystem(emailAdress, password);

        // Verify
        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
    }

    @Test
    public void User_03_CustomerInforPage(){
        customerInforPage = homePage.clickMyAccountLink();
        Assert.assertTrue(customerInforPage.isUserGenderMaleSelected());
        Assert.assertEquals(customerInforPage.getUserFirstNameTextboxValue(), firstName);
        Assert.assertEquals(customerInforPage.getUserLastNameTextboxValue(),lastName);
        Assert.assertEquals(customerInforPage.getUserEmailTextboxValue(), emailAdress);

    }

    @Test
    public void User_04_SwitchPage(){
        addressPage = customerInforPage.openAddressPage(driver);
        orderPage = addressPage.openOrderPage(driver);
        changePasswordPage = orderPage.openChangePasswordPage(driver);
        customerInforPage = changePasswordPage.openCustomerInforPage(driver);
        rewardPointPage = changePasswordPage.openRewardPointPage(driver);

    }
}
