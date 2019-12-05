package com.JKX.Model;

import com.JKX.Mysql.MysqlConnect;
import javafx.scene.control.Alert;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Staff {
    public String Uid;
    public String Name;
    public String sex;
    public Date date;
    public int[][] zw;
    public boolean isGly;
    public boolean isGlz;
    public String password;
    private MysqlConnect mysqlConnect;
    private String sfz;

    public Staff(String uid, String password)
    {
        this.Uid = uid;
        this.password = password;
        zw = new int[7][3];
        isGly = false;
        isGlz = false;
        this.mysqlConnect = new MysqlConnect(uid, password);
    }

    public void getInformation()
    {

    }

    public boolean Login()  //修改登录时间
    {
        mysqlConnect.USER = Uid;
        mysqlConnect.PASS = password;
        if(!mysqlConnect.Connect())
            return false;
        //使用存储过程修改其登录时间
        //查询在数据库中查询该员工的其他信息，并保存在对象的变量中。
        try {
            String userInform[][] = mysqlConnect.Search("select * from staff , staff_job where staff.staff_id = '" + this.Uid + "' and staff.staff_id = staff_job.staff_id");
            if(userInform.length == 1)
                return false;
            this.Name = userInform[1][1];
            this.sex = userInform[1][2];
            this.sfz = userInform[1][3];
            this.date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(userInform[1][4]);
            for(int i  = 1; i < userInform.length; i++)
            {
                int tag = 0;
                if(userInform[i][8].equals("业务人员"))
                    tag = 0;
                else if(userInform[i][8].equals("管理者"))
                {
                    tag = 1;
                    isGlz = true;
                }
                else if(userInform[i][8].equals("管理员"))
                {
                    isGly = true;
                    tag = 2;
                }
                if(userInform[i][7].equals("财务部"))
                    zw[1][tag] = 1;
                else if(userInform[i][7].equals("销售部"))
                    zw[2][tag] = 1;
                else if(userInform[i][7].equals("成品库"))
                    zw[3][tag] = 1;
                else if(userInform[i][7].equals("原料库"))
                    zw[4][tag] = 1;
                else if(userInform[i][7].equals("生产车间"))
                    zw[5][tag] = 1;
                else if(userInform[i][7].equals("生产计划部"))
                    zw[6][tag] = 1;
                else if(userInform[i][7].equals("系统"))
                    zw[0][tag] = 1;
            }
        }
        catch (SQLException se) {
            se.printStackTrace();
            return false;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return true;
    }

    public String[][] Search(String sql) throws SQLException
    {
        return mysqlConnect.Search(sql);
    }

    public int Does(String sql) throws SQLException
    {
        return mysqlConnect.Does(sql);
    }

    public String[][] ExcuteSearch(String sql) throws SQLException
    {
        return mysqlConnect.ExcuteSearch(sql);
    }

    public String[][] ExcuteSearch(String sql, String[] a, String[] b) throws SQLException
    {
        return mysqlConnect.ExcuteSearch(sql, a, b);
    }

    public int ExcuteDoes(String sql, String[] a, String[] b) throws SQLException
    {
        return mysqlConnect.ExcuteDoes(sql, a, b);
    }

    public String[] ExcuteDoesReturn(String sql, String[] a, String[] b, String[] c) throws SQLException
    {
        return mysqlConnect.ExcuteDoesReturn(sql, a, b, c);
    }
    public void showAlert(Alert.AlertType alertType, String title, String head, String content)
    {
        Alert _alert = new Alert(alertType);
        _alert.setTitle(title);
        _alert.setHeaderText(head);
        _alert.setContentText(content);
        _alert.showAndWait();
    }
}
