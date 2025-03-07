public class Menu {
    private static void stampaTitolo(){
        System.out.println(" ____    _               _     ____              ");
        System.out.println("|  _ \\  | |             | |   |  _ \\             ");
        System.out.println("| |_) | | |  __ _   ___ | | __| |_) |  ___ __  __");
        System.out.println("|  _ <  | | / _` | / __|| |/ /|  _ <  / _ \\\\ \\/ /");
        System.out.println("| |_) | | || (_| | | (__|   < | |_) || (_) |>  < ");
        System.out.println("|____/  |_| \\__,_| \\___||_|\\_\\|____/  \\___//_/\\_\\");
        System.out.println();

    }

    private static void stampaMenu(){
        System.out.println("1. Inizia una nuova partita");
        System.out.println("2. Visualizza i punteggi");
        System.out.println("3. Esci");
    }

    private static int leggiScelta(){
        int scelta = 0;
        while(scelta < 1 || scelta > 3){
            System.out.print("Inserisci la tua scelta: ");
            try{
                scelta = Integer.parseInt(System.console().readLine());
            }catch(NumberFormatException e){
                System.out.println("Inserisci un numero valido");
            }
        }

        return scelta;
    }

    private static void eseguiScelta(int scelta){
        switch(scelta){
            case 1 -> {
                String nome = leggiNome();
                iniziaPartita(nome);
            }
            case 2 -> System.out.println("Punteggi non disponibili");
            case 3 -> System.exit(0);
        }
    }

    /*private static void stampaPunteggi(){

    }*/

    private static String leggiNome(){
        System.out.print("Inserisci il tuo nome: ");
        return System.console().readLine();
    }

    public static void main(String[] args) {
        stampaTitolo();
        stampaMenu();
        eseguiScelta(leggiScelta());
    }

    private static void iniziaPartita(String nome){
        System.out.println("Inizia la partita per " + nome);
    }
}
