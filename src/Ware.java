import java.util.List;

public class Ware {
    String name;
    int preis;
    int gewicht;
    Ort gekauftIn;

    public Ware(String name, int preis, int gewicht) {
        // Null checks
        assert name != null;
        assert preis >= 0;
        assert gewicht >= 0;

        this.name = name;
        this.preis = preis;
        this.gewicht = gewicht;
    }

    public void setGekauftIn(Ort gekauftIn) {
        // Null checks
        assert gekauftIn != null;

        this.gekauftIn = gekauftIn;
    }

    public String getName() {
        return name;
    }

    public int getPreis() {
        return preis;
    }

    public int getGewicht() {
        return gewicht;
    }

    public Ort getGekauftIn() {
        return gekauftIn;
    }

    public boolean isBohnen() {
        return name.equals("Bohnen mit Speck");
    }
}
