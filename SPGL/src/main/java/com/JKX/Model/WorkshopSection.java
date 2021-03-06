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
            int rm = this.searchCpRm(ans[i][0]);
            productions[i - 1] = new Production(ans[i][0], ans[i][1], Float.parseFloat(ans[i][2]),Float.parseFloat(ans[i][3]), Float.parseFloat(ans[i][4]),Integer.parseInt(ans[i][5]), rm, raws, 0);
        }
        return productions;
    }

    public int searchCpRm(String cpId) throws SQLException {
        String[] a = {"string"};
        String[] b = {cpId};
        String[] c = {"int"};
        String sql = "Call Search_CpRm(?, ?)";
        String[] ans = this.staff.ExcuteDoesReturn(sql, a, b, c);
        return Integer.parseInt(ans[0]);
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

    public int[] SearchAdair(String id) throws SQLException
    {
        String sql = "SELECT * " +
                "FROM project_status " +
                "where project_status.project_id = '" + id + "'";
        String[][] ans = this.staff.Search(sql);
        int[] nums = new int[3];
        for(int i = 0; i < 3; i++) {
            nums[i] = Integer.parseInt(ans[1][i + 1]);
        }
        return nums;
    }

    public String findWorkShop(String staffid) throws SQLException
    {
        String sql = "select * from staff_job where staff_id = '" + staffid + "' and staff_bm = '生产车间' and staff_zw = '业务人员'";
        String[][] ans = this.staff.Search(sql);
        if(ans.length == 1)
            return "";
        else
            return ans[1][3];
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
        this.staff.ExcuteDoes(sql, a, b);
    }

    public int changeZtOver(String id, String fzr) throws SQLException//将状态改为待审核,修改完成时间并删除进度。
    {
        String[] a = {"string", "string"};
        String[] b = {id, fzr};
        String sql = "Call Over_Plan(?, ?)";
        int res = staff.ExcuteDoes(sql, a, b);
        return res;
    }

    public int updatePlan(String id, int now, int punum,String fzr) throws SQLException  //将ID的now值改为now， 然后判断每次update判断是否超过了AIM，超过了改订单状态；
    {
        String[] a = {"string", "int", "int", "string"};
        String[] b = {id, String.valueOf(now), String.valueOf(punum), fzr};
        String sql = "Call Push_Plan(?, ?, ?, ?)";
        int res = this.staff.ExcuteDoes(sql, a, b);
        return res;
    }
}
