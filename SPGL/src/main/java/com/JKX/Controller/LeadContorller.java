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
        if(actionBtn == btnCJ)
        {

        }
        else if (actionBtn == btnCP)
        {

        }
        else if (actionBtn == btnCW)
        {

        }
        else if (actionBtn == btnJH)
        {

        }
        else if (actionBtn == btnYL)
        {

        }
        else if (actionBtn == btnXS)
        {

        }
        else if (actionBtn == btnRY)
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
        else if (actionBtn == btnYW)
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
        btnXS.setDisable(false);
        for (String str : staff.zw) {
            if(str.equals("财务部"))
            {
                btnCW.setDisable(false);
            }
            else if(str.equals("销售部"))
            {
                btnXS.setDisable(false);
            }
            else if (str.equals("成品库"))
            {
                btnCP.setDisable(false);
            }
            else if (str.equals( "原料库"))
            {
                btnYL.setDisable(false);
            }
            else if (str.equals("生产车间"))
            {
                btnCJ.setDisable(false);
            }
            else if (str.equals("生产计划部"))
            {
                btnJH.setDisable(false);
            }
            else if (str.equals( "管理员"))
            {
                btnRY.setDisable(false);
            }
            else if (str.equals( "管理者"))
            {
                btnYW.setDisable(false);
            }
        }
    }
}
