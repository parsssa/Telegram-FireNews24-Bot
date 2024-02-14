package com.example.inferno_fx;

import ZonaFeedConClassi.FeedObj;
import ZonaFeedConClassi.Notizia;
import com.example.inferno_fx.OperazioniJSON.Utente;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.*;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;


public class ControllerNotizieManager implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Pane userPane;
    @FXML
    private ImageView floppyDisk;

    private boolean salvato = true;
    private ArrayList<Notizia> arraySport;
    private ArrayList<Notizia> arrayPolitica = new ArrayList<>();
    private ArrayList<Notizia> arraySpettacolo = new ArrayList<>();
    private ArrayList<Notizia> arrayTech = new ArrayList<>();
    private ArrayList<Notizia> arrayEconomia = new ArrayList<>();

    private ArrayList<Notizia> notizieRimosse = new ArrayList<>();


    private final Node rootIcon = new ImageView(new Image(getClass().getResourceAsStream("categorie.png")));
    private final Image depIcon = new Image(getClass().getResourceAsStream("Folder.png"));

    TreeItem<String> rootNode = new TreeItem<String>("Notizie per categoria");
    TreeItem<String> viewSport = new TreeItem<String>("Sport", new ImageView(new Image(getClass().getResourceAsStream("VariePNG/sportIcon.png"),18, 18, false, true)));
    TreeItem<String> viewPolitica = new TreeItem<String>("Politica", new ImageView(new Image(getClass().getResourceAsStream("VariePNG/politicsIcon.png"),18, 18, false, true)));
    TreeItem<String> viewSpettacolo = new TreeItem<String>("Spettacolo", new ImageView(new Image(getClass().getResourceAsStream("VariePNG/cinemaIcon.png"),18, 18, false, true)));
    TreeItem<String> viewTech = new TreeItem<String>("Tech", new ImageView(new Image(getClass().getResourceAsStream("VariePNG/laptopIcon.png"),17, 18, false, true)));
    TreeItem<String> viewEconomia = new TreeItem<String>("Economia", new ImageView(new Image(getClass().getResourceAsStream("VariePNG/economyIcon.png"),18, 18, false, true)));




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        rootNode.getChildren().addAll(viewSport, viewPolitica, viewSpettacolo, viewTech, viewEconomia);
        rootNode.setExpanded(true);

        //TODO mettere tutto il codice per far vedere le notizie dai vari file, che sarebbero:
        //NotizieSport.json
        //NotiziePolitica.json
        //NotizieSpettacolo.json
        //NotizieTech.json
        //NotizieEconomia.json

        //questo blocco try riempie la TreeItem itemSport
        try {
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            FileReader fileread = new FileReader("NotizieSport.json");
            BufferedReader bufferedReader = new BufferedReader(fileread);
            Type mapType = new TypeToken<ArrayList<Notizia>>() {}.getType();
            arraySport = gson.fromJson(bufferedReader, mapType);
            for (Notizia nsport : arraySport) {
                TreeItem<String> itemSport = new TreeItem<String>(nsport.getTitolo());
                viewSport.getChildren().add(itemSport);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File NotiziaSport.json non trovato");
            arraySport=new ArrayList<>();
            //throw new RuntimeException(e);
        }
        //questo blocco try riempie la TreeItem itemPolitica
        try {
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            FileReader fileread = new FileReader("NotiziePolitica.json");
            BufferedReader bufferedReader = new BufferedReader(fileread);
            Type mapType = new TypeToken<ArrayList<Notizia>>() {
            }.getType();
            this.arraySport = gson.fromJson(bufferedReader, mapType);
            for (Notizia nsport : arraySport) {
                TreeItem<String> itemPolitica = new TreeItem<String>(nsport.getTitolo());
                viewPolitica.getChildren().add(itemPolitica);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File NotiziePolitica.json non trovato");
            arraySport=new ArrayList<>();
        }
        //questo blocco try riempie la TreeItem itemSpettacolo
        try {
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            FileReader fileread = new FileReader("NotizieSpettacolo.json");
            BufferedReader bufferedReader = new BufferedReader(fileread);
            Type mapType = new TypeToken<ArrayList<Notizia>>() {
            }.getType();
            this.arraySport = gson.fromJson(bufferedReader, mapType);
            for (Notizia nsport : arraySport) {
                TreeItem<String> itemSpettacolo = new TreeItem<String>(nsport.getTitolo());
                viewSpettacolo.getChildren().add(itemSpettacolo);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File NotizieSpettacolo.json non trovato");
            arraySport=new ArrayList<>();
        }
        //questo blocco try riempie la TreeItem itemTech
        try {
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            FileReader fileread = new FileReader("NotizieTech.json");
            BufferedReader bufferedReader = new BufferedReader(fileread);
            Type mapType = new TypeToken<ArrayList<Notizia>>() {
            }.getType();
            this.arraySport = gson.fromJson(bufferedReader, mapType);
            for (Notizia nsport : arraySport) {
                TreeItem<String> itemTech = new TreeItem<String>(nsport.getTitolo());
                viewTech.getChildren().add(itemTech);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File NotizieTech.json non trovato");
            arraySport=new ArrayList<>();
        }
        //questo blocco try riempie la TreeItem itemEconomia
        try {
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            FileReader fileread = new FileReader("NotizieEconomia.json");
            BufferedReader bufferedReader = new BufferedReader(fileread);
            Type mapType = new TypeToken<ArrayList<Notizia>>() {
            }.getType();
            this.arraySport = gson.fromJson(bufferedReader, mapType);
            for (Notizia nsport : arraySport) {
                TreeItem<String> itemEconomia = new TreeItem<String>(nsport.getTitolo());
                viewEconomia.getChildren().add(itemEconomia);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File NotizieEconomia.json non trovato");
            arraySport=new ArrayList<>();
        }


        TreeView<String> treeView = new TreeView<String>(rootNode);
        treeView.setPrefWidth(400);
        treeView.setPrefHeight(300);
        //treeView.setEditable(true);

        treeView.setCellFactory(new Callback<TreeView<String>, TreeCell<String>>() {
            @Override
            public TreeCell<String> call(TreeView<String> p) {
                return new ControllerNotizieManager.TextFieldTreeCellImpl();
            }
        });

        userPane.getChildren().add(treeView);
    }

    private final class TextFieldTreeCellImpl extends TreeCell<String> {

        private TextField textField;
        private ContextMenu addMenu = new ContextMenu();


        public TextFieldTreeCellImpl() {


            MenuItem addMenuItem = new MenuItem("Aggiungi Notizia");
            MenuItem removeMenuItem = new MenuItem("Elimina Notizia");
            //addMenu.getItems().add(addMenuItem);
            addMenu.getItems().add(removeMenuItem);

            /*addMenuItem.setOnAction(new EventHandler() {
                public void handle(Event t) {
                    TreeItem newEmployee =
                            new TreeItem<String>("Nuova Notizia");
                    getTreeItem().getParent().getChildren().add(newEmployee);
                    nonSalvataggio();
                }
            });
*/
            removeMenuItem.setOnAction(new EventHandler() {
                public void handle(Event t) {
                    getTreeItem().getParent().getChildren().remove(getTreeItem());
                    nonSalvataggio();
                }
            });
        }


        @Override
        public void startEdit() {
            super.startEdit();

            if (textField == null) {
                createTextField();
            }
            setText(null);
            setGraphic(textField);
            textField.selectAll();
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();
            setText((String) getItem());
            setGraphic(getTreeItem().getGraphic());
        }

        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
                    }
                    setText(null);
                    setGraphic(textField);
                } else {
                    setText(getString());
                    setGraphic(getTreeItem().getGraphic());
                    if (
                            getTreeItem().isLeaf() && getTreeItem().getParent() != null
                    ) {
                        setContextMenu(addMenu);
                    }
                }
            }

        }

        private void createTextField() {
            textField = new TextField(getString());
            textField.setOnKeyReleased(new EventHandler<KeyEvent>() {

                @Override
                public void handle(KeyEvent t) {
                    if (t.getCode() == KeyCode.ENTER) {
                        commitEdit(textField.getText());

                        nonSalvataggio();
                    } else if (t.getCode() == KeyCode.ESCAPE) {
                        cancelEdit();
                    }
                }
            });
        }

        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }


    }


    //roba per navigare le pagine sul pannello
    public void switchToAutenticazioneAdmin(ActionEvent event) throws IOException {
        if (!salvato) {
            //pop-up di allarme, chiede se sei sicuro di voler fare il logout
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Salvataggio");
            alert.setHeaderText("Stai per effettuare il logout");
            alert.setContentText("Procedere senza effettuare il salvataggio?");
            if (alert.showAndWait().get() == ButtonType.OK) {

                Parent root = FXMLLoader.load(getClass().getResource("AutenticazioneAdmin.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                String css = this.getClass().getResource("application.css").toExternalForm();
                scene.getStylesheets().add(css);
            }
        } else {
            Parent root = FXMLLoader.load(getClass().getResource("AutenticazioneAdmin.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            String css = this.getClass().getResource("application.css").toExternalForm();
            scene.getStylesheets().add(css);
        }
    }

    public void switchToFeedManager(ActionEvent event) throws IOException {
        //pop-up di allarme, ti ricorda di salvare prima di cambiare pagina
        if (!salvato) {
            //pop-up di allarme, chiede se sei sicuro di voler fare il logout
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initStyle(StageStyle.UNDECORATED);
            alert.setTitle("Salvataggio");
            alert.setHeaderText("Stai per passare alla Gestione dei Feed");
            alert.setContentText("Procedere senza effettuare il salvataggio?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                alert.close();
                Parent root = FXMLLoader.load(getClass().getResource("FeedManager.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                String css = this.getClass().getResource("application.css").toExternalForm();
                scene.getStylesheets().add(css);
            }
        } else {
            Parent root = FXMLLoader.load(getClass().getResource("FeedManager.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            String css = this.getClass().getResource("application.css").toExternalForm();
            scene.getStylesheets().add(css);
        }
    }


    public void switchToUserManager(ActionEvent event) throws IOException {
        //pop-up di allarme, ti ricorda di salvare prima di cambiare pagina
        if (!salvato) {
            //pop-up di allarme, chiede se sei sicuro di voler fare il logout
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initStyle(StageStyle.UNDECORATED);
            alert.setTitle("Salvataggio");
            alert.setHeaderText("Stai per passare alla Gestione degli Utenti");
            alert.setContentText("Procedere senza effettuare il salvataggio?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                alert.close();
                Parent root = FXMLLoader.load(getClass().getResource("UserManager.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                String css = this.getClass().getResource("application.css").toExternalForm();
                scene.getStylesheets().add(css);
            }
        } else {
            Parent root = FXMLLoader.load(getClass().getResource("UserManager.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            String css = this.getClass().getResource("application.css").toExternalForm();
            scene.getStylesheets().add(css);
        }
    }

    public void switchToCommentManager(ActionEvent event) throws IOException {
        if (!salvato) {
            //pop-up di allarme, chiede se sei sicuro di voler fare il logout
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Salvataggio");
            alert.setHeaderText("Stai per passare alla Gestione dei commenti");
            alert.setContentText("Procedere senza effettuare il salvataggio?");
            if (alert.showAndWait().get() == ButtonType.OK) {

                Parent root = FXMLLoader.load(getClass().getResource("CommentManager.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                String css = this.getClass().getResource("application.css").toExternalForm();
                scene.getStylesheets().add(css);
            }
        } else {
            Parent root = FXMLLoader.load(getClass().getResource("CommentManager.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            String css = this.getClass().getResource("application.css").toExternalForm();
            scene.getStylesheets().add(css);
        }
    }


    public void nonSalvataggio() {
        this.salvato = false;
        this.floppyDisk.setOpacity(1);
    }

    public void salvataggio(MouseEvent event) throws FileNotFoundException {
        salvato = true;
        this.floppyDisk.setOpacity(0.5);
        ArrayList<String> nuovaArraySport = new ArrayList<>();
        ArrayList<String> nuovaArrayPolitica = new ArrayList<>();
        ArrayList<String> nuovaArraySpettacolo = new ArrayList<>();
        ArrayList<String> nuovaArrayTech = new ArrayList<>();
        ArrayList<String> nuovaArrayEconomia = new ArrayList<>();


        //per qualche motivo devo riempire tutte le vecchie array di nuovo perche' si svuotano
        try {
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            FileReader fileread = new FileReader("NotizieSport.json");
            BufferedReader bufferedReader = new BufferedReader(fileread);
            Type mapType = new TypeToken<ArrayList<Notizia>>() {}.getType();
            arraySport = gson.fromJson(bufferedReader, mapType);

            builder = new GsonBuilder();
            builder.setPrettyPrinting();
            gson = builder.create();
            fileread = new FileReader("NotiziePolitica.json");
            bufferedReader = new BufferedReader(fileread);
            arrayPolitica = gson.fromJson(bufferedReader, mapType);

            builder = new GsonBuilder();
            builder.setPrettyPrinting();
            gson = builder.create();
            fileread = new FileReader("NotizieSpettacolo.json");
            bufferedReader = new BufferedReader(fileread);
            arraySpettacolo = gson.fromJson(bufferedReader, mapType);

            builder = new GsonBuilder();
            builder.setPrettyPrinting();
            gson = builder.create();
            fileread = new FileReader("NotizieTech.json");
            bufferedReader = new BufferedReader(fileread);
            arrayTech = gson.fromJson(bufferedReader, mapType);

            builder = new GsonBuilder();
            builder.setPrettyPrinting();
            gson = builder.create();
            fileread = new FileReader("NotizieEconomia.json");
            bufferedReader = new BufferedReader(fileread);
            arrayEconomia = gson.fromJson(bufferedReader, mapType);


            for (TreeItem tastoCategoria : rootNode.getChildren()) {
                String testoTastoCategoria = tastoCategoria.getValue().toString();
                if (testoTastoCategoria.equalsIgnoreCase("Sport")) {
                    for (int i = 0; i < tastoCategoria.getChildren().size(); i++) {
                        TreeItem itemSport = (TreeItem) tastoCategoria.getChildren().get(i);
                        nuovaArraySport.add(itemSport.getValue().toString());
                    }

                    for (Notizia n : arraySport) {
                        if (!nuovaArraySport.contains(n.getTitolo())) {
                            notizieRimosse.add(n);
                        }
                    }
                    arraySport.removeAll(notizieRimosse);
                    try {
                        builder = new GsonBuilder();
                        builder.setPrettyPrinting();
                        System.out.println("\n\n\n INTANTO TI STAMPO L'ARRAYSPORT");
                        System.out.println(arraySport);
                        gson = builder.create();
                        FileWriter writer = new FileWriter("NotizieSport.json");
                        String ilToJson = gson.toJson(arraySport);
                        writer.write(ilToJson);
                        writer.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (tastoCategoria.getValue().toString() == "Politica") {
                    for (int i = 0; i < tastoCategoria.getChildren().size(); i++) {
                        TreeItem itemPolitica = (TreeItem) tastoCategoria.getChildren().get(i);
                        nuovaArrayPolitica.add(itemPolitica.getValue().toString());
                    }

                    for (Notizia n : arrayPolitica) {
                        if (!nuovaArrayPolitica.contains(n.getTitolo())) {
                            notizieRimosse.add(n);
                        }
                    }
                    arrayPolitica.removeAll(notizieRimosse);
                    try {
                        builder = new GsonBuilder();
                        builder.setPrettyPrinting();

                        gson = builder.create();
                        FileWriter writer = new FileWriter("NotiziePolitica.json");
                        String ilToJson = gson.toJson(arrayPolitica);
                        writer.write(ilToJson);
                        writer.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                } else if (tastoCategoria.getValue().toString() == "Spettacolo") {
                    for (int i = 0; i < tastoCategoria.getChildren().size(); i++) {
                        TreeItem itemSpettacolo = (TreeItem) tastoCategoria.getChildren().get(i);
                        nuovaArraySpettacolo.add(itemSpettacolo.getValue().toString());
                    }

                    for (Notizia n : arraySpettacolo) {
                        if (!nuovaArraySpettacolo.contains(n.getTitolo())) {
                            notizieRimosse.add(n);
                        }
                    }
                    arraySpettacolo.removeAll(notizieRimosse);
                    try {
                        builder = new GsonBuilder();
                        builder.setPrettyPrinting();

                        gson = builder.create();
                        FileWriter writer = new FileWriter("NotizieSpettacolo.json");
                        String ilToJson = gson.toJson(arraySpettacolo);
                        writer.write(ilToJson);
                        writer.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                } else if (tastoCategoria.getValue().toString() == "Tech") {
                    for (int i = 0; i < tastoCategoria.getChildren().size(); i++) {
                        TreeItem itemTech = (TreeItem) tastoCategoria.getChildren().get(i);
                        nuovaArraySport.add(itemTech.getValue().toString());
                    }

                    for (Notizia n : arrayTech) {
                        if (!nuovaArrayTech.contains(n.getTitolo())) {
                            notizieRimosse.add(n);
                        }
                    }
                    arrayTech.removeAll(notizieRimosse);
                    try {
                        builder = new GsonBuilder();
                        builder.setPrettyPrinting();

                        gson = builder.create();
                        FileWriter writer = new FileWriter("NotizieTech.json");
                        String ilToJson = gson.toJson(arrayTech);
                        writer.write(ilToJson);
                        writer.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                } else if (tastoCategoria.getValue().toString() == "Economia") {
                    for (int i = 0; i < tastoCategoria.getChildren().size(); i++) {
                        TreeItem itemEconomia = (TreeItem) tastoCategoria.getChildren().get(i);
                        nuovaArraySport.add(itemEconomia.getValue().toString());
                    }

                    for (Notizia n : arrayEconomia) {
                        if (!nuovaArrayEconomia.contains(n.getTitolo())) {
                            notizieRimosse.add(n);
                        }
                    }
                    arrayEconomia.removeAll(notizieRimosse);
                    try {
                        builder = new GsonBuilder();
                        builder.setPrettyPrinting();

                        gson = builder.create();
                        FileWriter writer = new FileWriter("NotizieEconomia.json");
                        String ilToJson = gson.toJson(arrayEconomia);
                        writer.write(ilToJson);
                        writer.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            //scrittura finale per riempire NotizieRimosse.json
            try {
                builder = new GsonBuilder();
                builder.setPrettyPrinting();

                gson = builder.create();
                FileWriter writer = new FileWriter("NotizieRimosse.json");
                String ilToJson = gson.toJson(notizieRimosse);
                writer.write(ilToJson);
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } catch (FileNotFoundException e) {
            System.out.println("manca uno dei file Json di Notizie");
            throw new RuntimeException(e);
        }
    }

}




