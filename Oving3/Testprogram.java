package Oving3;

import java.util.Date;

import static Oving3.Talltabell.quicksort;

public class Testprogram {

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
