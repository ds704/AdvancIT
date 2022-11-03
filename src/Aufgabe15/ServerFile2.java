package Aufgabe15;

import Aufgabe14.MyFile;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServerFile2 {


        public static void main(String[] args) throws IOException {
            DatagramSocket Server = new DatagramSocket(5999);
            MyFile2 datei = new MyFile2();

            while (true) {
                byte[] data = new byte[9999];
                DatagramPacket dp = new DatagramPacket(data, 9999);
                Server.receive(dp);

                String ausgabe = new String(dp.getData(), 0, dp.getLength());
                char stringAusgabe[] = new char[ausgabe.length()];
                stringAusgabe = ausgabe.toCharArray();
                System.out.println(ausgabe);
                String filename = "";
                int zeilennummer2 = -1;
                for(int i = 0; i < ausgabe.length()-3; i++)
                {
                    if(stringAusgabe[i] == 'R' & stringAusgabe[i+1] == 'E' & stringAusgabe[i+2] == 'A' & stringAusgabe[i+3] == 'D')
                    {
                        //System.out.println("Habe READ gleesen");
                        i=i+5;
                        int stelleKomma = -1;
                        for (int j = i; j < ausgabe.length(); j++)
                        {

                            //System.out.println(stringAusgabe[j]);
                            if (stringAusgabe[j] == ',')
                            {
                                //System.out.println("Komma gefunden an stelle: " + j);
                                stelleKomma = j;
                                String zeilennummer = "";
                                for (int k = j+1; k < ausgabe.length(); k++)
                                {

                                    zeilennummer += stringAusgabe[k];
                                }

                                //System.out.println("Nummer: " + zeilennummer);
                                zeilennummer2 = Integer.parseInt(zeilennummer);
                                //System.out.println("Nummer: " + zeilennummer2);
                                break;
                            }
                            filename += stringAusgabe[j];
                        }
                        //System.out.println("=================== filename: "+filename);
                        break;
                    }
                }
                String ergebnis = datei.readLine(filename, zeilennummer2);
                System.out.println(ergebnis);
                DatagramPacket SendendesPacket = new DatagramPacket(ergebnis.getBytes(), ergebnis.length(), dp.getAddress(), dp.getPort());
                Server.send(SendendesPacket);

                System.out.println(ausgabe);


            }
        }
    }


