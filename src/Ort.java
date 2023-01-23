import java.util.List;

public class Ort {
    private final String name;
    private final double positionX;
    private final double positionY;
    private final Taetigkeit arbeit;
    private final boolean istStadt;

    private List<Ware> angebot;

    public Ort(String name, double positionX, double positionY, Taetigkeit arbeit, List<Ware> angebot, boolean istStadt) {
        // Null checks
        assert name != null;
        assert arbeit != null;
        assert angebot != null;

        this.name = name;
        this.positionX = positionX;
        this.positionY = positionY;
        this.arbeit = arbeit;
        this.angebot = angebot;
        this.istStadt = istStadt;
    }

    public String getName() {
        return name;
    }

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
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
