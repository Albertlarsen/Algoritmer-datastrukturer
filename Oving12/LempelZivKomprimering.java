package Oving12;


import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;


public class LempelZivKomprimering {
    private byte[] innData;
    private byte[] utData;
    private final static int BLOKKSTORRELSE = -32_767; // Hvor langt tilbake vi søker etter matcher i bytetabellen.
    private final static int MIN_MATCH = 5; // Minimum matchlengde, slik ikke referanser til mindre matcher tar mer plass en selve matchen.
    private int noMatchTeller = 0; // Teller hvor langt vi ikke har match.
    private int utAntall = 0; // Holder kontroll på antall bytes skrevet til utData[].


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

    public void lempelZivKomprimering(String innFil, String utFil) throws IOException {
        /*
        Nullstiller noMatchTeller og utAntall, slik det er klart til neste fil som skal bli komprimert.
         */
        noMatchTeller = 0;
        utAntall = 0;

        /*
        Skrive inn bytes fra fil til innData[].
        Lager også en utData som skal brukes som mellomlagring før komprimeringen blir skrevet ut til fil igjen.
         */
        innData = fraFilTilBytetabell(innFil);
        utData = new byte[innData.length * 2];


        int index = 0;
        while(index < innData.length) {
            /*
            indexer som hjelper til i leting etter matcher.
            indexBak har kontroll på hvor langt bak vi finner matchen, slik vi kan bruke det i referansen senere.
            maxmatch holder tellingen på største match for hver index vi leter på.
             */
            int indexTemp = index;
            int indexBak = 0;
            int maxmatch = 0;


            /*
            Leter etter største match for gjeldende index, vi leter så lenge indexBakover >= 0 og ikke lengre bak enn 127.
             */
            for(int indexBakover = indexTemp - 1; indexBakover >= 0 && indexBakover > (index + BLOKKSTORRELSE); indexBakover--) {


                if(innData[index] == innData[indexBakover]) {

                    /*
                    Jobber bare med matcher over 4 bytes, da mindre vil bli bortkastet på grunn av referansen tar større plass.
                     */
                    int nyMaxmatch = finnMaxmatch(index, indexBakover);
                    if(nyMaxmatch >= MIN_MATCH) {

                        /*
                        Dersom vi har funnet bytes som ikke matcher tidligere, skriver vi nå ut disse med referasen noMatchTeller
                        som har holdt tellingen på hvor mange som ikke matchet på rad.
                         */
                        if(noMatchTeller < 0) {
                            ikkeMatchTilUtData(noMatchTeller, index);
                        }

                        /*
                        Dersom vi finner en ny matchlengde-rekord, lagrer vi denne i maxmatch.
                        Lagrer også verdien for hvor langt bak vi fant matchen.
                         */
                        if(nyMaxmatch > maxmatch) {
                            maxmatch = nyMaxmatch;
                            indexBak = index - indexBakover;
                        }
                    }
                }
            }




            /*
            Dersom maxmatch > 0, lager vi en referanse [X,Y] i utData[]
            X sier hvor langt bak vi fant matchen, Y hvor lang matchen var.
             */
            if(maxmatch > 0) {

                short indexBakShort = (short) indexBak;
                utData[utAntall] = (byte) (indexBakShort & 0xff);
                utAntall++;
                utData[utAntall] = (byte) ((indexBakShort >>> 8) & 0xff);
                utAntall++;
//
//                short maxmatchShort = (short) maxmatch;
//                utData[utAntall] = (byte) (maxmatchShort & 0xff);
//                utAntall++;
//                utData[utAntall] = (byte) ((maxmatchShort >>> 8) & 0xff);
//                utAntall++;

//
//                utData[utAntall] = (byte) indexBak;
//                utAntall++;
                utData[utAntall] = (byte) maxmatch;
                utAntall++;
                index += maxmatch;
            } else {

                /*
                Dersom noMatchTeller er større enn -127, fortsetter vi å telle ikke-matchende bytes.
                Om den er mindre eller lik -127, lagrer vi ikkeMatchede bytes til utData[] og teller på nytt.
                 */
                if(noMatchTeller > BLOKKSTORRELSE) {
                    noMatchTeller--;
                } else {
                    ikkeMatchTilUtData(noMatchTeller, index);
                    noMatchTeller--;
                }
                index++;
            }
            /*
            Dersom vi har komt til enden av bytestabellen og har ikke-matchende bytes på slutten, håndterer vi dem her.
             */
            if(index == innData.length && noMatchTeller < 0) {
                ikkeMatchTilUtData(noMatchTeller, index);
            }
        }
        /*
        Skriver ut utData[] til fil.
         */
        fraBytetabellTilFil(utFil, utData, utAntall);
    }


