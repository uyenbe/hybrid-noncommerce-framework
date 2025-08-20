package phuongHoaXuan;

public class LeHoan {
    public static void main(String[] args) {
        DinhBoLinh dinhBoLinh = new DinhBoLinh();

        //biến private nên khác class ko truy cập được
//        dinhBoLinh.espreso = "cafe";
//        System.out.println(dinhBoLinh.getEspreso());

        //biến default >> khác class nhưng cùng package vẫn truy cập được
        dinhBoLinh.capichino = "capuchino";
        System.out.printf(dinhBoLinh.getCapichino());

        //biến protected: Khác class nhưng trong cumgf package vẫn truy cập được
        dinhBoLinh.capichino = "Cherry";
        System.out.printf(dinhBoLinh.getCherry());

        //Biến public: chỗ nào cũng dùng được
        dinhBoLinh.catimor = "Catimor";
        System.out.printf(dinhBoLinh.getCatimor());
    }
}
