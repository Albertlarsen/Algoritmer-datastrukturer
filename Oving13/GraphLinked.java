package Oving13;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;


public class GraphLinked {
    private Connection[] nodes;
    final boolean undirected;

    public GraphLinked(int size, boolean undirected) {
        nodes = new Connection[size];
        this.undirected = undirected;
    }

    public void addNode(int index, int connection, int weight) {
        if (nodes[index] == null) nodes[index] = new Connection(connection, weight);
        else nodes[index].addConnection(connection, weight);

        if (undirected) {
            if (nodes[connection] == null) nodes[connection] = new Connection(index, weight);
            else nodes[connection].addConnection(index, weight);
        }
    }

    public String toString() {
        String string = "";
        for (int i = 0; i < nodes.length;i++) {
            if (nodes[i] != null) {
                string += "from: " + i + " to: ";
                Connection connection = nodes[i];
                string += connection.getConnection() + ", ";
                while (connection.getNextConnection() != null) {
                    connection = connection.getNextConnection();
                    string += connection.getConnection() + ", ";
                }
                string += "\n";
            }
        }

        return string;
    }

    public int[][] dijkstra(int fromNode) {

        int[] lengthFromStart = new int[nodes.length];
        int[] cameFrom = new int[nodes.length];

        LengthFromSourceComparator comparator = new LengthFromSourceComparator(lengthFromStart);
        PriorityQueue<Integer> queue = new PriorityQueue<>(size(), comparator);

        for (int i = 0;i < size();i++) {
            lengthFromStart[i] = 10000000;
            cameFrom[i] = 10000000;
        }

        lengthFromStart[fromNode] = 0;
        cameFrom[fromNode] = -2;

        queue.add(fromNode);

        int nodeIndex, totalLength, totalNodes = 0;
        Connection connection;

        while (!queue.isEmpty()) {

            nodeIndex = queue.poll();
            connection = nodes[nodeIndex];
            totalNodes++;

            while (connection != null) {

                totalLength = lengthFromStart[nodeIndex] + connection.getLength();

                if (totalLength < lengthFromStart[connection.getConnection()]) {
                    lengthFromStart[connection.getConnection()] = totalLength;
                    cameFrom[connection.getConnection()] = nodeIndex;

                    queue.add(connection.getConnection());
                }

                connection = connection.getNextConnection();
            }
        }

        return new int[][]{lengthFromStart, cameFrom};
    }

    public int[][] dijkstra(int fromNode, int toNode) {

        int[] lengthFromStart = new int[nodes.length];
        int[] cameFrom = new int[nodes.length];

        LengthFromSourceComparator comparator = new LengthFromSourceComparator(lengthFromStart);
        PriorityQueue<Integer> queue = new PriorityQueue<>(size(), comparator);

        for (int i = 0;i < size();i++) {
            lengthFromStart[i] = 10000000;
            cameFrom[i] = 10000000;
        }

        lengthFromStart[fromNode] = 0;
        cameFrom[fromNode] = -2;

        queue.add(fromNode);

        int nodeIndex, totalLength, totalNodesProcessed = 0, getConnection;
        Connection connection;

        while (!queue.isEmpty()) {

            nodeIndex = queue.poll();

            if (nodeIndex == toNode) break;

            connection = nodes[nodeIndex];
            totalNodesProcessed++;

            while (connection != null) {

                totalLength = lengthFromStart[nodeIndex] + connection.getLength();
                getConnection = connection.getConnection();

                if (totalLength < lengthFromStart[getConnection]) {
                    lengthFromStart[getConnection] = totalLength;
                    cameFrom[getConnection] = nodeIndex;

                    if (toNode == getConnection) queue.remove(getConnection);
                    queue.add(getConnection);
                }

                connection = connection.getNextConnection();
            }
        }

        int traceBackNode = toNode;
        List<Integer> passedNodes = new ArrayList();
        passedNodes.add(0, toNode);

        while (cameFrom[traceBackNode] != -2) {
            passedNodes.add(0, traceBackNode);
            traceBackNode = cameFrom[traceBackNode];
        }

        System.out.println("Processed nodes: " + totalNodesProcessed);

        return new int[][]{passedNodes.stream().mapToInt(i->i).toArray(), new int[]{lengthFromStart[toNode]}};
    }

    public int[][] aStar(int fromNode, int toNode, GraphLogLat graphLogLat) {

        int[] lengthFromStart = new int[nodes.length];
        int[] cameFrom = new int[nodes.length];
        int[] distanceTo = new int[nodes.length];

        LengthBetweenNodesComparator comparator = new LengthBetweenNodesComparator(distanceTo, lengthFromStart);
        PriorityQueue<Integer> queue = new PriorityQueue<>(size(), comparator);

        for (int i = 0;i < size();i++) {
            lengthFromStart[i] = 10000000;
            cameFrom[i] = 10000000;
        }

        lengthFromStart[fromNode] = 0;
        cameFrom[fromNode] = -2;

        queue.add(fromNode);

        int nodeIndex, totalLength, totalNodesProcessed = 0, calc = 0, getConnection;
        Connection connection;

        while (!queue.isEmpty()) {

            nodeIndex = queue.poll();

            if (nodeIndex == toNode) break;

            connection = nodes[nodeIndex];
            totalNodesProcessed++;

            while (connection != null) {

                totalLength = lengthFromStart[nodeIndex] + connection.getLength();
                getConnection = connection.getConnection();

                if (totalLength < lengthFromStart[getConnection]) {
                    lengthFromStart[getConnection] = totalLength;
                    cameFrom[getConnection] = nodeIndex;

                    if (distanceTo[getConnection] == 0) {
                        distanceTo[getConnection] = graphLogLat.calcDistance(getConnection, toNode);
                        calc++;
                    }

                    if (toNode == getConnection) queue.remove(getConnection);
                    queue.add(getConnection);
                }

                connection = connection.getNextConnection();
            }
        }

        int traceBackNode = toNode;
        List<Integer> passedNodes = new ArrayList();
        passedNodes.add(0, toNode);

        while (cameFrom[traceBackNode] != -2) {
            passedNodes.add(0, traceBackNode);
            traceBackNode = cameFrom[traceBackNode];
        }

        System.out.println("Processed nodes: " + totalNodesProcessed);

        return new int[][]{passedNodes.stream().mapToInt(i->i).toArray(), new int[]{lengthFromStart[toNode]}};
    }

    public int size() {
        return nodes.length;
    }

    private class LengthFromSourceComparator implements Comparator<Integer>
    {
        private int[] lengthFromSource;

        public LengthFromSourceComparator(int[] lengthFromSource) {
            this.lengthFromSource = lengthFromSource;
        }

        @Override
        public int compare(Integer node1, Integer node2) {
            return lengthFromSource[node1] - lengthFromSource[node2];
        }
    }

    private class LengthBetweenNodesComparator implements Comparator<Integer>
    {
        private int[] lengthFromSource;
        private int[] distanceTo;

        public LengthBetweenNodesComparator(int[] distanceTo, int[] lengthFromSource) {
            this.distanceTo = distanceTo;
            this.lengthFromSource = lengthFromSource;
        }

        @Override
        public int compare(Integer node1, Integer node2) {

            return  distanceTo[node1] + lengthFromSource[node1] - distanceTo[node2] - lengthFromSource[node2] ;
        }
    }
}