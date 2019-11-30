package com.JKX.Controller;

import com.JKX.Controller.ItemController.ItemDepRawDestroyController;
import com.JKX.Model.Raw;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.JKX.Model.Staff;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.JKX.Controller.ItemController.ItemDepRawController;
import javafx.util.Duration;


public class RawController {

    private static Staff staff;
    private static Raw raw;

    DateFormat df = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
    Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            String time = df.format(new Date());
            label_year.setText(time.substring(0,4));
            label_month.setText(time.substring(5,7));
            label_day.setText(time.substring(8,10));
            label_time.setText(time.substring(11,19));
            label_year2.setText(time.substring(0,4));
            label_month2.setText(time.substring(5,7));
            label_day2.setText(time.substring(8,10));
            label_time2.setText(time.substring(11,19));
        }
    }));

    @FXML
    private Button menuHomepage;

    @FXML
    private Button menuQuery;

    @FXML
    private Button menuStorage;

    @FXML
    private Button menuDestroy;

    @FXML
    private Button menuQuit;

    @FXML
    private Pane pageQuery;

    @FXML
    private Label label_year2;

    @FXML
    private Label label_month2;

    @FXML
    private Label label_day2;

    @FXML
    private Label label_time2;

    @FXML
    private VBox raw_items2;

    @FXML
    private TextField query_text;

    @FXML
    private ComboBox query_type;

    @FXML
    private VBox raw_items1;

    @FXML
    private Pane pageStorage;

    @FXML
    private TextField input_text1;

    @FXML
    private TextField input_text2;

    @FXML
    private Button input;

    @FXML
    private TextField input_text3;

    @FXML
    private Pane pageDestroy;

    @FXML
    private VBox pnItems1;

    @FXML
    private TextField destroy_text1;

    @FXML
    private TextField destroy_text2;

    @FXML
    private Button search_d;

    @FXML
    private VBox raw_items3;

    @FXML
    private Pane pageHomepage;

    @FXML
    private Label label_year;

    @FXML
    private Label label_month;

    @FXML
    private Label label_day;

    @FXML
    private Label label_time;

    @FXML
    private VBox raw_items;


    @FXML
    void click_input(MouseEvent event) {
        if(input_text1.getText().isEmpty() || input_text1.getText() == null ||
                input_text2.getText().isEmpty() || input_text2.getText() == null ||
                input_text3.getText().isEmpty() || input_text3.getText() == null)
        {
            Alert _alert = new Alert(Alert.AlertType.WARNING);
            _alert.setTitle("警告");
            _alert.setHeaderText("输入错误");
            _alert.setContentText("信息填写有缺失");
            _alert.show();
            return;
        }
        Alert _alert = new Alert(Alert.AlertType.CONFIRMATION);
        _alert.setTitle("确认入库");
        _alert.setHeaderText("");
        _alert.setContentText("是否将原料编号为" + input_text1.getText() + "入仓库编号为" + input_text2.getText() + "的仓库" + input_text3.getText() + "件");
        Optional<ButtonType> result = _alert.showAndWait();
        if (result.get() == ButtonType.OK){
            System.out.println("ok");
        }else{
            System.out.println("cancel");
        }
    }

    @FXML
    void click_search(MouseEvent event) {
        try {
            this.raw_items1.getChildren().clear();
            String str = "select * from raw, raw_ck where raw.raw_id = raw_ck.raw_id ";
            if(query_type.getValue() == "原料编号" && (!query_text.getText().isEmpty() || query_text.getText() == null))
            {
                str = str + "and raw_ck.raw_id = '" + query_text.getText() + "' ";
            }
            if(query_type.getValue() == "仓库编号" && (!query_text.getText().isEmpty() || query_text.getText() == null))
            {
                str = str + "and raw_ck.raw_in = '" + query_text.getText() + "' ";
            }
            str = str + "order by raw_date DESC";
            String[][] ans = raw.Search(str);
            for (int i = 1; i < ans.length; i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/ItemDepRaw.fxml"));
                Node node = null;
                try {
                    node = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ItemDepRawController itemDepRawController = loader.<ItemDepRawController>getController();
                itemDepRawController.setInform(ans[i][4], ans[i][0], ans[i][1], ans[i][2], ans[i][3], ans[i][6], ans[i][7]);
                this.raw_items1.getChildren().add(node);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void click_search_d(MouseEvent event) {
        try {
            this.raw_items3.getChildren().clear();
            String str = "select * from raw, raw_ck where raw.raw_id = raw_ck.raw_id ";
            if(!destroy_text1.getText().isEmpty() || destroy_text2.getText() == null)
            {
                str = str + "and raw_ck.raw_id = '" + destroy_text1.getText() + "' ";
            }
            if(!destroy_text2.getText().isEmpty() || destroy_text2.getText() == null)
            {
                str = str + "and raw_ck.raw_in = '" + destroy_text2.getText() + "' ";
            }
            str = str + "order by raw_date DESC";
            String[][] ans = raw.Search(str);
            for (int i = 1; i < ans.length; i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/ItemDepRaw_Destory.fxml"));
                Node node = null;
                try {
                    node = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ItemDepRawDestroyController itemDepRawDestroyController = loader.<ItemDepRawDestroyController>getController();
                itemDepRawDestroyController.setInform(ans[i][4], ans[i][0], ans[i][1], ans[i][2], ans[i][3], ans[i][6], ans[i][7]);
                this.raw_items3.getChildren().add(node);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleClicks(MouseEvent event) {
        if (event.getSource() == menuHomepage) {
            pageHomepage.setStyle("-fx-background-color : #02030A");
            pageHomepage.toFront();
        }
        if (event.getSource() == menuQuery) {
            pageQuery.setStyle("-fx-background-color : #02030A");
            pageQuery.toFront();
        }
        if (event.getSource() == menuStorage) {
            pageStorage.setStyle("-fx-background-color : #02030A");
            pageStorage.toFront();
        }
        if(event.getSource() == menuDestroy)
        {
            pageDestroy.setStyle("-fx-background-color : #02030A");
            pageDestroy.toFront();
        }
        if(event.getSource() == menuQuit)
        {

        }
    }

    static String[][] getInform() throws SQLException
    {
        String[][] ans;
        ans = raw.Search("select * from raw, raw_ck where raw.raw_id = raw_ck.raw_id order by raw_date DESC");
        return ans;
    }



    @FXML
    void initialize() {
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
        staff = new Staff("KenyonZ", "123456");

        if(staff.Login()){
            System.out.println("成功");
            raw = new Raw(staff);
        }

        try {
            this.raw_items.getChildren().clear();
            String[][] ans = this.getInform();
            for (int i = 1; i < ans.length; i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/ItemDepRaw.fxml"));
                Node node = null;
                try {
                    node = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ItemDepRawController itemDepRawController = loader.<ItemDepRawController>getController();
                itemDepRawController.setInform(ans[i][4], ans[i][0], ans[i][1], ans[i][2], ans[i][3], ans[i][6], ans[i][7]);
                this.raw_items.getChildren().add(node);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            this.raw_items1.getChildren().clear();
            String[][] ans = this.getInform();
            for (int i = 1; i < ans.length; i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/ItemDepRaw.fxml"));
                Node node = null;
                try {
                    node = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ItemDepRawController itemDepRawController = loader.<ItemDepRawController>getController();
                itemDepRawController.setInform(ans[i][4], ans[i][0], ans[i][1], ans[i][2], ans[i][3], ans[i][6], ans[i][7]);
                this.raw_items1.getChildren().add(node);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            this.raw_items3.getChildren().clear();
            String[][] ans = this.getInform();
            for (int i = 1; i < ans.length; i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/ItemDepRaw_Destory.fxml"));
                Node node = null;
                try {
                    node = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ItemDepRawDestroyController itemDepRawDestroyController = loader.<ItemDepRawDestroyController>getController();
                itemDepRawDestroyController.setInform(ans[i][4], ans[i][0], ans[i][1], ans[i][2], ans[i][3], ans[i][6], ans[i][7]);
                this.raw_items3.getChildren().add(node);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            this.raw_items2.getChildren().clear();
            String[][] ans = this.getInform();
            for (int i = 1; i < ans.length; i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/ItemDepRaw.fxml"));
                Node node = null;
                try {
                    node = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ItemDepRawController itemDepRawController = loader.<ItemDepRawController>getController();
                itemDepRawController.setInform(ans[i][4], ans[i][0], ans[i][1], ans[i][2], ans[i][3], ans[i][6], ans[i][7]);
                this.raw_items2.getChildren().add(node);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        query_type.getItems().addAll("原料编号", "仓库编号");
        query_type.setValue("原料编号");
    }
}

