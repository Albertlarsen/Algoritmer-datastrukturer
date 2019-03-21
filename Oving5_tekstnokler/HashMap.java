package Oving5_tekstnokler;

import static java.lang.StrictMath.sqrt;

public class HashMap{
    private static int kollisjon = 0;
    private static int tableSize = 128;
    private static HashEntry[] table;

    public HashMap() {
        table = new HashEntry[tableSize];
        for (int i = 0; i < tableSize; i++)
            table[i] = null;
    }

    private static int calcHashCode(String key){
        int m = tableSize;
        double A = (sqrt(5.00)-1)/2;
        double k = convert(key);
        double kA = A*k-Math.floor(A*k);
        int hk = m*(int)kA;
        return hk;
    }

    public boolean get1(String key){
        boolean bool = false;
        for(int i = 0; i < tableSize; i++){
            if(table[i]!=null) {
                if (key.equals(table[i].getKey())) {
                    bool =true;
                    break;
                }
            }
        }
        System.out.println(bool);
        return bool;
    }


    public static void put(String key){
        int hash = (convert(key)%tableSize);
        if(table[hash]==null) {
            table[hash] = new HashEntry(key, calcHashCode(key));
        }
        else{
            kollisjon++;
            System.out.println(key + " kolliderer med " + table[hash].getKey());
            HashEntry entry = table[hash];
            while(entry.getNext() != null && entry.getKey() != key) {
                entry = entry.getNext();

                if (entry.getKey() == key) {
                    entry.setValue(convert(key));
                } else {
                    entry.setNext(new HashEntry(key, convert(key)));
                }
            }
        }
    }
    public static int convert(String navn) {
        int a = 1;
        int b = 0;
        for (int i = 0; i < navn.length(); i++) {
            char character = navn.charAt(i);
            b += character * a;
            a++;
        }
        return b;
    }



    public static void main(String[] args){
        String []tab = {"Almås Annabelle Solem", "Andersson William", "Andersson Vegard", "Andresen Sebastian Martin", "Aune Jostein Johansen", "Axell Christian", "Daniel Larsen",
                "Bakhmadov Magomed Khatujevitsj", "Braathen Mathias", "Bui Aria Thuy Dung", "Bø Halvor Fladsrud", "Christiansen Herman", "Dahl Magnus", "Dalseth Christian Mathias", "Debik Karol Jerzy",
                "Elvemo Sebastian Aanesland", "Fossland Ole-Henrik", "Frantzen Odd-Erik", "Fridheim Marius", "Fylling Johan-Henrik", "Garmann Ingelin", "Gram Hans-Jeiger", "Habeeb Khaled Mohammad",
                "Halvarsson Kevin Mentzoni", "Haugum Terje", "Helgeland Kevin Andre", "Hestnes Håkon Dalen", "Hjelle Sara", "Holt Eyvind Nikolai", "Hynne Sigurd", "Iversen Anders Hallem",
                "Jacobsen Sigurd Lø¸ite", "Jacobsen William Chakroun", "Johansen Aleksander", "Johansen Kristaver Birkeland", "Johansen Eivind Alfsvåg", "Kampenhå y Kristian", "Knutsen Yair Day",
                "Knutsen Mathias", "Koteng Markus Thomassen", "Kristoffersen Jonas", "Larsen Knut Johan", "Larsen Albert Ohrem", "Larsson, Øivind Haugerø", "Lervik Eivind Hestnes",
                "Li Jingyi", "Lilleeng Simon", "Martinsen Magnus Revheim", "Martinsen Herman Ryen", "Mediå Fredrik", "Mellemseter Michael", "Midttun Jonathan", "Moan Martin Andreas", "Monsen Fredrik",
                "Nicolausson Sander", "Nordseth Morten Nyang", "Nygård Marius", "Nystuen Ådne", "Opsahl Elisabeth Marie", "Paulshus Sindre Haugland", "Rad Saadat Ilia", "Ramberg Håvard Hammer", "Roll Erling",
                "Rondestvedt Trond Jacob", "Rø stgård Kim Richard", "Sandnes Martin", "Siiri Anette Olli", "Skaug Oscar Tideman Borgerud", "Stavseng Ådne Eide", "Strand Snorre Kristoffer", "Strø mhylden Ben Oscar",
                "Sundø y Erlend", "Søther Ane", "Sørlie Lars", "Teiler-Johnsen Mari", "Tengs Simen Sølevik", "Thomassen Sindre", "Thorkildsen Patrick", "Timdal Eivind Rui", "Tokvam Balder",
                "Tran Quan Nguyen", "Tverfjell Julie Isabelle Malmedal", "Ullah Mona", "Valen Ruben Solvang", "Valstadsve Øyvind", "Vatle Jan-Marius", "Vedøy Rune", "Vesterlid Ørjan Bostad",
                "Wangensteen Kim Anners", "Wichstrøm Brage", "Worren Magne", "Østtveit Bjørnar", "Øverland Sveinung", "Øvsthus Vebjørn Hansen", "Ådnanes Stian", "Aasvestad Jørgen"};

            HashMap hm = new HashMap();
            for(int i = 0; i < tab.length; i++){
                put(tab[i]);
            }
                                                                                                                                                                                                                                                                                                            calcHashCode("Almås Annabelle Solem");
            double m = hm.tableSize;
            double n = tab.length;
            double lastFaktor = n/m;
            double gjK = hm.kollisjon / n;
            System.out.println("\n"+"Antall kollisjoner: "+ hm.kollisjon + "\n"+ " Lastfaktor: " + lastFaktor +
                    "\n" + " gj.snitt ant. Kollisjoner pr. pers: " + gjK);

            hm.get1("Almås Annabelle Solem");

    }

}
