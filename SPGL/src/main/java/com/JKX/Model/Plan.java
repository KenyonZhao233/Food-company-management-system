package com.JKX.Model;

import java.sql.SQLException;

public class Plan {
    private Staff sta;
    //可以向其中添加你所需要的相应的属性，在初始化中连接数据库，查询出你所需要的属性并将其初始化;

    public Plan(Staff s) throws SQLException
    {
        this.sta = s;
        if(!sta.Login())
            throw new SQLException();
    }

    //接下来开始写成品库所对应的操作
    //查询：写相应的sql语句，用sta中的函数调用select或存储过程，并返回一个二维数组；
    //修改：写相应的sql语句，用sta中的函数调用相应的存储过程，并返回影响的行数；
}
