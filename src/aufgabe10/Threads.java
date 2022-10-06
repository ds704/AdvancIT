package aufgabe10;

public class Threads extends Thread {


    public static FifoQueue List = new FifoQueue();
    static Runnable r = new Runnable() {
        @Override
        public void run() {


            try {
                for(int i = 0; i< 1000; i++) {
                    List = FifoQueue.Node.put(List, "Hallo");
                    System.out.println(Thread.currentThread().getName() +" das war der Thread");
                    FifoQueue.Node.get(List);
                }
            } /*catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }*/ catch (Exception e) {
                e.printStackTrace();
            }
        }
    };


    public static void main(String[] args) throws InterruptedException {
    Thread t1 = new Thread(r);
    Thread t2 = new Thread(r);
    t1.start();
    t2.start();
    t1.join();
    t2.join();
    new Thread(r).start();
    System.out.println("Es sind noch "+ FifoQueue.Node.getLenth() + " Elemente vorhanden");
    for(int i= 0; i< FifoQueue.Node.getLenth(); i++) {
        FifoQueue.Node.get(List);
    }
    }
}
