package phuongHoaXuan;

public class DinhBoLinh {
    //Method/variable có access modifier = Private >> chỉ được phép sử dụng trong Class chứa nó
    //Variable/Property
    //Private
    private String espreso;

    //Method/Function
    private String getEspreso(){
        return espreso;
    }

    //Default: Chỉ cho phép các class trong cùng package dùng
    String capichino;

    String getCapichino(){
        return capichino;
    }

    //Protected: Chỉ cho phép kế thừa mới sử dụng được
    protected String cherry;
    protected String getCherry(){
        return cherry;
    }

    //Public: Chỉ cho phép kế thừa mới sử dụng được
    public String catimor;
    public String getCatimor(){
        return catimor;
    }

    public static void main(String[] args) {
        //hàm static ko thể gọi trực tiếp đến 1 biến/ hàm non-static khác
        DinhBoLinh dinhBoLinh = new DinhBoLinh();
        dinhBoLinh.espreso = "Espreso";
        System.out.println(dinhBoLinh.getEspreso());

        dinhBoLinh.capichino = "Capichino";
        System.out.printf(dinhBoLinh.getCapichino());

        dinhBoLinh.cherry = "Cherry";
        System.out.printf(dinhBoLinh.getCherry());

        dinhBoLinh.catimor = "Catimor";
        System.out.printf(dinhBoLinh.getCatimor());
    }

}
