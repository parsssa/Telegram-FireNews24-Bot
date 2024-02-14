package com.example.inferno_fx;

import com.example.inferno_fx.OperazioniJSON.Categoria;
import com.example.inferno_fx.OperazioniJSON.gestoreGsonCategorie;
import javafx.application.Platform;
import javafx.event.Event;
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

public class ControllerEliminaFeed implements Initializable {
    private Stage stage;


    @FXML
    private TextField UsernameBox;
    @FXML
    private Label logMessage;
    @FXML
    private Label titoloUtente;
    @FXML
    private ImageView floppyDisk;
    

    private ArrayList<Categoria> categorieList = new ArrayList<Categoria>();
    private String feedInQuestione;
    private gestoreGsonCategorie ggc = new gestoreGsonCategorie();
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        this.categorieList = gestoreGsonCategorie.convertReadJson("Categorie.json");


    }

    public void salvataggio(MouseEvent event){
        int posizioneFeedInLista = 0;
        if(UsernameBox.getText()!=""){
            System.out.println("UsernameBox non vuoto");
            boolean feedEsistente = false;



            for (int i=0; i<categorieList.size(); i++) {
                if ((categorieList.get(i).getFeed()).contains(UsernameBox.getText())) {
                    feedEsistente = true;
                    System.out.println("l'ho trovato, esiste");
                    posizioneFeedInLista = i;
                }
                else{
                    System.out.println("non l'ho visto");
                }
            }
            if(feedEsistente) {
                categorieList.get(posizioneFeedInLista).getFeed().remove(UsernameBox.getText());
                logMessage.setText("Feed Rimosso 😅");
            }

        }


        gestoreGsonCategorie.convertWriteJson(categorieList,"Categorie.json");

    }


    public void switchToFeedManager(MouseEvent event) {

        try {
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            root = FXMLLoader.load(getClass().getResource("FeedManager.fxml"));
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
            System.out.println("Errore nel caricare FeedManager.fxml");
            throw new RuntimeException(e);
        }
        //commento per commit


    }



}
