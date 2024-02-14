package com.example.inferno_fx.OperazioniJSON;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class gestoreGsonUtente {


    public void writeJson(ArrayList<Utente> listonaScrittura) throws IOException { //RICORDARSI DI METTERE TRY CATCH
        //gli diamo in input un array giusto vero

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        FileWriter writer = new FileWriter("ListaUtentiVari.json");

        String ilToJson = gson.toJson(listonaScrittura);
        writer.write(ilToJson);
        writer.close();
    }

    public boolean aggiungiUtenteJSON(Utente daPrendere) throws IOException { //RICORDARSI DI METTERE TRY CATCH
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        //RICORDARE DI ELIMINARE IL FILE "ListaUtentiVari.json" SE è VUOTO
        ArrayList<Utente> daControllare=new ArrayList<>();
        daControllare=readJsonLista("ListaUtentiVari.json");

        FileWriter writer = new FileWriter("ListaUtentiVari.json");

        System.out.println("ecco la lista di utenti intanto:");
        for(Utente U: daControllare){
            System.out.println("CLASSONA: " + U.getUserName() + "PASS: "+ U.getPassword());
        }


        for(Utente U: daControllare){
            if(U.getUserName().equals(daPrendere.getUserName())){
                System.out.println("AH! ho trovato un nome uguale indi non scrivo un cazzo");
                return false;
            }
        }
        System.out.println("in teoria non ho trovato utenti duplicati indi scrivo tutto l'array");
        daControllare.add(daPrendere);
        writer.write(gson.toJson(daControllare));
        writer.close();
        return true;


    }



    public ArrayList<Utente> readJsonLista(String percorso) throws FileNotFoundException {
        try
        {
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();

            Gson gson = builder.create();
            FileReader fileread=new FileReader("ListaUtentiVari.json");
            BufferedReader bufferedReader = new BufferedReader(fileread);
            Type mapType = new TypeToken<ArrayList<Utente>>(){}.getType();
            ArrayList<Utente> lista= gson.fromJson(bufferedReader, mapType);

            //ho tolto questo System out che mi stava innervosendo
            //System.out.println("ecco ho finito di leggere la lista, ecchitela: "+lista.toString());

            return lista;
        }catch(FileNotFoundException e)
        {
            System.out.println("nessun file trovato di conseguenza presumo sia vuoto, allora niente eccoti un arraylist vuoto");
            ArrayList<Utente> lista=new ArrayList<>();
            //returna null?? no vero??
            return lista;
        }

    }
}








