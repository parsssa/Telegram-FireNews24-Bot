package ZonaFeedConClassi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.io.*;
import java.util.ArrayList;
import java.lang.reflect.Type;
import java.util.Scanner;
import java.util.TreeMap;

public class GestoreFeedback //situazione tipo notizia-feedobj: feedback è un elemento e gestorefeedback contiene la mappa di tutti gli elementi
{
    //contiene SOLO i metodi per scrivere e leggere da file, non per commentare e votare
    private TreeMap<String, Feedback> listaNotizieCommentate;
    //string=il link del commento, l'arraylist contiene tutti i commenti associati al link ArrayList<String>

    public GestoreFeedback()
    {
        try
        {
            this.leggiDaFile();
        } catch (FileNotFoundException e)
        {
            this.listaNotizieCommentate=new TreeMap<>();
        }
    }
    public void salvaSuFile()
    {
        try
        {
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();

            Gson gson = builder.create();
            FileWriter writer = new FileWriter("notizieCommentate.json");
            String ilToJson = gson.toJson(listaNotizieCommentate);
            writer.write(ilToJson);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void salvaSuFile(TreeMap<String, Feedback> mappa) throws IOException
    {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        FileWriter writer = new FileWriter("notizieCommentate.json");
        String ilToJson = gson.toJson(mappa);
        writer.write(ilToJson);
        writer.close();
    }
    public void leggiDaFile() throws FileNotFoundException {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        FileReader fileread=new FileReader("notizieCommentate.json");
        BufferedReader bufferedReader = new BufferedReader(fileread);
        System.out.println("GestoreFeedback: a quanto pare il file non è vuoto");
        Type mapType = new TypeToken<TreeMap<String, Feedback>>(){}.getType();
        listaNotizieCommentate = gson.fromJson(bufferedReader, mapType);
        System.out.println("ecco, per esempio, il feedback del valore 1 è: "+listaNotizieCommentate.firstKey());

    }

    public TreeMap<String, Feedback> getListaNotizieCommentate() {return this.listaNotizieCommentate;}

    //i metodi aggiungicommento e voto vanno qui perchè così gestorefeedback li mette sia nel file di tutte le notizie commentate sia nell'oggetto feedback
    public Notizia aggiungiCommento(Notizia n, String user, String commento) throws IOException {
        n.getFeedback().getCommenti().add(user+": "+commento);
        this.listaNotizieCommentate.put(n.getLink(), n.getFeedback());

        salvaSuFile();
        return n;
    }
    public Notizia aggiungiVoto(Notizia n, String user, int voto) throws IOException {
        n.getFeedback().getVoti().put(user, voto); //modifica il voto se metto un voto in una key che ha già un value??
        listaNotizieCommentate.put(n.getLink(), n.getFeedback());

        salvaSuFile();
        return n;
    }

    public String outputFeedbackCommenti(Notizia N)
    {
        String s = "";
        for (String C : N.getFeedback().getCommenti()) {
            s += C + "\n"; //al posto di system out comando magico usabile dal bot
        }
        return s;
    }

    public double outputFeedbackVotiMedia(Notizia N)
    {
        int somma=0;
        int numeri=0;
        for(int v: N.getFeedback().getVoti().values())
        {
            somma+=v;
            numeri++;
        }
        if (numeri==0)
        {return 0;}
        double media=somma/numeri;
        return media;
    }

}