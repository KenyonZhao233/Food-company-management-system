package com.JKX.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class FinanceController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button menuHomepage;

    @FXML
    private Button menuReceivables;

    @FXML
    private Button menuRefund;

    @FXML
    private Button menuPay;

    @FXML
    private Button menuIncome;

    @FXML
    private Button menuQuit;

    @FXML
    private Pane pageHomepage;

    @FXML
    private VBox pnItems3;

    @FXML
    private Pane pageReceivables;

    @FXML
    private VBox pnItems1;

    @FXML
    private Pane pageRefund;

    @FXML
    private VBox pnItems2;

    @FXML
    private Pane pagePay;

    @FXML
    private Pane pageIncome;

    @FXML
    void handleClicks(MouseEvent event) {
        if (event.getSource() == menuHomepage) {
            pageHomepage.setStyle("-fx-background-color : #02030A");
            pageHomepage.toFront();
        }
        if (event.getSource() == menuReceivables) {
            pageReceivables.setStyle("-fx-background-color : #02030A");
            pageReceivables.toFront();
        }
        if (event.getSource() == menuRefund) {
            pageRefund.setStyle("-fx-background-color : #02030A");
            pageRefund.toFront();
        }
        if(event.getSource() == menuPay)
        {
            pagePay.setStyle("-fx-background-color : #02030A");
            pagePay.toFront();
        }
        if(event.getSource() == menuIncome)
        {
            pageIncome.setStyle("-fx-background-color : #02030A");
            pageIncome.toFront();
        }
        if(event.getSource() == menuQuit)
        {

        }
    }

    @FXML
    void initialize() {
        assert menuHomepage != null : "fx:id=\"menuHomepage\" was not injected: check your FXML file 'Home.fxml'.";
        assert menuReceivables != null : "fx:id=\"menuReceivables\" was not injected: check your FXML file 'Home.fxml'.";
        assert menuRefund != null : "fx:id=\"menuRefund\" was not injected: check your FXML file 'Home.fxml'.";
        assert menuPay != null : "fx:id=\"menuPay\" was not injected: check your FXML file 'Home.fxml'.";
        assert menuIncome != null : "fx:id=\"menuIncome\" was not injected: check your FXML file 'Home.fxml'.";
        assert menuQuit != null : "fx:id=\"menuQuit\" was not injected: check your FXML file 'Home.fxml'.";
        assert pageHomepage != null : "fx:id=\"pageHomepage\" was not injected: check your FXML file 'Home.fxml'.";
        assert pnItems3 != null : "fx:id=\"pnItems3\" was not injected: check your FXML file 'Home.fxml'.";
        assert pageReceivables != null : "fx:id=\"pageReceivables\" was not injected: check your FXML file 'Home.fxml'.";
        assert pnItems1 != null : "fx:id=\"pnItems1\" was not injected: check your FXML file 'Home.fxml'.";
        assert pageRefund != null : "fx:id=\"pageRefund\" was not injected: check your FXML file 'Home.fxml'.";
        assert pnItems2 != null : "fx:id=\"pnItems2\" was not injected: check your FXML file 'Home.fxml'.";
        assert pagePay != null : "fx:id=\"pagePay\" was not injected: check your FXML file 'Home.fxml'.";
        assert pageIncome != null : "fx:id=\"pageIncome\" was not injected: check your FXML file 'Home.fxml'.";

    }
}
