package pageObjects.nopcommerce.user;

import org.openqa.selenium.WebDriver;

public class UserChangePasswordPO extends UserSidebarPO {
    private WebDriver driver;
    public UserChangePasswordPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
}
