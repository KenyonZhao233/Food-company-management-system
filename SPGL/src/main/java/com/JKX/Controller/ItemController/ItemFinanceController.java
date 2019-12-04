package com.JKX.Controller.ItemController;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemFinanceController{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane pane;

    @FXML
    private Label id;

    @FXML
    private Label user;

    @FXML
    private Label money;

    @FXML
    private Label time;

    @FXML
    private Label inf;

    public void setInform(String id, String user, String money, String time, String inf)
    {
        this.time.setText(time);
        this.id.setText(id);
        this.user.setText(user);

        if(Integer.parseInt(money) > 0){
            this.money.setTextFill(Color.RED);
            this.money.setText("+" + money);
        }
        else
            this.money.setText(money);

        this.inf.setText(inf);
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
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'ItemFinance.fxml'.";
        assert id != null : "fx:id=\"id\" was not injected: check your FXML file 'ItemFinance.fxml'.";
        assert user != null : "fx:id=\"user\" was not injected: check your FXML file 'ItemFinance.fxml'.";
        assert money != null : "fx:id=\"money\" was not injected: check your FXML file 'ItemFinance.fxml'.";
        assert time != null : "fx:id=\"time\" was not injected: check your FXML file 'ItemFinance.fxml'.";
        assert inf != null : "fx:id=\"inf\" was not injected: check your FXML file 'ItemFinance.fxml'.";

    }
}
