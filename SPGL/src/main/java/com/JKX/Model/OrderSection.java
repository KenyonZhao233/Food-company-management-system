package com.JKX.Model;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderSection {
    private Staff sta;

    public OrderSection(Staff s) throws SQLException
    {
        this.sta = s;
        if(!sta.Login())
            throw new SQLException();
    }

    public String[][] unpaidSearch() throws SQLException
    {
        return sta.Search("select * from unpaid");
    }

    public String[][] refundSearch() throws SQLException
    {
        return sta.Search("select * from refund where return_state = '待退款'");
    }

    public String[][] unpaidQuery(String s) throws SQLException
    {
        return sta.Search("select * from unpaid where order_id like '%"+ s + "%'");
    }

    public String[][] refundQuery(String s) throws SQLException
    {
        return sta.Search("select * from refund where return_id like '%"+ s + "%' and return_state = '待退款'");
    }

    public boolean unpaid(String id, String mn) throws SQLException
    {
        String ans[][] = sta.Search("select count(*) from finance");
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());

        String t = "insert into finance values(" + "'" + ans[1][0].format("%8d", Integer.parseInt(ans[1][0])).replace(" ", "0") + "','"+
        sta.Uid + "',"+ mn + ",'" + formatter.format(date) +"','"+ id + "')";
        sta.Dose(t);
        sta.Dose("delete from unpaid where order_id = '" + id + "'");
        return true;
    }

    public boolean refund(String id, String mn) throws SQLException
    {
        String ans[][] = sta.Search("select count(*) from finance");
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());

        String t = "insert into finance values(" + "'" + ans[1][0].format("%8d", Integer.parseInt(ans[1][0])).replace(" ", "0") + "','"+
                sta.Uid + "',-"+ mn + ",'" + formatter.format(date) +"','退款编号："+ id + "')";
        sta.Dose(t);
        sta.Dose("update refund set return_state = '已退款' where return_id = '" + id + "'");
        return true;
    }
}
