package com.JKX.Model;

import com.JKX.Model.Table.Plan;
import com.JKX.Model.Table.Production;
import com.JKX.Model.Table.Raw;

import java.sql.SQLException;

public class PlanSection {
    private Staff staff;
    //可以向其中添加你所需要的相应的属性，在初始化中连接数据库，查询出你所需要的属性并将其初始化;

    public PlanSection(Staff s) throws SQLException
    {
        this.staff = s;
        if(!staff.Login())
            throw new SQLException();
    }

    public Production[] searchCpOnID(String id) throws SQLException
    {
        String[] a = {"string"};
        String[] b = {id};
        String[][] ans;
        ans = staff.ExcuteSearch("Call SearchSearch_CpId(?)", a, b);
        Production[] productions = new Production[ans.length - 1];
        for(int i = 1; i < ans.length; i++)
        {
            String[][] ansRaw;
            ansRaw = staff.Search("SELECT raw.*\n" +
                                             "FROM product_raw, raw\n" +
                                             "where product_id = '" + ans[i][0] + "' AND raw.raw_id = product_raw.raw_id\n" +
                                             "ORDER BY raw.raw_name;");
            Raw[] raws = new Raw[ansRaw.length - 1];
            for(int j = 1; j < ansRaw.length; j++)
                raws[j - 1] = new Raw(ansRaw[j][0], ansRaw[j][1], Integer.parseInt(ansRaw[j][2]), Float.parseFloat(ansRaw[j][3]));
            productions[i - 1] = new Production(ans[i - 1][0], ans[i - 1][1], Float.parseFloat(ans[i - 1][2]),Float.parseFloat(ans[i - 1][3]), Float.parseFloat(ans[i - 1][4]),Integer.parseInt(ans[i - 1][5]), Integer.parseInt(ans[i - 1][6]), raws);
        }
        return productions;
    }

    public Production[] searchCpOnName(String name) throws SQLException
    {
        String[] a = {"string"};
        String[] b = {name};
        String[][] ans;
        ans = staff.ExcuteSearch("Call SearchSearch_CpId(?)", a, b);
        Production[] productions = new Production[ans.length - 1];
        for(int i = 1; i < ans.length; i++)
        {
            String[][] ansRaw;
            ansRaw = staff.Search("SELECT raw.*, product_raw.raw_num\n" +
                    "FROM product_raw, raw\n" +
                    "where product_id = '" + ans[i][0] + "' AND raw.raw_id = product_raw.raw_id\n" +
                    "ORDER BY raw.raw_name;");
            Raw[] raws = new Raw[ansRaw.length - 1];
            for(int j = 1; j < ansRaw.length; j++)
                raws[j - 1] = new Raw(ansRaw[j][0], ansRaw[j][1], Integer.parseInt(ansRaw[j][2]), Float.parseFloat(ansRaw[j][3]), 0,Float.parseFloat(ansRaw[j][4]));
            productions[i - 1] = new Production(ans[i - 1][0], ans[i - 1][1], Float.parseFloat(ans[i - 1][2]),Float.parseFloat(ans[i - 1][3]), Float.parseFloat(ans[i - 1][4]),Integer.parseInt(ans[i - 1][5]), Integer.parseInt(ans[i - 1][6]) ,raws);
        }
        return productions;
    }

    public Raw[] searchRawOnName(String name) throws SQLException
    {
        String[] a = {"string"};
        String[] b = {name};
        String[][] ans;
        ans = staff.ExcuteSearch("Call Search_RawName(?)", a, b);
        Raw[] raws = new Raw[ans.length - 1];
        for(int i = 1; i < ans.length; i++)
        {
            raws[i - 1] = new Raw(ans[i - 1][0], ans[i - 1][1], Integer.parseInt(ans[i - 1][3]), Float.parseFloat(ans[i - 1][2]), Float.parseFloat(ans[i - 1][4]));
        }
        return raws;
    }

    public Raw[] searchRawOnId(String id) throws SQLException
    {
        String[] a = {"string"};
        String[] b = {id};
        String[][] ans;
        ans = staff.ExcuteSearch("Call Search_RawName(?)", a, b);
        Raw[] raws = new Raw[ans.length - 1];
        for(int i = 1; i < ans.length; i++)
        {
            raws[i - 1] = new Raw(ans[i - 1][0], ans[i - 1][1], Integer.parseInt(ans[i - 1][3]), Float.parseFloat(ans[i - 1][2]), Float.parseFloat(ans[i - 1][4]));
        }
        return raws;
    }

    public void searchPlan(String id)       //存储过程，查询Plan返回计划编号，成品类，计划状态。
    {

    }

    public void changePlanOnnum(String id, String num)       //修改待执行的计划的成品数量
    {

    }

    public void changePlanOnZt(String id)
    {

    }

    public void deletePlan(String id)       //删除这个编号的
    {

    }

    public void confirmPlan(String id)
    {

    }

    public void addCp()
    {

    }

    public void deleteCp()
    {

    }

    public void changeCp()
    {

    }

    public void addRaw()
    {

    }

    public void changeRaw()
    {

    }

    public void deleteRaw()
    {

    }

    public void searchXl()
    {

    }

    public String[][] Search(String sql) throws SQLException
    {
        return staff.Search(sql);
    }

    public String[][] ExcuteSearchID(String sql) throws SQLException
    {
        return staff.ExcuteSearch(sql);
    }

    public int ExcuteDoes(String sql, String[] a, String[] b) throws SQLException
    {
        return staff.ExcuteDoes(sql, a, b);
    }

    public String[] ExcuteDoesReturn(String sql, String[] a, String[] b, String[] c) throws SQLException
    {
        return staff.ExcuteDoesReturn(sql, a, b, c);
    }

    //接下来开始写成品库所对应的操作
    //查询：写相应的sql语句，用sta中的函数调用select或存储过程，并返回一个二维数组；
    //修改：写相应的sql语句，用sta中的函数调用相应的存储过程，并返回影响的行数；
}
