package ZonaFeedConClassi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonStreamParser;
import com.google.gson.reflect.TypeToken;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class gestoreGsonUtentone
{
    public void writeJson(ArrayList<Utente> listaScrittura) throws IOException { //RICORDARSI DI METTERE TRY CATCH
        try
        {
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();

            Gson gson = builder.create();
            FileWriter writer = new FileWriter("ListaUtentiVari.json");

            String ilToJson = gson.toJson(listaScrittura);
            writer.write(ilToJson);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkStatus(ArrayList<Utente> listaUtenti, String nomeDaVerificare, String password){
        for(Utente U: listaUtenti){
            if(nomeDaVerificare.equals(U.getUserName()) && password.equals((U.getPassword()))){
                U.setOnline(true);
                try{
                    writeJson(listaUtenti);
                }catch(IOException E){
                    System.out.println("errore di scrittura");
                }
                return true;
            }
        }
        return false;
    }

    public boolean checkChatId(ArrayList<Utente> listaUtenti, long chatid){
        for(Utente U: listaUtenti){
            if(chatid==U.getChatId())
            {
                System.out.println("debug utente esiste già");
                return true;
            }
        }
        return false;
    }

    public boolean aggiungiUtenteJSON(Utente daPrendere) throws IOException
    {
        try
        {
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            //RICORDARE DI ELIMINARE IL FILE "ListaUtentiVari.json" SE è VUOTO
            ArrayList<Utente> daControllare=new ArrayList<>();
            daControllare=readJsonLista();

            /*dEBUG QUA SOTTO
            System.out.println("ecco la lista di utenti intanto:");
            for(Utente U: daControllare){
                System.out.println("CLASSONA: " + U.getUserName() + "PASS: "+ U.getPassword());
            }
            FINE DEBUG*/


            for(Utente U: daControllare)
            {
                if(U.getUserName().equals(daPrendere.getUserName())){
                    System.out.println("AH! ho trovato un nome uguale indi non scrivo un cazzo");
                    return false;
                }
            }
            System.out.println("in teoria non ho trovato utenti duplicati indi scrivo tutto l'array");
            FileWriter writer = new FileWriter("ListaUtentiVari.json");
            daControllare.add(daPrendere);
            writer.write(gson.toJson(daControllare));
            writer.close();
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean verificaUtenteJson(Utente daPrendere)
    {

        boolean verificaOnlineDef=false;

        try {
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            //RICORDARE DI ELIMINARE IL FILE "ListaUtentiVari.json" SE è VUOTO
            ArrayList<Utente> daControllare = new ArrayList<>();
            daControllare = readJsonLista();

            verificaOnlineDef = checkStatus(daControllare,daPrendere.getUserName(),daPrendere.getPassword());

            return verificaOnlineDef;

        }catch (IOException E){
            System.out.println("errore in fase di login");
        }
        return  verificaOnlineDef;

    }



    public ArrayList<Utente> readJsonLista() throws FileNotFoundException {
        try
        {
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            FileReader fileread=new FileReader("ListaUtentiVari.json");
            BufferedReader bufferedReader = new BufferedReader(fileread);
            Type mapType = new TypeToken<ArrayList<Utente>>(){}.getType();
            ArrayList<Utente> lista= gson.fromJson(bufferedReader, mapType);
            return lista;
        }catch(FileNotFoundException e)
        {
            System.out.println("nessun file trovato di conseguenza presumo sia vuoto, allora niente eccoti un arraylist vuoto");
            ArrayList<Utente> lista=new ArrayList<>();
            //returna null?? no vero??
            return lista;
        }
    }

    public void rimuoviUtente(String nome) throws FileNotFoundException
    {
        ArrayList<Utente> utenti=readJsonLista();
        for(Utente u:utenti)
        {
            if(u.getUserName().equals(nome))
            {
                System.out.println("gestoreGsonUtente: ah utente trovato! provvedo a rimuoverlo");
                utenti.remove(u);
                return;
            }
        }
        System.out.println("gestoreGsonUtente: non ho trovato l'utente da rimuovere");
    }
}
