package aufgabe12zEJ;

public class Philosophen extends Thread{

    private int id;
    static Philosophen PhilosophenArray[] = new Philosophen[5];
    //static int count = 0;
    protected static int[] ZustandPhilosophen = new int[5];

    protected static int DENKEND = 0;
    protected static int ESSEND = 1;
    protected static int WARTEND = 2;
    public static Monitor neuerMonitor = new Monitor();


    public static void main(String[] args) {
        Monitor neuerMonitor = new Monitor();
        for (int i = 0; i < 5 ; i++) {
            PhilosophenArray[i] = new Philosophen(i);
            PhilosophenArray[i].start();


        }

    }

    public Philosophen(int id)
    {
        this.id = id;
    }


    public void run()
    {
        try{
            while(true) {
                    neuerMonitor.essenStarten(id);
                //Thread.sleep(10000);
                neuerMonitor.essenStoppen(id);


            }

        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
