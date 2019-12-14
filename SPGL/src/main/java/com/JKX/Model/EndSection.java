package com.JKX.Model;

import com.JKX.Mysql.MysqlConnect;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class EndSection {

    private Staff staff;

    public EndSection(Staff s)
    {
        this.staff = s;
    }
    public void getInformation()
    {

    }

    public String getRight(int i) throws SQLException {
        String ans[][] = staff.Search("select * from right_end where staff_id = '" + staff.Uid +"'");
        return ans[1][i];
    }

    public Staff getStaff() {
        return staff;
    }

    public String[][] getInform() throws SQLException
    {
        return staff.Search("select * from product, product_ck where product.product_id = product_ck.product_id order by product_date DESC");
    }

    public String[][] search(ComboBox query_type, TextField query_text) throws SQLException
    {
        String str = "select * from product, product_ck where product.product_id = product_ck.product_id ";
        if(query_type.getValue() == "成品编号" && (!query_text.getText().isEmpty() || query_text.getText() == null))
        {
            str = str + "and product_ck.product_id like '%" + query_text.getText() + "%' ";
        }
        if(query_type.getValue() == "仓库编号" && (!query_text.getText().isEmpty() || query_text.getText() == null))
        {
            str = str + "and product_ck.product_in like '%" + query_text.getText() + "%' ";
        }
        str = str + "order by product_date DESC";
        return staff.Search(str);
    }

    public String[][] search_d(String destroy_text1,String destroy_text2) throws SQLException
    {
        String str = "select * from product, product_ck where product.product_id = product_ck.product_id ";
        if(!destroy_text1.isEmpty() || destroy_text1 == null)
        {
            str = str + "and product_ck.product_id like '%" + destroy_text1 + "%' ";
        }
        if(!destroy_text2.isEmpty() || destroy_text2 == null)
        {
            str = str + "and product_ck.product_in like '%" + destroy_text2 + "%' ";
        }
        str = str + "order by product_date DESC";
        return staff.Search(str);
    }

    public boolean in(String id, String rm, String in, String Uid) throws SQLException
    {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String t = "insert into product_ck values('" +formatter.format(date) +  "','" + id + "'," + rm + ",'" + in + "')";
        staff.Does(t);
        t = "insert into product_rec values('" +formatter.format(date) +  "','" + id + "','入库'," + rm + ",'" + Uid + "')";
        staff.Does(t);
        return true;
    }

    public boolean destory(String id, String time, String rm, String Uid) throws SQLException
    {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        staff.Does("insert into product_rec values('" +formatter.format(date) +  "','" + id + "','销毁'," + rm + ",'" + Uid + "')");
        staff.Does("delete from product_ck where product_id = '" + id + "' and product_date = '" + time + "'");
        return true;
    }

    public String[][] ck() throws SQLException
    {
        return staff.Search("select * from storehouse");
    }

    public String[][] kind() throws SQLException
    {
        return staff.Search("select * from product");
    }

    public String[][] call() throws SQLException
    {
        return staff.Search("select product_name, IFNULL(sum,0)  from product_ck_now,product where product.product_id = product_ck_now.product_id");
    }

    public String[][] itemorder_pre() throws SQLException
    {
        return staff.Search("select DISTINCT order_id,order_custom,order_date from orders where order_zt = '待发货'");
    }

    public String[][] itemorder_end() throws SQLException
    {
        return staff.Search("select DISTINCT order_id,order_custom,order_date from orders where order_zt = '待收货'");
    }

    public String[][] itemorder(String id) throws SQLException
    {
        return staff.Search("select order_product,order_num  from orders where order_id = '" + id + "'");
    }

    public boolean Send(String id) throws SQLException
    {
        String ans[][] = staff.Search("select count(*) - sum(order_num <= sum) from orders,product,product_ck_now where order_id = '"
                + id + "' and orders.order_product = product.product_name and product.product_id = product_ck_now.product_id");
        if(ans[1][0].equals("0"))
        {
            if(ans[1][0].equals("预付款")){
                ans = staff.Search("select order_type,order_date,order_custom,mn_re from orders,advance where advance.order_id = '"+
                        id
                        +"' and advance.order_id = orders.order_id");
                staff.Does("update orders set order_zt = '待付全款' where order_id = '"+ id +"'");
                staff.Does("insert unpaid values('"+ id +"','"+ans[1][1].substring(0,19) + "'," + ans[1][3] + ",'" +
                            ans[1][2] + "')");
            }
            else
                staff.Does("update orders set order_zt = '待收货' where order_id = '"+ id +"'");
            return true;
        }else{
            return false;
        }
    }

    public boolean Out(String id) throws SQLException
    {
        staff.Does("update orders set order_zt = '已完成' where order_id = '"+ id +"'");
        return true;
    }

    public boolean Output(String name, String time, int mn,String Uid) throws SQLException
    {
        String ans[][] = staff.Search("select product_rm from product_ck where product_date = '"+ time +"'");
        Integer res = Integer.parseInt(ans[1][0]) - mn;
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        if(res > 0){
            String id[][] = staff.Search("select product_id from product where product_name = '"+ name +"'");
            staff.Does("update product_ck set product_rm = " + res + " where product_date = '"+ time +"'");
            staff.Does("insert into product_rec values('" +formatter.format(date) +  "','" + id[1][0] + "','出库'," + mn + ",'" + Uid + "')");
        }
        else if(res == 0){ String id[][] = staff.Search("select product_id from product where product_name = '"+ name +"'");
        staff.Does("delete from product_ck  where product_date = '"+ time +"'");
        staff.Does("insert into product_rec values('" +formatter.format(date) +  "','" + id[1][0]  + "','出库'," + mn + ",'" + Uid + "')");
        }
        else{
            return false;
        }
        return true;
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
