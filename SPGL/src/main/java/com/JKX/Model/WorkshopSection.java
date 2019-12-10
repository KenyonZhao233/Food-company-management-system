package com.JKX.Model;

import com.JKX.Model.Table.Plan;
import com.JKX.Model.Table.Production;
import com.JKX.Model.Table.Raw;

import java.sql.SQLException;

public class WorkshopSection {
    private Staff staff;

    public WorkshopSection(Staff staff)
    {
        this.staff = staff;
    }

    public Staff getStaff() {
        return staff;
    }

    public Production[] searchCpOnID(String id) throws SQLException
    {
        String[] a = {"string"};
        String[] b = {id};
        String[][] ans;
        ans = staff.ExcuteSearch("Call Search_CpId(?)", a, b);
        Production[] productions = new Production[ans.length - 1];
        for(int i = 1; i < ans.length; i++)
        {
            String[][] ansRaw;
            ansRaw = staff.Search("SELECT raw.*, product_raw.raw_num " +
                    "FROM product_raw, raw " +
                    "where product_id = '" + ans[i][0] + "' AND raw.raw_id = product_raw.raw_id " +
                    "ORDER BY raw.raw_name;");
            Raw[] raws = new Raw[ansRaw.length - 1];
            for(int j = 1; j < ansRaw.length; j++) {
                raws[j - 1] = new Raw(ansRaw[j][0], ansRaw[j][1], Integer.parseInt(ansRaw[j][2]), Float.parseFloat(ansRaw[j][3]), 0, Float.parseFloat(ansRaw[j][4]));
            }
            productions[i - 1] = new Production(ans[i][0], ans[i][1], Float.parseFloat(ans[i][2]),Float.parseFloat(ans[i][3]), Float.parseFloat(ans[i][4]),Integer.parseInt(ans[i][5]), Integer.parseInt(ans[i][6]), raws, 0);
        }
        return productions;
    }

    public Plan[] searchPlan(String id, String sdate, String edate, String zt) throws SQLException      //存储过程，查询Plan返回计划编号，成品类，计划状态。
    {
        String[] a = {"string", "date", "date", "string"};
        String[] b = {id, sdate, edate, zt};
        String[][] ans;
        ans = staff.ExcuteSearch("Call Search_Plan_Date(?, ?, ?, ?) ", a, b);
        Plan[] plans = new Plan[ans.length - 1];
        for(int i = 1; i < ans.length; i++)
        {
            Production[] production = this.searchCpOnID(ans[i][2]);
            production[0].setNums(Integer.parseInt(ans[i][3]));
            plans[i - 1] = new Plan(ans[i][0], ans[i][1], production[0], ans[i][4], ans[i][5], ans[i][6], ans[i][7], ans[i][8]);
        }
        return plans;
    }

    public Plan[] searchPlan(String id, String zt) throws SQLException      //存储过程，查询Plan返回计划编号，成品类，计划状态。
    {
        String[] a = {"string", "string"};
        String[] b = {id, zt};
        String[][] ans;
        ans = staff.ExcuteSearch("Call Search_Plan_IdZt(?, ?) ", a, b);
        Plan[] plans = new Plan[ans.length - 1];
        for(int i = 1; i < ans.length; i++)
        {
            Production[] production = this.searchCpOnID(ans[i][2]);
            production[0].setNums(Integer.parseInt(ans[i][3]));
            for(int j = 0; j < production[0].getRaws().length; j++)
            {
                production[0].getRaws()[j].setRaw_num(production[0].getRaws()[j].getRaw_num() * production[0].getNums());
            }
            plans[i - 1] = new Plan(ans[i][0], ans[i][1], production[0], ans[i][4], ans[i][5], ans[i][6], ans[i][7], ans[i][8]);
        }
        return plans;
    }

    public void changeZtDo(String id, int now, int aim, String fzr) throws SQLException//将状态改为执行中，修改负责人
    {
        String[] a = {"string", "int", "int", "string"};
        String[] b = {id, String.valueOf(now), String.valueOf(aim), fzr};
        String sql = "Call Do_Plan(?, ?, ?, ?)";
        this.staff.Does(sql);
    }

    public int changeZtOver(String id) throws SQLException//将状态改为待审核,修改完成时间并删除进度。
    {
        String[] a = {"string"};
        String[] b = {id};
        String sql = "Call Over_Plan(?)";
        int res = staff.ExcuteDoes(sql, a, b);
        return res;
    }

    public int updatePlan(String id, int now) throws SQLException  //将ID的now值改为now， 然后判断每次update判断是否超过了AIM，超过了改订单状态；
    {
        String[] a = {"string", "int"};
        String[] b = {id, String.valueOf(now)};
        String sql = "Call Push_Plan(?, ?)";
        int res = this.staff.ExcuteDoes(sql, a, b);
        return res;
    }
}
