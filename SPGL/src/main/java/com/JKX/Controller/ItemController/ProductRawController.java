package com.JKX.Controller.ItemController;

import com.JKX.Controller.ProductionPlanController;
import com.JKX.Model.Table.Raw;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.sql.SQLException;

public class ProductRawController {

    public ProductionPlanController productionPlanController;

    public String ids;

    public Raw raw;

    public Node node;

    @FXML
    private Label raw_id, raw_name, raw_num;
    @FXML
    private JFXButton delete;

    public void setIds(String ids) {
        this.ids = ids;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public void setProductionPlanController(ProductionPlanController productionPlanController) {
        this.productionPlanController = productionPlanController;
    }

    public void setInform(Raw raw)
    {
        this.raw = raw;
        this.raw_id.setText(raw.getRaw_id());
        this.raw_name.setText(raw.getRaw_name());
        this.raw_num.setText(String.valueOf(raw.getRaw_num()));
    }

    public void handleClick(MouseEvent mouseEvent) {
    }

    public void handleDelete(MouseEvent mouseEvent) {
        try {
            this.productionPlanController.getPlanSection().deleteCpRaws(this.ids, this.raw);
            this.productionPlanController.deleteChangeCpRawbox(this.node);
        }
        catch (SQLException se)
        {
            se.printStackTrace();
            this.productionPlanController.getPlanSection().getStaff().showAlert(Alert.AlertType.ERROR, "失败", "查询失败", "系统错误");
        }
    }
}
