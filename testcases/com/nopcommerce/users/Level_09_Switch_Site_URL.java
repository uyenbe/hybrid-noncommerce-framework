package com.nopcommerce.users;

import commons.BaseTest;
import commons.GlobalConstants;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
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

    //Tạo Page Object
    private UserHomePO userHomePage;
    private UserLoginPO userLoginPage;
    private UserRegisterPO userRegisterPage;
    private UserCustomerInforPO userCustomerInforPage;
    private AdminLoginPO adminLoginPage;
    private AdminDashboardPO adminDashboardPage;
    private String userUrlValue, adminUrlValue;
    private String firstName, lastName, emailAddress, password;
    private String adminEmailAddress, adminPassword;
    @Parameters ({"browser", "userUrl", "adminUrl"})
    @BeforeClass
    public void BeforeClass(String browserName, String userUrl, String adminUrl) {
        adminUrlValue = adminUrl;
        userUrlValue = userUrl;
        driver = getBrowserDriver(browserName, userUrlValue);
       // Khởi tạo Home Page
        // Tại PageGenerator dùng hàm static để các hàm khác có thể truy cập trực tiếp thông qua class cha
        userHomePage = PageGenerator.getUserHomePage(driver);
        firstName = "uyen";
        lastName = "nguyen";
        emailAddress = "uyenNT7" + generateRandomNumber() + "@gmail.com";
        password = "12345678";
        adminEmailAddress = GlobalConstants.ADMIN_USERNAME;
        adminPassword = GlobalConstants.ADMIN_PASSWORD;

        //pre-condition
        userRegisterPage = userHomePage.openRegisterPage(); // page B: RegisterPage

        //Thực hiện action tại Register Page
        userRegisterPage.clickGenderRadioButton();
        userRegisterPage.enterFirstNameTextbox(firstName);
        userRegisterPage.enterLastNameTextbox(lastName);
        userRegisterPage.enterEmailTextbox(emailAddress);
        userRegisterPage.enterPasswordTextbox(password);
        userRegisterPage.enterConfirmPasswordTextbox(password);
        userRegisterPage.clickRegisterButton();

        //Verify
        Assert.assertEquals(userRegisterPage.getRegisterMessage(),"Your registration completed");

        // Logout site
        userRegisterPage.sleepInSeconds(2);

        userHomePage = userRegisterPage.clickLogoutLink();


    }

    @Test
    public void Role_01_User_Site_To_Admin_Site() {
        userLoginPage = userHomePage.openLoginPage();
        userLoginPage.sleepInSeconds(2);

        userHomePage = userLoginPage.loginToSystem(emailAddress,password);
        userHomePage.sleepInSeconds(2);

        //Verify My Account Link
        Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());

        // Từ trang userHomePage chuyển qua trang Admin để verify/approval cái order ở trên với quyền Admin
        //Cách 1:
        //userHomePage.openAdminSite(driver,adminUrlValue);
        // Cách 1 dùng hàm openAdminSite thực chất chính là đang gọi đến hàm openPageURL
        // Vì thực chất nó cũng chỉ gọi đến URL của site Admin
        // >> không cần tạo thêm hàm mới để tránh vi phạm Nguyên tắc DRY
        // Cách 2:
        userHomePage.openPageURL(driver,adminUrlValue); // truyền admin URL vào để mở ra trang admin

        //TH chưa login vào Admin site - lần đầu login
        // Mở ra màn hình đăng nhập của admin - Khởi tạo màn hình Login
        adminLoginPage = PageGenerator.getAdminLoginPage(driver);

        // TH đã login vào Admin site trước đó rồi - không phải login lần đầu
        // Mở ra màn hình dashboard - Khởi tạo màn hình Dashboard
        // adminDashboardPage = PageGenerator.getAdminDashboardPage(driver);
        // Nên không đưa đoạn khởi tạo các page Login/Dashboard vào trong hàm openAdminSite()

        //Login vào trang Admin
        adminLoginPage.enterToEmailTextbox(adminEmailAddress);
        adminLoginPage.enterToPasswordTextbox(adminPassword);
        adminDashboardPage = adminLoginPage.clickToLoginButton();
        adminDashboardPage.sleepInSeconds(2);

        // Verify
        Assert.assertTrue(adminDashboardPage.isAdminDashboardPageDisplay());

    }

    @Test
    public void Role_02_Admin_Site_To_User_Site() {
        // Tại Admin Dashboard page thực hiện các thao tác: Order/...
        //...
        // Từ trang Admin Dashboard quay về trang User
        adminDashboardPage.openPageURL(driver, userUrlValue);

        // Vì lúc trước đã login vào trang user rồi nên giờ quay lại sẽ hiển thị trang HomePage
        userHomePage = PageGenerator.getUserHomePage(driver);
        //Tại Homepage thực hiện các steps khác/action khác


    }

    @Test
    public void User_03_CustomerInforPage() {

        userCustomerInforPage = userHomePage.openCustomerInforPage();
        userCustomerInforPage.sleepInSeconds(2);

        Assert.assertTrue(userCustomerInforPage.isGenderMaleSelected());
        Assert.assertEquals(userCustomerInforPage.getFirstNameTextboxValue(),firstName);
        Assert.assertEquals(userCustomerInforPage.getLastNameTextboxValue(),lastName);
        Assert.assertEquals(userCustomerInforPage.getEmailTextboxValue(),emailAddress);

    }

    @Test
    public void User_04_Switch_Page() {


    }

    @AfterClass
    public void AfterClass() {
       // driver.quit();
    }



}
