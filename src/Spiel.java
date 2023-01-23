import java.util.Scanner;
import java.util.Arrays;
import java.util.List;

public class Spiel {
    public static Scanner sc = new Scanner(System.in);

    private static Spieler spieler;
    private static int tag = 1;
    private static List<Ort> ortListe;

    public static void main(String[] args) {
        // Create Ware
        Ware W_bohnen = new Ware("Bohnen mit Speck", 1, 1);
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
                T_fegen, Arrays.asList(W_bohnen, W_brot, W_fisch), true);
        Ort O_hamburg = new Ort("Hamburg",
                53.556451, 9.909963,
                T_limonade, Arrays.asList(W_bohnen, W_brot, W_wasser), true);
        Ort O_dresden = new Ort("Dresden",
                51.051431, 13.733612,
                T_fische, Arrays.asList(W_bohnen, W_schwert, W_fisch, W_brot), true);
        Ort O_schmalkladen = new Ort("Schmalkladen",
                50.717332, 10.459702,
                T_limonade, Arrays.asList(W_limonade), false);
        Ort O_erfurt = new Ort("Erfurt",
                50.977754, 11.016364,
                T_fegen, Arrays.asList(W_bohnen, W_brot, W_fisch), true);
        ortListe = Arrays.asList(O_berlin, O_hamburg, O_dresden, O_schmalkladen, O_erfurt);

        System.out.println("Geben Sie Ihren Namen ein:");

        // Create Spieler
        spieler = new Spieler(sc.nextLine(), O_schmalkladen);

        System.out.println("Hallo " + spieler.getName() + "\n");
        System.out.println("Wilkommen zum \"Karavanen, Karavanen und mehr Karavanen\"");

        while (true) {
            System.out.println("");
            System.out.println(
                    "Tag " + tag + "        " + "LP " + spieler.getLp() + "        GS " + spieler.getGoldstuecke()
                            + "        Bohnen " + spieler.getBohnen());
            System.out.println("Sie befinden sich in " + spieler.getJetzigerOrt().getName() + " \n");
            System.out.println("Wählen Sie eine Option aus: ");
            System.out.println("1. Ware kaufen. ");
            System.out.println("2. Ware verkaufen. ");
            System.out.println("3. Reisen. ");
            System.out.println("4. Tätigkeit annehmen. ");
            System.out.println("5. Lokalen Produkten essen. ");
            System.out.println("6. Einen Tag warten. ");
            System.out.println("7. Spiel beenden.");

            // Get input and validate
            int input = -1;
            try {
                input = sc.nextInt();
            } catch (Exception e) {
                sc.nextLine();
                System.out.println("Ungültige Eingabe");
                continue;
            }

            switch (input) {
                case 1:
                    spieler.kaufe();
                    incrementTag();
                    break;
                case 2:
                    spieler.verkaufe();
                    incrementTag();
                    break;
                case 3:
                    spieler.reisen();
                    break;
                case 4:
                    // taetigkeitAnnehmen();
                    incrementTag();
                    break;
                case 5:
                    spieler.essen();
                    break;
                case 6:
                    incrementTag();
                    break;
                case 7:
                    beenden();
                    break;
                default:
                    System.out.println("Ungültige Eingabe");
                    break;
            }
        }

    }

    public static void incrementTag() {
        tag++;

        spieler.setLp(spieler.getLp() - 20);

        if (tag >= 100 || spieler.getLp() <= 0 || spieler.getLp() > 100) {
            beenden();
        }
    }

    public static List<Ort> getOrtListe() {
        return ortListe;
    }

    public static void beenden() {
        if (spieler.getLp() <= 0) {
            System.out.println("Sie sind gestorben. ");
        } else if (spieler.getLp() > 100) {
            System.out.println("Sie sind an Übergewicht gestorben.");
        } else if (tag >= 100) {
            System.out.println("Sie haben die 100 Tage überlebt. ");
        }
        System.out.println(spieler.getName() + ", Sie haben " + spieler.getGoldstuecke() + " GS erreicht. ");
        System.exit(0);
    }

    public static int getTag() {
        return tag;
    }
}
