package javOOP;

public class Flowers extends  Tree{
   //Flower kế thừa Tree nê phải override lại abstract method của Class Tree
    @Override
    void setTreeName() {

    }

    public static void main(String[] args) {
        //Abstract class - Tree ko cho khởi tạo object mới trong
        //Tree tree = new Tree();
    }
}
