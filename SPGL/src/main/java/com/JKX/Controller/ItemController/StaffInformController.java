package com.JKX.Controller.ItemController;

import com.JKX.Controller.UserManageContorller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class StaffInformController {

    private UserManageContorller userManageContorller;

    @FXML
    private Label uid, name, zw, sfz, sex;

    public void setInform(String uid, String name, String zw, String sfz, String sex)
    {
        this.uid.setText(uid);
        this.name.setText(name);
        this.zw.setText(zw);
        this.sfz.setText(sfz);
        this.sex.setText(sex);
    }

    public void SetContorller(UserManageContorller userManageContorller)
    {
        this.userManageContorller = userManageContorller;
    }


    public void handleClick(MouseEvent mouseEvent) {
        userManageContorller.setInform(this.uid.getText(), this.name.getText(), this.zw.getText(), this.sfz.getText(), this.sex.getText());
    }
}
