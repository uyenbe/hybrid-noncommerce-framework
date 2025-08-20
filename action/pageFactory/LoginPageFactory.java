package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPageFactory extends BasePage {
    private WebDriver driver;
    public LoginPageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy (className = "email")
    private WebElement email;

    @FindBy (how = How.XPATH, using = "//input[@class='password']")
    private WebElement password;

    @FindBy (how = How.XPATH, using = "//button[contains(@class,'login-button')]")
    private WebElement loginButton;


    public void enterEmailTextbox(String emailAddress) {
        waitForElementVisible(driver, email);
        sendKeysToElement(email, emailAddress);

    }

    public void enterPasswordTextbox(String password1) {
        waitForElementVisible(driver, password);
        sendKeysToElement(password, password1 );
    }

    public void clickLoginButton() {
        waitForElementClickable(driver, loginButton);
        clickToElement(driver, loginButton);
    }

    public void loginToSystem(String emailAddress, String password) {
        enterEmailTextbox(emailAddress);
        enterPasswordTextbox(password);
        clickLoginButton();
    }
}
