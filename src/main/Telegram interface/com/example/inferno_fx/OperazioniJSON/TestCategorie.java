package com.example.inferno_fx.OperazioniJSON;

import java.io.IOException;
import java.util.ArrayList;

public class TestCategorie {

    public static void main(String[] args) throws IOException {
        ArrayList<String> linkSport = new ArrayList<>();
        ArrayList<String> linkPolitica = new ArrayList<>();
        linkSport.add("https://www.ansa.it/sito/notizie/sport/sport_rss.xml");
        linkSport.add("https://www.gazzetta.it/rss/home.xml");
        linkPolitica.add("https://www.ansa.it/sito/notizie/politica/politica_rss.xml");


        Categoria sport = new Categoria("Sport", linkSport);
        Categoria politica = new Categoria("Politica", linkPolitica);
        ArrayList<Categoria> listaDiCategorie = new ArrayList<>();
        listaDiCategorie.add(sport);
        listaDiCategorie.add(politica);


        gestoreGsonCategorie.writeJson(listaDiCategorie, "outputCategorie.json");




        MappaCategorie mc1 = new MappaCategorie("outputCategorie.json");
        System.out.println(mc1.getMappa());
    }
}
