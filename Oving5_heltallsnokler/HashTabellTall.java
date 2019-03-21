package Oving5_heltallsnokler;

import java.util.Date;
import java.util.HashMap;

public class HashTabellTall {
    int lengde;
    int[] tabell;

    public HashTabellTall(int lengde){
        this.lengde = lengde;
        tabell = new int[lengde];
    }

    public int h1 (int nmbr){
        return nmbr % lengde;
    }

    public int h2 (int nmbr){
        return nmbr % (lengde-1) + 1;
    }

    public void addElement(int nmbr){
        int h1 = h1(nmbr);
        int h2 = h2(nmbr);
        if (tabell[h1] == 0){
            tabell[h1] = nmbr;
        } else {
            if (tabell[h2] == 0){
                tabell[h2] = nmbr;
            } else {
                tabell[findClosest(h2)] = nmbr;
            }
        }
    }

    private int findClosest(int tall){
        tall++;
        while (tabell[tall] != 0){
            if (tall == lengde-1){
                tall = 0;
            } else {
                tall++;
            }
        }
        return tall;
    }

    private void fill(int[] inn){
        for (int i = 0; i < inn.length; i++){
            addElement(inn[i]);
        }
    }

    public static void main(String[] args){
        int size = 6395869;
        HashTabellTall htt = new HashTabellTall(size);

        int[] tall = new int[5000000];
        for (int i = 0; i < tall.length; i++){
            tall[i] = (int)Math.floor(Math.random()*size*10);
        }

        Date start = new Date();
        htt.fill(tall);
        Date slutt = new Date();

        System.out.println("Tid ms egen: " + (slutt.getTime()-start.getTime()));


        HashMap<Integer, Integer> hm = new HashMap<>();

        start = new Date();
        for (int i = 0; i < tall.length; i++){
            hm.put(tall[i], tall[i]);
        }
        slutt = new Date();
        System.out.println("Tid ms java: " + (slutt.getTime()-start.getTime()));
    }
}
