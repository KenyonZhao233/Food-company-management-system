package com.JKX.Controller.ItemController;
import com.JKX.Controller.SalesController;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import com.jfoenix.controls.JFXButton;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;

public class ProductInformController {

    @FXML
    private JFXButton deleteButton;

    @FXML
    private Label OneID;

    @FXML
    private Label OnePrice;

    @FXML
    private Label OneAllPrice;

    @FXML
    private Label OneName;

    @FXML
    private Label OneNumber;

    private SalesController salesController;

    private Node node;

    public void setInform(String OneID, String OnePrice, String OneAllPrice, String OneName, String OneNumber)
    {
        this.OneID.setText(OneID);
        this.OnePrice.setText(OnePrice);
        this.OneAllPrice.setText(OneAllPrice);
        this.OneName.setText(OneName);
        this.OneNumber.setText(OneNumber);
    }

    public void setContorller(SalesController salesController)
    {
        this.salesController = salesController;
    }

    void initialize() {
        assert deleteButton != null : "fx:id=\"deleteButton\" was not injected: check your FXML file 'ProductInform.fxml'.";
        assert OneID != null : "fx:id=\"OneID\" was not injected: check your FXML file 'ProductInform.fxml'.";
        assert OnePrice != null : "fx:id=\"OnePrice\" was not injected: check your FXML file 'ProductInform.fxml'.";
        assert OneAllPrice != null : "fx:id=\"OneAllPrice\" was not injected: check your FXML file 'ProductInform.fxml'.";
        assert OneName != null : "fx:id=\"OneName\" was not injected: check your FXML file 'ProductInform.fxml'.";
        assert OneNumber != null : "fx:id=\"OneNumber\" was not injected: check your FXML file 'ProductInform.fxml'.";
    }

    public void setNode(Node node)
    {
        this.node = node;
    }

    public void handleClicks(MouseEvent mouseEvent) {
//        try {
//            this.salesController.getSalesSection().deleteOrderItem(this.OneID.getText(), this.OneName.getText());
//        }
//        catch (SQLException se)
//        {
//            this.salesController.getSalesSection().getStaff().showAlert("1", "2", "3", "4");
//        }
        this.salesController.removeGoodsNode(this.node,OneName.getText(),OneAllPrice.getText());
    }
}
