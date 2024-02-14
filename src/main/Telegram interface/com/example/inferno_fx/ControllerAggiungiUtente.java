package com.example.inferno_fx;

import com.example.inferno_fx.OperazioniJSON.Utente;
import com.example.inferno_fx.OperazioniJSON.gestoreGsonUtente;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ControllerAggiungiUtente implements Initializable {
    private Stage stage;


    @FXML
    private TextField UsernameBox;

    @FXML
    private TextField PasswordBox;

    @FXML
    private Label titoloUtente;
    @FXML
    private ImageView floppyDisk;
    @FXML
    private Label logMessage;
    

    private ArrayList<Utente> UserList = new ArrayList<Utente>();
    private Utente utenteInQuestione;
    private gestoreGsonUtente ggu = new gestoreGsonUtente();
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        try {
            this.UserList = ggu.readJsonLista("ListaUtentiVari.json");
        } catch (FileNotFoundException e) {
            System.out.println("JSON Utenti inesistente");
        }



        titoloUtente.setText("✅ Aggiungi Utente");



    }

    public void salvataggio(MouseEvent event){


        if(UsernameBox.getText()!=""&&PasswordBox.getText()!=""){
            boolean giaEsistente = false;

            Utente utenteInQuestione = new Utente(UsernameBox.getText(), PasswordBox.getText());
            for (Utente u : UserList) {
                if (u.getUserName().equalsIgnoreCase(utenteInQuestione.getUserName())) {
                    System.out.println("Utente gia' esistente");
                    giaEsistente = true;
                }
            }
            if(giaEsistente==false){
                UserList.add(utenteInQuestione);
                logMessage.setText("Utente Aggiunto👍");
            }

        }




        try {
            ggu.writeJson(UserList);
        } catch (IOException e) {
            System.out.println("Errore a trovare file json per aggiornare gli utenti");
            throw new RuntimeException(e);
        }

    }


    public void switchToUserManager(MouseEvent event) {

        try {
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            root = FXMLLoader.load(getClass().getResource("UserManager.fxml"));
            stage = new Stage();//(Stage) ((Node) t.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            String css = this.getClass().getResource("application.css").toExternalForm();
            scene.getStylesheets().add(css);
            stage.getIcons().add(new Image(this.getClass().getResource("variLogo/powder-blue-designify.png").toString()));
            stage.setResizable(false);
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent e) {
                    Platform.exit();
                    System.exit(0);
                }
            });
            stage.show();

        } catch (IOException e) {
            System.out.println("Errore nel caricare UserManager.fxml");
            throw new RuntimeException(e);
        }
    }



}
