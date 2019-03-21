package Oving7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Graf {
    int N, K;
    Node[] node;
    int counter = 0;
    boolean dfs_print = false;


    /*
        Les inn graf fra fil
     */
    public void ny_ugraf(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        node = new Node[N];
        for (int i = 0; i < N; i++) node[i] = new Node();
        K = Integer.parseInt(st.nextToken());
        System.out.println("Antall noder: " + N + " Antall kanter: " + K);
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int fra = Integer.parseInt(st.nextToken());
            int til = Integer.parseInt(st.nextToken());
            node[fra].i = fra;
            node[til].i = til;
            Kant k = new Kant(node[til],node[fra].kant1);
            node[fra].kant1 = k;
        }
    }


    /*
            Resetter nodene sine forgjengere for ny dfs, setter tiden til null
     */
    public void dfs_init() {
        counter = 0;
        for (int i = N; i--> 0;) {
            node[i].d = new Dfs_forgj();
        }
        Dfs_forgj.null_tid();
    }

    /*
           Hovedmetoden for dfs
     */
    public void df_sok(Node n) {
        Dfs_forgj nd = (Dfs_forgj)n.d;
        nd.funnet_tid = Dfs_forgj.les_tid();
        for (Kant k = n.kant1; k != null; k = k.neste) {
            Dfs_forgj md = (Dfs_forgj)k.til.d;
            if (md.funnet_tid == 0) {
                if(dfs_print) {
                    md.forgj = n;
                    md.dist = nd.dist + 1;
                    df_sok(k.til);
                    System.out.print(" " + k.til.i);
                } else {
                    md.forgj = n;
                    md.dist = nd.dist + 1;
                    df_sok(k.til);
                }

            }
        }
        nd.ferdig_tid = Dfs_forgj.les_tid();
    }

    /*
        Kjører df_sok på alle nodene i listen rekursift
     */
    public void dfs(Node s) {
        ((Dfs_forgj)s.d).dist = 0;
        if(((Dfs_forgj)s.d).funnet_tid == 0) {
            if(dfs_print){
                System.out.print("Node :" + s.i);
                df_sok(s);
                System.out.println();
                counter++;
            } else {
                df_sok(s);
                counter++;
            }
        }
    }

    /*
            Inverterer graf før andre dfs
     */
    public void inverterGraf(){
        Node[] omvendtGraf = new Node[node.length];

        for(int i = 0; i<node.length; i++){
            omvendtGraf[i] = node[node.length - 1 - i];
        }

        dfs_print = true;
        node = omvendtGraf;
    }

    public void transponse() {
        for (Node a : node) {
            for (Kant k = a.kant1; k != null; k = k.neste) {
                if (k.snudd) continue;
                k.til.kant1 = new Kant(a,k.til.kant1);
                k.til.kant1.snudd = true;
            }
            Kant forrige = a.kant1;
            for(Kant k = a.kant1; k != null; k = k.neste) {
                if(!k.snudd && k == a.kant1 ){
                    a.kant1 = k.neste;
                    forrige = k.neste;
                } else if(!k.snudd){
                    forrige.neste = k.neste;
                }else{
                    forrige = k;
                }
            }
        }
        dfs_print = true;
    }

    public static void main(String[] args) throws IOException {

        FileReader leseforbTilFil = new FileReader("C:\\Users\\Alber\\Documents\\TDAT2005 - Algortimer\\src\\LesFraFil_ov7\\L7g6");
        BufferedReader br = new BufferedReader(leseforbTilFil);
        Graf g = new Graf();


        // leser graf fra fil
        g.ny_ugraf(br);
        br.close();

        // initialiser dfs
        g.dfs_init();

        // Kjør dfs på nabolisten
        for (Node a: g.node) {
            g.dfs(a);
        }

        // Sorter nabolisten
        Arrays.sort(g.node);

        // Inverter nabolisten
        g.inverterGraf();

        // initialiser dfs på nytt for ny dfs
        g.dfs_init();

        // Kjør dfs på nytt på den inverterte nabolisten
        for (Node a: g.node) {
            g.dfs(a);
        }

        // Print ut sterke sammenhenger
        System.out.println("Antall sterke sammenhenger " + g.counter);

    }
}
