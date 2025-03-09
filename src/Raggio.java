public class Raggio {
    /*
     * AF:
     * Un raggio è descritto da una riga e una colonna di partenza, una variazione di riga e una variazione di colonna e una griglia su cui si muove.
     * 
     * IR:
     * riga e colonna sono comprese tra -1 e 8
     * deltaRiga e deltaColonna sono 1 o -1
     * griglia non è null
     */
    private int riga;
    private int colonna;
    private int deltaRiga;
    private int deltaColonna;
    private final Griglia griglia;

    private int dove;

    /**
     * Costruttore di un raggio
     * 
     * @param riga la riga di partenza (-1 per partire dall'alto, 8 per partire dal basso)
     * @param colonna la colonna di partenza (-1 per partire da sinistra, 8 per partire da destra)
     * @param deltaRiga la variazione di riga (1 per andare dall'alto al basso, -1 per andare dal basso all'alto)
     * @param deltaColonna la variazione di colonna (1 per andare da sinistra a destra, -1 per andare da destra a sinistra)
     * @param griglia la griglia su cui si muove il raggio
     * 
     * @throws IllegalArgumentException se riga non è compresa tra -1 e 8, se colonna non è compresa tra -1 e 8, se deltaRiga non è 1 o -1, se deltaColonna non è 1 o -1
     * @throws NullPointerException se griglia è null
     */
    public Raggio(int riga, int colonna, int deltaRiga, int deltaColonna, Griglia griglia) throws IllegalArgumentException, NullPointerException {
        if(riga<-1||riga>8){
            throw new IllegalArgumentException("La riga deve essere compresa tra -1 e 8");
        }
        if(colonna<-1||colonna>8){
            throw new IllegalArgumentException("La colonna deve essere compresa tra -1 e 8");
        }
        if(deltaRiga!=1&&deltaRiga!=-1){
            throw new IllegalArgumentException("La variazione di riga deve essere 1 o -1");
        }
        if(deltaColonna!=1&&deltaColonna!=-1){
            throw new IllegalArgumentException("La variazione di colonna deve essere 1 o -1");
        }
        if(griglia==null){
            throw new NullPointerException("La griglia non può essere null");
        }
        this.riga = riga;
        this.colonna = colonna;
        this.deltaRiga = deltaRiga;
        this.deltaColonna = deltaColonna;
        this.griglia = griglia;
    }

    /**
     * Calcola il percorso del raggio
     * 
     * @return una stringa che descrive il percorso del raggio
     */
    public String calcolaPercorso() {
        while (true) {           
            // Prima di muoversi, controlla se colpisce un atomo
            int prossimaRiga = riga + deltaRiga;
            int prossimaColonna = colonna + deltaColonna;

            String r=controllaRimbalzo(prossimaRiga, prossimaColonna);
            if(r!=null){
                System.err.println("\tRimbalzo");
                return r;
            }

            // Controlla se il raggio viene assorbito subito
            if (controllaAssorbimento(prossimaRiga, prossimaColonna)) {
                System.err.println("\tAssorbito in (" + prossimaRiga + ", " + prossimaColonna + ")");
                return "Assorbito in (" + prossimaRiga + ", " + prossimaColonna + ")";
            }
            // Controlla se il raggio viene assorbito al prossimo passo (utile per evitare situazioni di deviazione anziché assorbimento per atomi adiacenti)
            if(controllaAssorbimento(prossimaRiga+deltaRiga, prossimaColonna+deltaColonna)){
                System.err.println("\tAssorbito in (" + (prossimaRiga+deltaRiga) + ", " + (prossimaColonna+deltaColonna) + ")");
                return "Assorbito in (" + (prossimaRiga+deltaRiga) + ", " + (prossimaColonna+deltaColonna) + ")";
            }

            // Aggiorna la posizione del raggio
            riga = prossimaRiga;
            colonna = prossimaColonna;

            // Se esce dalla griglia, restituisce il punto di uscita
            if (controllaUscita(riga, colonna)) {
                return "Esce in (" + riga + ", " + colonna + ")";
            }

            // Controlla se il raggio si deve deviare
            if (controllaDeviazione()) {
                System.err.println("\tDeviato");
                deviaRaggio();
            }
        }
    }

    /**
     * Controlla se il raggio esce dalla griglia
     * 
     * @param riga la riga
     * @param colonna la colonna
     * @return true se il raggio esce dalla griglia, false altrimenti
     */
    private boolean controllaUscita(int riga, int colonna) {
        return riga < 0 || riga >= 8 || colonna < 0 || colonna >= 8;
    }

    /**
     * Controlla se il raggio rimbalza
     * 
     * @param riga la riga
     * @param colonna la colonna
     * @return una stringa che descrive il rimbalzo del raggio (null se non rimbalza)
     */
    private String controllaRimbalzo(int riga, int colonna) {
        if((riga==0||riga==7||colonna==0||colonna==7)&&(griglia.presenteAtomo(riga, colonna+1)||griglia.presenteAtomo(riga, colonna-1)||griglia.presenteAtomo(riga+1, colonna)||griglia.presenteAtomo(riga-1, colonna))){
            return "Rimbalza in (" + riga + ", " + colonna + ")";
        }

        return null;
    }

    /**
     * Controlla se il raggio colpisce un atomo
     * 
     * @param prossimaRiga la prossima riga
     * @param prossimaColonna la prossima colonna
     * @return true se il raggio colpisce un atomo, false altrimenti
     */
    private boolean controllaAssorbimento(int prossimaRiga, int prossimaColonna) {
        return griglia.presenteAtomo(prossimaRiga, prossimaColonna);
    }

    /**
     * Controlla se il raggio deve deviare:
     * <ul>
     * <li> a causa di un atomo nelle vicinanze --> deviazione semplice
     * <li> a causa di due atomi nelle vicinanze --> inversione
     * </ul>
     * 
     * @return true se il raggio deve deviare, false altrimenti
     */
    private boolean controllaDeviazione() {
        //inversioni
        if(griglia.presenteAtomo(riga-1, colonna+1) && griglia.presenteAtomo(riga+1, colonna+1)){
            dove=4;
            return true;
        }
        if(griglia.presenteAtomo(riga+1, colonna+1) && griglia.presenteAtomo(riga+1, colonna-1)){
            dove=5;
            return true;
        }
        if(griglia.presenteAtomo(riga+1, colonna-1) && griglia.presenteAtomo(riga-1, colonna-1)){
            dove=6;
            return true;
        }
        if(griglia.presenteAtomo(riga-1, colonna-1) && griglia.presenteAtomo(riga-1, colonna+1)){
            dove=7;
            return true;
        }
        //deviazioni semplici
        if(griglia.presenteAtomo(riga-1, colonna+1)){
            dove=0;
            return true;
        }
        if(griglia.presenteAtomo(riga+1, colonna+1)){
            dove=1;
            return true;
        }
        if(griglia.presenteAtomo(riga+1, colonna-1)){
            dove=2;
            return true;
        }
        if(griglia.presenteAtomo(riga-1, colonna-1)){
            dove=3;
            return true;
        }
        return false;
    }

    /**
     * Devia il raggio in base alla direzione impostata da dove:
     * <ul>
     * <li>0: da sx a giu o viceversa</li>
     * <li>1: da sx a su o viceversa</li>
     * <li>2: da dx a su o viceversa</li>
     * <li>3: da giu a dx o viceversa</li>
     * <li>4: inversione da sx a dx</li>
     * <li>5: inversione da giu a su</li>
     * <li>6: inversione da dx a sx</li>
     * <li>7: inversione da su a giu</li>
     * </ul>
     */
    private void deviaRaggio() {
        /*
         *  . . o
         *  --+ .
         *  . | .
         */
        if(dove==0){
            //da sx
            if(deltaRiga==0){
                deltaRiga = deltaColonna;
                deltaColonna = 0;
            }else{ //da giu
                deltaColonna = deltaRiga;
                deltaRiga = 0;
            }
        }
        /*
         *  . | .
         *  --+ .
         *  . . o
         */
        if(dove==1){
            //da sx
            if(deltaRiga==0){
                deltaRiga = -deltaColonna;
                deltaColonna = 0;
            }else{ //da su
                deltaColonna = deltaRiga;
                deltaRiga = 0;
            }
        }
        /*
         *   . | .
         *   . +--
         *   o . .
         */
        if(dove==2){
            //da dx
            if(deltaRiga==0){
                deltaRiga = deltaColonna;
                deltaColonna = 0;
            }else{ //da su
                deltaColonna = deltaRiga;
                deltaRiga = 0;
            }
        }
        /*
         *   o . .
         *   . +--
         *   . | .
         */
        if(dove==3){
            //da dx
            if(deltaRiga==0){
                deltaRiga = -deltaColonna;
                deltaColonna = 0;
            }else{ //da giu
                deltaColonna = -deltaRiga;
                deltaRiga = 0;
            }
        }
        /*
         *  . . o
         *  --+ .
         *  . . o
         */
        if(dove==4){
            deltaColonna = -deltaColonna;
        }
        /*
         *  . | .
         *  . + .
         *  o . o
         */
        if(dove==5){
            deltaRiga = -deltaRiga;
        }
        /*
         *  o . .
         *  . +--
         *  o . .
         */
        if(dove==6){
            deltaColonna = -deltaColonna;
        }
        /*
         *  o . o
         *  . + .
         *  . | .
         */
        if(dove==7){
            deltaRiga = -deltaRiga;
        }
    }

    @Override
    public String toString() {
        return "Raggio(" + riga + ", " + colonna + ", " + deltaRiga + ", " + deltaColonna + ")";
    }
}
