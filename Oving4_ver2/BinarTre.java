package Oving4_ver2;

public class BinarTre<T extends Comparable<T>> {
    private BSTNode <T> root;

    public BinarTre() {
        root = null;
    }

    public boolean add(T value) {
        if (root == null) {
            root = new BSTNode<T>(value);
            return true;
        } else
            return root.add(value);
    }

    public void printInorder() {
        if (root != null){
            System.out.print("(");
            root.printInorder();
            System.out.println(")");
        }
    }
}