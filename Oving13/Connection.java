package Oving13;

public class Connection {
    private int connection;
    private int length;
    private Connection nextConnection;

    public Connection(int connection, int length) {
        this.connection = connection;
        this.length = length;
    }

    public int getConnection() {
        return connection;
    }

    public Connection getNextConnection() {
        return nextConnection;
    }

    public int getLength() {
        return length;
    }

    public void addConnection(int connection, int weight) {
        if (nextConnection == null) nextConnection = new Connection(connection, weight);
        else nextConnection.addConnection(connection, weight);
    }

}