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
            // Prima di muoversi, controlla se colpisce un atomo
            int prossimaRiga = riga + deltaRiga;
            int prossimaColonna = colonna + deltaColonna;

            if (griglia.presenteAtomo(prossimaRiga, prossimaColonna)) {
                return "Assorbito in (" + prossimaRiga + ", " + prossimaColonna + ")";
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
                deviaRaggio();
            }
        }
    }

    /**
     * Controlla se il raggio deve deviare a causa di un atomo nelle vicinanze e imposta la direzione di deviazione
     * 
     * @return true se il raggio deve deviare, false altrimenti
     */
    private boolean controllaDeviazione() {
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
     * Devia il raggio in base alla direzione impostata
     * <ul>
     * <li>0: da sx a giu o viceversa</li>
     * <li>1: da sx a su o viceversa</li>
     * <li>2: da dx a su o viceversa</li>
     * <li>3: da giu a dx o viceversa</li>
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
    }

    @Override
    public String toString() {
        return "Raggio(" + riga + ", " + colonna + ", " + deltaRiga + ", " + deltaColonna + ")";
    }
}
