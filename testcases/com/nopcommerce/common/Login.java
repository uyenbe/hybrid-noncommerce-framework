package com.nopcommerce.common;

import commons.BaseTest;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.nopcommerce.PageGenerator;
import pageObjects.nopcommerce.user.*;

import java.util.Set;

public class Login extends BaseTest {
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
    public static Set <Cookie> nopcommerceCookie;
    @Parameters ("browser")
    @BeforeTest
    public void BeforeTest(String browserName) {
        driver = getBrowserDriver(browserName);
        userHomePage = PageGenerator.getUserHomePage(driver);

        // Data test
        firstName = "uyen";
        lastName = "nguyen";
        emailAddress = "uyenNT7" + generateRandomNumber() + "@gmail.com";
        password = "12345678";

        userHomePage.sleepInSeconds(2);

        // New User
        userRegisterPage = userHomePage.openRegisterPage(); // page B: RegisterPage

        // Assert 01
        Assert.assertEquals(userRegisterPage.getRegisterTitle(), "Register");

        //Thực hiện action tại Register Page
        userRegisterPage.clickToRadioByID(driver,"gender-male");
        userRegisterPage.enterToTextboxByID(driver, "FirstName", firstName);
        userRegisterPage.enterToTextboxByID(driver, "LastName", lastName);
        userRegisterPage.enterToTextboxByID(driver,"Email", emailAddress);
        userRegisterPage.clickToCheckboxByID(driver, "Newsletter");
        userRegisterPage.enterToTextboxByID(driver, "Password", password);
        userRegisterPage.enterToTextboxByID(driver, "ConfirmPassword", password);
        userRegisterPage.clickToButtonByText(driver,"Register");
        //Asert 02
        Assert.assertEquals(userRegisterPage.getRegisterMessage(),"Your registration completed");

        userRegisterPage.sleepInSeconds(2);
        userHomePage = userRegisterPage.clickLogoutLink();

        // Login
        userLoginPage = userHomePage.openLoginPage();
        userLoginPage.sleepInSeconds(2);

        //userHomePage = userLoginPage.loginToSystem(emailAddress,password);
        // Gọi riêng từng hàm trong loginToSystem
        userLoginPage.enterToTextboxByID(driver, "Email", emailAddress);
        userLoginPage.enterToTextboxByID(driver, "Password", password);
        userRegisterPage.clickToButtonByText(driver,"Log in");
        userHomePage.sleepInSeconds(2);
        userHomePage = PageGenerator.getUserHomePage(driver);

        //Verify My Account Link
        Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());

        // get Cookies
        nopcommerceCookie = userHomePage.getAllCookies(driver);

    }

    @AfterTest
    public void AfterClass() {
        driver.quit();
    }


}
