import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class Varer {
    String produkt;
    Double mengde;
    String enhet;
    Double pris;
    int bestfor;

    HashMap<String, List<String>> lager = new HashMap<>();

    public Varer(String p, Double m, String e, Double nok, int b){
        Boolean gate = true;
        p = p.toLowerCase();
        for(String n : lager.keySet()){
            if(p==n){
                gate = false;
            }
        }
        if(gate){
            lager.put(p, new ArrayList<>());
        }
        lager.get(p).add(m);
        
    }

    public int enhetskalkulator(int m, String EnhetFra, String EnhetTil){
        List<String> volum = List.of("mL", "cL", "dL", "L", "daL", "hL", "kL");
        List<String> vekt = List.of("mg", "cg", "dg", "g", "dag", "hg", "kg");
        a;
    }
}
