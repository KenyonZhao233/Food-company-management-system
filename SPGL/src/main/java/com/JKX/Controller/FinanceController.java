package com.JKX.Controller;

import com.JKX.Controller.ItemController.ItemFinanceController;
import com.JKX.Controller.ItemController.ItemUnpaidController;
import com.JKX.Model.FinanceSection;
import com.JKX.Model.OrderSection;
import com.JKX.Model.Staff;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FinanceController {

    private OrderSection orderSection;
    private FinanceSection financeSection;

    DateFormat df = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
    Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            String timet = df.format(new Date());
            year.setText(timet.substring(0,4));
            month.setText(timet.substring(5,7));
            day.setText(timet.substring(8,10));
            time.setText(timet.substring(11,19));
        }
    }));

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
    private Pane pageReceivables;

    @FXML
    private VBox ItemsReceive;

    @FXML
    private TextField receive;

    @FXML
    private Pane pageRefund;

    @FXML
    private TextField refund;

    @FXML
    private VBox ItemsRefund;

    @FXML
    private Pane pagePay;

    @FXML
    private TextField in_text1;

    @FXML
    private TextField in_text2;

    @FXML
    private Pane pageIncome;

    @FXML
    private TextField out_text1;

    @FXML
    private TextField out_text2;

    @FXML
    private Button out;

    @FXML
    private Pane pageHomepage;

    @FXML
    private TextField qurey;

    @FXML
    private Label year;

    @FXML
    private Label month;

    @FXML
    private Label day;

    @FXML
    private Label time;

    @FXML
    private VBox ItemsHome;

    @FXML
    void click_in(MouseEvent event) {

    }

    @FXML
    void click_out(MouseEvent event) {

    }

    @FXML
    void click_query(MouseEvent event) {
        try {
            this.ItemsHome.getChildren().clear();
            String[][] ans = financeSection.Query(this.qurey.getText());
            for (int i = 1; i < ans.length; i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/ItemFinance.fxml"));
                Node node = null;
                try {
                    node = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ItemFinanceController itemFinanceController = loader.<ItemFinanceController>getController();
                itemFinanceController.setInform(ans[i][0], ans[i][1], ans[i][2], ans[i][3].substring(0,19), ans[i][4]);
                this.ItemsHome.getChildren().add(node);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void click_receive(MouseEvent event) {

    }

    @FXML
    void click_refund(MouseEvent event) {

    }

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
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
        try {
            orderSection = new OrderSection(new Staff("KenyonZ", "123456"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            financeSection = new FinanceSection(new Staff("KenyonZ", "123456"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            this.ItemsReceive.getChildren().clear();
            String[][] ans = orderSection.unpaidSearch();
            for (int i = 1; i < ans.length; i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/ItemUnpaid.fxml"));
                Node node = null;
                try {
                    node = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ItemUnpaidController itemUnpaidController = loader.<ItemUnpaidController>getController();
                itemUnpaidController.setInform(ans[i][0], ans[i][1].substring(0,19), ans[i][2], ans[i][3]);
                this.ItemsReceive.getChildren().add(node);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            this.ItemsHome.getChildren().clear();
            String[][] ans = financeSection.Search();
            for (int i = 1; i < ans.length; i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/ItemFinance.fxml"));
                Node node = null;
                try {
                    node = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ItemFinanceController itemFinanceController = loader.<ItemFinanceController>getController();
                itemFinanceController.setInform(ans[i][0], ans[i][1], ans[i][2], ans[i][3].substring(0,19), ans[i][4]);
                this.ItemsHome.getChildren().add(node);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
