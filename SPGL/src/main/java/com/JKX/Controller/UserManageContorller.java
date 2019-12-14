package com.JKX.Controller;

import com.JKX.Controller.ItemController.StaffInformController;
import com.JKX.Model.ManageSection;
import com.JKX.Model.Staff;
import com.jfoenix.controls.*;
import com.mysql.jdbc.log.NullLogger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
import java.util.Optional;
import java.util.ResourceBundle;

public class UserManageContorller implements Initializable {

    private String[] zw = {"系统", "财务部", "销售部", "成品库", "原料库", "生产车间", "生产计划部"};
    private String preUid, preBm, preZw;
    private ManageSection manageSection;

    @FXML
    private Label nameLable;

    @FXML
    private AnchorPane UserManagePane;

    @FXML
    private Label users;

    /*界面跳转按钮*/
    @FXML
    private Button userChange, userAdd, userSearch, roleGrant, btnSignout,roleCz;

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

    /*权限管理组件*/
    @FXML
    private Button searchRole;
    @FXML
    private TextField searchRoleText;
    @FXML
    private VBox vboxSale, vboxFinance, vboxPro, vboxRaw, vboxPlan;
    /*销售部权限*/
    @FXML
    private CheckBox zhuce, makedd, undodd, tuihuo;
    /*财务部权限*/
    @FXML
    private CheckBox sk, tk, zr, zc;
    /*成品库权限*/
    @FXML
    private CheckBox tzfh, sh, rk, xh;
    /*原料库权限*/
    @FXML
    private CheckBox rk1, ck1, xh1;
    /*生产计划部权限*/
    @FXML
    private CheckBox jhs, cps, yls;
    @FXML
    private Label Grantid, Grantname;

    @FXML
    private JFXButton changeRole;

    /*重置密码*/
    @FXML
    private Pane paneCz;
    @FXML
    private JFXButton resetPw;
    @FXML
    private TextField resetText;

