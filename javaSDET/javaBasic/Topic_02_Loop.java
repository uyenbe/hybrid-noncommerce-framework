package javaBasic;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Topic_02_Loop {

    // final: không cho phép gán lại giá trị mới
    //Có cả final class và final method, variable - Biến phải vết hoa
    public static final String PI = "3.1423456";

    //final Class: ko cho các class khác kế thừa
    public final void clickToElememt(){

    }

    //static: không cần khởi tạo , có thể truy cập trừ tên Class
    private static void clickToButton(){

    }

    public static void main(String[] args) {
        //Đối với các method/class/varibale không chứa key static thì phải tạo object new sau mới được truy cập
        Topic_02_Loop topic = new Topic_02_Loop();
        topic.clickToElememt();
        topic.isElementDisplay();
        //Đối với class/method/variable chứa key static >> gọi trực tiếp ko cần thông qua object new
        //Hoặc thông qua tên Class cha
        clickToButton();
        Topic_02_Loop.clickToButton();

        System.out.println(PI);
        System.out.println(Topic_02_Loop.PI);



        //Biểu thức vòng lặp
        //1. for
        //có thể kết hợp với các hàm điều kiện
        System.out.println("For");
        for(int i =0; i<= 10; i++){
            if(i == 5) {
                System.out.println(i);
                break;
            }
        }

        //2. while
        System.out.println("while");
        int a = 0;
        while (a <= 10){
            //Check điều kiện trước - chạy vòng lặp sau
            System.out.println(a);
            a++;
        }

        //3. do-while
        System.out.println("do-while");
        a = 1;
        do{
            //thực hiện vòng lặp ít nhất 1 lần trước
            System.out.println(a);
            a++;
        }while(a <= 10); //check điều kiện sau

    }

    //Hàm try-catch()
    public boolean isElementDisplay(){
        WebDriver driver = new ChromeDriver();
        WebElement element = driver.findElement(By.cssSelector(""));
        boolean status = false;
        try {
            status = element.isDisplayed();
        }catch (NoSuchElementException exception){
            //in ra exception
            exception.printStackTrace();

            //
            throw new RuntimeException(exception.getMessage());
        }finally { //step bắt buộc phải chạy
            // action bất kỳ
            //try-catch có thể ko cần finaly nhưng nếu có thì bắt buộc phải chạy

        }
        return status;
    }
}
