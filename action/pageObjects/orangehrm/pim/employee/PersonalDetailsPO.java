package pageObjects.orangehrm.pim.employee;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import pageUIs.orangehrm.pim.employee.PersonalDetailsPageUIs;

public class PersonalDetailsPO extends EmployeeTab {
    private WebDriver driver;
    public PersonalDetailsPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void clickToAvatarImage() {
        waitForElementClickable(driver, PersonalDetailsPageUIs.AVATAR_IMAGE);
        clickToElement(driver, PersonalDetailsPageUIs.AVATAR_IMAGE);

    }

    public Dimension getAvatarSize() {
        waitForElementVisible(driver, PersonalDetailsPageUIs.AVATAR_IMAGE);
       return getElementSize(driver, PersonalDetailsPageUIs.AVATAR_IMAGE);
        
    }

    public void clickToSaveButtonAtChangeProfilePicture() {
        waitForElementClickable(driver, PersonalDetailsPageUIs.SAVE_BUTTON_AT_CHANGE_PROFILE_PICTURE);
        clickToElement(driver, PersonalDetailsPageUIs.SAVE_BUTTON_AT_CHANGE_PROFILE_PICTURE);

    }

    // Chuyển vào BasePage làm hàm common
//    public boolean isSuccesMessageIsDisplayed() {
//        waitForElementVisible(driver, PersonalDetailsPageUIs.SUCCES_MASSAGE_UPLOAD);
//        return isElementDisplay(driver, PersonalDetailsPageUIs.SUCCES_MASSAGE_UPLOAD);
//    }

    public boolean isProfileAvatarUpdatedSucces(Dimension beforeUpload) {
        sleepInSeconds(2);
        Dimension afterUpload = getAvatarSize();
        return !(beforeUpload.equals(afterUpload));
    }

    public void enterToFirstNameTextbox(String firstNameUpdate) {
        waitForElementVisible(driver, PersonalDetailsPageUIs.FIRST_NAME_TEXTBOX);

        sendKeysToElement(driver, PersonalDetailsPageUIs.FIRST_NAME_TEXTBOX, firstNameUpdate);

    }

    public void enterToLastNameTextbox(String lastNameUpdate) {
        waitForElementVisible(driver, PersonalDetailsPageUIs.LAST_NAME_TEXTBOX);
        sendKeysToElement(driver, PersonalDetailsPageUIs.LAST_NAME_TEXTBOX, lastNameUpdate);
    }

    public String getEmployeeId() {
        waitForElementVisible(driver, PersonalDetailsPageUIs.EMPLOYEE_ID_VALUE);
        return getDOMPropertiesAttributeValue(driver, PersonalDetailsPageUIs.EMPLOYEE_ID_VALUE, "value");
    }

    public void enterToDriverLicenseTextbox(String driverLicense) {
        waitForElementVisible(driver, PersonalDetailsPageUIs.DRIVER_LICENSE_NUMBER_TEXTBOX);
        sendKeysToElement(driver, PersonalDetailsPageUIs.DRIVER_LICENSE_NUMBER_TEXTBOX, driverLicense);
    }



    public void selectNationlityDropdown(String nationality) {
        waitForElementClickable(driver, PersonalDetailsPageUIs.NATIONALITY_DROPDOWN_PARENT);
        selectItemInCustomDropdown(driver, PersonalDetailsPageUIs.NATIONALITY_DROPDOWN_PARENT, PersonalDetailsPageUIs.NATIONALITY_DROPDOWN_CHILD, nationality);
    }

    public void selectMaritalDropdown(String marital) {
        waitForElementClickable(driver, PersonalDetailsPageUIs.MARITAL_DROPDOWN_PARENT);
        selectItemInCustomDropdown(driver, PersonalDetailsPageUIs.MARITAL_DROPDOWN_PARENT, PersonalDetailsPageUIs.MARITAL_DROPDOWN_CHILD, marital);
    }

