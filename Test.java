public class Test {

    public static void main(String [] args) {


        RBT rbt = new RBT();

        rbt.add(25);
        rbt.add(14);
        rbt.add(51);
        rbt.add(45);
        rbt.add(55);
        rbt.add(40);
        rbt.add(32);
        rbt.add(60);
        rbt.add(57);
        rbt.add(12);
        rbt.add(13);
    System.out.println(rbt.root.right.right);
   
    
        
        rbt.print2D(rbt.root);

    }
}