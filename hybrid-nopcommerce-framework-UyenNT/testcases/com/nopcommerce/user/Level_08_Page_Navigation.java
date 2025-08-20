package com.nopcommerce.user;

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
        customerInforPage = homePage.clickMyAccountLink();
        orderPage = customerInforPage.openUserOrderPO();
        addressPage = orderPage.openUserAddressPO();
        rewardPointPage = addressPage.openUserRewardPointPO();
        changePasswordPage = registerPage.openUserChangePasswordPO();


    }

}
