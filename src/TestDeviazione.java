public class TestDeviazione {
    public static void main(String[] args) {
        Griglia griglia = new Griglia(8);
        griglia.posizionaAtomo(3, 3);
        griglia.posizionaAtomo(5, 5);

        Raggio r1 = new Raggio(2, -1, 0, 1, griglia);
        System.out.println(r1.calcolaPercorso());

        Raggio r2 = new Raggio(-1, 6, 1, 0, griglia);
        System.out.println(r2.calcolaPercorso());
    }    
}
