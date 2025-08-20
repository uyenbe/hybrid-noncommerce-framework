import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Demo_01 {
    WebDriver driver;

    @BeforeClass
    public void BeforeClass() {
        driver = new ChromeDriver();
        driver.get("https://www.youtube.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }
    @Test
    public void TC_01(){
        driver.findElement(By.cssSelector("input[name='search_query']")).sendKeys("mixigaming");
        driver.findElement(By.cssSelector("button[aria-label='Search']")).click();

    }
}
