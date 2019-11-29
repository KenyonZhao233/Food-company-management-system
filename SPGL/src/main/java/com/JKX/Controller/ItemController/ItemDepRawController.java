package com.JKX.Controller.ItemController;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

public class ItemDepRawController {

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
    private Label prize;

    @FXML
    private Label count;

    @FXML
    private Label num;

    public void setInform(String time, String id, String name, String date, String prize, String count, String num)
    {
        this.time.setText(time);
        this.id.setText(id);
        this.name.setText(name);
        this.date.setText(date);
        this.prize.setText(prize);
        this.count.setText(count);
        this.num.setText(num);
    }

    @FXML
    void initialize() {
        assert time != null : "fx:id=\"time\" was not injected: check your FXML file 'ItemDepRaw.fxml'.";
        assert id != null : "fx:id=\"id\" was not injected: check your FXML file 'ItemDepRaw.fxml'.";
        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'ItemDepRaw.fxml'.";
        assert date != null : "fx:id=\"date\" was not injected: check your FXML file 'ItemDepRaw.fxml'.";
        assert prize != null : "fx:id=\"prize\" was not injected: check your FXML file 'ItemDepRaw.fxml'.";
        assert count != null : "fx:id=\"count\" was not injected: check your FXML file 'ItemDepRaw.fxml'.";
        assert num != null : "fx:id=\"num\" was not injected: check your FXML file 'ItemDepRaw.fxml'.";

    }
}