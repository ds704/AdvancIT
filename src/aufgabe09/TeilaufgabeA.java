package aufgabe09;

public class TeilaufgabeA {

    public static long main(String[] args) {
        int meinArray[] = new int[2097152];
        int sum = 0;
        long gesamtDauer = 0;

        for(int i = 0; i < meinArray.length; i++){
            meinArray[i] = 1;
        }

        long zeitDavor = System.currentTimeMillis();

        for(int j = 0; j < meinArray.length; j++){
            sum += meinArray[j];
        }

        long zeitDanach = System.currentTimeMillis();

        gesamtDauer = zeitDanach - zeitDavor;

        //System.out.println("Gesamtsumme: " + sum);
        //System.out.println("Dauer: " + gesamtDauer + " ms");

        return gesamtDauer;
    }

}