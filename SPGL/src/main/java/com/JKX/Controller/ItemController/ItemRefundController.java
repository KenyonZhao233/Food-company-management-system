package com.JKX.Controller.ItemController;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.JKX.Controller.FinanceController;
import com.JKX.Model.FinanceSection;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ItemRefundController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane pane;

    @FXML
    private Label no;

    @FXML
    private Label time;

    @FXML
    private Label mn;

    @FXML
    private Label id;

    private FinanceSection financeSection;

    private FinanceController controller;

    public void setInform(String no, String time, String mn, String id, FinanceSection financeSection,FinanceController controller)
    {
        this.time.setText(time);
        this.id.setText(id);
        this.no.setText(no);
        this.mn.setText(mn);
        this.financeSection = financeSection;
        this.controller = controller;
    }

    @FXML
    void enter(MouseEvent event) {
        pane.setStyle("-fx-background-color : #22A7F0");
    }

    @FXML
    void exit(MouseEvent event) {
        pane.setStyle("-fx-background-color : #02030A");
    }

    @FXML
    void click(MouseEvent event) throws SQLException {
        Alert _alert = new Alert(Alert.AlertType.CONFIRMATION);
        _alert.setTitle("退款系统");
        _alert.setHeaderText("确认退款信息");
        _alert.setContentText("退款编号：" + this.id.getText() + "         退款" + this.mn.getText() + "元");
        _alert.showAndWait();
        if(_alert.getResult()==ButtonType.OK) {
            financeSection.refund(this.no.getText(),this.mn.getText());
        }
        controller.click_refund(event);
        controller.click_query(event);
    }

    @FXML
    void initialize() {
        assert no != null : "fx:id=\"no\" was not injected: check your FXML file 'ItemUnpaid.fxml'.";
        assert time != null : "fx:id=\"time\" was not injected: check your FXML file 'ItemUnpaid.fxml'.";
        assert mn != null : "fx:id=\"mn\" was not injected: check your FXML file 'ItemUnpaid.fxml'.";
        assert id != null : "fx:id=\"id\" was not injected: check your FXML file 'ItemUnpaid.fxml'.";
    }
}
