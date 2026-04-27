package com.nopcommerce.users;

import com.nopcommerce.common.Login;
import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.nopcommerce.PageGenerator;
import pageObjects.nopcommerce.user.*;

public class Level_20_Share_State extends BaseTest {
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
        // New User
        userHomePage = PageGenerator.getUserHomePage(driver);
        // Data test
        firstName = "uyen";
        lastName = "nguyen";
        emailAddress = "uyenNT7" + generateRandomNumber() + "@gmail.com";
        password = "12345678";

        // Pre-condition: Login by Cookies
        userHomePage.setCookies(driver, Login.nopcommerceCookie);
        userHomePage.refreshPage(driver);

        //Verify My Account Link
        Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());

    }

    @Test
    public void User_01_My_Account() {
        userCustomerInforPage = userHomePage.openCustomerInforPage();
        userCustomerInforPage.sleepInSeconds(2);

        Assert.assertTrue(userCustomerInforPage.isRadioSelected(driver, "gender-male"));

//        verifyEquals(userCustomerInforPage.getTextboxByID(driver, "FirstName"), firstName);
//        verifyEquals(userCustomerInforPage.getTextboxByID(driver, "LastName"),lastName);
//        verifyEquals(userCustomerInforPage.getTextboxByID(driver, "Email"),emailAddress);
        Assert.assertTrue(userCustomerInforPage.isCheckboxSelected(driver, "Newsletter"));

    }

    @Test
    public void User_02_Payment() {

    }

    @Test
    public void User_03_Orders() {


    }

    @AfterClass
    public void AfterClass() {
        driver.quit();
    }


}
