package dynamicLocator;

import java.io.File;
import java.lang.reflect.Field;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

public class SystemInformation {
    public static void main(String[] args) {
        // Lấy ra hệ điều hành đang dùng
        String osName = System.getProperty("os.name");
        System.out.println("Tên hệ điều hành là: " + osName);

        // Lấy ra đường dẫn của project
        String projectPath = System.getProperty("user.dir");
        System.out.println("Đường dẫn của project: "+ projectPath);

        // Lấy đường dẫn đến thư mục cụ thể/ file cụ thể
        String xpathImagePath = projectPath + "uploadFiles" + "xpath0.png";
        System.out.println("Đường dẫn file xpath: "+ xpathImagePath);

        //các cách để lấy đúng format đường dẫn:
        //cách 1. dựa vào hệ điều hành. Dùng vòng lặp for để check
        // TH nếu hệ điều hành là Window: thì sẽ thêm dấu \ - backlash vào trước tên thư mục và tên file
        // ví dụ:
//        String xpath0ImagePath = projectPath + "\\uploadFiles" + "\\xpath1.png";
//        System.out.println("hệ điều hành window: " + xpath0ImagePath);
        // TH nếu hệ điều hành là MAC hoặc Linux: thì sẽ thêm đấu / - forwardlash vào trước tên thư mục và tên file
        // ví dụ:
//        String xpath1ImagePath = projectPath + "/uploadFiles" + "/xpath1.png";
//        System.out.println("hệ điều hành MAC/Linux: " + xpath1ImagePath);

        // cách 2. Dùng File.separator trước tên thư mục và tên file thay cho dấu \ hoặc /
        // File.separator sẽ tự lấy ra dấu / hoặc \ tuỳ theo hệ điều hành đang dùng
        // Ví dụ:
        String xpath2ImagePath = projectPath + File.separator + "uploadFiles" + File.separator + "xpath0.png";
        System.out.println("Cách 2: " + xpath2ImagePath);

        // cách 3. Dùng FileSystems.getDefault().getSeparator()
        // Ví dụ:
        String separator1 = FileSystems.getDefault().getSeparator();
        System.out.println("separator1: " + separator1);
        // hoặc
        // System.getProperty("file.separator") >> lấy ra dấu backlash hay forwardlash
        // Vis dụ:
        String separator = System.getProperty("file.separator");
        System.out.println("separator: " + separator);

    }
}
