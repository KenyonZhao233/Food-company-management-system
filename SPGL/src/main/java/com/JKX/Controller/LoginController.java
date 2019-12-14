package com.JKX.Controller;

import com.JKX.Model.Staff;
import com.JKX.Mysql.Secret;
import com.jfoenix.controls.*;
import com.sun.javafx.robot.impl.FXRobotHelper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Dialog;
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
import java.io.*;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class LoginController implements Initializable {

    private Staff staff;

    @FXML
    private AnchorPane loginPane;

    @FXML
    private JFXPasswordField passWord;

    @FXML
    private JFXButton signIn;

    @FXML
    private JFXComboBox<String> uid;

    @FXML
    private JFXCheckBox remember;

    @FXML
    private Label clear;

    @FXML
    private Label exits;

    private String ids[] = new String[50];

    private String pws[] = new String[50];

    private int index = 0;

    private String  filePath = "src//main//resources//data//data.bin";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        staff = new Staff(" ", " ");

        try{
            FileInputStream fin = new FileInputStream(filePath);
            InputStreamReader reader = new InputStreamReader(fin);
            BufferedReader buffReader = new BufferedReader(reader);
            String strTmp = "";
            while((strTmp = buffReader.readLine())!=null)
            {
                strTmp = Secret.encode(strTmp,'z');
                String idpw[] = strTmp.split(",");
                uid.getItems().add(idpw[0]);
                uid.setValue(idpw[0]);
                passWord.setText(idpw[1]);
                ids[index] = idpw[0];
                pws[index] = idpw[1];
                index = index + 1;
                remember.setSelected(true);
            }
            buffReader.close();
        }
        catch(IOException e) {
            File file=new File(filePath);
            if(file.exists()){
                file.delete();
            }else{
                try {
                   file.createNewFile();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        uid.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                for(int i = 0;  i < index; i++)
                {
                    if(ids[i].equals(t1))
                    {
                        passWord.setText(pws[i]);
                    }
                }
            }
        });
    }

    public void handleClose(MouseEvent mouseEvent) {
        System.exit(0);
    }

    @FXML
    void change_password(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/Login_pw.fxml"));

        Stage stage = new Stage(StageStyle.UTILITY);
        stage.setScene(new Scene((Parent) loader.load()));
        stage.setTitle("修改密码");
        PwcController controller = loader.<PwcController>getController();
        controller.initdata(uid.getValue().toString());
        stage.show();
    }

    @FXML
    void click_clear(MouseEvent event) {
        Alert _alert = new Alert(Alert.AlertType.CONFIRMATION);
        _alert.setTitle("提示");
        _alert.setHeaderText("清空信息");
        _alert.setContentText("是否清除计算机上所有存储的账户信息？");
        Optional<ButtonType> result = _alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            uid.getItems().clear();
            index = 0;
            passWord.setText("");
        }
    }

    public void handleSignin(MouseEvent mouseEvent) throws Exception{
        staff.Uid = uid.getValue().toString();
        staff.password = passWord.getText();
        if(staff.Login()) {
            Alert _alert = new Alert(Alert.AlertType.INFORMATION);
            _alert.setTitle("提示");
            _alert.setHeaderText("登录成功");
            _alert.setContentText("欢迎您： " + staff.Name);
            _alert.showAndWait();

            if(remember.isSelected())
            {
                boolean flag = true;
                for(int i = 0;  i < index; i++)
                {
                    if(ids[i].equals(staff.Uid))
                    {
                        pws[i] = staff.password;
                        flag = false;
                        break;
                    }
                }
                if(flag)
                {
                    ids[index] = staff.Uid;
                    pws[index] = staff.password;
                    index++;
                }
                File f=new File(filePath);
                FileWriter fw=null;
                try{
                    fw=new FileWriter(f);
                    for(int i = 0;  i < index; i++)
                    {
                        fw.write(Secret.encode(ids[i] + "," + pws[i],'z')+"\r\n");
                    }
                    fw.close();
                }catch(Throwable e){e.printStackTrace();// 把异常给输出出来
                }finally {
                    if (fw != null)
                        try {
                            fw.close();
                        } catch (Throwable e) {
                        }
                }
            }

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

    public void handleExit(MouseEvent mouseEvent) {
        System.exit(0);
    }
}
