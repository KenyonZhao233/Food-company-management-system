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
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class LeadContorller implements Initializable {

    public Staff staff;

    @FXML
    private AnchorPane LeadPane;

    @FXML
    private Button btnXS;

    @FXML
    private Button btnCW;

    @FXML
    private Button btnYW;

    @FXML
    private Button btnCP;

    @FXML
    private Button btnYL;

    @FXML
    private Button btnCJ;

    @FXML
    private Button btnJH;

    @FXML
    private Button btnRY;

    @FXML
    void handleClick(MouseEvent event) throws Exception{
        Button actionBtn = (Button) event.getSource();
        if(actionBtn == btnCJ)  //生产车间
        {

        }
        else if (actionBtn == btnCP) //成品库
        {

        }
        else if (actionBtn == btnCW) //财务部
        {

        }
        else if (actionBtn == btnJH) //生产计划部
        {

        }
        else if (actionBtn == btnYL) //原料库
        {

        }
        else if (actionBtn == btnXS) //销售部
        {

        }
        else if (actionBtn == btnRY) //人员管理
        {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/UserManage.fxml"));

            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.setScene(new Scene((Parent) loader.load()));

            UserManageContorller controller = loader.<UserManageContorller>getController();

            controller.initData(staff);

            stage.show();

            Stage index = (Stage)LeadPane.getScene().getWindow();
            index.close();
        }
        else if (actionBtn == btnYW) //部门管理
        {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/Section.fxml"));

            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.setScene(new Scene((Parent) loader.load()));

            SectionController controller = loader.<SectionController>getController();

            controller.initData(staff);

            stage.show();

            Stage index = (Stage)LeadPane.getScene().getWindow();
            index.close();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void initData(Staff staff)
    {
        this.staff = staff;
        if(staff.isGly)
            btnRY.setDisable(false);
        if(staff.isGlz)
            btnYW.setDisable(false);
        for (int i = 0; i < 7; i++)
        {
            if(staff.zw[1][0] == 1)
            {
                btnCW.setDisable(false);
            }
            else if(staff.zw[2][0] == 1)
            {
                btnXS.setDisable(false);
            }
            else if (staff.zw[3][0] == 1)
            {
                btnCP.setDisable(false);
            }
            else if (staff.zw[4][0] == 1)
            {
                btnYL.setDisable(false);
            }
            else if (staff.zw[5][0] == 1)
            {
                btnCJ.setDisable(false);
            }
            else if (staff.zw[6][0] == 1)
            {
                btnJH.setDisable(false);
            }
        }
    }
}
