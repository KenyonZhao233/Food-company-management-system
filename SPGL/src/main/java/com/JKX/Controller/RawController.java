package com.JKX.Controller;

import com.JKX.Controller.ItemController.ItemDepRawDestroyController;
import com.JKX.Controller.ItemController.ItemDepRawOutController;
import com.JKX.Model.RawSection;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.JKX.Model.Staff;
import com.JKX.Model.Table.Raw;
import com.JKX.Model.Table.Storehouse;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.sql.SQLException;

import com.JKX.Controller.ItemController.ItemDepRawController;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


public class RawController {

    private RawSection rawSection;

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
    private AnchorPane all;

    @FXML
    private Label idname;

    @FXML
    private Button menuHomepage;

    @FXML
    private Button menuQuery;

    @FXML
    private Button menuManage;

    @FXML
    private Button menuStorage;

    @FXML
    private Button menuDestroy;

    @FXML
    private Button menuOut;

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
    private Pane pageManage;

    @FXML
    private TableView<Storehouse> ck;

    @FXML
    private TableView<Raw> kind;

    @FXML
    private Button edit;

    @FXML
    private Pane pageOut;

    @FXML
    private TextField out_text1;

    @FXML
    private TextField out_text2;

    @FXML
    private Button search_o;

    @FXML
    private VBox raw_items4;

    public RawController() {
    }

