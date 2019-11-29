package com.JKX.Controller;

import com.JKX.Model.Raw;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RawController {

    private static Raw raw;

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

    static String[][] getInform() throws SQLException
    {
        String[][] ans;
        ans = raw.Search("select * from raw");
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
        assert pnItems3 != null : "fx:id=\"pnItems3\" was not injected: check your FXML file 'DepRaw.fxml'.";
        assert pageStorage != null : "fx:id=\"pageStorage\" was not injected: check your FXML file 'DepRaw.fxml'.";
        assert pnItems11 != null : "fx:id=\"pnItems11\" was not injected: check your FXML file 'DepRaw.fxml'.";
        assert pageDestroy != null : "fx:id=\"pageDestroy\" was not injected: check your FXML file 'DepRaw.fxml'.";
        assert pnItems1 != null : "fx:id=\"pnItems1\" was not injected: check your FXML file 'DepRaw.fxml'.";
        assert pageHomepage != null : "fx:id=\"pageHomepage\" was not injected: check your FXML file 'DepRaw.fxml'.";
        assert pnItems32 != null : "fx:id=\"pnItems32\" was not injected: check your FXML file 'DepRaw.fxml'.";
        raw = new Raw("KenyonZ", "123456");
        if(raw.Login())
            System.out.println("成功");
        else
            System.out.println("失败");
        String[][] inf_all;
        try {
            inf_all = RawController.getInform();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

