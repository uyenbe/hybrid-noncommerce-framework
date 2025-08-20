package com.nopcommerce.users;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.nopcommerce.user.UserCustomerInforPO;
import pageObjects.nopcommerce.user.UserHomePO;
import pageObjects.nopcommerce.user.UserLoginPO;
import pageObjects.nopcommerce.user.UserRegisterPO;

import java.time.Duration;

public class Level_03_Page_Object_Pattern_Exercise extends BaseTest {
    //Steps1: Phân tích TCs về mặt nghiệp vụ. tính năng xem đi qua bao nhiêu page/flow như thế nào:
    //1.Open URL >> 2. Open Home Page >> 3. Click Register link
    // >> 4. Open Register Page >> 5. Action to Register Page and Click Register button
    // >> 6. Open Login Page >> 7. Click Log out button >> 8. Open Home Page
    // >> 9. Click Login button >> 10. Verify My Account link isDisplayed
    // >> 10. Click My Account link >> 11. Open Customer Infor Page >> 12. Verify Customer Infor

    //Step 2: Các bước chuyển trang trong flow trên: 2, 4, 6, 8, 10
    //Step 3: Tạo Các Page Object tương ứng với các Page liệt kê ở Step 2
    //Khai báo biến:
    private WebDriver driver;

    //Tạo Page Object
    private UserHomePO homePage;
    private UserLoginPO loginPage;
    private UserRegisterPO registerPage;
    private UserCustomerInforPO customerInforPage;
    private String firstName, lastName, emailAddress, password;

    //Step 4: Tạo Test Class
    //Pre-Condition
    @BeforeClass
    public void BeforeClass() {
        driver = new ChromeDriver();
       //1. Open URL >> chuyển sang HomePage
         driver.get("http://192.168.3.110:5000/");
         driver.manage().window().maximize();
         driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
       // Khởi tạo Home Page
        homePage = new UserHomePO(driver);
        firstName = "uyen";
        lastName = "nguyen";
        emailAddress = "uyenNT7" + generateRandomNumber() + "@gmail.com";
        password = "12345678";

    }

    @Test
    public void User_01_RegisterPage() {
        //Thực hiện các action trên HomePage
        homePage.changeLanguage(driver);
        homePage.openRegisterPage();

        // Từ HomePage chuyển sang RegisterPage
        //Thực hiện action tại Register Page
        registerPage = new UserRegisterPO(driver);
        registerPage.clickGenderRadioButton();
        registerPage.enterFirstNameTextbox("");
        registerPage.enterLastNameTextbox("");
        registerPage.enterEmailTextbox("");
        registerPage.enterPasswordTextbox("");
        registerPage.enterConfirmPasswordTextbox("");
        registerPage.clickRegisterButton();

        //Verify
        Assert.assertEquals(registerPage.getRegisterMessage(),"Your registration completed");

    }

    @Test
    public void User_02_LoginPage() {
        //uyennguyennt7@gmail.com.vn - 12345678
        registerPage.clickLogoutLink();
        //Từ Register page sang Home page
        homePage = new UserHomePO(driver);
        homePage.openLoginPage();

        // Từ Home page chuyển sang Login Page
        // thực hiện các action tại Login page
        loginPage = new UserLoginPO(driver);
        loginPage.enterEmailTextbox("");
        loginPage.enterPasswordTextbox("");
        loginPage.clickLoginButton();

        // Từ Login Page chuyển sang Home page
        homePage = new UserHomePO(driver);

        //Verify My Account Link
        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());

    }

    @Test
    public void User_03_CustomerInforPage() {
        homePage.openCustomerInforPage();

        //Chuyển từ Home Page sang Customer Infor Page
        customerInforPage = new UserCustomerInforPO(driver);
        Assert.assertEquals(customerInforPage.isGenderMaleSelected(),"");
        Assert.assertEquals(customerInforPage.getFirstNameTextboxValue(),"");
        Assert.assertEquals(customerInforPage.getLastNameTextboxValue(),"");
        Assert.assertEquals(customerInforPage.getEmailTextboxValue(),"");

    }



}
