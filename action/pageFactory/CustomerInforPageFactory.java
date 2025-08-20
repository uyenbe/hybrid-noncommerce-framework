package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class CustomerInforPageFactory extends BasePage {
    private WebDriver driver;

    public CustomerInforPageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy (how = How.XPATH,using = "//input[@id='gender-male']")
    private WebElement genderMale;

    @FindBy (how = How.XPATH,using = "//input[@id='FirstName']")
    private WebElement firstName;

    @FindBy (how = How.XPATH,using = "//input[@id='LastName']")
    private WebElement lastName;

    @FindBy (how = How.XPATH,using = "//input[@id='Email']")
    private WebElement email;


    public boolean isGenderMaleSelected() {
        waitForElementVisible(driver, genderMale);
       return isElementSelected(genderMale);
    }

    public String getFirstNameTextboxValue() {
        waitForElementVisible(driver, firstName);
        return getAttributeValue(firstName, "value");
    }

    public String getLastNameTextboxValue() {
        waitForElementVisible(driver, lastName);
        return getAttributeValue(lastName, "value");
    }

    public String getEmailTextboxValue() {
        waitForElementVisible(driver, email);
        return getAttributeValue(email, "value");
    }
}
