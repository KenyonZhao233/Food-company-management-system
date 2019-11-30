package com.JKX.Controller;

import com.JKX.Controller.ItemController.ItemDepRawDestroyController;
import com.JKX.Model.Raw;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.JKX.Controller.ItemController.ItemDepRawController;
import javafx.util.Duration;


public class RawController {

    private static Staff staff;
    private static Raw raw;

    DateFormat df = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
    Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            String time = df.format(new Date());
            label_year.setText(time.substring(0,4));
            label_month.setText(time.substring(5,7));
            label_day.setText(time.substring(8,10));
            label_time.setText(time.substring(11,19));
            label_year2.setText(time.substring(0,4));
            label_month2.setText(time.substring(5,7));
            label_day2.setText(time.substring(8,10));
            label_time2.setText(time.substring(11,19));
        }
    }));

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
    private Label label_year2;

    @FXML
    private Label label_month2;

    @FXML
    private Label label_day2;

    @FXML
    private Label label_time2;

    @FXML
    private VBox pnItems3;

    @FXML
    private VBox raw_items1;

    @FXML
    private Pane pageStorage;

    @FXML
    private VBox raw_items2;

    @FXML
    private Pane pageDestroy;

    @FXML
    private VBox pnItems1;

    @FXML
    private VBox raw_items3;

    @FXML
    private Pane pageHomepage;

    @FXML
    private Label label_year;

    @FXML
    private Label label_month;

    @FXML
    private Label label_day;

    @FXML
    private Label label_time;

    @FXML
    private VBox raw_items;

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

    static String[][] getInform() throws SQLException
    {
        String[][] ans;
        ans = raw.Search("select * from raw, raw_ck where raw.raw_id = raw_ck.raw_id order by raw_date DESC");
        return ans;
    }


    @FXML
    void initialize() {
        assert menuHomepage != null : "fx:id=\"menuHomepage\" was not injected: check your FXML file 'DepRaw.fxml'.";
        assert menuQuery != null : "fx:id=\"menuQuery\" was not injected: check your FXML file 'DepRaw.fxml'.";
        assert menuStorage != null : "fx:id=\"menuStorage\" was not injected: check your FXML file 'DepRaw.fxml'.";
        assert menuDestroy != null : "fx:id=\"menuDestroy\" was not injected: check your FXML file 'DepRaw.fxml'.";
        assert menuQuit != null : "fx:id=\"menuQuit\" was not injected: check your FXML file 'DepRaw.fxml'.";
        assert pageQuery != null : "fx:id=\"pageQuery\" was not injected: check your FXML file 'DepRaw.fxml'.";
        assert label_year2 != null : "fx:id=\"label_year2\" was not injected: check your FXML file 'DepRaw.fxml'.";
        assert label_month2 != null : "fx:id=\"label_month2\" was not injected: check your FXML file 'DepRaw.fxml'.";
        assert label_day2 != null : "fx:id=\"label_day2\" was not injected: check your FXML file 'DepRaw.fxml'.";
        assert label_time2 != null : "fx:id=\"label_time2\" was not injected: check your FXML file 'DepRaw.fxml'.";
        assert pnItems3 != null : "fx:id=\"pnItems3\" was not injected: check your FXML file 'DepRaw.fxml'.";
        assert raw_items1 != null : "fx:id=\"raw_items1\" was not injected: check your FXML file 'DepRaw.fxml'.";
        assert pageStorage != null : "fx:id=\"pageStorage\" was not injected: check your FXML file 'DepRaw.fxml'.";
        assert raw_items2 != null : "fx:id=\"raw_items2\" was not injected: check your FXML file 'DepRaw.fxml'.";
        assert pageDestroy != null : "fx:id=\"pageDestroy\" was not injected: check your FXML file 'DepRaw.fxml'.";
        assert pnItems1 != null : "fx:id=\"pnItems1\" was not injected: check your FXML file 'DepRaw.fxml'.";
        assert raw_items3 != null : "fx:id=\"raw_items3\" was not injected: check your FXML file 'DepRaw.fxml'.";
        assert label_year != null : "fx:id=\"label_year\" was not injected: check your FXML file 'DepRaw.fxml'.";
        assert label_month != null : "fx:id=\"label_month\" was not injected: check your FXML file 'DepRaw.fxml'.";
        assert label_day != null : "fx:id=\"label_day\" was not injected: check your FXML file 'DepRaw.fxml'.";
        assert label_time != null : "fx:id=\"label_time\" was not injected: check your FXML file 'DepRaw.fxml'.";
        assert pageHomepage != null : "fx:id=\"pageHomepage\" was not injected: check your FXML file 'DepRaw.fxml'.";
        assert raw_items != null : "fx:id=\"raw_items\" was not injected: check your FXML file 'DepRaw.fxml'.";
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
        staff = new Staff("KenyonZ", "123456");

        if(staff.Login()){
            System.out.println("成功");
            raw = new Raw(staff);
        }

        try {
            this.raw_items.getChildren().clear();
            String[][] ans = this.getInform();
            for (int i = 1; i < ans.length; i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/ItemDepRaw.fxml"));
                Node node = null;
                try {
                    node = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ItemDepRawController itemDepRawController = loader.<ItemDepRawController>getController();
                itemDepRawController.setInform(ans[i][4], ans[i][0], ans[i][1], ans[i][2], ans[i][3], ans[i][6], ans[i][7]);
                this.raw_items.getChildren().add(node);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            this.raw_items1.getChildren().clear();
            String[][] ans = this.getInform();
            for (int i = 1; i < ans.length; i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/ItemDepRaw.fxml"));
                Node node = null;
                try {
                    node = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ItemDepRawController itemDepRawController = loader.<ItemDepRawController>getController();
                itemDepRawController.setInform(ans[i][4], ans[i][0], ans[i][1], ans[i][2], ans[i][3], ans[i][6], ans[i][7]);
                this.raw_items1.getChildren().add(node);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            this.raw_items3.getChildren().clear();
            String[][] ans = this.getInform();
            for (int i = 1; i < ans.length; i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/ItemDepRaw_Destory.fxml"));
                Node node = null;
                try {
                    node = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ItemDepRawDestroyController itemDepRawDestroyController = loader.<ItemDepRawDestroyController>getController();
                itemDepRawDestroyController.setInform(ans[i][4], ans[i][0], ans[i][1], ans[i][2], ans[i][3], ans[i][6], ans[i][7]);
                this.raw_items3.getChildren().add(node);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            this.raw_items2.getChildren().clear();
            String[][] ans = this.getInform();
            for (int i = 1; i < ans.length; i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/ItemDepRaw.fxml"));
                Node node = null;
                try {
                    node = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ItemDepRawController itemDepRawController = loader.<ItemDepRawController>getController();
                itemDepRawController.setInform(ans[i][4], ans[i][0], ans[i][1], ans[i][2], ans[i][3], ans[i][6], ans[i][7]);
                this.raw_items2.getChildren().add(node);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

