import java.util.ArrayList;

public class TestKlient {
    public static void main(String[] args) {
        
        Lager lager = new Lager();

        lager.oppdaterLager("Melk", 10.0, "L", 21.90, "12-12-2024");

        Vare melk = lager.hentVare("melk");
        System.out.println(melk.hentNavn());
        System.out.println(melk.hentMengde());
        System.out.println(melk.hentBestfor());
        
        lager.oppdaterLager("melk", 200.0, "dL", 21.90, "10-12-2024");
        lager.oppdaterLager("kniv", 2000.0, "stk", 300.90, "00-00-0000");
        lager.oppdaterLager("ost", 1.04, "kg", 134.40, "20-01-2025");

        Vare kniv = lager.hentVare("kniv");

        System.out.println(melk.hentNavn());
        System.out.println(melk.hentMengde());
        System.out.println(melk.hentBestfor());

        System.out.println(kniv.hentNavn());
        System.out.println(kniv.hentMengde());
        System.out.println(kniv.hentEnhet());


        System.out.println(lager.hentVare("ost").hentMengde());
        
        ArrayList<String> ostliste = lager.vareTabell("ost");
        for(String i : ostliste){
            System.out.print(i+"--");
        }
        System.out.println("\n");

        skrivUtTabell();

    }

    public static void skrivUtTabell(){
        Lager lager = new Lager();
        ArrayList<ArrayList<String>> prodListe = lager.vareOversikt();

        // Skrive ut overskrift
        System.out.printf("%-10s %-10s %-10s %-10s %-15s%n", "Produkt", "Mengde", "Enhet", "Pris", "UtløpsDato");
        System.out.println("--------------------------------------------------------------");

        // Skrive ut data med formattering
        for (ArrayList<String> liste : prodListe) {
            System.out.printf("%-10s %-10s %-10s %-10s %-15s%n",
                    liste.get(0), // Produkt
                    liste.get(1), // Mengde
                    liste.get(2), // Enhet
                    liste.get(3), // Pris
                    liste.get(4)  // Dato
            );
        }
    }
}
