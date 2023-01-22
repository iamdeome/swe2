import java.util.List;

public class Spieler {
    private final String name;
    private int goldstuecke;
    private int lp;
    boolean darfKaufen;
    boolean darfVerkaufen;
    Spiel spiel;
    List<Ware> wareListe;
    Ort jetzigerOrt;

    public Spieler(String name) {
        this.name = name;
        this.goldstuecke = 100;
        this.lp = 100;
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

    public void kaufe(){

    }

    public void verkaufe(){

    }

    public void reisen(){

    }

}
