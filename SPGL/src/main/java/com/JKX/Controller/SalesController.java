package com.JKX.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.JKX.Controller.ItemController.ProductInformController;
import com.JKX.Model.Staff;
import com.JKX.Model.SalesSection;
import com.JKX.Model.Table.Custom;
import com.JKX.Model.Table.Order;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sun.awt.windows.WPrinterJob;

import javax.swing.*;

public class SalesController implements Initializable {

    private SalesSection salesSection;

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

    @FXML
    private Pane pageCreate;//生成订单界面

    @FXML
    private Pane pageCancel;//取消订单界面

    @FXML
    private Pane pageReturnGoods;//客户退货界面

    @FXML
    private JFXButton RegisterButton;

    @FXML
    private JFXTextField RegisterID;

    @FXML
    private JFXTextField RegisterName;

    @FXML
    private JFXTextField RegisterTele;

    @FXML
    private ComboBox<String> RegisterType;

    @FXML
    private JFXButton CreateSearch1;

    @FXML
    private JFXButton CreateSearch2;

    @FXML
    private JFXButton CreateAddButton;

    @FXML
    private JFXButton CreateButton;

    @FXML
    private JFXTextField CreateTextSearch1;

    @FXML
    private JFXTextField CreateTextSearch2;

    @FXML
    private JFXTextField CreateID1;

    @FXML
    private JFXTextField CreateID2;

    @FXML
    private JFXTextField CreateName;

    @FXML
    private JFXTextField CreateOnePrice;

    @FXML
    private JFXTextField CreateOneID;

    @FXML
    private JFXTextField CreateNumber;

    @FXML
    private JFXTextField CreatePay;

    @FXML
    private JFXTextField CreateNextPay;

    @FXML
    private ComboBox<String> CreatePayType;

    @FXML
    private JFXButton CancelSearch;

    @FXML
    private JFXButton SearchNamePrice;

    @FXML
    private JFXButton CancelButton;

    @FXML
    private JFXTextField CancelTextSearch;

    @FXML
    private JFXTextField CancelID;

    @FXML
    private JFXTextField CancelMoney;

    @FXML
    private ComboBox<String> CancelStage;

    @FXML
    private JFXButton ReturnSearch;

    @FXML
    private JFXButton ReturnButton;

    @FXML
    private JFXTextField ReturnTextSearch;

    @FXML
    private JFXTextField ReturnID;

    @FXML
    private JFXTextField ReturnMoney;

    @FXML
    private TextArea ReturnReason;

    @FXML
    private VBox GoodsBox;

    public void initData(Staff staff)
    {
        salesSection = new SalesSection(staff);
        RegisterType.getItems().addAll("零售商","批发商","代理商");
        CreatePayType.getItems().addAll("预付款","全款");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


    public void ClearRegister()
    {
        try
        {
            RegisterID.setText(salesSection.CreateCustomID());
        }
        catch (SQLException se)
        {
            this.salesSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "查询失败", "系统错误");
            return;
        }
        this.RegisterName.setText("");
        this.RegisterTele.setText("");
        this.RegisterType.setValue("");
    }

