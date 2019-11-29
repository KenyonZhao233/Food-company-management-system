package com.JKX.Controller;

import com.JKX.Controller.ItemController.StaffInformController;
import com.JKX.Model.Staff;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserManageContorller implements Initializable {

    private Staff staff;
    private String[] zw = {"'系统'", "'财务部'", "'销售部'", "'成品库'", "'原料库'", "'生产车间'", "'生产计划部'"};

    @FXML
    private AnchorPane UserManagePane;

    /*界面跳转按钮*/
    @FXML
    private Button userChange, userAdd, userSearch, btnSignout;

    /*业务实现组件-人员查询*/
    @FXML
    private Button doSearch1;
    @FXML
    private TextField uidSearch;
    @FXML
    private VBox pnlInform;

    /*业务实现组件-人员修改*/
    @FXML
    private Button btnChange, btnDelete, btnSearch;
    @FXML
    private TextField textInform;
    @FXML
    private JFXTextField uidText, sexText, sfzText, nameText;
    @FXML
    private JFXComboBox<String> zwText, bmText;
    @FXML
    private VBox v2;

    /*业务实现组件-人员添加*/
    @FXML
    private Button addUser;
    @FXML
    private JFXTextField uidAddText, nameAddText, sexAddText, sfzAddText;
    @FXML
    private JFXCheckBox glyCheck, glzCheck;
    @FXML
    private JFXComboBox<String> sectionCombox;
    @FXML
    private JFXPasswordField psw, pswConfir;

    @FXML
    private Pane pnlChange, pnlAdd, pnlSearch, paneEmpty;

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    public void handleClicks(MouseEvent mouseEvent) throws IOException {
        Button actionBtn = (Button) mouseEvent.getSource();
        paneEmpty.toFront();
        if(actionBtn == userChange)
        {
            pnlChange.toFront();
        }
        if(actionBtn == userAdd)
        {
            pnlAdd.toFront();
        }
        if(actionBtn == userSearch)
        {
            pnlSearch.toFront();
        }
        if(actionBtn == btnSignout)
        {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/Lead.fxml"));

            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.setScene(new Scene((Parent) loader.load()));

            LeadContorller controller = loader.<LeadContorller>getController();

            controller.initData(staff);

            stage.show();

            Stage index = (Stage)UserManagePane.getScene().getWindow();
            index.close();
        }
    }

    public void setInform(String uid, String name, String bm, String zw, String sfz, String sex)
    {
        this.uidText.setText(uid);
        this.nameText.setText(name);
        this.sfzText.setText(sfz);
        this.sexText.setText(sex);
        this.zwText.setValue(zw);
        this.bmText.setValue(bm);
    }

    public void handleSearch(MouseEvent mouseEvent) throws IOException
    {
        this.pnlInform.getChildren().clear();
        try {
            String[][] ans = this.getInform(this.textInform.getText());
            for(int i = 1; i < ans.length; i++)
            {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/StaffInform.fxml"));
                Node node = loader.load();
                StaffInformController staffInformController = loader.<StaffInformController>getController();
                staffInformController.setInform(ans[i][0], ans[i][1], ans[i][2], ans[i][3],ans[i][4], ans[i][5], ans[i][6]);
                staffInformController.setContorller(this);

                this.pnlInform.getChildren().add(node);
            }
        }
        catch (SQLException se){
            se.printStackTrace();
        }
    }

    public void handleChange(MouseEvent mouseEvent) throws IOException
    {
        Button actionButton = (Button)mouseEvent.getSource();
        if(actionButton == btnChange)
        {
//            try {
//
//            }
//            catch (SQLException se)
//            {
//
//            }
        }
        else if(actionButton == btnDelete)
        {
//            try {
//
//            }
//            catch (SQLException se)
//            {
//
//            }
        }
        else if(actionButton == btnSearch)
        {
            this.v2.getChildren().clear();
            try {
                String[][] ans = this.getInform(this.textInform.getText());
                for(int i = 1; i < ans.length; i++)
                {
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/StaffInform.fxml"));
                    Node node = loader.load();
                    StaffInformController staffInformController = loader.<StaffInformController>getController();
                    staffInformController.setInform(ans[i][0], ans[i][1], ans[i][2], ans[i][3],ans[i][4], ans[i][5], ans[i][6]);
                    staffInformController.setContorller(this);

                    this.v2.getChildren().add(node);
                }
            }
            catch (SQLException se){
                se.printStackTrace();
            }
        }
    }

    public void handleAdd(MouseEvent mouseEvent) throws IOException
    {

    }

    public String[][] getInform(String info) throws SQLException
    {
        String[][] ans;
        if(staff.zw[0][2] == 1)
        {
            String[] b = {""};
            String[] a = {"string"};
            if(info.equals("")) {
                b[0] = "NULL";
                ans = staff.ExcuteSearch("Call GLY_Search(?)", a, b);
                 }
            else{
                b[0] = info;
                ans = staff.ExcuteSearch("Call GLY_Search(?)", a, b);
            }
        }
        else
        {
            String inform = "";
            boolean flag = true;
            for(int i = 1; i < 7; i++)
            {
                if(staff.zw[i][2] == 1 && flag)
                {
                    inform += zw[i];
                    flag = !flag;
                }
                else if(staff.zw[i][2] == 1)
                {
                    inform += ",";
                    inform += zw[i];
                }
            }
            if(info.equals(""))
                ans = staff.Search("select staff_id, staff_bm, staff_zw, staff_name, staff_sfz, staff_sex, staff_date" +
                                        " from staff" +
                                        " where staff_zw in (" + inform + ") and staff_zw = '业务人员'" +
                                        " group by staff_id, staff_bm, staff_zw, staff_name, staff_sfz, staff_sex, staff_date");
            else
                ans = staff.Search("select staff_id, staff_bm, staff_zw, staff_name, staff_sfz, staff_sex, staff_date" +
                                        " from staff" +
                                        " where staff.staff_id = '" + info + "' and staff_zw in (" + inform + ") and staff_zw = '业务人员'" +
                                        " group by staff_id, staff_bm, staff_zw, staff_name, staff_sfz, staff_sex, staff_date");
        }
        return ans;
    }

    public void handleCreate(MouseEvent mouseEvent) {

    }

    public void initData(Staff staff)
    {
        this.staff = staff;
        List<String> staffBm = new ArrayList<String>();
        List<String> staffZw = new ArrayList<String>();
        if(staff.zw[0][2] == 1)
        {
            for(int i = 1; i < 7; i++)
                staffBm.add(zw[i]);
            staffZw.add("管理员");
            staffZw.add("管理者");
        }
        else
        {
            this.glyCheck.setVisible(false);
            this.glzCheck.setVisible(false);
            for(int i = 1; i < 7; i++)
            {
                if(staff.zw[i][2] == 1)
                    staffBm.add(zw[i]);
            }
            staffZw.add("业务人员");
        }
        this.bmText.getItems().addAll(staffBm);
        this.zwText.getItems().addAll(staffZw);
        this.sectionCombox.getItems().addAll(staffBm);
    }
}
