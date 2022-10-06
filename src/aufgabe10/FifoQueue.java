package aufgabe10;

import java.sql.Array;
import java.util.ArrayList;

public class FifoQueue {
    public static void main(String[] args) {
        FifoQueue testList = new FifoQueue();
        testList.put("Hallo");
        testList.put("Daniel");
        testList.put("Das Programm");
        testList.put("Funktioniert :)");

        System.out.println(testList.getLength()+" Es hat funktioniert! zEJ");

        for(int i = 0; i < testList.getLenghOriginal(); i ++)
        {
            String ausgabe = testList.get();
            System.out.println(ausgabe);
        }
        System.out.println(testList.getLength()+" Es hat funktioniert! zEJ");
        System.out.println(testList.getLenghOriginal()+" Es hat funktioniert! zEJ");

    }

    //private String[] hauptspeicherStings = new Array[7];
    //private String[] hauptspeicherStings = new Array[7];
    private int objecteZaehler;
    private ArrayList<String> hauptspeicherStrings = new ArrayList<String>();

    /*public void FifoQueue ()
    {

    }*/


    public void put(String s)
    {
        hauptspeicherStrings.add(s);
        objecteZaehler++;
    }

    public String get()
    {
        String Ausgabe = hauptspeicherStrings.get(0);
        /*String[] zwischenspeicher = new Array[objectZaehler-1];
        for(int i = 1; i < objecteZaehler; i++)
        {
            zwischenspeicher[i-1] = hauptspeicherStrings.get(i);
        }*/
        hauptspeicherStrings.remove(0);
        objecteZaehler--;
        return Ausgabe;

    }

    public int getLength()
    {
        return objecteZaehler;

    }

    public int getLenghOriginal()
    {
        return hauptspeicherStrings.size();
    }
}
