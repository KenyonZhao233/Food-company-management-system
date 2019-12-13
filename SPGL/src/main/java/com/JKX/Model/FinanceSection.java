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

    public Staff getStaff() {
        return sta;
    }

    public String getRight(int i) throws SQLException {
        String ans[][] = sta.Search("select * from right_finance where staff_id = '" + sta.Uid +"'");
        return ans[1][i];
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
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String ans[][] = sta.Search("select count(*) from finance");
        String t = "";

        String tp[][] = sta.Search("SELECT order_type from orders where order_id = '" + id + "'");
        if(tp[1][0].equals("全款")){
            sta.Does("update orders set order_zt = '待发货' where order_id = '" + id + "'");
            t = "insert into finance values(" + "'" + ans[1][0].format("%8d", Integer.parseInt(ans[1][0])).replace(" ", "0") + "','"+
                    sta.Uid + "',"+ mn + ",'" + formatter.format(date) +"','" + id + tp[1][0] + "')";
        }else{
            String st[][] = sta.Search("SELECT order_zt from orders where order_id = '" + id + "'");
            if(st[1][0].equals("待付款")){
                sta.Does("update orders set order_zt = '待发货' where order_id = '" + id + "'");
                String order_id[][] = sta.Search("SELECT order_custom  from orders where order_id = '" + id + "'");
                String mno[][] = sta.Search("SELECT mn_re  from advance where order_id = '" + id + "'");
                t = "insert into finance values(" + "'" + ans[1][0].format("%8d", Integer.parseInt(ans[1][0])).replace(" ", "0") + "','"+
                        sta.Uid + "',"+ mn + ",'" + formatter.format(date) +"','" + id + tp[1][0] + "')";
            }else if(st[1][0].equals("待付全款"))
            {
                sta.Does("update orders set order_zt = '待收货' where order_id = '" + id + "'");
                t = "insert into finance values(" + "'" + ans[1][0].format("%8d", Integer.parseInt(ans[1][0])).replace(" ", "0") + "','"+
                        sta.Uid + "',"+ mn + ",'" + formatter.format(date) +"','" + id + "补齐全款')";
            }
        }

        sta.Does(t);
        sta.Does("delete from unpaid where order_id = '" + id + "'");
        return true;
    }

    public boolean refund(String id, String mn) throws SQLException
    {
        String ans[][] = sta.Search("select count(*) from finance");
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());

        String t = "insert into finance values(" + "'" + ans[1][0].format("%8d", Integer.parseInt(ans[1][0])).replace(" ", "0") + "','"+
                sta.Uid + "',-"+ mn + ",'" + formatter.format(date) +"','退款编号："+ id + "')";
        sta.Does(t);
        sta.Does("update refund set return_state = '已退款' where return_id = '" + id + "'");
        return true;
    }

}
