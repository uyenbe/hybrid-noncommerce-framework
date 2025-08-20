package pageObjects.nopcommerce.user;

import org.openqa.selenium.WebDriver;

public class UserAdressPO extends UserSidebarPO {
    private WebDriver driver;

    public UserAdressPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }


}
