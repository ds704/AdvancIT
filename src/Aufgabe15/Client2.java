package Aufgabe15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class Client2 {
    static String Input;
    //static DatagramSocket clienten;
    static DatagramPacket SendendesPacket;
    static int port = 5999;
    public static void main(String[] args) throws IOException {
        //j
        try (DatagramSocket clienten = new DatagramSocket()) {

            /*Scanner systemIn = new Scanner(System.in);

            Input = "Hallo Daniel";

            SendendesPacket = new DatagramPacket(Input.getBytes(), Input.length(), dest, port);
            clienten.send(SendendesPacket);*/
            InetAddress dest = InetAddress.getByName("127.0.0.1");


            while(true)
            {
                //System.out.println(System.in);

                //String c = systemIn.next();
                BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
                byte[] c = userIn.readLine().getBytes();

                //InetAddress dest = InetAddress.getByName("127.0.0.1");
                SendendesPacket = new DatagramPacket(c, c.length, dest, port);
                clienten.send(SendendesPacket);
                byte[] antwortArray = new byte[100];
                DatagramPacket antwortPacket = new DatagramPacket(antwortArray, antwortArray.length);
                clienten.receive(antwortPacket);

                String antwortString = new String(antwortPacket.getData(), 0, antwortPacket.getLength());
                System.out.println(antwortString);


            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }
}