    public void enterToDateOfBirthTextbox(String dateOfBirth) {
        waitForElementVisible(driver, PersonalDetailsPageUIs.DATE_OF_BIRTH_TEXTBOX);
        sendKeysToElement(driver, PersonalDetailsPageUIs.DATE_OF_BIRTH_TEXTBOX, dateOfBirth);
    }

    public void selectGenderMaleRadioButton(String gender) {
        waitForElementClickable(driver, PersonalDetailsPageUIs.GENDER_RADIO_BUTTON);
        checkToCheckboxRadio(driver, PersonalDetailsPageUIs.GENDER_RADIO_BUTTON, gender);
    }

    public void clickToSaveButtonAtPersonalDetailContainer() {
        waitForElementClickable(driver, PersonalDetailsPageUIs.SAVE_BUTTON);
        clickToElement(driver, PersonalDetailsPageUIs.SAVE_BUTTON);
    }

    public String getFirstNameTextboxValue() {
        waitForElementVisible(driver, PersonalDetailsPageUIs.FIRST_NAME_TEXTBOX);
        return getDOMPropertiesAttributeValue(driver, PersonalDetailsPageUIs.FIRST_NAME_TEXTBOX, "value");
    }

    public String getLastNameTextboxValue() {
        waitForElementVisible(driver, PersonalDetailsPageUIs.LAST_NAME_TEXTBOX);
        return getDOMPropertiesAttributeValue(driver, PersonalDetailsPageUIs.LAST_NAME_TEXTBOX, "value");
    }

    public String getLicenseExpiryTextboxValue() {
        waitForElementVisible(driver, PersonalDetailsPageUIs.LICENSE_EXPIRY_TEXTBOX);
        return getDOMPropertiesAttributeValue(driver, PersonalDetailsPageUIs.LICENSE_EXPIRY_TEXTBOX, "value");
    }

    public String getDriverLicenseTextboxValue() {
        waitForElementVisible(driver, PersonalDetailsPageUIs.DRIVER_LICENSE_NUMBER_TEXTBOX);
        return getDOMPropertiesAttributeValue(driver, PersonalDetailsPageUIs.DRIVER_LICENSE_NUMBER_TEXTBOX, "value");
    }
    public boolean isGenderMaleRadioSelected(String gender) {
        waitForElementSelected(driver, PersonalDetailsPageUIs.GENDER_RADIO_BUTTON_VALUE_SELECTED, gender);
        return isElementSelected(driver, PersonalDetailsPageUIs.GENDER_RADIO_BUTTON_VALUE_SELECTED, gender);
    }

    public void enterToLicenseExpiryDate(String licenseExpiryDate) {
        waitForElementVisible(driver, PersonalDetailsPageUIs.LICENSE_EXPIRY_TEXTBOX);
        sendKeysToElement(driver, PersonalDetailsPageUIs.LICENSE_EXPIRY_TEXTBOX, licenseExpiryDate);

    }

    public String getNationlityDropdownValue() {
        waitForElementVisible(driver, PersonalDetailsPageUIs.NATIONALITY_DROPDOWN_ITEM_SELECTED);
        return getElementText(driver, PersonalDetailsPageUIs.NATIONALITY_DROPDOWN_ITEM_SELECTED);

    }

    public String getMaritalDropdownValue() {
        waitForElementVisible(driver, PersonalDetailsPageUIs.MARITAL_DROPDOWN_ITEM_SELECTED);
        return getElementText(driver, PersonalDetailsPageUIs.MARITAL_DROPDOWN_ITEM_SELECTED);
    }

    public String getDateOfBirthTextboxValue() {
        waitForElementVisible(driver, PersonalDetailsPageUIs.DATE_OF_BIRTH_TEXTBOX);
        return getDOMPropertiesAttributeValue(driver, PersonalDetailsPageUIs.DATE_OF_BIRTH_TEXTBOX, "value");
    }


}