    @FXML
    private Pane pnlChange, pnlAdd, pnlSearch, paneEmpty, paneGrand;

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
        if(actionBtn == roleGrant){
            paneGrand.toFront();
        }
        if(actionBtn == roleCz)
        {
            paneCz.toFront();
        }
        if(actionBtn == btnSignout)
        {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/Lead.fxml"));

            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.setScene(new Scene((Parent) loader.load()));

            LeadContorller controller = loader.<LeadContorller>getController();

            controller.initData(this.manageSection.getStaff());

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
                int nums = this.manageSection.Update_User(this.preUid, this.preBm, this.preZw,this.bmText.getValue(), this.zwText.getValue(), this.nameText.getText(), this.sexText.getValue(), this.sfzText.getText());
                Staff.showAlert(Alert.AlertType.INFORMATION, "成功", "修改成功", "员工编号为：" + this.preUid);
                SearchUser(this.textInform, this.v2);
            }
            catch (SQLException se)
            {
                se.printStackTrace();
            }
        }
        else if(actionButton == btnDelete)
        {
            try {
                int nums = this.manageSection.Delete_User(this.uidText.getText(), this.bmText.getValue(), this.zwText.getValue());
                Staff.showAlert(Alert.AlertType.INFORMATION, "成功", "删除成功", "员工编号为：" + this.uidText.getText());
                SearchUser(this.textInform, this.v2);
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
        if(this.uidAddText.getText().equals("") || this.nameAddText.getText().equals("") || this.sexAddText.getItems().equals("") || this.sfzAddText.getText().equals("") || this.sectionCombox.getItems().equals("")|| this.psw.getText().equals(""))
        {
            Alert _alert = new Alert(Alert.AlertType.INFORMATION);
            _alert.setTitle("提示");
            _alert.setHeaderText("添加失败");
            _alert.setContentText("请填写完整信息！");
            _alert.showAndWait();
        }
        else
        {
            String bmm = "业务人员";
            if(this.manageSection.getStaff().zw[0][2] == 1) {
                if (this.glzCheck.isSelected())
                    bmm = this.glzCheck.getText();
                else
                    bmm = this.glyCheck.getText();
            }
            String[] ans = this.manageSection.Add_User(this.uidAddText.getText(), this.sectionCombox.getValue(), bmm, this.nameAddText.getText(), this.sexAddText.getValue(), this.sfzAddText.getText(), this.psw.getText());
            if(ans[0].equals("1")) {
                Staff.showAlert(Alert.AlertType.INFORMATION, "成功", "添加成功", "员工编号为：" + this.uidAddText.getText());
                this.clearAdd();
            }
            else if(ans[0].equals("0"))
                Staff.showAlert(Alert.AlertType.INFORMATION, "失败", "添加失败", "已存在该条信息");
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

    public String[][] getInform(String info) throws SQLException
    {
        String[][] ans;
        if(this.manageSection.getStaff().zw[0][2] == 1)
        {
            ans = this.manageSection.Search_Gly(info);
        }
        else
        {
            String inform = "";
            boolean flag = true;
            for(int i = 1; i < 7; i++)
            {
                if(this.manageSection.getStaff().zw[i][2] == 1 && flag)
                {
                    inform += "'" + zw[i] + "'";
                    flag = !flag;
                }
                else if(this.manageSection.getStaff().zw[i][2] == 1)
                {
                    inform += ",'";
                    inform += zw[i] + "'";
                }
            }
            ans = this.manageSection.Search_Other(info, inform);
        }
        return ans;
    }

    public void handleCreate(MouseEvent mouseEvent) {
        try {
            this.uidAddText.setText(this.manageSection.getStaffUid());
        }
       catch (SQLException se)
       {
           Staff.showAlert(Alert.AlertType.ERROR, "错误", "生成员工编号失败", "系统错误");
       }
    }

    public void initData(Staff staff)
    {
        this.nameLable.setText(staff.Name);
        this.manageSection = new ManageSection(staff);
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
            this.glyCheck.setVisible(false);
            this.glzCheck.setVisible(false);
            for(int i = 1; i < 7; i++)
            {
                if(staff.zw[i][2] == 1)
                    staffBm.add(zw[i]);
            }
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
        if(staff.zw[1][2] == 0)
            this.vboxFinance.setDisable(true);
        if(staff.zw[2][2] == 0)
            this.vboxSale.setDisable(true);
        if(staff.zw[3][2] == 0)
            this.vboxPro.setDisable(true);
        if(staff.zw[4][2] == 0)
            this.vboxRaw.setDisable(true);
        if(staff.zw[6][2] == 0)
            this.vboxPlan.setDisable(true);
        if(staff.zw[0][2] == 0)
            this.roleCz.setDisable(false);
        else
            this.roleGrant.setDisable(true);

        this.changeRole.setDisable(true);

        try {
            this.users.setText(String.valueOf(this.manageSection.getStaffs()));
        }
        catch (SQLException se)
        {
            Staff.showAlert(Alert.AlertType.ERROR, "错误", "获取员工数量失败", "");
        }
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
            String[][] ans = this.manageSection.Simple_Search(search);
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
            Staff.showAlert(Alert.AlertType.ERROR, "失败", "查询失败", "系统错误");
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

    public void handleSearchRole(MouseEvent mouseEvent) {
        if(this.searchRoleText.getText().isEmpty())
        {
            Staff.showAlert(Alert.AlertType.ERROR, "错误", "查询失败", "请输入员工编号");
        }
        else
        {
            try {
                String info = this.searchRoleText.getText();
                String inform = "";
                boolean flag = true;
                for(int i = 1; i < 7; i++)
                {
                    if(this.manageSection.getStaff().zw[i][2] == 1 && flag)
                    {
                        inform += "'" + zw[i] + "'";
                        flag = !flag;
                    }
                    else if(this.manageSection.getStaff().zw[i][2] == 1)
                    {
                        inform += ",'";
                        inform += zw[i] + "'";
                    }
                }
                String[][] ans = this.manageSection.Search_Other(info, inform);
                if(ans.length > 1)
                {
                    this.Grantid.setText(ans[1][0]);
                    this.Grantname.setText(ans[1][3]);
                    int[][] zw = this.manageSection.searchZw(ans[1][0]);
                    int[][] qx = this.manageSection.SearchQx(ans[1][0], zw);
                    this.confirmQx(qx);
                    this.changeRole.setDisable(false);
                }
                else{
                    Staff.showAlert(Alert.AlertType.ERROR, "错误", "查询失败", "未查询到该员工");
                }
            }
            catch (SQLException se)
            {
                se.printStackTrace();
            }
        }
    }

    public void confirmQx(int[][] qx)
    {
        this.clearQx();
        if(qx[2][0] == 1 && this.manageSection.getStaff().zw[2][2] == 1)
            this.vboxSale.setDisable(false);
        else
            this.vboxSale.setDisable(true);
        if(qx[2][1] == 1)
            this.zhuce.setSelected(true);
        if(qx[2][2] == 1)
            this.makedd.setSelected(true);
        if(qx[2][3] == 1)
            this.undodd.setSelected(true);
        if(qx[2][4] == 1)
            this.tuihuo.setSelected(true);

        if(qx[1][0] == 1 && this.manageSection.getStaff().zw[1][2] == 1)
            this.vboxFinance.setDisable(false);
        else
            this.vboxFinance.setDisable(true);
        /*财务部权限*/
        if(qx[1][1] == 1)
            this.sk.setSelected(true);
        if(qx[1][2] == 1)
            this.tk.setSelected(true);
        if(qx[1][3] == 1)
            this.zr.setSelected(true);
        if(qx[1][4] == 1)
            this.zc.setSelected(true);

        if(qx[3][0] == 1 && this.manageSection.getStaff().zw[3][2] == 1)
            this.vboxPro.setDisable(false);
        else
            this.vboxPro.setDisable(true);
        /*成品库权限*/
        if(qx[3][1] == 1)
            this.tzfh.setSelected(true);
        if(qx[3][2] == 1)
            this.sh.setSelected(true);
        if(qx[3][3] == 1)
            this.rk.setSelected(true);
        if(qx[3][4] == 1)
            this.xh.setSelected(true);

        if(qx[4][0] == 1 && this.manageSection.getStaff().zw[4][2] == 1)
            this.vboxRaw.setDisable(false);
        else
            this.vboxRaw.setDisable(true);
        /*原料库权限*/
        if(qx[4][1] == 1)
            this.rk1.setSelected(true);
        if(qx[4][2] == 1)
            this.ck1.setSelected(true);
        if(qx[4][3] == 1)
            this.xh1.setSelected(true);

        if(qx[5][0] == 1 && this.manageSection.getStaff().zw[6][2] == 1)
            this.vboxPlan.setDisable(false);
        else
            this.vboxPlan.setDisable(true);
        /*生产计划部权限*/
        if(qx[5][1] == 1)
            this.jhs.setSelected(true);
        if(qx[5][2] == 1)
            this.cps.setSelected(true);
        if(qx[5][3] == 1)
            this.yls.setSelected(true);
    }

    public void clearQx()
    {
        this.zhuce.setSelected(false);
        this.makedd.setSelected(false);
        this.undodd.setSelected(false);
        this.tuihuo.setSelected(false);
        /*财务部权限*/
        this.sk.setSelected(false);
        this.tk.setSelected(false);
        this.zr.setSelected(false);
        this.zc.setSelected(false);
        /*成品库权限*/
        this.tzfh.setSelected(false);
        this.sh.setSelected(false);
        this.rk.setSelected(false);
        this.xh.setSelected(false);
        /*原料库权限*/
        this.rk1.setSelected(false);
        this.ck1.setSelected(false);
        this.xh1.setSelected(false);
        /*生产计划部权限*/
        this.jhs.setSelected(false);
        this.cps.setSelected(false);
        this.yls.setSelected(false);
    }

    public void handleChangeRole(MouseEvent mouseEvent) {
        try {
            int[][] qx = new int[6][8];
            if(this.zhuce.isSelected())
                qx[2][1] = 1;
            else
                qx[2][1] = 0;
            if(this.makedd.isSelected())
                qx[2][2] = 1;
            if(this.undodd.isSelected())
                qx[2][3] = 1;
            if(this.tuihuo.isSelected())
                qx[2][4] = 1;
            /*财务部权限*/
            if(this.sk.isSelected())
                qx[1][1] = 1;
            if(this.tk.isSelected())
                qx[1][2] = 1;
            if(this.zr.isSelected())
                qx[1][3] = 1;
            if(this.zc.isSelected())
                qx[1][4] = 1;
            /*成品库权限*/
            if(this.tzfh.isSelected())
                qx[3][1] = 1;
            if(this.sh.isSelected())
                qx[3][2] = 1;
            if(this.rk.isSelected())
                qx[3][3] = 1;
            if(this.xh.isSelected())
                qx[3][4] = 1;
            /*原料库权限*/
            if(this.rk1.isSelected())
                qx[4][1] = 1;
            if(this.ck1.isSelected())
                qx[4][2] = 1;
            if(this.xh1.isSelected())
                qx[4][3] = 1;
            /*生产计划部权限*/
            if(this.jhs.isSelected())
                qx[5][1] = 1;
            if(this.cps.isSelected())
                qx[5][2] = 1;
            if(this.yls.isSelected())
                qx[5][3] = 1;

            int[][] zw = this.manageSection.searchZw(this.Grantid.getText());
            this.manageSection.ChangeQx(this.Grantid.getText(), qx, zw);

            this.clearQx();
            this.Grantid.setText("");
            this.Grantname.setText("");
            this.searchRoleText.clear();
            this.changeRole.setDisable(true);
            Staff.showAlert(Alert.AlertType.ERROR, "成功", "修改权限成功", "");
        }
        catch (SQLException se)
        {
            se.printStackTrace();
            Staff.showAlert(Alert.AlertType.ERROR, "错误", "修改权限失败", "系统错误");
        }
    }

    public void handleReset(MouseEvent mouseEvent) {
        if(this.resetText.getText().isEmpty())
        {
            Staff.showAlert(Alert.AlertType.ERROR, "错误", "查询失败", "请填写完整信息");
        }
        else
        {
            try {
                String[][] ans = this.manageSection.Simple_Search(this.resetText.getText());
                if(ans.length == 1)
                {
                    Staff.showAlert(Alert.AlertType.ERROR, "错误", "查询失败", "未查询到该员工");
                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("确认将" + ans[1][0] + "的密码重置？");
                    Optional result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        try {
                            this.manageSection.ResetPsw(ans[1][0]);
                            Staff.showAlert(Alert.AlertType.INFORMATION, "成功", "重置成功", "密码已重置为身份证后六位,请及时修改");
                        } catch (SQLException e) {
                            Staff.showAlert(Alert.AlertType.ERROR, "错误", "查询失败", "系统错误");
                        }
                    }
                }
            }
            catch (SQLException se)
            {
                se.printStackTrace();
                Staff.showAlert(Alert.AlertType.ERROR, "错误", "查询失败", "系统错误");
            }
        }
    }
}
