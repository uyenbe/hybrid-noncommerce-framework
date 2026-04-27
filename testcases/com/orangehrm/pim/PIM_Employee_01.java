package com.orangehrm.pim;

import commons.BaseTest;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.orangehrm.DashboardPO;
import pageObjects.orangehrm.LoginPO;
import pageObjects.orangehrm.PageGenerator;
import pageObjects.orangehrm.pim.employee.*;

public class PIM_Employee_01 extends BaseTest {
    private WebDriver driver;
    private LoginPO loginPage;
    private DashboardPO dashboardPage;
    private AddNewEmployeePO addNewEmployeePage;
    private PersonalDetailsPO personalDetailsPage;
    private EmployeeListPO employeeListPage;
    private  String employeeID, firstName, lastName, firstNameUpdate, lastNameUpdate  ;
    private String nationlity, driverLicense, licenseExpiryDate, gender, marital, dateOfBirth;
    private String avatarImageName = "xpath0.png";

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        driver = getBrowserDriver(browserName, url);
        loginPage = PageGenerator.getLoginPage(driver);

        firstName = "nguyen";
        lastName = "uyen" + generateRandomNumber();

        firstNameUpdate = "Nguyễn";
        lastNameUpdate = "Oén" + generateRandomNumber();
        driverLicense = "23031997";
        licenseExpiryDate = "2024-04-25";
        nationlity = "Vietnamese";
        marital = "Single";
        dateOfBirth = "2000-04-04";
        gender = "Male";

        loginPage.enterToUsername("nguyenuyen");
        loginPage.enterToPassword("4tbLxgFMyx64npLs9!");
        dashboardPage = loginPage.clickToLoginButton();

    }
    @Test
    public void Employee_01_Add_New() {
          employeeListPage = dashboardPage.clickPIMPage();

          addNewEmployeePage = employeeListPage.clickAddNewEmployee();
          addNewEmployeePage.enterToFirstNameTextbox(firstName);
          addNewEmployeePage.enterToLastNameTextbox(lastName);
          employeeID = addNewEmployeePage.getEmployeeID();
          personalDetailsPage = addNewEmployeePage.clickToSaveButtonAtEmployeeContainer();

        System.out.println("employeeID: " + employeeID);
    }

    @Test
    public void Employee_02_Upload_Avatar() {
        personalDetailsPage.clickToAvatarImage();

        // Lấy ra height và width của element (avatar) >> A
        Dimension beforeUpload = personalDetailsPage.getAvatarSize();

        personalDetailsPage.uploadMultipleFiles(driver, avatarImageName);

        personalDetailsPage.clickToSaveButtonAtChangeProfilePicture();

        Assert.assertTrue(personalDetailsPage.isSuccesMessageIsDisplayed(driver));// "Successfully Updated"

        // không viết hàm này vào trong hàm clickToSave mà phải gọi ra hàm riêng vì sau khi click btn Save thì hệ thống hiển thị như sau:
        //1. Message upload success trước
        //2. Sau đó mới mất icon loadding
            // >> nếu mà cho đoạn wait vào hàm clickToSave thì sẽ không handle được case  check message, vì lúc đó message đã ko hiển thị nữa rồi
        //3. Sau khi mất loading icon >> ảnh mới được update
        personalDetailsPage.waitAllLoadingIconInvisible(driver);

        //Chỗ này verify dựa vào sự thay đổi kích thước của ảnh trước và sau khi upload
        Assert.assertTrue(personalDetailsPage.isProfileAvatarUpdatedSucces(beforeUpload));

    }

    @Test
    public void Employee_03_Personal_Details() {
        // Update thông tin trong Personal Detail Page

        personalDetailsPage.openPersonalDetailsPage();
        personalDetailsPage.enterToFirstNameTextbox(firstNameUpdate);
        personalDetailsPage.enterToLastNameTextbox(lastNameUpdate);
        personalDetailsPage.enterToDriverLicenseTextbox(driverLicense);
        personalDetailsPage.enterToLicenseExpiryDate(licenseExpiryDate);
        personalDetailsPage.selectNationlityDropdown(nationlity);
        personalDetailsPage.selectMaritalDropdown(marital);
        personalDetailsPage.enterToDateOfBirthTextbox(dateOfBirth);
        personalDetailsPage.selectGenderMaleRadioButton(gender);
        personalDetailsPage.clickToSaveButtonAtPersonalDetailContainer();
        // Vì hàm isSuccesMessageIsDisplayed xuất hiện ở hầu hết các màn hình sau khi click Save
        // Nên >> chuyển thành hàm common trong BasePage để dùng lại nhiều lần
        Assert.assertTrue(personalDetailsPage.isSuccesMessageIsDisplayed(driver));
        personalDetailsPage.waitAllLoadingIconInvisible(driver);

        //Verify
        Assert.assertEquals(personalDetailsPage.getFirstNameTextboxValue(), firstNameUpdate);
        Assert.assertEquals(personalDetailsPage.getLastNameTextboxValue(), lastNameUpdate);
        Assert.assertEquals(personalDetailsPage.getEmployeeId(), employeeID);
        Assert.assertEquals(personalDetailsPage.getDriverLicenseTextboxValue(), driverLicense);
        Assert.assertEquals(personalDetailsPage.getLicenseExpiryTextboxValue(), licenseExpiryDate);
        Assert.assertEquals(personalDetailsPage.getNationlityDropdownValue(), nationlity);
        Assert.assertEquals(personalDetailsPage.getMaritalDropdownValue(), marital);
        Assert.assertEquals(personalDetailsPage.getDateOfBirthTextboxValue(), dateOfBirth);
        Assert.assertTrue(personalDetailsPage.isGenderMaleRadioSelected(gender));

    }

    @Test
    public void Employee_04_Contact_Details() {

    }

    @Test
    public void Employee_05_Emergency_Details() {

    }

    @Test
    public void Employee_06_Assigned_Dependents() {

    }

    @AfterClass
    public void afterClass() {
       // driver.quit();
    }
}
