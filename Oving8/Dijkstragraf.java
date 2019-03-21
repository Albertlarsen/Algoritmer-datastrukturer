package Oving8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Dijkstragraf {

    private int N; //antall noder
    private int K; //antall kanter
    private Node[] node;

    PriorityQueue<Node> priorityQueue; // = new PriorityQueue<>();


    public Dijkstragraf(String filPlassering) {
        lagGrafFraFil(filPlassering); //Hente graf fra fil på pcen

        priorityQueue = new PriorityQueue<>(N, new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) {
                return (node1.d.distanse - node2.d.distanse);
            }
        });
    }

    private void lagGrafFraFil(String filPlassering) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filPlassering));
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            N = Integer.parseInt(stringTokenizer.nextToken());
            node = new Node[N];

            for (int i = 0; i < N; i++) {
                node[i] = new Node();
                node[i].nodeNummer = i;
            }

            K = Integer.parseInt(stringTokenizer.nextToken());

            for (int j = 0;  j < K; j++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                int fra = Integer.parseInt(stringTokenizer.nextToken());
                int til = Integer.parseInt(stringTokenizer.nextToken());
                int vekt = Integer.parseInt(stringTokenizer.nextToken());
                VKant vKant = new VKant(node[til], (VKant)node[fra].kant1, vekt);
                node[fra].kant1 = vKant;
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    public void skrivUtGraf(int start) {

        System.out.println("Node  |  Forgjenger  |  Distanse");
        for (int i = 0; i < N; i++) {
            if (i != start) {

                String distanse = "                       nåes ikke";

                if (node[i].d.finnDistanse() != Forgjenger.uendelig) {
                    distanse = "             " + node[i].d.finnDistanse();
                }

                if (node[i].d.finnForgjenger() != null) {
                    System.out.println(node[i].nodeNummer + "            " + node[i].d.finnForgjenger().nodeNummer + distanse);
                } else {
                    System.out.println(node[i].nodeNummer + distanse);
                }

            } else {
                System.out.println(start + "          start           0");
            }
        }
    }

    private void initForgjenger(Node s) {
        for (int i = N; i-- > 0;) {
            node[i].d = new Forgjenger();
        }
        s.d.distanse = 0;
    }

    private void forkort(Node n, VKant kant) {
        Forgjenger nd = n.d;
        Forgjenger md = kant.til.d;

        if (md.distanse > nd.distanse + kant.avstandMellom) {
            md.distanse = nd.distanse + kant.avstandMellom;
            md.forgjenger = n;
            priorityQueue.remove(kant.til);
            priorityQueue.add(kant.til);
        }
    }

    public void dijkstra(int startNummer) {
        Node noden = node[startNummer];
        initForgjenger(noden);

        priorityQueue.addAll(new ArrayList<Node>(Arrays.asList(node)));

        for (int i = N; i > 1; --i) {

            Node n = priorityQueue.poll();
            for (VKant k = n.kant1; k != null; k = (VKant)k.neste) {
                forkort(n, k);
            }
        }
        skrivUtGraf(startNummer);
    }
}