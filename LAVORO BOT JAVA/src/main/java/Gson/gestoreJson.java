package Gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;

public class gestoreJson {

     public void writeJSON(ArrayList<Notizia> tipo) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        FileWriter writer = new FileWriter("Provazza.json");

        writer.write("[");

        for(Notizia N: tipo){
            if (!(tipo.indexOf(N)== tipo.size()-1)){
            writer.write(gson.toJson(N)+ ",");
            }
            else {
                writer.write(gson.toJson(N));

            }
        }
         writer.write("]");

         writer.close();
     }



    public Studente readJSON() throws FileNotFoundException {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader("Provazza.json"));

        Studente student = gson.fromJson(bufferedReader, Studente.class);
        return student;
    }


}








