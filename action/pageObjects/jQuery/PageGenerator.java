package pageObjects.jQuery;

import org.openqa.selenium.WebDriver;

public class PageGenerator {

    public static HomePO getHomePageObject(WebDriver driver){
        return new HomePO(driver);
    }
}
