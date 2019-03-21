package Oving13;

public class Main {

    public static void main(String[] args) throws Exception{

        GPS gps = new GPS("kanter.txt","interessepkt.txt", "noder.txt");
        System.out.println(gps.getStartLog());
        System.out.println(gps.navigateD("Trondheim", "Stjørdal"));
        System.out.println();
        System.out.println(gps.navigateA("Trondheim", "Stjørdal"));
    }
}