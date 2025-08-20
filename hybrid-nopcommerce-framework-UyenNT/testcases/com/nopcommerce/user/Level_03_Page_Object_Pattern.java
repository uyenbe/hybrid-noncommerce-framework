package com.nopcommerce.user;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopcommerce.user.UserCustomerInforPO;
import pageObjects.nopcommerce.user.UserHomePO;
import pageObjects.nopcommerce.user.UserLoginPO;
import pageObjects.nopcommerce.user.UserRegisterPO;

public class Level_03_Page_Object_Pattern extends BaseTest {
    //Declare Variable
    private WebDriver driver;
    private UserHomePO homePage;
    private UserLoginPO loginPage;
    private UserCustomerInforPO customerInforPage;
    private UserRegisterPO registerPage;

    //Khai báo các tham số truyền vào
    private String firstName, lastName, emailAddress, password;
    @Parameters("browser")
    //pre-condition
    @BeforeClass
    public void BeforeClass(String browserName) {
        // Khởi tạo driver
        //gán driver = hàm getBrowserDriver
        // >> kiểu trả về của hàm getBrowserDriver sẽ phải là: return driver
        // >> kiểu dữ liệu của hàm getBrowserDriver là: WebDriver
        driver = getBrowserDriver(browserName);

        //Khởi tạo các homePage trước
        homePage = new UserHomePO(driver);

        // Khởi tạo các biến
        firstName = "uyen";
        lastName = "nguyen";
        emailAddress = "uyenNT7" + generateRandomNumber() + "@gmail.com";
        password = "12345678";

    }
    @Test
    public void User_01_Register() {
        homePage.openUserRegisterPage();

        registerPage = new UserRegisterPO(driver);
        registerPage.clickUserGenderRadioButton();
        registerPage.enterUserFirstNameTextbox(firstName);
        registerPage.enterUserLastNameTextbox(lastName);
        registerPage.enterUserEmailTextbox(emailAddress);
        registerPage.enterUserPasswordTextbox(password);
        registerPage.enterUserConfirmPasswordTextbox(password);
        registerPage.clickUserRegisterButton();

        //Verify
        Assert.assertEquals(registerPage.getRegisterMessage(), "Your registration completed");
    }

    @Test
    // email: nguyenuyen@gmail.com - 12345678
    public void User_02_Login() {
        registerPage.clickUserLogoutLink();

        homePage = new UserHomePO(driver);
        homePage.openUserLoginPage();

        loginPage = new UserLoginPO(driver);
        loginPage.enterUserEmailTextbox(emailAddress);
        loginPage.enterUserPasswordTextbox(password);
        loginPage.clickUserToLoginButton();

        homePage = new UserHomePO(driver);

        // Verify
        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());

    }

    @Test
    public void User_03_CustomerInfor() {
        homePage.clickMyAccountLink();
        customerInforPage = new UserCustomerInforPO(driver);
        Assert.assertTrue(customerInforPage.isUserGenderMaleSelected());
        Assert.assertEquals(customerInforPage.getUserFirstNameTextboxValue(),firstName);
        Assert.assertEquals(customerInforPage.getUserLastNameTextboxValue(),lastName);
        Assert.assertEquals(customerInforPage.getUserEmailTextboxValue(),emailAddress);
    }

    @AfterClass
    public void AfterClass() {

    }
}
