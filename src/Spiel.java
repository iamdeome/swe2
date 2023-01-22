import java.util.Scanner;

public class Spiel {
    private static Spieler spieler;
    private static int tag;
    private static boolean spielBeenden = false;


    public static void main(String[] args) {

     //   Ort ort1 = new Ort("Dorf1", //)


        System.out.println("Geben Sie Ihren Namen ein: \n ");
        Scanner sc = new Scanner(System.in);

        spieler = new Spieler(sc.nextLine());
        tag = 1;

        System.out.println("Hallo " + spieler.getName() + "\n");
        System.out.println("Wilkommen zum \"Karavanen, Karavanen und mehr Karavanen\" \n");


        while (true){
            System.out.println("tag: " + tag + " \t " + " LP " + spieler.getLp() + " \t " + " GS " + spieler.getGoldstuecke());
            System.out.println("Sie befinden sich im \n");
            System.out.println("Wählen Sie eine Option aus: ");
            System.out.println("1. Ware kaufen. ");
            System.out.println("2. Ware verkaufen. ");
            System.out.println("3. Reisen. ");
            System.out.println("4. Tätigkeit annehmen. ");
            System.out.println("5. Spiel beenden. \n ");


            int input = sc.nextInt();

            switch(input) {
                case 1:
                    // code block
                    break;
                case 2:
                    // code block
                    break;
                case 3:
                    // code block
                    break;
                case 4:
                    // code block
                    break;
                case 5:
                    spielBeenden = true;
                    break;
            }

            if(tag >= 100 || spieler.getLp() <= 0 || spielBeenden){
                System.out.println("Spielende. Sie haben " + spieler.getGoldstuecke() + " GS erreicht. ");

                break;
            }

            tag++;
        }
    }
}
