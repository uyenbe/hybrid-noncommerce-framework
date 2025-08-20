package com.nopcommerce.user;

import commons.BaseTest;
import commons.GlobalConstants;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopcommerce.PageGenerator;
import pageObjects.nopcommerce.admin.AdminDashboardPO;
import pageObjects.nopcommerce.admin.AdminLoginPO;
import pageObjects.nopcommerce.user.UserCustomerInforPO;
import pageObjects.nopcommerce.user.UserHomePO;
import pageObjects.nopcommerce.user.UserLoginPO;
import pageObjects.nopcommerce.user.UserRegisterPO;

public class Level_09_Switch_Site_URL extends BaseTest {
    private WebDriver driver;
    private String userURLValue, adminURLValue;
    private UserHomePO userHomePage;
    private UserLoginPO userLoginPage;
    private UserRegisterPO userRegisterPage;
    private UserCustomerInforPO userCustomerInforPage;
    private String firstName, lastName, emailAddress, password;
    private AdminLoginPO adminLoginPage;
    private AdminDashboardPO adminDashboardPage;
    private String adminEmailAddress, adminPassword;

    @Parameters({"browser", "userURL", "adminURL"})
    @BeforeClass
    public void beforeClass(String browserName, String userURL, String adminURL) {
        userURLValue = userURL;
        adminURLValue = adminURL;
        driver = getBrowserDriver(browserName, userURLValue);
        firstName = "nguyen";
        lastName = "uyen";
        emailAddress = "UyenNT7" + generateRandomNumber() + "@gmail.com";
        password = "12345678";
        adminEmailAddress = GlobalConstants.ADMIN_USERNAME;
        adminPassword = GlobalConstants.ADMIN_PASSWORD;
        //Pre-condition
        userHomePage = PageGenerator.getHomePage(driver);
        userRegisterPage = userHomePage.openUserRegisterPage();
        userRegisterPage.clickUserGenderRadioButton();
        userRegisterPage.sleepInSeconds(5);
        userRegisterPage.enterUserFirstNameTextbox(firstName);
        userRegisterPage.enterUserLastNameTextbox(lastName);
        userRegisterPage.enterUserEmailTextbox(emailAddress);
        userRegisterPage.enterUserPasswordTextbox(password);
        userRegisterPage.enterUserConfirmPasswordTextbox(password);
        userRegisterPage.clickUserRegisterButton();

        // Verify
        Assert.assertEquals(userRegisterPage.getRegisterMessage(),"Your registration completed");

        //Logout User Site after Register successfully
        userHomePage = userRegisterPage.clickUserLogoutLink();

    }

    @Test
    public void Role_01_User_Site_To_Admin_Site(){
        //Login User Site
        userLoginPage = userHomePage.openUserLoginPage();
        userLoginPage.enterUserEmailTextbox(emailAddress);
        userLoginPage.enterUserPasswordTextbox(password);
        userHomePage = userLoginPage.clickUserToLoginButton();

        //Verify User Site
        Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());

        // Switch Admin Site
        userHomePage.openPageURL(driver, adminURLValue);

        //The first Login Admin Site
        adminLoginPage = PageGenerator.getAdminLoginPage(driver);
        adminLoginPage.enterAdminEmailAddressTextbox(adminEmailAddress);
        adminLoginPage.enterAdminPasswordTextbox(adminPassword);
        adminDashboardPage = adminLoginPage.clickAdminLoginButton();

        //Verify Admin Site after login successfully
        Assert.assertTrue(adminDashboardPage.isDashboardLinkDisplayed());

    }

    @Test
    public void Role_02_Admin_Site_To_User_Site(){
        // Open User Site
        adminDashboardPage.openPageURL(driver,userURLValue);

        // Switch User Site
        userHomePage = PageGenerator.getHomePage(driver);

        //Verify User Site
        Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());

    }

    @Test
    public void Role_03_User_Site_To_Action(){
        userCustomerInforPage = userHomePage.clickMyAccountLink();

        //Verify
        Assert.assertTrue(userCustomerInforPage.isUserGenderMaleSelected());
        Assert.assertEquals(userCustomerInforPage.getUserFirstNameTextboxValue(),firstName);
        Assert.assertEquals(userCustomerInforPage.getUserLastNameTextboxValue(),lastName);
        Assert.assertEquals(userCustomerInforPage.getUserEmailTextboxValue(),emailAddress);

    }
}
