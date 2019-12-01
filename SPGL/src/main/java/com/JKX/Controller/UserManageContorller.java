package com.JKX.Controller;

import com.JKX.Controller.ItemController.StaffInformController;
import com.JKX.Model.Staff;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.log.NullLogger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.naming.directory.SearchControls;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserManageContorller implements Initializable {

    private Staff staff;
    private String[] zw = {"系统", "财务部", "销售部", "成品库", "原料库", "生产车间", "生产计划部"};
    private String preUid, preBm, preZw;

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
    private JFXTextField uidText, sfzText, nameText;
    @FXML
    private JFXComboBox<String> zwText, bmText, sexText;
    @FXML
    private VBox v2;

    /*业务实现组件-人员添加*/
    @FXML
    private Button addUser;
    @FXML
    private JFXTextField uidAddText, nameAddText, sfzAddText;
    @FXML
    private JFXCheckBox glyCheck, glzCheck;
    @FXML
    private JFXComboBox<String> sectionCombox, sexAddText;
    @FXML
    private JFXPasswordField psw, pswConfir;
    @FXML
    private Label pswConfirTip1, pswConfirTip2;

    @FXML
    private Pane pnlChange, pnlAdd, pnlSearch, paneEmpty;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

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
        this.sexText.setValue(sex);
        this.zwText.setValue(zw);
        this.bmText.setValue(bm);
        this.preUid = uid;
        this.preBm = bm;
        this.preZw = zw;
    }

    public void handleSearch(MouseEvent mouseEvent) throws IOException
    {
        SearchUser(this.uidSearch, this.pnlInform);
    }

    public void SearchUser(TextField textField, VBox vBox) throws IOException
    {
        vBox.getChildren().clear();
        try {
            String[][] ans = this.getInform(textField.getText());
            for(int i = 1; i < ans.length; i++)
            {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/StaffInform.fxml"));
                Node node = loader.load();
                StaffInformController staffInformController = loader.<StaffInformController>getController();
                staffInformController.setInform(ans[i][0], ans[i][1], ans[i][2], ans[i][3],ans[i][4], ans[i][5], ans[i][6]);
                staffInformController.setContorller(this);

                vBox.getChildren().add(node);
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
            try {
                String[] a = {"string", "string", "string", "string", "string", "string", "string", "string"};
                String[] b = {this.preUid, this.preBm, this.preZw,this.bmText.getValue(), this.zwText.getValue(), this.nameText.getText(), this.sexText.getValue(), this.sfzText.getText()};
                int nums = staff.ExcuteDoes("Call Staff_Update(?, ?, ?, ?, ?, ?, ?, ?)", a, b);
                if(nums == 1) {
                    System.out.println("删除成功！");
                    SearchUser(this.textInform, this.v2);
                }
            }
            catch (SQLException se)
            {
                se.printStackTrace();
            }
        }
        else if(actionButton == btnDelete)
        {
            try {
                String[] a = {"string", "string", "string"};
                String[] b = {this.uidText.getText(), this.bmText.getValue(), this.zwText.getValue()};
                int nums = staff.ExcuteDoes("Call Delete_Staff(?, ?, ?)", a, b);
                if(nums == 1) {
                    System.out.println("删除成功！");
                    SearchUser(this.textInform, this.v2);
                }
             }
            catch (SQLException se)
            {
                se.printStackTrace();
            }
        }
        else if(actionButton == btnSearch)
        {
            SearchUser(this.textInform, this.v2);
        }
    }

    public void handleAdd(MouseEvent mouseEvent) throws IOException, SQLException
    {
        if(this.uidAddText.getText().equals("") || this.nameAddText.getText().equals("") || this.sexAddText.getValue().equals("null") || this.sfzAddText.getText().equals("") || this.sectionCombox.getItems().equals("")|| this.psw.getText().equals(""))
        {
            Alert _alert = new Alert(Alert.AlertType.INFORMATION);
            _alert.setTitle("提示");
            _alert.setHeaderText("添加失败");
            _alert.setContentText("请填写完整信息！");
            _alert.showAndWait();
        }
        else
        {
            String bmm;
            if(this.glzCheck.isSelected())
                bmm = this.glzCheck.getText();
            else
                bmm = this.glyCheck.getText();
            String[] a = {"string", "string", "string", "string", "string", "string", "string"};
            String[] b = {this.uidAddText.getText(), this.sectionCombox.getValue(), bmm, this.nameAddText.getText(), this.sexAddText.getValue(), this.sfzAddText.getText(), this.psw.getText()};
            String[] c = {"int"};
            String[] ans = staff.ExcuteDoesReturn("CALL Staff_Add(?, ?, ?, ?, ?, ?, ?, ?)",a, b, c);
            System.out.println(ans[0]);
            if(ans[0].equals("1")) {
                //this.addLogin();
                this.clearAdd();
                System.out.println("添加成功！");
            }
            else if(ans[0].equals("0"))
                System.out.println("添加失败！");
        }
    }

    public void clearAdd()
    {
        this.uidAddText.clear();
        this.nameAddText.clear();
        this.sexAddText.setValue("");
        this.sectionCombox.setValue("");
        this.glyCheck.setDisable(false);
        this.glyCheck.setDisable(true);
        this.glyCheck.setSelected(true);
        this.glzCheck.setSelected(false);
        this.sfzAddText.clear();
        this.psw.clear();
        this.pswConfir.clear();
    }

    public void addLogin(String uid, String psw, String bm, String zw)
    {
        //先给他建立一个登录名
        //根据业务人员+部门赋权限
        //根据管理员+部门赋权限
        //根据管理者+部门赋权限
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
                    inform += "'" + zw[i] + "'";
                    flag = !flag;
                }
                else if(staff.zw[i][2] == 1)
                {
                    inform += ",'";
                    inform += zw[i] + "'";
                }
            }
            if(info.equals(""))
                ans = staff.Search("select staff.staff_id, staff_bm, staff_zw, staff_name, staff_sfz, staff_sex, staff_date" +
                                        " from staff, staff_job" +
                                        " where staff.staff_id = staff_job.staff_id and staff_zw in (" + inform + ") and  in ('业务人员', '员工')" +
                                        " group by staff.staff_id, staff_bm, staff_zw, staff_name, staff_sfz, staff_sex, staff_date");
            else
                ans = staff.Search("select staff.staff_id, staff_bm, staff_zw, staff_name, staff_sfz, staff_sex, staff_date" +
                                        " from staff, staff_job" +
                                        " where staff.staff_id = '" + info + "' and staff.staff_id = staff_job.staff_id and staff_zw in (" + inform + ") and staff_zw in ('业务人员', '员工')" +
                                        " group by staff.staff_id, staff_bm, staff_zw, staff_name, staff_sfz, staff_sex, staff_date");
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
        List<String> staffSex = new ArrayList<String>();
        staffSex.add("男");
        staffSex.add("女");
        if(staff.zw[0][2] == 1)
        {
            for(int i = 1; i < 7; i++)
                staffBm.add(zw[i]);
            staffZw.add("管理员");
            staffZw.add("管理者");
        }
        else
        {
            staffZw.add("业务人员");
            staffZw.add("普通员工");
            this.glyCheck.setText("业务人员");
            this.glzCheck.setText("普通员工");
            for(int i = 1; i < 7; i++)
            {
                if(staff.zw[i][2] == 1)
                    staffBm.add(zw[i]);
            }
            staffZw.add("业务人员");
        }
        this.bmText.getItems().addAll(staffBm);
        this.zwText.getItems().addAll(staffZw);
        this.sexText.getItems().addAll(staffSex);
        this.sexAddText.getItems().addAll(staffSex);
        this.sectionCombox.getItems().addAll(staffBm);
        this.uidAddText.textProperty().addListener((observable, oldValue, newValue)-> {
                String trimed = newValue.trim();
                if (trimed.length() > 0) {
                    this.handleAddUid(trimed);
                }
        });
        this.pswConfir.textProperty().addListener((observable, oldValue, newValue)-> {
            String trimed = newValue.trim();
            if (trimed.length() > 0) {
                this.handleCheckPSW(trimed);
            }
        });
    }

    public void handleCheck(MouseEvent mouseEvent) {
        JFXCheckBox action = (JFXCheckBox) mouseEvent.getSource();
        if(action == this.glzCheck)
        {
            if(this.glzCheck.isSelected())
            {
                this.glyCheck.setDisable(false);
                this.glyCheck.setSelected(false);
                this.glzCheck.setDisable(true);
            }
        }
        else
        {
            if(this.glyCheck.isSelected())
            {
                this.glzCheck.setDisable(false);
                this.glzCheck.setSelected(false);
                this.glyCheck.setDisable(true);
            }
        }
    }

    public void handleAddUid(String search)
    {
        this.nameAddText.clear();
        this.sexAddText.setValue("");
        this.sfzAddText.clear();
        this.psw.clear();
        this.pswConfir.clear();
        try {
            String[][] ans = staff.Search("select * from staff where staff_id = '" + search + "';");
            if(ans.length == 2)
            {
                this.nameAddText.setDisable(true);
                this.sexAddText.setDisable(true);
                this.sfzAddText.setDisable(true);
                this.psw.setDisable(true);
                this.pswConfir.setDisable(true);
                this.nameAddText.setText(ans[1][1]);
                this.sexAddText.setValue(ans[1][2]);
                this.sfzAddText.setText(ans[1][3]);
                this.psw.setText(ans[1][5]);
                this.pswConfir.setText(ans[1][5]);
            }
            else
            {
                this.nameAddText.setDisable(false);
                this.sexAddText.setDisable(false);
                this.sfzAddText.setDisable(false);
                this.psw.setDisable(false);
                this.pswConfir.setDisable(false);
            }
        }
        catch (SQLException se)
        {
            se.printStackTrace();
        }
    }

    public void handleCheckPSW(String search)
    {
        if(!search.equals(this.psw.getText()))
        {
            this.pswConfirTip2.setVisible(false);
            this.pswConfirTip1.setVisible(true);
            this.addUser.setDisable(true);
        }
        else
        {
            this.pswConfirTip1.setVisible(false);
            this.pswConfirTip2.setVisible(true);
            this.addUser.setDisable(false);
        }
    }
}
