package Gson;

public class Studente {

    String nome;
    int eta;
    boolean onesta;


    public Studente(String nome, int eta, boolean onesta){

        this.nome=nome;
        this.eta=eta;
        this.onesta=onesta;
    }

    public String getNome(){
        return nome;
    }
    public int getEta(){
        return eta;
    }
    public boolean getOnesta(){
        return onesta;
    }

}
