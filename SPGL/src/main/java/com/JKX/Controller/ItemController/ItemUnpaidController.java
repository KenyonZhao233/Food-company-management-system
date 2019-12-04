package com.JKX.Controller.ItemController;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ItemUnpaidController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane pane;

    @FXML
    private Label no;

    @FXML
    private Label time;

    @FXML
    private Label mn;

    @FXML
    private Label id;

    public void setInform(String no, String time, String mn, String id)
    {
        this.time.setText(time);
        this.id.setText(id);
        this.no.setText(no);
        this.mn.setText(mn);
    }

    @FXML
    void enter(MouseEvent event) {
        pane.setStyle("-fx-background-color : #22A7F0");
    }

    @FXML
    void exit(MouseEvent event) {
        pane.setStyle("-fx-background-color : #02030A");
    }

    @FXML
    void initialize() {
        assert no != null : "fx:id=\"no\" was not injected: check your FXML file 'ItemUnpaid.fxml'.";
        assert time != null : "fx:id=\"time\" was not injected: check your FXML file 'ItemUnpaid.fxml'.";
        assert mn != null : "fx:id=\"mn\" was not injected: check your FXML file 'ItemUnpaid.fxml'.";
        assert id != null : "fx:id=\"id\" was not injected: check your FXML file 'ItemUnpaid.fxml'.";
    }
}
