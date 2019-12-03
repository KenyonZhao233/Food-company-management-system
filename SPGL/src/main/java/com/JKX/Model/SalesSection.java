package com.JKX.Model;
import com.JKX.Model.Table.Order;
import com.JKX.Model.Table.Production;
import com.JKX.Mysql.MysqlConnect;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.sql.Date;
import java.sql.SQLException;


public class SalesSection{
    Staff staff;
    public Order[] searchOrderOnId(String CustomerID) throws SQLException
    {
        //String sql="select * from `order` where order_zt = '已完成' and order_custom = '"+CustomerID+"'";
        String sql="SELECT orders.order_id, orders.order_zt, orders.order_date, orders.order_type, orders.order_custom " +
                    "from orders " +
                    "where orders.order_custom = '" + CustomerID + "' " +
                    "GROUP BY orders.order_id, orders.order_zt, orders.order_date, orders.order_type, orders.order_custom";
        System.out.println(sql);
        String[][] temp = staff.Search(sql);
        Order[] orders = new Order[temp.length - 1];
        for(int i = 1; i < temp.length; i++)
        {
            Production[] productions = this.searchCpOnOrder(temp[i][0]);
            orders[i - 1] = new Order(temp[i][0], Date.valueOf(temp[i][2]), temp[i][3], temp[i][1], temp[i][4], productions);
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
}
