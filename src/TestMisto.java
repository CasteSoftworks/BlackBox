

public class TestMisto {
    public static void main(String[] args) {
        Griglia griglia = new Griglia();
        griglia.posizionaAtomo(3, 3);
        griglia.posizionaAtomo(5, 5);

        //da sx a dx deviato esce
        Raggio r1 = new Raggio(2, -1, 0, 1, griglia);
        System.out.println(r1.toString());
        System.out.println(r1.calcolaPercorso());

        //da su a giu esce diretto
        Raggio r2 = new Raggio(-1, 7, 1, 0, griglia);
        System.out.println(r2.toString());
        System.out.println(r2.calcolaPercorso());

        //da dx a sx assorbito
        Raggio r3 = new Raggio(3, 8, 0, -1, griglia);
        System.out.println(r3.toString());
        System.out.println(r3.calcolaPercorso());

        //da su a giu assorbito
        Raggio r4 = new Raggio(-1, 5, 1, 0, griglia);
        System.out.println(r4.toString());
        System.out.println(r4.calcolaPercorso());

        //da giu a su esce diretto
        Raggio r5 = new Raggio(1, 8, 0, 1, griglia);
        System.out.println(r5.toString());
        System.out.println(r5.calcolaPercorso());

        //da su a giu deviato esce
        Raggio r6 = new Raggio(-1, 6, 1, 0, griglia);
        System.out.println(r6.toString());
        System.out.println(r6.calcolaPercorso());
    }
}
