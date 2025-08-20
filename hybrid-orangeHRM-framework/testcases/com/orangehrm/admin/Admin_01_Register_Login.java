package com.orangehrm.admin;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.EmployeeInforPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;

import java.time.Duration;

public class Admin_01_Register_Login extends BaseTest {
    private WebDriver driver;

    //Tạo các page object
    private HomePageObject homePage;
    private LoginPageObject loginPage;
    private EmployeeInforPageObject employeeInforPage;

    // Khai báo biến
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String middleName;
    private String employeeID;
    private String otherID;
    private String nationality;
    private String maritalStatus;
    private String gender;
    private String licenseExpiryDate;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = getBrowserDriver(browserName);

        //Khởi tạo Home page
        loginPage = new LoginPageObject(driver);
        username = "Admin";
        password = "admin123";
        firstName = "manda";
        lastName = "user";
        middleName = "akhil";
        employeeID = "muser";
        otherID = "4957589";
        licenseExpiryDate = "2023-10-18";
        nationality = "American";
        maritalStatus = "Single";
        gender = "Male";

    }

    @Test
    public void Admin_01_LoginPage() {
        loginPage.enterToUsernameTextbox(username);
        loginPage.enterToPasswordTextbox(password);
        loginPage.clickToLoginButton();

        homePage = new HomePageObject(driver);

        Assert.assertTrue(homePage.isDashboardTextDisplayed());
    }

   // @Test
    public void Admin_02_MyInforPage() {
        homePage.clickMyInforMenu();
        employeeInforPage = new EmployeeInforPageObject(driver);
       // Assert.assertTrue(employeeInforPage.isEmployeeNameDisplayed());
        Assert.assertEquals(employeeInforPage.getFirstNameTextboxValue(),firstName);
        Assert.assertEquals(employeeInforPage.getMiddleNameTextboxValue(),middleName);
        Assert.assertEquals(employeeInforPage.getLastNameTextboxValue(),lastName);
        Assert.assertEquals(employeeInforPage.getEmployeeIDTextboxValue(),employeeID);
        Assert.assertEquals(employeeInforPage.getOrtherIDTextboxValue(),otherID);
        Assert.assertEquals(employeeInforPage.isGenderMaleSelected(),gender);
        Assert.assertEquals(employeeInforPage.getLicenseExpiryDateValue(),licenseExpiryDate);
        Assert.assertEquals(employeeInforPage.getMaritalStatusValue(),maritalStatus);
        Assert.assertEquals(employeeInforPage.getNationalityValue(),nationality);
    }


}
