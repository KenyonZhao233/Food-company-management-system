package com.JKX.Model;

import com.JKX.Mysql.MysqlConnect;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class RawSection {

    private Staff staff;

    public RawSection(Staff s) throws SQLException
    {
        this.staff = s;
        if(!staff.Login())
            throw new SQLException();
    }

    public Staff getStaff() {
        return staff;
    }

    public String getRight(int i) throws SQLException {
        String ans[][] = staff.Search("select * from right_raw where staff_id = '" + staff.Uid +"'");
        return ans[1][i];
    }

    public void getInformation()
    {

    }

    public boolean Output(String id, String time, int mn,String Uid) throws SQLException
    {
        String ans[][] = staff.Search("select raw_rm from raw_ck where raw_date = '"+ time +"'");
        Integer res = Integer.parseInt(ans[1][0]) - mn;
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        if(res > 0){
            staff.Does("update raw_ck set raw_rm = " + res + " where raw_date = '"+ time +"'");
            staff.Does("insert into raw_rec values('" +formatter.format(date) +  "','" + id + "','出库'," + mn + ",'" + Uid + "')");
        }
        else if(res == 0){
            staff.Does("delete raw_ck  where raw_date = '"+ time +"'");
            staff.Does("insert into raw_rec values('" +formatter.format(date) +  "','" + id  + "','出库'," + mn + ",'" + Uid + "')");
        }
        else{
            return false;
        }
        return true;
    }

    public String[][] getInform() throws SQLException
    {
        return staff.Search("select * from raw, raw_ck where raw.raw_id = raw_ck.raw_id order by raw_date DESC");
    }

    public String[][] search(ComboBox query_type, TextField query_text) throws SQLException
    {
        String str = "select * from raw, raw_ck where raw.raw_id = raw_ck.raw_id ";
        if(query_type.getValue() == "原料编号" && (!query_text.getText().isEmpty() || query_text.getText() == null))
        {
            str = str + "and raw_ck.raw_id like '%" + query_text.getText() + "%' ";
        }
        if(query_type.getValue() == "仓库编号" && (!query_text.getText().isEmpty() || query_text.getText() == null))
        {
            str = str + "and raw_ck.raw_in like '%" + query_text.getText() + "%' ";
        }
        str = str + "order by raw_date DESC";
        return staff.Search(str);
    }

    public String[][] search_o(String out_text1, String out_text2) throws SQLException
    {
        String str = "select * from raw, raw_ck where raw.raw_id = raw_ck.raw_id ";
        if(!out_text1.isEmpty() || out_text1 == null)
        {
            str = str + "and raw_ck.raw_id like '%" + out_text1 + "%' ";
        }
        if(!out_text2.isEmpty() || out_text2 == null)
        {
            str = str + "and raw_ck.raw_in like '%" + out_text2 + "%' ";
        }
        str = str + "order by raw_date DESC";
        return staff.Search(str);
    }

    public String[][] search_d(String destroy_text1, String destroy_text2) throws SQLException
    {
        String str = "select * from raw, raw_ck where raw.raw_id = raw_ck.raw_id ";
        if(!destroy_text1.isEmpty() || destroy_text1 == null)
        {
            str = str + "and raw_ck.raw_id like '%" + destroy_text1 + "%' ";
        }
        if(!destroy_text2.isEmpty() || destroy_text2 == null)
        {
            str = str + "and raw_ck.raw_in like '%" + destroy_text2 + "%' ";
        }
        str = str + "order by raw_date DESC";
        return staff.Search(str);
    }

    public boolean in(String id, String rm, String in) throws SQLException
    {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String t = "insert into raw_ck values('" +formatter.format(date) +  "','" + id + "'," + rm + ",'" + in + "')";
        staff.Does(t);
        return true;
    }

    public boolean destory(String id, String time) throws SQLException
    {
        staff.Does("delete from raw_ck where raw_id = '" + id + "' and raw_date = '" + time + "'");
        return true;
    }

    public String[][] ck() throws SQLException
    {
        return staff.Search("select * from storehouse");
    }

    public String[][] kind() throws SQLException
    {
        return staff.Search("select * from raw");
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
