package Oving11;

public class Automat {

    private char[] inputalfabet;
    private int[] aksepttilstand;
    private int[][] nesteTilstand;

    public Automat(char[] inputalfabet, int[] aksepttilstand, int[][] nesteTilstand) {
        this.inputalfabet = inputalfabet;
        this.aksepttilstand = aksepttilstand;
        this.nesteTilstand = nesteTilstand;
    }


    private boolean erIAkseptTilstand(int posisjon) {
        boolean erAksept = false;
        for (int i = 0; i < aksepttilstand.length; i++) {
            if (posisjon == aksepttilstand[i]) {
                erAksept = true;
            }
        }
        return erAksept;
    }

    private int hentAlfabetIndeks(char input) {
        for (int i = 0; i < inputalfabet.length; i++) {
            if (input == inputalfabet[i]) {
                return i;
            }
        }
        return -1;
    }

    public boolean sjekkInput(char[] input) {
        int posisjon = 0;
        for (int i = 0; i < input.length; i++) {
            boolean erFunnet = false;
            for (int j = 0; j < nesteTilstand[posisjon].length; j++) {
                if (hentAlfabetIndeks(input[i]) == j && !erFunnet) {
                    posisjon = nesteTilstand[posisjon][j];
                    erFunnet = true;  //j = nesteTilstand[posisjon].length;
                }
            }
        }
        return erIAkseptTilstand(posisjon);
    }
}