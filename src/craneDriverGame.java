import javax.swing.JOptionPane;
import java.util.Scanner;
import java.util.Random;

public class craneDriverGame {

    static short userGuess;
    static short score = 0;
    static short materialNumber;
    static short distanceNumber;
    static float dichtheid;
    static short afstand;
    static short hijscapaciteit;
    static short grijperinhoud;
    static float grijpergewicht;
    static float hijsgewicht;
    static int keuze1;
    static int keuze2;
    static String materiaal;

    static String gegevens1 = "Het te verhandelen bulkmateriaal is ";
    static String gegevens2 = " heeft een dichtheid van ";
    static String gegevens3 = "Het schip ligt op een afstand van ";
    static String gegeven4 = "Op deze werkafstand heeft de kraan een hijscapaciteit van ";
    static String vraag = "Wat is de inhoud in kubieke meter van de grijper die moet worden ingezet?";
    static String opties = "Type het juiste aantal kubieke meter in";
    static String goed = "Je hebt het juiste antwoord gekozen.";
    static String fout = "Dit is niet het goede antwoord.";
    static String fouteInvoer = "Foute invoer! Type een van de genoemde getallen in.";

    static String[] bulkMaterial = new String[8];
    static float[] bulkDensity = new float[8];
    static short[] operatingDistance = new short[8];
    static short[] liftingCapacity = new short[8];

    //int array met 14 verschillende capaciteiten in m3 grijpers
    static short[] grabContent = new short[]{3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};


    /*int array met de 14 gewichten in ton voor de capaciteiten van de grijpers,
    wordt momenteel niet gebruikt omdat het gewicht in ton van de grijper
    gelijk is aan de capaciteit
    */
    //static short[] grabWeight = new short[]{3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};

    static boolean endplay = false;

    static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {

        fillStringArrays();

        do {


            if (score >= 20) {
                JOptionPane.showInternalMessageDialog(null, score + " sterren! Je hebt je doel behaald!");
                endplay = true;
                break;
            }

            //random een nummer uit de bulkmateriaal Array kiezen
            materialNumber = (short) Math.floor(Math.random() * bulkMaterial.length);
            materiaal = bulkMaterial[materialNumber];
            dichtheid = bulkDensity[materialNumber];

            String Gegevens1= gegevens1 + materiaal;

            String Gegevens2= materiaal + gegevens2 + dichtheid + " t/m3";

            JOptionPane.showMessageDialog(null, Gegevens1 + "\n" + Gegevens2,"Bulkmateriaal",
                    JOptionPane.INFORMATION_MESSAGE);


            //random een nummer uit de werkafstanden Array kiezen
            distanceNumber = (short) Math.floor(Math.random() * operatingDistance.length);
            afstand = operatingDistance[distanceNumber];
            hijscapaciteit = liftingCapacity[distanceNumber];

            String Gegevens3=gegevens3 + afstand + " meter";
            String Gegevens4=gegeven4 + hijscapaciteit + " ton";

            JOptionPane.showMessageDialog(null, Gegevens3 + "\n" + Gegevens4,"Bulkmateriaal " + materiaal + ", met stortgewicht " + dichtheid,
                    JOptionPane.INFORMATION_MESSAGE);

            //computer berekent het juiste antwoord
            berekenGrijperInhoud();

            //cheatcode
            System.out.println(grijperinhoud);

            //twee false optie selecteren en door elkaar husselen met juiste antwoord
            showOptions();

            playGame();


            //blijven doorlopen terwijl endplay false is
        } while (!endplay);

    } //end main

    public static String[] showOptions() {
        //twee keuzevariabelen, mogen niet gelijk zijn aan elkaar of aan de berekende grijperinhoud
        Random r = new Random();

        do {
            keuze1 = grabContent[r.nextInt(grabContent.length)];
            keuze2 = grabContent[r.nextInt(grabContent.length)];

        } while ((keuze1 == keuze2) || (grijperinhoud == keuze1) || (grijperinhoud == keuze2));

        //keuze1 en keuze2 zijn altijd fout
        //array aanmaken om waarden in random volgorde weer te geven
        int[] meerkeuze = new int[3];
        meerkeuze[0] = grijperinhoud;
        meerkeuze[1] = keuze1;
        meerkeuze[2] = keuze2;

        for (int i = 0; i < meerkeuze.length; i++) {
            int randomIndexToSwap = r.nextInt(meerkeuze.length);
            int keuze = meerkeuze[randomIndexToSwap];
            meerkeuze[randomIndexToSwap] = meerkeuze[i];
            meerkeuze[i] = keuze;
        }

        String OptieA="Optie a is " + meerkeuze[0] + " m3. Deze grijper weegt " + meerkeuze[0] + " ton.";
        String OptieB="Optie b is " + meerkeuze[1] + " m3. Deze grijper weegt " + meerkeuze[1] + " ton.";
        String OptieC="Optie c is " + meerkeuze[2] + " m3. Deze grijper weegt " + meerkeuze[2] + " ton.";

        String [] Options = new String[] {OptieA, OptieB, OptieC};

        return Options;

    }

