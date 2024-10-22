import java.util.HashMap;
import java.util.List;


public class Lager {

    private static HashMap<String, Vare> lager = new HashMap<>();

    public Lager() {}

    public void oppdaterLager(String navn, Double mengde, String enhet, Double pris, String bestfor){
        navn = navn.toLowerCase();
        boolean nyVare =! lager.containsKey(navn);

        if (nyVare) {
            // Legg til nytt produkt i lageret
            UtlopsDato dato = new UtlopsDato(navn, mengde, bestfor);
            lager.put(navn, new Vare(navn, mengde, enhet, pris, dato));
        } else {
            // Oppdater eksisterende produkt
            Vare eksisterendeVare = lager.get(navn);
            
            // Konverter mengde hvis nødvendig
            if (!eksisterendeVare.enhet.equals(enhet)) {
                mengde = enhetskalkulator(mengde, enhet, eksisterendeVare.enhet);
            }

            // Oppdater mengde
            eksisterendeVare.mengde += mengde;

            // Oppdater pris hvis den er forskjellig og ikke null
            if (pris != 0) {
                eksisterendeVare.pris = pris;
            }

            //legg til i dato greia
            //eksisterendeVare.dato = 
            eksisterendeVare.bestfor.oppdater(mengde, bestfor); 





        }
    }


    private Double enhetskalkulator(Double mengde, String enhetFra, String enhetTil) {
        List<String> volum = List.of("mL", "cL", "dL", "L", "daL", "hL", "kL");
        List<String> vekt = List.of("mg", "cg", "dg", "g", "dag", "hg", "kg");

        int indeksFra = finnEnhetsIndeks(enhetFra, volum, vekt);
        int indeksTil = finnEnhetsIndeks(enhetTil, volum, vekt);

        // Hvis begge enheter er i samme kategori (volum eller vekt)
        if (indeksFra != -1 && indeksTil != -1) {
            int kategoriFra = indeksFra / 10; //blir 0 dersom det er volum(index 0-6) og 1 dersom det er vekt(index 10-16)
            int kategoriTil = indeksTil / 10;

            if (kategoriFra == kategoriTil) {
                // Konverter mengde
                int f = indeksFra % 10; //gir resten av (0-6 eller 10-16) / 10, som blir 0-6 og man får den opprinnelige indexen til vekt
                int t = indeksTil % 10;
                return mengde * Math.pow(10, (f - t)); // regner ut den nye mengden med den gamle enheten
            }
        }

        // Returner -1 som feilmelding hvis enhetene ikke kan konverteres
        return -1.0;
    }

    private int finnEnhetsIndeks(String enhet, List<String> volum, List<String> vekt) {
        if (volum.contains(enhet)) {
            return volum.indexOf(enhet); // 0 til 6 for volum
        } else if (vekt.contains(enhet)) {
            return vekt.indexOf(enhet) + 10; // 10 til 16 for vekt
        }
        return -1; // Returner -1 hvis enheten ikke finnes
    }


    //Brukes til å skape utlopsdato
    public void oppdaterBestfor(String navn, Double mengde, String dato){
        
    }

    

    public Vare hentVare(String key) {
        return lager.get(key.toLowerCase());
    }
}
