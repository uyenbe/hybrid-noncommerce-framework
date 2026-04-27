package com.orangehrm.pim;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PIM_Employee_02 extends BaseTest {
    private WebDriver driver;
    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        driver = getBrowserDriver(browserName, url);
        // add new vào pre-condition
    }
    @Test

    public void Employee_07_Personal_Details() {

    }
    public void Employee_08_Personal_Details() {

    }
    public void Employee_09_Personal_Details() {

    }
    public void Employee_10_Personal_Details() {

    }
    public void Employee_11_Personal_Details() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
