package pageUIs.nopcommerce.user;

public class UserHomePageUI {
    //biến non-static và non-final
    public String loginLink = "";

    //biến non-static
    public final String ACCOUNT_LINK = "";

    //Biến static và final
    public static final String REGISTER_LINK = "xpath=//a[@class='ico-register']";

    public static final String LOGIN_BUTTON = "xpath=//a[@class='ico-login']";

    public static final String MY_ACCOUNT_LINK = "xpath=//a[@class='ico-account']";

  //  public static final String CHANGE_LANGUAGE = ""


    //public: để cho các class bên PageObject (PO) có thể dùng được. chỉ cần gọi hàm/biến ra dùng bình thường
    // TH nếu khai báo là private/default thì các class bên PO khác package nên ko dùng được
    // TH nếu khai báo là protected: các class bên PO không kế thừa PUI nên ko áp dụng được

    //static: cho phép các class trong PO gọi trực tiếp từ class bên PUI để dùng mà ko cần tạo object
    // cách gọi từ PO: tênClass_PUI.biến();
    //TH ko để static thì các class bên PO muốn truy cập biến non-static sẽ phải
    // khởi tạo object hoặc kế thừa class PUI thì mới dùng được các biến
    //Mà tại PUI chỉ lưu các biến nên ko cần thiết PO phải tạo object, sẽ bị lâu

    //final: các class dùng biến sẽ ko thể chỉnh sửa lại giá trị của biến >> ngăn việc update lại giá trị trong quá trình chạy
    // Vì 1 page ít khi/không nên thay đổi loactor trong lúc chạy
    //TH khai báo biên non-final: các class khác sử dụng biến có thể update lại giá trị mới cho biến đó

    //String: Vì By locator của Selenium luôn nhận giá trị là String
    //REGISTER_LINK: dùng cố định, quy ước 1 biến là hằng số trong Java >> viết hoa
    //convention cho hằng số: phải viết hoa - nhiều hơn 1 từ thì phải dùng dấu _ để phân tách
}
