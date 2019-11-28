package com.JKX.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class EndproductController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button menuHomepage;

    @FXML
    private Button menuQuery;

    @FXML
    private Button menuStorage;

    @FXML
    private Button menuDestroy;

    @FXML
    private Button menuQuit;

    @FXML
    private Pane pageQuery;

    @FXML
    private VBox pnItems3;

    @FXML
    private Pane pageStorage;

    @FXML
    private VBox pnItems11;

    @FXML
    private Pane pageDestroy;

    @FXML
    private VBox pnItems1;

    @FXML
    private Pane pageHomepage;

    @FXML
    private VBox pnItems32;

    @FXML
    void handleClicks(MouseEvent event) {
        if (event.getSource() == menuHomepage) {
            pageHomepage.setStyle("-fx-background-color : #02030A");
            pageHomepage.toFront();
        }
        if (event.getSource() == menuQuery) {
            pageQuery.setStyle("-fx-background-color : #02030A");
            pageQuery.toFront();
        }
        if (event.getSource() == menuStorage) {
            pageStorage.setStyle("-fx-background-color : #02030A");
            pageStorage.toFront();
        }
        if(event.getSource() == menuDestroy)
        {
            pageDestroy.setStyle("-fx-background-color : #02030A");
            pageDestroy.toFront();
        }
        if(event.getSource() == menuQuit)
        {

        }
    }

    @FXML
    void initialize() {
        assert menuHomepage != null : "fx:id=\"menuHomepage\" was not injected: check your FXML file 'DepEndproduct.fxml'.";
        assert menuQuery != null : "fx:id=\"menuQuery\" was not injected: check your FXML file 'DepEndproduct.fxml'.";
        assert menuStorage != null : "fx:id=\"menuStorage\" was not injected: check your FXML file 'DepEndproduct.fxml'.";
        assert menuDestroy != null : "fx:id=\"menuDestroy\" was not injected: check your FXML file 'DepEndproduct.fxml'.";
        assert menuQuit != null : "fx:id=\"menuQuit\" was not injected: check your FXML file 'DepEndproduct.fxml'.";
        assert pageQuery != null : "fx:id=\"pageQuery\" was not injected: check your FXML file 'DepEndproduct.fxml'.";
        assert pnItems3 != null : "fx:id=\"pnItems3\" was not injected: check your FXML file 'DepEndproduct.fxml'.";
        assert pageStorage != null : "fx:id=\"pageStorage\" was not injected: check your FXML file 'DepEndproduct.fxml'.";
        assert pnItems11 != null : "fx:id=\"pnItems11\" was not injected: check your FXML file 'DepEndproduct.fxml'.";
        assert pageDestroy != null : "fx:id=\"pageDestroy\" was not injected: check your FXML file 'DepEndproduct.fxml'.";
        assert pnItems1 != null : "fx:id=\"pnItems1\" was not injected: check your FXML file 'DepEndproduct.fxml'.";
        assert pageHomepage != null : "fx:id=\"pageHomepage\" was not injected: check your FXML file 'DepEndproduct.fxml'.";
        assert pnItems32 != null : "fx:id=\"pnItems32\" was not injected: check your FXML file 'DepEndproduct.fxml'.";

    }
}
