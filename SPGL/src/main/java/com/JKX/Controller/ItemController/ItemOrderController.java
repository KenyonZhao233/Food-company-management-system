package com.JKX.Controller.ItemController;

import com.JKX.Controller.EndproductController;
import com.JKX.Model.EndSection;
import com.JKX.Model.Staff;
import com.JKX.Model.Table.Production;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.sql.SQLException;

public class ItemOrderController {

    @FXML
    private AnchorPane pane;

    @FXML
    private Label id;

    @FXML
    private Label cus;

    @FXML
    private Label time;

    @FXML
    private TableView<Production> pro;

    private EndproductController endproductController;

    private EndSection endSection;

    public void setInform(String id, String cus, String time, EndSection endSection,EndproductController endproductController)
    {
        this.time.setText("订单时间："+time);
        this.id.setText("订单编号："+id);
        this.cus.setText("客户编号："+cus);
        this.endproductController = endproductController;
        this.endSection = endSection;
        try {
            String [][] ans = endSection.itemorder(id);
            final ObservableList<Production> data = FXCollections.observableArrayList();
            for (int i = 1; i < ans.length; i++) {
                data.add(new Production(ans[i][0], Integer.parseInt(ans[i][1])));
            }
            ObservableList<TableColumn<Production, ?>> observableList = pro.getColumns();
            observableList.get(0).setCellValueFactory(new PropertyValueFactory("production_name"));
            observableList.get(1).setCellValueFactory(new PropertyValueFactory("production_bzq"));
            pro.setItems(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void click_call(MouseEvent event) throws SQLException {
        Alert _alert = new Alert(Alert.AlertType.CONFIRMATION);
        _alert.setTitle("发货系统");
        _alert.setHeaderText("确认通知发货");
        _alert.setContentText("订单编号：" + this.id.getText() + "         客户编号：" + this.cus.getText() );
        _alert.showAndWait();
        if(_alert.getResult()== ButtonType.OK) {
            if(endSection.Send(this.id.getText().substring(5))){
                endproductController.fresh(event);
            }else{
                Alert _alert2 = new Alert(Alert.AlertType.WARNING);
                _alert2.setTitle("发货系统");
                _alert2.setHeaderText("错误");
                _alert2.setContentText("成品数量不足！");
                _alert2.show();
            }
        }
        endproductController.fresh(event);
    }

    @FXML
    void enter(MouseEvent event) {
        pane.setStyle("-fx-background-color : #22A7F0");
    }

    @FXML
    void exit(MouseEvent event) {
        pane.setStyle("-fx-background-color : #02030A");
    }

}