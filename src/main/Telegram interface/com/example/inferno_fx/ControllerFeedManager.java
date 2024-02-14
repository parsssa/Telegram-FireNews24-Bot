package com.example.inferno_fx;

import com.example.inferno_fx.OperazioniJSON.Categoria;
import com.example.inferno_fx.OperazioniJSON.MappaCategorie;
import com.example.inferno_fx.OperazioniJSON.Utente;
import com.example.inferno_fx.OperazioniJSON.gestoreGsonCategorie;
import javafx.application.Platform;
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
import javafx.stage.WindowEvent;
import javafx.util.Callback;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerFeedManager implements Initializable{
    private Stage stage;
    private Scene scene;
    private Parent root;
    private gestoreGsonCategorie ggc = new gestoreGsonCategorie();

    @FXML
    private TreeView treeView;

    @FXML
    private ImageView floppyDisk;

    private boolean salvato = true;
    @FXML
    private Pane feedPane;
    private ContextMenu myContext;


    Image categorieBox = new Image(getClass().getResourceAsStream("categorie.png"), 20, 20, false, true);
    Image folderImage = new Image(getClass().getResourceAsStream("Folder.png"), 15, 15, false, true);
    Image urlImage = new Image(getClass().getResourceAsStream("url.png"), 14, 14, false, true);
    private TreeItem<String> rootItem;

    public void switchToAutenticazioneAdmin(ActionEvent event) throws IOException {
        if(!salvato) {
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
        }
        else{
            Parent root = FXMLLoader.load(getClass().getResource("AutenticazioneAdmin.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            String css = this.getClass().getResource("application.css").toExternalForm();
            scene.getStylesheets().add(css);        }
    }

    public void switchToUserManager(ActionEvent event) throws IOException{
        if(!salvato) {
            //pop-up di allarme, chiede se sei sicuro di voler fare il logout
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Salvataggio");
            alert.setHeaderText("Stai per passare alla Gestione degli Utenti");
            alert.setContentText("Procedere senza effettuare il salvataggio?");
            if (alert.showAndWait().get() == ButtonType.OK) {

                Parent root = FXMLLoader.load(getClass().getResource("UserManager.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                String css = this.getClass().getResource("application.css").toExternalForm();
                scene.getStylesheets().add(css);
            }
        }
        else{
            Parent root = FXMLLoader.load(getClass().getResource("UserManager.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            String css = this.getClass().getResource("application.css").toExternalForm();
            scene.getStylesheets().add(css);        }
    }

    //TODO mettere initialize decente a "TreeCell implementation" per FeedManager
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //all'inizio metto il tasto salvataggio trasparente perche' non c'e' nulla da salvare
        floppyDisk.setOpacity(0.5);
        //

        this.rootItem = new TreeItem<>("Categorie",new ImageView(categorieBox));
        this.treeView = new TreeView<>(rootItem);
        rootItem.setExpanded(true);
        /*MappaCategorie mappaCategorie = new MappaCategorie("Categorie.json");
        for(String nomeCategoria: mappaCategorie.getMappa().keySet()){

            TreeItem thisCategoria = new TreeItem(nomeCategoria, new ImageView(folderImage));
            rootItem.getChildren().add(thisCategoria);
            for(String link:mappaCategorie.getMappa().get(nomeCategoria)){
                TreeItem thisLink = new TreeItem(link, new ImageView(urlImage));

                //cosa significa questa riga di codice?
                //thisLink.getGraphic().setOnMouseClicked(event -> System.out.println("OnAction {}"));

                thisCategoria.getChildren().add(thisLink);
            }
        }*/

        for(Categoria oggettoCategoria: gestoreGsonCategorie.convertReadJson("Categorie.json")){
            TreeItem thisCategoria = new TreeItem(oggettoCategoria.getTitolo(), new ImageView(folderImage));
            rootItem.getChildren().add(thisCategoria);
            for(String link: oggettoCategoria.getFeed()){
                TreeItem thisLink = new TreeItem(link, new ImageView(urlImage));
                thisCategoria.getChildren().add(thisLink);
            }
        }
        treeView.setEditable(true);
        treeView.setCellFactory(new Callback<TreeView<String>,TreeCell<String>>(){
            @Override
            public TreeCell<String> call(TreeView<String> p){
                return new ControllerFeedManager.TextFieldTreeCellImpl();
            }
        });

        feedPane.getChildren().add(treeView);


        this.treeView.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

    }



    public void switchToCommentManager(ActionEvent event) throws IOException {
        if(!salvato) {
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
        }
        else{
            Parent root = FXMLLoader.load(getClass().getResource("CommentManager.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            String css = this.getClass().getResource("application.css").toExternalForm();
            scene.getStylesheets().add(css);        }
    }


    public void switchToNotiziaManager(ActionEvent event) throws IOException {
        if(!salvato) {
            //pop-up di allarme, chiede se sei sicuro di voler fare il logout
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Salvataggio");
            alert.setHeaderText("Stai per passare alla Gestione delle Notizie");
            alert.setContentText("Procedere senza effettuare il salvataggio?");
            if (alert.showAndWait().get() == ButtonType.OK) {

                Parent root = FXMLLoader.load(getClass().getResource("NotiziaManager.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                String css = this.getClass().getResource("application.css").toExternalForm();
                scene.getStylesheets().add(css);
            }
        }
        else{
            Parent root = FXMLLoader.load(getClass().getResource("NotiziaManager.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            String css = this.getClass().getResource("application.css").toExternalForm();
            scene.getStylesheets().add(css);        }
    }


    public void nonSalvataggio(){
        this.salvato = false;
        this.floppyDisk.setOpacity(1);
    }
    public void salvataggio(MouseEvent event){
        salvato = true;
        this.floppyDisk.setOpacity(0.5);
        //l'idea e' che -premuto il tasto salvataggio- scorro gli elementi della TreeView e li paragono a quelli della mappa generata dal file, se c'e'
        //qualcosa di nuovo lo aggiungo al file

        ArrayList<Categoria> nuovaCategoriaList = new ArrayList<>();

        for (TreeItem categoria:rootItem.getChildren()){
            ArrayList<String>feedDellaCategoria = new ArrayList<>();
            for (Object feed:categoria.getChildren()){
                feedDellaCategoria.add(((TreeItem)feed).getValue().toString());
            }
            Categoria categoriaTemp = new Categoria(categoria.getValue().toString(),feedDellaCategoria);
            nuovaCategoriaList.add(categoriaTemp);
        }


        ggc.writeJson(nuovaCategoriaList, "Categorie.json");
    }
    public void salvataggio(){
        salvato = true;
        this.floppyDisk.setOpacity(0.5);
        //l'idea e' che -premuto il tasto salvataggio- scorro gli elementi della TreeView e li paragono a quelli della mappa generata dal file, se c'e'
        //qualcosa di nuovo lo aggiungo al file

        ArrayList<Categoria> nuovaCategoriaList = new ArrayList<>();

        for (TreeItem categoria:rootItem.getChildren()){
            ArrayList<String>feedDellaCategoria = new ArrayList<>();
            for (Object feed:categoria.getChildren()){
                feedDellaCategoria.add(((TreeItem)feed).getValue().toString());
            }
            Categoria categoriaTemp = new Categoria(categoria.getValue().toString(),feedDellaCategoria);
            nuovaCategoriaList.add(categoriaTemp);
        }


        gestoreGsonCategorie.convertWriteJson(nuovaCategoriaList, "Categorie.json");
    }

    public void eliminaFeed(ActionEvent event)throws IOException{
        salvataggio();
        Parent root = null;
        try {
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            root = FXMLLoader.load(getClass().getResource("EliminaFeed.fxml"));
            stage = new Stage();//(Stage) ((Node) t.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            String css = this.getClass().getResource("application.css").toExternalForm();
            scene.getStylesheets().add(css);
            stage.getIcons().add(new Image(this.getClass().getResource("variLogo/powder-blue-designify.png").toString()));
            stage.setResizable(false);
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent e) {
                    Platform.exit();
                    System.exit(0);
                }
            });
            stage.show();

        } catch (IOException e) {
            System.out.println("Errore nel caricare ModificaUtente.fxml");
            throw new RuntimeException(e);
        }
    }


    private final class TextFieldTreeCellImpl extends TreeCell<String> {

        private TextField textField;
        private ContextMenu addMenu = new ContextMenu();


        public TextFieldTreeCellImpl() {



            MenuItem aggiungiFeed = new MenuItem("Aggiungi Feed");
            addMenu.getItems().add(aggiungiFeed);

            aggiungiFeed.setOnAction(new EventHandler() {
                public void handle(Event t) {
                    TreeItem newFeed = new TreeItem<String>("nuovo feed", new ImageView(urlImage));
                    getTreeItem().getParent().getChildren().add(newFeed);
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
            setText((String)getItem());
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
                            getTreeItem().isLeaf()&&getTreeItem().getParent()!= null
                    ){
                        setContextMenu(addMenu);
                    }
                }
            }

        }

        private void createTextField() {
            textField = new TextField(getString());
            textField.setOnKeyReleased(t -> {
                if (t.getCode() == KeyCode.ENTER) {
                    commitEdit(textField.getText());

                    nonSalvataggio();
                } else if (t.getCode() == KeyCode.ESCAPE) {
                    cancelEdit();
                }
            });
        }

        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }




    }
}




