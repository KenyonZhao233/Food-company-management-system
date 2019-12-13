package com.JKX.Controller.ItemController;

import com.JKX.Controller.ProductionPlanController;
import com.JKX.Model.Staff;
import com.JKX.Model.Table.Production;
import com.JKX.Model.Table.Raw;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;
import java.util.Optional;

public class ProductItemController {

    public ProductionPlanController productionPlanController;

    public Production production;

    private int flag;

    private Node node;

    final ObservableList<Raw> data = FXCollections.observableArrayList();

    @FXML
    private Label pro_id, pro_name, p1, p2, p3, bzq;
    @FXML
    private TableView<Raw> raw_table;
    @FXML
    private JFXButton delete;

    public void setProductionPlanController(ProductionPlanController productionPlanController) {
        this.productionPlanController = productionPlanController;
    }

    public void setInform(Production production, int flag)
    {
        this.flag = flag;
        this.production = production;
        Raw[] raws = production.getRaws();
        for (int i = 0; i < raws.length; i++) {
            data.add(raws[i]);
        }
        ObservableList<TableColumn<Raw, ?>> observableList = raw_table.getColumns();
        observableList.get(0).setCellValueFactory(new PropertyValueFactory("raw_id"));
        observableList.get(1).setCellValueFactory(new PropertyValueFactory("raw_name"));
        observableList.get(2).setCellValueFactory(new PropertyValueFactory("raw_num"));
        raw_table.setItems(data);
        this.production = production;
        this.pro_id.setText(production.getProduction_id());
        this.pro_name.setText(production.getProduction_name());
        this.p1.setText(String.valueOf(production.getProduction_p1()));
        this.p2.setText(String.valueOf(production.getProduction_p2()));
        this.p3.setText(String.valueOf(production.getProduction_p3()));
        this.bzq.setText(String.valueOf(production.getProduction_bzq()));
    }

    public void setDeleteDisable(boolean deleteDisable)
    {
        this.delete.setDisable(deleteDisable);
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public void handleClick(MouseEvent mouseEvent) {
        if(flag == 1) {
            this.productionPlanController.setChangeCpInform(this.production);
        }
    }

    public void handleDelete(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("确认删除？");
        Optional result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                this.productionPlanController.getPlanSection().deleteCp(this.production.getProduction_id());
                this.productionPlanController.deletevboxChangeCp(this.node);
                Staff.showAlert(Alert.AlertType.INFORMATION, "成功", "删除成功", "");
            }
            catch (SQLException se)
            {
                se.printStackTrace();
                Staff.showAlert(Alert.AlertType.ERROR, "失败", "删除失败", "不可删除");
            }
        }
    }
}
