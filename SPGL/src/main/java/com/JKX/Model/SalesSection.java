package com.JKX.Model;
import com.JKX.Model.Table.Custom;
import com.JKX.Model.Table.CustomType;
import com.JKX.Model.Table.Order;
import com.JKX.Model.Table.Production;
import com.JKX.Mysql.MysqlConnect;
import com.sun.org.apache.xpath.internal.operations.Or;
import javafx.scene.control.Alert;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


public class SalesSection{
    Staff staff;
    private CustomType[] customTypes;

    public SalesSection(Staff staff)
    {
        this.staff=staff;
        String[][] ans;
        String sql = "select * from customer_type";
        try {
            ans = this.staff.Search(sql);
            this.customTypes = new CustomType[ans.length - 1];
            for(int i = 1; i < ans.length; i++)
            {
                customTypes[i - 1] = new CustomType(ans[i][0], ans[i][1], Float.valueOf(ans[i][2]));
            }
        }
        catch (SQLException se)
        {
            Alert _alert = new Alert(Alert.AlertType.ERROR);
            _alert.setTitle("错误");
            _alert.setHeaderText("查询失败");
            _alert.setContentText("系统错误");
            _alert.showAndWait();
        }
    }

    public Staff getStaff() {
        return staff;
    }

    public Order[] searchOrderOnId(String CustomerID) throws SQLException
    {
        //String sql="select * from `order` where order_zt = '已完成' and order_custom = '"+CustomerID+"'";
        String sql="SELECT orders.order_id, orders.order_zt, orders.order_date, orders.order_type, orders.order_custom " +
                    "from orders " +
                    "where orders.order_custom = '" + CustomerID + "' " +
                    "GROUP BY orders.order_id, orders.order_zt, orders.order_date, orders.order_type, orders.order_custom";
        String[][] temp = staff.Search(sql);
        Order[] orders = new Order[temp.length - 1];
        for(int i = 1; i < temp.length; i++)
        {
            Production[] productions = this.searchCpOnOrder(temp[i][0]);
            Custom[] customs = this.searchCusOnPhone(temp[i][4]);
            orders[i - 1] = new Order(temp[i][0], temp[i][2], temp[i][3], temp[i][1], customs[0], productions);
        }
        System.out.println(orders.length);
        return orders;
    }

    public Production[] searchCpOnOrder(String order_id) throws SQLException
    {
        String sql = "SELECT orders.order_custom, orders.order_num \n" +
                    "FROM orders\n" +
                    "WHERE orders.order_id = '" + order_id + "'";
        String[][] ans = staff.Search(sql);
        Production[] productions = new Production[ans.length - 1];
        for(int i = 1; i < productions.length; i++)
            productions[i - 1] = new Production("", ans[i][0], 0, 0, 0, 0, 0, null, Integer.valueOf(ans[i][1]));
        return productions;
    }

    public Custom[] searchCusOnPhone(String phone) throws SQLException
    {
        //通过手机号查询客户的详细信息
        String sql = "SELECT customer.customer_id, customer.customer_name, customer.customer_tp, customer.customer_wg, customer.customer_tele " +
                     "FROM customer " +
                     "WHERE customer.customer_tele = '" + phone + "'";
        String[][] ans = staff.Search(sql);
        Custom[] customs = new Custom[ans.length - 1];
        for(int i = 1; i < ans.length; i++)
        {
            customs[i - 1] = new Custom(ans[i][0], customTypes[Integer.valueOf(ans[i][2]) - 1], ans[i][1], Integer.valueOf(ans[i][5]), ans[i][6]);
        }
        return customs;
    }

    public Custom[] searchCusOnId(String id) throws SQLException
    {
        //通过客户编号查询客户的详细信息
        String sql = "SELECT customer.customer_id, customer.customer_name, customer.customer_tp, customer.customer_wg, customer.customer_tele " +
                     "FROM customer" +
                     "WHERE customer.customer_id = '" + id + "'";
        String[][] ans = staff.Search(sql);
        Custom[] customs = new Custom[ans.length - 1];
        for(int i = 1; i < ans.length; i++)
        {
            customs[i - 1] = new Custom(ans[i][0], customTypes[Integer.valueOf(ans[i][2]) - 1], ans[i][1], Integer.valueOf(ans[i][5]), ans[i][6]);
        }
        return customs;
    }

    public int insertCustomer(String id, String type, String name, String phone) throws SQLException
    {
        String sql = "insert into customer values('"+ id +"',"+ this.GetTypeidByTypename(type) + ",'"+name+"',0,'"+ phone +"')";
        int res = this.staff.Does(sql);
        return res;
    }

    public String GetNumber()
    {
        //生成计划号
        //生成原则：xx + 年（4位）+月（2位）+日（2位）+时（2位）+分（2位）+秒（2位）+3 位随机数
        String num = GetRandomString(3);//自动生成一个3位随机数

        String ordernum = "PL" + String.format("%04d", Calendar.getInstance().get(Calendar.YEAR))
                + String.format("%02d", Calendar.getInstance().get(Calendar.MONTH))
                + String.format("%02d", Calendar.getInstance().get(Calendar.DATE))
                + String.format("%02d", Calendar.getInstance().get(Calendar.HOUR_OF_DAY))
                + String.format("%02d", Calendar.getInstance().get(Calendar.MINUTE))
                + String.format("%02d", Calendar.getInstance().get(Calendar.SECOND))
                + num;

        return ordernum;
    }

    public String GetRandomString(int Length)
    {
        String buffer = "0123456789";// 随机字符中也可以为汉字（任何）
        StringBuilder sb = new StringBuilder();
        Random r = new Random();
        int range = buffer.length();
        for (int i = 0; i < Length; i++)
        {
            sb.append(buffer.substring(r.nextInt(range), 1));
        }
        return sb.toString();
    }

    public String CreateCustomID() throws SQLException
    {
        String sql="select max(customer_id) from customer";
        String[][] num=staff.Search(sql);
        int n=Integer.valueOf(num[1][0])+1;
        return String.valueOf(String.format("%05d",n));
    }

    public String GetTypeidByTypename(String name) throws SQLException
    {
        for(int i = 0; i < customTypes.length; i++)
        {
            if(customTypes[i].getType_name().equals(name))
                return customTypes[i].getType_id();
        }
        return "";
    }
}
