package com.JKX.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class UserManageContorller implements Initializable {

    @FXML
    private Button userChange, userAdd, userSearch;

    @FXML
    private Pane pnlChange, pnlAdd, pnlSearch, paneEmpty;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void handleClicks(MouseEvent mouseEvent) {
        Button actionBtn = (Button) mouseEvent.getSource();
        paneEmpty.toFront();
        if(actionBtn == userChange)
        {
            pnlChange.toFront();
        }
        if(actionBtn == userAdd)
        {
            pnlAdd.toFront();
        }
        if(actionBtn == userSearch)
        {
            pnlSearch.toFront();
        }
    }
}
