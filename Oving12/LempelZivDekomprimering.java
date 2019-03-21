package Oving12;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;


public class LempelZivDekomprimering {
    private byte[] innData;
    private byte[] utData;

    public void lempelZivDekomprimering(String innFil, String utFil) throws IOException {
        int utAntall = 0;

        innData = fraFilTilBytetabell(innFil);
        utData = new byte[innData.length * 10];


        int index = 0;
        while (index < innData.length) {

            /*
            Dersom vi finner negativ byte, skriver vi ut antallet ikke-matchende bytes direkte fra innData.
            Bruker Math.abs for å få absouluttverdien, siden referansen er negativ for ikke-matchende
             */

            byte[] tempArray = {innData[index], innData[index + 1]};
            int indexValue = (int)ByteBuffer.wrap(tempArray).order(ByteOrder.LITTLE_ENDIAN).getShort();
            // System.out.println(indexValue);


            if(indexValue < 0) {
                System.out.println("i = (index +2) = " + (index + 2));
                System.out.println("index + Math.abs(indexValue - 2) = " + (index + Math.abs(indexValue - 2)));
                for(int i = (index + 2); i < (index + Math.abs(indexValue - 2)); i++) {
                    utData[utAntall] = innData[i];
                    utAntall++;
                }
                index += Math.abs(indexValue) + 2;
            }
            /*
            Dersom vi har positiv byte, skrive vi ut referansen for match.
             */
            else {
                System.out.println("utAntall " + utAntall);
                System.out.println("indexValue " + indexValue);
                int startIndex = (utAntall - indexValue);
                for(int i = startIndex; i < (startIndex + innData[index + 2]); i++) {
                    System.out.println("utData.length: " + utData.length);

                    System.out.println(i);
                    System.out.println("start index + inndata: " + innData[index + 2]);
                    System.out.println("er her et problem? " + (startIndex + innData[index + 2]));
                    utData[utAntall] = utData[i];
                    utAntall++;
                }
                index +=3;
            }
        }
        fraBytetabellTilFil(utFil, utData, utAntall);
    }

    /*
    Metoden leser inn bytes fra fil, og legger det deretter inn i en bytestabell.
     */
    public static byte[] fraFilTilBytetabell(String innFil) throws IOException {
        return Files.readAllBytes(Paths.get(innFil));
    }


    /*
    Metoden leser inn bytes fra en bytestabell, og skriver det ut til fil.
     */
    public static void fraBytetabellTilFil(String utFil, byte[] utData, int utAntall) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(utFil);

        if(utAntall < utData.length) {
            utData = clean(utData, utAntall);
        }

        fileOutputStream.write(utData);
        fileOutputStream.close();
    }

    /*
    Metoden fjerner elementer i utData som ikke er i bruk.
     */
    private static byte[] clean(byte[] utData, int utAntall) {
        byte[] nyUtData = new byte[utAntall];
        for(int i = 0; i < utAntall; i++) {
            nyUtData[i] = utData[i];
        }
        return nyUtData;
    }

    public static void main(String[] args) throws IOException {
        LempelZivDekomprimering LZD = new LempelZivDekomprimering();
        String innFil;
        String utFil;

        Date start = new Date();
        int rounds = 0;
        double time;
        Date finished;

        do {
            innFil = "C:\\Users\\Alber\\Documents\\TDAT2005 - Algortimer\\Testtestkomprimert.alb";
            utFil = "C:\\Users\\Alber\\Documents\\TDAT2005 - Algortimer\\Testtestdekomprimert.txt";
            LZD.lempelZivDekomprimering(innFil, utFil);


            finished = new Date();
            rounds++;
        } while (finished.getTime() - start.getTime() < 1000);
        time = (double) (finished.getTime() - start.getTime()) / rounds;
        System.out.println("Dekompremering tid: " + time + " ms"); //Beste tid: 655,0 ms
    }
}