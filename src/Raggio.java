public class Raggio {
    private int riga;
    private int colonna;
    private int deltaRiga;
    private int deltaColonna;
    private final Griglia griglia;

    private int dove;

    public Raggio(int riga, int colonna, int deltaRiga, int deltaColonna, Griglia griglia) {
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
            System.err.println("\tRaggio in (" + riga + ", " + colonna + ")");

            // Controlla se il raggio rimbalza
            if (controllaRimbalzo(riga, colonna)) {
                System.err.println("\tRimbalzo");
                return "Rimbalza in (" + riga + ", " + colonna + ")";
            }
            // Prima di muoversi, controlla se colpisce un atomo
            int prossimaRiga = riga + deltaRiga;
            int prossimaColonna = colonna + deltaColonna;
            System.err.println("\tProssima posizione: (" + prossimaRiga + ", " + prossimaColonna + ")");

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
            if (riga < 0 || riga >= 8 || colonna < 0 || colonna >= 8) {
                return "Esce in (" + riga + ", " + colonna + ")";
            }

            // Controlla se il raggio si deve deviare
            if (controllaDeviazione()) {
                System.err.println("\tDeviato");
                deviaRaggio();
            }
        }
    }

    private boolean controllaRimbalzo(int riga, int colonna) {
        /*
         * ----|--   ----|--
         * . o + . V . . + o
         * . . . .   . . . .
         */
        if(griglia.presenteAtomo(0,colonna-1)||griglia.presenteAtomo(0,colonna+1)){
            return true;
        }
        /*
         * | o .   | . .
         * --+ . V --+ .
         * | . .   | o .
         */
        if(griglia.presenteAtomo(riga-1,0)||griglia.presenteAtomo(riga+1,0)){
            return true;
        }
        /*
         * . . . .   . . . .
         * . o + . V . . + o
         * ----|--   ----|--
         */
        if(griglia.presenteAtomo(7,colonna-1)||griglia.presenteAtomo(7,colonna+1)){
            return true;
        }
        /*
         * . o |   . . |
         * . +-- V . +--
         * . . |   . o |
         */
        if(griglia.presenteAtomo(riga-1,7)||griglia.presenteAtomo(riga+1,7)){
            return true;
        }

        return false;
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
