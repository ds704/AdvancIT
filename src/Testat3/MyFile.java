package Testat3;

import java.io.*;

public class MyFile {
    public String readLine(String filename, int Zeilennummer) throws IOException {
        if (Zeilennummer > 0) {
            try {
                BufferedReader fileIn = new BufferedReader((new FileReader(filename)));

                String zeile = null;
                for (int i = 0; i < Zeilennummer; i++) {
                    zeile = fileIn.readLine();
                }
                return zeile;
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            return "Da gab es einen Fehler";
        }
        return "Error";
    }

    public String write(String fileName, int lineNo, String data) {
        boolean found = false; // i s l ineNo i n i n F i l e ?
        BufferedReader inFile = null;
        PrintWriter outFile = null;
        String answer= "ERROR hier ist was schief gegangen";
        try {
            inFile = new BufferedReader(new FileReader(fileName));
            outFile = new PrintWriter(new FileWriter(fileName + ". temp "));
            answer = "!!! ERROR Write faild, weil Zeile nicht gefunden ";
            String s = "";
            for (int i = 0; s != null; i++) {
                s = inFile.readLine();
                if (i == lineNo - 1) {
                    found = true;
                    outFile.println(data);
                } else if (s != null) {
                    outFile.println(s);
                }
            }// f o r
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (inFile != null) {
            try {
                inFile.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (outFile != null) {
            try {
                outFile.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    if (found) {
        answer = data ;
        try {

            File f1 = new File (fileName ) ;
            File f2 = new File (fileName+". temp ") ;
            File f3 = new File (fileName+". bak ") ;
            f3.delete( ) ; // p o s s i b l y r e q u i r e d f o r subs equent rename
            f1.renameTo(f3) ; // o r i g i n i s new backup f i l e
            f2.renameTo(f1) ; // make changes s t a b l e
        } catch( Exception e ){e . printStackTrace () ; }
    }// i f
    return answer ;
    }// wr i t e
}

