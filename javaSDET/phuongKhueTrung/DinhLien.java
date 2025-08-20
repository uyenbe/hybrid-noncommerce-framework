package phuongKhueTrung;

import phuongHoaXuan.DinhBoLinh;


public class DinhLien extends DinhBoLinh {
    public void clickToElement(){

        //Biến private nên class khác package nhưng kế thừa cũng ko truy cập trực tiếp được
//        System.out.println(espreso);
//        System.out.println(dinhBoLinh.getEspreso());

        //Biến default >> khác Class khác package ko truy cập trực tiếp được
//        System.out.println(espreso);
//        System.out.println(dinhBoLinh.getCapichino);

        //Biến protected: class con kế thừa có thể gọi trực tiếp ra dùng mà ko cần tạo object hoặc instance
        cherry = "Cherry coffe";
        System.out.println(getCherry());

        //Biến public
        catimor = "Catimor coffe";
        System.out.println(getCatimor());
    }
}
