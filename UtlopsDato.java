import java.util.ArrayList;

public class UtlopsDato {
    String navn;
    ArrayList<Double> mengde = new ArrayList<>();
    ArrayList<String> dato = new ArrayList<>();

    public UtlopsDato(String navn, Double mengde, String bestfor){
        this.navn = navn;
        this.mengde.add(mengde);
        this.dato.add(bestfor);
    }

    public void oppdater(Double mengde, String bestfor){
        this.mengde.add(mengde);
        this.dato.add(bestfor);
    }


    public String eldste(){
        String temp = this.dato.get(0).substring(4, 8);
        int aar = Integer.parseInt(temp);
        temp = this.dato.get(0).substring(2, 4);
        int maaned = Integer.parseInt(temp);
        temp = this.dato.get(0).substring(0, 2);
        int dag = Integer.parseInt(temp);

        int indexEldste = 0;

        for (int index = 1; index < dato.size(); index++) {
            temp = this.dato.get(index).substring(4, 8);
            int aarNeste = Integer.parseInt(temp);
            temp = this.dato.get(index).substring(2, 4);
            int maanedNeste = Integer.parseInt(temp);
            temp = this.dato.get(index).substring(0, 2);
            int dagNeste = Integer.parseInt(temp);

            if(aar >= aarNeste){
                if(maaned >= maanedNeste){
                    if(dag >= dagNeste){
                        indexEldste = 1;
                        aar = aarNeste;
                        maaned = maanedNeste;
                        dag = dagNeste;
                    }
                }
            }
        }
        return this.dato.get(indexEldste);
    }
}

