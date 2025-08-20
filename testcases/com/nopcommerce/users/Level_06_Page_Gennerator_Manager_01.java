package com.nopcommerce.users;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopcommerce.user.UserCustomerInforPO;
import pageObjects.nopcommerce.user.UserHomePO;
import pageObjects.nopcommerce.user.UserLoginPO;
import pageObjects.nopcommerce.user.UserRegisterPO;

public class Level_06_Page_Gennerator_Manager_01 extends BaseTest {
    private WebDriver driver;

    //Tạo Page Object
    private UserHomePO homePage;
    private UserLoginPO loginPage;
    private UserRegisterPO registerPage;
    private UserCustomerInforPO customerInforPage;
    private String firstName, lastName, emailAddress, password;
    @Parameters ("browser")
    @BeforeClass
    public void BeforeClass(String browserName) {
        driver = getBrowserDriver(browserName);
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
        homePage.sleepInSeconds(2);
        homePage.openRegisterPage(); //page A: HomePage
        //Cách 1:
        // Thể hiện trực tiếp trên testClass >> Chạy TCs bình thường
        // Nguyên tắc thiết kế phần mềm/framework: Vi phạm tính đóng gói trong OOP - che giấu đi việc khởi tạo 1 đối tượng
        //Lặp lại việc khởi tạo page ở các step sau - Vi phạm nguyên tắc DRY - Ko lặp lại tính năng/dòng code trong 1 class haowjc framework
        // Không có sự kết nối giữa 2 page với nhau - khi TCs có sự kết nối giữa 2 page với nhau thì code sẽ ngắn gọn, dễ đọc, dễ hiểu hơn

        // Từ HomePage chuyển sang RegisterPage: từ page A sang page B
        registerPage = new UserRegisterPO(driver); // page B: RegisterPage

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
        registerPage.changeLanguage(driver);
        registerPage.sleepInSeconds(2);
        registerPage.clickLogoutLink();

        //Từ Register page sang Home page
        homePage = new UserHomePO(driver);
        homePage.openLoginPage();

        // Từ Home page chuyển sang Login Page
        // thực hiện các action tại Login page
        loginPage = new UserLoginPO(driver);
        loginPage.changeLanguage(driver);
        loginPage.sleepInSeconds(2);

        loginPage.enterEmailTextbox(emailAddress);
        loginPage.enterPasswordTextbox(password);
        loginPage.clickLoginButton();

        // Từ Login Page chuyển sang Home page
        homePage = new UserHomePO(driver);
        homePage.changeLanguage(driver);
        homePage.sleepInSeconds(2);

        //Verify My Account Link
        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());

    }

    @Test
    public void User_03_CustomerInforPage() {
        homePage.openCustomerInforPage();

        //Chuyển từ Home Page sang Customer Infor Page
        customerInforPage = new UserCustomerInforPO(driver);
        customerInforPage.changeLanguage(driver);
        customerInforPage.sleepInSeconds(2);

        Assert.assertTrue(customerInforPage.isGenderMaleSelected());
        Assert.assertEquals(customerInforPage.getFirstNameTextboxValue(),firstName);
        Assert.assertEquals(customerInforPage.getLastNameTextboxValue(),lastName);
        Assert.assertEquals(customerInforPage.getEmailTextboxValue(),emailAddress);

    }



}
