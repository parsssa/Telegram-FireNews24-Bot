package ZonaFeedConClassi;

import java.io.IOException;

public class mainPerProvareLeClassi
{
    public static void main(String[] args) throws IOException {
        System.out.println("genero un feed, aggiungo un commento all'ultima notizia, rigenero il feed e il commento dovrebbe esserci.");

        System.out.println("genero un feed:");
        FeedObj ilfeed=new FeedObj("https://www.ansa.it/sito/ansait_rss.xml", "ziopupu.txt");
        System.out.println(ilfeed.getCurrentNotizia());

        System.out.println("aggiungo un commento alla prima notizia");
        ilfeed.getGestoreFeedBack().aggiungiCommento(ilfeed.getCurrentNotizia(), "@fra", "dai cazzo eh!");
        //da sistemare filewrite

        //che merda qua
        System.out.println("chiudo il feedback salvando le notizie commentate su file");
        ilfeed.getGestoreFeedBack().salvaSuFile();

        System.out.println("RIGENERO IL FEED");
        FeedObj ilfeedrigenerato=new FeedObj("https://www.ansa.it/sito/ansait_rss.xml", "ziopupu.txt");

        System.out.println("e il commento dovrebbe esserci.");
        System.out.println(ilfeedrigenerato.getCurrentNotizia());
        System.out.println(ilfeedrigenerato.getCurrentNotizia().getFeedback().getCommenti());
        //commento per committare
    }
}
//IL MAIN DI PROVA QUESTO