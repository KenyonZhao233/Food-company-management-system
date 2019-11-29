package com.JKX.Model;

import com.JKX.Mysql.MysqlConnect;

import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Raw {
    public String raw_id;
    public String raw_name;
    public int raw_bzq;
    public float raw_pri;
    private MysqlConnect mysqlConnect;
    public String Uid;
    public String password;

    public Raw(String uid, String password)
    {
        this.Uid = uid;
        this.password = password;
        raw_id = new String();
        raw_name = new String();
        raw_bzq = 0;
        raw_pri = 0;
        this.mysqlConnect = new MysqlConnect(uid, password);
    }

    public boolean Login()
    {
        mysqlConnect.USER = Uid;
        mysqlConnect.PASS = password;
        if (!mysqlConnect.Connect())
            return false;
        try {
            String rawInform[][] = mysqlConnect.Search("select * from staff where staff_id = '" + this.Uid + "'");
            if (rawInform.length == 1)
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public void getInformation()
    {

    }

    public String[][] Search(String sql) throws SQLException
    {
        return mysqlConnect.Search(sql);
    }

    public String[][] ExcuteSearchID(String sql) throws SQLException
    {
        return mysqlConnect.ExcuteSearch(sql);
    }

    public int ExcuteDoes(String sql, String[] a, String[] b) throws SQLException
    {
        return mysqlConnect.ExcuteDoes(sql, a, b);
    }

    public String[] ExcuteDoesReturn(String sql, String[] a, String[] b, String[] c) throws SQLException
    {
        return mysqlConnect.ExcuteDoesReturn(sql, a, b, c);
    }
}
