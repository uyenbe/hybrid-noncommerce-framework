package javOOP;

public class Annimals {

    private String annimalName ;

    protected int annimalAge = 4;

    protected String getAnnimalName() { //hàm getter

        return annimalName;
    }

    protected void setAnnimalName(String annimalName) {// hàm setter
        this.annimalName = annimalName;
    }
}
