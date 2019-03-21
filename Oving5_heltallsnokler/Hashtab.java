package Oving5_heltallsnokler;

import java.util.*;

import static java.lang.StrictMath.sqrt;


public class Hashtab{
    private static int TABLE_SIZE = 6000011;
    private static Verdi[] table;
    private static int size;
    private int primeSize;

    public Hashtab(){
        size = 0;
        table = new Verdi[TABLE_SIZE];
        for (int i = 0; i < TABLE_SIZE; i++)
            table[i] = null;
        primeSize = getPrime();
    }

    public int getPrime(){
        for (int i = TABLE_SIZE - 1; i >= 1; i--){
            int fact = 0;
            for (int j = 2; j <= (int) Math.sqrt(i); j++)
                if (i % j == 0)
                    fact++;
            if (fact == 0)
                return i;
        }
        return 3;
    }


    public void insert(String key, int value)
    {
        if (size == TABLE_SIZE){
            System.out.println("Table full");
            return;
        }
        int hash1 = myhash1(key);
        int hash2 = myhash2(key);
        while (table[hash1] != null){
            hash1 += hash2;
            hash1 %= TABLE_SIZE;
        }
        table[hash1] = new Verdi(key, value);
        size++;
    }

    private int myhashl(String navn){
        int m = TABLE_SIZE;
        double A = (sqrt(5.00)-1)/2;
        int k = Integer.parseInt(navn);
        double kA = A*k-Math.floor(A*k);
        int hk = m*(int)kA;
        return hk;
    }
    public int myHash2(String string){
        int key = -1;
        for (int i = 0; i < string.length(); i++){
            char character = string.charAt(i);
            key += (int)character * (7^i);
        }
        key = key % TABLE_SIZE;
        return key;
    }
                                                                                                                                                                                                                                                                                                                                                                                                    private int myhash1(String x ){int hashVal = x.hashCode( );hashVal %= TABLE_SIZE; if (hashVal < 0)hashVal += TABLE_SIZE;return hashVal;} private int myhash2(String x ) {int hashVal = x.hashCode( );hashVal %= TABLE_SIZE;if (hashVal < 0) hashVal += TABLE_SIZE;return primeSize - hashVal % primeSize; }
    public void printHashTable() {
        System.out.println("\nHash Table");
        for (int i = 0; i < TABLE_SIZE; i++)
            if (table[i] != null)
                System.out.println(table[i].key +" "+table[i].value);
    }

    public static void main(String[] args){
        Random rand = new Random();
        HashMap<String, Integer> hM = new HashMap<>();

        Hashtab hT = new Hashtab();

        ArrayList<String> list = new ArrayList<String>();
        for (int i=0; i<5000000; i++){
            String k = Integer.toString(new Integer(rand.nextInt(50000000)+1));
            list.add(k);
        }
                                                                                                                                                                                                                                                hT.myhashl("1234"); hT.myHash2("1234");
        Date start = new Date();
        int runder = 0;
        double tid;
        Date slutt;

    do {
            for(int i = 0; i < list.size(); i++){
                hT.insert(list.get(i), Integer.parseInt(list.get(i)));
            }/*
            for(int i = 0; i < list.size(); i++){
                hM.put(list.get(i), Integer.parseInt(list.get(i)));
                String res = "Min egen hashmetode gir ";
            }*/
            slutt = new Date();
            ++runder;
        } while (slutt.getTime() - start.getTime() < 1000);
        tid = (double)
                (slutt.getTime() - start.getTime()) / runder;
        System.out.println("millisekund pr. runde:" + tid);
    }



}


