package com.example.inferno_fx.OperazioniJSON;

import java.util.ArrayList;

public class Categoria {

    String titolo;
    ArrayList<String> link;

    public Categoria(String titolo, ArrayList<String> link){
        this.titolo=titolo;
        this.link=link;
    }

    public ArrayList<String> getFeed(){
        return this.link;
    }

    public String getTitolo() {
        return titolo;
    }
}
