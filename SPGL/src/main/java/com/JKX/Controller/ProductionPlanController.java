package com.JKX.Controller;

import com.JKX.Controller.ItemController.*;
import com.JKX.Model.PlanSection;
import com.JKX.Model.Staff;
import com.JKX.Model.Table.Plan;
import com.JKX.Model.Table.Production;
import com.JKX.Model.Table.Projectrec;
import com.JKX.Model.Table.Raw;
import com.calendarfx.view.print.TimeRangeView;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

public class ProductionPlanController {

    private PlanSection planSection;

    @FXML
    private AnchorPane UserManagePane;

    @FXML
    private Label nameLable;

    //跳转界面
    @FXML
    private javafx.scene.control.Button SearchCpkc, SearchYlkc, SearchScjh ,ChangeScjh, ChangeCp, ChangeRaw, SearchXl, btnSignout,SearchRec;

    //各种界面
    @FXML
    private AnchorPane Recsearch, RawkcPane, PlanSearPane, PlanGlpane, ChangeCppane, ChangeRawPane, searchXlPane, CpkcPane;

    //成品库存查询
    @FXML
    private TextField messageCp;
    @FXML
    private JFXButton searchCp;
    @FXML
    private ComboBox<String> comboxCp;
    @FXML
    private TableView<Production> viewCpkc;
    @FXML
    private TableColumn<Production, String> CcpId, CcpName;
    @FXML
    private TableColumn<Production, Float> CcpP1, CcpP2, CcpP3;
    @FXML
    private TableColumn<Production, Integer> CcpKc;


    //原料库存查询
    @FXML
    private TextField messageRaw;
    @FXML
    private JFXButton searchRaw;
    @FXML
    private ComboBox<String> comboxRaw;
    @FXML
    private TableView<Raw> viewRawkc;
    @FXML
    private TableColumn<Raw, String> CrawId, CrawName;
    @FXML
    private TableColumn<Raw, Integer> CrawBzq;
    @FXML
    private TableColumn<Raw, Float> CrawPri, CrawKc;

    //计划查询
    @FXML
    private TextField messagePlan;
    @FXML
    private JFXButton searchPlanId, searchPlanDate;
    @FXML
    private TimeRangeView searchPlanPicker;
    @FXML
    private VBox vboxSearchPlan;
    @FXML
    private ComboBox<String> comboxZt;

    //计划管理
    //新建计划页面
    @FXML
    private JFXTextField PlanId, Cpname, Cpnum, workshops;
    @FXML
    private JFXButton makePlan;
    @FXML
    private DatePicker planDdl;

    //修改计划页面
    @FXML
    private TextField messagePlan1;
    @FXML
    private JFXButton searchPlanId1, searchPlanDate1;
    @FXML
    private TimeRangeView searchPlanPicker1;
    @FXML
    private VBox vboxPlan;

    //审核计划页面
    @FXML
    private TextField messagePlan2;
    @FXML
    private JFXButton searchPlanId2;
    @FXML
    private VBox vboxPlanSh;

    //成品管理页面
    //成品查询界面
    @FXML
    private TextField messageCp1;
    @FXML
    private JFXButton searchCp1;
    @FXML
    private ComboBox<String> comboxSearchCp1;
    @FXML
    private VBox cpList;

    //成品修改界面
    @FXML
    private JFXTextField cpId, cpName, p1, p2, p3, bzq, cpRawName, cpRawPrice, cpRawNum;
    @FXML
    private JFXButton changeCp, addRaw, searchCp3;
    @FXML
    private VBox vboxChangeCp, vboxChangeCPRaw;
    @FXML
    private TextField messageCp3;
    @FXML
    private ComboBox<String> comboxSearchCp3;

    //添加成品界面
    @FXML
    private JFXTextField cpId1, cpName1, p11, p21, p31, bzq1;
    @FXML
    private JFXButton addCp;
    @FXML
    private JFXTextField cpHead;

    //原料管理界面
    //查询原料界面
    @FXML
    private TextField messageRaw1;
    @FXML
    private JFXButton searchRaw1;
    @FXML
    private ComboBox<String> comboxSearchRaw;
    @FXML
    private VBox rawList;
    //修改原料界面
    @FXML
    private TextField messageRaw11;
    @FXML
    private JFXButton searchRaw2;
    @FXML
    private ComboBox<String> comboxSearchRaw1;
    @FXML
    private VBox vboxYl;
    //添加原料界面
    @FXML
    private JFXTextField rawId1, rawName1, rawPri1, rawBzq1;
    @FXML
    private JFXButton addRaw1;
    @FXML
    private JFXTextField rawHead;

   //销量分析页面
    @FXML
    private TimeRangeView timeXl;
    @FXML
    private JFXButton searchXlonDate;
    @FXML
    private Pane xlPane;

    //记录查询界面
    @FXML
    private TextField recId;
    @FXML
    private JFXButton btnRec;
    @FXML
    private TableView<Projectrec> tableRec;

    private int[] qx;


    final NumberAxis yAxis = new NumberAxis(0, 10000, 100);
    final CategoryAxis xAxis = new CategoryAxis();
    final BarChart<String,Number> ChartXl =
            new BarChart<String,Number>(xAxis,yAxis);

