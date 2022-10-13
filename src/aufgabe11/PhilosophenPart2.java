package aufgabe11;

import java.util.concurrent.Semaphore;

public class PhilosophenPart2 extends Thread{
    public static void main(String[] args) {
        //jr
        for(int i = 0; i < 5; i++)
        {
            PhilosphenObejekt[i] = new PhilosophenPart2(i);
            PhilosphenWarteschlange[i] = new Semaphore(0, true);
        }
        for(int i = 0; i<5; i++)
        {
            EssendePhilosophen[i] = false;
        }

        for(int i = 0; i<5;i++)
        {
            PhilosphenObejekt[i].start();
        }



    }

    int id;

    static PhilosophenPart2[] PhilosphenObejekt = new PhilosophenPart2[5];

    static Semaphore[] PhilosphenWarteschlange = new Semaphore[5];
    static Semaphore mutex = new Semaphore(1,true);


    public static boolean[] EssendePhilosophen = new boolean[5];

    public static boolean[] wartendePhilisophen = new boolean[5];

    public PhilosophenPart2(int id)
    {
        this.id = id;
    }
    public void essen(int id)
    {
        System.out.println("Der Philosoph mit der Id: " + id +" isst gerade!");
    }

    public void run()
    {
        try
        {
            while(true) {
                //Thread.sleep(int(Math.random() * 1000));

                int linkerNachbar = id - 1;
                int rechterNachbar = id + 1;
                if (id == 0) {
                    linkerNachbar = 4;
                } else if (id == 4) {
                    rechterNachbar = 0;
                }

                mutex.acquire();
                if (EssendePhilosophen[linkerNachbar] == false && EssendePhilosophen[rechterNachbar] == false) {
                    EssendePhilosophen[id] = true;
                    //EssendePhilosophen[rechterNachbar] = true;
                    PhilosphenWarteschlange[id].release();

                } else {
                    wartendePhilisophen[id] = true;
                }
                mutex.release();
                PhilosphenWarteschlange[id].acquire();
                essen(id);
                EssendePhilosophen[rechterNachbar] = false;
                mutex.acquire();

                for (int i = 0; i < 5; i++) {
                    //gJ
                    if (wartendePhilisophen[i] == true) {
                        int linkerNachbarAufrufen = i - 1;
                        int rechterNachbarAufrufen = i + 1;
                        if (i == 0) {
                            linkerNachbarAufrufen = 4;
                        } else if (i == 4) {
                            rechterNachbarAufrufen = 0;
                        }
                        if (EssendePhilosophen[linkerNachbarAufrufen] == false & EssendePhilosophen[rechterNachbarAufrufen] == false) {
                            PhilosphenWarteschlange[i].release();
                            EssendePhilosophen[rechterNachbar] = true;
                        }

                    }
                }
                mutex.release();

            }




        }catch(Exception e)
        {
            e.printStackTrace();
        }

    }
}


