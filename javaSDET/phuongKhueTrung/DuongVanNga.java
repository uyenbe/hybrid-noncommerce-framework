package phuongKhueTrung;

import phuongHoaXuan.DinhBoLinh;

public class DuongVanNga {
    public static void main(String[] args) {
        DinhBoLinh dinhBoLinh = new DinhBoLinh();
        //Biến protected: khác class, khác package và ko kế thừa thì ko dùng được
//        dinhBoLinh.cherry = "Cherry Coffe";
//        System.out.println(dinhBoLinh.getCherry());

        //Biến public
        dinhBoLinh.catimor = "Catimor Check";
        System.out.println(dinhBoLinh.getCatimor());
    }
}
