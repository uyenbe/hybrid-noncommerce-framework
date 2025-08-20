package com.jquery;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.jquery.HomePO;
import pageObjects.jquery.PageGenerator;

public class Level_11_Data_Table extends BaseTest {
    private WebDriver driver;
    private HomePO homePage;


    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        driver = getBrowserDriver(browserName, url);
        homePage = PageGenerator.getHomePO(driver);
    }
    @Test
    public void User_01_Paging(){
//        homePage.openPageByNumber("5");
//        Assert.assertTrue(homePage.isPageByNumberActive("5"));
//
//        homePage.openPageByNumber("8");
//        Assert.assertTrue(homePage.isPageByNumberActive("8"));
//
//        homePage.openPageByNumber("10");
//        Assert.assertTrue(homePage.isPageByNumberActive("10"));

    }

    @Test
    public void User_02_Search(){
//        homePage.enterTextboxByHeaderName("Females","777");
//        homePage.sleepInSeconds(2);
//        Assert.assertTrue(homePage.isRowDataDisplayed("777", "Antigua and Barbuda","803", "1580"));
//        homePage.refreshToPage(driver);
//
//        homePage.enterTextboxByHeaderName("Country", "Argentina");
//        homePage.sleepInSeconds(2);
//        Assert.assertTrue(homePage.isRowDataDisplayed("338282", "Argentina","349238", "687522"));
//        homePage.refreshToPage(driver);


    }

    @Test
    public void User_03_Delete_And_Edit(){

//        homepage.enterTextboxByHeaderName("Country", "Aruba");
//        homepage.sleepInSeconds(3);
//        homepage.clickDeleteButton("Aruba");
//        homepage.sleepInSeconds(2);
//        homepage.refreshToPage(driver);


//        homePage.enterTextboxByHeaderName("Country","Argentina");
//       //homePage.waitForSearchResult("Argentina");
//        homePage.clickEditButton("Argentina");
//        homePage.sleepInSeconds(2);
//      //  homepage.refreshToPage(driver);
        homePage.enterTextboxByHeaderName("Country", "Argentina");
        //homePage.editRowByCountryName("Argentina");
        homePage.clickEditButton("Argentina");
        homePage.sleepInSeconds(2);
        homePage.refreshToPage(driver);


    }


}
