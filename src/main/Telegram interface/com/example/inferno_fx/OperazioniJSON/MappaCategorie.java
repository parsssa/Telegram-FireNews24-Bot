package com.example.inferno_fx.OperazioniJSON;



import java.util.ArrayList;
import java.util.TreeMap;

public class MappaCategorie{
    TreeMap<String, ArrayList<String>> mappa;


    public MappaCategorie(String nomePercorso) {

        this.mappa = new TreeMap<>();

        gestoreGsonCategorie gestorone = new gestoreGsonCategorie();
        try {
            ArrayList<Categoria> temporaneo = gestorone.readJSONArray(nomePercorso);
            for (Categoria C : temporaneo) {
                this.mappa.put(C.titolo, C.link);
            }
        }catch (Error e){
            System.out.println("per questioni di compatibilita' uso convertReadJson");
            ArrayList<Categoria> temporaneo = gestoreGsonCategorie.convertReadJson(nomePercorso);
            for (Categoria C : temporaneo) {
                this.mappa.put(C.titolo, C.link);
            }
        }
    }




    public TreeMap<String, ArrayList<String>> getMappa()
    {
        return mappa;
    }


}
