import java.util.List;

public class Ort {
    private final String name;
    int positionX;
    int positionY;
    Taetigkeit arbeit;
    List<Ware> angebot;

    boolean hatTaetigkeit;
    boolean istStadt;

    public Ort(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
