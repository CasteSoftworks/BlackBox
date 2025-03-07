public class BlackBox {
    private final Griglia grigliaVera;
    private final Griglia grigliaGiocatore;
    private final int numeroAtomi;
    private int numeroMosse;
    private int k=0;

    public BlackBox(int dimensione, int numeroAtomi){
        this.grigliaVera = new Griglia(8);
        this.grigliaGiocatore = new Griglia(8);
        this.numeroAtomi = numeroAtomi;
    }

    public void posizionaAtomi(){
        for(int i = 0; i < numeroAtomi; i++){
            grigliaVera.posizionaAtomo((int)(Math.random() * 8), (int)(Math.random() * 8));
        }
    }

    public void iniziaPartita(String nome){
        posizionaAtomi();
        numeroMosse = 0;
        gioca();
    }

    private void gioca(){
        Integer x, y, dx=0, dy=0;
        while(true){
            System.out.println(grigliaGiocatore.toString());
            System.out.println("Numero mosse: " + numeroMosse);
            System.out.println("""
                               Inserisci le coordinate del raggio:
                               > x =  -1 per partire dall'alto
                               > x = 8 per partire dal basso
                               > y = -1 per partire da sinistra
                               > y = 8 per partire da destra
                               """);
            System.out.println("Inserisci la coordinata x: ");
            x = Integer.valueOf(System.console().readLine());
            //dall'alto
            if(x==-1){
               dx=1;
               dy=0;
            }
            //dal basso
            if(x==8){
               dx=-1;
               dy=0;
            }
            System.out.println("Inserisci la coordinata y: ");
            y = Integer.valueOf(System.console().readLine());
            //da sinistra
            if(y==-1){
               dx=0;
               dy=1;
            }
            //da destra
            if(y==8){
               dx=0;
               dy=-1;
            }
            Raggio r = new Raggio(x, y, dx, dy, grigliaVera);
            System.out.println(r.calcolaPercorso());

            provaAdIndovinare();
            numeroMosse++;
        }
    }

    private void provaAdIndovinare(){
        System.out.println("Voui provare ad indovinare la posizione degli atomi? (s/n)");
        if(System.console().readLine().equals("n")){
            return;
        }

        System.out.println("Da capo? (s/n)");
        if(System.console().readLine().equals("s")){
            grigliaGiocatore.svuotaGriglia();
            k=0;
        }

        int x, y;
        for(; k < numeroAtomi; k++){
            System.out.println("Inserisci la coordinata x dell'atomo " + (k+1) + ": ");
            x = Integer.parseInt(System.console().readLine());
            System.out.println("Inserisci la coordinata y dell'atomo " + (k+1) + ": ");
            y = Integer.parseInt(System.console().readLine());
            
            grigliaGiocatore.posizionaAtomo(x, y);

            System.out.println("Vuoi posizionare un altro atomo? (s/n)");
            if(System.console().readLine().equals("n")){
                break;
            }
        }

        System.out.println("Griglia giocatore: ");
        System.out.println(grigliaGiocatore.toString());

        System.out.println("è la tua scelta definitiva? (s/n)");
        if(System.console().readLine().equals("s")){
            if(grigliaVera.equals(grigliaGiocatore)){
                System.out.println("Hai vinto!");
            } else {
                System.out.println("Hai perso!");
            }
            System.out.println("Griglia reale: ");
            System.out.println(grigliaVera.toString());
            System.exit(0);
        }
    }
}
