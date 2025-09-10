package com.jQuery;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.jQuery.HomePO;
import pageObjects.jQuery.PageGenerator;

public class Level_12_Upload extends BaseTest {
    private WebDriver driver;
    private String xpath,capture,htmlDOM;

    //Tạo Page Object
    HomePO homePage;
    @Parameters ({"browser", "url"})
    @BeforeClass
    public void BeforeClass(String browserName, String url) {
        driver = getBrowserDriver(browserName, url);
        homePage = PageGenerator.getHomePageObject(driver);
        xpath = "xpath0.png";
        capture = "Capture.png";
        htmlDOM = "HTML-DOM-la-gi.jpg";

    }

    @Test
    public void Upload_01() {
        //Yêu cầu của upload:
        // Lấy ra được đường dẫn của file/thư mục cho đúng
        // Đúng trên Tất cả OS: Windown/Mac/Linux >> đều phải chạy được
        //UploadPath đã có bên GlobalConstants
        // Có thể upload 1 lần 1 file
//        homePage.uploadMultipleFiles(driver, xpath);
//        homePage.sleepInSeconds(3);
//        homePage.refreshPage(driver);

        // Upload 1 lần nhiều file >> dùng cùng 1 hàm với upload 1 lần 1 file
//        homePage.uploadMultipleFiles(driver, xpath, capture);
//        homePage.sleepInSeconds(3);
//        homePage.refreshPage(driver);

        homePage.uploadMultipleFiles(driver, xpath, capture, htmlDOM);
        homePage.sleepInSeconds(3);


        // Verify việc load file lên
        Assert.assertTrue(homePage.isFileLoadedByName(xpath));
        Assert.assertTrue(homePage.isFileLoadedByName(capture));
        Assert.assertTrue(homePage.isFileLoadedByName(htmlDOM));

        // Click Upload button tại từng file
        homePage.clickToUploadButton(driver);

        // Có thể Verify 1 file/ nhiều file được upload xong >> Dùng 1  hàm
        Assert.assertTrue(homePage.isFileUpLoadedSuccess(xpath));
        Assert.assertTrue(homePage.isFileUpLoadedSuccess(capture));
        Assert.assertTrue(homePage.isFileUpLoadedSuccess(htmlDOM));

        // Có cần care tới Open file dialog hay ko ? (dialog là của desktop app - hệ điều hành của máy chứ ko phải của web hay app)
        // >> Ko cần care đến việc open file dialog vì:
        // cách làm của mình dựa vào việc sendkey vào thẻ input có type = file
        // >> ko đùng đến việc open file dialog

    }







}
