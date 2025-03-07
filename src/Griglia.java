public class Griglia {
    private static final int DIM = 8;
    private boolean[][] atomi;

    /**
     * Costruisce una griglia vuota
     */
    public Griglia() {
        atomi = new boolean[DIM][DIM];
    }

    /**
     * Posiziona un atomo nella posizione specificata
     * 
     * @param x la coordinata x
     * @param y la coordinata y
     * @throws IllegalArgumentException se le coordinate non sono valide
     */
    public void posizionaAtomo(int x, int y) {
        if(x < 0 || x >= DIM || y < 0 || y >= DIM) {
            throw new IllegalArgumentException("Coordinate non valide");
        }
        atomi[x][y] = true;
    }

    /**
     * Verifica se un atomo è presente nella posizione specificata
     * 
     * @param x la coordinata x
     * @param y la coordinata y
     * @return true se l'atomo è presente, false altrimenti
     */
    public boolean presenteAtomo(int x, int y) {
        if(x < 0 || x >= DIM || y < 0 || y >= DIM) {
            return false;
        }
        return atomi[x][y];
    }
}
