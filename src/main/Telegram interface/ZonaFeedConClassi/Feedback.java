package ZonaFeedConClassi;


import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;

public class Feedback implements Comparable//la classe feedback contiene tutte le informazioni necessarie da associare ad un link della notizia
{
    //potrei mettere un private String linkAssociatoAlFeedback oppure mettere il link come key nella mappa del gestoreFeedback, ho fatto la seconda per ora
    private ArrayList<String> commenti;
    //nome utente + ": " + commento
    //associa una stringa utente a diversi commenti. al posto di arraylist potremmo mettere un oggetto così salviamo anche la data del commento
    private HashMap<String,Integer> voti; //qui ho fatto che i voti sono anonimi idk

    public Feedback(String username) {}
    public Feedback()
    {
        this.commenti=new ArrayList<>();
        this.voti=new HashMap<>();
    }

    public ArrayList<String> getCommenti() {return this.commenti;}
    public HashMap<String,Integer> getVoti() {return this.voti;}

    @Override
    public int compareTo(@NotNull Object o) {
        if(o.getClass().equals(Feedback.class)) {
            if (((Feedback) o).getCommenti().equals(this.getCommenti()) && ((Feedback) o).getVoti().equals(this.getVoti())) {
                return 0;
            }
            else {
                return 1;
            }
        }
        else{
            return 1;
        }
    }
}