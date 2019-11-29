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

    /*跳转按钮*/
    @FXML
    private Button btnXs, btnCw, btnCp, btnYl, btnCj, btnJh, btnSignout;

    /*查询（编号or时间）按钮*/
    @FXML
    private Button btnSearchOrder, btnSearchOrder2, btnSearchLs, btnSearchDfk, btnSearchDfk2, btnSearchDth, btnSearchScjh, btnSearchScjh2;

    /*查询（编号or名称）按钮*/
    @FXML
    private Button btnSeachTh, btnSearchKh, btnSearchXl, btnSearchXl2, btnSearchKc, btnSearchKc2, btnSearchCK, btnSearchYl, btnSearchYl2, btnSearchCp2, btnSearchCp, btnSearchCj;

    /*返回按钮*/
    @FXML
    private Button btnCccjReturn, btnCpReturn, btnCwReturn, btnScjhReturn, btnXsReturn, btnYlReturn;

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

    public void SetInform()
    {

    }

    public void handleSearch(MouseEvent event) throws IOException
    {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }

    public void initData(Staff staff)
    {
        this.staff = staff;
        if(staff.zw[0][1] != 1)
        {
            if(staff.zw[1][1] != 1)
                this.btnCw.setDisable(true);
            if(staff.zw[2][1] != 1)
                this.btnXs.setDisable(true);
            if(staff.zw[3][1] != 1)
                this.btnCp.setDisable(true);
            if(staff.zw[4][1] != 1)
                this.btnYl.setDisable(true);
            if(staff.zw[5][1] != 1)
                this.btnCj.setDisable(true);
            if(staff.zw[6][1] != 1)
                this.btnJh.setDisable(true);
        }
    }
}
