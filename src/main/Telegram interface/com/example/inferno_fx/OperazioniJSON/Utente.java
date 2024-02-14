package com.example.inferno_fx.OperazioniJSON;

public class Utente
{
    //l'utente deve avere un feedback (commento + voto) (appartiene sia alla notizia che all'utente quindi servirà un'altra classe)
    //un user id così possiamo indicizzarlo (è un integer), prendiamo anche il nome così lo visualizziamo

    //EXTRA: Categorie preferite?
    //private int UserID; useless in teoria
    private String UserName;
    //mancava la password
    private String Password;

    private String ultimaNotizia; //lista di link delle notizie, così sappiamo con quali notizie ha interagito l'utente, così per esempio possiamo fare che non vengono rimandate di nuovo


    private boolean registrato = Boolean.FALSE;
    //l'amministratore può prendere le notizie con cui ha interagito l'utente e prendere
    //NotizieSalvate.getKey(il proprio userid).getFeedback
    //

    public Utente(String UserName, String Password) //costruttore senza ultimanotizia
    {
        //this.UserID=UserID;
        this.UserName=UserName;
        this.Password = Password;
        this.ultimaNotizia = null;
    }
    public Utente(String UserName, String Password, String ultimaNotizia) //costruttore con
    {
        //this.UserID=UserID;
        this.UserName=UserName;
        this.Password = Password;
        this.ultimaNotizia = ultimaNotizia;
    }

    public boolean isRegistrato() {
        return registrato;
    }

    public void setRegistrato(boolean registrato) {
        this.registrato = registrato;
    }

//metodi get
    //public int getUserID(){return UserID;}

    public String getUserName()
    {
        return UserName;
    }

    public String getPassword(){return this.Password;}

    public void setPassword(String Password){
        this.Password = Password;
    }

    public void setUserName(String Nome){
        this.UserName = Nome;
    }


    //metodo che deve essere invocato quando l'utente esce dal bot/è offline da 5 minuti ecc.
    public void salvaUtente(String linkUltimaNotizia) //metodo da chiamare nel codice del bot
    {
        this.ultimaNotizia=linkUltimaNotizia;
    }
}
