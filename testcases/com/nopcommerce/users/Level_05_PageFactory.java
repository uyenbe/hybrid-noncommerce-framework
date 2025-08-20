package com.nopcommerce.users;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageFactory.*;

public class Level_05_PageFactory extends BaseTest {
    private WebDriver driver;

    //Tạo Page Object
    private HomePageFactory homePage;
    private LoginPageFactory loginPage;
    private RegisterPageFactoy registerPage;
    private CustomerInforPageFactory customerInforPage;
    private String firstName, lastName, emailAddress, password;
    @Parameters("browser")
    @BeforeClass
    public void BeforeClass(String browserName) {
        driver = getBrowserDriver(browserName);
        // Khởi tạo Home Page
        homePage = new HomePageFactory(driver);
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
        homePage.clickToRegisterLink();

        // Từ HomePage chuyển sang RegisterPage
        //Thực hiện action tại Register Page
        registerPage = new RegisterPageFactoy(driver);
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
        homePage = new HomePageFactory(driver);
        homePage.clickToLoginButton();

        // Từ Home page chuyển sang Login Page
        // thực hiện các action tại Login page
        loginPage = new LoginPageFactory(driver);
        loginPage.changeLanguage(driver);
        loginPage.sleepInSeconds(2);

        loginPage.enterEmailTextbox(emailAddress);
        loginPage.enterPasswordTextbox(password);
        loginPage.clickLoginButton();

        // Từ Login Page chuyển sang Home page
        homePage = new HomePageFactory(driver);
        homePage.changeLanguage(driver);
        homePage.sleepInSeconds(2);

        //Verify My Account Link
        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());

    }

    @Test
    public void User_03_CustomerInforPage() {
        homePage.clickMyAccountLink();

        //Chuyển từ Home Page sang Customer Infor Page
        customerInforPage = new CustomerInforPageFactory(driver);
        customerInforPage.changeLanguage(driver);
        customerInforPage.sleepInSeconds(2);

        Assert.assertTrue(customerInforPage.isGenderMaleSelected());
        Assert.assertEquals(customerInforPage.getFirstNameTextboxValue(),firstName);
        Assert.assertEquals(customerInforPage.getLastNameTextboxValue(),lastName);
        Assert.assertEquals(customerInforPage.getEmailTextboxValue(),emailAddress);

    }
}
