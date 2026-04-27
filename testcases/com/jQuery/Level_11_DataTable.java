package com.jQuery;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.jQuery.HomePO;
import pageObjects.jQuery.PageGenerator;

public class Level_11_DataTable extends BaseTest {
    private WebDriver driver;

    //Tạo Page Object
    HomePO homePage;
    @Parameters ({"browser", "url"})
    @BeforeClass
    public void BeforeClass(String browserName, String url) {
        driver = getBrowserDriver(browserName, url);
        homePage = PageGenerator.getHomePageObject(driver);

    }

   // @Test
    public void Table_01_Paging() {
        // Các action cần xử lý cho table:
//        1. Chuyển trang (navigate paging >> trang nào được chuyển đến thì sẽ active
//         >> Lấy locator đại diện cho tất cả các trang(Dynamic locator) để chỉ cần truyền số trang vào thì sẽ chuyển sang trang đó
        homePage.openPageByNumber("5");
        //Verify
        Assert.assertTrue(homePage.isPageNumberActive("5"));

        homePage.openPageByNumber("10");
        //Verify
        Assert.assertTrue(homePage.isPageNumberActive("10"));

        homePage.openPageByNumber("15");
        //Verify
        Assert.assertTrue(homePage.isPageNumberActive("15"));


    }

   // @Test
    public void Table_02_Search_And_Verify() {
      //  Enter value to header textbox
        homePage.enterToTextboxByHeaderName("Females", "12253515");
        homePage.sleepInSeconds(2);
        // Verify
        Assert.assertTrue(homePage.isRowDataValueDisplayed("12253515", "AFRICA", "12599691","24853148"));
        //Đoạn này refresh lại để xoá dữ liệu đã nhập trước đó đi, để nhập tiếp các lần sau
        homePage.refreshPage(driver);


        homePage.enterToTextboxByHeaderName("Country", "Angola");
        homePage.sleepInSeconds(2);
        // Verify
        Assert.assertTrue(homePage.isRowDataValueDisplayed("276880", "Angola", "276472","553353"));
        homePage.refreshPage(driver);


        homePage.enterToTextboxByHeaderName("Males", "349238");
        homePage.sleepInSeconds(2);
        // Verify
        Assert.assertTrue(homePage.isRowDataValueDisplayed("338282", "Argentina", "349238","687522"));
        homePage.refreshPage(driver);

        homePage.enterToTextboxByHeaderName("Total", "1504");
        homePage.sleepInSeconds(2);
        // Verify
        Assert.assertTrue(homePage.isRowDataValueDisplayed("750", "Aruba", "756","1504"));
        homePage.refreshPage(driver);



    }

  //  @Test
    public void Table_03_Delete_Edit() {
        // trong 1 table cần tìm ra cột nào mang 1 giá trị duy nhất
        // Ở đây cột mang giá trị duy nhất là Country
        //>> Khi cần thao tác/action nào đó tại row nào đó thì nên đi từ cột chứa giá trị duy nhất để đi đến các action đó

        //Thực tế nên search giá trị cần delete/edit trước rồi sau đó mới xoá
        // Như thế thì dễ dàng hơn trong việc xoá 1 row bất kỳ và hợp lý với thao tác người dùng hơn

        //Click delete button
        homePage.enterToTextboxByHeaderName("Country", "Afghanistan");
        homePage.sleepInSeconds(3);
        homePage.deleteRowByCountryName("Afghanistan");
        homePage.sleepInSeconds(2);
        //refesh lại trang
        homePage.refreshPage(driver);

        // Click edit button
        homePage.enterToTextboxByHeaderName("Country", "Argentina");
        //homePage.editRowByCountryName("Argentina");
        homePage.clickEditButton("Argentina");
        homePage.sleepInSeconds(2);
        homePage.refreshPage(driver);


    }

    @Test
    public void Table_04_Get_All_Value_Row_Or_Column() {
        // Lấy hết ra
       // homePage.getAllValueAtColumnName("Country");
        System.out.println(homePage.getAllValueAtColumnName("Country"));
    }

  //  @Test
    public void Table_05_Action_By_Index() {
        // Vì TCs này dùng URL khác nên dùng hàm openURL để gọi đến URL mới cho tiện
        // Đỡ gây ảnh hưởng đến các TCs trên
        homePage.openPageURL(driver, "https://www.jqueryscript.net/demo/jQuery-Dynamic-Data-Grid-Plugin-appendGrid/");

        // Vì mặc định trang này load lên 3 row nên để hiển thị nhiều data hơn >> click buton Load data
        homePage.clickToLoadDataButton();
        homePage.sleepInSeconds(2);
        // Có thể thao tác với bất kỳ 1 colum hay 1 row nào
        // Truyền vào tên cột >> tự xử lý để lấy ra column Index sau đó thực hiện sendKey
      homePage.enterToTextboxByIndex("1","Contact Person","UyenNT");
//        homePage.sleepInSeconds(2);
//
//        homePage.enterToTextboxByIndex("2","Company","Công ty TNHH A");
//        homePage.sleepInSeconds(2);
//
//        homePage.enterToDropByIndex("6","Country","Hong Kong");

//        homePage.checkToCheckboxByIndex("6","NPO?",true);
//
//        homePage.checkToCheckboxByIndex("5","NPO?",false);

        // Khi thao tác lên các row thì index của row sẽ bị thay đổi
        // Bởi vì:
        //- TH Move up row 8 thì lúc này row 8 sẽ chuyển lên vị trí của row 7 và row 7 sẽ chuyển xuống vị trí của row 8
        // >> có sự thay đổi index của các row
        // Tương tự như các action khác:
        //- Insert tại row 4 thì hiển thị thêm dòng trống tại row 4 còn row 4 trước đó sẽ bị đẩy xuống vị trí của row 5,
        // >> các row phía dưới cũng bị ảnh hưởng đến vị trí
        homePage.clickToIconByIndex("8","Move Up");

        homePage.clickToIconByIndex("7","Remove");

        homePage.clickToIconByIndex("4","Insert");
    }





}
