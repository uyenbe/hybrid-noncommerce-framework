package pageUIs;

public class EmployeeInforPageUI {
    public static final String FIRST_NAME = "//input[@name='firstName']";

    public static final String FIRST_NAME_1 = "input[name = 'firstName']";

    public static final String LAST_NAME = "//input[@name='lastName']";

    public static final String MIDDLE_NAME = "//input[@name='middleName']";

    public static final String EMPLOYEE_ID = "//label[text()='Employee Id']//parent::div//following-sibling::div//input";

    public static final String OTHER_ID = "//label[text()='Other Id']//parent::div//following-sibling::div//input";

    public static final String NATIONALITY = "//label[text()='Nationality']//parent::div//following-sibling::div//div[@clear='false']";

    public static final String GENDER_MALE = "//label[text()='Gender']//parent::div//following-sibling::div//label[text()='Male']";

    public static final String MARITAL_STATUS = "//label[text()='Marital Status']//parent::div//following-sibling::div//div[@clear='false']";

    public static final String LICENSE_EXPIRY_DATE = "//label[text()='License Expiry Date']//parent::div//following-sibling::div//div[@class='oxd-date-input']//input";
}
