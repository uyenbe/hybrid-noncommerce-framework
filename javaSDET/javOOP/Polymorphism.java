package javOOP;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class Polymorphism {

    // Đa hình
    public Polymorphism() {
        //Constructor ko tham số
    }

    public Polymorphism(WebDriver driver) {

    }

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        Actions actions = new Actions(driver);
        actions.click();
    }
}
