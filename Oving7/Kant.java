package Oving7;

public class Kant {
    Kant neste;
    Node til;
    boolean snudd = false;
    public Kant(Node n, Kant nst){
        til = n;
        neste = nst;
    }

    void deleteNode(Kant node) {
        Kant temp = node.neste;
        node.til = temp.til;
        node.neste = temp.neste;

    }
}
