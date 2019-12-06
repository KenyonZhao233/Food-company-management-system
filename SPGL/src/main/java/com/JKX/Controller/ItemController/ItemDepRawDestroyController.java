package com.JKX.Controller.ItemController;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ItemDepRawDestroyController {

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

    @FXML
    void enter(MouseEvent event) {
        pane.setStyle("-fx-background-color : #22A7F0");
    }

    @FXML
    void exit(MouseEvent event) {
        pane.setStyle("-fx-background-color : #02030A");
    }

    @FXML
    void click_destroy(MouseEvent event) {
        String t = this.time.getText();
        System.out.println(this.time.getText()+this.id.getText());
        Alert _alert = new Alert(Alert.AlertType.CONFIRMATION);
        _alert.setTitle("确认销毁");
        _alert.setHeaderText("");
        _alert.setContentText("是否销毁于" + this.time.getText().substring(0,19) + "入库\n编号为" + this.id.getText() + "的原材料？");
        Optional<ButtonType> result = _alert.showAndWait();
        if (result.get() == ButtonType.OK) {

        }
    }
    public void setInform(String time, String id, String name, String date, String prize, String count, String num)
    {
        this.time.setText(time);
        this.id.setText(id);
        this.name.setText(name);
        this.date.setText(date);
        this.prize.setText(prize);
        this.count.setText(count);
        this.num.setText(num);
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
