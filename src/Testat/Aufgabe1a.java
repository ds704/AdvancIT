package Testat;

import java.util.concurrent.Semaphore;

public class Aufgabe1a extends Thread{
    public static void main(String[] args) {
        empty = new Semaphore(AnzahlDerProdukte, true);
        full = new Semaphore(0, true);
        for (int i = 0; i < 2; i++) {
                ZugArray[i] = new Aufgabe1a(i);

        }
        for (int i = 0; i < 2; i++) {
            ZugArray[i].start();// = new Aufgabe1a(i);

        }

    }

    private int id;
    static int AnzahlDerProdukte = 1;

    static Aufgabe1a[] ZugArray = new Aufgabe1a[2];

    static Semaphore full ;

    static Semaphore empty;

    public Aufgabe1a(int id)
    {
        this.id = id;
    }

    public void run()
    {
        try
        {
            while(id == 0)
            {
                Thread.sleep(300); // Zug verlangsamen
                enterLok0();
                exitLok0();
            }
            while (id == 1)
            {
                enterLok1();
                exitLok1();
            }


        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void enterLok0()  throws InterruptedException
    {
        empty.acquire();
        System.out.println("Lok 0 fährt in den Kritischen Abschnitt");
    }

    public void exitLok0()  throws InterruptedException
    {
       full.release();
       System.out.println("Lok 0 verlässt den Kritischen Abschnitt");
    }

    public void enterLok1() throws InterruptedException
    {
        full.acquire();
        System.out.println("Lok  1 fährt in den Kritischen Abschnitt");
    }

    public void exitLok1() throws InterruptedException
    {
       empty.release();
       System.out.println("Lok 1 verlässt den Kritischen Abschnitt");
    }
}
