package pageObjects.faceBook;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class LoginPO extends BasePage {
    private WebDriver driver;
    public LoginPO(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToNewAccountButton() {
    }

    public void enterEmailAdrressTextbox(String s) {
    }

    public boolean isConfirmEmailTextboxDisplayed() {
        return false;
    }
}
