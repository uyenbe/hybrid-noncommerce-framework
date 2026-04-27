package pageUIs.orangehrm.pim.employee;

public class PersonalDetailsPageUIs {
    public static final String AVATAR_IMAGE = "xpath=//div[@class='orangehrm-edit-employee-image']//img[@class='employee-image']";
    public static final String SAVE_BUTTON_AT_CHANGE_PROFILE_PICTURE = "xpath=//h6[text()='Change Profile Picture']/following-sibling::form//button[contains(string(), 'Save')]";
    public static final String FIRST_NAME_TEXTBOX = "xpath=//input[@name='firstName']";
    public static final String LAST_NAME_TEXTBOX = "xpath=//input[@name='lastName']";
    public static final String EMPLOYEE_ID_VALUE = "xpath=//label[text()='Employee Id']/parent::div/following-sibling::div//input";
    public static final String DRIVER_LICENSE_NUMBER_TEXTBOX = "xpath=//label[text()=\"Driver's License Number\"]/parent::div/following-sibling::div//input";
    public static final String LICENSE_EXPIRY_TEXTBOX = "xpath=//label[text()='License Expiry Date']/parent::div/following-sibling::div//input";
    public static final String NATIONALITY_DROPDOWN_PARENT = "xpath=//label[text()='Nationality']/parent::div/following-sibling::div//i";
    public static final String NATIONALITY_DROPDOWN_CHILD = "xpath=//label[text()='Nationality']/parent::div/following-sibling::div//div[contains(@class, 'oxd-select-option')]/span";
    public static final String NATIONALITY_DROPDOWN_ITEM_SELECTED = "xpath=//label[text()='Nationality']/parent::div/following-sibling::div//div[contains(@class, 'oxd-select-text-input')]";
    public static final String  MARITAL_DROPDOWN_PARENT = "xpath=//label[text()='Marital Status']/parent::div/following-sibling::div//i";
    public static final String  MARITAL_DROPDOWN_CHILD = "xpath=//label[text()='Marital Status']/parent::div/following-sibling::div//div[contains(@class, 'oxd-select-option')]/span";
    public static final String  MARITAL_DROPDOWN_ITEM_SELECTED = "xpath=//label[text()='Marital Status']/parent::div/following-sibling::div//div[contains(@class, 'oxd-select-text-input')]";
    public static final String  DATE_OF_BIRTH_TEXTBOX = "xpath=//label[text()='Date of Birth']/parent::div/following-sibling::div//input";
    public static final String GENDER_RADIO_BUTTON = "xpath=//label[text()='Gender']/parent::div/following-sibling::div//label[contains(string(), 'Male')]/span[contains(@class, 'oxd-radio-input--active')]";
    public static final String GENDER_RADIO_BUTTON_VALUE_SELECTED = "xpath=//label[text()='Gender']/parent::div/following-sibling::div//label[contains(string(), 'Male')]//input";
    public static final String SAVE_BUTTON = "xpath=//button[contains(string(),'Save')]";


}