    /*
    finnMaxmatch() finner den største mulig macthen på indexer som er lik i metoden over.
     */
    private int finnMaxmatch(int index, int indexBakover){
        int maxmatch = 0;
        int indexTemp = index;

        /*
        Leter så lenge vi ikke går ut av tabellen, og ikke lengre enn 127 plasser.
        Kjører heller ikke over orginal index.
        Vi kjører så lenge vi finne match og kriteriene over er oppfyllt.
         */
        while((indexTemp < innData.length && indexTemp < (index + (Math.abs(BLOKKSTORRELSE))) && indexBakover < index) && innData[indexTemp] == innData[indexBakover] && maxmatch <= 127) {
            maxmatch++;
            indexTemp++;
            indexBakover++;
        }
        return maxmatch;
    }

    /*
    Metoden legger ved referanse på hvor mange ikke-matchende bytes vi har funnet, og skrive de deretter ut til utData[]
     */
    private void ikkeMatchTilUtData(int ingenMatchTeller, int index) {

        /*
        Skriver ut referansen, altså hvor mange ikke-matchende vi fant på rad.
         */
//        utData[utAntall] = (byte) ingenMatchTeller;
//        utAntall++;

        short ingenMatchTellerShort = (short) ingenMatchTeller;
        utData[utAntall] = (byte) (ingenMatchTellerShort & 0xff);
        utAntall++;
        utData[utAntall] = (byte) ((ingenMatchTellerShort >>> 8) & 0xff);
        utAntall++;


        /*
        Skriver ut de ikke-matchende bytesene til utData.
         */
        for(int i = (index - Math.abs(ingenMatchTeller)); i < index; i++) {
            utData[utAntall] = innData[i];
            utAntall++;
        }

        /*
        Nullstiller noMatchTeller, slik vi kan lete på nytt.
         */
        this.noMatchTeller = 0;
    }


    public static void main(String[] args) throws IOException {
        LempelZivKomprimering LZK = new LempelZivKomprimering();
        String innFil;
        String utFil;

        Date start = new Date();
        int rounds = 0;
        double time;
        Date finished;

        do {
            innFil = "C:\\Users\\Alber\\Documents\\TDAT2005 - Algortimer\\Testtest.txt";
            utFil = "C:\\Users\\Alber\\Documents\\TDAT2005 - Algortimer\\Testtestkomprimert.alb";
            LZK.lempelZivKomprimering(innFil, utFil);
/*
            innFil = "src/data/inn/opg12.txt";
            utFil = "src/data/komprimert/opg12Komprimert.txt";
            LZK.lempelZivKomprimering(innFil, utFil);

            innFil = "src/data/inn/opg12.pdf";
            utFil = "src/data/komprimert/opg12Komprimert.pdf";
            LZK.lempelZivKomprimering(innFil, utFil);

            innFil = "src/data/inn/opg12.tex";
            utFil = "src/data/komprimert/opg12Komprimert.tex";
            LZK.lempelZivKomprimering(innFil, utFil);


            innFil = "src/data/inn/diverse.txt";
            utFil = "src/data/komprimert/diverseKomprimert.txt";
            LZK.lempelZivKomprimering(innFil, utFil);
*/
//
//            innFil = "src/data/inn/diverse.pdf";
//            utFil = "src/data/komprimert/diverseKomprimert.pdf";
//            LZK.lempelZivKomprimering(innFil, utFil);
//
//            innFil = "src/data/inn/big.txt";
//            utFil = "src/data/komprimert/bigKomprimert.txt";
//            LZK.lempelZivKomprimering(innFil, utFil);

//            innFil = "src/data/inn/video.mp4";
//            utFil = "src/data/komprimert/videoKomprimert.mp4";
//            LZK.lempelZivKomprimering(innFil, utFil);

//            innFil = "src/data/inn/video2.mp4";
//            utFil = "src/data/komprimert/video2Komprimert.mp4";
//            LZK.lempelZivKomprimering(innFil, utFil);


            finished = new Date();
            rounds++;
        } while (finished.getTime() - start.getTime() < 1000);
        time = (double) (finished.getTime() - start.getTime()) / rounds;
        System.out.println("Komprimering tid: " + time + " ms"); //Beste tid: 15 796,0 ms
    }
}

