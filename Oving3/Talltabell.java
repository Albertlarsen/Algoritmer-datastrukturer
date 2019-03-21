package Oving3;

import java.util.Date;
import java.util.Random;

public class Talltabell {
    public static int randomTall() {

        Random rand = new Random();
        int randomNum = rand.nextInt();
        return randomNum;

    }

    public static void randomFill(int[] arr) {
        for(int i = 0; i<arr.length;i++){
            arr[i] = Talltabell.randomTall();
        }
    }

    public static String test(int[] arr){
        String suks ="";
        for(int i = 0; i < arr.length-2; i++){
            if(arr[i+1]>=arr[i]){
                suks = "Suksess";
            }
        }
        return suks;
    }
    public static void quicksort(int []t, int v, int h){
        if(h-v>2){
            int delepos = splitt(t, v, h);
            quicksort(t, v, delepos - 1);
            quicksort(t, delepos + 1, h);
        }//else mediansort(t, v, h);
    }
    private static int mediansort(int []t, int v, int h){
        int m =(v+h)/2;
        if(t[v] > t[m]) bytt(t, v, m);
        if(t[m] > t[h]){
            bytt(t, m, h);
            if(t[v]>t[m]) bytt(t, v, m);
        }
        return m;
    }
    public static void bytt(int []t, int i, int j){
        int k = t[j];
        t[j]= t[i];
        t[i] = k;
    }
    private static int splitt(int []t, int v, int h){
        int iv;
        int ih = 0;
        int m = mediansort(t, v, h);
        int dv = t[m];
        bytt(t, m, h-1);
        for(iv = v; ih == h-1;){
            while (t[++iv] < dv) ;
            while (t[--h] > dv) ;
            if (iv >= ih) break;
            bytt(t, iv, ih);
        }
        bytt(t, iv, h-1);
        return iv;
    }
    static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i]+" ");
        System.out.println();
    }

    public static void main(String[] args){
        int[] arr2 = new int[10000000];
        Talltabell tt = new Talltabell();
        Talltabell.randomFill(arr2);
        int n = arr2.length;


        /*for (int element: arr2) {
            System.out.println(element);
        }

        tt.quicksort(arr2, 0, n-2);
        //Talltabell.printArray(arr2);
        tt.test(arr2);

        for(int i = 0; i < arr2.length-2; i++){
            if(arr2[i+1]>=arr2[i]){
                System.out.println("Suksess");
            }
        }*/
        Date start = new Date();
        int runder = 0;
        double tid;
        Date slutt;
        do {
            tt.quicksort(arr2, 0, arr2.length-2);
            slutt = new Date();
            ++runder;
        } while (slutt.getTime() - start.getTime() < 1000);
        tid = (double)
                (slutt.getTime() - start.getTime()) / runder;
        System.out.println("Millisekund pr. runde:" + tid);


    }
}
