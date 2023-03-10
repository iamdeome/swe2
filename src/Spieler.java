import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Spieler {
    private final String name;
    private int goldstuecke;
    private int lp;
    List<Ware> wareListe = new ArrayList<>();
    Ort jetzigerOrt;

    public Spieler(String name, Ort startOrt) {
        // Null checks
        assert name != null;
        assert startOrt != null;

        this.name = name;
        this.goldstuecke = 100;
        this.lp = 100;
        this.jetzigerOrt = startOrt;
    }

    public int getGewicht() {
        int gewicht = 0;
        for (Ware ware : wareListe) {
            gewicht += ware.getGewicht();
        }
        return gewicht;
    }

    public int getBohnen() {
        int bohnen = 0;
        for (Ware ware : wareListe) {
            if (ware.isBohnen()) {
                bohnen++;
            }
        }
        return bohnen;
    }

    private void bohnenEssen() {
        int bohnen = getBohnen();
        if (bohnen == 0) {
            System.out.println("Du hast keine Bohnen mit Speck. LP -20");
            return;
        }

        // remove the first Bohnen mit Speck
        for (Ware ware : wareListe) {
            if (ware.isBohnen()) {
                wareListe.remove(ware);
                break;
            }
        }
        lp += 20;
    }

    public void essen() {
        if (getGoldstuecke() <= 0) {
            System.out.println("Du hast kein Geld um dir Essen zu kaufen.");
            return;
        }

        setGoldstuecke(getGoldstuecke() - 1);
        setLp(getLp() + 20);
        System.out.println("Du hast dich von lokalen Produkten ernähren. LP +20");

        if (getLp() > 100) {
            Spiel.beenden();
        }
    }

    public int getLp() {
        return lp;
    }

    public void setLp(int lp) {
        this.lp = lp;
    }

    public int getGoldstuecke() {
        return goldstuecke;
    }

    public void setGoldstuecke(int goldstuecke) {
        this.goldstuecke = goldstuecke;
    }

    public String getName() {
        return name;
    }

    public Ort getJetzigerOrt() {
        return jetzigerOrt;
    }

    public void kaufe() {
        // While the player is buying something
        while (true) {
            System.out.println();
            System.out.println("Was möchtest du kaufen?");
            System.out.println("Gewicht: " + getGewicht() + "kg" + "\tGoldstücke: " + getGoldstuecke());

            // Print all available wares
            List<Ware> angebot = getJetzigerOrt().getAngebot();
            System.out.println("0: Nichts kaufen");
            for (int i = 0; i < angebot.size(); i++) {
                System.out.println(
                        (i + 1) + ": " + angebot.get(i).getName() + " (" + angebot.get(i).getPreis() + " Goldstücke)");
            }

            // Check if the player entered a selected a valid ware
            int selected;
            try {
                selected = Spiel.sc.nextInt() - 1;
                if (selected < -1 || selected >= angebot.size()) {
                    throw new Exception();
                }
            } catch (Exception e) {
                Spiel.sc.nextLine();
                System.out.println("Ungültige Eingabe");
                continue;
            }

            // Check if the player wants to buy something
            if (selected == -1) {
                break;
            }

            // Check if the player entered a valid amount
            System.out.println("Wie viele möchtest du kaufen?");
            int anzahl;
            try {
                anzahl = Spiel.sc.nextInt();
                if (anzahl < 0) {
                    throw new Exception();
                }
            } catch (Exception e) {
                Spiel.sc.nextLine();
                System.out.println("Ungültige Eingabe");
                continue;
            }

            // Check if the player can carry the wares
            int gewicht = angebot.get(selected).getGewicht() * anzahl;
            if (gewicht + getGewicht() > 100) {
                System.out.println("Du kannst nicht mehr als 100kg tragen");
                continue;
            }

            // Check if the player has enough money
            int preis = angebot.get(selected).getPreis() * anzahl;
            if (preis > getGoldstuecke()) {
                System.out.println("Du hast nicht genug Goldstücke");
                continue;
            }

            // Buy the wares
            for (int i = 0; i < anzahl; i++) {
                wareListe.add(angebot.get(selected));
            }

            // Pay for the wares
            setGoldstuecke(getGoldstuecke() - preis);

            // Print receipt
            System.out.println("Du hast " + anzahl + " " + angebot.get(selected).getName() + " gekauft für " + preis
                    + " Goldstücke");
        }
    }

    public void arbeiten() {
        Random random = new Random(Spiel.getTag() + Spiel.random);
        if (random.nextInt(100) < 50) {
            System.out.println("Heute wird keine Arbeit angeboten");
            return;
        }

        setGoldstuecke(getGoldstuecke() + getJetzigerOrt().getArbeit().getLohn());

        System.out.println("Sie haben diese Tätigkeit gemacht: " + getJetzigerOrt().getArbeit().getName());
        System.out.println("Sie haben " + getJetzigerOrt().getArbeit().getLohn() + " Goldstücke verdient");
        Spiel.incrementTag();
    }

    public void verkaufe() {
        while (true) {

            // Display all wares
            System.out.println();
            System.out.println("Was möchtest du verkaufen?");
            System.out.println("Gewicht: " + getGewicht() + "kg" + "\tGoldstücke: " + getGoldstuecke());
            System.out.println("0: Nichts verkaufen");

            // Calculate the amount of each ware
            HashMap<Ware, Integer> anzahlMap = new HashMap<>();
            for (Ware ware : wareListe) {
                // Skip wares that are not for sale
                if (ware.isBohnen())
                    continue;

                if (anzahlMap.containsKey(ware)) {
                    anzahlMap.put(ware, anzahlMap.get(ware) + 1);
                } else {
                    anzahlMap.put(ware, 1);
                }
            }

            // Print all wares
            int i = 1;
            for (Ware ware : anzahlMap.keySet()) {
                int preis = jetzigerOrt.preisFuer(ware);
                System.out.println(i + ": " + ware.getName() + " für " + preis + " Goldstücke (" + anzahlMap.get(ware)
                        + " Stück)");
                i++;
            }

            // Check if the player entered a selected a valid ware
            int selectedIndex;
            try {
                selectedIndex = Spiel.sc.nextInt() - 1;
                if (selectedIndex < -1 || selectedIndex >= anzahlMap.size()) {
                    throw new Exception();
                }
            } catch (Exception e) {
                Spiel.sc.nextLine();
                System.out.println("Ungültige Eingabe");
                continue;
            }

            // Check if the player wants to sell something
            if (selectedIndex == -1) {
                break;
            }

            // Get the selected ware
            Ware selected = (Ware) anzahlMap.keySet().toArray()[selectedIndex];

            System.out.println("Wie viele möchtest du verkaufen?");
            // Check if the player entered a valid amount
            int anzahl;
            try {
                anzahl = Spiel.sc.nextInt();
                if (anzahl < 0 || anzahl > anzahlMap.get(selected)) {
                    throw new Exception();
                }
            } catch (Exception e) {
                Spiel.sc.nextLine();
                System.out.println("Ungültige Eingabe");
                continue;
            }

            // Sell the wares
            for (int j = 0; j < anzahl; j++) {
                wareListe.remove(selected);
            }

            // increase the players money
            int preis = jetzigerOrt.preisFuer(selected) * anzahl;
            setGoldstuecke(getGoldstuecke() + preis);

            // Print receipt
            System.out.println("Du hast " + anzahl + " " + selected.getName() + " verkauft für " + preis
                    + " Goldstücke");
        }
    }

    public void reisen() {

        System.out.println("Wohin möchtest du reisen?");
        ArrayList<Ort> ziele = new ArrayList<Ort>(Spiel.getOrtListe());

        // Filter out the current location
        ziele.remove(jetzigerOrt);

        // Show all available destinations
        System.out.println("0: Nicht reisen");
        for (int i = 0; i < ziele.size(); i++) {
            double distance = jetzigerOrt.berechneEntfernungZu(ziele.get(i));
            int days = Ort.distanceToDays(distance);
            System.out.println((i + 1) + ": " + ziele.get(i).getName() + " ("
                    + days + " tage, " + Math.round(distance) + "km)");
        }

        // Check if the player entered a valid destination
        int selected;
        try {
            selected = Spiel.sc.nextInt() - 1;
            if (selected < -1 || selected >= ziele.size()) {
                throw new Exception();
            }
        } catch (Exception e) {
            Spiel.sc.nextLine();
            System.out.println("Ungültige Eingabe");
            return;
        }

        // Check if the player wants to travel
        if (selected == -1) {
            return;
        }

        // Increase the days
        double distance = jetzigerOrt.berechneEntfernungZu(ziele.get(selected));
        int days = Ort.distanceToDays(distance);
        for (int i = 0; i < days; i++) {
            Spiel.incrementTag();

            // Bohnen essen
            if (getLp() <= 80 && getBohnen() > 0) {
                bohnenEssen();
            }
        }

        // Travel to the selected destination
        jetzigerOrt = ziele.get(selected);
    }

}
