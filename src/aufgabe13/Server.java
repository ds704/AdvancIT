package aufgabe13;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Server {
    public static void main(String[] args) throws IOException {
        DatagramSocket Server = new DatagramSocket(4998);



        while (true)
        {
            byte[] data = new byte[9999];
            DatagramPacket dp = new DatagramPacket(data, 9999);
            Server.receive(dp);
            String ausgabe = new String(dp.getData(), 0, dp.getLength());
            System.out.println(ausgabe);


        }
    }
}
