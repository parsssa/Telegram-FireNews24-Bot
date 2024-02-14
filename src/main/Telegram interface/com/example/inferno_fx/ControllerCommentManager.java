package com.example.inferno_fx;

import ZonaFeedConClassi.Feedback;
import ZonaFeedConClassi.GestoreFeedback;
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

import java.io.IOException;
import java.net.URL;
import java.util.*;


public class ControllerCommentManager implements Initializable{
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Pane userPane;
    @FXML
    private ImageView floppyDisk;

    private boolean salvato = true;




    //roba copiata da tutorial treeView
    private final Node rootIcon =
            new ImageView(new Image(getClass().getResourceAsStream("categorie.png")));
    private final Image depIcon =
            new Image(getClass().getResourceAsStream("Folder.png"));

    private TreeMap<String, Feedback> listaNotizieCommentate;
    private GestoreFeedback gf = new GestoreFeedback();
    TreeItem<String> rootNode = new TreeItem<String>("Notizie Commentate",new ImageView(new Image(getClass().getResourceAsStream("categorie.png"), 20, 20, false, true)));


     //fine roba copiata da tutorial treeView


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        this.listaNotizieCommentate = gf.getListaNotizieCommentate();
        //all'inizio metto il tasto salvataggio trasparente perche' non c'e' nulla da salvare
        floppyDisk.setOpacity(0.5);
        //

        rootNode.setExpanded(true);
        for (String linkNotizia : listaNotizieCommentate.keySet()) {
            TreeItem<String> itemNotiziaCommentata = new TreeItem<String>(linkNotizia, new ImageView(new Image(getClass().getResourceAsStream("VariePNG/news.png"),14, 14, false, true)));
            itemNotiziaCommentata.setExpanded(true);
            ArrayList<String> esempioArrayVuoto = new ArrayList<>();
            if(!listaNotizieCommentate.get(linkNotizia).getCommenti().equals(esempioArrayVuoto)) {
                rootNode.getChildren().add(itemNotiziaCommentata);
            }
            Feedback feedBackTemp = listaNotizieCommentate.get(linkNotizia);
            for(String parere: feedBackTemp.getCommenti()){
                TreeItem<String> itemCommento = new TreeItem<>(parere, new ImageView(new Image(getClass().getResourceAsStream("VariePNG/talk.png"),14, 14, false, true)));
                itemNotiziaCommentata.getChildren().add(itemCommento);
            }
        }





        TreeView<String> treeView = new TreeView<String>(rootNode);
        treeView.setPrefWidth(400);
        treeView.setPrefHeight(300);

        //questa TreeView qua non deve essere editabile
        //treeView.setEditable(true);
        treeView.setCellFactory(new Callback<TreeView<String>,TreeCell<String>>(){
            @Override
            public TreeCell<String> call(TreeView<String> p){
                return new TextFieldTreeCellImpl();
            }
        });

        userPane.getChildren().add(treeView);


    }

    private final class TextFieldTreeCellImpl extends TreeCell<String> {

        private TextField textField;
        private ContextMenu addMenu = new ContextMenu();


        public TextFieldTreeCellImpl() {

            MenuItem removeMenuItem = new MenuItem("Cancella commento");
            addMenu.getItems().add(removeMenuItem);


            removeMenuItem.setOnAction(new EventHandler() {
                public void handle(Event t) {
                    boolean commentoEsistente = false;
                    Feedback feedbackTempInQuestione = new Feedback();
                    String linkInQuestione = "";
                    String parereInQuestione = "";
                    int posizioneFeedBackInQuestione = 0;
                    for (String linkNotizia : listaNotizieCommentate.keySet()) {
                        Feedback feedBackTemp = listaNotizieCommentate.get(linkNotizia);
                        for(int i=0;i<feedBackTemp.getCommenti().size();i++){
                            if (feedBackTemp.getCommenti().get(i).equals(getTreeItem().getValue().toString())){
                                commentoEsistente = true;
                                feedbackTempInQuestione=feedBackTemp;
                                parereInQuestione = feedBackTemp.getCommenti().get(i);
                                linkInQuestione = linkNotizia;
                                break;
                            }
                        }
                    }
                    if(commentoEsistente){
                        listaNotizieCommentate.get(linkInQuestione).getCommenti().remove(parereInQuestione);
                        listaNotizieCommentate.keySet().remove(feedbackTempInQuestione);
                        getTreeItem().getParent().getChildren().remove(getTreeItem());
                        nonSalvataggio();
                    }
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



    //roba per navigare le pagine sul pannello
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

    public void switchToFeedManager(ActionEvent event) throws IOException {
        //pop-up di allarme, ti ricorda di salvare prima di cambiare pagina
        if(!salvato) {
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
        }
        else{
            Parent root = FXMLLoader.load(getClass().getResource("FeedManager.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            String css = this.getClass().getResource("application.css").toExternalForm();
            scene.getStylesheets().add(css);
        }
    }


    public void switchToUserManager(ActionEvent event) throws IOException{
        //pop-up di allarme, ti ricorda di salvare prima di cambiare pagina
        if(!salvato) {
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
        }
        else{
            Parent root = FXMLLoader.load(getClass().getResource("UserManager.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            String css = this.getClass().getResource("application.css").toExternalForm();
            scene.getStylesheets().add(css);
        }
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
        try {
            gf.salvaSuFile(this.listaNotizieCommentate);
        } catch (IOException e) {
            System.out.println("file notizieCommentate.json non trovato");
            throw new RuntimeException(e);
        }
    }




}
