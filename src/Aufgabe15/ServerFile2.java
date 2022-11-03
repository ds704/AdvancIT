package Aufgabe15;

import Aufgabe14.MyFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ServerFile2 extends Thread {

    static DatagramSocket Server;

    static {
        try {
            Server = new DatagramSocket(5999);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    static MyFile2 datei;

    static {
        try {
            datei = new MyFile2();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String ausgabe;
    private InetAddress adresse;
    private String port;
    private byte[] data = new byte[9999];
    private DatagramPacket datenpacket = new DatagramPacket(data, 9999);;
    public ServerFile2(String ausgabe, InetAddress dest, String port)
    {
        this.ausgabe = ausgabe;
        this.adresse = dest;
        this.port = port;
    }

    public ServerFile2(DatagramPacket dp)
    {
        this.datenpacket = dp;
        //this.adresse = dest;
        //this.port = port;
    }
    public void run() {

        System.out.println("Thread nummer: "+ Thread.getAllStackTraces()+ " ist gestartet!!!");

        String ausgabe = new String(datenpacket.getData(), 0, datenpacket.getLength());
        char stringAusgabe[] = new char[ausgabe.length()];
        stringAusgabe = ausgabe.toCharArray();
        System.out.println(ausgabe);
        String filename = "";
        int zeilennummer2 = -1;
        for(int i = 0; i < ausgabe.length()-3; i++)
        {
            if(stringAusgabe[i] == 'R' & stringAusgabe[i+1] == 'E' & stringAusgabe[i+2] == 'A' & stringAusgabe[i+3] == 'D')
            {
                System.out.println("Habe READ gleesen");
                i=i+5;
                int stelleKomma = -1;
                for (int j = i; j < ausgabe.length(); j++)
                {

                    System.out.println(stringAusgabe[j]);
                    if (stringAusgabe[j] == ',')
                    {
                        System.out.println("Komma gefunden an stelle: " + j);
                        stelleKomma = j;
                        String zeilennummer = "";
                        for (int k = j+1; k < ausgabe.length(); k++)
                        {

                            zeilennummer += stringAusgabe[k];
                        }

                        System.out.println("Nummer: " + zeilennummer);
                        zeilennummer2 = Integer.parseInt(zeilennummer);
                        System.out.println("Nummer: " + zeilennummer2);
                        break;
                    }
                    filename += stringAusgabe[j];
                }
                System.out.println("=================== filename: "+filename);
                break;
            }
        }
        String ergebnis = null;
        try {
            ergebnis = datei.readLine(filename, zeilennummer2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(ergebnis);
        DatagramPacket SendendesPacket = new DatagramPacket(ergebnis.getBytes(), ergebnis.length(), datenpacket.getAddress(), datenpacket.getPort());
        try {
            Server.send(SendendesPacket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(ausgabe);

    }

    public static void main(String[] args) throws IOException {


            while (true) {
                byte[] data = new byte[9999];
                DatagramPacket dp = new DatagramPacket(data, 9999);
                Server.receive(dp);
                System.out.println(dp.getData());
                ServerFile2 neuerThread = new ServerFile2(dp);//(ausgabe, dp.getAddress(), dp.getPort());
                neuerThread.run();




            }
        }
    }


