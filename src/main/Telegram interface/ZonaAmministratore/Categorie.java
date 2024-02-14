package ZonaAmministratore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class Categorie
{
    TreeMap<String, ArrayList<String>> categories;

    public void setMappa(TreeMap<String, ArrayList<String>> mappa){
        this.categories = mappa;
    }
    public Categorie(String percorsoJson) //costruisco un oggetto categorie dando in input il percorso del file json da cui prendere i dati
    {
        try
        {
            Scanner fromjson=new Scanner(new File(percorsoJson));
            categories=new TreeMap<>();

            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();

            Gson gson = builder.create();
            BufferedReader bufferedReader = new BufferedReader(
                    new FileReader("Categorie.json"));
            categories = gson.fromJson(bufferedReader, TreeMap.class);
            System.out.println("output di debug: ecco com'è venuta la mappa");
            System.out.println(categories);

        } catch(FileNotFoundException e)
        {
            categories=new TreeMap<>();
            System.out.println("non ha trovato il file");
        }
    }

    public TreeMap<String, ArrayList<String>> getMap()
    {
        return categories;
    }
    public ArrayList<String> listaUrl(String key)
    {
        return categories.get(key);
    }

    public void aggiungiUrlACategoria(String categoria, String urlone) throws IOException //questo aggiunge un value ad un key
    {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        FileWriter writer = new FileWriter("ProvaPeCategorie.json");

        //writer.write("[");

        //aggiunge url all'oggetto Categorie, per ora non sul json
        if (categories.containsKey(categoria))
        {
            categories.get(categoria).add(urlone);
        }
        else
        {
            ArrayList<String> temp = new ArrayList<>();
            temp.add(urlone);
            categories.put(categoria, temp);
        }
        //qui poi scrive subito sul file una volta aggiunto qualcosa
        writer.write(gson.toJson(categories));
        writer.close();

    }

    public void writeJsonVersioneVecchia(String percorso){
        try {
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            FileWriter writer = new FileWriter(percorso);
            writer.write(gson.toJson(categories));
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
