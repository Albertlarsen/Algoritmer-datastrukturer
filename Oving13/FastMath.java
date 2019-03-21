package Oving13;


public class FastMath {

    private static final int precision = 10;

    private static final int length = 360*precision;
    private static final float[] sin = new float[length];
    static {
        for (int i = 0; i<sin.length; i++) {
            sin[i]=(float)Math.sin((i*Math.PI)/(precision*180));
        }
    }

    private static double sinLookup(int a) {
        return a>=0 ? sin[a%(length)] : -sin[-a%(length)];
    }

    public static double sin(double a) {
        return sinLookup((int)(a * precision + 0.5));
    }
    public static double cos(double a) {
        return sinLookup((int)((a+90.0) * precision + 0.5));
    }

    public static double sqrt(double num) {
        //double sqrt = Double.longBitsToDouble( ( ( Double.doubleToLongBits( num )-(1l<<52) )>>1 ) + ( 1l<<61 ) );
        //return (sqrt + num/sqrt)/2.0;
        return Double.longBitsToDouble( ( ( Double.doubleToLongBits( num )-(1l<<52) )>>1 ) + ( 1l<<61 ) );
    }

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            System.out.println("math: " + Math.sin((2.0/100)*i) + " fast: " + sin(i) );
        }


    }
}