    public void initData(Staff staff)
    {
        this.cpId1.setEditable(false);
        this.rawId1.setEditable(false);
        this.planSection = new PlanSection(staff);
        this.nameLable.setText(staff.Name);
        List<String> searchMethod = new ArrayList<String>();
        List<String> searchMethod2 = new ArrayList<String>();
        searchMethod.add("按编号查询");
        searchMethod.add("按名称查询");
        searchMethod2.add("待执行");
        searchMethod2.add("执行中");
        searchMethod2.add("待审核");
        searchMethod2.add("已完成");
        this.comboxCp.getItems().addAll(searchMethod);
        this.comboxRaw.getItems().addAll(searchMethod);
        this.comboxSearchCp1.getItems().addAll(searchMethod);
        this.comboxSearchCp3.getItems().addAll(searchMethod);
        this.comboxSearchRaw.getItems().addAll(searchMethod);
        this.comboxSearchRaw1.getItems().addAll(searchMethod);
        this.comboxZt.getItems().addAll(searchMethod2);
        this.comboxCp.setValue("按编号查询");
        this.comboxRaw.setValue("按编号查询");
        this.comboxSearchRaw.setValue("按编号查询");
        this.comboxSearchCp1.setValue("按编号查询");
        this.comboxSearchCp3.setValue("按编号查询");
        this.comboxSearchRaw1.setValue("按编号查询");
        this.comboxZt.setValue("待执行");
        this.CcpId.setCellValueFactory(new PropertyValueFactory<Production, String>("production_id"));
        this.CcpName.setCellValueFactory(new PropertyValueFactory<Production, String>("production_name"));
        this.CcpP1.setCellValueFactory(new PropertyValueFactory<Production, Float>("production_p1"));
        this.CcpP2.setCellValueFactory(new PropertyValueFactory<Production, Float>("production_p2"));
        this.CcpP3.setCellValueFactory(new PropertyValueFactory<Production, Float>("production_p3"));
        this.CcpKc.setCellValueFactory(new PropertyValueFactory<Production, Integer>("production_kc"));

        this.CrawId.setCellValueFactory(new PropertyValueFactory<Raw, String>("raw_id"));
        this.CrawName.setCellValueFactory(new PropertyValueFactory<Raw, String>("raw_name"));
        this.CrawBzq.setCellValueFactory(new PropertyValueFactory<Raw, Integer>("raw_bzq"));
        this.CrawPri.setCellValueFactory(new PropertyValueFactory<Raw, Float>("raw_price"));
        this.CrawKc.setCellValueFactory(new PropertyValueFactory<Raw, Float>("raw_kc"));

        ObservableList<TableColumn<Projectrec, ?>> observableList = tableRec.getColumns();
        observableList.get(0).setCellValueFactory(new PropertyValueFactory("planId"));
        observableList.get(1).setCellValueFactory(new PropertyValueFactory("zt"));
        observableList.get(2).setCellValueFactory(new PropertyValueFactory("num"));
        observableList.get(3).setCellValueFactory(new PropertyValueFactory("fzr"));
        observableList.get(4).setCellValueFactory(new PropertyValueFactory("date"));

        xAxis.setLabel("产品");
        yAxis.setLabel("销量");


        this.xlPane.getChildren().add((Node)ChartXl);
        this.ChartXl.minWidthProperty().bind(this.xlPane.widthProperty());
        this.ChartXl.minHeightProperty().bind(this.xlPane.heightProperty());

        this.cpRawName.textProperty().addListener((observable, oldValue, newValue)-> {
            String trimed = newValue.trim();
            if (trimed.length() > 0) {
                this.handleAddRaw(trimed);
            }
        });

        this.cpName1.textProperty().addListener((observable, oldValue, newValue)-> {
            String trimed = newValue.trim();
            if (trimed.length() > 0) {
                this.handleCpnameChange(trimed);
            }
        });

        this.cpHead.textProperty().addListener((observable, oldValue, newValue)-> {
            String trimed = newValue.trim();
            if (trimed.length() > 0) {
                this.handleCpheadChange(trimed);
            }
        });

        this.rawHead.textProperty().addListener((observable, oldValue, newValue)-> {
            String trimed = newValue.trim();
            if (trimed.length() > 0) {
                this.handleRawheadChange(trimed);
            }
        });

        try {
            qx = this.planSection.SearchQx();
        }
        catch (SQLException se)
        {
            se.printStackTrace();
            Staff.showAlert(Alert.AlertType.ERROR, "错误", "权限查询失败", "系统错误");
        }
        catch (NumberFormatException ie)
        {
            Staff.showAlert(Alert.AlertType.WARNING, "警告", "请输入正确格式！", "");
        }
    }

    private void handleRawheadChange(String trimed) {
        try
        {
            if(this.rawHead.getText().length() != 3)
                this.addRaw1.setDisable(true);
            else {
                this.addRaw1.setDisable(false);
                this.rawId1.setText(this.planSection.getNewRawId(this.rawHead.getText()));
            }
        }
        catch (SQLException se)
        {
            se.printStackTrace();
            Staff.showAlert(Alert.AlertType.ERROR, "错误", "查询失败", "系统错误");
        }
        catch (NumberFormatException ie)
        {
            Staff.showAlert(Alert.AlertType.WARNING, "警告", "请输入正确格式！", "");
        }
    }

    private void handleCpheadChange(String trimed) {
        try
        {
            if(this.cpHead.getText().length() != 3)
                this.addCp.setDisable(true);
            else
                this.cpId1.setText(this.planSection.getNewCpId(this.cpHead.getText()));
        }
        catch (SQLException se)
        {
            se.printStackTrace();
            Staff.showAlert(Alert.AlertType.ERROR, "错误", "查询失败", "系统错误");
        }
        catch (NumberFormatException ie)
        {
            Staff.showAlert(Alert.AlertType.WARNING, "警告", "请输入正确格式！", "");
        }
    }

