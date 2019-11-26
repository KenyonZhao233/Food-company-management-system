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

public class SectionController implements Initializable {

    public Staff staff;

    @FXML
    private Pane mainPane, xsPane, cwPane, cpkPane, ylkPane, sccjPane, scjhPane, emptyPane, pnlTime, pnlName;

    @FXML
    private AnchorPane SectionPane;

    @FXML
    private Button btnXs;

    @FXML
    private Button btnCw;

    @FXML
    private Button btnCp;

    @FXML
    private Button btnYl;

    @FXML
    private Button btnCj;

    @FXML
    private Button btnJh;

    @FXML
    private Button btnSignout;

    @FXML
    private Button btnSearchCp;

    @FXML
    private Button btnSearchOrder;

    @FXML
    private Button btnSearchXl;

    @FXML
    private Button btnSearchKc;

    @FXML
    private Button btnXsReturn;

    @FXML
    private Button btnSearchLs;

    @FXML
    private Button btnSearchDfk;

    @FXML
    private Button btnSearchDfk2;

    @FXML
    private Button btnCwReturn;

    @FXML
    private Button btnSearchCK;

    @FXML
    private Button btnSearchDth;

    @FXML
    private Button btnCpReturn;

    @FXML
    private Button btnSearchYl;

    @FXML
    private Button btnYlReturn;

    @FXML
    private Button btnSearchScjh;

    @FXML
    private Button btnCccjReturn;

    @FXML
    private Button btnSearchScjh2;

    @FXML
    private Button btnSearchXl2;

    @FXML
    private Button btnSearchOrder2;

    @FXML
    private Button btnSearchYl2;

    @FXML
    private Button btnSearchKc2;

    @FXML
    private Button btnSearchCp2;

    @FXML
    private Button btnSearchCj;

    @FXML
    private Button btnScjhReturn;

    @FXML
    private Button btnSearchKh;

    @FXML
    private Button btnSeachTh;

    @FXML
    public void handleClicks(MouseEvent event) throws IOException {
        Button actionBtn = (Button) event.getSource();
        emptyPane.toFront();
        if(actionBtn == btnXs)
        {
            xsPane.toFront();
        }
        if(actionBtn == btnCw)
        {
            System.out.println("sds");
            cwPane.toFront();
        }
        if(actionBtn == btnCp)
        {
            cpkPane.toFront();
        }
        if(actionBtn == btnYl)
        {
            ylkPane.toFront();
        }
        if(actionBtn == btnCj)
        {
            sccjPane.toFront();
        }
        if(actionBtn == btnJh)
        {
            scjhPane.toFront();
        }
        if(actionBtn == btnCccjReturn || actionBtn == btnCpReturn || actionBtn == btnCwReturn || actionBtn == btnScjhReturn || actionBtn == btnXsReturn || actionBtn == btnYlReturn)
        {
            mainPane.toFront();
        }
        if(actionBtn == btnSearchOrder || actionBtn == btnSearchOrder2 || actionBtn == btnSearchLs || actionBtn == btnSearchDfk || actionBtn == btnSearchDfk2 || actionBtn == btnSearchDth || actionBtn == btnSearchScjh || actionBtn == btnSearchScjh2)
        {
            pnlTime.toFront();
        }
        if(actionBtn == btnSeachTh || actionBtn == btnSearchKh || actionBtn == btnSearchXl || actionBtn == btnSearchXl2 || actionBtn == btnSearchKc || actionBtn == btnSearchKc2 || actionBtn == btnSearchCK || actionBtn == btnSearchYl || actionBtn == btnSearchYl2 || actionBtn == btnSearchCp2 || actionBtn == btnSearchCp || actionBtn == btnSearchCj)
        {
            pnlName.toFront();
        }
        if(actionBtn == btnSignout)
        {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/Lead.fxml"));

            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.setScene(new Scene((Parent) loader.load()));

            LeadContorller controller = loader.<LeadContorller>getController();

            controller.initData(staff);

            stage.show();

            Stage index = (Stage)SectionPane.getScene().getWindow();
            index.close();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void initData(Staff staff)
    {
        this.staff = staff;
    }
}
