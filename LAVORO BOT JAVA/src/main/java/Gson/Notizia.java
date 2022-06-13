package Gson;

import com.rometools.rome.feed.synd.SyndContent;

import java.util.Date;

public class Notizia {

    private String titolo; //ecc tutti i vari dati prendibili, per esempio con Notizia.titolo;
    private Date data;
    private SyndContent description; //SyndContent o String??
    private String author;
    private String link;

    public Notizia(String titolo, Date data, SyndContent description, String author, String link)
    {
        this.titolo=titolo;
        this.data=data;
        this.description=description;
        this.author=author;
        this.link=link;

        //output di debug sta roba
        System.out.println(titolo); //titolo
        System.out.println("[" + data.toString() + "] "); //timestamp
        System.out.println(description.getValue()); //per scrivere la descrizione come stringa si fa getValue cuz è un SyndContent
        System.out.println(author); //autore
        System.out.println("source: " + link+"\n"); //link/fonte

    }

}
