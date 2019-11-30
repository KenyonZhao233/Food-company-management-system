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
    public Staff staff;

    public Raw(Staff staff)
    {
        raw_id = new String();
        raw_name = new String();
        raw_bzq = 0;
        raw_pri = 0;
        this.staff = staff;
    }

    public void getInformation()
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
}
