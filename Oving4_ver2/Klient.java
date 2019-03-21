package Oving4_ver2;

import java.util.Scanner;

public class Klient {
    public static void main(String[] args){
        BinarTre run = new BinarTre();

        boolean running = true;
        while(running == true){
            System.out.println("Du har følgende valg");
            System.out.println("[0] For å legge til i treet");
            System.out.println("[1] For å vise treet");
            Scanner select = new Scanner(System.in);
            int in = select.nextInt();
            switch(in){
                case 0:
                    System.out.println("Skriv inn ord for å legge til i treet");
                    String adding = select.next();
                    run.add(adding);
                    System.out.println(adding + " har blitt lagt til");
                    break;
                case 1:
                    run.printInorder();
                    break;
            }
        }
    }
}