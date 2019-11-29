package com.JKX.Controller;

import com.JKX.Model.Staff;
import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXPasswordField;
import com.sun.javafx.robot.impl.FXRobotHelper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sun.util.resources.cldr.ak.CurrencyNames_ak;

import javax.security.auth.login.LoginContext;
import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private Staff staff;

    @FXML
    private Label labelClose;

    @FXML
    private JFXButton signIn;

    @FXML
    private JFXPasswordField passWord;

    @FXML
    private JFXTextField uid;

    @FXML
    private AnchorPane loginPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        staff = new Staff(" ", " ");
    }

    public void handleClose(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void handleSignin(MouseEvent mouseEvent) throws Exception{
        staff.Uid = uid.getText();
        staff.password = passWord.getText();
        if(staff.Login()) {
            Alert _alert = new Alert(Alert.AlertType.INFORMATION);
            _alert.setTitle("提示");
            _alert.setHeaderText("登录成功");
            _alert.setContentText("欢迎您： " + staff.Name);
            _alert.showAndWait();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/Lead.fxml"));

            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.setScene(new Scene((Parent) loader.load()));

            LeadContorller controller = loader.<LeadContorller>getController();

            controller.initData(staff);

            stage.show();

            Stage index = (Stage)loginPane.getScene().getWindow();
            index.close();

        }
        else{
            Alert _alert = new Alert(Alert.AlertType.INFORMATION);
            _alert.setTitle("提示");
            _alert.setContentText("账号或密码错误");
            _alert.show();
        }
    }


}
