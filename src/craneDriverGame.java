import java.util.Scanner;
import java.util.Random;

public class craneDriverGame {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        short userGuess;
        short score = 0;
        short materialNumber;
        short distanceNumber;
        float dichtheid;
        short afstand;
        short hijscapaciteit;
        short grijperinhoud;
        float grijpergewicht;
        float hijsgewicht;
        String materiaal;

        String gegevens1 = "Het te verhandelen bulkmateriaal is ";
        String gegevens2 = " heeft een dichtheid van ";
        String gegevens3 = "Het schip ligt op een afstand van ";
        String gegeven4 = "Op deze werkafstand heeft de kraan een hijscapaciteit van ";
        String vraag = "Wat is de inhoud in kubieke meter van de grijper die moet worden ingezet?";
        String opties = "Type het juiste aantal kubieke meter in";
        String goed = "Je hebt het juiste antwoord gekozen.";
        String fout = "Dit is niet het goede antwoord.";


        //String array met  8 verschillende bulkmaterialen
        String[] bulkMaterial = new String[8];
        bulkMaterial[0] = "graan";
        bulkMaterial[1] = "meel";
        bulkMaterial[2] = "steenkolen";
        bulkMaterial[3] = "kunstmest";
        bulkMaterial[4] = "zand";
        bulkMaterial[5] = "grind";
        bulkMaterial[6] = "klei";
        bulkMaterial[7] = "ijzererts";

        /*float array met 8 verschillende density die
        corresponderen met de bovenstaande bulkmaterialen
         */
        float[] bulkDensity = new float[8];
        bulkDensity[0] = (float) 0.8;
        bulkDensity[1] = (float) 1.0;
        bulkDensity[2] = (float) 1.2;
        bulkDensity[3] = (float) 1.4;
        bulkDensity[4] = (float) 1.6;
        bulkDensity[5] = (float) 1.8;
        bulkDensity[6] = (float) 2.0;
        bulkDensity[7] = (float) 2.2;

        //int array met 8 verschillende werkafstanden in meter
        short[] operatingDistance = new short[8];
        operatingDistance[0] = 10;
        operatingDistance[1] = 14;
        operatingDistance[2] = 16;
        operatingDistance[3] = 18;
        operatingDistance[4] = 20;
        operatingDistance[5] = 22;
        operatingDistance[6] = 24;
        operatingDistance[7] = 26;

        /*int array met 8 verschillende hijscapaciteiten in ton die corresponderen
        met de gegeven werkafstanden
         */
        short[] liftingCapacity = new short[8];
        liftingCapacity[0] = 30;
        liftingCapacity[1] = 25;
        liftingCapacity[2] = 20;
        liftingCapacity[3] = 16;
        liftingCapacity[4] = 14;
        liftingCapacity[5] = 12;
        liftingCapacity[6] = 11;
        liftingCapacity[7] = 10;

        boolean endplay = false;


