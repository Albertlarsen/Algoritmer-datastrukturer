package Oving2;

import java.util.Date;
import java.util.Random;

public class Alg {

    private static int[] anArray;



    public static double randomFill() {

        Random rand = new Random();
        int randomNum = rand.nextInt();
        return randomNum;
    }

    static void oppg(int[] arr1) {

        int[] arr2 = new int[arr1.length - 1];
        int sum = 0;
        for (int i = 0; i < arr1.length; i++) {
            if (i == arr1.length - 1) {
                break;
            } else {
                sum += arr1[i];
                arr2[i] = sum;
                System.out.println(arr2[i]);
            }
        }
        int kjop = 0;
        int salg = 0;
        int maks = -1000000000;
        for (int i = 1; i < arr2.length; i++) {
            for (int j = i; j < arr2.length; j++) {
                int profitt = arr2[j] - arr2[i];
                if (profitt > maks) {
                    kjop = i + 1;
                    System.out.println(kjop);
                    salg = j + 1;
                    System.out.println("salg" + salg);
                    maks = profitt;
                    System.out.println("prft " + profitt);

                }
            }
        }
        System.out.println("Kjøper aksjen når den er " + kjop + " Selger når den er " + salg);
    }

    /*public static void alg2(int arr[]) {
        int i = 0, kjop = 0, selg = 0, min = 0, profitt = 0;

        for (i = 0; i < arr.length; i++) {
            if (arr[i] < arr[min])
                min = i;
            else if (arr[i] - arr[min] > profitt) {
                kjop = min;
                selg = i;
                profitt = arr[i] - arr[min];
            }

        }

        System.out.println("Kjøper når aksjen er " + arr[kjop] + " Selger når aksjen er " + arr[selg]);

    }*/


    public static void main(String[] args) {

        int[] arr3 = {-1, 2, -4, 5, -2, -4};

        /*int[] arr3 = new int[10];
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            arr3[i] = rand.nextInt(10);
        }*/

        oppg(arr3);


        /*Date start = new Date();
        int runder = 0;
        double tid;
        Date slutt;
        do {
            oppg(arr3);
            slutt = new Date();
            ++runder;
        } while (slutt.getTime() - start.getTime() < 1000);
        tid = (double)
                (slutt.getTime() - start.getTime()) / runder;
        System.out.println("Millisekund pr. runde:" + tid);
        */




    }

    public static class oppg223 {
        public static double oppg1(double x, int n) {
                if (n == 0) return 1;
                if (n == 1) return x;
                if (n == 2) return x * x;
                if (n % 2 == 0) return oppg1(oppg1(x, n / 2), 2);
                return x * (oppg1(x, n - 1));
        }



        public static void main(String[] args) {

            System.out.println(oppg1(1.001, 5000));

            Date start = new Date();
            int runder = 0;
            double tid;
            Date slutt;
            do {
                oppg1(1.001, 5000);
                slutt = new Date();
                ++runder;
            } while (slutt.getTime() - start.getTime() < 1000);
            tid = (double)
                    (slutt.getTime() - start.getTime()) / runder;
            System.out.println("Millisekund pr. runde:" + tid);
        }
    }

    public static class Oving2 {

        //oppg 2.1-1 1
        public static int eksp(int a, int n) {

            if (n==0) { return 1; }

            else {
                a *= eksp(a, n-1);
                return a;
            }
        }


        public static void main(String[] args){

            System.out.println("oppg 2.1-1 = " + eksp(5, 3));


            Date start = new Date();
            int runder = 0;
            double tid;
            Date slutt;
            do {
                eksp(5, 2);
                slutt = new Date();
                ++runder;
            } while (slutt.getTime() - start.getTime() < 1000);
            tid = (double)
                    (slutt.getTime() - start.getTime()) / runder;
            System.out.println("Millisekund pr. runde:" + tid);
        }
    }

    public static class øving2oppg3 {
        public static void main(String[] args) {
            Date start = new Date();
            int runder = 0;
            double tid;
            Date slutt;
            do {
                Math.pow(1.001, 5000);
                slutt = new Date();
                ++runder;
            } while (slutt.getTime() - start.getTime() < 1000);
            tid = (double)
                    (slutt.getTime() - start.getTime()) / runder;
            System.out.println("Millisekund pr. runde:" + tid);
        }
    }
}
