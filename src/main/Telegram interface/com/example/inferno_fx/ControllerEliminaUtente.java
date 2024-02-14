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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerEliminaUtente implements Initializable {
    private Stage stage;


    @FXML
    private TextField UsernameBox;
    @FXML
    private Label logMessage;
    @FXML
    private Label titoloUtente;
    @FXML
    private ImageView floppyDisk;
    

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





    }

    public void salvataggio(MouseEvent event){


        if(UsernameBox.getText()!=""){
            boolean utenteEsistente = false;

            for (Utente u : UserList) {
                if (u.getUserName().equalsIgnoreCase(UsernameBox.getText())) {
                    utenteInQuestione = u;
                    utenteEsistente = true;
                }
            }
            if(utenteEsistente) {
                UserList.remove(utenteInQuestione);
                logMessage.setText("Utente Rimosso 😅");
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
        //commento per commit


    }



}
