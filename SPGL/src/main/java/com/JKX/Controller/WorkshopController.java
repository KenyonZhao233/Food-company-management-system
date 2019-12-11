package com.JKX.Controller;

import com.JKX.Controller.ItemController.PlanItemController;
import com.JKX.Model.Staff;
import com.JKX.Model.Table.Plan;
import com.JKX.Model.WorkshopSection;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;


public class WorkshopController {

    @FXML
    private JFXButton searchPlan1, searchPlan2;

    @FXML
    private VBox vBox1, vBox2;

    @FXML
    private Button doPlan, pushPlan, btnSignout;

    @FXML
    private AnchorPane pushPlanPane, doPlanPane,WorkshopPane;

    @FXML
    private TextField planId1, planId2;

    private WorkshopSection workshopSection;

    public void initData(Staff staff)
    {
        this.workshopSection = new WorkshopSection(staff);
    }

    public WorkshopSection getWorkshopSection() {
        return workshopSection;
    }

    public void deleteVbox1(Node node){
        this.vBox1.getChildren().remove(node);
    }

    public void deleteVbox2(Node node)
    {
        this.vBox2.getChildren().remove(node);
    }

    public void handleSearch(MouseEvent mouseEvent) {
        JFXButton actionBtn = (JFXButton)mouseEvent.getSource();
        if(actionBtn == this.searchPlan1)
        {
                try {
                    vBox1.getChildren().clear();
                    String inform;
                    if(this.planId1.getText().isEmpty())
                        inform = "全部";
                    else
                        inform = this.planId1.getText();
                    System.out.println(inform);
                    Plan[] plans = this.workshopSection.searchPlan(inform, "待执行");
                    for(int i = 0; i < plans.length; i++)
                    {
                        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/PlanItem.fxml"));
                        Node node = loader.load();

                        PlanItemController planItemController = loader.<PlanItemController>getController();
                        planItemController.setWorkshopController(this);
                        planItemController.setInform(plans[i], 0);
                        planItemController.setNode(node);
                        planItemController.setConfirmVisable(false);
                        planItemController.setDeleteVisable(false);
                        planItemController.setChangeVisable(false);
                        planItemController.setNumEditable(false);
                        planItemController.setPushNumVisable(false);
                        planItemController.setPushVisable(false);
                        planItemController.setDoPlanVisable(true);
                        planItemController.setDeadlineEditable(false);

                        vBox1.getChildren().add(node);
                    }
                }
                catch (SQLException | IOException se)
                {
                    se.printStackTrace();
                }
        }
        else if (actionBtn == searchPlan2)
        {
            try {
                vBox2.getChildren().clear();
                String inform;
                if(this.planId2.getText().isEmpty())
                    inform = "全部";
                else
                    inform = this.planId2.getText();
                Plan[] plans = this.workshopSection.searchPlan(inform, "执行中");
                String name = this.workshopSection.getStaff().Name;
                for(int i = 0; i < plans.length; i++)
                {
                    System.out.println(plans[i].getZrr() + "    " + name);
                    if(plans[i].getZrr().equals(name))
                    {
                        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/PlanItem.fxml"));
                        Node node = loader.load();

                        PlanItemController planItemController = loader.<PlanItemController>getController();
                        planItemController.setWorkshopController(this);
                        planItemController.setInform(plans[i], 0);
                        planItemController.setNode(node);
                        planItemController.setConfirmVisable(false);
                        planItemController.setDeleteVisable(false);
                        planItemController.setChangeVisable(false);
                        planItemController.setNumEditable(false);
                        planItemController.setPushNumVisable(true);
                        planItemController.setPushVisable(true);

                        vBox2.getChildren().add(node);
                    }
                }
            }
            catch (SQLException | IOException se)
            {
                se.printStackTrace();
                this.workshopSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "查询失败", "系统错误");
            }
        }
    }

    public void handleClicks(MouseEvent mouseEvent) throws IOException {
        Button actionBtn = (Button)mouseEvent.getSource();
        if(actionBtn == this.doPlan)
        {
            this.doPlanPane.toFront();
        }
        else if(actionBtn == this.pushPlan)
        {
            this.pushPlanPane.toFront();
        }
        else if(actionBtn == this.btnSignout)
        {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/Lead.fxml"));

            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.setScene(new Scene((Parent) loader.load()));

            LeadContorller controller = loader.<LeadContorller>getController();

            controller.initData(this.workshopSection.getStaff());

            stage.show();

            Stage index = (Stage)WorkshopPane.getScene().getWindow();
            index.close();
        }
    }
}
