package com.JKX.Model;

import com.JKX.Model.Table.Production;

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

    public void searchCpOnID(String id)
    {
        String sql = "select * from product where product.product_id = '" + id + "' and product_id";
    }

//    public Production[] searchCpOnName(String name)
//    {
//
//    }

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
