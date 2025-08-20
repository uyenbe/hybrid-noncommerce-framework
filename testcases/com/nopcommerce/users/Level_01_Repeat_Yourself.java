package com.nopcommerce.users;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.time.Duration;
import java.util.Random;

public class Level_01_Repeat_Yourself {
    private WebDriver driver;
    Random rand = new Random();
    private String firstName, lastName, email, password;
//            email = "automationtesting" + rand.nextInt() + "@gmail.com";
    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();

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
        driver.findElement(By.cssSelector("a.ico-register")).click();

        driver.findElement(By.cssSelector("span.male>input")).click();
        driver.findElement(By.cssSelector("input#FirstName")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#LastName")).sendKeys(lastName);
        driver.findElement(By.cssSelector("div.inputs>input[type='email']")).sendKeys(email);
        driver.findElement(By.cssSelector("input#Password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys(password);
        driver.findElement(By.cssSelector("button#register-button")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(),"Your registration completed");

        driver.findElement(By.cssSelector("a.ico-logout")).click();

    }

    @Test
    public void TC_02_Login() {

        //nguyenuyennt22868@gmail.com.vn
        driver.findElement(By.cssSelector("a.ico-login")).click();

        driver.findElement(By.cssSelector("input.email")).sendKeys(email);
        driver.findElement(By.cssSelector("input.password")).sendKeys(password);
        driver.findElement(By.cssSelector("button.login-button")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//a[@class='ico-account' and text()='My account']")).isDisplayed());


    }

    @Test
    public void TC_03_MyAccount() {
        driver.findElement(By.cssSelector("a.ico-account")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("input#gender-male")).isSelected());
        Assert.assertEquals(driver.findElement(By.cssSelector("input#FirstName")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#LastName")).getAttribute("value"), lastName);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.inputs>input[type='email']")).getAttribute("value"), email);

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
