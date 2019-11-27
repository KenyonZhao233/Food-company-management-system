package com.JKX.Controller.ItemController;

import com.JKX.Controller.UserManageContorller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class StaffInformController {

    private UserManageContorller userManageContorller;

    @FXML
    private Label uid, name, zw, sfz, sex, date;

    public void setInform(String uid, String zw, String name, String sex, String sfz, String date)
    {
        this.uid.setText(uid);
        this.name.setText(name);
        this.zw.setText(zw);
        this.sfz.setText(sfz);
        this.sex.setText(sex);
        this.date.setText(date);
    }

    public void setContorller(UserManageContorller userManageContorller)
    {
        this.userManageContorller = userManageContorller;
    }


    public void handleClick(MouseEvent mouseEvent) {
        userManageContorller.setInform(this.uid.getText(), this.name.getText(), this.zw.getText(), this.sfz.getText(), this.sex.getText());
    }
}
