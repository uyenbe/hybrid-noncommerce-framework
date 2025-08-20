package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class RegisterPageFactoy extends BasePage {
    private WebDriver driver;

    public RegisterPageFactoy(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy (how = How.XPATH, using = "//input[@id='gender-male']")
    private WebElement genderMale;

    @FindBy (how = How.XPATH, using = "//input[@id='FirstName']")
    private WebElement firstNameTextbox;

    @FindBy (how = How.XPATH, using = "//input[@id='LastName']")
    private WebElement lastNameTextbox;

    @FindBy (how = How.XPATH, using = "//input[@id='Email']")
    private WebElement emailTextbox;

    @FindBy (how = How.XPATH, using = "//input[@id='Password']")
    private WebElement passwordTextbox;

    @FindBy (how = How.XPATH, using = "//input[@id='ConfirmPassword']")
    private WebElement confirmPasswordTextbox;

    @FindBy (how = How.XPATH, using = "//button[@id='register-button']")
    private WebElement registerButton;

    @FindBy (how = How.XPATH, using = "//div[@class='result']")
    private WebElement registerMessage;

    @FindBy (how = How.XPATH, using = "//a[@class='ico-logout']")
    private WebElement logoutButton;

    public void clickGenderRadioButton() {
        waitForElementClickable(driver, genderMale);
        clickToElement(driver, genderMale);
    }

    public void enterFirstNameTextbox(String firstName) {
        waitForElementVisible(driver,firstNameTextbox);
        sendKeysToElement(firstNameTextbox, firstName);
    }

    public void enterLastNameTextbox(String lastName) {
        waitForElementVisible(driver,lastNameTextbox);
        sendKeysToElement(lastNameTextbox, lastName);
    }

    public void enterEmailTextbox(String emailAddress) {
        waitForElementVisible(driver,emailTextbox);
        sendKeysToElement(emailTextbox, emailAddress);
    }

    public void enterPasswordTextbox(String password) {
        waitForElementVisible(driver,passwordTextbox);
        sendKeysToElement(passwordTextbox, password);
    }

    public void enterConfirmPasswordTextbox(String password) {
        waitForElementVisible(driver,confirmPasswordTextbox);
        sendKeysToElement(confirmPasswordTextbox, password);
    }

    public void clickRegisterButton() {
        waitForElementClickable(driver, registerButton);
        clickToElement(driver, registerButton);
    }

    public String getRegisterMessage() {
        waitForElementVisible(driver, registerMessage);
        return getElementText(registerMessage);
    }

    public void clickLogoutLink() {
        waitForElementClickable(driver, logoutButton);
        clickToElement(driver, logoutButton);

    }
}
