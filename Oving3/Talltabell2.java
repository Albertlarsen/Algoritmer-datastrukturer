package Oving3;

public class Talltabell2 {
    /*import java.io.*;

    class GFG {



        static void sort(int[] arr, int lowIndex, int highIndex) {

            if(highIndex <= lowIndex) {
                return;
            }

            if(arr[lowIndex] > arr[highIndex]) {
                swap(arr, lowIndex, highIndex);
            }

            int pivot1 = arr[lowIndex];
            int pivot2 = arr[highIndex];

            int lt = lowIndex + 1;
            int gt = highIndex - 1;
            int i = lowIndex + 1;

            while(i <= gt) {
                if(arr[i] < pivot1) {
                    swap(arr, i, lt);
                    lt++;
                    i++;
                } else if(arr[i] > pivot2) {
                    swap(arr, i, gt);
                    gt--;
                } else {
                    i++;
                }
            }

            swap(arr, lowIndex, lt-1);
            swap(arr, gt+1, highIndex);

            lt--;
            gt++;

            sort(arr, lowIndex, lt-1);
            sort(arr, lt+1, gt-1);
            sort(arr, gt+1, highIndex);

        }

        static void swap(int[] arr, int i, int j) {
            int temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;
        }

        static void printArray(int[] arr) {
            for(int i=0; i< arr.length; i++) {
                System.out.print(" " + arr[i]);
            }
            System.out.println();
        }
    }

    public static void main (String[] args) {
        int arr[] = new int [100];
        printArray(arr);

        System.out.println("After sorting using dual pivot quick sort");

        sort(arr, 0, arr.length-1);

        printArray(arr);
    }*/
}
