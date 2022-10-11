package aufgabe11;

import java.util.concurrent.Semaphore;
import java.util.concurrent.Semaphore;

public class Philosoph extends Thread{
    public static Semaphore[] GabelSemaphore = new Semaphore[5];
    public static Thread[] Threads = new Thread[5];

    public static int Deadlockcounter =0;
    private int id;

    public Philosoph (int id)
    {
        this.id = id;
    }

    public static void main(String[] args) {
        for(int i= 0; i<5; i++) {
            GabelSemaphore[i] = new Semaphore(1,true);
        }
        for(int i = 0; i<5; i++)
        {
            Threads[i] = new Philosoph(i);
        }

        try
        {
            for(int i = 0; i<5; i++)
            {
                Threads[i].start();
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }



    }


    @Override
    public void run() {
        try {
        while(true) {
            if(id > 4) {
                GabelSemaphore[id].acquire();
                GabelSemaphore[(id + 1) % 5].acquire();
                System.out.println("Der Thread: " + Thread.currentThread() + " isst gerade!");
                Deadlockcounter++;
                System.out.println("Es gab schon " + Deadlockcounter + " Gerichte");
                GabelSemaphore[id].release();
                GabelSemaphore[(id + 1) % 5].release();
            } else if(id == 4) {
                GabelSemaphore[(id + 1) % 5].acquire();
                GabelSemaphore[id].acquire();
                System.out.println("Der Thread: " + Thread.currentThread() + " isst gerade!");
                Deadlockcounter++;
                System.out.println("Es gab schon " + Deadlockcounter + " Gerichte");
                GabelSemaphore[id].release();
                GabelSemaphore[(id + 1) % 5].release();
            }
        }
        } catch (Exception e) {
            System.out.println(e) ;
        }

    }
}
