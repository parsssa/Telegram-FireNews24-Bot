package Gson;

import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndImage;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import org.xml.sax.InputSource;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FeedObj {

    public ArrayList<Notizia> newsList;
    public FeedObj(String sourceURL) throws IOException, FeedException
    {
        newsList = new ArrayList<Notizia>();
        sourceURL = "https://www.ansa.it/sito/ansait_rss.xml"; //qui bisogna fare che l'url cambia sempre
        URL FeedURL = new URL(sourceURL); //qui crea un oggetto di classe URL, forse vorremmo un file xml scaricato sul server
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new InputSource(FeedURL.openStream())); //e se non fosse XML però?? da cambiare eventualmente
        //da capire che fa la roba sopra ciao
        List<SyndEntry> entries = feed.getEntries(); //getEntries deriva dalle librerie di rome. che fa???
        Iterator<SyndEntry> itEntries = entries.iterator(); //?????????????''
        this.GenerateNewsList(itEntries);
    }

    public void GenerateNewsList(Iterator<SyndEntry> itEntries)
    {
        while (itEntries.hasNext()) {
            SyndEntry entry = itEntries.next(); //secondo me mettendolo qua non prende la prima notizia?? idk
            newsList.add(new Notizia(entry.getTitle(), entry.getPublishedDate(), entry.getDescription(), entry.getAuthor(), entry.getLink()));
        }

    }

}
