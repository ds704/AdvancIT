package aufgabe09;

public class Mehrfachausfuehrung_TeilaufgabeB {

    public static void main(String[] args) {
        String platzhalter[] = new String[] {"123"};
        int anzahlAusfuehrungen = 1000;
        double gesamtDauer = 0;
        double durchschnittsDauer;

        for(int j = 2; j <= 128; j = j*2){
            TeilaufgabeB.anzahlThreads = j;
            TeilaufgabeB.bruchteil = TeilaufgabeB.zahlenArray.length / TeilaufgabeB.anzahlThreads;
            for (int i = 0; i < anzahlAusfuehrungen; i++){
                gesamtDauer += TeilaufgabeB.main(platzhalter);
            }

            durchschnittsDauer = gesamtDauer / anzahlAusfuehrungen;

            System.out.println("Durchnittsdauer von " + anzahlAusfuehrungen + " Ausführungen mit " + TeilaufgabeB.anzahlThreads + " Threads: " + durchschnittsDauer + " ms");
        }
    }

}
