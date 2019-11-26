package com.JKX.Mysql;

import jdk.nashorn.internal.codegen.types.Type;

import javax.rmi.ssl.SslRMIClientSocketFactory;
import java.sql.*;

public class MysqlConnect {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://cdb-9mi11dym.bj.tencentcdb.com:10229/FoodManage";
    Connection conn;
    public String USER;
    public String PASS;

    public MysqlConnect(String USER, String PASS) {
        this.PASS = PASS;
        this.USER = USER;
        this.conn = null;
    }

    public boolean Connect() {
        try {
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            return true;
        } catch (SQLException se) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public String[][] Search(String sql) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        ResultSetMetaData rsmd = rs.getMetaData();
        int len = rsmd.getColumnCount();
        rs.last();
        String[][] ans = new String[rs.getRow() + 1][len];
        for (int i = 0; i < len; i++) {
            ans[0][i] = rsmd.getColumnLabel(i + 1);
        }
        int cnt = 1;
        rs.beforeFirst();
        while (rs.next()) {
            for (int i = 0; i < len; i++)
                ans[cnt][i] = rs.getString(i + 1);
            cnt++;
        }
        stmt.close();
        rs.close();
        return ans;
    }

    public String[][] ExcuteSearch(String sql) throws SQLException  //执行一个关于查询的存储过程；
    {
        CallableStatement cs = conn.prepareCall(sql);
        ResultSet rs = cs.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        rs.last();
        String[][] ans = new String[rs.getRow() + 1][rsmd.getColumnCount()];
        for (int i = 0; i < rsmd.getColumnCount(); i++)
            ans[0][i] = rsmd.getColumnName(i + 1);
        int cnt = 1;
        rs.beforeFirst();
        while (rs.next()) {
            for (int i = 0; i < rsmd.getColumnCount(); i++)
                ans[cnt][i] = rs.getString(i + 1);
            cnt++;
        }
        cs.close();
        rs.close();
        return ans;
    }

    public String[][] ExcuteSearch(String sql, String[] a, String[] b) throws SQLException  //执行一个关于查询的存储过程,a数组为传入参数的类型，b为参数的值；
    {
        CallableStatement cs = conn.prepareCall(sql);
        for(int i = 0; i < a.length; i++)
        {
            if(a[i].equals("int"))
                cs.setInt(i + 1, Integer.parseInt(b[i]));
            else if(a[i].equals("float"))
                cs.setFloat(i + 1, Float.parseFloat(b[i]));
            else if(a[i].equals("date"))
                cs.setDate(i + 1, Date.valueOf(b[i]));
            else if(a[i].equals("string"))
                cs.setString(i + 1, b[i]);
        }
        ResultSet rs = cs.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        rs.last();
        String[][] ans = new String[rs.getRow() + 1][rsmd.getColumnCount()];
        for (int i = 0; i < rsmd.getColumnCount(); i++)
            ans[0][i] = rsmd.getColumnName(i + 1);
        int cnt = 1;
        rs.beforeFirst();
        while (rs.next()) {
            for (int i = 0; i < rsmd.getColumnCount(); i++)
                ans[cnt][i] = rs.getString(i + 1);
            cnt++;
        }
        cs.close();
        rs.close();
        return ans;
    }

//    public  String[][] ExcuteSearchDate(String sql, Date date1, Date date2) throws SQLException
//    {
//        String[][] ans;
//        CallableStatement cs = conn.prepareCall(sql);
//        cs.setDate(1, date1);
//        cs.setDate(2, date2);
//        ResultSet rs = cs.executeQuery();
//        ResultSetMetaData rsmd = rs.getMetaData();
//        ans = new String[rs.getRow()][rsmd.getColumnCount()];
//        for (int i = 0; i < rsmd.getColumnCount(); i++)
//            ans[0][i] = rsmd.getColumnName(i);
//        int cnt = 1;
//        while (rs.next()) {
//            for (int i = 0; i < rsmd.getColumnCount(); i++)
//                ans[cnt][i] = rs.getString(i);
//            cnt++;
//        }
//        cs.close();
//        rs.close();
//        return ans;
//    }

    public int ExcuteDoes(String sql, String[] a, String[] b) throws SQLException  //执行增删改的存储过程，a数组为传入参数的类型，b为参数的值, 返回更新的行数
    {
        CallableStatement cs = null;
        cs = conn.prepareCall(sql);
        for(int i = 0; i < a.length; i++)
        {
            if(a[i].equals("int"))
                cs.setInt(i + 1, Integer.parseInt(b[i]));
            else if(a[i].equals("float"))
                cs.setFloat(i + 1, Float.parseFloat(b[i]));
            else if(a[i].equals("date"))
                cs.setDate(i + 1, Date.valueOf(b[i]));
            else if(a[i].equals("string"))
                cs.setString(i + 1, b[i]);
        }
        int rows = cs.executeUpdate();
        cs.close();
        return rows;
    }

    public String[] ExcuteDoesReturn(String sql, String[] a, String[] b, String[] c) throws SQLException //执行存储过程，a数组为传入参数的类型，b为参数的值，c为output的类型，返回output值
    {
        CallableStatement cs = null;
        cs = conn.prepareCall(sql);
        int lena = a.length;
        int lenc = c.length;
        for(int i = 0; i < lena; i++) {
            if (a[i].equals("int"))
                cs.setInt(i + 1, Integer.parseInt(b[i]));
            else if (a[i].equals("float"))
                cs.setFloat(i + 1, Float.parseFloat(b[i]));
            else if (a[i].equals("date"))
                cs.setDate(i + 1, Date.valueOf(b[i]));
            else if (a[i].equals("string"))
                cs.setString(i + 1, b[i]);
        }
        for(int i = 0; i < lenc; i++)
        {
            if(c[i].equals("int"))
                cs.registerOutParameter(i + lena, Types.INTEGER);
            else if(c[i].equals("float"))
                cs.registerOutParameter(i + lena, Types.FLOAT);
            else if(c[i].equals("date"))
                cs.registerOutParameter(i + lena, Types.DATE);
            else if(c[i].equals("string"))
                cs.registerOutParameter(i + lena, Types.VARCHAR);
        }
        String[] ans = new String[lenc];
        for(int i = 0; i < lenc; i++){
            if(c[i].equals("int"))
                ans[i] = String.valueOf(cs.getInt(i + 1));
            else if(c[i].equals("float"))
                ans[i] = String.valueOf(cs.getFloat(i + 1));
            else if(c[i].equals("date"))
                ans[i] = String.valueOf(cs.getDate(i + 1));
            else if(c[i].equals("string"))
                ans[i] = String.valueOf(cs.getString(i + 1));
        }
        cs.close();
        return ans;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        conn.close();
    }
}
