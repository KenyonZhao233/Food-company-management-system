package com.JKX.Controller;

import com.JKX.Model.Staff;
import com.JKX.Model.WorkshopSection;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;



public class WorkshopController {

    @FXML
    private JFXButton searchPlan1, searchPlan2;

    @FXML
    private VBox vBox1, vBox2;

    @FXML
    private Button doPlan, pushPlan;

    @FXML
    private AnchorPane pushPlanPane, doPlanPane;

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
    }

    public void handleClicks(MouseEvent mouseEvent) {
    }
}
