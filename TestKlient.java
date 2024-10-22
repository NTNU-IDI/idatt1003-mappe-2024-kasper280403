public class TestKlient {
    public static void main(String[] args) {
        
        Lager lager = new Lager();

        lager.oppdaterLager("Melk", 10.0, "L", 21.90, "12122024");

        Vare melk = lager.hentVare("melk");
        System.out.println(melk.hentNavn());
        System.out.println(melk.hentMengde());
        System.out.println(melk.hentBestfor());
        
        lager.oppdaterLager("melk", 200.0, "dL", 21.90, "10122024");
        lager.oppdaterLager("kniv", 2000.0, "stk", 300.90, "0");

        Vare kniv = lager.hentVare("kniv");

        System.out.println(melk.hentNavn());
        System.out.println(melk.hentMengde());
        System.out.println(melk.hentBestfor());

        System.out.println(kniv.hentNavn());
        System.out.println(kniv.hentMengde());
        System.out.println(kniv.hentEnhet());

    }
}
