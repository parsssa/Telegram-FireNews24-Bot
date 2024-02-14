package com.example.inferno_fx;

import com.example.inferno_fx.OperazioniJSON.MappaCategorie;
import com.example.inferno_fx.OperazioniJSON.Utente;
import com.example.inferno_fx.OperazioniJSON.gestoreGsonUtente;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;



public class ControllerUserManager implements Initializable{
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Pane userPane;
    @FXML
    private ImageView floppyDisk;


    private boolean salvato = true;








    private final Node rootIcon =
            new ImageView(new Image(getClass().getResourceAsStream("categorie.png")));
    private final Image depIcon =
            new Image(getClass().getResourceAsStream("Folder.png"));


    TreeItem<String> rootNode =
            new TreeItem<String>("Utenti presenti nel Bot", new ImageView(new Image(getClass().getResourceAsStream("VariePNG/community.png"),22, 18, false, true)));

    public ArrayList<Utente> UserList = new ArrayList<>();
    gestoreGsonUtente ggu = new gestoreGsonUtente();







    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        try {
            this.UserList = ggu.readJsonLista("ListaUtentiVari.json");
        } catch (
                FileNotFoundException e) {
            System.out.println("File Json Utenti trovato!!!!");
        }

        //all'inizio metto il tasto salvataggio trasparente perche' non c'e' nulla da salvare
        floppyDisk.setOpacity(0.5);
        //

        rootNode.setExpanded(true);
        for (Utente u : UserList) {
            TreeItem<String> userLeaf = new TreeItem<String>(u.getUserName(), new ImageView(new Image(getClass().getResourceAsStream("VariePNG/UserAvatar2.png"),14, 14, false, true)));
                    rootNode.getChildren().add(userLeaf);

                }



        TreeView<String> treeView = new TreeView<String>(rootNode);
        treeView.setEditable(true);

        treeView.setCellFactory(new Callback<TreeView<String>,TreeCell<String>>(){
            @Override
            public TreeCell<String> call(TreeView<String> p){
                return new TextFieldTreeCellImpl();
            }
        });

        userPane.getChildren().add(treeView);


    }



    //roba per navigare le pagine sul pannello

    public void switchToAutenticazioneAdmin(ActionEvent event) throws IOException {
        if(!salvato) {
            //pop-up di allarme, chiede se sei sicuro di voler fare il logout
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Salvataggio");
            alert.setHeaderText("Stai per effettuare il logout");
            alert.setContentText("Considera un salvataggio prima di uscire");
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
            alert.setHeaderText("Stai per cambiare pagina");
            alert.setContentText("Considera un salvataggio prima di passare al Gestore Feed");
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

    public void aggiungiUtente(ActionEvent event)throws IOException{
        Parent root = null;
        try {
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            root = FXMLLoader.load(getClass().getResource("AggiungiUtente.fxml"));
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

    public void eliminaUtente(ActionEvent event)throws IOException{
        Parent root = null;
        try {
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            root = FXMLLoader.load(getClass().getResource("EliminaUtente.fxml"));
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
            System.out.println("Errore nel caricare ModificaUtente.fxml");
            throw new RuntimeException(e);
        }
    }

    public void nonSalvataggio(){
        this.salvato = false;
        this.floppyDisk.setOpacity(1);
    }

    public void salvataggio(MouseEvent event){
        salvato = true;
        this.floppyDisk.setOpacity(0.5);
        //l'idea e' che -premuto il tasto salvataggio- scorro gli elementi della TreeView, li metto in una nuovaLista,
        //e tolgo dalla UserList gli elementi che non ci sono piu' nella nuovaLista

       ArrayList<String> nuovaUserNameList = new ArrayList<>();

       for (TreeItem tastoUtente:rootNode.getChildren()){
           String testoTasto = tastoUtente.getValue().toString();
           nuovaUserNameList.add(testoTasto);
       }
       for(int i=0; i<UserList.size(); i++){
           if(!nuovaUserNameList.contains(UserList.get(i).getUserName())){
               UserList.remove(i);
           }
       }

        try {
            ggu.writeJson(UserList);
        } catch (IOException e) {
            System.out.println("Errore a trovare file json per aggiornare gli utenti");;
        }

    }



//classe privata all'interno di classe principale


    private final class TextFieldTreeCellImpl extends TreeCell<String> {

        private TextField textField;
        private ContextMenu addMenu = new ContextMenu();
        //non vado ad implementare addMenu (contextMenu) che tanto non serve  per gli Utenti


        public TextFieldTreeCellImpl() {

        }






        /*@Override
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
        }*/

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

                        setOnMouseClicked(new EventHandler() {
                            public void handle(Event t) {
                                try {
                                    PrintWriter printWriter = new PrintWriter("usernameUtenteInQuestione.txt");
                                    printWriter.println(getTreeItem().getValue());
                                    printWriter.close();
                                } catch (FileNotFoundException e) {
                                    throw new RuntimeException(e);
                                }


                                Parent root = null;
                                try {
                                    stage = (Stage) ((Node) t.getSource()).getScene().getWindow();
                                    stage.close();
                                    root = FXMLLoader.load(getClass().getResource("ModificaUtente.fxml"));
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
                        });
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




}
