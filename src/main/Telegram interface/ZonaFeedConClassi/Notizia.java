package ZonaFeedConClassi;

import com.rometools.rome.feed.synd.SyndContent;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Notizia implements Comparable<Notizia>
{
    private String titolo; //ecc tutti i vari dati prendibili, per esempio con Notizia.titolo;
    private Date data;
    private String description; //SyndContent o String??
    private String author;
    private String link;

    //la notizia contiene una mappa che associa un utente ad un feedback.
    private Feedback feedbacks;
    //private Map<Utente, Feedback> ranking; //quando un utente inserisce un feedback controllare che non ne abbia già inserito uno. Se c'è già, modifica

    public Notizia(String titolo, Date data, SyndContent description, String author, String link) {
        this.titolo = titolo;
        this.data = data;
        this.description = description.getValue();
        this.author = author;
        this.link = link;
        this.feedbacks = new Feedback();
    }

    public String getTitolo() {
        return titolo;
    }

    public Date getData() {
        return data;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor()
    {
        return author;
    }

    public String getLink()
    {
        return link;
    }

    /*public String toString()
    {
        return this.titolo + "\n" + this.data.toString() + "\n" + this.description.getValue() + " (scritto da " + this.author + ")\n source: " + this.link;
    }*/
    public Feedback getFeedback()
    {
        return this.feedbacks;
    }

    public void setFeedback(Feedback f)
    {
        this.feedbacks = f;
    }

    public int compareTo(Notizia altraNotizia) //rendiamo Notizia comparable ?????????????
    {
        return this.link.compareTo(altraNotizia.getLink());
    }
    public boolean equals(Object o)
    {
        if (this == o) {return true;}

        if (o == null || getClass() != o.getClass()) return false;

        Notizia n=(Notizia) o;
        return (this.link==n.getLink());

    }
}