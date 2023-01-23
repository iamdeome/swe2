import java.util.Scanner;
import java.nio.channels.Pipe;
import java.util.Arrays;

public class Spiel {
    private static Spieler spieler;
    private static int tag = 1;
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Create Ware
        Ware W_brot = new Ware("Brot", 3, 1);
        Ware W_fisch = new Ware("Fisch", 10, 2);
        Ware W_wasser = new Ware("Wasser", 3, 3);
        Ware W_limonade = new Ware("Limonade", 5, 3);
        Ware W_schwert = new Ware("Schwert", 20, 10);

        // Create Tätigkeiten
        Taetigkeit T_fegen = new Taetigkeit("Straße fegen", 10);
        Taetigkeit T_limonade = new Taetigkeit("Limonade verkaufen", 20);
        Taetigkeit T_fische = new Taetigkeit("Fische fangen", 30);

        // Create Orte
        Ort O_berlin = new Ort("Berlin",
                52.482514, 13.396573,
                T_fegen, Arrays.asList(W_brot, W_fisch), true);
        Ort O_hamburg = new Ort("Hamburg",
                53.556451, 9.909963,
                T_limonade, Arrays.asList(W_brot, W_wasser), true);
        Ort O_dresden = new Ort("Dresden",
                51.051431, 13.733612,
                T_fische, Arrays.asList(W_schwert, W_fisch, W_brot), true);
        Ort O_schmalkladen = new Ort("Schmalkladen",
                50.717332, 10.459702,
                T_limonade, Arrays.asList(W_limonade), false);

        System.out.println("Geben Sie Ihren Namen ein:");

        // Create Spieler
        spieler = new Spieler(sc.nextLine(), O_schmalkladen);

        System.out.println("Hallo " + spieler.getName() + "\n");
        System.out.println("Wilkommen zum \"Karavanen, Karavanen und mehr Karavanen\"");

        while (true) {
            System.out.println("");
            System.out.println(
                    "tag: " + tag + " \t " + " LP " + spieler.getLp() + " \t " + " GS " + spieler.getGoldstuecke());
            System.out.println("Sie befinden sich in " + spieler.getJetzigerOrt().getName() + " \n");
            System.out.println("Wählen Sie eine Option aus: ");
            System.out.println("1. Ware kaufen. ");
            System.out.println("2. Ware verkaufen. ");
            System.out.println("3. Reisen. ");
            System.out.println("4. Tätigkeit annehmen. ");
            System.out.println("5. Spiel beenden.");

            // Get input and validate
            int input = -1;
            try {
                input = sc.nextInt();
                if (input < 1 || input > 5) {
                    throw new Exception();
                }
            } catch (Exception e) {
                sc.nextLine();
                System.out.println("Ungültige Eingabe. Bitte geben Sie eine Zahl zwischen 1 und 5 ein");
                continue;
            }

            switch (input) {
                case 1:
                    spieler.kaufe();
                    break;
                case 2:
                    spieler.verkaufe();
                    break;
                case 3:
                    reisen();
                    break;
                case 4:
                    taetigkeitAnnehmen();
                    break;
                case 5:
                    beenden();
                    break;
            }

            if (tag >= 100 || spieler.getLp() <= 0) {
                beenden();
            }

            tag++;
        }

    }

    private static void reisen() {

    }

    private static void taetigkeitAnnehmen() {

    }

    private static void beenden() {
        System.out.println("Spielende. Sie haben " + spieler.getGoldstuecke() + " GS erreicht. ");
        System.exit(0);
    }
}