    public void handleAddRaw(String s)
    {
        try {
            Raw[] raw = this.planSection.searchRawOnName(this.cpRawName.getText());
            if(raw.length == 0)
                this.addRaw.setDisable(true);
            else
            {
                this.addRaw.setDisable(false);
                this.cpRawPrice.setText(String.valueOf(raw[0].getRaw_price()));
            }
        }
        catch (SQLException se)
        {
            se.printStackTrace();
            this.planSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "查询失败", "系统错误");
        }
        catch (NumberFormatException ie)
        {
            Staff.showAlert(Alert.AlertType.WARNING, "警告", "请输入正确格式！", "");
        }
    }

    public void handleJump(MouseEvent mouseEvent) throws IOException {
        Button actionBtn = (Button) mouseEvent.getSource();
        if(actionBtn == this.SearchRec)
            this.Recsearch.toFront();
        else if(actionBtn == this.SearchCpkc)
            this.CpkcPane.toFront();
        else if(actionBtn == this.SearchYlkc)
            this.RawkcPane.toFront();
        else if(actionBtn == this.SearchScjh) {
            if(qx[0] == 0)
            {
                Staff.showAlert(Alert.AlertType.WARNING, "警告", "权限受限", "您没有执行这一项功能的权限，请与相关负责人联系授权！");
                return;
            }
            this.PlanSearPane.toFront();
        }
        else if(actionBtn == this.ChangeScjh) {
            this.PlanId.setText(this.planSection.GetNumber());
            this.PlanGlpane.toFront();
        }
        else if(actionBtn == this.ChangeCp) {
            if(qx[1] == 0)
            {
                Staff.showAlert(Alert.AlertType.WARNING, "警告", "权限受限", "您没有执行这一项功能的权限，请与相关负责人联系授权！");
                return;
            }
            this.ChangeCppane.toFront();
        }
        else if(actionBtn == this.ChangeRaw) {
            if(qx[2] == 0)
            {
                Staff.showAlert(Alert.AlertType.WARNING, "警告", "权限受限", "您没有执行这一项功能的权限，请与相关负责人联系授权！");
                return;
            }
            this.ChangeRawPane.toFront();
        }
        else if(actionBtn == this.SearchXl)
            this.searchXlPane.toFront();
        else if(actionBtn == this.btnSignout)
        {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/Lead.fxml"));

            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.setScene(new Scene((Parent) loader.load()));

            LeadContorller controller = loader.<LeadContorller>getController();

            controller.initData(this.planSection.getStaff());

            stage.show();

            Stage index = (Stage)UserManagePane.getScene().getWindow();
            index.close();
        }
    }

    public void handleClicks(MouseEvent mouseEvent) {

    }

    public void deleteVboxPlanSh(Node node)
    {
        this.vboxPlanSh.getChildren().remove(node);
    }

    public void handleSearchRaw(MouseEvent mouseEvent) {
        Button actionBtn = (Button) mouseEvent.getSource();
        if(actionBtn == this.searchRaw)
        {
            try {
                viewRawkc.getItems().clear();
                viewRawkc.refresh();
                String infom;
                if(this.messageRaw.getText().isEmpty())
                    infom = "null";
                else
                    infom = messageRaw.getText();
                Raw[] raws = new Raw[0];
                if(this.comboxCp.getValue().equals("按编号查询"))
                    raws = this.planSection.searchRawOnId(infom);
                else if(this.comboxCp.getValue().equals("按名称查询"))
                    raws = this.planSection.searchRawOnName(infom);
                ObservableList<Raw> list = FXCollections.observableArrayList();
                for(int i = 0; i < raws.length; i++)
                    list.add(raws[i]);
                viewRawkc.getItems().addAll(list);
            }
            catch (SQLException se)
            {
                se.printStackTrace();
                this.planSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "查询失败", "系统错误");
            }
            catch (NumberFormatException ie)
            {
                Staff.showAlert(Alert.AlertType.WARNING, "警告", "请输入正确格式！", "");
            }
        }
        else if(actionBtn == this.searchRaw1)
        {
            try {
                this.rawList.getChildren().clear();
                String infom;
                if(this.messageRaw1.getText().isEmpty())
                    infom = "null";
                else
                    infom = messageRaw1.getText();
                Raw[] raws = new Raw[0];
                if(this.comboxSearchRaw.getValue().equals("按编号查询"))
                    raws = this.planSection.searchRawOnId(infom);
                else if(this.comboxSearchRaw.getValue().equals("按名称查询"))
                    raws = this.planSection.searchRawOnName(infom);
                for(int i = 0; i < raws.length; i++) {
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/RawItem.fxml"));
                    Node node = loader.load();

                    RawItemController rawItemController = loader.<RawItemController>getController();
                    rawItemController.setNode(node);
                    rawItemController.initData(raws[i]);
                    rawItemController.setProductionPlanController(this);
                    rawItemController.setChangeDisable(true);
                    rawItemController.setDeleteDisable(true);
                    rawItemController.setBzqEditable(false);
                    rawItemController.setRawnameEditable(false);
                    rawItemController.setRawpriEditable(false);

                    this.rawList.getChildren().add(node);
                }
            }
            catch (SQLException | IOException se)
            {
                se.printStackTrace();
                this.planSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "查询失败", "系统错误");
            }
            catch (NumberFormatException ie)
            {
                Staff.showAlert(Alert.AlertType.WARNING, "警告", "请输入正确格式！", "");
            }
        }
        else if(actionBtn == this.searchRaw2)
        {
            try {
                this.vboxYl.getChildren().clear();
                String infom;
                if(this.messageRaw11.getText().isEmpty())
                    infom = "null";
                else
                    infom = messageRaw11.getText();
                Raw[] raws = new Raw[0];
                if(this.comboxSearchRaw1.getValue().equals("按编号查询"))
                    raws = this.planSection.searchRawOnId(infom);
                else if(this.comboxSearchRaw1.getValue().equals("按名称查询"))
                    raws = this.planSection.searchRawOnName(infom);
                for(int i = 0; i < raws.length; i++) {
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/RawItem.fxml"));
                    Node node = loader.load();

                    RawItemController rawItemController = loader.<RawItemController>getController();
                    rawItemController.setProductionPlanController(this);
                    rawItemController.setNode(node);
                    rawItemController.initData(raws[i]);
                    rawItemController.setChangeDisable(false);
                    rawItemController.setDeleteDisable(false);
                    rawItemController.setBzqEditable(true);
                    rawItemController.setRawnameEditable(true);
                    rawItemController.setRawpriEditable(true);

                    this.vboxYl.getChildren().add(node);
                }
            }
            catch (SQLException | IOException se)
            {
                se.printStackTrace();
                this.planSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "查询失败", "系统错误");
            }
            catch (NumberFormatException ie)
            {
                Staff.showAlert(Alert.AlertType.WARNING, "警告", "请输入正确格式！", "");
            }
        }
    }

    public void handleSearchPlan(MouseEvent mouseEvent) throws IOException{
        JFXButton actionBtn = (JFXButton)mouseEvent.getSource();
        if(actionBtn == this.searchPlanId)
        {
            try
            {
                String infom;
                if(this.messagePlan.getText().isEmpty())
                    infom = "全部";
                else
                    infom = this.messagePlan.getText();
                Plan[] plans = this.planSection.searchPlan(infom, this.comboxZt.getValue());
                this.vboxSearchPlan.getChildren().clear();
                for(int i = 0; i < plans.length; i++)
                {
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/PlanItem.fxml"));
                    Node node = loader.load();

                    PlanItemController planItemController = loader.<PlanItemController>getController();
                    planItemController.setController(this);
                    planItemController.setInform(plans[i], 1);
                    planItemController.setNode(node);
                    planItemController.setConfirmDisable(true);
                    planItemController.setDeleteDisable(true);
                    planItemController.setChangeDisable(true);
                    planItemController.setNumEditable(false);
                    planItemController.setPushNumVisable(false);
                    planItemController.setPushVisable(false);
                    planItemController.setDeadlineEditable(false);
                    planItemController.setWorkshopEditable(false);

                    vboxSearchPlan.getChildren().add(node);
                }
            }
            catch (SQLException se)
            {
                se.printStackTrace();
                this.planSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "查询失败", "系统错误");
            }
            catch (NumberFormatException ie)
            {
                Staff.showAlert(Alert.AlertType.WARNING, "警告", "请输入正确格式！", "");
            }
        }
        else if(actionBtn == this.searchPlanDate)
        {
            try {
                String sdate = this.searchPlanPicker.getStartDate().toString();
                String edate = this.searchPlanPicker.getEndDate().toString();
                Plan[] plans = this.planSection.searchPlan("全部", sdate, edate, this.comboxZt.getValue());
                this.vboxSearchPlan.getChildren().clear();
                for(int i = 0; i < plans.length; i++)
                {
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/PlanItem.fxml"));
                    Node node = loader.load();

                    PlanItemController planItemController = loader.<PlanItemController>getController();
                    planItemController.setController(this);
                    planItemController.setInform(plans[i], 1);
                    planItemController.setNode(node);
                    planItemController.setConfirmDisable(true);
                    planItemController.setDeleteDisable(true);
                    planItemController.setChangeDisable(true);
                    planItemController.setNumEditable(false);
                    planItemController.setPushNumVisable(false);
                    planItemController.setPushVisable(false);
                    planItemController.setDeadlineEditable(false);
                    planItemController.setWorkshopEditable(false);

                    vboxSearchPlan.getChildren().add(node);
                }
            }
            catch (SQLException se)
            {
                se.printStackTrace();
                this.planSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "查询失败", "系统错误");
            }
            catch (NumberFormatException ie)
            {
                Staff.showAlert(Alert.AlertType.WARNING, "警告", "请输入正确格式！", "");
            }
        }
        else if(actionBtn == this.searchPlanId1)
        {
            try
            {
                String infom;
                if(this.messagePlan1.getText().isEmpty())
                    infom = "全部";
                else
                    infom = this.messagePlan1.getText();
                Plan[] plans1 = this.planSection.searchPlan(infom, "待执行");
                Plan[] plans2 = this.planSection.searchPlan(infom, "执行中");
                Plan[] plans3 = this.planSection.searchPlan(infom, "审核中");
                Plan[][] plans = {plans1, plans2, plans3};
                this.vboxPlan.getChildren().clear();
                for(int k = 0; k < 3; k++) {
                    for (int i = 0; i < plans[k].length; i++) {
                        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/PlanItem.fxml"));
                        Node node = loader.load();

                        PlanItemController planItemController = loader.<PlanItemController>getController();
                        planItemController.setController(this);
                        planItemController.setInform(plans[k][i], 1);
                        planItemController.setNode(node);
                        planItemController.setConfirmDisable(true);
                        planItemController.setDeleteDisable(false);
                        planItemController.setChangeDisable(false);
                        planItemController.setNumEditable(true);
                        planItemController.setPushNumVisable(false);
                        planItemController.setPushVisable(false);
                        if(!plans[k][i].getPlan_zt().equals("审核中"))
                            planItemController.setWorkshopEditable(true);

                        vboxPlan.getChildren().add(node);
                    }
                }
            }
            catch (SQLException se)
            {
                se.printStackTrace();
                this.planSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "查询失败", "系统错误");
            }
            catch (NumberFormatException ie)
            {
                Staff.showAlert(Alert.AlertType.WARNING, "警告", "请输入正确格式！", "");
            }
        }
        else if(actionBtn == this.searchPlanDate1)
        {
            try {
                String infom;
                String sdate = this.searchPlanPicker1.getStartDate().toString();
                String edate = this.searchPlanPicker1.getEndDate().toString();
                if(this.messagePlan1.getText().isEmpty())
                    infom = "全部";
                else
                    infom = this.messagePlan1.getText();
                Plan[] plans1 = this.planSection.searchPlan(infom, sdate, edate, "待执行");
                Plan[] plans2 = this.planSection.searchPlan(infom, sdate, edate, "执行中");
                Plan[] plans3 = this.planSection.searchPlan(infom, sdate, edate, "审核中");
                Plan[][] plans = {plans1, plans2, plans3};
                this.vboxPlan.getChildren().clear();
                for(int k = 0; k < 3; k++) {
                    for (int i = 0; i < plans[k].length; i++) {
                        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/PlanItem.fxml"));
                        Node node = loader.load();

                        PlanItemController planItemController = loader.<PlanItemController>getController();
                        planItemController.setController(this);
                        planItemController.setInform(plans[k][i],1);
                        planItemController.setNode(node);
                        planItemController.setConfirmDisable(true);
                        planItemController.setDeleteDisable(false);
                        planItemController.setChangeDisable(false);
                        planItemController.setNumEditable(true);
                        planItemController.setPushNumVisable(false);
                        planItemController.setPushVisable(false);
                        if(!plans[k][i].getPlan_zt().equals("审核中"))
                            planItemController.setWorkshopEditable(true);

                        vboxPlan.getChildren().add(node);
                    }
                }
            }
            catch (SQLException se)
            {
                se.printStackTrace();
                this.planSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "查询失败", "系统错误");
            }
            catch (NumberFormatException ie)
            {
                Staff.showAlert(Alert.AlertType.WARNING, "警告", "请输入正确格式！", "");
            }
        }
        else if(actionBtn == searchPlanId2)
        {
            try
            {
                String infom;
                if(this.messagePlan2.getText().isEmpty())
                    infom = "全部";
                else
                    infom = this.messagePlan2.getText();
                Plan[] plans = this.planSection.searchPlan(infom, "待审核");
                this.vboxPlanSh.getChildren().clear();
                for(int i = 0; i < plans.length; i++)
                {
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/PlanItem.fxml"));
                    Node node = loader.load();

                    PlanItemController planItemController = loader.<PlanItemController>getController();
                    planItemController.setController(this);
                    planItemController.setInform(plans[i], 1);
                    planItemController.setNode(node);
                    planItemController.setConfirmDisable(false);
                    planItemController.setDeleteDisable(true);
                    planItemController.setChangeDisable(true);
                    planItemController.setNumEditable(false);
                    planItemController.setPushNumVisable(false);
                    planItemController.setPushVisable(false);
                    planItemController.setDeadlineEditable(false);
                    planItemController.setWorkshopEditable(false);

                    vboxPlanSh.getChildren().add(node);
                }
            }
            catch (SQLException se)
            {
                se.printStackTrace();
                this.planSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "查询失败", "系统错误");
            }
            catch (NumberFormatException ie)
            {
                Staff.showAlert(Alert.AlertType.WARNING, "警告", "请输入正确格式！", "");
            }
        }
    }

    public PlanSection getPlanSection() {
        return planSection;
    }

    public void deletevboxPlannode(Node node)
    {
        this.vboxPlan.getChildren().remove(node);
    }

    public void handleChangeCp(MouseEvent mouseEvent) {
        JFXButton actionBtn = (JFXButton)mouseEvent.getSource();
        if(actionBtn == this.changeCp)
        {
            if(this.cpName.getText().isEmpty() || this.p1.getText().isEmpty() || this.p2.getText().isEmpty() || this.p3.getText().isEmpty() || this.bzq.getText().isEmpty())
            {
                this.planSection.getStaff().showAlert(Alert.AlertType.INFORMATION, "警告", "修改失败", "请填写完整信息");
            }
            else
            {
                try {
                    this.planSection.changeCpInform(this.cpId.getText(), this.cpName.getText(), this.p1.getText(), this.p2.getText(), this.p3.getText(), this.bzq.getText());
                    Staff.showAlert(Alert.AlertType.INFORMATION, "提示", "修改成功！", "已修改成品基本信息");
                }
                catch (SQLException se)
                {
                    se.printStackTrace();
                    Staff.showAlert(Alert.AlertType.INFORMATION, "警告", "修改失败", "系统错误");
                }
                catch (NumberFormatException ie)
                {
                    Staff.showAlert(Alert.AlertType.WARNING, "警告", "请输入正确格式！", "");
                }
            }
        }
        else if(actionBtn == this.addRaw)
        {
            if(this.cpRawName.getText().isEmpty() || this.cpRawPrice.getText().isEmpty() || this.cpRawNum.getText().isEmpty())
            {
                Staff.showAlert(Alert.AlertType.INFORMATION, "警告", "添加失败", "请填写完整信息");
            }
            else
            {
                try {
                    Raw[] raws = this.planSection.searchRawOnName(this.cpRawName.getText());
                    raws[0].setRaw_num(Float.parseFloat(this.cpRawNum.getText()));
                    this.planSection.addCpRaws(this.cpId.getText(), raws[0]);
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/ProductRawItem.fxml"));
                    Node node = loader.load();

                    ProductRawController productRawController = loader.<ProductRawController>getController();
                    productRawController.setInform(raws[0]);
                    productRawController.setNode(node);
                    productRawController.setProductionPlanController(this);
                    productRawController.setIds(this.cpId.getText());

                    vboxChangeCPRaw.getChildren().add(node);
                    Staff.showAlert(Alert.AlertType.INFORMATION, "提示", "添加成功！", "已为" + this.cpId.getText() + "添加" + this.cpRawName.getText() + this.cpRawNum.getText() + "箱");
                }
                catch (SQLException | IOException se)
                {
                    se.printStackTrace();
                    Staff.showAlert(Alert.AlertType.ERROR, "错误", "添加失败", "原料已存在， 请先删除后再添加");
                }
                catch (NumberFormatException ie)
                {
                    Staff.showAlert(Alert.AlertType.WARNING, "警告", "请输入正确格式！", "");
                }
            }
        }
    }

    public void handleSearchCp(MouseEvent mouseEvent) {
        Button actionBtn = (Button) mouseEvent.getSource();
        if(actionBtn == this.searchCp)
        {
            try {
                viewCpkc.getItems().clear();
                viewCpkc.refresh();
                String infom;
                if(this.messageCp.getText().isEmpty())
                    infom = "null";
                else
                    infom = messageCp.getText();
                Production[] productions = new Production[0];
                if(this.comboxCp.getValue().equals("按编号查询"))
                    productions = this.planSection.searchCpOnID(infom);
                else if(this.comboxCp.getValue().equals("按名称查询"))
                    productions = this.planSection.searchCpOnName(infom);
                ObservableList<Production> list = FXCollections.observableArrayList();
                for(int i = 0; i < productions.length; i++)
                    list.add(productions[i]);
                viewCpkc.getItems().addAll(list);
            }
            catch (SQLException se)
            {
                se.printStackTrace();
                Staff.showAlert(Alert.AlertType.ERROR, "错误", "查询失败", "系统错误");
            }
            catch (NumberFormatException ie)
            {
                Staff.showAlert(Alert.AlertType.WARNING, "警告", "请输入正确格式！", "");
            }
        }
        else if(actionBtn == this.searchCp1)
        {
            try {
                if(this.comboxSearchCp1.getValue().equals("按编号查询"))
                {
                    String inform;
                    if(this.messageCp1.getText().isEmpty())
                        inform = "null";
                    else
                        inform = this.messageCp1.getText();
                    Production[] productions = this.planSection.searchCpOnID(inform);
                    cpList.getChildren().clear();
                    for(int i = 0; i < productions.length; i++)
                    {

                        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/ProductItem.fxml"));
                        Node node = loader.load();

                        ProductItemController productItemController = loader.<ProductItemController>getController();
                        productItemController.setInform(productions[i], 0);
                        productItemController.setProductionPlanController(this);
                        productItemController.setNode(node);
                        productItemController.setDeleteDisable(true);

                        cpList.getChildren().add(node);
                    }
                }
                else if(this.comboxSearchCp1.getValue().equals("按名称查询"))
                {
                    String inform;
                    if(this.messageCp1.getText().isEmpty())
                        inform = "null";
                    else
                        inform = this.messageCp1.getText();
                    Production[] productions = this.planSection.searchCpOnName(inform);
                    cpList.getChildren().clear();
                    for(int i = 0; i < productions.length; i++)
                    {

                        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/ProductItem.fxml"));
                        Node node = loader.load();

                        ProductItemController productItemController = loader.<ProductItemController>getController();
                        productItemController.setInform(productions[i], 0);
                        productItemController.setProductionPlanController(this);
                        productItemController.setNode(node);
                        productItemController.setDeleteDisable(true);

                        cpList.getChildren().add(node);
                    }
                }
            }
            catch (SQLException | IOException se)
            {
                se.printStackTrace();
                Staff.showAlert(Alert.AlertType.ERROR, "错误", "查询失败", "系统错误");
            }
            catch (NumberFormatException ie)
            {
                Staff.showAlert(Alert.AlertType.WARNING, "警告", "请输入正确格式！", "");
            }
        }
        else if(actionBtn == this.searchCp3)
        {
            try {
                if(this.comboxSearchCp3.getValue().equals("按编号查询"))
                {
                    String inform;
                    if(this.messageCp3.getText().isEmpty())
                        inform = "null";
                    else
                        inform = this.messageCp3.getText();
                    Production[] productions = this.planSection.searchCpOnID(inform);
                    vboxChangeCp.getChildren().clear();
                    for(int i = 0; i < productions.length; i++)
                    {
                        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/ProductItem.fxml"));
                        Node node = loader.load();

                        ProductItemController productItemController = loader.<ProductItemController>getController();
                        productItemController.setInform(productions[i], 1);
                        productItemController.setProductionPlanController(this);
                        productItemController.setNode(node);
                        productItemController.setDeleteDisable(false);

                        vboxChangeCp.getChildren().add(node);
                    }
                }
                else if(this.comboxSearchCp3.getValue().equals("按名称查询"))
                {
                    String inform;
                    if(this.messageCp3.getText().isEmpty())
                        inform = "null";
                    else
                        inform = this.messageCp3.getText();
                    Production[] productions = this.planSection.searchCpOnName(inform);
                    vboxChangeCp.getChildren().clear();
                    for(int i = 0; i < productions.length; i++)
                    {
                        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/ProductItem.fxml"));
                        Node node = loader.load();

                        ProductItemController productItemController = loader.<ProductItemController>getController();
                        productItemController.setInform(productions[i], 1);
                        productItemController.setProductionPlanController(this);
                        productItemController.setNode(node);
                        productItemController.setDeleteDisable(false);

                        vboxChangeCp.getChildren().add(node);
                    }
                }
            }
            catch (SQLException | IOException se)
            {
                se.printStackTrace();
                Staff.showAlert(Alert.AlertType.ERROR, "错误", "查询失败", "系统错误");
            }
            catch (NumberFormatException ie)
            {
                Staff.showAlert(Alert.AlertType.WARNING, "警告", "请输入正确格式！", "");
            }
        }
    }

    public void setChangeCpInform(Production production) {
        this.cpId.setText(production.getProduction_id());
        this.cpName.setText(production.getProduction_name());
        this.p1.setText(String.valueOf(production.getProduction_p1()));
        this.p2.setText(String.valueOf(production.getProduction_p2()));
        this.p3.setText(String.valueOf(production.getProduction_p3()));
        this.bzq.setText(String.valueOf(production.getProduction_bzq()));
        vboxChangeCPRaw.getChildren().clear();
        try {
            for(int i = 0; i < production.getRaws().length; i++)
            {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/ProductRawItem.fxml"));
                Node node = loader.load();

                ProductRawController productRawController = loader.<ProductRawController>getController();
                productRawController.setProductionPlanController(this);
                productRawController.setNode(node);
                productRawController.setInform(production.getRaws()[i]);
                productRawController.setIds(production.getProduction_id());

                vboxChangeCPRaw.getChildren().add(node);
            }
        }
        catch (IOException ie)
        {
            ie.printStackTrace();
        }
        catch (NumberFormatException ie)
        {
            Staff.showAlert(Alert.AlertType.WARNING, "警告", "请输入正确格式！", "");
        }
    }

    public void deleteChangeCpRawbox(Node node)
    {
        this.vboxChangeCPRaw.getChildren().remove(node);
    }

    public void deletevboxChangeCp(Node node)
    {
        this.vboxChangeCp.getChildren().remove(node);
    }

    public void handleRaw(MouseEvent mouseEvent) {
        if(this.rawHead.getText().isEmpty() || this.rawName1.getText().isEmpty() || this.rawPri1.getText().isEmpty() || this.rawBzq1.getText().isEmpty())
        {
            Staff.showAlert(Alert.AlertType.ERROR, "错误", "添加失败", "请填写完整信息");
        }
        else{
            try {
                Raw raw = new Raw(this.rawHead.getText() + this.rawId1.getText(), this.rawName1.getText(), Integer.parseInt(this.rawBzq1.getText()), Float.parseFloat(this.rawPri1.getText()));
                this.planSection.addRaw(raw);
                Staff.showAlert(Alert.AlertType.INFORMATION, "成功", "添加成功", "商品编号:" + this.rawHead.getText() + this.rawId1.getText());
            }
            catch (SQLException se)
            {
                se.printStackTrace();
                Staff.showAlert(Alert.AlertType.ERROR, "错误", "添加失败", "系统错误");
            }
            catch (NumberFormatException ie)
            {
                Staff.showAlert(Alert.AlertType.WARNING, "警告", "请输入正确格式！", "");
            }
        }
    }

    public void handleSearchXl(MouseEvent mouseEvent) {
        this.ChartXl.getData().clear();
        ObservableList<XYChart.Series<String, Number>> list_data = FXCollections.observableArrayList();
        XYChart.Series<String, Number> xy = new XYChart.Series<String, Number>();
        ObservableList<XYChart.Data<String, Number> > data = FXCollections.observableArrayList();
        String sates = this.timeXl.getStartDate().toString();
        String elates = this.timeXl.getEndDate().toString();
        try {
            HashMap<String, Integer> hashMap = this.planSection.searchXl(sates, elates);
            Iterator<Map.Entry<String, Integer>> iter = hashMap.entrySet().iterator();
            int max = -1;
            while (iter.hasNext()) {
                Map.Entry<String, Integer> entry = iter.next();
                Object key = entry.getKey();
                Object val = entry.getValue();
                data.add(new XYChart.Data<String,Number>(key.toString(),(int)val));
                max = Math.max(max, (int)val);
            }
            xy.setData(data);
            list_data.add(xy);
            this.yAxis.setUpperBound(max + 1000);
            this.ChartXl.setData(list_data);
        }catch (SQLException se)
        {
            se.printStackTrace();
            Staff.showAlert(Alert.AlertType.ERROR, "错误", "查询失败", "系统错误");
        }
        catch (NumberFormatException ie)
        {
            Staff.showAlert(Alert.AlertType.WARNING, "警告", "请输入正确格式！", "");
        }

    }

    public void handleCpnameChange(String s)
    {
        try {
            Production[] productions = this.planSection.searchCpOnName(this.cpName1.getText());
            if(productions.length == 0)
                this.addCp.setDisable(false);
            else
                this.addCp.setDisable(true);
        }
        catch (SQLException se)
        {
            Staff.showAlert(Alert.AlertType.ERROR, "错误", "查询失败", "系统错误");
        }
        catch (NumberFormatException ie)
        {
            Staff.showAlert(Alert.AlertType.WARNING, "警告", "请输入正确格式！", "");
        }
    }

    public void handleAddCp(MouseEvent mouseEvent) {
        if(this.cpHead.getText().isEmpty() || this.cpId1.getText().isEmpty() || this.cpName1.getText().isEmpty() || this.p11.getText().isEmpty() || this.p21.getText().isEmpty() || this.p31.getText().isEmpty() || this.bzq1.getText().isEmpty())
        {
            Staff.showAlert(Alert.AlertType.ERROR, "错误", "添加失败", "请输入完整信息");
        }
        else
        {
            try {
                Production production = new Production(this.cpHead.getText() + this.cpId1.getText(), this.cpName1.getText(), Float.parseFloat(this.p11.getText()), Float.parseFloat(this.p21.getText()), Float.parseFloat(this.p31.getText()), Integer.parseInt(this.bzq1.getText()), null, 0);
                this.planSection.addCp(production);
                Staff.showAlert(Alert.AlertType.INFORMATION, "成功", "添加成功", "商品编号:" + this.cpHead.getText() + this.cpId1.getText());
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
                Staff.showAlert(Alert.AlertType.ERROR, "错误", "添加失败", "系统错误");
            }
            catch (NumberFormatException ie)
            {
                Staff.showAlert(Alert.AlertType.WARNING, "警告", "请输入正确格式！", "");
            }
        }
    }

    public void handleMakePlan(MouseEvent mouseEvent) {
        JFXButton actionBtn = (JFXButton)mouseEvent.getSource();

        if(actionBtn == this.makePlan)
        {
            try
            {
                if(this.Cpname.getText().isEmpty() || this.Cpnum.getText().isEmpty() || this.planDdl.getValue().equals("") || this.workshops.getText().isEmpty())
                {
                    Staff.showAlert(Alert.AlertType.ERROR, "错误", "添加失败", "请填写完整信息");
                }
                else
                 {
                    Production[] production = this.planSection.searchCpOnName(this.Cpname.getText());
                    if (production.length == 0) {
                        Staff.showAlert(Alert.AlertType.ERROR, "错误", "添加失败", "未查询到该成品");
                    }
                    else if(production[0].getRaws().length == 0)
                    {
                        Staff.showAlert(Alert.AlertType.ERROR, "错误", "添加失败", "请先为其添加原料!");
                    }
                    else {
                        production[0].setNums(Integer.parseInt(this.Cpnum.getText()));
                        int flag = 0;
                        for (int i = 0; i < production[0].getRaws().length; i++) {
                            float vae = production[0].getRaws()[i].getRaw_num() * Integer.parseInt(this.Cpnum.getText());
                            Raw[] raws = this.planSection.searchRawOnId(production[0].getRaws()[i].getRaw_id());
                            if (raws[0].getRaw_kc() < vae)
                            {
                                flag = 1;
                            }
                        }
                        if (flag == 1) {
                            Staff.showAlert(Alert.AlertType.ERROR, "错误", "添加失败", "库存不足");
                        }
                        else {
                            Plan plan = new Plan(this.PlanId.getText(), "待执行", production[0], "", "", this.planSection.getStaff().Name, this.workshops.getText(), this.planDdl.getValue().toString());
                            this.planSection.makePlan(plan);
                            Staff.showAlert(Alert.AlertType.INFORMATION, "成功", "添加成功", "计划编号" + this.PlanId.getText());
                        }
                    }
                }
            }
            catch (SQLException se)
            {
                se.printStackTrace();
                Staff.showAlert(Alert.AlertType.ERROR, "错误", "添加失败", "系统错误");
            }
            catch (NumberFormatException ie)
            {
                Staff.showAlert(Alert.AlertType.WARNING, "警告", "请输入正确格式！", "");
            }
        }
    }

    public void deletevboxYl(Node node) {
        this.vboxYl.getChildren().remove(node);
    }

    public void handleSearchRec(MouseEvent mouseEvent) {
        if(this.recId.getText().isEmpty()) {
            Staff.showAlert(Alert.AlertType.ERROR, "错误", "查询失败", "请输入计划编号！");
        }
        else
        {
            try {
                Projectrec[] projectrecs = this.planSection.searchRec(this.recId.getText());
                if(projectrecs.length == 0)
                    Staff.showAlert(Alert.AlertType.ERROR, "失败", "查询失败", "未查询到该信息");
                else
                {
                    tableRec.getItems().clear();
                    tableRec.refresh();
                    ObservableList<Projectrec> list = FXCollections.observableArrayList();
                    for(int i = 0; i < projectrecs.length; i++)
                        list.add(projectrecs[i]);
                    tableRec.getItems().addAll(list);
                }
            }
           catch (SQLException se)
           {
               se.printStackTrace();
               Staff.showAlert(Alert.AlertType.ERROR, "错误", "查询失败", "系统错误");
           }
            catch (NumberFormatException ie)
            {
                Staff.showAlert(Alert.AlertType.WARNING, "警告", "请输入正确格式！", "");
            }
        }
    }
}
