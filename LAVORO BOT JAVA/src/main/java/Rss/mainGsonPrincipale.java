package Rss;
import Gson.Studente;
import Gson.gestoreJson;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import Gson.FeedObj;
import com.rometools.rome.io.FeedException;
import Gson.Notizia;
import Gson.gestoreJson;

import java.io.IOException;
import java.util.ArrayList;

public class mainGsonPrincipale {

    public static void main(String[] args) throws FeedException, IOException {

        gestoreJson GESON = new gestoreJson();
        FeedObj vaffanculo = new FeedObj("https://www.repubblica.it/rss/homepage/rss2.0.xml");
        ArrayList<Notizia> questeNotizie = vaffanculo.newsList;
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        try {
             GESON.writeJSON(questeNotizie);

        }catch(IOException e){
            System.out.println("errore Json");
        }


    }




}
