package com.JKX.Controller;

import com.JKX.Model.Staff;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserManageContorller implements Initializable {

    private Staff staff;

    @FXML
    private AnchorPane UserManagePane;

    @FXML
    private Button userChange, userAdd, userSearch, btnSignout;

    @FXML
    private Pane pnlChange, pnlAdd, pnlSearch, paneEmpty;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void handleClicks(MouseEvent mouseEvent) throws IOException {
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
        if(actionBtn == btnSignout)
        {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/Lead.fxml"));

            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.setScene(new Scene((Parent) loader.load()));

            LeadContorller controller = loader.<LeadContorller>getController();

            controller.initData(staff);

            stage.show();

            Stage index = (Stage)UserManagePane.getScene().getWindow();
            index.close();
        }
    }

    public void initData(Staff staff)
    {
        this.staff = staff;
    }
}
