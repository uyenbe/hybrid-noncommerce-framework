package dynamicLocator;

public class StringFormat {
    public static void main(String[] args) {
        //Locator ban đầu
        String DELETE_ICON_BY_FEMALE_0 = "//td[@data-key='females' and text()='384187']" +
                "/preceding-sibling::td//button[@class='qgrd-remove-row-btn']";

//        clickToDeleteButton0(DELETE_ICON_BY_FEMALE_0);

        //Locator sau khi format với %s để thành chuỗi locator động và có thể truyền tham số bên ngoài vào
        // >> lúc này tại làm clickToDeleteButton cần thêm tham số
        String DELETE_ICON_BY_FEMALE = "//td[@data-key='females' and text()='%s']" +
                "/preceding-sibling::td//button[@class='qgrd-remove-row-btn']";

//        clickToDeleteButton(DELETE_ICON_BY_FEMALE, "384187");
//        clickToDeleteButton(DELETE_ICON_BY_FEMALE, "12253515");
//        clickToDeleteButton(DELETE_ICON_BY_FEMALE, "24128");

        // Khi thêm tham số country thì phải viết hàm clickToDeleteButton mới, thêm tham số country vào hàm
        String DELETE_ICON_BY_FEMALE_AND_COUNTRY = "//td[@data-key='females' and text()='%s']" +
                "/following-sibling::td[@data-key='country'and text()='%s']" +
                "/preceding-sibling::td//button[@class='qgrd-remove-row-btn']";
//        clickToDeleteButton(DELETE_ICON_BY_FEMALE_AND_COUNTRY, "384187","Afghanistan");
//        clickToDeleteButton(DELETE_ICON_BY_FEMALE, "12253515","AFRICA");
//        clickToDeleteButton(DELETE_ICON_BY_FEMALE, "24128","Albania");

        // áp dụng hàm chứa rest parameter
        clickToDeleteButton(DELETE_ICON_BY_FEMALE_AND_COUNTRY, "12253515","AFRICA");

    }
    // Hàm khi chưa dùng format
//    public static void clickToDeleteButton0(String locator){
//        System.out.println("Delete to icon: " + locator);
//    }
//
//    public static void clickToDeleteButton(String locator, String female){
//        System.out.println(String.format(locator, female));
//    }
//
//    // Khi thêm tham số country thì phải viết hàm clickToDeleteButton mới, thêm tham số country vào hàm
//    public static void clickToDeleteButton(String locator, String female, String country){
//        System.out.println(String.format(locator, female, country));
//    }

    // Thay vì mỗi lần thêm 1 tham số phải viết hàm mới thì mình áp dụng REST Parmeter - Tham số  cuối cùng
    // Tham số String... values phải được đặt ở cuối cùng trong các biến khi khai báo
    public static void clickToDeleteButton(String locator, String... values){
        System.out.println(String.format(locator, (Object[]) values));
    }
}
