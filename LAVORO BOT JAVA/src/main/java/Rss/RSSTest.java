package Rss;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.xml.sax.InputSource;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;

public class RSSTest {

    public static void main(String[] args) {

        System.out.println("Esempio libreria Rome RSS: dati ANSA");

        String sourceURL = "https://www.ansa.it/sito/ansait_rss.xml";

        ArrayList<String> ilNostroFeed = new ArrayList<>();

        try {
            URL feedUrl = new URL(sourceURL);

            SyndFeedInput input = new SyndFeedInput();



            try {
                SyndFeed feed = input.build(new InputSource(feedUrl.openStream()));

                List<SyndEntry> entries = feed.getEntries();

                Iterator<SyndEntry> itEntries = entries.iterator();

                PrintWriter banana = new PrintWriter(new File("feedRicevuto.txt"));

                while (itEntries.hasNext()) {

                    SyndEntry entry = itEntries.next();
                    //banana.println("\n[" + entry.getPublishedDate() + "] ");
                   // banana.println(entry.getTitle());
                    //banana.println(entry.getLink());

                }

            } catch (IllegalArgumentException | FeedException | IOException e) {
                // Errore lettura feed
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            // Errore indirizzo e accesso ai feed
            e.printStackTrace();
        }

    }

}