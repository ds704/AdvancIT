package Aufgabe14;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MyFile {
    private String filename;// = "src/Aufgabe14/";

    public static void main(String[] args) throws IOException {
        MyFile meineDatei = new MyFile();
        System.out.println(meineDatei.readLine("src/Aufgabe14/test",3));

    }

    public MyFile() throws FileNotFoundException {
        //filename = "";


    }

    public String readLine(String filename, int Zeilennummer) throws IOException {
        if(Zeilennummer > 0)
        {

            BufferedReader fileIn = new BufferedReader((new FileReader(filename)));
            String zeile = null;
            for (int i = 0; i < Zeilennummer; i++) {
                zeile = fileIn.readLine();
            }
            return zeile;

        }else {
            return "Da gab es einen Fehler";
        }
    }
}
