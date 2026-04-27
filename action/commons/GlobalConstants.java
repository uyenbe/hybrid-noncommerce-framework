package commons;

public class GlobalConstants {
    // System Infor
    public static final String PROJECT_PATH = System.getProperty("user.dir");
    public static final String OS_NAME = System.getProperty("os.name");
    public static final String SEPARATOR = System.getProperty("file.separator");

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
    public static final long LONG_TIMEOUT = 20;

    //Download/ Upload file

    public static final String UPLOAD_PATH = PROJECT_PATH + SEPARATOR + "uploadFiles" + SEPARATOR;
    public static final String DOWNLOAD_PATH = PROJECT_PATH + SEPARATOR + "downloadFiles" + SEPARATOR ;

    //Retry Case Failed
    public static final int RETRY_NUMBER = 3;
    // Browser Logs/Extentions
    public static final String BROWSER_LOG_PATH = PROJECT_PATH + SEPARATOR + "browserLog" + SEPARATOR;
    public static final String BROWSER_EXTENTION_PATH = PROJECT_PATH + SEPARATOR + "browserExtensions" + SEPARATOR;

    // HTML Report Folder
    public static final String REPORTING_PATH = PROJECT_PATH + SEPARATOR + "htmlReportNG" + SEPARATOR;
    public static final String EXTENT_PATH = PROJECT_PATH + SEPARATOR + "htmlExtent" + SEPARATOR;
    public static final String ALLURE_PATH = PROJECT_PATH + SEPARATOR + "htmlAllure" + SEPARATOR;

    // Data Test/ Environment
    public static final String DATA_TEST = PROJECT_PATH + SEPARATOR + "dataTest" + SEPARATOR;
    public static final String ENVIRONMENT_CONFIG_PATH = PROJECT_PATH + SEPARATOR + "environmentConfig" + SEPARATOR;



}
