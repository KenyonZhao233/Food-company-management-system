package com.JKX.Model;
import com.JKX.Model.Table.Custom;
import com.JKX.Model.Table.CustomType;
import com.JKX.Model.Table.Order;
import com.JKX.Model.Table.Production;
import com.JKX.Mysql.MysqlConnect;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.sql.SQLException;
import java.util.Date;


public class SalesSection{
    Staff staff;
    public SalesSection(Staff staff)
    {
        this.staff=staff;
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
        String sql = "SELECT customer.customer_id, customer.customer_name, customer.customer_tp, customer_type.type_name, customer_type.type_pay, customer.customer_wg, customer.customer_tele " +
                     "FROM customer, customer_type " +
                     "WHERE customer.customer_tele = '" + phone + "' AND customer.customer_id = customer_type.type_id";
        String[][] ans = staff.Search(sql);
        Custom[] customs = new Custom[ans.length - 1];
        for(int i = 1; i < ans.length; i++)
        {
            CustomType customType = new CustomType(ans[i][2], ans[i][3], Float.valueOf(ans[i][4]));
            customs[i - 1] = new Custom(ans[i][0], customType, ans[i][1], Integer.valueOf(ans[i][5]), ans[i][6]);
        }
        return customs;
    }

    public Custom[] searchCusOnId(String id) throws SQLException
    {
        //通过客户编号查询客户的详细信息
        String sql = "SELECT customer.customer_id, customer.customer_name, customer.customer_tp, customer_type.type_name, customer_type.type_pay, customer.customer_wg, customer.customer_tele " +
                     "FROM customer, customer_type " +
                     "WHERE customer.customer_id = '" + id + "' AND customer.customer_id = customer_type.type_id";
        String[][] ans = staff.Search(sql);
        Custom[] customs = new Custom[ans.length - 1];
        for(int i = 1; i < ans.length; i++)
        {
            CustomType customType = new CustomType(ans[i][2], ans[i][3], Float.valueOf(ans[i][4]));
            customs[i - 1] = new Custom(ans[i][0], customType, ans[i][1], Integer.valueOf(ans[i][5]), ans[i][6]);
        }
        return customs;
    }
}
