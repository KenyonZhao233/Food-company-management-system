package com.JKX.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.JKX.Model.Staff;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SalesController implements Initializable {

    private Staff staff;

    @FXML
    private AnchorPane SalePanel;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button menuRegister;//客户注册按钮

    @FXML
    private Button menuCreate;//生成订单按钮

    @FXML
    private Button menuCancel;//取消订单按钮

    @FXML
    private Button menuReturnGoods;//客户退货按钮

    @FXML
    private Button menuQuit;//退出按钮

    @FXML
    private Pane pageRegister;//客户注册界面

//    @FXML
//    private VBox pnItems3;

    @FXML
    private Pane pageCreate;//生成订单界面

//    @FXML
//    private VBox pnItems11;

    @FXML
    private Pane pageCancel;//取消订单界面

//    @FXML
//    private VBox pnItems1;

    @FXML
    private Pane pageReturnGoods;//客户退货界面

//    @FXML
//    private VBox pnItems32;


    public void initData(Staff staff)
    {
        this.staff = staff;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


    @FXML
    void handleClicks(MouseEvent event) throws IOException {
        if (event.getSource() == menuRegister) {
            pageRegister.setStyle("-fx-background-color : #02030A");
            pageRegister.toFront();
        }
        if (event.getSource() == menuCreate) {
            pageCreate.setStyle("-fx-background-color : #02030A");
            pageCreate.toFront();
        }
        if (event.getSource() == menuCancel) {
            pageCancel.setStyle("-fx-background-color : #02030A");
            pageCancel.toFront();
        }
        if(event.getSource() == menuReturnGoods)
        {
            pageReturnGoods.setStyle("-fx-background-color : #02030A");
            pageReturnGoods.toFront();
        }
        if(event.getSource() == menuQuit)
        {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/Lead.fxml"));

            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.setScene(new Scene((Parent) loader.load()));

            LeadContorller controller = loader.<LeadContorller>getController();

            controller.initData(staff);

            stage.show();

            Stage index = (Stage)SalePanel.getScene().getWindow();
            index.close();
        }
    }

    @FXML
    void initialize() {
        assert menuRegister != null : "fx:id=\"menuRegister\" was not injected: check your FXML file 'DepSales.fxml'.";
        assert menuCreate != null : "fx:id=\"menuCreate\" was not injected: check your FXML file 'DepSales.fxml'.";
        assert menuCancel != null : "fx:id=\"menuCancel\" was not injected: check your FXML file 'DepSales.fxml'.";
        assert menuReturnGoods != null : "fx:id=\"menuReturnGoods\" was not injected: check your FXML file 'DepSales.fxml'.";
        assert menuQuit != null : "fx:id=\"menuQuit\" was not injected: check your FXML file 'DepSales.fxml'.";
        assert pageRegister != null : "fx:id=\"pageRegister\" was not injected: check your FXML file 'DepSales.fxml'.";
        //assert pnItems3 != null : "fx:id=\"pnItems3\" was not injected: check your FXML file 'DepSales.fxml'.";
        assert pageCreate != null : "fx:id=\"pageCreate\" was not injected: check your FXML file 'DepSales.fxml'.";
        //assert pnItems11 != null : "fx:id=\"pnItems11\" was not injected: check your FXML file 'DepSales.fxml'.";
        assert pageCancel != null : "fx:id=\"pageCancel\" was not injected: check your FXML file 'DepSales.fxml'.";
        //assert pnItems1 != null : "fx:id=\"pnItems1\" was not injected: check your FXML file 'DepSales.fxml'.";
        assert pageReturnGoods != null : "fx:id=\"pageReturnGoods\" was not injected: check your FXML file 'DepSales.fxml'.";
        //assert pnItems32 != null : "fx:id=\"pnItems32\" was not injected: check your FXML file 'DepSales.fxml'.";

    }
}
