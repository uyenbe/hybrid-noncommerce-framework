package javOOP;

public class Dell implements Computer {

    @Override
    public void cpu() {
        System.out.println("CPU Core i9");
    }

    @Override
    public void ram() {

    }

    @Override
    public void ssd() {

    }

    @Override
    public void fan() {

    }

    public void card_GPU(){
        System.out.println("Card GPU");
    }
}
