package com.example.inferno_fx.OperazioniJSON;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonStreamParser;
import ZonaAmministratore.Categorie;

import java.io.*;
import java.util.ArrayList;
import java.util.TreeMap;

public class gestoreGsonCategorie{

    //visto che abbiamo una formattazzione vecchia nel json di categorie del bot creo un metodo per la conversione
    public static ArrayList<Categoria> convertReadJson(String percorso){
        ArrayList<Categoria> listaCategorie = new ArrayList<>();
        Categorie vecchiaVersioneCat = new Categorie(percorso);
        TreeMap<String, ArrayList<String>> mappaVersioneVecchia = vecchiaVersioneCat.getMap();
        for(String s:mappaVersioneVecchia.keySet()){
            Categoria nuovaVersioneCat = new Categoria(s, mappaVersioneVecchia.get(s));
            listaCategorie.add(nuovaVersioneCat);
        }
        return listaCategorie;
    }
    public static void convertWriteJson(ArrayList<Categoria> listaCategorie, String percorso){
        TreeMap<String, ArrayList<String>> mappa = new TreeMap<>();
        for(Categoria categoriaNuova:listaCategorie){
            mappa.put(categoriaNuova.titolo,categoriaNuova.link);
        }
        Categorie categoriaVecchia = new Categorie(percorso);
        categoriaVecchia.setMappa(mappa);
        categoriaVecchia.writeJsonVersioneVecchia(percorso);
    }


    public Categoria readJson(String percorso) {
            //commento
            try {
                GsonBuilder builder = new GsonBuilder();
                builder.setPrettyPrinting();

                Gson gson = builder.create();
                BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(percorso)));

                Categoria categoria = gson.fromJson(bufferedReader, Categoria.class);
                return categoria;
            } catch (FileNotFoundException E) {

                System.out.println("Non riesco a trovare il file");
                return new Categoria("Categoria Vuota", new ArrayList<>());
            }
    }

    //WRITER PER CATEGORIE
    public static void writeJson(ArrayList<Categoria> categorie, String nomeFileOutput){
        /*GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        FileWriter writer = null;
        try {
            writer = new FileWriter(nomeFileOutput);


            for(Categoria C: categorie){
                writer.write(gson.toJson(C));
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        gestoreGsonCategorie.convertWriteJson(categorie,nomeFileOutput);
    }


    public ArrayList<Categoria> readJSONArray(String percorso) {
        try {
            ArrayList<Categoria> listaCategorie = new ArrayList<>();
            try {
                InputStream is = new FileInputStream(percorso);
                Reader r = new InputStreamReader(is, "UTF-8");
                Gson gson = new GsonBuilder().create();
                JsonStreamParser p = new JsonStreamParser(r);

                while (p.hasNext()) {
                    JsonElement e = p.next();
                    if (((JsonElement) e).isJsonObject()) {
                        Categoria categoria = gson.fromJson(e, Categoria.class);
                        listaCategorie.add(categoria);
                    }
                }
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            return listaCategorie;
        }catch (Exception e){
            System.out.println("Si tratta di formatto Categorie vecchio quindi uso convertReadJSON");
            return gestoreGsonCategorie.convertReadJson(percorso);
        }
    }
}











