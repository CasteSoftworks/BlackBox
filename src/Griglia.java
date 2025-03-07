public class Griglia {
    private static final int DIM = 8;
    private boolean[][] atomi;

    /**
     * Costruisce una griglia vuota
     */
    public Griglia(int par) {
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

    public void svuotaGriglia() {
        for(int i = 0; i < DIM; i++) {
            for(int j = 0; j < DIM; j++) {
                atomi[i][j] = false;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("   -1 0 1 2 3 4 5 6 7 8\n-1  +-----------------+\n");
        for(int i = 0; i < DIM; i++) {
            sb.append(" ").append(i).append("  | ");
            for(int j = 0; j < DIM; j++) {
                sb.append(atomi[i][j] ? "O " : ". ");
            }
            sb.append("|\n");
        }
        sb.append(" 8  +-----------------+\n");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(getClass() != obj.getClass()) {
            return false;
        }
        Griglia other = (Griglia) obj;
        return java.util.Arrays.deepEquals(atomi, other.atomi);
    }

    @Override
    public int hashCode() {
        return java.util.Arrays.deepHashCode(atomi);
    }
}
