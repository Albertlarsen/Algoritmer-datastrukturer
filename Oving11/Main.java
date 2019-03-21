package Oving11;

public class Main {

    private static char[] gjorOmTilCharArray(String input) {
        char[] temp = new char[input.length()];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = input.charAt(i);
        }
        return temp;
    }
    public static void main(String[] args) {
        System.out.println("Automat 1: med alfabetet {0, 1}");
        char[] inputalfabet = {'0', '1'};
        int[] aksepttilstand = {2};
        int[][] nesteTilstand = {{1, 3}, {1, 2}, {2, 3}, {3, 3}};

        Automat automat = new Automat(inputalfabet, aksepttilstand, nesteTilstand);

        String input = "010";
        System.out.println("010: " + automat.sjekkInput(gjorOmTilCharArray(input)));

        String input2 = "111";
        System.out.println("111: "+ automat.sjekkInput(gjorOmTilCharArray(input2)));

        String input3 = "010110";
        System.out.println("010110: " + automat.sjekkInput(gjorOmTilCharArray(input3)));

        //Automat 2: med alfabetet {a, b}
        System.out.println("\nAutomat 2: med alfabetet {a, b}");
        char[] inputalfabet2 = {'a', 'b'};
        int[] aksepttilstand2 = {2};
        int[][] nesteTilstand2 = {{1, 3}, {4, 2}, {2, 2}, {2, 4}, {4, 4}};

        Automat automat2 = new Automat(inputalfabet2, aksepttilstand2, nesteTilstand2);

        String input21 = "abbb";
        System.out.println("abbb: " + automat2.sjekkInput(gjorOmTilCharArray(input21)));

        String input22 = "aaab";
        System.out.println("aaab: " + automat2.sjekkInput(gjorOmTilCharArray(input22)));

        String input23 = "babab";
        System.out.println("babab: " + automat2.sjekkInput(gjorOmTilCharArray(input23)));


    }

}
