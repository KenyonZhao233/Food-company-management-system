package com.JKX.Controller;

import com.JKX.Controller.ItemController.PlanItemController;
import com.JKX.Controller.ItemController.StaffInformController;
import com.JKX.Model.PlanSection;
import com.JKX.Model.Staff;
import com.JKX.Model.Table.Plan;
import com.JKX.Model.Table.Production;
import com.JKX.Model.Table.Raw;
import com.calendarfx.view.print.TimeRangeView;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.sun.javaws.util.JfxHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.omg.CORBA.PRIVATE_MEMBER;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProductionPlanController {

    private PlanSection planSection;

    @FXML
    private AnchorPane UserManagePane;

    @FXML
    private Label nameLable;

    //跳转界面
    @FXML
    private javafx.scene.control.Button SearchCpkc, SearchYlkc, SearchScjh ,ChangeScjh, ChangeCp, ChangeRaw, SearchXl, btnSignout;

    //各种界面
    @FXML
    private AnchorPane RawkcPane, PlanSearPane, PlanGlpane, ChangeCppane, ChangeRawPane, searchXlPane, CpkcPane;

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

    //计划管理
    //新建计划页面
    @FXML
    private JFXTextField PlanId, Cpname, Cpnum;
    @FXML
    private JFXButton makePlan;

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
    private JFXTextField cpId, cpName, p1, p2, p3, bzq, cpRawId, cpRawName, cpRawPrice, cpRawNum;
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
    private JFXTextField rawId, rawName, rawPri, rawBzq;
    @FXML
    private JFXButton changeRaw;
    @FXML
    private VBox vboxYl;
    //添加原料界面
    @FXML
    private JFXTextField rawId1, rawName1, rawPri1, rawBzq1;
    @FXML
    private JFXButton addRaw1;

   //销量分析页面
    @FXML
    private TimeRangeView timeXl;
    @FXML
    private JFXButton searchXlonDate;
    @FXML
    private BarChart ChartXl;

    public void initData(Staff staff)
    {
        this.planSection = new PlanSection(staff);
        List<String> searchMethod = new ArrayList<String>();
        searchMethod.add("按编号查询");
        searchMethod.add("按名称查询");
        this.comboxCp.getItems().addAll(searchMethod);
        this.comboxRaw.getItems().addAll(searchMethod);
        this.comboxSearchCp1.getItems().addAll(searchMethod);
        this.comboxSearchCp3.getItems().addAll(searchMethod);
        this.comboxSearchRaw.getItems().addAll(searchMethod);
        this.comboxCp.setValue("按编号查询");
        this.comboxRaw.setValue("按编号查询");
        this.comboxSearchRaw.setValue("按编号查询");
        this.comboxSearchCp1.setValue("按编号查询");
        this.comboxSearchCp3.setValue("按编号查询");
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
    }

    public void handleJump(MouseEvent mouseEvent) throws IOException {
        Button actionBtn = (Button) mouseEvent.getSource();
        if(actionBtn == this.SearchCpkc)
            this.CpkcPane.toFront();
        else if(actionBtn == this.SearchYlkc)
            this.RawkcPane.toFront();
        else if(actionBtn == this.SearchScjh)
            this.PlanSearPane.toFront();
        else if(actionBtn == this.ChangeScjh) {
            this.PlanId.setText(this.planSection.GetNumber());
            this.PlanGlpane.toFront();
        }
        else if(actionBtn == this.ChangeCp)
            this.ChangeCppane.toFront();
        else if(actionBtn == this.ChangeRaw)
            this.ChangeRawPane.toFront();
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
                Plan[] plans = this.planSection.searchPlan(infom, "全部");
                this.vboxSearchPlan.getChildren().clear();
                for(int i = 0; i < plans.length; i++)
                {
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/PlanItem.fxml"));
                    Node node = loader.load();

                    PlanItemController planItemController = loader.<PlanItemController>getController();
                    planItemController.setInform(plans[i]);
                    planItemController.setController(this);
                    planItemController.setNode(node);
                    planItemController.setConfirmDisable(true);
                    planItemController.setDeleteDisable(true);
                    planItemController.setChangeDisable(true);
                    planItemController.setNumEditable(false);

                    vboxSearchPlan.getChildren().add(node);
                }
            }
            catch (SQLException se)
            {
                se.printStackTrace();
                this.planSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "查询失败", "系统错误");
            }
        }
        else if(actionBtn == this.searchPlanDate)
        {
            try {
                String sdate = this.searchPlanPicker.getStartDate().toString();
                String edate = this.searchPlanPicker.getEndDate().toString();
                Plan[] plans = this.planSection.searchPlan("全部", sdate, edate, "全部");
                this.vboxSearchPlan.getChildren().clear();
                for(int i = 0; i < plans.length; i++)
                {
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/PlanItem.fxml"));
                    Node node = loader.load();

                    PlanItemController planItemController = loader.<PlanItemController>getController();
                    planItemController.setInform(plans[i]);
                    planItemController.setController(this);
                    planItemController.setNode(node);
                    planItemController.setConfirmDisable(true);
                    planItemController.setDeleteDisable(true);
                    planItemController.setChangeDisable(true);
                    planItemController.setNumEditable(false);

                    vboxSearchPlan.getChildren().add(node);
                }
            }
            catch (SQLException se)
            {
                se.printStackTrace();
                this.planSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "查询失败", "系统错误");
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
                Plan[] plans = this.planSection.searchPlan(infom, "待执行");
                this.vboxSearchPlan.getChildren().clear();
                for(int i = 0; i < plans.length; i++)
                {
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/PlanItem.fxml"));
                    Node node = loader.load();

                    PlanItemController planItemController = loader.<PlanItemController>getController();
                    planItemController.setInform(plans[i]);
                    planItemController.setController(this);
                    planItemController.setNode(node);
                    planItemController.setConfirmDisable(true);
                    planItemController.setDeleteDisable(false);
                    planItemController.setChangeDisable(false);
                    planItemController.setNumEditable(true);

                    vboxPlan.getChildren().add(node);
                }
            }
            catch (SQLException se)
            {
                se.printStackTrace();
                this.planSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "查询失败", "系统错误");
            }
        }
        else if(actionBtn == this.searchPlanDate1)
        {
            try {
                String sdate = this.searchPlanPicker1.getStartDate().toString();
                String edate = this.searchPlanPicker1.getEndDate().toString();
                Plan[] plans = this.planSection.searchPlan("全部", sdate, edate, "待执行");
                this.vboxSearchPlan.getChildren().clear();
                for(int i = 0; i < plans.length; i++)
                {
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/PlanItem.fxml"));
                    Node node = loader.load();

                    PlanItemController planItemController = loader.<PlanItemController>getController();
                    planItemController.setInform(plans[i]);
                    planItemController.setNode(node);
                    planItemController.setController(this);
                    planItemController.setConfirmDisable(true);
                    planItemController.setDeleteDisable(false);
                    planItemController.setChangeDisable(false);
                    planItemController.setNumEditable(true);

                    vboxPlan.getChildren().add(node);
                }
            }
            catch (SQLException se)
            {
                se.printStackTrace();
                this.planSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "查询失败", "系统错误");
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
                this.vboxSearchPlan.getChildren().clear();
                for(int i = 0; i < plans.length; i++)
                {
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/PlanItem.fxml"));
                    Node node = loader.load();

                    PlanItemController planItemController = loader.<PlanItemController>getController();
                    planItemController.setInform(plans[i]);
                    planItemController.setController(this);
                    planItemController.setNode(node);
                    planItemController.setConfirmDisable(false);
                    planItemController.setDeleteDisable(true);
                    planItemController.setChangeDisable(true);
                    planItemController.setNumEditable(false);

                    vboxPlanSh.getChildren().add(node);
                }
            }
            catch (SQLException se)
            {
                se.printStackTrace();
                this.planSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "查询失败", "系统错误");
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
                this.planSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "查询失败", "系统错误");
            }
        }
        else if(actionBtn == this.searchCp1)
        {
            try {
                if(this.comboxSearchCp1.getValue().equals("按编号查询"))
                {
                    Production[] productions = this.planSection.searchCpOnID(this.messageCp1.getText());
                }
                else if(this.comboxSearchCp1.getValue().equals("按名称查询"))
                {
                    Production[] productions = this.planSection.searchCpOnName(this.messageCp1.getText());
                }
            }
            catch (SQLException se)
            {
                se.printStackTrace();
                this.planSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "查询失败", "系统错误");
            }
        }
        else if(actionBtn == this.searchCp3)
        {

        }
    }

    public void handleRaw(MouseEvent mouseEvent) {
    }

    public void handleSearchXl(MouseEvent mouseEvent) {
    }

    public void handleAddCp(MouseEvent mouseEvent) {
    }

    public void handleMakePlan(MouseEvent mouseEvent) {
        JFXButton actionBtn = (JFXButton)mouseEvent.getSource();

        if(actionBtn == this.makePlan)
        {
            try
            {
                if(this.Cpname.getText().isEmpty() || this.Cpnum.getText().isEmpty())
                {
                    this.planSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "添加失败", "请填写完整信息");
                }
                else
                 {
                    Production[] production = this.planSection.searchCpOnName(this.Cpname.getText());
                    if (production.length == 0) {
                        this.planSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "添加失败", "未查询到该成品");
                    }
                    else {
                        production[0].setNums(Integer.parseInt(this.Cpnum.getText()));
                        int flag = 0;
                        for (int i = 0; i < production[0].getRaws().length; i++) {
                            System.out.println(production[0].getRaws()[i].getRaw_num());
                            float vae = production[0].getRaws()[i].getRaw_num() * Integer.parseInt(this.Cpnum.getText());
                            Raw[] raws = this.planSection.searchRawOnId(production[0].getRaws()[i].getRaw_id());
                            if (raws[0].getRaw_kc() < vae)
                            {
                                flag = 1;
                            }
                        }
                        if (flag == 1) {
                            this.planSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "添加失败", "库存不足");
                        }
                        else {
                            Plan plan = new Plan(this.PlanId.getText(), "待执行", production[0], "", "", this.planSection.getStaff().Name);
                            this.planSection.makePlan(plan);
                            this.planSection.getStaff().showAlert(Alert.AlertType.INFORMATION, "成功", "添加成功", "计划编号" + this.PlanId.getText());
                        }
                    }
                }
            }
            catch (SQLException se)
            {
                se.printStackTrace();
                this.planSection.getStaff().showAlert(Alert.AlertType.ERROR, "错误", "添加失败", "系统错误");
            }
        }
    }
}
