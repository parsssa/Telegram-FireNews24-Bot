package com.example.inferno_fx;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ControllerAdmin{

    @FXML
    private PasswordField passwordBox;
    @FXML
    private TextField usernameBox;
    @FXML
    private Text inputSbagliato;
    @FXML
    private Text asteriscoUsername;
    @FXML
    private Text asteriscoPassword;



    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToUserManager(ActionEvent event) throws IOException {
        DatiAdmin datiAdmin = new DatiAdmin();
        datiAdmin.aggiungiAdmin("@parsssa13");
        datiAdmin.aggiungiAdmin("@Massimomanonpericolo");
        datiAdmin.aggiungiAdmin("@Francibo");
        datiAdmin.setPassword("123ADMIN");
        String inputPassword = passwordBox.getText();
        String inputUsername = usernameBox.getText();

        if(datiAdmin.getListaAdmin().contains(inputUsername) && datiAdmin.getPassword().equals(inputPassword)) {
            Parent root = FXMLLoader.load(getClass().getResource("UserManager.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            String css = this.getClass().getResource("application.css").toExternalForm();
            scene.getStylesheets().add(css);

        } else if (inputUsername.equals("")||inputPassword.equals("")) {
            inputSbagliato.setText("Completare tutti i campi");
            asteriscoPassword.setText("*");
            asteriscoUsername.setText("*");
        } else{
            if(!(datiAdmin.getListaAdmin()).contains(usernameBox.getText())) {
                inputSbagliato.setText("Utente non riconosciuto");
                asteriscoPassword.setText("");
                asteriscoUsername.setText("*");
            }else{
                inputSbagliato.setText("password errato");
                asteriscoUsername.setText("");
                asteriscoPassword.setText("*");
            }
        }
    }

}