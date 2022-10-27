package aufgabe12zEJ;

public class Monitor {

    public int Hallo =123;
    public static void main(String[] args) {

    }

    public void essenStarten(int id) throws InterruptedException {
        if(Philosophen.ZustandPhilosophen[(id+4)%5] != Philosophen.ESSEND && Philosophen.ZustandPhilosophen[(id+1)%5] != Philosophen.ESSEND)
        {
            Philosophen.ZustandPhilosophen[id] = Philosophen.ESSEND;
            System.out.println("Philosoph mit der id: " + id + " isst gerade!");
        }else {
            Philosophen.ZustandPhilosophen[id] = Philosophen.WARTEND;
            wait();
            essenStarten(id);
        }

    }
    protected void essenStoppen(int id)
    {
        Philosophen.ZustandPhilosophen[id] = Philosophen.DENKEND;
        if(Philosophen.ZustandPhilosophen[(id+4)%5] == Philosophen.WARTEND)
        {
            notifyAll();
        }else if(Philosophen.ZustandPhilosophen[(id+1)%5] == Philosophen.WARTEND) {
            notifyAll();
    }
    }
}
