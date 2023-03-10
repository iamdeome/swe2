import java.util.List;
import java.util.Random;

public class Ort {
    private final String name;
    private final double longitude;
    private final double latutude;
    private final Taetigkeit arbeit;
    private final boolean istStadt;

    private List<Ware> angebot;

    public Ort(String name, double longitude, double latutude, Taetigkeit arbeit, List<Ware> angebot,
            boolean istStadt) {
        // Null checks
        assert name != null;
        assert arbeit != null;
        assert angebot != null;

        this.name = name;
        this.longitude = longitude;
        this.latutude = latutude;
        this.arbeit = arbeit;
        this.angebot = angebot;
        this.istStadt = istStadt;

        // Set gekauftIn for all angebot
        for (Ware ware : angebot) {
            ware.setGekauftIn(this);
        }
    }

    public int preisFuer(Ware ware) {
        double preis = (double) ware.getPreis();
        // Einkaufspreis * (1 + 0.1 * Tagesreisen von Dorf in Stadt)
        preis *= 1d + (0.1d * (Ort.distanceToDays(this.berechneEntfernungZu(ware.getGekauftIn()))));
        // Interesse
        Random r = new Random(Spiel.getTag() + Spiel.random);
        int interesse = r.nextInt(4);
        if (interesse == 0) {
            // 1/4 chance to increase price by 100%
            preis *= 2d;
        } else if (interesse == 1) {
            // 1/4 chance to decrease price by 100%
            preis *= 0d;
        }
        return (int) Math.round(preis);
    }

    public static int distanceToDays(double distance) {
        // Convert distance to days
        return (int) Math.ceil(distance / 75);
    }

    public double berechneEntfernungZu(Ort ort) {
        // Null checks
        assert ort != null;

        // Distance formula (https://en.wikipedia.org/wiki/Distance#Pythagorean_theorem)
        // latex: \sqrt{(x_2 - x_1)^2 + (y_2 - y_1)^2}
        // unicode: √((x₂ - x₁)² + (y₂ - y₁)²)
        double distance = Math.sqrt(Math.pow(ort.getLongitude() - this.getLongitude(), 2)
                + Math.pow(ort.getLatutude() - this.getLatutude(), 2));

        // Convert latitude/longitude distance to kilometers
        return distance * 111.2;
    }

    public String getName() {
        return name;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatutude() {
        return latutude;
    }

    public Taetigkeit getArbeit() {
        return arbeit;
    }

    public List<Ware> getAngebot() {
        return angebot;
    }

    public boolean istStadt() {
        return istStadt;
    }
}
