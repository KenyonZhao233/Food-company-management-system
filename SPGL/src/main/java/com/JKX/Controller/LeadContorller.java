package com.JKX.Controller;

import com.JKX.Model.Staff;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.TreeSet;

public class LeadContorller implements Initializable {

    public Staff staff;

    @FXML
    private Button btnXS;

    @FXML
    private Button btnCW;

    @FXML
    private Button btnYW;

    @FXML
    private Button btnCP;

    @FXML
    private Button btnYL;

    @FXML
    private Button btnCJ;

    @FXML
    private Button btnJH;

    @FXML
    private Button btnRY;

    @FXML
    void handleClick(MouseEvent event) {
        Button actionBtn = (Button) event.getSource();
        if(actionBtn == btnCJ)
        {

        }
        else if (actionBtn == btnCP)
        {

        }
        else if()
        {

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void initData(Staff staff)
    {
        this.staff = staff;
        for (String str : staff.zw) {
            if(str == "财务业务人员")
        }
    }
}
