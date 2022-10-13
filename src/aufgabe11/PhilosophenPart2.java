package aufgabe11;

import java.util.concurrent.Semaphore;

public class PhilosophenPart2 extends Thread{
    public static void main(String[] args) {
        //jr

    }

    int id;

    public static Semaphore[] HungrigePhilosophen = new Semaphore[5];

    public PhilosophenPart2(int id)
    {
        this.id = id;
    }
}
