package com.JKX.Controller;

import com.JKX.Model.Staff;
import com.calendarfx.view.print.TimeRangeView;
import com.jfoenix.controls.JFXButton;
import com.sun.javaws.util.JfxHelper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class SectionController implements Initializable {

    public Staff staff;

    private int activePage = -1;

    private String[] Table = {"cj", "ck", "customer", "customer_type", "finance", "order", "pick", "product", "product_ck", "product_rec", "project", "raw", "raw_ck", "raw_rec", "unpaid"};

    @FXML
    private Pane mainPane, xsPane, cwPane, cpkPane, ylkPane, sccjPane, scjhPane, emptyPane, pnlTime, pnlName;

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
    private TextField bhD;

    /*名称界面组件*/
    @FXML
    private JFXButton searchName, searchIdN;
    @FXML
    private TextField bnN, name;

    /*名称界面组件*/

    /*表格*/
    @FXML
    private TableView<String> nameTable, dateTable;

    @FXML
    public void handleClicks(MouseEvent event) throws IOException {
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
    }

    public void SetInform()
    {

    }

    public void handleName(MouseEvent event)
    {

    }

    public void handleDate(MouseEvent event)
    {

        JFXButton activeBtn = (JFXButton)event.getSource();
        if(activeBtn == this.searchDate)
        {

        }
        else if(activeBtn == this.searchIdD)
        {

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
}
