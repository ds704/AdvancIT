package Testat3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.Semaphore;

public class Server extends Thread {

    private static final String wdir = System.getProperty("user.home");
    static DatagramPacket[] ArrayAuftraegeThreads = new DatagramPacket[7];
    static Server[] ArrayThreads = new Server[7]; // ist eigentlich nicht notwendig, außer für Garbage-collector
    static DatagramSocket ds;
    static int ctr = 0;
    static int nextfree = 0;
    static int nextfull = 0;
    static Semaphore mutex = new Semaphore(1, true);
    static Semaphore full = new Semaphore(0, true);
    static Semaphore empty = new Semaphore(7, true);
    static Monitor monitor = new Monitor();

    static {
        try {
            ds = new DatagramSocket (5999 );
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    private int id = -1;

    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println("Server successfully started on port: 5999" ) ;
        for(int i = 0; i<7;i++)
        {
            ArrayThreads[i] = new Server(i);
            ArrayThreads[i].start();
        }
        while (true)
        {
            DatagramPacket dp = new DatagramPacket (new byte [65507] , 65507) ;
            ds.receive(dp);
            empty.acquire();
            mutex.acquire();
            ArrayAuftraegeThreads[nextfree] = dp;
            nextfree = nextfree+1%7;
            ctr++;
            mutex.release();
            full.release();
            /*for(int i = 0; i <7; i++)
            {
                if(ArrayAuftraegeThreads[i] == null)
                {
                    ArrayAuftraegeThreads[i] = dp;
                    break;
                }
            }*/

        }
    }

    public Server(int id)
    {
        this.id = id;
    }


    @Override
    public void run() {
        while (true) {
            DatagramPacket dp = null;
            try {
                full.acquire();
                mutex.acquire();
                ctr--;
                dp = ArrayAuftraegeThreads[nextfull];
                nextfull = nextfull+1%7;
                mutex.release();
                empty.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            String[] param = null;
            String[] param2 = null;
            String filename;
            int lineNo = -1;
            MyFile f = null;
            String answer = null;
            String newData = "";

            String dpData = new String(dp.getData(), 0, dp.getLength()).trim();
            System.out.println("Nachricht erhalten!");
            if (dpData.startsWith("READ")) {
                try {
                    System.out.println("READ erhalten");
                    param = dpData.split(" ", 2);
                    param2 = param[1].split(",", 2);
                    filename = param2[0].trim();
                    lineNo = Integer.parseInt(param2[1].trim());
                    f = new MyFile();
                    System.out.println("Filename: "+ filename+ " Zeilennummer: "+ lineNo);
                    monitor.startRead();
                    answer = f.readLine(filename, lineNo);
                    monitor.endRead();
                } catch (Exception e) {
                    answer = "!!! ERROR 901: bad READ command ";
                } // catch
            } else if (dpData.startsWith("WRITE")) {
                try {
                    param = dpData.split(" ", 2);
                    param2 = param[1].split(",", 3);
                    filename = param2[0].trim();
                    lineNo = Integer.parseInt(param2[1].trim());
                    newData = param2 [2];
                    f = new MyFile();
                    monitor.startWrite();
                    answer = f.write(filename, lineNo, newData);
                    monitor.endWrite();
                } catch (NumberFormatException | InterruptedException e) {
                    throw new RuntimeException(e);
                }

        } else {
            answer = "!! ERROR 902: unknown command ";
             }

        try {
            System.out.println("Die Antwort ist: "+ answer);
            DatagramPacket dp2 = new DatagramPacket (answer.getBytes(),answer.length(), dp.getAddress(), dp.getPort());
            ds.send(dp2);
            System.out.println("Antwort ist gesendet");
        } catch ( Exception e ){ e.printStackTrace() ;
        }

        }



    }
}


