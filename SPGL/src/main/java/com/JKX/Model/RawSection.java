package com.JKX.Model;

import com.JKX.Mysql.MysqlConnect;

import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class RawSection {
    public Staff staff;

    public RawSection(Staff staff)
    {
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
