package com.JKX.Controller.ItemController;

import com.JKX.Controller.RawController;
import com.JKX.Model.RawSection;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class ItemDepRawOutController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label time;

    @FXML
    private Label id;

    @FXML
    private Label name;

    @FXML
    private Label date;

    @FXML
    private Label prize;

    @FXML
    private Label count;

    @FXML
    private Label num;

    @FXML
    private AnchorPane pane;

    private RawSection rawSection;

    private RawController rawController;

    @FXML
    void enter(MouseEvent event) {
        pane.setStyle("-fx-background-color : #22A7F0");
    }

    @FXML
    void exit(MouseEvent event) {
        pane.setStyle("-fx-background-color : #02030A");
    }

    @FXML
    void click_destroy(MouseEvent event) throws SQLException {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("出库系统");
        dialog.setHeaderText("确认出库");
        dialog.setContentText("出库数量：");
        Optional result = dialog.showAndWait();
        if (result.isPresent()) {
            try{
                if(Integer.parseInt(result.get().toString()) < 0)
                {
                    Alert _alert2 = new Alert(Alert.AlertType.WARNING);
                    _alert2.setTitle("出库系统");
                    _alert2.setHeaderText("错误");
                    _alert2.setContentText("请输入正确格式的数量！");
                    _alert2.show();
                    return;
                }
                if(rawSection.Output(this.id.getText(),this.time.getText(),Integer.parseInt(result.get().toString()),this.rawSection.getStaff().Uid)){
                    rawController.fresh(event);
                }else{
                    Alert _alert2 = new Alert(Alert.AlertType.WARNING);
                    _alert2.setTitle("出库系统");
                    _alert2.setHeaderText("错误");
                    _alert2.setContentText("成品数量不足！");
                    _alert2.show();
                }
            }catch(NumberFormatException e)
            {
                Alert _alert2 = new Alert(Alert.AlertType.WARNING);
                _alert2.setTitle("出库系统");
                _alert2.setHeaderText("错误");
                _alert2.setContentText("请输入正确格式的数量！");
                _alert2.show();
            }

        }
    }
    public void setInform(String time, String id, String name, String date, String prize, String count, String num, RawSection rawSection, RawController rawController)
    {
        this.time.setText(time);
        this.id.setText(id);
        this.name.setText(name);
        this.date.setText(date);
        this.prize.setText(prize);
        this.count.setText(count);
        this.num.setText(num);
        this.rawSection = rawSection;
        this.rawController = rawController;
    }

    @FXML
    void initialize() {
        assert time != null : "fx:id=\"time\" was not injected: check your FXML file 'ItemDepRaw_Destory.fxml'.";
        assert id != null : "fx:id=\"id\" was not injected: check your FXML file 'ItemDepRaw_Destory.fxml'.";
        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'ItemDepRaw_Destory.fxml'.";
        assert date != null : "fx:id=\"date\" was not injected: check your FXML file 'ItemDepRaw_Destory.fxml'.";
        assert prize != null : "fx:id=\"prize\" was not injected: check your FXML file 'ItemDepRaw_Destory.fxml'.";
        assert count != null : "fx:id=\"count\" was not injected: check your FXML file 'ItemDepRaw_Destory.fxml'.";
        assert num != null : "fx:id=\"num\" was not injected: check your FXML file 'ItemDepRaw_Destory.fxml'.";

    }
}
