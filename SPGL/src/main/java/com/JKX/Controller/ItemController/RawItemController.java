package com.JKX.Controller.ItemController;

import com.JKX.Controller.ProductionPlanController;
import com.JKX.Model.Table.Raw;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.sun.rowset.internal.Row;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;

public class RawItemController {
    private ProductionPlanController productionPlanController;

    private Node node;

    private Raw raw;

    @FXML
    private JFXTextField rawname, rawpri, bzq;
    @FXML
    private Label rawid;
    @FXML
    private JFXButton change, delete;

    public void initData(Raw raw)
    {
        this.raw = raw;
        this.rawid.setText(raw.getRaw_id());
        this.rawname.setText(raw.getRaw_name());
        this.rawpri.setText(String.valueOf(raw.getRaw_price()));
        this.bzq.setText(String.valueOf(raw.getRaw_bzq()));
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public void setProductionPlanController(ProductionPlanController productionPlanController) {
        this.productionPlanController = productionPlanController;
    }

    public void setRawnameEditable(boolean rawnameEditable)
    {
        this.rawname.setEditable(rawnameEditable);
    }

    public void setRawpriEditable(boolean rawpriEditable)
    {
        this.rawpri.setEditable(rawpriEditable);
    }

    public void setBzqEditable(boolean bzqEditable)
    {
        this.bzq.setEditable(bzqEditable);
    }

    public void setDeleteDisable(boolean deleteDisable)
    {
        this.delete.setDisable(deleteDisable);
    }

    public void setChangeDisable(boolean changeDisable)
    {
        this.change.setDisable(changeDisable);
    }

    public void handleChange(MouseEvent mouseEvent) {
        try {
            this.productionPlanController.getPlanSection().changeRaw(this.rawid.getText(), this.rawname.getText(), this.rawpri.getText(), this.bzq.getText());

        }
        catch (SQLException se)
        {
            se.printStackTrace();
            this.productionPlanController.getPlanSection().getStaff().showAlert(Alert.AlertType.ERROR, "错误", "修改失败", "系统错误");
        }
    }

    public void handleDelete(MouseEvent mouseEvent) {
        try{
            this.productionPlanController.getPlanSection().deleteRaw(this.rawid.getText());
            this.productionPlanController.deletevboxYl(node);
        }
        catch (SQLException se)
        {
            se.printStackTrace();
            this.productionPlanController.getPlanSection().getStaff().showAlert(Alert.AlertType.ERROR, "错误", "删除失败", "系统错误");
        }
    }
}