package Oving12;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FilBehandler {

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
}

