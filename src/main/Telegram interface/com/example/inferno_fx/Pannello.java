package com.example.inferno_fx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class Pannello extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //Parent root = FXMLLoader.load(getClass().getResource("AutenticazioneAdmin.fxml"));
        //per ora carico direttamente FeedManager.fxml
        Parent root = FXMLLoader.load(getClass().getResource("AutenticazioneAdmin.fxml"));

        Scene scene = new Scene(root);

        //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        String css = this.getClass().getResource("application.css").toExternalForm();
        scene.getStylesheets().add(css);


        stage.setTitle("FireNews24");
        stage.getIcons().add(new Image(this.getClass().getResource("variLogo/powder-blue-designify.png").toString()));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}