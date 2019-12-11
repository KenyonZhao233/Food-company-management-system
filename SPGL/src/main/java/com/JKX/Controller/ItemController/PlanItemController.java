package com.JKX.Controller.ItemController;

import com.JKX.Controller.ProductionPlanController;
import com.JKX.Controller.WorkshopController;
import com.JKX.Model.Staff;
import com.JKX.Model.Table.Plan;
import com.JKX.Model.Table.Production;
import com.JKX.Model.Table.Raw;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;


public class PlanItemController {

    private ProductionPlanController productionPlanController;

    private WorkshopController workshopController;

    private Plan plan;

    private Node node;

    private int nowNum;

    private int aimNum;

    final ObservableList<Raw> data = FXCollections.observableArrayList();

    @FXML
    private Label proId, planId, proName, sdate, edate, zt, wait, zrr, fzr, outs;

    @FXML
    private DatePicker deadline;

    @FXML
    private TableView<Raw> rawView;

    @FXML
    private JFXButton delete, confirm, change, push, complete, doPlan;

    @FXML
    private ProgressBar progress;

    @FXML
    private JFXTextField proNum, pushNum;

    public void setNode(Node node) {
        this.node = node;
    }

    public void setInform(Plan plan, int flag)
    {
        try {
            this.complete.setVisible(false);
            this.plan = plan;
            Raw[] raws = plan.getProduction().getRaws();
            for (int i = 0; i < raws.length; i++) {
                data.add(raws[i]);
            }
            ObservableList<TableColumn<Raw, ?>> observableList = rawView.getColumns();
            observableList.get(0).setCellValueFactory(new PropertyValueFactory("raw_id"));
            observableList.get(1).setCellValueFactory(new PropertyValueFactory("raw_name"));
            observableList.get(2).setCellValueFactory(new PropertyValueFactory("raw_num"));
            rawView.setItems(data);
            this.planId.setText(plan.getPlan_id());
            this.proId.setText(plan.getProduction().getProduction_id());
            this.proName.setText(plan.getProduction().getProduction_name());
            this.proNum.setText(String.valueOf(plan.getProduction().getNums()));
            this.sdate.setText(plan.getPlan_sdate());
            this.edate.setText(plan.getPlan_edate());
            this.zrr.setText(plan.getZrr());
            this.fzr.setText(plan.getZrr());
//            final Callback<DatePicker, DateCell> dayCellFactory =
//                    new Callback<DatePicker, DateCell>() {
//                        @Override
//                        public DateCell call(final DatePicker datePicker) {
//                            return new DateCell() {
//                                @Override
//                                public void updateItem(LocalDate item, boolean empty) {
//                                    super.updateItem(item, empty);
//
//                                    if (item.isBefore(
//                                            deadline.getValue().plusDays(1))
//                                    ) {
//                                        setDisable(true);
//                                        setStyle("-fx-background-color: #ffc0cb;");
//                                    }
//                                }
//                            };
//                        }
//                    };
//            deadline.setDayCellFactory(dayCellFactory);
            this.deadline.setValue(LocalDate.of(Integer.parseInt(plan.getPlan_ddl().substring(0, 4)), Integer.parseInt(plan.getPlan_ddl().substring(5, 7)), Integer.parseInt(plan.getPlan_ddl().substring(8, 10))));
            this.zt.setText(plan.getPlan_zt());
            String z = plan.getPlan_zt();
            this.wait.setVisible(false);
            if(z.equals("待执行"))
            {
                this.progress.setProgress(0.0);
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = df.format(new Date());
                if(plan.getPlan_ddl().compareTo(date) < 0) {
                    this.outs.setVisible(true);
                }
            }
            else if(z.equals("执行中"))
            {
                this.progress.setProgress(0.0);
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = df.format(new Date());
                if(plan.getPlan_ddl().compareTo(date) < 0) {
                    this.outs.setVisible(true);
                }
                int[] nums;
                if (flag == 1) {
                    nums = this.productionPlanController.getPlanSection().SearchAdair(this.plan.getPlan_id());
                    this.push.setVisible(false);
                    this.pushNum.setVisible(false);
                }
                else {
                    nums = this.workshopController.getWorkshopSection().SearchAdair(this.plan.getPlan_id());
                }
                this.setNumEditable(false);
                this.doPlan.setVisible(false);
                this.nowNum = nums[0];
                this.aimNum = nums[1];
                if(this.nowNum >= this.aimNum)
                {
                    this.complete.setVisible(true);
                    this.push.setVisible(false);
                    this.pushNum.setVisible(false);
                }
                this.progress.setProgress(this.nowNum * (1.0) / this.aimNum);
                this.zt.setText(this.nowNum + "/" + this.aimNum);
                this.setNumEditable(false);
            }
            else if(z.equals("待审核"))
            {
                if(plan.getPlan_ddl().compareTo(plan.getPlan_sdate()) < 0)
                {
                    this.edate.setTextFill(Color.RED);
                }
                this.doPlan.setVisible(false);
                this.deadline.setEditable(false);
                this.setNumEditable(false);
                this.progress.setProgress(0.66);
            }
            else if(z.equals("已完成"))
            {
                if(plan.getPlan_ddl().compareTo(plan.getPlan_sdate()) < 0)
                {
                    this.edate.setTextFill(Color.RED);
                }
                this.deadline.setEditable(false);
                this.setNumEditable(false);
                this.progress.setProgress(1.0);
            }
        }
        catch (Exception se)
        {
            se.printStackTrace();
            Staff.showAlert(Alert.AlertType.ERROR, "错误", "查询失败", "系统错误");
        }
    }

