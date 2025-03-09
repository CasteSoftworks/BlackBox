public class TestRimbalzo {
    public static void main(String[] args) {
        Griglia griglia = new Griglia(8);
        griglia.posizionaAtomo(3, 0);
        griglia.posizionaAtomo(5, 5);

        System.out.println(griglia.toString());

        Raggio r1 = new Raggio(2, -1, 0, 1, griglia);
        System.out.println(r1.calcolaPercorso());

        Raggio r2 = new Raggio(3, -1, 0, 1, griglia);
        System.out.println(r2.calcolaPercorso());

        Raggio r3 = new Raggio(4, 8, 0, -1, griglia);
        System.out.println(r3.calcolaPercorso());

        Raggio r4 = new Raggio(-1, 7, 1, 0, griglia);
        System.out.println(r4.calcolaPercorso());
    }    
}
