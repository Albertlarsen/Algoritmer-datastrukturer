package Oving3_ver2;

import java.util.Date;
import java.util.Random;

public class Test {

    int partition(int arr[], int left, int right) {

        int i = left, j = right;
        int tmp;
        int pivot = arr[(left + right) / 2];
        while (i <= j) {
            while (arr[i] < pivot)
                i++;
            while (arr[j] > pivot)
                j--;
            if (i <= j) {
                tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
                j--;
            }
        }
        ;
        return i;
    }


    void quickSort(int arr[], int left, int right) {
        int index = partition(arr, left, right);
        if (left < index - 1)
            quickSort(arr, left, index - 1);
        if (index < right)
            quickSort(arr, index, right);
    }


    public void dualPivotSort(int[] arr, int lowIndex, int highIndex) {
        if (highIndex <= lowIndex) {
            return;
        }
        if (arr[lowIndex] > arr[highIndex]) {
            swap(arr, lowIndex, highIndex);
        }
        int pivot1 = arr[lowIndex];
        int pivot2 = arr[highIndex];
        int lt = lowIndex + 1;
        int gt = highIndex - 1;
        int i = lowIndex + 1;
        while (i <= gt) {
            if (arr[i] < pivot1) {
                swap(arr, i, lt);
                lt++;
                i++;
            } else if (arr[i] > pivot2) {
                swap(arr, i, gt);
                gt--;
            } else {
                i++;
            }
        }

        swap(arr, lowIndex, lt - 1);
        swap(arr, gt + 1, highIndex);
        lt--;
        gt++;
        dualPivotSort(arr, lowIndex, lt - 1);
        dualPivotSort(arr, lt + 1, gt - 1);
        dualPivotSort(arr, gt + 1, highIndex);
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    public void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(" " + arr[i]);
        }
        System.out.println();
    }


    int[] genArray(int length) {
        Random rand = new Random();
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = rand.nextInt(1000000000) + 1;
        }
        return arr;
    }

    /*int[] genSortedArray(int length){

        int[] arr = new int[length];
        for(int i=0; i<length; i++){
            arr[i]= i+1;
        }
        return arr;
    }*/

    int[] genDuplicateArray(int length) {
        Random rand = new Random();
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            if (i % 2 == 0) {
                arr[i] = rand.nextInt(100000000) + 1;
            } else {
                arr[i] = 2;
            }
        }
        return arr;
    }

    public boolean isSorted(int[] arr) {
        boolean sorted = true;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                sorted = false;
            }
        }
        if (sorted) {
            return sorted;
        }
        return false;
    }
    public void heapSort(int arr[])
    {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        for (int i=n-1; i>=0; i--)
        {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }
    }


    void heapify(int arr[], int n, int i) {
        int largest = i;
        int l = 2*i + 1;
        int r = 2*i + 2;

        if (l < n && arr[l] > arr[largest])
            largest = l;

        if (r < n && arr[r] > arr[largest])
            largest = r;

        if (largest != i)
        {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            heapify(arr, n, largest);
        }
    }

    /* A utility function to print array of size n */


    // Driver program
    public static void main(String args[]) {

        Test ob = new Test();
        int arr[] = ob.genArray(1000000);
        /*int sortArr[] = ob.genArray(10000000);
        ob.quickSort(sortArr, 0, sortArr.length - 1);*/
        // int arrTwo[] = ob.genArray(10000000);
        int dupArr[] = ob.genDuplicateArray(100000);
        // int dupArrTwo[] = ob.genDuplicateArray(10000000);
        int n = arr.length;
        int k = dupArr.length -1;
        //ob.quickSort(arr, 0, n-1);
        //ob.dualPivotSort(arr, 0, n-1);
        Date start = new Date();
        int rounds = 0;
        double time;
        Date end;


        do {
           //ob.quickSort(arr, 0, n - 1);
            //ob.quickSort(arr, 0, n - 1);
            //ob.quickSort(dupArr, 0, k-1);

            //ob.dualPivotSort(arr, 0, n-1);
            ob.dualPivotSort(dupArr, 0, k-1);

            //ob.heapSort(arr);
            //ob.heapSort(dupArr);
            //ob.heapSort(arr);
            //ob.heapSort(arr);
            end = new Date();
            ++rounds;
        } while (end.getTime() - start.getTime() < 1000);
        time = (double)
                (end.getTime() - start.getTime()) / rounds;
        if (ob.isSorted(arr) || ob.isSorted(dupArr)) {
            System.out.println("Sorted");
        } else {
            System.out.println("Not Sorted");
        }
        System.out.println("ms : " + time);
    }

}