import java.util.Scanner;

public class Spiel {
    private static Spieler spieler;
    private static int tag;


    public static void main(String[] args) {

        Ort ort1 = new Ort("Dorf1", )


        System.out.println("Geben Sie Ihren Namen ein: \n ");
        Scanner sc = new Scanner(System.in);

        spieler = new Spieler(sc.nextLine());

        System.out.println("Hallo " + spieler.getName());

        while (true){
            System.out.println("Sie befinden sich im " + spieler.jetzigerOrt.getName());
            switch(expression) {
                case x:
                    // code block
                    break;
                case y:
                    // code block
                    break;
                default:

            if(tag >= 100 || spieler.getLp() <= 0){
                break;
            }
        }
        System.out.println("Speilende. Sie haben " + spieler.getGoldstuecke() + " für " + tag +" Tage erreicht.");
    }
}
