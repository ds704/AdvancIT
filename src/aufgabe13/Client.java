package aufgabe13;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;


public class Client {
    static String Input;
    //static DatagramSocket clienten;
    static DatagramPacket SendendesPacket;
    static int port = 4999;
    public static void main(String[] args) throws IOException {
        //j
        try (DatagramSocket clienten = new DatagramSocket()) {

            Scanner systemIn = new Scanner(System.in);

            Input = "Hallo Daniel";
            InetAddress dest = InetAddress.getByName("127.0.0.1");
            SendendesPacket = new DatagramPacket(Input.getBytes(), Input.length(), dest, port);
            clienten.send(SendendesPacket);

        
        while(true)
        {
            //System.out.println(System.in);

            String c = systemIn.next();

                //InetAddress dest = InetAddress.getByName("127.0.0.1");
                SendendesPacket = new DatagramPacket(c.getBytes(), c.length(), dest, port);
                clienten.send(SendendesPacket);
                c = "";

        }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }
}
