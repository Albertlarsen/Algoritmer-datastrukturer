package Oving12;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class LempelZivKompresjonv2 {
    private String filInn = "";
    private String filUt = "";
    private final int MIN_LENGDE = 4;
    private final int MAX_DIST_VERDI = 127*1000;
    private byte[] bytesFraFil = new byte[0];
    private byte[] komprimertBuffer = new byte[0];

    public LempelZivKompresjonv2(String filInn, String filUt) {
        this.filInn = filInn;
        this.filUt = filUt;
        lesFil();
    }

    private void lesFil() {
        try {
            bytesFraFil = Files.readAllBytes(Paths.get(filInn));
            komprimertBuffer = new byte[bytesFraFil.length];
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void skrivFil() {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(filUt)));
            dataOutputStream.write(komprimertBuffer);
            dataOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Finner beste match til et ord, beste match er det første ordet som er like langt
    //Metoden finner posisjonen til dette ordet
    private int finnBesteMatch(ArrayList<Byte> bytesToMatch, int startIndex) {
        if (startIndex < MIN_LENGDE) { //Dette skal ikke skje
            return -3;
        }
        //Sjekker hvor langt en kan gå tilbake å søke etter lignenede bytes
        //MAXIMUM_DISTANCE_BACK = 127
        int startBackIndex = startIndex - MAX_DIST_VERDI;
        int distanceBack = MAX_DIST_VERDI;
        if (startBackIndex < 0) {
            startBackIndex = 0;
            distanceBack = startIndex;
        }

        for (int i = startBackIndex; i <= (distanceBack - MIN_LENGDE); i++) { //Går for hver bokstav eller så langt som mulig fra startVerdi og opp til start-verdi
            boolean isFound = true;
            for (int j = i, k = 0; k < bytesToMatch.size(); j++, k++) { //Sjekker om bytes matcher for hvert ord
                if (tempBlock[j] != bytesToMatch.get(k)) { //Sjekker hver bokstav i hvert ord
                    isFound = false;
                    break;
                }
            }

            if (isFound) {
                return i; //representerer posisjonen til ordet
            }
        }

        return -2;
    }

    private byte[] tempBlock;
    private int startHent = 0;
    private int bytesIgjen;

    private void hentBlokk(int bytesIgjen) {
        if (bytesIgjen < MAX_DIST_VERDI) { //dersom det er mindre enn 127 bytes igjen av filen, lages det en tempBlock bytes-array som har lengde etter hvor mange bytes det er igjen
            tempBlock = new byte[bytesIgjen];
            for (int i = 0; i < bytesIgjen; i++, startHent++) {
                tempBlock[i] = bytesFraFil[startHent];
            }
            this.bytesIgjen = 0;
        } else {
            tempBlock = new byte[MAX_DIST_VERDI]; //tempBlock får lengde 127 og verdier på samme måte
            for (int i = 0; i < MAX_DIST_VERDI; i++, startHent++) {
                tempBlock[i] = bytesFraFil[startHent];
            }
            this.bytesIgjen -= MAX_DIST_VERDI; //bytesigjen Får distanse -=127 for hver gang bytesIgjen >= MAX_DIST_VERDI
        }
    }

    public void komprimerFil() {
        bytesIgjen = bytesFraFil.length;
        int bufferIndex = 0;
        while (bytesIgjen != 0) {
            hentBlokk(bytesIgjen);
            int byteIndex = 0; //Hvor neste byte skal settes inn [-10], [10, 10] som beskrevet i leksjon
            boolean funnetKomprimering = false;
            int doneBytes = 0;
            int komprimeringsIndex = -2;
            for (int i = 0; i < bytesFraFil.length; i++) { //For hver bokstav i hele filen
                ArrayList<Byte> currentBytes = new ArrayList<Byte>();
                funnetKomprimering = false;
                int komprimeringsLengde = -2;
                int ukomprimerteBytes = -2;
                int startKomprimeringsIndex = 0;
                for (int j = i; j < bytesFraFil.length; j++) { //For i, så går den i en løkke frammover slik at vi får ulike ord: ABCDEF blir til ABC (i=0), og så BCD (i=1) osv...
                    currentBytes.add(bytesFraFil[j]); //legger til bytes fra fil i currentBytes-arraylista
                    //Under sjekkes det om noen bytes matcher, ordene må være lengre enn minimumslengden
                    if ((currentBytes.size() >= MIN_LENGDE) && (i >= MIN_LENGDE) && (bytesFraFil.length - i >= MIN_LENGDE)) {

                        int komprimeringsPlass = finnBesteMatch(currentBytes, i); //blir satt til startbackIndexen dersom man finner en match eller -2 dersom det ikke finnes
                        if (komprimeringsPlass >= 0) {
                            funnetKomprimering = true;
                            komprimeringsIndex = i;
                            startKomprimeringsIndex = komprimeringsPlass; //setter indexen til det som ble funnet i finnBesteMatch()
                            komprimeringsLengde = currentBytes.size();
                        } else {
                            break;
                        }
                    }
                }
                if (funnetKomprimering) {
                    int ukomprimert = komprimeringsIndex - doneBytes; //komprimeringsIndex - lastKomprimeringsIndex
                    komprimertBuffer[bufferIndex] = (byte) -ukomprimert; //bufferarrayet blir satt til negativ byte-verdi for ukomprimert-inten
                    bufferIndex++;

                    for (int b = doneBytes; b < komprimeringsIndex; b++, bufferIndex++) {
                        komprimertBuffer[bufferIndex] = bytesFraFil[b]; //for hver runde økes b og bufferIndex slik at komprimertBuffer-arrayet får verdier etter bytesFraFil arrayet med doneBytes som indeks
                    }

                    int tilbake = komprimeringsIndex - startKomprimeringsIndex;
                    komprimertBuffer[bufferIndex] = (byte) tilbake;
                    bufferIndex++;
                    komprimertBuffer[bufferIndex] = (byte) komprimeringsLengde;
                    bufferIndex++;

                    doneBytes = komprimeringsIndex + komprimeringsLengde;
                    i += komprimeringsLengde; //= komprimeringsIndex
                }
            }
            int ukomprimert = bytesFraFil.length - doneBytes; //komprimeringsIndex - doneBytes; //komprimeringsIndex - lastKomprimeringsIndex
            komprimertBuffer[bufferIndex] = (byte) -ukomprimert;
            bufferIndex++;
            for (int b = doneBytes; b < bytesFraFil.length; b++, bufferIndex++) {//for å gi verdi til ordene som ikke ble komprimert pga. ingen match
                komprimertBuffer[bufferIndex] = bytesFraFil[b];
            }

        }
        byte[] buffer = komprimertBuffer;
        fixEmptyBufferBytes(buffer, bufferIndex);
        skrivFil();
    }

    private void fixEmptyBufferBytes(byte[] buffer, int bufferLength) {//fikser buffer-arrayet der det er tomme bytes
        komprimertBuffer = new byte[bufferLength];
        for (int i = 0; i < bufferLength; i++) {
            komprimertBuffer[i] = buffer[i];
        }
    }

    public void komprimerFil(String filInn, String filUt) {
        this.filInn = filInn;
        this.filUt = filUt;
        komprimerFil();
    }


    public static void main(String[] args) {
        LempelZivKompresjonv2 lempelZivNy = new LempelZivKompresjonv2("Testtest.txt", "TestFilKomprimert.alb");
        lempelZivNy.komprimerFil();
    }
}
