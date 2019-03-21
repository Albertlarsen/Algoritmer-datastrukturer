package Oving8;

public class Forgjenger {
    int distanse;
    Node forgjenger;

    public static final int uendelig = 1000000000;

    public Forgjenger() {
        distanse = uendelig;
    }
    public int finnDistanse() {
        return distanse;
    }

    public Node finnForgjenger() {
        return forgjenger;
    }
}