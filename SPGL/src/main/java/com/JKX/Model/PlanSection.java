package com.JKX.Model;

import com.JKX.Model.Table.Plan;
import com.JKX.Model.Table.Production;
import com.JKX.Model.Table.Raw;

import java.sql.Date;
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
            productions[i - 1] = new Production(ans[i][0], ans[i][1], Float.parseFloat(ans[i][2]),Float.parseFloat(ans[i][3]), Float.parseFloat(ans[i][4]),Integer.parseInt(ans[i][5]), Integer.parseInt(ans[i][6]), raws, 0);
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
            productions[i - 1] = new Production(ans[i][0], ans[i][1], Float.parseFloat(ans[i][2]),Float.parseFloat(ans[i][3]), Float.parseFloat(ans[i][4]),Integer.parseInt(ans[i][5]), Integer.parseInt(ans[i][6]) ,raws, 0);
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
            raws[i - 1] = new Raw(ans[i][0], ans[i][1], Integer.parseInt(ans[i][3]), Float.parseFloat(ans[i][2]), Float.parseFloat(ans[i][4]));
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
            raws[i - 1] = new Raw(ans[i][0], ans[i][1], Integer.parseInt(ans[i][3]), Float.parseFloat(ans[i][2]), Float.parseFloat(ans[i][4]));
        }
        return raws;
    }

    public void makePlan(String planId, Production production,Date s_date, Date e_date, String planType, String fzr) throws SQLException
    {
        Plan plan = new Plan(planId, planType, production,s_date, e_date, fzr);
        //Insert
    }

    public Plan[] searchPlan(String id) throws SQLException      //存储过程，查询Plan返回计划编号，成品类，计划状态。
    {
        String[] a = {"string"};
        String[] b = {id};
        String[][] ans;
        ans = staff.ExcuteSearch("Call ", a, b);
        Plan[] plans = new Plan[ans.length - 1];
        for(int i = 1; i < ans.length; i++)
        {
            Production[] production = this.searchCpOnID(ans[i - 1][2]);
            //plans[i - 1] = new Plan(ans[i][0], ans[i][1], production[0], Integer.valueOf(ans[i][3]), Date.valueOf(ans[i][4]), Date.valueOf(ans[i][5]), ans[i][6]);
        }
        return plans;
    }

    public void changePlanOnnum(String id, String num)       //修改待执行的计划的成品数量
    {
        //直接写个存储过程
    }

    public void deletePlan(String id)       //删除这个编号的
    {
        //让小界面自己解决这个问题，如果是待执行是可以删除的。
    }

    public void confirmPlan(String id)
    {
        //将待审核的计划进行审核，同时修改为已完成， 添加个结束时间
    }

    public void addCp(Production production) //构造时先添加成品， 然后再添加原料
    {

    }

    public void addCpRaws(String id, Raw raw)    //添加完成品之后，想特定的一个成品添加原料
    {

    }

    public void deleteCpRaws(String id, Raw raw)
    {
        //根据成品编号，删除相应的原料
    }

    public void deleteCp(String id)
    {
        //同样是小界面来解决问题，按编号来删除
    }

    public void changeCp()
    {
        //删除该成品的所有原料信息，逐个添加
    }

    public void addRaw(Raw raw)
    {

    }

    public void changeRaw()
    {
        //可修改原料的单价， 名称， 保质期
    }

    public void deleteRaw(String id)
    {
        //同样是小界面来解决问题，按编号来删除
    }

    public void searchXl(Date d1, Date d2)
    {
        //根据时间来查询，希望能做成柱状图，用大到小排序
        //从订单表里统计数据
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
