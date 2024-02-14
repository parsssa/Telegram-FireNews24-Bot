package com.example.inferno_fx;

import java.util.ArrayList;
import java.util.Arrays;

public class DatiAdmin {
    private ArrayList<String> listaAdmin;
    private String password;


    public DatiAdmin(){
        this.listaAdmin = new ArrayList<>();
        this.password = "";
    }

    public ArrayList<String> getListaAdmin() {
        return listaAdmin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void aggiungiAdmin(String idTelegram){
        this.listaAdmin.add(idTelegram);
    }


    //main che si esegue solo per inserire admin in un arrayList e determinare password
    /*public static void main(String[] args) {
        DatiAdmin datiAdmin = new DatiAdmin();
        datiAdmin.aggiungiAdmin("@Massimomanonpericolo");
        datiAdmin.setPassword("123ADMIN");

    }*/

}
