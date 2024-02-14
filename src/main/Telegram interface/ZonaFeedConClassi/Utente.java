package ZonaFeedConClassi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.*;

public class Utente
{
    //l'utente deve avere un feedback (commento + voto) (appartiene sia alla notizia che all'utente quindi servirà un'altra classe)
    //un user id così possiamo indicizzarlo (è un integer), prendiamo anche il nome così lo visualizziamo

    //private int UserID; useless in teoria

    private String UserName;
    private String Password;
    private boolean Online;
    private long chatId;

    public Utente(String UserName, String Password, boolean Online, long chatId)
    {
        //this.UserID=UserID;
        this.UserName=UserName;
        this.Password = Password;
        this.Online=Online;
        this.chatId=chatId;
    }

    public String toString()
    {
        String s="";
        s+=UserName+"\n";
        s+="password: "+Password+"\n";
        return s;
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

    public void setUserName(String Nome){UserName=Nome;}

    public boolean getOnline() {
        return Online;
    }

    public void setOnline(boolean online) {
        Online = online;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }
}
