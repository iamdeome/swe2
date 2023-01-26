public class Taetigkeit {

    private String name;
    int lohn;

    public Taetigkeit(String name, int lohn) {
        // Null checks
        assert name != null;

        this.name = name;
        this.lohn = lohn;
    }

    public String getName() {
        return name;
    }

    public int getLohn() {
        return lohn;
    }
}
