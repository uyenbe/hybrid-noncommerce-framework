package com.nopcommerce.user;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopcommerce.PageGenerator;
import pageObjects.nopcommerce.user.UserCustomerInforPO;
import pageObjects.nopcommerce.user.UserHomePO;
import pageObjects.nopcommerce.user.UserLoginPO;
import pageObjects.nopcommerce.user.UserRegisterPO;

public class Level_06_Page_Generator_Manager_02_03 extends BaseTest {
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
        // cách 1 và 2 sẽ khơởi tạo trực tiếp >> chưa đáp ứng được nguyên tắc đóng gói
        //homePage = new HomePageObject(driver);
        //Cách 3.
        homePage = PageGenerator.getHomePage(driver);

        // Khởi tạo các biến
        firstName = "uyen";
        lastName = "nguyen";
        emailAddress = "uyenNT7" + generateRandomNumber() + "@gmail.com";
        password = "12345678";

    }
    @Test
    public void User_01_Register() {
        //Ẩn việc khởi tạo các page trong các hàm của PageObject
        registerPage = homePage.openUserRegisterPage();

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
        homePage = registerPage.clickUserLogoutLink();
        loginPage = homePage.openUserLoginPage();
        homePage = loginPage.loginToSystem(emailAddress, password);
        // Verify
        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());

    }

    @Test
    public void User_03_CustomerInfor() {
        customerInforPage = homePage.clickMyAccountLink();
        Assert.assertTrue(customerInforPage.isUserGenderMaleSelected());
        Assert.assertEquals(customerInforPage.getUserFirstNameTextboxValue(),firstName);
        Assert.assertEquals(customerInforPage.getUserLastNameTextboxValue(),lastName);
        Assert.assertEquals(customerInforPage.getUserEmailTextboxValue(),emailAddress);
    }

    @AfterClass
    public void AfterClass() {

    }
}
