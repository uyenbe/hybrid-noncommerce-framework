package commons;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.time.Duration;
import java.util.Random;

public class BaseTest {
    public WebDriver getDriver() {
        return driver;
    }

    protected WebDriver driver;
//    protected final Logger log;
//
//    public BaseTest() {
//        log = LogManager.getLogger(getClass());
//    }

    protected WebDriver getBrowserDriver(String browserName) {
        BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
        switch (browserList){
            case CHROME:
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            case EDGE:
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("Browser Not Found");
        }
        driver.get(GlobalConstants.TEST_USER_LINK);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        return driver;
    }

    protected WebDriver getBrowserDriver(String browserName, String Url) {
        BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
        switch (browserList){
            case CHROME:
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            case EDGE:
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("Browser Not Found");
        }
        driver.get(Url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        return driver;
    }
    protected int generateRandomNumber(){
        return new Random().nextInt(99999);
    }

    protected void assertTrue(boolean condition) {
        Assert.assertTrue(condition);
    }

//    protected boolean verifyTrue(boolean condition) {
//        boolean status = true;
//        try {
//            Assert.assertTrue(condition);
//            log.info("--------PASSED----------");
//
//        } catch (Throwable e) {
//            status = false;
//            log.info("--------FAILED----------");
//            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
//            Reporter.getCurrentTestResult().setThrowable(e);
//        }
//        return status;
//    }
//
//    protected boolean verifyTFalse(boolean condition) {
//        boolean status = false;
//        try {
//            Assert.assertTrue(condition);
//            log.info("--------PASSED----------");
//        } catch (Throwable e) {
//            status = true;
//            log.info("--------FAILED----------");
//            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
//            Reporter.getCurrentTestResult().setThrowable(e);
//        }
//        return status;
//    }
//
//    protected boolean verifyEquals(Object actual, Object expected) {
//        boolean status = true;
//        try {
//            Assert.assertEquals(actual, expected);
//            log.info("--------PASSED----------");
//        } catch (Throwable e) {
//            status = false;
//            log.info("--------FAILED----------");
//            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
//            Reporter.getCurrentTestResult().setThrowable(e);
//        }
//        return status;
//    }

    @BeforeSuite
    public void deleteFileInReport() {
        // Remove all file in ReportNG screenshot (image)
        deleteAllFileInFolder("htmlReportNG");

        // Remove all file in Allure attachment (json file)
       // deleteAllFileInFolder("allure-json");
    }

    private void deleteAllFileInFolder(String folderName) {
        try {
            String pathFolderDownload = GlobalConstants.PROJECT_PATH + File.separator + folderName;
            File file = new File(pathFolderDownload);
            File[] listOfFiles = file.listFiles();
            if (listOfFiles.length != 0) {
                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].isFile() && !listOfFiles[i].getName().equals("environment.properties")) {
                        new File(listOfFiles[i].toString()).delete();
                    }
                }
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

}