    public void setDeadlineEditable(boolean deadlineEditable)
    {
        this.deadline.setDisable(!deadlineEditable);
    }


    public void setWorkshopController(WorkshopController workshopController) {
        this.workshopController = workshopController;
    }

    public void setConfirmVisable(boolean confirmVisable)
    {
        this.confirm.setVisible(confirmVisable);
    }

    public void setChangeVisable(boolean changeVisable)
    {
        this.change.setVisible(changeVisable);
    }

    public void setDeleteVisable(boolean deleteVisable)
    {
        this.delete.setVisible(deleteVisable);
    }

    public void setPushVisable(boolean pushVisable)
    {
        this.push.setVisible(pushVisable);
    }

    public void setPushNumVisable(boolean pushNumVisable)
    {
        this.pushNum.setVisible(pushNumVisable);
    }

    public void setDoPlanVisable(boolean doPlanVisable)
    {
        this.doPlan.setVisible(doPlanVisable);
    }

    public void setNumEditable(boolean numEditable){this.proNum.setEditable(numEditable);}

    public void setChangeDisable(boolean changeDisable){this.change.setDisable(changeDisable);}

    public void setDeleteDisable(boolean deleteDisable)
    {
        this.delete.setDisable(deleteDisable);
    }

    public void setConfirmDisable(boolean confirmDisable)
    {
        this.confirm.setDisable(confirmDisable);
    }

    public void setController(ProductionPlanController productionPlanController) {
        this.productionPlanController = productionPlanController;
    }