    public static void playGame() {
        boolean check = false;

        //De meerkeuzevragen opvragen, bij fout antwoord komen er echter weer andere opties vanwege de call showOptions
        String [] Opties = showOptions();


        do {

            //Dit toont de opties
            JOptionPane.showInternalMessageDialog(null, Opties);

            //dit is een invoerveld, alleen kan de meerkeuzearray hier niet in
            userGuess = Short.parseShort(JOptionPane.showInputDialog(null, vraag, opties, JOptionPane.QUESTION_MESSAGE));

            System.out.println(grijperinhoud);

            //dichtheid en hijsgewicht op 1 cijfer achter de komma afronden in OptionPane (%.1f)
            if (userGuess == grijperinhoud) {
                JOptionPane.showMessageDialog(null,
                        "\n Immers, de grijperinhoud van " + grijperinhoud + " m3 x een stortgewicht van "
                                + dichtheid + " t/m3 = " + grijpergewicht + " ton aan bulkmateriaal."
                                + " \n En de inhoud aan bulkmateriaal van " + grijpergewicht + " ton" +
                                " het grijpergewicht van " + grijperinhoud + " ton = " + hijsgewicht +
                                " ton wat de kraan nog kan tillen op deze afstand.", goed,
                        JOptionPane.INFORMATION_MESSAGE);

                score = (short) (score + 2);

                check = true;
                if ((score >= 0) && (score <= 19)) {

                    continueGame();

                    if (((score == 6) || (score == 7)) || ((score == 14) || (score == 15))) {
                        JOptionPane.showMessageDialog(null, score + " sterren is een nieuwe mijlpaal.", "improving",
                                JOptionPane.INFORMATION_MESSAGE);
                    }


                } //end if
            } else if ((userGuess == keuze1) || (userGuess == keuze2)) {
                JOptionPane.showMessageDialog(null, fout, "try again",
                        JOptionPane.INFORMATION_MESSAGE);
                score--;
            //hier moet code komen voor het gev
            /*}   else if (userGuess == JOptionPane.OK_OPTION) {
                    JOptionPane.showMessageDialog(null, opties, "invoer vereist",
                    JOptionPane.INFORMATION_MESSAGE);
            } else if (userGuess == JOptionPane.CANCEL_OPTION) {
                endplay = true;
                break;*/

        } else {
                JOptionPane.showMessageDialog(null, fouteInvoer, "pay attention",
                        JOptionPane.INFORMATION_MESSAGE);

            } //end else

        } while (!check);

    }

    private static void berekenGrijperInhoud() {
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
    }

    private static void fillStringArrays() {
        //String array met  8 verschillende bulkmaterialen

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

        bulkDensity[0] = (float) 0.8;
        bulkDensity[1] = (float) 1.0;
        bulkDensity[2] = (float) 1.2;
        bulkDensity[3] = (float) 1.4;
        bulkDensity[4] = (float) 1.6;
        bulkDensity[5] = (float) 1.8;
        bulkDensity[6] = (float) 2.0;
        bulkDensity[7] = (float) 2.2;

        //int array met 8 verschillende werkafstanden in meter

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

        liftingCapacity[0] = 30;
        liftingCapacity[1] = 25;
        liftingCapacity[2] = 20;
        liftingCapacity[3] = 16;
        liftingCapacity[4] = 14;
        liftingCapacity[5] = 12;
        liftingCapacity[6] = 11;
        liftingCapacity[7] = 10;
    }

    private static void continueGame() {
        boolean control = false;

        int reply = JOptionPane.showConfirmDialog(null,
                "Doorgaan met spelen?",
                "Je hebt nu " + score + " sterren.", JOptionPane.YES_NO_OPTION);


            if (reply == JOptionPane.YES_OPTION) {
                endplay = false;
                //endplay = true stopt het spel niet meer
            } else System.exit(0);



    }


} // end class

/*




 */