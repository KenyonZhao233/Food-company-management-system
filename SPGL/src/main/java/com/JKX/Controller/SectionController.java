package com.JKX.Controller;

import com.JKX.Model.SearchTables.*;
import com.JKX.Model.Staff;
import com.JKX.Model.SearchSection;
import com.calendarfx.view.print.TimeRangeView;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class SectionController implements Initializable {

    public Staff staff;

    private SearchSection searchSection;

    private int activePage = -1;

    private String[] Table = {"cj", "ck", "customer", "customer_type", "finance", "order", "pick", "product", "product_ck", "product_rec", "project", "raw", "raw_ck", "raw_rec", "unpaid"};

    @FXML
    private Pane mainPane, xsPane, cwPane, cpkPane, ylkPane, sccjPane, scjhPane, emptyPane, pnlTime, pnlName;

    @FXML
    private Pane PaneCustom;

    @FXML
    private Pane product_kc;

    @FXML
    private Pane raw1_panel;

    @FXML
    private Pane raw2_panel;

    @FXML
    private Pane yljl_panel;

    @FXML
    private Pane unpaid_panel;

    @FXML
    private Pane refund_pane;

    @FXML
    private Pane finance_pane;

    @FXML
    private Pane project_panel;

    @FXML
    private Pane cangku_panel;

    @FXML
    private Pane finished_product_panel;

    @FXML
    private Pane pick_panel;

    @FXML
    private Pane chejian_panel;

    @FXML
    private AnchorPane SectionPane;

    /*跳转按钮*/
    @FXML
    private Button btnXs, btnCw, btnCp, btnYl, btnCj, btnJh, btnSignout;

    /*查询（编号or时间）按钮*/
    @FXML
    private Button btnSearchOrder, btnSearchOrder2, btnSearchLs, btnSearchDfk, btnSearchDfk2, btnSearchDth, btnSearchScjh, btnSearchScjh2, btnSearchYlREC;

    /*查询（编号or名称）按钮*/
    @FXML
    private Button btnSearchYlKC, btnSearchYlKC2, btnSearchTh, btnSearchKh, btnSearchXl, btnSearchXl2, btnSearchKc, btnSearchKc2, btnSearchKc3,btnSearchCK, btnSearchYl, btnSearchCp2, btnSearchCp, btnSearchCj;

    /*返回按钮*/
    @FXML
    private Button btnCccjReturn, btnCpReturn, btnCwReturn, btnScjhReturn, btnXsReturn, btnYlReturn;

    /*时间界面组件*/
    @FXML
    private JFXButton searchDate, searchIdD;

    @FXML
    private TimeRangeView datePick;

    @FXML
    private TimeRangeView yl_cz_date_view;

    @FXML
    private TimeRangeView unpaid_dateview;

    @FXML
    private TimeRangeView refund_dateview;

    @FXML
    private TimeRangeView finance_dateview;

    @FXML
    private TimeRangeView project_dateview1;

    @FXML
    private TextField finance_inf_text;

    @FXML
    private TextField bhD;

    @FXML
    private TextField yl_cz_id_text;

    @FXML
    private TextField unpaid_ddid_text;

    @FXML
    private TextField unpaid_khid_text;

    @FXML
    private TextField project_cp_id_text;

    @FXML
    private TextField product_id_text1;

    @FXML
    private TextField product_name_text1;

    @FXML
    private JFXButton yl_cz_date_btn;

    @FXML
    private JFXButton product_id_btn1;

    @FXML
    private JFXButton product_name_btn1;

    @FXML
    private JFXButton project_btn1;

    @FXML
    private JFXButton project_cp_id_btn;

    @FXML
    private JFXButton finance_dateview_btn;

    @FXML
    private JFXButton finance_inf_btn;

    @FXML
    private JFXButton yl_cz_id_btn;

    @FXML
    private JFXButton refund_dateview_btn;

    /*名称界面组件*/
    @FXML
    private JFXButton searchIdN;

    @FXML
    private JFXButton saerchName;

    @FXML
    private TextField name;

    @FXML
    private TextField bhN;

    @FXML
    private TextField cust_id_text;

    @FXML
    private TextField cust_tele_text;

    @FXML
    private TextField kc_name_text;

    @FXML
    private TextField kc_ck_id_text;

    @FXML
    private TextField raw_id_text;

    @FXML
    private TextField rew_name_text;

    @FXML
    private TextField yl_kc_id_text;

    @FXML
    private TextField yl_kc_ck_text;

    @FXML
    private TextField refund_ddid_text;

    @FXML
    private TextField refund_khid_text;

    @FXML
    private TextField orderid_text1;

    @FXML
    private TextField customid_text1;

    @FXML
    private TextField chejian_cj_id_text;

    @FXML
    private TextField chejian_cp_id_text;

    @FXML
    private JFXButton yl_kc_id_btn;

    @FXML
    private JFXButton yl_kc_ck_btn;

    @FXML
    private JFXButton chejian_cj_id_btn;

    @FXML
    private JFXButton chejian_cp_id_btn;

    @FXML
    private JFXButton cust_id_btn;

    @FXML
    private JFXButton cust_tele_btn;

    @FXML
    private JFXButton kc_name_btn;

    @FXML
    private JFXButton kc_ck_id_btn;

    @FXML
    private JFXButton rew_id_btn;

    @FXML
    private JFXButton rew_name_btn;

    @FXML
    private JFXButton unpaid_date_btn;

    @FXML
    private JFXButton unpaid_ddid_btn;

    @FXML
    private JFXButton unpaid_khid_btn;

    @FXML
    private JFXButton refund_ddid_btn;

    @FXML
    private JFXButton refund_khid_btn;

    @FXML
    private JFXButton orderid_btn1;

    @FXML
    private JFXButton customid_btn1;

    /*名称界面组件*/

    /*表格*/
    @FXML
    private TableView<OneOrderRecord> dateTable;

    @FXML
    private TableView<ProductSales> nameTable;

    @FXML
    private TableView<MyCustom> CustomTable;

    @FXML
    private TableView<ProductKuCun> kc_table;

    @FXML
    private TableView<Raw> raw1_table;

    @FXML
    private TableView<RawKC> raw2_table;

    @FXML
    private TableView<RawRecord> yljl_table;

    @FXML
    private TableView<Unpaid> unpaid_table;

    @FXML
    private TableView<Refund> refund_table;

    @FXML
    private TableView<Finance> finance_table;

    @FXML
    private TableView<Project> project_table;

    @FXML
    private TableView<Warehouse> cangku_table;

    @FXML
    private TableView<Product> finished_product_table;

    @FXML
    private TableView<Pick> pick_table;

    @FXML
    private TableView<WorkShop> chejian_table;

    @FXML
    public void handleClicks(MouseEvent event) throws IOException
    {
        Button actionBtn = (Button) event.getSource();
        emptyPane.toFront();
        if(actionBtn == btnXs)
        {
            xsPane.toFront();
        }
        if(actionBtn == btnCw)
        {
            cwPane.toFront();
        }
        if(actionBtn == btnCp)
        {
            cpkPane.toFront();
        }
        if(actionBtn == btnYl)
        {
            ylkPane.toFront();
        }
        if(actionBtn == btnCj)
        {
            sccjPane.toFront();
        }
        if(actionBtn == btnJh)
        {
            scjhPane.toFront();
        }
        if(actionBtn == btnCccjReturn || actionBtn == btnCpReturn || actionBtn == btnCwReturn || actionBtn == btnScjhReturn || actionBtn == btnXsReturn || actionBtn == btnYlReturn)
        {
            mainPane.toFront();
        }
        if(actionBtn == this.btnSearchYlREC || actionBtn == btnSearchOrder || actionBtn == btnSearchOrder2 || actionBtn == btnSearchLs || actionBtn == btnSearchDfk || actionBtn == btnSearchDfk2 || actionBtn == btnSearchDth || actionBtn == btnSearchScjh || actionBtn == btnSearchScjh2)
        {
            pnlTime.toFront();
        }
        if(actionBtn == this.btnSearchYlKC || actionBtn == this.btnSearchYlKC2 || actionBtn == btnSearchTh || actionBtn == btnSearchKh || actionBtn == btnSearchXl || actionBtn == btnSearchXl2 || actionBtn == btnSearchKc || actionBtn == btnSearchKc2 || actionBtn == btnSearchKc3 || actionBtn == btnSearchCK || actionBtn == btnSearchYl || actionBtn == btnSearchCp2 || actionBtn == btnSearchCp || actionBtn == btnSearchCj)
        {
            pnlName.toFront();
        }
        if(actionBtn == btnSignout)
        {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/Lead.fxml"));

            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.setScene(new Scene((Parent) loader.load()));

            LeadContorller controller = loader.<LeadContorller>getController();

            controller.initData(staff);

            stage.show();

            Stage index = (Stage)SectionPane.getScene().getWindow();
            index.close();
        }
        if(actionBtn == this.btnSearchCj)
            this.activePage = 0;
        else if(actionBtn == this.btnSearchCK)
            this.activePage = 1;
        else if(actionBtn == this.btnSearchKh)
            this.activePage = 2;
        else if(actionBtn == this.btnSearchLs)
            this.activePage = 4;
        else if(actionBtn == this.btnSearchOrder || actionBtn == this.btnSearchOrder2)
            this.activePage = 5;
        else if(actionBtn == this.btnSearchDth)
            this.activePage = 6;
        else if(actionBtn == this.btnSearchCp || actionBtn == this.btnSearchCp2)
            this.activePage = 7;
        else if(actionBtn == this.btnSearchKc || actionBtn == this.btnSearchKc2 || actionBtn == this.btnSearchKc3)
            this.activePage = 8;
        else if(actionBtn == this.btnSearchScjh || actionBtn == this.btnSearchScjh2)
            this.activePage = 10;
        else if(actionBtn == this.btnSearchYl)
            this.activePage = 11;
        else if(actionBtn == this.btnSearchYlKC || actionBtn == this.btnSearchYlKC2)
            this.activePage = 12;
        else if(actionBtn == this.btnSearchYlREC)
            this.activePage = 13;
        else if(actionBtn == this.btnSearchDfk || actionBtn == this.btnSearchDfk2)
            this.activePage = 14;
        else{
            System.out.println("None");
        }
        //销量 * 2
        //


        if(actionBtn == this.btnSearchOrder||actionBtn==btnSearchOrder2)
        {
            final ObservableList<OneOrderRecord> data = FXCollections.observableArrayList();
            try
            {
                OneOrderRecord[] orders=searchSection.SearchAllOrders();
                for(int i=0;i<orders.length;i++)
                {
                    data.add(orders[i]);
                }
            }
            catch(SQLException ex)
            {
                staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "系统错误");
                return;
            }
            ObservableList<TableColumn<OneOrderRecord, ?>> observableList = dateTable.getColumns();
            observableList.get(0).setCellValueFactory(new PropertyValueFactory("order_id"));
            observableList.get(1).setCellValueFactory(new PropertyValueFactory("order_date"));
            observableList.get(2).setCellValueFactory(new PropertyValueFactory("order_product"));
            observableList.get(3).setCellValueFactory(new PropertyValueFactory("order_num"));
            observableList.get(4).setCellValueFactory(new PropertyValueFactory("order_zt"));
            observableList.get(5).setCellValueFactory(new PropertyValueFactory("order_custom"));
            observableList.get(6).setCellValueFactory(new PropertyValueFactory("order_type"));
            observableList.get(7).setCellValueFactory(new PropertyValueFactory("order_fzr"));
            dateTable.setItems(data);
        }

        if(actionBtn==this.btnSearchXl||actionBtn==btnSearchXl2)
        {
            final ObservableList<ProductSales> data = FXCollections.observableArrayList();
            try
            {
                ProductSales[] sales=searchSection.SearchAllSales();
                for(int i=0;i<sales.length;i++)
                {
                    data.add(sales[i]);
                }
            }
            catch(SQLException ex)
            {
                staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "系统错误");
                return;
            }
            ObservableList<TableColumn<ProductSales, ?>> observableList = nameTable.getColumns();
            observableList.get(0).setCellValueFactory(new PropertyValueFactory("product_name"));
            observableList.get(1).setCellValueFactory(new PropertyValueFactory("product_id"));
            observableList.get(2).setCellValueFactory(new PropertyValueFactory("product_sales"));
            observableList.get(3).setCellValueFactory(new PropertyValueFactory("product_p1"));
            observableList.get(4).setCellValueFactory(new PropertyValueFactory("product_p2"));
            observableList.get(5).setCellValueFactory(new PropertyValueFactory("product_p3"));
            nameTable.setItems(data);
        }

        if(actionBtn==btnSearchKh)
        {
            final ObservableList<MyCustom> data = FXCollections.observableArrayList();
            try
            {
                MyCustom[] customs=searchSection.SearchAllCustomers();
                for(int i=0;i<customs.length;i++)
                {
                    data.add(customs[i]);
                }
            }
            catch(SQLException ex)
            {
                staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "系统错误");
                return;
            }
            ObservableList<TableColumn<MyCustom, ?>> observableList = CustomTable.getColumns();
            observableList.get(0).setCellValueFactory(new PropertyValueFactory("custom_id"));
            observableList.get(1).setCellValueFactory(new PropertyValueFactory("custom_tp_name"));
            observableList.get(2).setCellValueFactory(new PropertyValueFactory("custom_name"));
            observableList.get(3).setCellValueFactory(new PropertyValueFactory("custom_wg"));
            observableList.get(4).setCellValueFactory(new PropertyValueFactory("custom_tele"));
            CustomTable.setItems(data);

            PaneCustom.toFront();
        }

        if(actionBtn==cust_id_btn)
        {
            final ObservableList<MyCustom> data = FXCollections.observableArrayList();
            try
            {
                MyCustom[] customs=searchSection.SearchCustomersById(cust_id_text.getText());
                if(customs.length==0)
                {
                    staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "此客户编号不存在");
                    return;
                }
                for(int i=0;i<customs.length;i++)
                {
                    data.add(customs[i]);
                }
            }
            catch(SQLException ex)
            {
                staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "系统错误");
                return;
            }
            ObservableList<TableColumn<MyCustom, ?>> observableList = CustomTable.getColumns();
            observableList.get(0).setCellValueFactory(new PropertyValueFactory("custom_id"));
            observableList.get(1).setCellValueFactory(new PropertyValueFactory("custom_tp_name"));
            observableList.get(2).setCellValueFactory(new PropertyValueFactory("custom_name"));
            observableList.get(3).setCellValueFactory(new PropertyValueFactory("custom_wg"));
            observableList.get(4).setCellValueFactory(new PropertyValueFactory("custom_tele"));
            CustomTable.setItems(data);

            PaneCustom.toFront();
        }

        if(actionBtn==cust_tele_btn)
        {
            final ObservableList<MyCustom> data = FXCollections.observableArrayList();
            try
            {
                MyCustom[] customs=searchSection.SearchCustomersByTele(cust_tele_text.getText());
                if(customs.length==0)
                {
                    staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "此客户联系方式不存在");
                    return;
                }
                for(int i=0;i<customs.length;i++)
                {
                    data.add(customs[i]);
                }
            }
            catch(SQLException ex)
            {
                staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "系统错误");
                return;
            }
            ObservableList<TableColumn<MyCustom, ?>> observableList = CustomTable.getColumns();
            observableList.get(0).setCellValueFactory(new PropertyValueFactory("custom_id"));
            observableList.get(1).setCellValueFactory(new PropertyValueFactory("custom_tp_name"));
            observableList.get(2).setCellValueFactory(new PropertyValueFactory("custom_name"));
            observableList.get(3).setCellValueFactory(new PropertyValueFactory("custom_wg"));
            observableList.get(4).setCellValueFactory(new PropertyValueFactory("custom_tele"));
            CustomTable.setItems(data);

            PaneCustom.toFront();
        }

        if(actionBtn==btnSearchKc||actionBtn==btnSearchKc2||actionBtn==btnSearchKc3)
        {
            final ObservableList<ProductKuCun> data = FXCollections.observableArrayList();
            try
            {
                ProductKuCun[] productKuCuns=searchSection.SearchAllKuCun();
                if(productKuCuns.length==0)
                {
                    staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "此不存在");
                    return;
                }
                for(int i=0;i<productKuCuns.length;i++)
                {
                    data.add(productKuCuns[i]);
                }
            }
            catch(SQLException ex)
            {
                staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "系统错误");
                return;
            }
            ObservableList<TableColumn<ProductKuCun, ?>> observableList = kc_table.getColumns();
            observableList.get(0).setCellValueFactory(new PropertyValueFactory("product_id"));
            observableList.get(1).setCellValueFactory(new PropertyValueFactory("product_name"));
            observableList.get(2).setCellValueFactory(new PropertyValueFactory("product_num"));
            observableList.get(3).setCellValueFactory(new PropertyValueFactory("product_in"));
            kc_table.setItems(data);

            product_kc.toFront();
        }

        if(actionBtn==kc_name_btn)
        {
            final ObservableList<ProductKuCun> data = FXCollections.observableArrayList();
            try
            {
                ProductKuCun[] productKuCuns=searchSection.SearchKuCunByName(kc_name_text.getText());
                if(productKuCuns.length==0)
                {
                    staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "此产品名称不存在");
                    return;
                }
                for(int i=0;i<productKuCuns.length;i++)
                {
                    data.add(productKuCuns[i]);
                }
            }
            catch(SQLException ex)
            {
                staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "系统错误");
                return;
            }
            ObservableList<TableColumn<ProductKuCun, ?>> observableList = kc_table.getColumns();
            observableList.get(0).setCellValueFactory(new PropertyValueFactory("product_id"));
            observableList.get(1).setCellValueFactory(new PropertyValueFactory("product_name"));
            observableList.get(2).setCellValueFactory(new PropertyValueFactory("product_num"));
            observableList.get(3).setCellValueFactory(new PropertyValueFactory("product_in"));
            kc_table.setItems(data);

            product_kc.toFront();
        }

        if(actionBtn==kc_ck_id_btn)
        {
            final ObservableList<ProductKuCun> data = FXCollections.observableArrayList();
            try
            {
                ProductKuCun[] productKuCuns=searchSection.SearchKuCunByCKID(kc_ck_id_text.getText());
                if(productKuCuns.length==0)
                {
                    staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "此产品名称不存在");
                    return;
                }
                for(int i=0;i<productKuCuns.length;i++)
                {
                    data.add(productKuCuns[i]);
                }
            }
            catch(SQLException ex)
            {
                staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "系统错误");
                return;
            }
            ObservableList<TableColumn<ProductKuCun, ?>> observableList = kc_table.getColumns();
            observableList.get(0).setCellValueFactory(new PropertyValueFactory("product_id"));
            observableList.get(1).setCellValueFactory(new PropertyValueFactory("product_name"));
            observableList.get(2).setCellValueFactory(new PropertyValueFactory("product_num"));
            observableList.get(3).setCellValueFactory(new PropertyValueFactory("product_in"));
            kc_table.setItems(data);

            product_kc.toFront();
        }

        if(actionBtn==btnSearchYl)
        {
            final ObservableList<Raw> data = FXCollections.observableArrayList();
            try
            {
                Raw[] raws=searchSection.SearchAllRaw();
                if(raws.length==0)
                {
                    staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "不存在");
                    return;
                }
                for(int i=0;i<raws.length;i++)
                {
                    data.add(raws[i]);
                }
            }
            catch(SQLException ex)
            {
                staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "系统错误");
                return;
            }
            ObservableList<TableColumn<Raw, ?>> observableList = raw1_table.getColumns();
            observableList.get(0).setCellValueFactory(new PropertyValueFactory("raw_id"));
            observableList.get(1).setCellValueFactory(new PropertyValueFactory("raw_name"));
            observableList.get(2).setCellValueFactory(new PropertyValueFactory("bzq"));
            observableList.get(3).setCellValueFactory(new PropertyValueFactory("pri"));
            raw1_table.setItems(data);

            raw1_panel.toFront();
        }

        if(actionBtn==rew_id_btn)
        {
            final ObservableList<Raw> data = FXCollections.observableArrayList();
            try
            {
                Raw[] raws=searchSection.SearchRawById(raw_id_text.getText());
                if(raws.length==0)
                {
                    staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "此原料编号不存在");
                    return;
                }
                for(int i=0;i<raws.length;i++)
                {
                    data.add(raws[i]);
                }
            }
            catch(SQLException ex)
            {
                staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "系统错误");
                return;
            }
            ObservableList<TableColumn<Raw, ?>> observableList = raw1_table.getColumns();
            observableList.get(0).setCellValueFactory(new PropertyValueFactory("raw_id"));
            observableList.get(1).setCellValueFactory(new PropertyValueFactory("raw_name"));
            observableList.get(2).setCellValueFactory(new PropertyValueFactory("bzq"));
            observableList.get(3).setCellValueFactory(new PropertyValueFactory("pri"));
            raw1_table.setItems(data);

            raw1_panel.toFront();
        }

        if(actionBtn==rew_name_btn)
        {
            final ObservableList<Raw> data = FXCollections.observableArrayList();
            try
            {
                Raw[] raws=searchSection.SearchRawByName(rew_name_text.getText());
                if(raws.length==0)
                {
                    staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "此原料名称不存在");
                    return;
                }
                for(int i=0;i<raws.length;i++)
                {
                    data.add(raws[i]);
                }
            }
            catch(SQLException ex)
            {
                staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "系统错误");
                return;
            }
            ObservableList<TableColumn<Raw, ?>> observableList = raw1_table.getColumns();
            observableList.get(0).setCellValueFactory(new PropertyValueFactory("raw_id"));
            observableList.get(1).setCellValueFactory(new PropertyValueFactory("raw_name"));
            observableList.get(2).setCellValueFactory(new PropertyValueFactory("bzq"));
            observableList.get(3).setCellValueFactory(new PropertyValueFactory("pri"));
            raw1_table.setItems(data);

            raw1_panel.toFront();
        }

        if(actionBtn==btnSearchYlKC||actionBtn==btnSearchYlKC2)
        {
            final ObservableList<Raw> data = FXCollections.observableArrayList();
            try
            {
                Raw[] raws=searchSection.SearchAllRaw();
                if(raws.length==0)
                {
                    staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "不存在");
                    return;
                }
                for(int i=0;i<raws.length;i++)
                {
                    data.add(raws[i]);
                }
            }
            catch(SQLException ex)
            {
                staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "系统错误");
                return;
            }
            ObservableList<TableColumn<Raw, ?>> observableList = raw1_table.getColumns();
            observableList.get(0).setCellValueFactory(new PropertyValueFactory("raw_id"));
            observableList.get(1).setCellValueFactory(new PropertyValueFactory("raw_name"));
            observableList.get(2).setCellValueFactory(new PropertyValueFactory("bzq"));
            observableList.get(3).setCellValueFactory(new PropertyValueFactory("pri"));
            raw1_table.setItems(data);

            raw1_panel.toFront();
        }

        if(event.getSource()==btnSearchYlKC)
        {
            final ObservableList<RawKC> data = FXCollections.observableArrayList();
            try
            {
                RawKC[] raws=searchSection.SearchAllRawKC();
                if(raws.length==0)
                {
                    staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "不存在");
                    return;
                }
                for(int i=0;i<raws.length;i++)
                {
                    data.add(raws[i]);
                }
            }
            catch(SQLException ex)
            {
                staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "系统错误");
                return;
            }
            ObservableList<TableColumn<RawKC, ?>> observableList = raw2_table.getColumns();
            observableList.get(0).setCellValueFactory(new PropertyValueFactory("raw_name"));
            observableList.get(1).setCellValueFactory(new PropertyValueFactory("raw_id"));
            observableList.get(2).setCellValueFactory(new PropertyValueFactory("raw_rm"));
            observableList.get(3).setCellValueFactory(new PropertyValueFactory("raw_in"));
            raw2_table.setItems(data);

            raw2_panel.toFront();
        }

        if(actionBtn==yl_kc_id_btn)
        {
            final ObservableList<RawKC> data = FXCollections.observableArrayList();
            try
            {
                RawKC[] raws=searchSection.SearchRawKCById(yl_kc_id_text.getText());
                if(raws.length==0)
                {
                    staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "不存在");
                    return;
                }
                for(int i=0;i<raws.length;i++)
                {
                    data.add(raws[i]);
                }
            }
            catch(SQLException ex)
            {
                staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "系统错误");
                return;
            }
            ObservableList<TableColumn<RawKC, ?>> observableList = raw2_table.getColumns();
            observableList.get(0).setCellValueFactory(new PropertyValueFactory("raw_name"));
            observableList.get(1).setCellValueFactory(new PropertyValueFactory("raw_id"));
            observableList.get(2).setCellValueFactory(new PropertyValueFactory("raw_rm"));
            observableList.get(3).setCellValueFactory(new PropertyValueFactory("raw_in"));
            raw2_table.setItems(data);

            raw2_panel.toFront();
        }

        if(actionBtn==yl_kc_ck_btn)
        {
            final ObservableList<RawKC> data = FXCollections.observableArrayList();
            try
            {
                RawKC[] raws=searchSection.SearchRawKCByIn(yl_kc_ck_text.getText());
                if(raws.length==0)
                {
                    staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "不存在");
                    return;
                }
                for(int i=0;i<raws.length;i++)
                {
                    data.add(raws[i]);
                }
            }
            catch(SQLException ex)
            {
                staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "系统错误");
                return;
            }
            ObservableList<TableColumn<RawKC, ?>> observableList = raw2_table.getColumns();
            observableList.get(0).setCellValueFactory(new PropertyValueFactory("raw_name"));
            observableList.get(1).setCellValueFactory(new PropertyValueFactory("raw_id"));
            observableList.get(2).setCellValueFactory(new PropertyValueFactory("raw_rm"));
            observableList.get(3).setCellValueFactory(new PropertyValueFactory("raw_in"));
            raw2_table.setItems(data);

            raw2_panel.toFront();
        }

        if(actionBtn==btnSearchYlREC)
        {
            final ObservableList<RawRecord> data = FXCollections.observableArrayList();
            try
            {
                RawRecord[] raws=searchSection.SearchAllRawRecord();
                if(raws.length==0)
                {
                    staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "不存在");
                    return;
                }
                for(int i=0;i<raws.length;i++)
                {
                    data.add(raws[i]);
                }
            }
            catch(SQLException ex)
            {
                staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "系统错误");
                return;
            }
            ObservableList<TableColumn<RawRecord, ?>> observableList = yljl_table.getColumns();
            observableList.get(0).setCellValueFactory(new PropertyValueFactory("raw_id"));
            observableList.get(1).setCellValueFactory(new PropertyValueFactory("raw_name"));
            observableList.get(2).setCellValueFactory(new PropertyValueFactory("raw_date"));
            observableList.get(3).setCellValueFactory(new PropertyValueFactory("raw_lx"));
            observableList.get(4).setCellValueFactory(new PropertyValueFactory("raw_num"));
            observableList.get(5).setCellValueFactory(new PropertyValueFactory("raw_fzrid"));
            yljl_table.setItems(data);

            yljl_panel.toFront();
        }

        if(actionBtn==yl_cz_date_btn)
        {
            final ObservableList<RawRecord> data = FXCollections.observableArrayList();
            try
            {
                RawRecord[] raws=searchSection.SearchRawRecordByDate(yl_cz_date_view.getStartDate().toString(),yl_cz_date_view.getEndDate().toString());
                if(raws.length==0)
                {
                    staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "不存在");
                    return;
                }
                for(int i=0;i<raws.length;i++)
                {
                    data.add(raws[i]);
                }
            }
            catch(SQLException ex)
            {
                staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "系统错误");
                return;
            }
            ObservableList<TableColumn<RawRecord, ?>> observableList = yljl_table.getColumns();
            observableList.get(0).setCellValueFactory(new PropertyValueFactory("raw_id"));
            observableList.get(1).setCellValueFactory(new PropertyValueFactory("raw_name"));
            observableList.get(2).setCellValueFactory(new PropertyValueFactory("raw_date"));
            observableList.get(3).setCellValueFactory(new PropertyValueFactory("raw_lx"));
            observableList.get(4).setCellValueFactory(new PropertyValueFactory("raw_num"));
            observableList.get(5).setCellValueFactory(new PropertyValueFactory("raw_fzrid"));
            yljl_table.setItems(data);

            yljl_panel.toFront();
        }

        if(actionBtn==yl_cz_id_btn)
        {
            final ObservableList<RawRecord> data = FXCollections.observableArrayList();
            try
            {
                RawRecord[] raws=searchSection.SearchRawRecordById(yl_cz_id_text.getText());
                if(raws.length==0)
                {
                    staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "不存在");
                    return;
                }
                for(int i=0;i<raws.length;i++)
                {
                    data.add(raws[i]);
                }
            }
            catch(SQLException ex)
            {
                staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "系统错误");
                return;
            }
            ObservableList<TableColumn<RawRecord, ?>> observableList = yljl_table.getColumns();
            observableList.get(0).setCellValueFactory(new PropertyValueFactory("raw_id"));
            observableList.get(1).setCellValueFactory(new PropertyValueFactory("raw_name"));
            observableList.get(2).setCellValueFactory(new PropertyValueFactory("raw_date"));
            observableList.get(3).setCellValueFactory(new PropertyValueFactory("raw_lx"));
            observableList.get(4).setCellValueFactory(new PropertyValueFactory("raw_num"));
            observableList.get(5).setCellValueFactory(new PropertyValueFactory("raw_fzrid"));
            yljl_table.setItems(data);

            yljl_panel.toFront();
        }

        if(actionBtn==btnSearchDfk||actionBtn==btnSearchDfk2)
        {
            final ObservableList<Unpaid> data = FXCollections.observableArrayList();
            try
            {
                Unpaid[] unpaids=searchSection.SearchAllUnpaid();
                if(unpaids.length==0)
                {
                    staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "不存在");
                    return;
                }
                for(int i=0;i<unpaids.length;i++)
                {
                    data.add(unpaids[i]);
                }
            }
            catch(SQLException ex)
            {
                staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "系统错误");
                return;
            }
            ObservableList<TableColumn<Unpaid, ?>> observableList = unpaid_table.getColumns();
            observableList.get(0).setCellValueFactory(new PropertyValueFactory("order_id"));
            observableList.get(1).setCellValueFactory(new PropertyValueFactory("order_date"));
            observableList.get(2).setCellValueFactory(new PropertyValueFactory("order_mn"));
            observableList.get(3).setCellValueFactory(new PropertyValueFactory("order_cusrom"));
            observableList.get(4).setCellValueFactory(new PropertyValueFactory("customer_name"));
            observableList.get(5).setCellValueFactory(new PropertyValueFactory("customer_tele"));
            unpaid_table.setItems(data);

            unpaid_panel.toFront();
        }

        if(actionBtn==unpaid_date_btn)
        {
            final ObservableList<Unpaid> data = FXCollections.observableArrayList();
            try
            {
                Unpaid[] unpaids=searchSection.SearchUnpaidByDate(unpaid_dateview.getStartDate().toString(),unpaid_dateview.getEndDate().toString());
                if(unpaids.length==0)
                {
                    staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "不存在");
                    return;
                }
                for(int i=0;i<unpaids.length;i++)
                {
                    data.add(unpaids[i]);
                }
            }
            catch(SQLException ex)
            {
                staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "系统错误");
                return;
            }
            ObservableList<TableColumn<Unpaid, ?>> observableList = unpaid_table.getColumns();
            observableList.get(0).setCellValueFactory(new PropertyValueFactory("order_id"));
            observableList.get(1).setCellValueFactory(new PropertyValueFactory("order_date"));
            observableList.get(2).setCellValueFactory(new PropertyValueFactory("order_mn"));
            observableList.get(3).setCellValueFactory(new PropertyValueFactory("order_cusrom"));
            observableList.get(4).setCellValueFactory(new PropertyValueFactory("customer_name"));
            observableList.get(5).setCellValueFactory(new PropertyValueFactory("customer_tele"));
            unpaid_table.setItems(data);

            unpaid_panel.toFront();
        }

        if(actionBtn==unpaid_ddid_btn)
        {
            final ObservableList<Unpaid> data = FXCollections.observableArrayList();
            try
            {
                Unpaid[] unpaids=searchSection.SearchUnpaidByOrderId(unpaid_ddid_text.getText());
                if(unpaids.length==0)
                {
                    staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "不存在");
                    return;
                }
                for(int i=0;i<unpaids.length;i++)
                {
                    data.add(unpaids[i]);
                }
            }
            catch(SQLException ex)
            {
                staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "系统错误");
                return;
            }
            ObservableList<TableColumn<Unpaid, ?>> observableList = unpaid_table.getColumns();
            observableList.get(0).setCellValueFactory(new PropertyValueFactory("order_id"));
            observableList.get(1).setCellValueFactory(new PropertyValueFactory("order_date"));
            observableList.get(2).setCellValueFactory(new PropertyValueFactory("order_mn"));
            observableList.get(3).setCellValueFactory(new PropertyValueFactory("order_cusrom"));
            observableList.get(4).setCellValueFactory(new PropertyValueFactory("customer_name"));
            observableList.get(5).setCellValueFactory(new PropertyValueFactory("customer_tele"));
            unpaid_table.setItems(data);

            unpaid_panel.toFront();
        }

        if(actionBtn==unpaid_khid_btn)
        {
            final ObservableList<Unpaid> data = FXCollections.observableArrayList();
            try
            {
                Unpaid[] unpaids=searchSection.SearchUnpaidByCustomId(unpaid_khid_text.getText());
                if(unpaids.length==0)
                {
                    staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "不存在");
                    return;
                }
                for(int i=0;i<unpaids.length;i++)
                {
                    data.add(unpaids[i]);
                }
            }
            catch(SQLException ex)
            {
                staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "系统错误");
                return;
            }
            ObservableList<TableColumn<Unpaid, ?>> observableList = unpaid_table.getColumns();
            observableList.get(0).setCellValueFactory(new PropertyValueFactory("order_id"));
            observableList.get(1).setCellValueFactory(new PropertyValueFactory("order_date"));
            observableList.get(2).setCellValueFactory(new PropertyValueFactory("order_mn"));
            observableList.get(3).setCellValueFactory(new PropertyValueFactory("order_cusrom"));
            observableList.get(4).setCellValueFactory(new PropertyValueFactory("customer_name"));
            observableList.get(5).setCellValueFactory(new PropertyValueFactory("customer_tele"));
            unpaid_table.setItems(data);

            unpaid_panel.toFront();
        }

        if(actionBtn==btnSearchTh)
        {
            final ObservableList<Refund> data = FXCollections.observableArrayList();
            try
            {
                Refund[] refunds=searchSection.SearchAllRefund();
                if(refunds.length==0)
                {
                    staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "不存在");
                    return;
                }
                for(int i=0;i<refunds.length;i++)
                {
                    data.add(refunds[i]);
                }
            }
            catch(SQLException ex)
            {
                staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "系统错误");
                return;
            }
            ObservableList<TableColumn<Refund, ?>> observableList = refund_table.getColumns();
            observableList.get(0).setCellValueFactory(new PropertyValueFactory("return_id"));
            observableList.get(1).setCellValueFactory(new PropertyValueFactory("order_money"));
            observableList.get(2).setCellValueFactory(new PropertyValueFactory("order_id"));
            observableList.get(3).setCellValueFactory(new PropertyValueFactory("return_date"));
            observableList.get(4).setCellValueFactory(new PropertyValueFactory("return_reason"));
            observableList.get(5).setCellValueFactory(new PropertyValueFactory("return_state"));
            observableList.get(6).setCellValueFactory(new PropertyValueFactory("custom_id"));
            observableList.get(7).setCellValueFactory(new PropertyValueFactory("custom_name"));
            observableList.get(8).setCellValueFactory(new PropertyValueFactory("custom_tele"));

            refund_table.setItems(data);

            refund_pane.toFront();
        }

        if(actionBtn==refund_ddid_btn)
        {
            final ObservableList<Refund> data = FXCollections.observableArrayList();
            try
            {
                Refund[] refunds=searchSection.SearchRefundByOrderId(refund_ddid_text.getText());
                if(refunds.length==0)
                {
                    staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "不存在");
                    return;
                }
                for(int i=0;i<refunds.length;i++)
                {
                    data.add(refunds[i]);
                }
            }
            catch(SQLException ex)
            {
                staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "系统错误");
                return;
            }
            ObservableList<TableColumn<Refund, ?>> observableList = refund_table.getColumns();
            observableList.get(0).setCellValueFactory(new PropertyValueFactory("return_id"));
            observableList.get(1).setCellValueFactory(new PropertyValueFactory("order_money"));
            observableList.get(2).setCellValueFactory(new PropertyValueFactory("order_id"));
            observableList.get(3).setCellValueFactory(new PropertyValueFactory("return_date"));
            observableList.get(4).setCellValueFactory(new PropertyValueFactory("return_reason"));
            observableList.get(5).setCellValueFactory(new PropertyValueFactory("return_state"));
            observableList.get(6).setCellValueFactory(new PropertyValueFactory("custom_id"));
            observableList.get(7).setCellValueFactory(new PropertyValueFactory("custom_name"));
            observableList.get(8).setCellValueFactory(new PropertyValueFactory("custom_tele"));

            refund_table.setItems(data);

            refund_pane.toFront();
        }

        if(actionBtn==refund_khid_btn)
        {
            final ObservableList<Refund> data = FXCollections.observableArrayList();
            try
            {
                Refund[] refunds=searchSection.SearchRefundByCustomId(refund_khid_text.getText());
                if(refunds.length==0)
                {
                    staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "不存在");
                    return;
                }
                for(int i=0;i<refunds.length;i++)
                {
                    data.add(refunds[i]);
                }
            }
            catch(SQLException ex)
            {
                staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "系统错误");
                return;
            }
            ObservableList<TableColumn<Refund, ?>> observableList = refund_table.getColumns();
            observableList.get(0).setCellValueFactory(new PropertyValueFactory("return_id"));
            observableList.get(1).setCellValueFactory(new PropertyValueFactory("order_money"));
            observableList.get(2).setCellValueFactory(new PropertyValueFactory("order_id"));
            observableList.get(3).setCellValueFactory(new PropertyValueFactory("return_date"));
            observableList.get(4).setCellValueFactory(new PropertyValueFactory("return_reason"));
            observableList.get(5).setCellValueFactory(new PropertyValueFactory("return_state"));
            observableList.get(6).setCellValueFactory(new PropertyValueFactory("custom_id"));
            observableList.get(7).setCellValueFactory(new PropertyValueFactory("custom_name"));
            observableList.get(8).setCellValueFactory(new PropertyValueFactory("custom_tele"));

            refund_table.setItems(data);

            refund_pane.toFront();
        }

        if(actionBtn==refund_dateview_btn)
        {
            final ObservableList<Refund> data = FXCollections.observableArrayList();
            try
            {
                Refund[] refunds=searchSection.SearchRefundByDate(refund_dateview.getStartDate().toString(),refund_dateview.getEndDate().toString());
                if(refunds.length==0)
                {
                    staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "不存在");
                    return;
                }
                for(int i=0;i<refunds.length;i++)
                {
                    data.add(refunds[i]);
                }
            }
            catch(SQLException ex)
            {
                staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "系统错误");
                return;
            }
            ObservableList<TableColumn<Refund, ?>> observableList = refund_table.getColumns();
            observableList.get(0).setCellValueFactory(new PropertyValueFactory("return_id"));
            observableList.get(1).setCellValueFactory(new PropertyValueFactory("order_money"));
            observableList.get(2).setCellValueFactory(new PropertyValueFactory("order_id"));
            observableList.get(3).setCellValueFactory(new PropertyValueFactory("return_date"));
            observableList.get(4).setCellValueFactory(new PropertyValueFactory("return_reason"));
            observableList.get(5).setCellValueFactory(new PropertyValueFactory("return_state"));
            observableList.get(6).setCellValueFactory(new PropertyValueFactory("custom_id"));
            observableList.get(7).setCellValueFactory(new PropertyValueFactory("custom_name"));
            observableList.get(8).setCellValueFactory(new PropertyValueFactory("custom_tele"));

            refund_table.setItems(data);

            refund_pane.toFront();
        }

        if(actionBtn==btnSearchLs)
        {
            final ObservableList<Finance> data = FXCollections.observableArrayList();
            try
            {
                Finance[] finances=searchSection.SearchAllFinance();
                if(finances.length==0)
                {
                    staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "不存在");
                    return;
                }
                for(int i=0;i<finances.length;i++)
                {
                    data.add(finances[i]);
                }
            }
            catch(SQLException ex)
            {
                staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "系统错误");
                return;
            }
            ObservableList<TableColumn<Finance, ?>> observableList = finance_table.getColumns();
            observableList.get(0).setCellValueFactory(new PropertyValueFactory("finance_id"));
            observableList.get(1).setCellValueFactory(new PropertyValueFactory("finance_user"));
            observableList.get(2).setCellValueFactory(new PropertyValueFactory("finance_mn"));
            observableList.get(3).setCellValueFactory(new PropertyValueFactory("finance_time"));
            observableList.get(4).setCellValueFactory(new PropertyValueFactory("finance_inf"));

            finance_table.setItems(data);

            finance_pane.toFront();
        }

        if(actionBtn==finance_dateview_btn)
        {
            final ObservableList<Finance> data = FXCollections.observableArrayList();
            try
            {
                Finance[] finances=searchSection.SearchFinanceByDate(finance_dateview.getStartDate().toString(),finance_dateview.getEndDate().toString());
                if(finances.length==0)
                {
                    staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "不存在");
                    return;
                }
                for(int i=0;i<finances.length;i++)
                {
                    data.add(finances[i]);
                }
            }
            catch(SQLException ex)
            {
                staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "系统错误");
                return;
            }
            ObservableList<TableColumn<Finance, ?>> observableList = finance_table.getColumns();
            observableList.get(0).setCellValueFactory(new PropertyValueFactory("finance_id"));
            observableList.get(1).setCellValueFactory(new PropertyValueFactory("finance_user"));
            observableList.get(2).setCellValueFactory(new PropertyValueFactory("finance_mn"));
            observableList.get(3).setCellValueFactory(new PropertyValueFactory("finance_time"));
            observableList.get(4).setCellValueFactory(new PropertyValueFactory("finance_inf"));

            finance_table.setItems(data);

            finance_pane.toFront();
        }

        if(actionBtn==finance_inf_btn)
        {
            final ObservableList<Finance> data = FXCollections.observableArrayList();
            try
            {
                Finance[] finances=searchSection.SearchFinanceByInfo(finance_inf_text.getText());
                if(finances.length==0)
                {
                    staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "不存在");
                    return;
                }
                for(int i=0;i<finances.length;i++)
                {
                    data.add(finances[i]);
                }
            }
            catch(SQLException ex)
            {
                staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "系统错误");
                return;
            }
            ObservableList<TableColumn<Finance, ?>> observableList = finance_table.getColumns();
            observableList.get(0).setCellValueFactory(new PropertyValueFactory("finance_id"));
            observableList.get(1).setCellValueFactory(new PropertyValueFactory("finance_user"));
            observableList.get(2).setCellValueFactory(new PropertyValueFactory("finance_mn"));
            observableList.get(3).setCellValueFactory(new PropertyValueFactory("finance_time"));
            observableList.get(4).setCellValueFactory(new PropertyValueFactory("finance_inf"));

            finance_table.setItems(data);

            finance_pane.toFront();
        }

        if(actionBtn==btnSearchScjh||actionBtn==btnSearchScjh2)
        {
            final ObservableList<Project> data = FXCollections.observableArrayList();
            try
            {
                Project[] projects=searchSection.SearchAllProject();
                if(projects.length==0)
                {
                    staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "不存在");
                    return;
                }
                for(int i=0;i<projects.length;i++)
                {
                    data.add(projects[i]);
                }
            }
            catch(SQLException ex)
            {
                staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "系统错误");
                return;
            }
            ObservableList<TableColumn<Project, ?>> observableList = project_table.getColumns();
            observableList.get(0).setCellValueFactory(new PropertyValueFactory("produce_id"));
            observableList.get(1).setCellValueFactory(new PropertyValueFactory("produce_type"));
            observableList.get(2).setCellValueFactory(new PropertyValueFactory("produce_wp"));
            observableList.get(3).setCellValueFactory(new PropertyValueFactory("produce_num"));
            observableList.get(4).setCellValueFactory(new PropertyValueFactory("produce_sdate"));
            observableList.get(5).setCellValueFactory(new PropertyValueFactory("produce_edate"));
            observableList.get(6).setCellValueFactory(new PropertyValueFactory("produce_zzr"));
            observableList.get(7).setCellValueFactory(new PropertyValueFactory("produce_fzr"));
            observableList.get(8).setCellValueFactory(new PropertyValueFactory("produce_ddl"));

            project_table.setItems(data);

            project_panel.toFront();
        }

        if(actionBtn==project_btn1)
        {
            final ObservableList<Project> data = FXCollections.observableArrayList();
            try
            {
                Project[] projects=searchSection.SearchProjectByStartDate(project_dateview1.getStartDate().toString(),project_dateview1.getEndDate().toString());
                if(projects.length==0)
                {
                    staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "不存在");
                    return;
                }
                for(int i=0;i<projects.length;i++)
                {
                    data.add(projects[i]);
                }
            }
            catch(SQLException ex)
            {
                staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "系统错误");
                return;
            }
            ObservableList<TableColumn<Project, ?>> observableList = project_table.getColumns();
            observableList.get(0).setCellValueFactory(new PropertyValueFactory("produce_id"));
            observableList.get(1).setCellValueFactory(new PropertyValueFactory("produce_type"));
            observableList.get(2).setCellValueFactory(new PropertyValueFactory("produce_wp"));
            observableList.get(3).setCellValueFactory(new PropertyValueFactory("produce_num"));
            observableList.get(4).setCellValueFactory(new PropertyValueFactory("produce_sdate"));
            observableList.get(5).setCellValueFactory(new PropertyValueFactory("produce_edate"));
            observableList.get(6).setCellValueFactory(new PropertyValueFactory("produce_zzr"));
            observableList.get(7).setCellValueFactory(new PropertyValueFactory("produce_fzr"));
            observableList.get(8).setCellValueFactory(new PropertyValueFactory("produce_ddl"));

            project_table.setItems(data);

            project_panel.toFront();
        }

        if(actionBtn==project_cp_id_btn)
        {
            final ObservableList<Project> data = FXCollections.observableArrayList();
            try
            {
                Project[] projects=searchSection.SearchProjectByProductId(project_cp_id_text.getText());
                if(projects.length==0)
                {
                    staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "不存在");
                    return;
                }
                for(int i=0;i<projects.length;i++)
                {
                    data.add(projects[i]);
                }
            }
            catch(SQLException ex)
            {
                staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "系统错误");
                return;
            }
            ObservableList<TableColumn<Project, ?>> observableList = project_table.getColumns();
            observableList.get(0).setCellValueFactory(new PropertyValueFactory("produce_id"));
            observableList.get(1).setCellValueFactory(new PropertyValueFactory("produce_type"));
            observableList.get(2).setCellValueFactory(new PropertyValueFactory("produce_wp"));
            observableList.get(3).setCellValueFactory(new PropertyValueFactory("produce_num"));
            observableList.get(4).setCellValueFactory(new PropertyValueFactory("produce_sdate"));
            observableList.get(5).setCellValueFactory(new PropertyValueFactory("produce_edate"));
            observableList.get(6).setCellValueFactory(new PropertyValueFactory("produce_zzr"));
            observableList.get(7).setCellValueFactory(new PropertyValueFactory("produce_fzr"));
            observableList.get(8).setCellValueFactory(new PropertyValueFactory("produce_ddl"));

            project_table.setItems(data);

            project_panel.toFront();
        }

        if(actionBtn==btnSearchCK)
        {
            final ObservableList<Warehouse> data = FXCollections.observableArrayList();
            try
            {
                Warehouse[] warehouses=searchSection.SearchAllWarehouse();
                if(warehouses.length==0)
                {
                    staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "不存在");
                    return;
                }
                for(int i=0;i<warehouses.length;i++)
                {
                    data.add(warehouses[i]);
                }
            }
            catch(SQLException ex)
            {
                staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "系统错误");
                return;
            }
            ObservableList<TableColumn<Warehouse, ?>> observableList = cangku_table.getColumns();
            observableList.get(0).setCellValueFactory(new PropertyValueFactory("ck_id"));
            observableList.get(1).setCellValueFactory(new PropertyValueFactory("ck_pos"));

            cangku_table.setItems(data);

            cangku_panel.toFront();
        }

        if(actionBtn==btnSearchCp||actionBtn==btnSearchCp2)
        {
            final ObservableList<Product> data = FXCollections.observableArrayList();
            try
            {
                Product[] products=searchSection.SearchAllProduct();
                if(products.length==0)
                {
                    staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "不存在");
                    return;
                }
                for(int i=0;i<products.length;i++)
                {
                    data.add(products[i]);
                }
            }
            catch(SQLException ex)
            {
                staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "系统错误");
                return;
            }
            ObservableList<TableColumn<Product, ?>> observableList = finished_product_table.getColumns();
            observableList.get(0).setCellValueFactory(new PropertyValueFactory("product_id"));
            observableList.get(1).setCellValueFactory(new PropertyValueFactory("product_name"));
            observableList.get(2).setCellValueFactory(new PropertyValueFactory("product_p1"));
            observableList.get(3).setCellValueFactory(new PropertyValueFactory("product_p2"));
            observableList.get(4).setCellValueFactory(new PropertyValueFactory("product_p3"));
            observableList.get(5).setCellValueFactory(new PropertyValueFactory("product_bzq"));

            finished_product_table.setItems(data);

            finished_product_panel.toFront();
        }

        if(actionBtn==product_id_btn1)
        {
            final ObservableList<Product> data = FXCollections.observableArrayList();
            try
            {
                Product[] products=searchSection.SearchProductById(product_id_text1.getText());
                if(products.length==0)
                {
                    staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "不存在");
                    return;
                }
                for(int i=0;i<products.length;i++)
                {
                    data.add(products[i]);
                }
            }
            catch(SQLException ex)
            {
                staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "系统错误");
                return;
            }
            ObservableList<TableColumn<Product, ?>> observableList = finished_product_table.getColumns();
            observableList.get(0).setCellValueFactory(new PropertyValueFactory("product_id"));
            observableList.get(1).setCellValueFactory(new PropertyValueFactory("product_name"));
            observableList.get(2).setCellValueFactory(new PropertyValueFactory("product_p1"));
            observableList.get(3).setCellValueFactory(new PropertyValueFactory("product_p2"));
            observableList.get(4).setCellValueFactory(new PropertyValueFactory("product_p3"));
            observableList.get(5).setCellValueFactory(new PropertyValueFactory("product_bzq"));

            finished_product_table.setItems(data);

            finished_product_panel.toFront();
        }

        if(actionBtn==product_name_btn1)
        {
            final ObservableList<Product> data = FXCollections.observableArrayList();
            try
            {
                Product[] products=searchSection.SearchProductByName(product_name_text1.getText());
                if(products.length==0)
                {
                    staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "不存在");
                    return;
                }
                for(int i=0;i<products.length;i++)
                {
                    data.add(products[i]);
                }
            }
            catch(SQLException ex)
            {
                staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "系统错误");
                return;
            }
            ObservableList<TableColumn<Product, ?>> observableList = finished_product_table.getColumns();
            observableList.get(0).setCellValueFactory(new PropertyValueFactory("product_id"));
            observableList.get(1).setCellValueFactory(new PropertyValueFactory("product_name"));
            observableList.get(2).setCellValueFactory(new PropertyValueFactory("product_p1"));
            observableList.get(3).setCellValueFactory(new PropertyValueFactory("product_p2"));
            observableList.get(4).setCellValueFactory(new PropertyValueFactory("product_p3"));
            observableList.get(5).setCellValueFactory(new PropertyValueFactory("product_bzq"));

            finished_product_table.setItems(data);

            finished_product_panel.toFront();
        }

        if(actionBtn==btnSearchDth)
        {
            final ObservableList<Pick> data = FXCollections.observableArrayList();
            try
            {
                Pick[] picks=searchSection.SearchAllPick();
                if(picks.length==0)
                {
                    staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "不存在");
                    return;
                }
                for(int i=0;i<picks.length;i++)
                {
                    data.add(picks[i]);
                }
            }
            catch(SQLException ex)
            {
                staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "系统错误");
                return;
            }
            ObservableList<TableColumn<Pick, ?>> observableList = pick_table.getColumns();
            observableList.get(0).setCellValueFactory(new PropertyValueFactory("order_id"));
            observableList.get(1).setCellValueFactory(new PropertyValueFactory("order_type"));
            observableList.get(2).setCellValueFactory(new PropertyValueFactory("custom_id"));
            observableList.get(3).setCellValueFactory(new PropertyValueFactory("custom_name"));
            observableList.get(4).setCellValueFactory(new PropertyValueFactory("custom_tele"));
            observableList.get(5).setCellValueFactory(new PropertyValueFactory("order_date"));

            pick_table.setItems(data);

            pick_panel.toFront();
        }

        if(actionBtn==orderid_btn1)
        {
            final ObservableList<Pick> data = FXCollections.observableArrayList();
            try
            {
                Pick[] picks=searchSection.SearchPickByOrderId(orderid_text1.getText());
                if(picks.length==0)
                {
                    staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "不存在");
                    return;
                }
                for(int i=0;i<picks.length;i++)
                {
                    data.add(picks[i]);
                }
            }
            catch(SQLException ex)
            {
                staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "系统错误");
                return;
            }
            ObservableList<TableColumn<Pick, ?>> observableList = pick_table.getColumns();
            observableList.get(0).setCellValueFactory(new PropertyValueFactory("order_id"));
            observableList.get(1).setCellValueFactory(new PropertyValueFactory("order_type"));
            observableList.get(2).setCellValueFactory(new PropertyValueFactory("custom_id"));
            observableList.get(3).setCellValueFactory(new PropertyValueFactory("custom_name"));
            observableList.get(4).setCellValueFactory(new PropertyValueFactory("custom_tele"));
            observableList.get(5).setCellValueFactory(new PropertyValueFactory("order_date"));

            pick_table.setItems(data);

            pick_panel.toFront();
        }

        if(actionBtn==customid_btn1)
        {
            final ObservableList<Pick> data = FXCollections.observableArrayList();
            try
            {
                Pick[] picks=searchSection.SearchPickByCustomId(customid_text1.getText());
                if(picks.length==0)
                {
                    staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "不存在");
                    return;
                }
                for(int i=0;i<picks.length;i++)
                {
                    data.add(picks[i]);
                }
            }
            catch(SQLException ex)
            {
                staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "系统错误");
                return;
            }
            ObservableList<TableColumn<Pick, ?>> observableList = pick_table.getColumns();
            observableList.get(0).setCellValueFactory(new PropertyValueFactory("order_id"));
            observableList.get(1).setCellValueFactory(new PropertyValueFactory("order_type"));
            observableList.get(2).setCellValueFactory(new PropertyValueFactory("custom_id"));
            observableList.get(3).setCellValueFactory(new PropertyValueFactory("custom_name"));
            observableList.get(4).setCellValueFactory(new PropertyValueFactory("custom_tele"));
            observableList.get(5).setCellValueFactory(new PropertyValueFactory("order_date"));

            pick_table.setItems(data);

            pick_panel.toFront();
        }

        if(actionBtn==btnSearchCj)
        {
            final ObservableList<WorkShop> data = FXCollections.observableArrayList();
            try
            {
                WorkShop[] workShops=searchSection.SearchAllWorkShop();
                if(workShops.length==0)
                {
                    staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "不存在");
                    return;
                }
                for(int i=0;i<workShops.length;i++)
                {
                    data.add(workShops[i]);
                }
            }
            catch(SQLException ex)
            {
                staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "系统错误");
                return;
            }
            ObservableList<TableColumn<WorkShop, ?>> observableList = chejian_table.getColumns();
            observableList.get(0).setCellValueFactory(new PropertyValueFactory("cj_id"));
            observableList.get(1).setCellValueFactory(new PropertyValueFactory("cj_fzr"));
            observableList.get(2).setCellValueFactory(new PropertyValueFactory("cj_productid"));
            observableList.get(3).setCellValueFactory(new PropertyValueFactory("product_name"));
            observableList.get(4).setCellValueFactory(new PropertyValueFactory("cj_num"));

            chejian_table.setItems(data);

            chejian_panel.toFront();
        }

        if(actionBtn==chejian_cj_id_btn)
        {
            final ObservableList<WorkShop> data = FXCollections.observableArrayList();
            try
            {
                WorkShop[] workShops=searchSection.SearchWorkShopByCheJianId(chejian_cj_id_text.getText());
                if(workShops.length==0)
                {
                    staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "不存在");
                    return;
                }
                for(int i=0;i<workShops.length;i++)
                {
                    data.add(workShops[i]);
                }
            }
            catch(SQLException ex)
            {
                staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "系统错误");
                return;
            }
            ObservableList<TableColumn<WorkShop, ?>> observableList = chejian_table.getColumns();
            observableList.get(0).setCellValueFactory(new PropertyValueFactory("cj_id"));
            observableList.get(1).setCellValueFactory(new PropertyValueFactory("cj_fzr"));
            observableList.get(2).setCellValueFactory(new PropertyValueFactory("cj_productid"));
            observableList.get(3).setCellValueFactory(new PropertyValueFactory("product_name"));
            observableList.get(4).setCellValueFactory(new PropertyValueFactory("cj_num"));

            chejian_table.setItems(data);

            chejian_panel.toFront();
        }

        if(actionBtn==chejian_cp_id_btn)
        {
            final ObservableList<WorkShop> data = FXCollections.observableArrayList();
            try
            {
                WorkShop[] workShops=searchSection.SearchWorkShopByProductId(chejian_cp_id_text.getText());
                if(workShops.length==0)
                {
                    staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "不存在");
                    return;
                }
                for(int i=0;i<workShops.length;i++)
                {
                    data.add(workShops[i]);
                }
            }
            catch(SQLException ex)
            {
                staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "系统错误");
                return;
            }
            ObservableList<TableColumn<WorkShop, ?>> observableList = chejian_table.getColumns();
            observableList.get(0).setCellValueFactory(new PropertyValueFactory("cj_id"));
            observableList.get(1).setCellValueFactory(new PropertyValueFactory("cj_fzr"));
            observableList.get(2).setCellValueFactory(new PropertyValueFactory("cj_productid"));
            observableList.get(3).setCellValueFactory(new PropertyValueFactory("product_name"));
            observableList.get(4).setCellValueFactory(new PropertyValueFactory("cj_num"));

            chejian_table.setItems(data);

            chejian_panel.toFront();
        }
    }

    public void SetInform()
    {

    }

    public void handleName(MouseEvent event)
    {
        if(event.getSource()==searchIdN)
        {
            final ObservableList<ProductSales> data = FXCollections.observableArrayList();
            try
            {
                ProductSales[] sales=searchSection.SearchSalesById(bhN.getText());
                if(sales.length==0)
                {
                    staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "此产品编号不存在");
                    return;
                }
                for(int i=0;i<sales.length;i++)
                {
                    data.add(sales[i]);
                }
            }
            catch(SQLException ex)
            {
                staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "系统错误");
                return;
            }
            ObservableList<TableColumn<ProductSales, ?>> observableList = nameTable.getColumns();
            observableList.get(0).setCellValueFactory(new PropertyValueFactory("product_name"));
            observableList.get(1).setCellValueFactory(new PropertyValueFactory("product_id"));
            observableList.get(2).setCellValueFactory(new PropertyValueFactory("product_sales"));
            observableList.get(3).setCellValueFactory(new PropertyValueFactory("product_p1"));
            observableList.get(4).setCellValueFactory(new PropertyValueFactory("product_p2"));
            observableList.get(5).setCellValueFactory(new PropertyValueFactory("product_p3"));
            nameTable.setItems(data);
        }
        if(event.getSource()==saerchName)
        {
            final ObservableList<ProductSales> data = FXCollections.observableArrayList();
            try
            {
                ProductSales[] sales=searchSection.SearchSalesByName(name.getText());
                if(sales.length==0)
                {
                    staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "此产品名称不存在");
                    return;
                }
                for(int i=0;i<sales.length;i++)
                {
                    data.add(sales[i]);
                }
            }
            catch(SQLException ex)
            {
                staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "系统错误");
                return;
            }
            ObservableList<TableColumn<ProductSales, ?>> observableList = nameTable.getColumns();
            observableList.get(0).setCellValueFactory(new PropertyValueFactory("product_name"));
            observableList.get(1).setCellValueFactory(new PropertyValueFactory("product_id"));
            observableList.get(2).setCellValueFactory(new PropertyValueFactory("product_sales"));
            observableList.get(3).setCellValueFactory(new PropertyValueFactory("product_p1"));
            observableList.get(4).setCellValueFactory(new PropertyValueFactory("product_p2"));
            observableList.get(5).setCellValueFactory(new PropertyValueFactory("product_p3"));
            nameTable.setItems(data);
        }

        if(event.getSource()==btnSearchKh)
        {

        }
    }

    public void handleDate(MouseEvent event)
    {

        JFXButton activeBtn = (JFXButton)event.getSource();
        if(activeBtn == this.searchDate)
        {
            final ObservableList<OneOrderRecord> data = FXCollections.observableArrayList();
            try
            {
                OneOrderRecord[] orders=searchSection.SearchOrdersByDate(datePick.getStartDate().toString(),datePick.getEndDate().toString());
                if(orders.length==0)
                {
                    staff.showAlert(Alert.AlertType.INFORMATION, "提示", "查询提示", "此时间段销售量为零");
                    return;
                }
                for(int i=0;i<orders.length;i++)
                {
                    data.add(orders[i]);
                }
            }
            catch(SQLException ex)
            {
                staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "系统错误");
                return;
            }
            ObservableList<TableColumn<OneOrderRecord, ?>> observableList = dateTable.getColumns();
            observableList.get(0).setCellValueFactory(new PropertyValueFactory("order_id"));
            observableList.get(1).setCellValueFactory(new PropertyValueFactory("order_date"));
            observableList.get(2).setCellValueFactory(new PropertyValueFactory("order_product"));
            observableList.get(3).setCellValueFactory(new PropertyValueFactory("order_num"));
            observableList.get(4).setCellValueFactory(new PropertyValueFactory("order_zt"));
            observableList.get(5).setCellValueFactory(new PropertyValueFactory("order_custom"));
            observableList.get(6).setCellValueFactory(new PropertyValueFactory("order_type"));
            observableList.get(7).setCellValueFactory(new PropertyValueFactory("order_fzr"));
            dateTable.setItems(data);
        }
        else if(activeBtn == this.searchIdD)
        {
            final ObservableList<OneOrderRecord> data = FXCollections.observableArrayList();
            try
            {
                OneOrderRecord[] orders=searchSection.SearchOrdersById(bhD.getText());
                if(orders.length==0)
                {
                    staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "此订单编号不存在");
                    return;
                }
                for(int i=0;i<orders.length;i++)
                {
                    data.add(orders[i]);
                }
            }
            catch(SQLException ex)
            {
                staff.showAlert(Alert.AlertType.ERROR, "错误", "查询错误", "系统错误");
                return;
            }
            ObservableList<TableColumn<OneOrderRecord, ?>> observableList = dateTable.getColumns();
            observableList.get(0).setCellValueFactory(new PropertyValueFactory("order_id"));
            observableList.get(1).setCellValueFactory(new PropertyValueFactory("order_date"));
            observableList.get(2).setCellValueFactory(new PropertyValueFactory("order_product"));
            observableList.get(3).setCellValueFactory(new PropertyValueFactory("order_num"));
            observableList.get(4).setCellValueFactory(new PropertyValueFactory("order_zt"));
            observableList.get(5).setCellValueFactory(new PropertyValueFactory("order_custom"));
            observableList.get(6).setCellValueFactory(new PropertyValueFactory("order_type"));
            observableList.get(7).setCellValueFactory(new PropertyValueFactory("order_fzr"));
            dateTable.setItems(data);
        }
    }

    public void dosearchDate(Date date1, Date date2)
    {

    }

    public void dosearchName(String name)
    {

    }

    public void dosearchId(String id)
    {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }

    public void initData(Staff staff)
    {
        this.staff = staff;
        this.searchSection=new SearchSection(staff);
        if(staff.zw[0][1] != 1)
        {
            if(staff.zw[1][1] != 1)
                this.btnCw.setDisable(true);
            if(staff.zw[2][1] != 1)
                this.btnXs.setDisable(true);
            if(staff.zw[3][1] != 1)
                this.btnCp.setDisable(true);
            if(staff.zw[4][1] != 1)
                this.btnYl.setDisable(true);
            if(staff.zw[5][1] != 1)
                this.btnCj.setDisable(true);
            if(staff.zw[6][1] != 1)
                this.btnJh.setDisable(true);
        }
    }

    @FXML
    void initialize() {
        assert dateTable != null : "fx:id=\"dateTable\" was not injected: check your FXML file 'Section.fxml'.";
        assert bhN != null : "fx:id=\"bhN\" was not injected: check your FXML file 'Section.fxml'.";
        assert saerchName != null : "fx:id=\"saerchName\" was not injected: check your FXML file 'Section.fxml'.";
        assert CustomTable != null : "fx:id=\"CustomTable\" was not injected: check your FXML file 'Section.fxml'.";
        assert PaneCustom != null : "fx:id=\"PaneCustom\" was not injected: check your FXML file 'Section.fxml'.";
        assert cust_id_text != null : "fx:id=\"cust_id_text\" was not injected: check your FXML file 'Section.fxml'.";
        assert cust_id_btn != null : "fx:id=\"cust_id_btn\" was not injected: check your FXML file 'Section.fxml'.";
        assert cust_tele_text != null : "fx:id=\"cust_tele_text\" was not injected: check your FXML file 'Section.fxml'.";
        assert cust_tele_btn != null : "fx:id=\"cust_tele_btn\" was not injected: check your FXML file 'Section.fxml'.";
        assert product_kc != null : "fx:id=\"product_kc\" was not injected: check your FXML file 'Section.fxml'.";
        assert kc_table != null : "fx:id=\"kc_table\" was not injected: check your FXML file 'Section.fxml'.";
        assert kc_name_text != null : "fx:id=\"kc_name_text\" was not injected: check your FXML file 'Section.fxml'.";
        assert kc_ck_id_text != null : "fx:id=\"kc_ck_id_text\" was not injected: check your FXML file 'Section.fxml'.";
        assert kc_name_btn != null : "fx:id=\"kc_name_btn\" was not injected: check your FXML file 'Section.fxml'.";
        assert kc_ck_id_btn != null : "fx:id=\"kc_ck_id_btn\" was not injected: check your FXML file 'Section.fxml'.";
        assert raw1_panel != null : "fx:id=\"raw1_panel\" was not injected: check your FXML file 'Section.fxml'.";
        assert raw1_table != null : "fx:id=\"raw1_table\" was not injected: check your FXML file 'Section.fxml'.";
        assert raw_id_text != null : "fx:id=\"raw_id_text\" was not injected: check your FXML file 'Section.fxml'.";
        assert rew_id_btn != null : "fx:id=\"rew_id_btn\" was not injected: check your FXML file 'Section.fxml'.";
        assert rew_name_text != null : "fx:id=\"rew_name_text\" was not injected: check your FXML file 'Section.fxml'.";
        assert rew_name_btn != null : "fx:id=\"rew_name_btn\" was not injected: check your FXML file 'Section.fxml'.";
        assert raw2_panel != null : "fx:id=\"raw2_panel\" was not injected: check your FXML file 'Section.fxml'.";
        assert raw2_table != null : "fx:id=\"raw2_table\" was not injected: check your FXML file 'Section.fxml'.";
        assert yl_kc_id_text != null : "fx:id=\"yl_kc_id_text\" was not injected: check your FXML file 'Section.fxml'.";
        assert yl_kc_id_btn != null : "fx:id=\"yl_kc_id_btn\" was not injected: check your FXML file 'Section.fxml'.";
        assert yl_kc_ck_text != null : "fx:id=\"yl_kc_ck_text\" was not injected: check your FXML file 'Section.fxml'.";
        assert yl_kc_ck_btn != null : "fx:id=\"yl_kc_ck_btn\" was not injected: check your FXML file 'Section.fxml'.";
        assert yljl_panel != null : "fx:id=\"yljl_panel\" was not injected: check your FXML file 'Section.fxml'.";
        assert yljl_table != null : "fx:id=\"yljl_table\" was not injected: check your FXML file 'Section.fxml'.";
        assert yl_cz_date_view != null : "fx:id=\"yl_cz_date_view\" was not injected: check your FXML file 'Section.fxml'.";
        assert yl_cz_date_btn != null : "fx:id=\"yl_cz_date_btn\" was not injected: check your FXML file 'Section.fxml'.";
        assert yl_cz_id_text != null : "fx:id=\"yl_cz_id_text\" was not injected: check your FXML file 'Section.fxml'.";
        assert yl_cz_id_btn != null : "fx:id=\"yl_cz_id_btn\" was not injected: check your FXML file 'Section.fxml'.";
        assert unpaid_panel != null : "fx:id=\"unpaid_panel\" was not injected: check your FXML file 'Section.fxml'.";
        assert unpaid_table != null : "fx:id=\"unpaid_table\" was not injected: check your FXML file 'Section.fxml'.";
        assert unpaid_dateview != null : "fx:id=\"unpaid_dateview\" was not injected: check your FXML file 'Section.fxml'.";
        assert unpaid_date_btn != null : "fx:id=\"unpaid_date_btn\" was not injected: check your FXML file 'Section.fxml'.";
        assert unpaid_ddid_text != null : "fx:id=\"unpaid_ddid_text\" was not injected: check your FXML file 'Section.fxml'.";
        assert unpaid_ddid_btn != null : "fx:id=\"unpaid_ddid_btn\" was not injected: check your FXML file 'Section.fxml'.";
        assert unpaid_khid_text != null : "fx:id=\"unpaid_khid_text\" was not injected: check your FXML file 'Section.fxml'.";
        assert unpaid_khid_btn != null : "fx:id=\"unpaid_khid_btn\" was not injected: check your FXML file 'Section.fxml'.";
        assert refund_pane != null : "fx:id=\"refund_pane\" was not injected: check your FXML file 'Section.fxml'.";
        assert refund_table != null : "fx:id=\"refund_table\" was not injected: check your FXML file 'Section.fxml'.";
        assert refund_ddid_text != null : "fx:id=\"refund_ddid_text\" was not injected: check your FXML file 'Section.fxml'.";
        assert refund_ddid_btn != null : "fx:id=\"refund_ddid_btn\" was not injected: check your FXML file 'Section.fxml'.";
        assert refund_khid_text != null : "fx:id=\"refund_khid_text\" was not injected: check your FXML file 'Section.fxml'.";
        assert refund_khid_btn != null : "fx:id=\"refund_khid_btn\" was not injected: check your FXML file 'Section.fxml'.";
        assert refund_dateview != null : "fx:id=\"refund_dateview\" was not injected: check your FXML file 'Section.fxml'.";
        assert refund_dateview_btn != null : "fx:id=\"refund_dateview_btn\" was not injected: check your FXML file 'Section.fxml'.";
        assert finance_pane != null : "fx:id=\"finance_pane\" was not injected: check your FXML file 'Section.fxml'.";
        assert finance_table != null : "fx:id=\"finance_table\" was not injected: check your FXML file 'Section.fxml'.";
        assert finance_dateview != null : "fx:id=\"finance_dateview\" was not injected: check your FXML file 'Section.fxml'.";
        assert finance_dateview_btn != null : "fx:id=\"finance_dateview_btn\" was not injected: check your FXML file 'Section.fxml'.";
        assert finance_inf_text != null : "fx:id=\"finance_inf_text\" was not injected: check your FXML file 'Section.fxml'.";
        assert finance_inf_btn != null : "fx:id=\"finance_inf_btn\" was not injected: check your FXML file 'Section.fxml'.";
        assert project_panel != null : "fx:id=\"project_panel\" was not injected: check your FXML file 'Section.fxml'.";
        assert project_table != null : "fx:id=\"project_table\" was not injected: check your FXML file 'Section.fxml'.";
        assert cangku_panel != null : "fx:id=\"cangku_panel\" was not injected: check your FXML file 'Section.fxml'.";
        assert cangku_table != null : "fx:id=\"cangku_table\" was not injected: check your FXML file 'Section.fxml'.";
        assert project_dateview1 != null : "fx:id=\"project_dateview1\" was not injected: check your FXML file 'Section.fxml'.";
        assert project_btn1 != null : "fx:id=\"project_btn1\" was not injected: check your FXML file 'Section.fxml'.";
        assert project_cp_id_text != null : "fx:id=\"project_cp_id_text\" was not injected: check your FXML file 'Section.fxml'.";
        assert project_cp_id_btn != null : "fx:id=\"project_cp_id_btn\" was not injected: check your FXML file 'Section.fxml'.";
        assert finished_product_panel != null : "fx:id=\"finished_product_panel\" was not injected: check your FXML file 'Section.fxml'.";
        assert finished_product_table != null : "fx:id=\"finished_product_table\" was not injected: check your FXML file 'Section.fxml'.";
        assert product_id_text1 != null : "fx:id=\"product_id_text1\" was not injected: check your FXML file 'Section.fxml'.";
        assert product_id_btn1 != null : "fx:id=\"product_id_btn1\" was not injected: check your FXML file 'Section.fxml'.";
        assert product_name_text1 != null : "fx:id=\"product_name_text1\" was not injected: check your FXML file 'Section.fxml'.";
        assert product_name_btn1 != null : "fx:id=\"product_name_btn1\" was not injected: check your FXML file 'Section.fxml'.";
        assert orderid_text1 != null : "fx:id=\"orderid_text1\" was not injected: check your FXML file 'Section.fxml'.";
        assert orderid_btn1 != null : "fx:id=\"orderid_btn1\" was not injected: check your FXML file 'Section.fxml'.";
        assert customid_text1 != null : "fx:id=\"customid_text1\" was not injected: check your FXML file 'Section.fxml'.";
        assert customid_btn1 != null : "fx:id=\"customid_btn1\" was not injected: check your FXML file 'Section.fxml'.";
        assert chejian_panel != null : "fx:id=\"chejian_panel\" was not injected: check your FXML file 'Section.fxml'.";
        assert chejian_table != null : "fx:id=\"chejian_table\" was not injected: check your FXML file 'Section.fxml'.";
        assert chejian_cj_id_text != null : "fx:id=\"chejian_cj_id_text\" was not injected: check your FXML file 'Section.fxml'.";
        assert chejian_cj_id_btn != null : "fx:id=\"chejian_cj_id_btn\" was not injected: check your FXML file 'Section.fxml'.";
        assert chejian_cp_id_text != null : "fx:id=\"chejian_cp_id_text\" was not injected: check your FXML file 'Section.fxml'.";
        assert chejian_cp_id_btn != null : "fx:id=\"chejian_cp_id_btn\" was not injected: check your FXML file 'Section.fxml'.";
    }
}
