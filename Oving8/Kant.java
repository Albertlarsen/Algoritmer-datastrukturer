package Oving8;

public class Kant {
    Kant neste;
    Node til;

    public Kant(Node n, Kant neste) {
        til = n;
        this.neste = neste;
    }
}
