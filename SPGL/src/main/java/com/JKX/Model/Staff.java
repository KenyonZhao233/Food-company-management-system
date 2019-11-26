package com.JKX.Model;

import com.JKX.Mysql.MysqlConnect;

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
    public Set<String> zw;
    public int[] gly, glz;
    public String password;
    private MysqlConnect mysqlConnect;
    private String sfz;

    public Staff(String uid, String password)
    {
        this.Uid = uid;
        this.password = password;
        zw = new TreeSet<String>();
        gly = new int[7];
        glz = new int[7];
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
            String userInform[][] = mysqlConnect.Search("select * from staff where staff_id = '" + this.Uid + "'");
            if(userInform.length == 1)
                return false;
            this.Name = userInform[1][2];
            this.sex = userInform[1][3];
            this.sfz = userInform[1][4];
            this.date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(userInform[1][5]);
            for(int i = 1; i < userInform.length; i++)
            {
                zw.add(userInform[i][1]);
                if(userInform[i][1].equals("管理员"))
                {
                    String glyInform[][] = mysqlConnect.Search("select * from staff_gly where staff_id = '" + this.Uid + "'");
                    if(glyInform.length == 1)
                        return false;
                    for(int j  = 1; j < glyInform.length; j++)
                    {
                        if(glyInform[j][1].equals("财务部"))
                            gly[1] = 1;
                        else if(glyInform[i][1].equals("销售部"))
                            gly[2] = 1;
                        else if(glyInform[i][1].equals("成品库"))
                            gly[3] = 1;
                        else if(glyInform[i][1].equals("原料库"))
                            gly[4] = 1;
                        else if(glyInform[i][1].equals("生产车间"))
                            gly[5] = 1;
                        else if(glyInform[i][1].equals("生产计划部"))
                            gly[6] = 1;
                        else if(glyInform[i][1].equals("系统"))
                            gly[0] = 1;
                    }
                }
                else if (userInform[i][1].equals("管理者"))
                {
                    String glzInform[][] = mysqlConnect.Search("select * from staff_glz where staff_id = '" + this.Uid + "'");
                    if(glzInform.length == 1)
                        return false;
                    for(int j  = 1; j < glzInform.length; j++)
                    {
                        if(glzInform[j][1].equals("财务部"))
                            gly[1] = 1;
                        else if(glzInform[i][1].equals("销售部"))
                            gly[2] = 1;
                        else if(glzInform[i][1].equals("成品库"))
                            gly[3] = 1;
                        else if(glzInform[i][1].equals("原料库"))
                            gly[4] = 1;
                        else if(glzInform[i][1].equals("生产车间"))
                            gly[5] = 1;
                        else if(glzInform[i][1].equals("生产计划部"))
                            gly[6] = 1;
                        else if(glzInform[i][1].equals("系统"))
                            gly[0] = 1;
                    }
                }
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
