package com.JKX.Controller;

import com.JKX.Controller.ItemController.ItemFinanceController;
import com.JKX.Controller.ItemController.ItemRefundController;
import com.JKX.Controller.ItemController.ItemUnpaidController;
import com.JKX.Model.FinanceSection;
import com.JKX.Model.Staff;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FinanceController {

    private FinanceSection financeSection;

    DateFormat df = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
    Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            String timet = df.format(new Date());
            year.setText(timet.substring(0,4));
            month.setText(timet.substring(5,7));
            day.setText(timet.substring(8,10));
            time.setText(timet.substring(11,19));
        }
    }));

    @FXML
    private AnchorPane all;

    @FXML
    private Label idname;

    @FXML
    private Button menuHomepage;

    @FXML
    private Button menuReceivables;

    @FXML
    private Button menuRefund;

    @FXML
    private Button menuPay;

    @FXML
    private Button menuIncome;

    @FXML
    private Button menuQuit;

    @FXML
    private Pane pageReceivables;

    @FXML
    private VBox ItemsReceive;

    @FXML
    private TextField receive;

    @FXML
    private Pane pageRefund;

    @FXML
    private TextField refund;

    @FXML
    private VBox ItemsRefund;

    @FXML
    private Pane pagePay;

    @FXML
    private TextField in_text1;

    @FXML
    private TextField in_text2;

    @FXML
    private Pane pageIncome;

    @FXML
    private TextField out_text1;

    @FXML
    private TextField out_text2;

    @FXML
    private Button out;

    @FXML
    private Pane pageHomepage;

    @FXML
    private TextField qurey;

    @FXML
    private Label year;

    @FXML
    private Label month;

    @FXML
    private Label day;

    @FXML
    private Label time;

    @FXML
    private VBox ItemsHome;

    @FXML
    void click_in(MouseEvent event) throws SQLException {
        try{
            Integer.parseInt(this.in_text1.getText());
        }catch (NumberFormatException e) {
            Alert _alert = new Alert(Alert.AlertType.WARNING);
            _alert.setTitle("支入系统");
            _alert.setHeaderText("输入格式错误");
            _alert.setContentText("请输入正确的金额");
            _alert.show();
            return;
        }

        Alert _alert = new Alert(Alert.AlertType.CONFIRMATION);
        _alert.setTitle("支入系统");
        _alert.setHeaderText("确认支入信息");
        _alert.setContentText("备注：" + this.in_text2.getText() + "         支入" + this.in_text1.getText() + "元");
        _alert.showAndWait();
        if(_alert.getResult()== ButtonType.OK) {
            financeSection.in(this.in_text2.getText(),this.in_text1.getText());
        }
        this.click_query(event);
        this.in_text1.setText("");
        this.in_text2.setText("");
    }

    @FXML
    void click_out(MouseEvent event) throws SQLException {

        try{
            Integer.parseInt(this.out_text1.getText());
        }catch (NumberFormatException e) {
            Alert _alert = new Alert(Alert.AlertType.WARNING);
            _alert.setTitle("支出系统");
            _alert.setHeaderText("输入格式错误");
            _alert.setContentText("请输入正确的金额");
            _alert.show();
            return;
        }

        Alert _alert = new Alert(Alert.AlertType.CONFIRMATION);
        _alert.setTitle("支出系统");
        _alert.setHeaderText("确认支出信息");
        _alert.setContentText("备注：" + this.out_text2.getText() + "         支出" + this.out_text1.getText() + "元");
        _alert.showAndWait();
        if(_alert.getResult()== ButtonType.OK) {
            financeSection.out(this.out_text2.getText(),this.out_text1.getText());
        }
        this.click_query(event);
        this.out_text1.setText("");
        this.out_text2.setText("");
    }

    @FXML
    public void click_query(MouseEvent event) {
        try {
            this.ItemsHome.getChildren().clear();
            String[][] ans = financeSection.Query(this.qurey.getText());
            for (int i = 1; i < ans.length; i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/ItemFinance.fxml"));
                Node node = null;
                try {
                    node = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ItemFinanceController itemFinanceController = loader.<ItemFinanceController>getController();
                itemFinanceController.setInform(ans[i][0], ans[i][1], ans[i][2], ans[i][3].substring(0,19), ans[i][4]);
                this.ItemsHome.getChildren().add(node);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void click_receive(MouseEvent event) {
        try {
            this.ItemsReceive.getChildren().clear();
            String[][] ans = financeSection.unpaidQuery(this.receive.getText());
            for (int i = 1; i < ans.length; i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/ItemUnpaid.fxml"));
                Node node = null;
                try {
                    node = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ItemUnpaidController itemUnpaidController = loader.<ItemUnpaidController>getController();
                itemUnpaidController.setInform(ans[i][0], ans[i][1].substring(0,19), ans[i][2], ans[i][3],financeSection,this);
                this.ItemsReceive.getChildren().add(node);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void click_refund(MouseEvent event) {
        try {
            this.ItemsRefund.getChildren().clear();
            String[][] ans = financeSection.refundQuery(this.refund.getText());
            for (int i = 1; i < ans.length; i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/ItemRefund.fxml"));
                Node node = null;
                try {
                    node = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ItemRefundController itemRefundController = loader.<ItemRefundController>getController();
                itemRefundController.setInform(ans[i][0], ans[i][3].substring(0,19), ans[i][1], ans[i][2],financeSection,this);
                this.ItemsRefund.getChildren().add(node);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleClicks(MouseEvent event) throws IOException, SQLException {
        if (event.getSource() == menuHomepage) {
            pageHomepage.setStyle("-fx-background-color : #02030A");
            pageHomepage.toFront();
            return;
        }
        if (event.getSource() == menuReceivables) {
            if(financeSection.getRight(1).equals("1"))
            {
                pageReceivables.setStyle("-fx-background-color : #02030A");
                pageReceivables.toFront();
                return;
            }
        }
        if (event.getSource() == menuRefund) {
            if(financeSection.getRight(2).equals("1"))
            {
                pageRefund.setStyle("-fx-background-color : #02030A");
                pageRefund.toFront();
                return;
            }
        }
        if(event.getSource() == menuPay)
        {
            if(financeSection.getRight(4).equals("1"))
            {
                pagePay.setStyle("-fx-background-color : #02030A");
                pagePay.toFront();
                return;
            }
        }
        if(event.getSource() == menuIncome)
        {
            if(financeSection.getRight(3).equals("1"))
            {
                pageIncome.setStyle("-fx-background-color : #02030A");
                pageIncome.toFront();
                return;
            }
        }
        if(event.getSource() == menuQuit)
        {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/Lead.fxml"));

            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.setScene(new Scene((Parent) loader.load()));

            LeadContorller controller = loader.<LeadContorller>getController();

            controller.initData(financeSection.getStaff());

            stage.show();

            Stage index = (Stage)all.getScene().getWindow();
            index.close();
            return;
        }
        Alert _alert = new Alert(Alert.AlertType.WARNING);
        _alert.setTitle("警告");
        _alert.setHeaderText("权限受限");
        _alert.setContentText("您没有进行这一项功能的权限，请与相关负责人联系授权！");
        _alert.show();
        return;
    }

    public void initData(Staff staff) throws SQLException {
        idname.setText(staff.Name + "\r\n" + staff.Uid);
        financeSection = new FinanceSection(staff);
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
        try {
            this.ItemsReceive.getChildren().clear();
            String[][] ans = financeSection.unpaidSearch();
            for (int i = 1; i < ans.length; i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/ItemUnpaid.fxml"));
                Node node = null;
                try {
                    node = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ItemUnpaidController itemUnpaidController = loader.<ItemUnpaidController>getController();
                itemUnpaidController.setInform(ans[i][0], ans[i][1].substring(0,19), ans[i][2], ans[i][3],financeSection,this);
                this.ItemsReceive.getChildren().add(node);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            this.ItemsRefund.getChildren().clear();
            String[][] ans = financeSection.refundSearch();
            for (int i = 1; i < ans.length; i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/ItemRefund.fxml"));
                Node node = null;
                try {
                    node = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ItemRefundController itemRefundController = loader.<ItemRefundController>getController();
                itemRefundController.setInform(ans[i][0], ans[i][3].substring(0,19), ans[i][1], ans[i][2],financeSection,this);
                this.ItemsRefund.getChildren().add(node);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            this.ItemsHome.getChildren().clear();
            String[][] ans = financeSection.Search();
            for (int i = 1; i < ans.length; i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/ItemFinance.fxml"));
                Node node = null;
                try {
                    node = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ItemFinanceController itemFinanceController = loader.<ItemFinanceController>getController();
                itemFinanceController.setInform(ans[i][0], ans[i][1], ans[i][2], ans[i][3].substring(0,19), ans[i][4]);
                this.ItemsHome.getChildren().add(node);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void initialize() {
    }
}