    public void handleClicks(MouseEvent mouseEvent) {
        JFXButton actionBtn = (JFXButton)mouseEvent.getSource();
        if(actionBtn == this.change)
        {
            try {
                int prenums = this.plan.getProduction().getNums();
                int nums = Integer.parseInt(this.proNum.getText());
                this.plan.getProduction().setNums(nums);
                for(int i = 0; i < this.plan.getProduction().getRaws().length; i++) {
                    float vae = this.plan.getProduction().getRaws()[i].getRaw_num() / prenums * nums;
                    float now = this.productionPlanController.getPlanSection().searchRawOnId(this.plan.getProduction().getRaws()[i].getRaw_id())[0].getRaw_kc()+ this.productionPlanController.getPlanSection().searchPlanRawNum(this.plan.getProduction().getRaws()[i].getRaw_id());
                    if(now >= vae)
                    {
                        this.plan.getProduction().getRaws()[i].setRaw_num(vae);
                    }
                    else
                    {
                        Staff.showAlert(Alert.AlertType.ERROR, "错误", "修改失败", "当前库存仅剩：" + String.valueOf(now));
                    }
                }
                this.productionPlanController.getPlanSection().changePlanOnnum(this.planId.getText(), this.proNum.getText(), this.deadline.getValue().toString());
                this.rawView.refresh();
            }
            catch (SQLException se)
            {
                se.printStackTrace();
                Staff.showAlert(Alert.AlertType.ERROR, "错误", "修改失败", "系统错误");
            }
        }
        else if(actionBtn == this.delete)
        {
            try {
                this.productionPlanController.getPlanSection().deletePlan(this.planId.getText());
                this.productionPlanController.deletevboxPlannode(this.node);
            }
            catch (SQLException se)
            {
                se.printStackTrace();
                Staff.showAlert(Alert.AlertType.ERROR, "错误", "删除失败", "系统错误");
            }
        }
        else if(actionBtn == this.confirm)
        {
            try {
                this.productionPlanController.getPlanSection().confirmPlan(this.planId.getText());
                this.progress.setProgress(1.0);
                this.zt.setText("已完成");
                this.confirm.setDisable(true);
            }
            catch (SQLException se)
            {
                se.printStackTrace();
                Staff.showAlert(Alert.AlertType.ERROR, "错误", "删除失败", "系统错误");
            }
        }
    }





    public void handleComplete(MouseEvent mouseEvent) {
        try {
            this.zt.setText("待审核");
            this.progress.setProgress(0.66);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = df.format(new Date());
            this.plan.setPlan_edate(date);
            this.edate.setText(date);
            this.plan.setPlan_zt("待审核");
            this.workshopController.getWorkshopSection().changeZtOver(this.plan.getPlan_id());
            this.workshopController.deleteVbox2(this.node);
        }
        catch (SQLException se)
        {
            se.printStackTrace();
            this.workshopController.getWorkshopSection().getStaff().showAlert(Alert.AlertType.ERROR, "错误", "完成失败", "系统错误");
        }
    }

    public void handlePush(MouseEvent mouseEvent) {
        if(this.pushNum.getText().isEmpty() )
        {
            this.workshopController.getWorkshopSection().getStaff().showAlert(Alert.AlertType.ERROR, "错误", "交付失败", "请填写信息");
        }
        else
        {
            try {
                this.nowNum += Integer.parseInt(this.pushNum.getText());
                this.workshopController.getWorkshopSection().updatePlan(this.plan.getPlan_id(), Math.min(this.nowNum, this.aimNum));
                if(this.nowNum >= this.aimNum)
                {
                    this.zt.setText(this.aimNum + "/" + this.aimNum);
                    this.progress.setProgress(this.nowNum * (1.0) / this.aimNum);
                    this.push.setVisible(false);
                    this.complete.setVisible(true);
                    this.pushNum.setVisible(false);
                }
                else
                {
                    this.zt.setText(this.nowNum + "/" + this.aimNum);
                    this.progress.setProgress(this.nowNum * (1.0) / this.aimNum);
                }
            }
            catch (SQLException se)
            {
                se.printStackTrace();
                this.workshopController.getWorkshopSection().getStaff().showAlert(Alert.AlertType.ERROR, "错误", "交付失败", "系统错误");
            }
        }
    }

    public void handleDo(MouseEvent mouseEvent) {
        try {
            this.workshopController.getWorkshopSection().changeZtDo(this.plan.getPlan_id(), 0, this.plan.getProduction().getNums(), this.workshopController.getWorkshopSection().getStaff().Name);
            this.workshopController.deleteVbox1(this.node);
        }
        catch (SQLException se)
        {
            se.printStackTrace();
            this.workshopController.getWorkshopSection().getStaff().showAlert(Alert.AlertType.ERROR, "错误", "执行失败", "系统错误");
        }
    }
}
