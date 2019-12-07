package com.JKX.Controller.ItemController;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemDepEndController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label time;

    @FXML
    private Label id;

    @FXML
    private Label name;

    @FXML
    private Label date;

    @FXML
    private Label count;

    @FXML
    private Label in;

    @FXML
    private AnchorPane pane;

    @FXML
    void enter(MouseEvent event) {
        pane.setStyle("-fx-background-color : #22A7F0");
    }

    @FXML
    void exit(MouseEvent event) {
        pane.setStyle("-fx-background-color : #02030A");
    }

    public void setInform(String time, String id, String name, String date, String count, String in)
    {
        this.time.setText(time);
        this.id.setText(id);
        this.name.setText(name);
        this.date.setText(date);
        this.count.setText(count);
        this.in.setText(in);
    }

    @FXML
    void initialize() {
        assert time != null : "fx:id=\"time\" was not injected: check your FXML file 'ItemDepRaw.fxml'.";
        assert id != null : "fx:id=\"id\" was not injected: check your FXML file 'ItemDepRaw.fxml'.";
        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'ItemDepRaw.fxml'.";
        assert date != null : "fx:id=\"date\" was not injected: check your FXML file 'ItemDepRaw.fxml'.";
        assert count != null : "fx:id=\"count\" was not injected: check your FXML file 'ItemDepRaw.fxml'.";
    }
}