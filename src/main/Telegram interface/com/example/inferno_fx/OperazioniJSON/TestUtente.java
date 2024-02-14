package com.example.inferno_fx.OperazioniJSON;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class TestUtente {

    public static void main(String[] args) {
        gestoreGsonUtente ggu = new gestoreGsonUtente();
        ArrayList<Utente> UsersList = new ArrayList<>();
        try {
            UsersList = ggu.readJsonLista("ListaUtentiVari.json");
        } catch (FileNotFoundException e) {
            System.out.println("File not trovato!!!!");
        }

        System.out.println(UsersList);
    }
}
