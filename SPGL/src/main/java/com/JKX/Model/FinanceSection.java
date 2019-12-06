package com.JKX.Model;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FinanceSection {
    private Staff sta;

    public FinanceSection(Staff s) throws SQLException
    {
        this.sta = s;
        if(!sta.Login())
            throw new SQLException();
    }

    public String[][] Search() throws SQLException
    {
        return sta.Search("select * from finance order by finance_id DESC");
    }

    public String[][] Query(String s) throws SQLException
    {
        return sta.Search("select * from finance where finance_id like '%"+ s + "%' order by finance_id DESC");
    }

    public boolean in(String id, String mn) throws SQLException
    {
        String ans[][] = sta.Search("select count(*) from finance");
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());

        String t = "insert into finance values(" + "'" + ans[1][0].format("%8d", Integer.parseInt(ans[1][0])).replace(" ", "0") + "','"+
                sta.Uid + "',"+ mn + ",'" + formatter.format(date) +"','"+ id + "')";
        sta.Dose(t);
        return true;
    }

    public boolean out(String id, String mn) throws SQLException
    {
        String ans[][] = sta.Search("select count(*) from finance");
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());

        String t = "insert into finance values(" + "'" + ans[1][0].format("%8d", Integer.parseInt(ans[1][0])).replace(" ", "0") + "','"+
                sta.Uid + "',-"+ mn + ",'" + formatter.format(date) +"','"+ id + "')";
        sta.Dose(t);
        return true;
    }

}
