package Oving5_tekstnokler;

public class HashEntry {
    private String key;
    private int value;
    private HashEntry next;

    public HashEntry(String key, int value){
        this.key = key;
        this.value = value;
        this.next = null;
    }

    public int getValue() {
        return value;
    }



    public void setValue(int value) {

        this.value = value;

    }



    public String getKey() {

        return key;

    }



    public HashEntry getNext() {

        return next;

    }



    public void setNext(HashEntry next) {

        this.next = next;

    }


}
