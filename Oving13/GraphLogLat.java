package Oving13;

import java.util.Random;

import static java.lang.Math.*;

public class GraphLogLat {
    private double[][] nodes;
    private double r2 = 6371000 * 2, b1, b2, l1, l2, db, dl;

    public GraphLogLat(int totalNodes) {
        nodes = new double[totalNodes][];
    }

    public void addLocation(int node, double longitude, double latitude) {
        nodes[node] = new double[2];
        nodes[node][0] = longitude;
        nodes[node][1] = latitude;
    }

    public double[] getLogLat(int node) {
        return nodes[node];
    }

    public int calcDistance(int node1, int node2) {
        b1 = getLogLat(node1)[0];
        b2 = getLogLat(node2)[0];
        l1 = getLogLat(node1)[1];
        l2 = getLogLat(node2)[1];
        db = b2-b1;
        dl = l2-l1;

        return (int) floor(r2*asin(FastMath.sqrt(pow(FastMath.sin(((db)/2)), 2) + FastMath.cos(b1)*FastMath.cos((b2))*pow(FastMath.sin(((dl)/2)), 2))));
    }

    public static void main(String[] args) {
        GraphLogLat graphLogLat = new GraphLogLat(2);
        graphLogLat.addLocation(0, 62.67, 8.53);
        graphLogLat.addLocation(1, 63.42, 10.37);
        System.out.println(graphLogLat.calcDistance(0,1));

        Random rand = new Random();
        int n = 2000000;
        graphLogLat = new GraphLogLat(n);

        for (int i = 0; i < n; i++) {
            graphLogLat.addLocation(i, rand.nextDouble()*180-90, rand.nextDouble()*180-90);
        }

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < n - 1; i++) {
            graphLogLat.calcDistance(i, i + 1);
        }
        System.out.println("Used: "+ (System.currentTimeMillis()-startTime) + " mS.");
    }
}