    public void fresh(MouseEvent event)
    {
        try {
            this.raw_items2.getChildren().clear();
            String[][] ans = rawSection.getInform();
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
        click_search(event);
        click_search_d(event);
        click_search_o(event);
        try {
            this.raw_items.getChildren().clear();
            String[][] ans = rawSection.getInform();
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
    }


    @FXML
    void click_input(MouseEvent event) throws SQLException {
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
        try{
            if(Float.parseFloat(input_text2.getText()) <= 0)
            {
                Alert _alert = new Alert(Alert.AlertType.WARNING);
                _alert.setTitle("警告");
                _alert.setHeaderText("输入错误");
                _alert.setContentText("请输入正确的数量");
                _alert.show();
                return;
            };
        }catch(NumberFormatException e){
            Alert _alert = new Alert(Alert.AlertType.WARNING);
            _alert.setTitle("警告");
            _alert.setHeaderText("输入错误");
            _alert.setContentText("请输入正确的数量");
            _alert.show();
            return;
        }
        Alert _alert = new Alert(Alert.AlertType.CONFIRMATION);
        _alert.setTitle("确认入库");
        _alert.setHeaderText("");
        _alert.setContentText("是否将原料编号为" + input_text1.getText() + "入仓库编号为" + input_text2.getText() + "的仓库" + input_text3.getText() + "件");
        Optional<ButtonType> result = _alert.showAndWait();
        if (result.get() == ButtonType.OK){
            rawSection.in(input_text1.getText(),input_text3.getText(),input_text2.getText());
            try {
                this.raw_items2.getChildren().clear();
                String[][] ans = rawSection.getInform();
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
            fresh(event);
        }
    }

    @FXML
    void click_search(MouseEvent event) {
        try {
            this.raw_items1.getChildren().clear();

            String[][] ans = rawSection.search(query_type, query_text);
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
            String[][] ans = rawSection.search_d(destroy_text1.getText(),destroy_text2.getText());
            for (int i = 1; i < ans.length; i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/ItemDepRaw_Destory.fxml"));
                Node node = null;
                try {
                    node = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ItemDepRawDestroyController itemDepRawDestroyController = loader.<ItemDepRawDestroyController>getController();
                itemDepRawDestroyController.setInform(ans[i][4], ans[i][0], ans[i][1], ans[i][2], ans[i][3], ans[i][6], ans[i][7],rawSection,this);
                this.raw_items3.getChildren().add(node);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void click_search_o(MouseEvent event) {
        try {
            this.raw_items4.getChildren().clear();
            String[][] ans = rawSection.search_o(out_text1.getText(),out_text2.getText());
            for (int i = 1; i < ans.length; i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/ItemDepRaw_Out.fxml"));
                Node node = null;
                try {
                    node = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ItemDepRawOutController itemDepRawOutController = loader.<ItemDepRawOutController>getController();
                itemDepRawOutController.setInform(ans[i][4], ans[i][0], ans[i][1], ans[i][2], ans[i][3], ans[i][6], ans[i][7],rawSection,this);
                this.raw_items4.getChildren().add(node);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void click_edit(MouseEvent event) {
        if(edit.getText().equals("修改"))
        {
            ck.setEditable(true);
            kind.setEditable(true);
            edit.setText("完成");
            edit.setStyle("-fx-background-color: blue");
        }else{
            ck.setEditable(false);
            kind.setEditable(false);
            edit.setText("修改");
            edit.setStyle("-fx-background-color: red");
        }
    }

    @FXML
    void handleClicks(MouseEvent event) throws IOException,SQLException  {
        if (event.getSource() == menuHomepage) {
            pageHomepage.setStyle("-fx-background-color : #02030A");
            pageHomepage.toFront();
            return;
        }
        if (event.getSource() == menuQuery) {
            pageQuery.setStyle("-fx-background-color : #02030A");
            pageQuery.toFront();
            return;
        }
        if (event.getSource() == menuStorage) {
            if(rawSection.getRight(1).equals("1"))
            {
                pageStorage.setStyle("-fx-background-color : #02030A");
                pageStorage.toFront();
                return;
            }
        }
        if(event.getSource() == menuDestroy)
        {
            if(rawSection.getRight(2).equals("1"))
            {
                pageDestroy.setStyle("-fx-background-color : #02030A");
                pageDestroy.toFront();
                return;
            }
        }
        if(event.getSource() == menuManage)
        {
            pageManage.setStyle("-fx-background-color : #02030A");
            pageManage.toFront();
            return;
        }
        if(event.getSource() == menuOut)
        {
            if(rawSection.getRight(3).equals("1"))
            {
                pageOut.setStyle("-fx-background-color : #02030A");
                pageOut.toFront();
                return;
            }
        }
        if(event.getSource() == menuQuit)
        {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/Lead.fxml"));

            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.setScene(new Scene((Parent) loader.load()));

            LeadContorller controller = loader.<LeadContorller>getController();

            controller.initData(rawSection.getStaff());

            stage.show();

            Stage index = (Stage)all.getScene().getWindow();
            index.close();
            return;
        }
        Alert _alert = new Alert(Alert.AlertType.WARNING);
        _alert.setTitle("警告");
        _alert.setHeaderText("权限受限");
        _alert.setContentText("您没有进行这一项功能的权限，请与相关负责人联系授权！");
        _alert.show();
        return;
    }

    public void initData(Staff staff) throws SQLException {

        idname.setText(staff.Name + "\r\n" + staff.Uid);

        rawSection = new RawSection(staff);
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();

        try {
            this.raw_items.getChildren().clear();
            String[][] ans = rawSection.getInform();
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
            String[][] ans = rawSection.getInform();
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
            String[][] ans = rawSection.getInform();
            for (int i = 1; i < ans.length; i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/ItemDepRaw_Destory.fxml"));
                Node node = null;
                try {
                    node = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ItemDepRawDestroyController itemDepRawDestroyController = loader.<ItemDepRawDestroyController>getController();
                itemDepRawDestroyController.setInform(ans[i][4], ans[i][0], ans[i][1], ans[i][2], ans[i][3], ans[i][6], ans[i][7],rawSection,this);
                this.raw_items3.getChildren().add(node);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            this.raw_items2.getChildren().clear();
            String[][] ans = rawSection.getInform();
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
        try {
            String [][] ans = rawSection.ck();
            final ObservableList<Storehouse> data = FXCollections.observableArrayList();
            for (int i = 1; i < ans.length; i++) {
                data.add(new Storehouse(ans[i][0], ans[i][1]));
            }
            ObservableList<TableColumn<Storehouse, ?>> observableList = ck.getColumns();
            observableList.get(0).setCellValueFactory(new PropertyValueFactory("ck_id"));
            observableList.get(1).setCellValueFactory(new PropertyValueFactory("ck_pos"));
            ck.setItems(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            String [][] ans = rawSection.kind();
            final ObservableList<Raw> data = FXCollections.observableArrayList();
            for (int i = 1; i < ans.length; i++) {
                data.add(new Raw(ans[i][0], ans[i][1], Integer.parseInt(ans[i][2]), Float.parseFloat(ans[i][3])));
            }
            ObservableList<TableColumn<Raw, ?>> observableList = kind.getColumns();
            observableList.get(0).setCellValueFactory(new PropertyValueFactory("raw_id"));
            observableList.get(1).setCellValueFactory(new PropertyValueFactory("raw_name"));
            observableList.get(2).setCellValueFactory(new PropertyValueFactory("raw_bzq"));
            observableList.get(3).setCellValueFactory(new PropertyValueFactory("raw_price"));
            kind.setItems(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            this.raw_items4.getChildren().clear();
            String[][] ans = rawSection.getInform();
            for (int i = 1; i < ans.length; i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/ItemDepRaw_Out.fxml"));
                Node node = null;
                try {
                    node = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ItemDepRawOutController itemDepRawOutController = loader.<ItemDepRawOutController>getController();
                itemDepRawOutController.setInform(ans[i][4], ans[i][0], ans[i][1], ans[i][2], ans[i][3], ans[i][6], ans[i][7],rawSection,this);
                this.raw_items4.getChildren().add(node);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void initialize() {

    }

}

