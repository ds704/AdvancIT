package aufgabe09;

import java.util.concurrent.CountDownLatch;

public class TeilaufgabeB extends Thread {

    protected static int anzahlThreads = 2;
    protected static int zahlenArray[] = new int[2097152];
    protected static int bruchteil = zahlenArray.length / anzahlThreads;
    private static long summeEinzelnerThreads[] = new long[128];
    private static long gesamtSumme = 0;
    private int id;
    //private CountDownLatch latch;

    public TeilaufgabeB(int id){//, CountDownLatch latch) {
        this.id = id;
        //this.latch = latch;
    }

    @Override
    public void run() {

        int einzelSumme = 0;
        for(int counter = bruchteil*id; counter < bruchteil*(id+1); counter++){
            einzelSumme += zahlenArray[counter];
        }
        summeEinzelnerThreads[id] = einzelSumme;

        //this.latch.countDown();
    }

    public static long main(String[] args) {

        for(int i = 0; i < zahlenArray.length; i++){
            zahlenArray[i] = 1;
        }

        //CountDownLatch latch = new CountDownLatch(anzahlThreads);
        Thread[] t = new Thread[anzahlThreads];


        for (int i = 0; i < anzahlThreads; i++) {
            t[i] = new TeilaufgabeB(i);//, latch);
        }

        long zeitDavor = System.currentTimeMillis();

        for (int i = 0; i < anzahlThreads; i++) {
            t[i].start();
        }

        try {
            for (int i = 0; i < anzahlThreads; i++) {
                t[i].join();
            }
            //latch.await();
        } catch (Exception e) {
            System.out.println(e) ;
        }

        for (long zwischensumme: summeEinzelnerThreads) {
            gesamtSumme += zwischensumme;
        }

        long zeitDanach = System.currentTimeMillis();

        //System.out.println("Gesamtsumme: " + gesamtSumme);

        long dauer = zeitDanach - zeitDavor;

        //System.out.println("Dauer: " + dauer + " ms");

        gesamtSumme = 0;

        return dauer;
    }

}