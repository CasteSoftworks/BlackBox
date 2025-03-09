public class Menu {
    /**
     * Stampa il titolo del gioco
     */
    private static void stampaTitolo(){
        System.out.println(" ____    _               _     ____              ");
        System.out.println("|  _ \\  | |             | |   |  _ \\             ");
        System.out.println("| |_) | | |  __ _   ___ | | __| |_) |  ___ __  __");
        System.out.println("|  _ <  | | / _` | / __|| |/ /|  _ <  / _ \\\\ \\/ /");
        System.out.println("| |_) | | || (_| | | (__|   < | |_) || (_) |>  < ");
        System.out.println("|____/  |_| \\__,_| \\___||_|\\_\\|____/  \\___//_/\\_\\");
        System.out.println();

    }

    /**
     * Stampa il menu principale
     */
    private static void stampaMenu(){
        System.out.println("1. Inizia una nuova partita");
        System.out.println("2. Visualizza i punteggi");
        System.out.println("3. Esci");
    }

    /**
     * Legge la scelta dell'utente
     * 
     * @return la scelta dell'utente
     * @throws NumberFormatException se l'utente non inserisce un numero valido
     */
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

    /**
     * Esegue la scelta dell'utente
     * 
     * @param scelta la scelta dell'utente
     */
    private static void eseguiScelta(int scelta){
        switch(scelta){
            case 1 -> {
                iniziaPartita();
            }
            case 2 -> System.out.println("Punteggi non disponibili");
            case 3 -> System.exit(0);
        }
    }

    /*private static void stampaPunteggi(){

    }*/

    public static void main(String[] args) {
        stampaTitolo();
        stampaMenu();
        eseguiScelta(leggiScelta());
    }

    private static void iniziaPartita(){
        BlackBox blackBox = new BlackBox(8, 4);
        blackBox.iniziaPartita();
    }
}
