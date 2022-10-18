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

    static Semaphore mutex = new Semaphore(1, true);

    static Semaphore full ;

    static Semaphore empty;// = new Semaphore(1, true);

    static Semaphore Weichebelegt = new Semaphore(1, true);

    static int ctr = 0;

    static int FreifahrtenZug0 = 0;
    static int FreifahrtenZug1 = 0;

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
                enterLok0();

                //enterLok1();
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

    public void enterLok0()
    {
        empty.acquire();
        Weichebelegt.acquire();




    }

    public void exitLok0()
    {
        Weichebelegt.release();
        /*mutex.acquire();
        ctr++;
        FreifahrtenZug0--;
        FreifahrtenZug1++;
        mutex.release();*/
        full.release();
    }

    public void enterLok1()
    {
        full.acquire();
        Weichebelegt.acquire();
    }

    public void exitLok1()
    {
        Weichebelegt.release();
        mutex.acquire();
        ctr--;
        FreifahrtenZug0++;
        FreifahrtenZug1--;
        mutex.release();
        empty.release();
    }
}