    public void ClearCreate()
    {
        try
        {
            CreateID1.setText(salesSection.CreateOrderID());
        }
        catch (SQLException se)
        {
            this.salesSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "查询失败", "系统错误");
            return;
        }
        CreateID2.setText("");
        CreateName.setText("");
        CreateOnePrice.setText("");
        CreateOneID.setText("");
        CreateNumber.setText("");
        CreatePayType.setValue("");
        CreatePay.setText("");
        CreateNextPay.setText("");

    }

    public void ClearCancel()
    {
        CancelID.setText("");
        CancelMoney.setText("");
    }

    public void ClearReturn()
    {
        this.ReturnID.setText("");
        this.ReturnMoney.setText("");
        this.ReturnReason.setText("");
    }

    @FXML
    void handleClicks(MouseEvent event) throws IOException {
        if (event.getSource() == menuRegister) {
            pageRegister.setStyle("-fx-background-color : #02030A");
            pageRegister.toFront();
            ClearRegister();
        }
        if (event.getSource() == menuCreate) {
            pageCreate.setStyle("-fx-background-color : #02030A");
            pageCreate.toFront();
            ClearCreate();
        }
        if (event.getSource() == menuCancel) {
            pageCancel.setStyle("-fx-background-color : #02030A");
            pageCancel.toFront();
            ClearCancel();
        }
        if(event.getSource() == menuReturnGoods)
        {
            pageReturnGoods.setStyle("-fx-background-color : #02030A");
            pageReturnGoods.toFront();
            ClearReturn();
        }
        if(event.getSource() == menuQuit)
        {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/Lead.fxml"));

            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.setScene(new Scene((Parent) loader.load()));

            LeadContorller controller = loader.<LeadContorller>getController();

            controller.initData(salesSection.getStaff());

            stage.show();

            Stage index = (Stage)SalePanel.getScene().getWindow();
            index.close();
        }
        if(event.getSource() == RegisterButton)
        {
            if(RegisterName.getText().equals(""))
            {
                this.salesSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "注册失败", "客户姓名不能为空");
                return;
            }
            if(RegisterTele.getText().equals(""))
            {
                this.salesSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "注册失败", "联系电话不能为空");
                return;
            }
            try
            {
                Custom[] customs=salesSection.searchCusOnPhone(RegisterTele.getText());
                if(customs.length>0)
                {
                    this.salesSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "注册失败", "此联系电话已存在，请检查联系电话");
                    return;
                }
            }
            catch (SQLException se)
            {
                this.salesSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "查询失败", "系统错误");
                return;
            }
            if(RegisterType.getValue().equals(""))
            {
                this.salesSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "注册失败", "未选择客户类型");
                return;
            }
            try
            {
                System.out.println(salesSection.CreateCustomID());
            }
            catch (SQLException se)
            {
                this.salesSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "查询失败", "系统错误");
                return;
            }
            try
            {
                this.salesSection.insertCustomer(RegisterID.getText(),RegisterType.getSelectionModel().getSelectedItem().toString(), RegisterName.getText(), RegisterTele.getText());
                this.salesSection.getStaff().showAlert(Alert.AlertType.CONFIRMATION, "成功", "注册成功", "客户编号： " + RegisterID.getText());
            }
            catch (SQLException se)
            {
                this.salesSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "查询失败", "系统错误");
            }
            ClearRegister();
        }
        if(event.getSource()==ReturnButton)
        {
            if(ReturnID.getText().equals(""))
            {
                this.salesSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "退货失败", "未填写退货订单编号");
                return;
            }
            try
            {
                if(salesSection.CanReturn(ReturnID.getText())==false)
                {
                    this.salesSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "退货失败", "此订单不可退货或已退货");
                    return;
                }
            }
            catch (SQLException se)
            {
                this.salesSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "退货失败", "系统错误");
            }
            if(ReturnMoney.getText().equals(""))
            {
                this.salesSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "退货失败", "未填写退款金额");
                return;
            }
            if(ReturnReason.getText().equals(""))
            {
                this.salesSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "退货失败", "未填写退款退款原因");
                return;
            }
            try
            {
                this.salesSection.returnGoods(ReturnID.getText(),ReturnMoney.getText(),ReturnReason.getText());
                this.salesSection.getStaff().showAlert(Alert.AlertType.INFORMATION, "成功退货", "已退货", "此订单已退");
                ClearReturn();
            }
            catch (SQLException se)
            {
                this.salesSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "退货失败", "系统错误");
            }
        }
        if(event.getSource()==CancelButton)
        {
            if(CancelID.getText().equals(""))
            {
                this.salesSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "取消订单失败", "订单编号不能为空");
                return;
            }
            try
            {
                if(this.salesSection.CanCancel(CancelID.getText()).equals("error"))
                {
                    this.salesSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "取消订单失败", "此订单已完成，不可取消");
                    return;
                }

                System.out.println(this.salesSection.CanCancel(CancelID.getText()));
            }
            catch (SQLException se)
            {
                this.salesSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "取消订单失败", "系统错误1");
            }
            if(CancelMoney.getText().equals(""))
            {
                this.salesSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "取消订单失败", "请填写商议退款金额");
                return;
            }
            try
            {
                int money=salesSection.cancelGoods(CancelID.getText(),CancelMoney.getText(),this.salesSection.CanCancel(CancelID.getText()));
                this.salesSection.getStaff().showAlert(Alert.AlertType.INFORMATION, "成功取消订单", "已取消订单", "此订单已取消，按规定退款"+String.valueOf(money)+"元");
            }
            catch (SQLException se)
            {
                this.salesSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "取消订单失败", "系统错误2");
            }
        }
        if(event.getSource()==CancelSearch)
        {
            try {
                Order[] orders =salesSection.searchOrderOnId(ReturnTextSearch.getText());
                if(orders.length == 0) {
                    this.salesSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "查询失败", "此客户编号不存在，请输入正确的客户编号");
                }
                else {
                    String str_id="";
                    for(int i = 0; i < orders.length; i++)
                    {
                        if(!orders[i].getOrder_zt().equals("已完成"))
                        {
                            str_id=str_id+orders[i].getOrder_id()+"\n";
                        }
                    }
                    this.salesSection.getStaff().showAlert(Alert.AlertType.INFORMATION, "完成", "查询完成", "此客户可以退货的订单编号：\n"+str_id);
                }
            }
            catch (SQLException se)
            {
                this.salesSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "查询失败", "系统错误0");
            }
        }
        if(event.getSource() == ReturnSearch)
        {
            try {
                Order[] orders =salesSection.searchOrderOnId(ReturnTextSearch.getText());
                if(orders.length == 0) {
                    this.salesSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "查询失败", "此客户编号不存在，请输入正确的客户编号");
                }
                else {
                    String str_id="";
                    for(int i = 0; i < orders.length; i++)
                    {
                        if(orders[i].getOrder_zt().equals("已完成"))
                        {
                            str_id=str_id+orders[i].getOrder_id()+"\n";
                        }
                    }
                    this.salesSection.getStaff().showAlert(Alert.AlertType.INFORMATION, "完成", "查询完成", "此客户可以退货的订单编号：\n"+str_id);
                }
            }
            catch (SQLException se)
            {
               this.salesSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "查询失败", "系统错误0");
            }
        }
        if(event.getSource()==CreateSearch1)
        {
            if(CreateTextSearch1.getText().equals(""))
            {
                this.salesSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "查询失败", "请输入联系电话查询客户编号");
                return;
            }
            try
            {
                Custom[] c=salesSection.searchCusOnPhone(CreateTextSearch1.getText());
                if(c.length==0)
                {
                    this.salesSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "查询失败", "查无此人");
                    return;
                }
                CreateID2.setText(c[0].getCustom_id());
                this.salesSection.getStaff().showAlert(Alert.AlertType.ERROR, "完成", "查询完成", "客户编号为"+c[0].getCustom_id()+",已粘贴到客户编号栏");
            }
            catch (SQLException se)
            {
                this.salesSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "查询失败", "系统错误");
            }
        }
        if(event.getSource()==CreateSearch2)
        {
            if(CreateTextSearch2.getText().equals(""))
            {
                this.salesSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "查询失败", "请输入产品名称查询产品编号");
                return;
            }
            if(CreateID2.getText().equals(""))
            {
                this.salesSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "请完善查询信息", "请输入客户编号以确定产品单价");
                return;
            }
            try
            {
                Custom[] c=salesSection.searchCusOnId(CreateID2.getText());
                if(c.length==0)
                {
                    this.salesSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "查询失败", "此客户不存在");
                    return;
                }
                String price_type="product_p"+c[0].getCustomType().getType_id();
                String sql="select product_name,"+price_type+",product_id from product where product_name = '"+CreateTextSearch2.getText()+"'";
                String [][]s=salesSection.getStaff().Search(sql);
                if(s.length==1)
                {
                    this.salesSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "查询失败", "此商品不存在");
                    return;
                }
                CreateName.setText(s[1][0]);
                CreateOnePrice.setText(s[1][1]);
                CreateOneID.setText(s[1][2]);
                this.salesSection.getStaff().showAlert(Alert.AlertType.INFORMATION, "完成", "查询完成", "查询成功，已将商品信息粘贴到面板");
            }
            catch (SQLException se)
            {
                this.salesSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "查询失败", "系统错误?");
            }
        }
        if(event.getSource()==SearchNamePrice)
        {
            if(CreateOneID.getText().equals(""))
            {
                this.salesSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "查询失败", "请输入产品编号");
                return;
            }
            try
            {
                Custom[] c=salesSection.searchCusOnId(CreateID2.getText());
                if(c.length==0)
                {
                    this.salesSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "查询失败", "此客户不存在，请检查是否输入客户编号");
                    return;
                }
                String price_type="product_p"+c[0].getCustomType().getType_id();
                String sql="select product_name,"+price_type+",product_id from product where product_id = '"+CreateOneID.getText()+"'";
                String [][]s=salesSection.getStaff().Search(sql);
                if(s.length==1)
                {
                    this.salesSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "查询失败", "此商品不存在");
                    return;
                }
                CreateName.setText(s[1][0]);
                CreateOnePrice.setText(s[1][1]);
                this.salesSection.getStaff().showAlert(Alert.AlertType.INFORMATION, "完成", "查询完成", "查询成功，已将商品信息粘贴到面板");
            }
            catch (SQLException se)
            {
                this.salesSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "查询失败", "系统错误!");
            }
        }
        if(event.getSource()==CreateAddButton)
        {
            if(CreateOneID.getText().equals(""))
            {
                this.salesSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "无法添加到购物车", "请输入产品编号");
                return;
            }
            if(CreateNumber.getText().equals(""))
            {
                this.salesSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "无法添加到购物车", "请输入产品数量");
                return;
            }
            if(CreateName.getText().equals(""))
            {
                this.salesSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "无法添加到购物车", "请使用右上角查询按钮或查询名称单价按钮补全产品名称和产品单价");
                return;
            }

            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/ProductInform.fxml"));
            Node node = loader.load();
            ProductInformController productInformController=loader.<ProductInformController>getController();
            String allPrice=String.valueOf(Integer.valueOf(CreateOnePrice.getText())*Integer.valueOf(CreateNumber.getText()));

            productInformController.setInform(CreateOneID.getText(),CreateOnePrice.getText(),allPrice,CreateName.getText(),CreateNumber.getText());

            productInformController.setContorller(this);
            GoodsBox.getChildren().add(node);
        }
    }

    public SalesSection getSalesSection() {
        return salesSection;
    }

    @FXML
    void initialize() {
        assert menuRegister != null : "fx:id=\"menuRegister\" was not injected: check your FXML file 'DepSales.fxml'.";
        assert menuCreate != null : "fx:id=\"menuCreate\" was not injected: check your FXML file 'DepSales.fxml'.";
        assert menuCancel != null : "fx:id=\"menuCancel\" was not injected: check your FXML file 'DepSales.fxml'.";
        assert menuReturnGoods != null : "fx:id=\"menuReturnGoods\" was not injected: check your FXML file 'DepSales.fxml'.";
        assert menuQuit != null : "fx:id=\"menuQuit\" was not injected: check your FXML file 'DepSales.fxml'.";
        assert pageRegister != null : "fx:id=\"pageRegister\" was not injected: check your FXML file 'DepSales.fxml'.";
        assert pageCreate != null : "fx:id=\"pageCreate\" was not injected: check your FXML file 'DepSales.fxml'.";
        assert pageCancel != null : "fx:id=\"pageCancel\" was not injected: check your FXML file 'DepSales.fxml'.";
        assert pageReturnGoods != null : "fx:id=\"pageReturnGoods\" was not injected: check your FXML file 'DepSales.fxml'.";
        assert RegisterButton != null : "fx:id=\"RegisterButton\" was not injected: check your FXML file 'DepSales.fxml'.";
        assert RegisterID != null : "fx:id=\"RegisterID\" was not injected: check your FXML file 'DepSales.fxml'.";
        assert RegisterName != null : "fx:id=\"RegisterName\" was not injected: check your FXML file 'DepSales.fxml'.";
        assert RegisterTele != null : "fx:id=\"RegisterTele\" was not injected: check your FXML file 'DepSales.fxml'.";
        assert RegisterType != null : "fx:id=\"RegisterType\" was not injected: check your FXML file 'DepSales.fxml'.";
        assert CreateSearch1 != null : "fx:id=\"CreateSearch1\" was not injected: check your FXML file 'DepSales.fxml'.";
        assert CreateSearch2 != null : "fx:id=\"CreateSearch2\" was not injected: check your FXML file 'DepSales.fxml'.";
        assert CreateAddButton != null : "fx:id=\"CreateAddButton\" was not injected: check your FXML file 'DepSales.fxml'.";
        assert CreateButton != null : "fx:id=\"CreateButton\" was not injected: check your FXML file 'DepSales.fxml'.";
        assert CreateTextSearch1 != null : "fx:id=\"CreateTextSearch1\" was not injected: check your FXML file 'DepSales.fxml'.";
        assert CreateTextSearch2 != null : "fx:id=\"CreateTextSearch2\" was not injected: check your FXML file 'DepSales.fxml'.";
        assert CreateID1 != null : "fx:id=\"CreateID1\" was not injected: check your FXML file 'DepSales.fxml'.";
        assert CreateID2 != null : "fx:id=\"CreateID2\" was not injected: check your FXML file 'DepSales.fxml'.";
        assert CreateName != null : "fx:id=\"CreateName\" was not injected: check your FXML file 'DepSales.fxml'.";
        assert CreateOnePrice != null : "fx:id=\"CreateOnePrice\" was not injected: check your FXML file 'DepSales.fxml'.";
        assert CreateOneID != null : "fx:id=\"CreateOneID\" was not injected: check your FXML file 'DepSales.fxml'.";
        assert CreateNumber != null : "fx:id=\"CreateNumber\" was not injected: check your FXML file 'DepSales.fxml'.";
        assert CreatePay != null : "fx:id=\"CreatePay\" was not injected: check your FXML file 'DepSales.fxml'.";
        assert CreateNextPay != null : "fx:id=\"CreateNextPay\" was not injected: check your FXML file 'DepSales.fxml'.";
        assert CreatePayType != null : "fx:id=\"CreatePayType\" was not injected: check your FXML file 'DepSales.fxml'.";
        assert CancelSearch != null : "fx:id=\"CancelSearch\" was not injected: check your FXML file 'DepSales.fxml'.";
        assert CancelButton != null : "fx:id=\"CancelButton\" was not injected: check your FXML file 'DepSales.fxml'.";
        assert CancelTextSearch != null : "fx:id=\"CancelTextSearch\" was not injected: check your FXML file 'DepSales.fxml'.";
        assert CancelID != null : "fx:id=\"CancelID\" was not injected: check your FXML file 'DepSales.fxml'.";
        assert CancelMoney != null : "fx:id=\"CancelMoney\" was not injected: check your FXML file 'DepSales.fxml'.";
        //assert CancelStage != null : "fx:id=\"CancelStage\" was not injected: check your FXML file 'DepSales.fxml'.";
        assert ReturnSearch != null : "fx:id=\"ReturnSearch\" was not injected: check your FXML file 'DepSales.fxml'.";
        assert ReturnButton != null : "fx:id=\"ReturnButton\" was not injected: check your FXML file 'DepSales.fxml'.";
        assert ReturnTextSearch != null : "fx:id=\"ReturnTextSearch\" was not injected: check your FXML file 'DepSales.fxml'.";
        assert ReturnID != null : "fx:id=\"ReturnID\" was not injected: check your FXML file 'DepSales.fxml'.";
        assert ReturnMoney != null : "fx:id=\"ReturnMoney\" was not injected: check your FXML file 'DepSales.fxml'.";
        assert ReturnReason != null : "fx:id=\"ReturnReason\" was not injected: check your FXML file 'DepSales.fxml'.";
        assert GoodsBox != null : "fx:id=\"GoodsBox\" was not injected: check your FXML file 'DepSales.fxml'.";
        assert SearchNamePrice != null : "fx:id=\"SearchNamePrice\" was not injected: check your FXML file 'DepSales.fxml'.";
    }
}
