package commons;

public class GlobalConstants {
    // System Infor
    public static final String PROJECT_PATH = System.getProperty("user.dir");
    public static final String OS_NAME = System.getProperty("os.name");

    //App Infor User
    public static final String DEV_USER_LINK = "http://user.local:8086/";
    public static final String TEST_USER_LINK = "http://user.local:8086/";

    //App Infor Admin
    public static final String DEV_ADMIN_LINK = "http://admin.local:8086/admin";
    public static final String TEST_ADMIN_LINK = "http://admin.local:8086/admin";

    public static final String ADMIN_USERNAME = "nguyenuyen.ba@gmail.com";
    public static final String ADMIN_PASSWORD = "Automation111!!!";

    // Wait infor
    public static final long SHORT_TIMEOUT = 10;
    public static final long LONG_TIMEOUT = 30;

    //Download/ Upload file
    public static final String UPLOAD_PATH = PROJECT_PATH + "/uploadFiles";
    public static final String DOWNLOAD_PATH = PROJECT_PATH + "/downloadFiles";

    //Retry Case Failed
    public static final int RETRY_NUMBER = 3;
    // Browser Logs/Extentions
    public static final String BROWSER_LOG_PATH = PROJECT_PATH + "/browserLog";
    public static final String BROWSER_EXTENTION_PATH = PROJECT_PATH + "/browserExtensions";

    // HTML Report Folder
    public static final String REPORTING_PATH = PROJECT_PATH + "/htmlReportNG";
    public static final String EXTENT_PATH = PROJECT_PATH + "/htmlExtent";
    public static final String ALLURE_PATH = PROJECT_PATH + "/htmlAllure";

    // Data Test/ Environment
    public static final String DATA_TEST = PROJECT_PATH + "/dataTest/";
    public static final String ENVIRONMENT_CONFIG_PATH = PROJECT_PATH + "/environmentConfig/";



}
