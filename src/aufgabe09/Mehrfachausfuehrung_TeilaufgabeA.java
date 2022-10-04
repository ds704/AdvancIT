package aufgabe09;

public class Mehrfachausfuehrung_TeilaufgabeA {

    public static void main(String[] args) {
        String platzhalter[] = new String[] {"ABC"};
        int anzahlAusfuehrungen = 1000;
        double gesamtDauer = 0;
        double durchschnittsDauer;

        for (int i = 0; i < anzahlAusfuehrungen; i++){
            gesamtDauer += TeilaufgabeA.main(platzhalter);
        }

        durchschnittsDauer = gesamtDauer / anzahlAusfuehrungen;

        System.out.println("Durchnittsdauer von " + anzahlAusfuehrungen + " Ausführungen ohne Threads: " + durchschnittsDauer + " ms");

    }
}
