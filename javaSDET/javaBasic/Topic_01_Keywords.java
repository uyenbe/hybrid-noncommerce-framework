package javaBasic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic_01_Keywords {
    //1 abstract - Annimal
    //Thể hiện tính chất trừu tượng trong OPP (Abstraction)
    //Các hàm abstract yêu cầu các hàm con kế thừa nó phải tự implement lại
    // Có các hàm non-abstract không được chứa các hàm abstract
    public void clickToElement(){
        // non-abstract có phần thân hàm (body)
        // các hàm con kế thừa nó có thể đem ra dùng luôn
    }

    //public abstract void selectE();

    // ==========================

    //Kiểu dữ liệu
    //1. Kiểu nguyên thuỷ - Primitive type
    char c = 'a';

    byte bNumber = 5;

    short sNumber = 16;

    int iNumber = 17;

    long lNumber = 18;

    float fNumber = 1.99f;

    double dNumber = 20d;

    boolean bBoolean = true;

    //2. Kiểu tham chiếu - Reference type
    String name = "";

    //===============
    //Các hàm
    //1. void - hàm này ko cần trả về kiểu gì hết
    void clickButton(){

    }

    //2. Hàm cần trả về kiểu dữ liệu >> sẽ có tên kiểu dữ liệu làm keyword

    String getName(){
        //Chỉ được return 1 lần, return > 1 lần sẽ báo lỗi
        return name;
    }

    //==============
    //Access modifier - Phạm vi truy cập
    //1. public: bất kỳ 1 class nào cũng truy cập vào bién này được
    public String address = "Hà Nam Hà Nội";

    //2. protected: Chỉ class kế thừa mới được truy cập
    protected String city = "Thái Bình";

    //3. private: chỉ dùng trong class này thôi
    private int a = 4;

    //4. default: trong package thì dùng được
    String nameCity = "Thái Bình";

    //package: define xem class/interface nằm trong package nào

    WebDriver driver;
    //Biểu thức điều kiện
    // Hàm IF
    public WebDriver getBrowser(String browser) {
        if (browser.equals("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            driver = new FirefoxDriver();
        }else {
            driver = new EdgeDriver();
        }

        switch (browser) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                driver = new EdgeDriver();

        }
        return driver;
    }



}


