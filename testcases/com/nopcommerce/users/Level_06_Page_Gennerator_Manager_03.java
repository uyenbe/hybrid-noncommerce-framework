package com.nopcommerce.users;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopcommerce.PageGenerator;
import pageObjects.nopcommerce.user.UserCustomerInforPO;
import pageObjects.nopcommerce.user.UserHomePO;
import pageObjects.nopcommerce.user.UserLoginPO;
import pageObjects.nopcommerce.user.UserRegisterPO;

public class Level_06_Page_Gennerator_Manager_03 extends BaseTest {
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
        // Tại PageGenerator dùng hàm static để các hàm khác có thể truy cập trực tiếp thông qua class cha
        homePage = PageGenerator.getUserHomePage(driver);
        firstName = "uyen";
        lastName = "nguyen";
        emailAddress = "uyenNT7" + generateRandomNumber() + "@gmail.com";
        password = "12345678";

    }

    @Test
    public void User_01_RegisterPage() {
        //Thực hiện các action trên HomePage
        homePage.sleepInSeconds(2);
       // homePage.clickToRegisterLink(); //page A: HomePage
        //Cách 2:
        // Chuyển việc khởi tạo các page từ ngoài testClass vào tùng pageObject
        // Không vi phạm tính đóng gói trong OOP - che giấu đi việc khởi tạo 1 đối tượng
        //Nhưng vẫn Lặp lại việc khởi tạo page ở các step sau
        // - Vi phạm nguyên tắc DRY - Ko lặp lại tính năng/dòng code trong 1 class haowjc framework
        // Có sự kết nối giữa 2 page với nhau
        // - khi TCs có sự kết nối giữa 2 page với nhau thì code sẽ ngắn gọn, dễ đọc, dễ hiểu hơn

        // Từ HomePage chuyển sang RegisterPage: từ page A sang page B
        // registerPage = new RegisterPageObject(driver);
        // >> được chuyển sang khởi tạo tại hàm clickToRegisterLink
        registerPage = homePage.openRegisterPage(); // page B: RegisterPage

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
        //registerPage.clickLogoutLink();

        //Từ Register page sang Home page
       // homePage = new HomePageObject(driver); >> được khởi tạo tại hàm register.clickToLogoutLink()
        homePage = registerPage.clickLogoutLink();
        //homePage.clickToLoginButton();

        // Từ Home page chuyển sang Login Page
        // thực hiện các action tại Login page
        // loginPage = new LoginPageObject(driver); >> được khởi tạo tại hàm homePage.clickToLoginButton()

        loginPage = homePage.openLoginPage();
        loginPage.changeLanguage(driver);
        loginPage.sleepInSeconds(2);

//        loginPage.enterEmailTextbox(emailAddress);
//        loginPage.enterPasswordTextbox(password);
//        loginPage.clickLoginButton();
        // 3 dòng trên thay thế bằng hàm dưới để tối ưu code
       // loginPage.loginToSystem(emailAddress,password);

        // Từ Login Page chuyển sang Home page
        // homePage = new HomePageObject(driver);
        // >> được khởi tạo tại hàm loginPage.loginToSystem(emailAddress,password);
        //gán lại giá trị cho homePage
        homePage = loginPage.loginToSystem(emailAddress,password);
        homePage.changeLanguage(driver);
        homePage.sleepInSeconds(2);

        //Verify My Account Link
        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());

    }

    @Test
    public void User_03_CustomerInforPage() {
        //homePage.clickMyAccountLink();

        //Chuyển từ Home Page sang Customer Infor Page
        //customerInforPage = new CustomerInforPageObject(driver);

        customerInforPage = homePage.openCustomerInforPage();
        customerInforPage.changeLanguage(driver);
        customerInforPage.sleepInSeconds(2);

        Assert.assertTrue(customerInforPage.isGenderMaleSelected());
        Assert.assertEquals(customerInforPage.getFirstNameTextboxValue(),firstName);
        Assert.assertEquals(customerInforPage.getLastNameTextboxValue(),lastName);
        Assert.assertEquals(customerInforPage.getEmailTextboxValue(),emailAddress);

    }



}
