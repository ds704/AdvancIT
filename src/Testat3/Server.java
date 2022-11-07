package Testat3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Server extends Thread {

    private static String wdir = System.getProperty("user.home");
    static DatagramPacket[] ArrayAuftraegeThreads = new DatagramPacket[7];
    static Server[] ArrayThreads = new Server[7]; // ist eigentlich nicht notwendig, außer für Garbage-collector
    static DatagramSocket ds;

    static {
        try {
            ds = new DatagramSocket (5999 );
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    private int id = -1;

    public static void main(String[] args) throws IOException {

        System.out.println("Se r v e r s u c c e s s f u l l y s t a r t e d on por t: 5999" ) ;
        for(int i = 0; i<7;i++)
        {
            ArrayThreads[i] = new Server(i);
            ArrayThreads[i].start();
        }
        while (true)
        {
            DatagramPacket dp = new DatagramPacket (new byte [65507] , 65507) ;
            ds.receive(dp);
            for(int i = 0; i <7; i++)
            {
                if(ArrayAuftraegeThreads[i] == null)
                {
                    ArrayAuftraegeThreads[i] = dp;
                    break;
                }
            }

        }
    }

    public Server(int id)
    {
        this.id = id;
    }


    @Override
    public void run() {
        while (true) {
            while(ArrayAuftraegeThreads[id] == null)
            {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            DatagramPacket dp = ArrayAuftraegeThreads[id];
            String param[] = null;
            String param2[] = null;
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
                    answer = f.readLine(filename, lineNo);
                } catch (Exception e) {
                    answer = "!!! ERROR 901: bad READ command ";
                } // catch
            } else if (dpData.startsWith("WRITE")) {
                try {
                    param = dpData.split(" ", 2);
                    param2 = param[1].split(",", 3);
                    filename = param2[0].trim();
                    lineNo = Integer.parseInt(param2[1].trim());
                    newData = param2 [ 2 ] ;
                    f = new MyFile() ;
                    answer = f.write(filename, lineNo, newData) ;
                } catch (NumberFormatException e) {
                    throw new RuntimeException(e);
                }

        } else {
            answer = "!! ERROR 902: unknown command ";

             } // i f −e l s e

        try {
            System.out.println("Die Antwort ist: "+ answer);
            DatagramPacket dp2 = new DatagramPacket (answer.getBytes(),answer.length(), dp.getAddress(), dp.getPort());
            ds.send(dp2);
            System.out.println("Antowrt ist gesendet");
        } catch ( Exception e ){ e.printStackTrace() ;
        }

            ArrayAuftraegeThreads[id] = null;
        }


    }
}


