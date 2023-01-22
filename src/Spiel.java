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
            System.out.println("wählen Sie eine Option: ");
            System.out.println("1. Ware kaufen. ");
            System.out.println("2. Ware verkaufen. ");
            System.out.println("3. Reisen. ");
            System.out.println("4. Tätigkeit annehmen. ");
            System.out.println("5. Spiel beenden. ");


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

                    break;
                default:

            if(tag >= 100 || spieler.getLp() <= 0){
                break;
            }
        }
        System.out.println("Speilende. Sie haben " + spieler.getGoldstuecke() + " für " + tag +" Tage erreicht.");
    }
}



        1
    2       3
4       5       6