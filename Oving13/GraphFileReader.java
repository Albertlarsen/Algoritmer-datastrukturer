package Oving13;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class GraphFileReader {

    public static GraphLinked readGraphFile(String filename) {
        return GraphFileReader.readGraphFile(filename, false);
    }

    public static GraphLinked readGraphFile(String filename, boolean undirected) {
        String line = null;
        String[] line2;
        GraphLinked graph = null;

        try {

            BufferedReader bufferreader = new BufferedReader(new FileReader(filename));
            line = bufferreader.readLine();

            while (line != null) {

                line2 = line.trim().split("\\s+");
                if (graph == null) {
                    graph = new GraphLinked(Integer.parseInt(line2[0]), undirected);
                } else {
                    graph.addNode(Integer.parseInt(line2[0]), Integer.parseInt(line2[1]), Integer.parseInt(line2[2]));
                }

                line = bufferreader.readLine();
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return graph;
    }

    public static GraphLocation readGraphLocationFile(String filename) {
        String line = null;
        String[] line2;
        GraphLocation graphLocation = new GraphLocation();

        try {

            BufferedReader bufferreader = new BufferedReader(new FileReader(filename));
            line = bufferreader.readLine();
            line = bufferreader.readLine();

            while (line != null) {

                line2 = line.trim().split("\\s+");

                graphLocation.addLocation(Integer.parseInt(line2[0]), line2[2].substring(1, line2[2].length() - 1));

                line = bufferreader.readLine();
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return graphLocation;
    }

    public static GraphLogLat readGraphLogLatFile(String filename) {
        String line = null;
        String[] line2;
        GraphLogLat graphLogLat = null;

        try {

            BufferedReader bufferreader = new BufferedReader(new FileReader(filename));
            graphLogLat = new GraphLogLat(Integer.parseInt(bufferreader.readLine().trim()));
            line = bufferreader.readLine();

            while (line != null) {

                line2 = line.trim().split("\\s+");

                graphLogLat.addLocation(Integer.parseInt(line2[0]), Double.parseDouble(line2[1]), Double.parseDouble(line2[2]));

                line = bufferreader.readLine();
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return graphLogLat;
    }
}