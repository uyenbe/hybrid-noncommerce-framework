package com.facebook;

import com.sun.source.tree.AssertTree;
import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.faceBook.LoginPO;
import pageObjects.faceBook.PageGenerator;
import pageObjects.jQuery.HomePO;

public class Level_18_UnDisplayed extends BaseTest {
    private WebDriver driver;

    //Tạo Page Object
    private LoginPO loginPage;
    @Parameters ({"browser", "url"})
    @BeforeClass
    public void BeforeClass(String browserName, String url) {
        driver = getBrowserDriver(browserName, url);
        loginPage = PageGenerator.getLoginPage(driver);
        loginPage.clickToNewAccountButton();
    }

    @Test
    public void TC_01_Element_UnDisplayed() {
        loginPage.enterEmailAdrressTextbox("");

        // Case 1: Verify confirm email textbox isDisplayed
        Assert.assertTrue(loginPage.isConfirmEmailTextboxDisplayed());

        // Case 2: Verify confirm email textbox is unDisplayed
        loginPage.enterEmailAdrressTextbox("");
        Assert.assertFalse(loginPage.isConfirmEmailTextboxDisplayed());
    }







}
