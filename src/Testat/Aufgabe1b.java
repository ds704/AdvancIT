package Testat;

import java.util.concurrent.Semaphore;

public class Aufgabe1b extends Thread {

    static Semaphore WarteschlangeSemaphore[] = new Semaphore[2];
    static Aufgabe1b LoksObjektArray[] = new Aufgabe1b[2];
    private int id;


    public static void main(String[] args) { //Semaphore werden erzeugt Zug 0 Darf laut Aufgabenstellung als erster fahren desswgen Semaphore -> 1
        WarteschlangeSemaphore[0] = new Semaphore(1, true);
        WarteschlangeSemaphore[1] = new Semaphore(0, true);
        for (int i = 0; i < 2; i++) {
            LoksObjektArray[i] = new Aufgabe1b(i);

        }

        for (int i = 0; i < 2; i++) {
            LoksObjektArray[i].start();
        }

    }

    @Override
    public void run() {
        try { //hier muss gescand werden um welche Lok es sich handelt, da Aufgabenstellung unterschiedliche Methoden für die Züge fordert
            while (id == 0) {
                Thread.sleep(300); // Zug verlangsamen
                enterLok0();
                exitLok0();
            }
            while (id == 1) {
                enterLok1();
                exitLok1();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Aufgabe1b(int id) {
        this.id = id;
    }

    //Methoden für die Loks, sodass der Semaphore aufgerufen wird. Grund für diese Methoden ist die Aufgabenstellung mit dne individuellen Methoden
    private void enterLok0() {
        enterMutex();
    }

    private void exitLok0() {
        exitMutex();
    }

    private void enterLok1() {
        enterMutex();
    }

    private void exitLok1() {
        exitMutex();
    }

    private void enterMutex() {
        //hier wird sich an die Weiche angestellt, entweder ist die We  iche bereits umgestellt, dann kann der Zug durchfahren. Wenn nicht muss er warten
        try {
            WarteschlangeSemaphore[id].acquire();
            System.out.println("Der Zug nummer: " + id + " fährt jetzt");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void exitMutex()
    {
        //hier wird die Weiche umgestellt, dass der ander Zug durchfahren kann
        int andererZug = id - 1;
        if (id == 0) {
            andererZug = 1;
        }
        WarteschlangeSemaphore[andererZug].release();
    }
}
