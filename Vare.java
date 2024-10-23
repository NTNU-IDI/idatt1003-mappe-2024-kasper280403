public class Vare {
    String navn;
    Double mengde;
    String enhet;
    Double pris;
    UtlopsDato bestfor;

    public Vare(String navn, Double mengde, String enhet, Double pris, UtlopsDato bestfor) {
        this.navn = navn.toLowerCase();
        this.mengde = mengde;
        this.enhet = enhet;
        this.pris = pris;
        this.bestfor = bestfor;
    }

    public String hentNavn(){
        return navn;
    }
    public Double hentMengde(){
        return mengde;
    }
    public String hentEnhet(){
        return enhet;
    }
    public Double hentPris(){
        return pris;
    }
    public String hentBestfor(){
        return bestfor.hentDato(bestfor.eldsteIndex());
    }
}
