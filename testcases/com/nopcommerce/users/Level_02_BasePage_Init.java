package com.nopcommerce.users;

import commons.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Level_02_BasePage_Init {
    private WebDriver driver;
    BasePage basePage; //Declera - Khai báo
    Random rand = new Random();
    private String firstName, lastName, email, password;
//            email = "automationtesting" + rand.nextInt() + "@gmail.com";
    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        basePage = new BasePage(); // Inital - Khởi tạo

        driver.get("http://192.168.3.110:5000/");
        //driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        firstName = "uyennt";
        lastName = "nguyen";
        email = "nguyenuyennt" +generateRandomNumber() + "@gmail.com.vn";
        password = "12345678";



    }

    @Test
    public void TC_01_Register() {

        clickChangeLanguage();

        basePage.waitForElementClickable(driver,"//a[@class='ico-register']");
        basePage.clickToElement(driver,"//a[@class='ico-register']");

        basePage.waitForElementClickable(driver,"//input[@id='gender-male']");
        basePage.clickToElement(driver,"//input[@id='gender-female']");

        basePage.sendKeysToElement(driver, "//input[@id='FirstName']",firstName);
        basePage.sendKeysToElement(driver, "//input[@id='LastName']",lastName);
        basePage.sendKeysToElement(driver, "//div[@class='inputs']//input[@type='email']",email);
        basePage.sendKeysToElement(driver, "//input[@id='Password']",password);
        basePage.sendKeysToElement(driver, "//input[@id='ConfirmPassword']",password);
        basePage.clickToElement(driver,"//button[@id='register-button']");

        Assert.assertEquals(basePage.getElementText(driver,"//div[@class='result']"),"Your registration completed");

        basePage.clickToElement(driver,"//a[@class='ico-logout']");


    }

    @Test
    public void TC_02_Login() {

        //nguyenuyennt22868@gmail.com.vn
        basePage.waitForElementClickable(driver,"//a[@class='ico-login']");
        basePage.clickToElement(driver,"//a[@class='ico-login']");

        basePage.sendKeysToElement(driver, "//div[@class='inputs']//input[@type='email']",email);
        basePage.sendKeysToElement(driver, "//input[@id='Password']",password);

        basePage.clickToElement(driver,"//button[@class='login-button']");


        Assert.assertTrue(basePage.isElementDisplay(driver,"//a[@class='ico-account' and text()='My account']"));


    }

    @Test
    public void TC_03_MyAccount() {
        basePage.waitForElementClickable(driver,"//a[@class='ico-account']");
        basePage.clickToElement(driver,"//a[@class='ico-account']");
        Assert.assertTrue(basePage.isElementSelected(driver,"//input[@id='gender-male']"));
        Assert.assertEquals(basePage.getAttributeValue(driver,"//input[@id='FirstName']","value"), firstName);
        Assert.assertEquals(basePage.getAttributeValue(driver,"//input[@id='LastName']","value"), lastName);
        Assert.assertEquals(basePage.getAttributeValue(driver,"//div[@class='inputs']//input[@id='Email']","value"), email);

    }
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