        //het spel blijven spelen als speler verder wil totdat 20 sterren zijn behaald
        do {

            if (score >= 20) {
                System.out.println(score + " sterren! Je hebt je doel behaald!");
                endplay = true;
                break;
            }

            //random een nummer uit de bulkmateriaal Array kiezen
            materialNumber = (short) Math.floor(Math.random() * bulkMaterial.length);
            materiaal = bulkMaterial[materialNumber];
            dichtheid = bulkDensity[materialNumber];

            System.out.println(gegevens1 + materiaal);
            System.out.println(materiaal + gegevens2 + dichtheid + " t/m3");

            //random een nummer uit de werkafstanden Array kiezen
            distanceNumber = (short) Math.floor(Math.random() * operatingDistance.length);
            afstand = operatingDistance[distanceNumber];
            hijscapaciteit = liftingCapacity[distanceNumber];

            System.out.println(gegevens3 + afstand + " meter");
            System.out.println(gegeven4 + hijscapaciteit + " ton");

            //int array met 17 verschillende capaciteiten in m3 grijpers
            short[] grabContent = new short[]{2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18};


            /*int array met de 17 gewichten in ton voor de capaciteiten van de grijpers,
            wordt momenteel niet gebruikt omdat het gewicht in ton van de grijper
            gelijk is aan de capaciteit
            */
            short[] grabWeight = new short[]{2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18};


            //deelberekening grijperinhoud op basis van door de computer gekozen hijsvermogen en stortgewicht
            grijperinhoud = (short) Math.round((hijscapaciteit / dichtheid) * 0.5);
            grijpergewicht = (grijperinhoud * dichtheid);
            hijsgewicht = (grijpergewicht + grijperinhoud);

            //berekening grijperinhoud naar boven of onder corrigeren met while statements tot de juiste waarde
            while ((hijsgewicht + 1) < hijscapaciteit) {
                grijperinhoud = (short) (grijperinhoud + 1);
                grijpergewicht = (grijperinhoud * dichtheid);
                hijsgewicht = (grijpergewicht + grijperinhoud);
            }

            while (hijsgewicht > hijscapaciteit) {
                grijperinhoud = (short) (grijperinhoud - 1);
                grijpergewicht = (grijperinhoud * dichtheid);
                hijsgewicht = (grijpergewicht + grijperinhoud);
            }

            //cheatcode
            System.out.println(grijperinhoud);

            //twee keuzevariabelen, mogen niet gelijk zijn aan elkaar of aan de berekende grijperinhoud
            int keuze1;
            int keuze2;
            int aantalOpties = grabContent.length;
            keuze1 = grabContent[(int) Math.floor(Math.random() * aantalOpties)];
            do {
                keuze2 = grabContent[(int) Math.floor(Math.random() * aantalOpties)];
            } while ((keuze1 == keuze2) || (grijperinhoud == keuze1) || (grijperinhoud == keuze2));

            //keuze1 en keuze2 zijn altijd fout
            System.out.println(vraag);

            //array aanmaken om waarden in random volgorde weer te geven
            int[] meerkeuze = new int[3];
            meerkeuze[0] = grijperinhoud;
            meerkeuze[1] = keuze1;
            meerkeuze[2] = keuze2;
            Random r = new Random();
            for (int i = 0; i < meerkeuze.length; i++) {
                int randomIndexToSwap = r.nextInt(meerkeuze.length);
                int keuze = meerkeuze[randomIndexToSwap];
                meerkeuze[randomIndexToSwap] = meerkeuze[i];
                meerkeuze[i] = keuze;
            }
            System.out.println("Optie a is " + meerkeuze[0] + " m3. Deze grijper weegt " + meerkeuze[0] + " ton.");
            System.out.println("Optie b is " + meerkeuze[1] + " m3. Deze grijper weegt " + meerkeuze[1] + " ton.");
            System.out.println("Optie c is " + meerkeuze[2] + " m3. Deze grijper weegt " + meerkeuze[2] + " ton.");

            boolean check = false;


            do {
                System.out.println(opties);
                userGuess = s.nextShort();


                if (userGuess == grijperinhoud) {
                    System.out.println(goed);
                    System.out.println("Immers, de grijperinhoud van " + grijperinhoud + " m3 x een stortgewicht van " + dichtheid + " t/m3 = " + grijpergewicht + " ton aan bulkmateriaal.");
                    System.out.println("En de inhoud aan bulkmateriaal van " + grijpergewicht + " ton + het grijpergewicht van " + grijperinhoud + " ton = " + hijsgewicht + " ton wat de kraan nog kan tillen op deze afstand.");
                    score = (short) (score + 2);

                    check = true;
                    if (score < 20) {
                        System.out.println("Je hebt nu " + score + " sterren");
                        System.out.println("Wil je nog een keer spelen? (y of n)");
                        boolean control = false;
                        do {
                            System.out.println("Antwoord met y of n");
                            String antwoord = s.next();
                            if (antwoord.equals("y")) {
                                control = true;
                            } else if (antwoord.equals("n")) {
                                endplay = true;
                                control = true;
                            }

                        } while (!control);
                    } //end if
                } else {
                    System.out.println(fout);
                    score--;
                } //end else

            } while (!check);

            //blijven doorlopen terwijl endplay false is
        } while (!endplay);


    } //end main
} // end class