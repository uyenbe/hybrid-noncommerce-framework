package com.nopcommerce.users;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.nopcommerce.user.UserCustomerInforPO;
import pageObjects.nopcommerce.user.UserHomePO;
import pageObjects.nopcommerce.user.UserLoginPO;
import pageObjects.nopcommerce.user.UserRegisterPO;

import java.time.Duration;
import java.util.Random;

public class Level_03_Page_Object_Pattern {
    //Declare Variables
    private WebDriver driver;

    // Khởi tạo các biến từ Page Object Class
    private UserHomePO homePage;
    private UserLoginPO loginPage;
    private UserRegisterPO registerPage;
    private UserCustomerInforPO customerInforPage;



//    BasePage basePage; //Declera - Khai báo
//    Random rand = new Random();
//    private String firstName, lastName, email, password;
////            email = "automationtesting" + rand.nextInt() + "@gmail.com";

    // Pre-condition
    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
//        basePage = new BasePage(); // Inital - Khởi tạo

        // Mở URL lên >> qua HomePage
        driver.get("http://192.168.3.110:5000/");
        //driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        //Khởi tạo HomePage >> page đó được sinh ra và bắt đầu các action của Page đó
        homePage = new UserHomePO(driver);

//        firstName = "uyennt";
//        lastName = "nguyen";
//        email = "nguyenuyennt" +generateRandomNumber() + "@gmail.com.vn";
//        password = "12345678";

    }

    // Testcases
    @Test
    public void User_01_Register() {


        clickChangeLanguage();
        //Action 1 của HomePage
        //Viết hàm giả trước
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
//uyenNT7@gmail.com.vn/12345678
    @Test
    public void User_02_Login() {
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
    public void User_03_MyAccount() {
        homePage.openCustomerInforPage();

        //Từ LoginPage sang Customer Infor Page
        // >> Khởi tạo MyAccount và bắt đầu làm những action của page đó
        UserCustomerInforPO customerInforPage = new UserCustomerInforPO(driver);

        Assert.assertTrue(customerInforPage.isGenderMaleSelected());
        Assert.assertEquals(customerInforPage.getFirstNameTextboxValue(),"");
        Assert.assertEquals(customerInforPage.getLastNameTextboxValue(),"");
        Assert.assertEquals(customerInforPage.getEmailTextboxValue(),"");

//
    }

    //Post-condition
    @AfterClass
    public void afterClass() {

    }

    private int generateRandomNumber(){
        return new Random().nextInt(99999);
    }

    private void clickChangeLanguage(){

        String languageChange = driver.findElement(By.cssSelector("select#customerlanguage option[selected = 'selected']")).getText();
        if (languageChange.equals("VI")){
            driver.findElement(By.cssSelector("select#customerlanguage")).click();
            driver.findElement(By.xpath("//option[text()='EN']")).click();

        }
    }
}
