package com.JKX.Controller;

import com.JKX.Model.Staff;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;

public class PwcController {

    @FXML
    private AnchorPane loginPane;

    @FXML
    private TextField t1;

    @FXML
    private PasswordField t2;

    @FXML
    private PasswordField t3;

    @FXML
    private PasswordField t4;

    private Staff staff;

    @FXML
    void change(MouseEvent event) {
        staff.Uid = t1.getText();
        staff.password = t2.getText();
        if(t3.getText().equals(t4.getText()))
        {
            if(staff.Login()) {
                try {
                    staff.changepwd(t3.getText());
                    Alert _alert = new Alert(Alert.AlertType.INFORMATION);
                    _alert.setTitle("提示");
                    _alert.setHeaderText("修改成功");
                    _alert.setContentText(staff.Uid + "修改密码成功!");
                    _alert.showAndWait();
                    t1.getScene().getWindow().hide();
                } catch (SQLException e) {
                    Alert _alert = new Alert(Alert.AlertType.INFORMATION);
                    _alert.setTitle("提示");
                    _alert.setHeaderText("输入信息有误");
                    _alert.setContentText("请确认账号与密码是否正确");
                    _alert.showAndWait();
                    e.printStackTrace();
                }

            }else{
                Alert _alert = new Alert(Alert.AlertType.INFORMATION);
                _alert.setTitle("提示");
                _alert.setHeaderText("输入信息有误");
                _alert.setContentText("请确认账号与密码是否正确");
                _alert.showAndWait();
            }
        }
        else{
            Alert _alert = new Alert(Alert.AlertType.INFORMATION);
            _alert.setTitle("提示");
            _alert.setHeaderText("输入信息有误");
            _alert.setContentText("请核对新密码与重新输入的新密码");
            _alert.showAndWait();
        }
    }

    void initdata(String Uid) {
        staff = new Staff(" ", " ");
        t1.setText(Uid);
    }
}
