package com.JKX.Model;
import com.JKX.Model.Table.Custom;
import com.JKX.Model.Table.CustomType;
import com.JKX.Model.Table.Order;
import com.JKX.Model.Table.Production;
import com.JKX.Mysql.MysqlConnect;
import com.sun.org.apache.xpath.internal.operations.Or;
import javafx.scene.control.Alert;
import sun.awt.windows.WPrinterJob;

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

    public void searchIdOnName(String name,String CustomID) throws SQLException
    {
        String type;
        for(int i=0;i<customTypes.length;i++)
        {
            if(customTypes[i].getType_id().equals(CustomID))
            {
                type=customTypes[i].getType_name();
            }
        }
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
            Custom[] customs = this.searchCusOnId(temp[i][4]);
            orders[i - 1] = new Order(temp[i][0], temp[i][2], temp[i][3], temp[i][1], customs[0], productions);
        }
        System.out.println(orders.length);
        orders[0].getProductions()[0].getProduction_id();
        return orders;
    }

    public Production[] searchCpOnOrder(String order_id) throws SQLException
    {
        String sql = "SELECT orders.order_custom, orders.order_num " +
                    "FROM orders " +
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
            customs[i - 1] = new Custom(ans[i][0], customTypes[Integer.valueOf(ans[i][2]) - 1], ans[i][1], Integer.valueOf(ans[i][3]), ans[i][4]);
        }
        return customs;
    }

    public Custom[] searchCusOnId(String id) throws SQLException
    {
        //通过客户编号查询客户的详细信息
        String sql = "SELECT customer.customer_id, customer.customer_name, customer.customer_tp, customer.customer_wg, customer.customer_tele " +
                     "FROM customer " +
                     "WHERE customer.customer_id = '" + id + "'";
        String[][] ans = staff.Search(sql);
        Custom[] customs = new Custom[ans.length - 1];
        for(int i = 1; i < ans.length; i++)
        {
            customs[i - 1] = new Custom(ans[i][0], customTypes[Integer.valueOf(ans[i][2]) - 1], ans[i][1], Integer.valueOf(ans[i][3]), ans[i][4]);
        }
        return customs;
    }

    public int insertOrderItem(String id, String pro, String num, String custom, String type) throws SQLException
    {
        String sql = "INSERT INTO orders(orders.order_id, orders.order_product, orders.order_num, orders.order_zt, orders.order_custom, orders.order_type) " +
                     "VALUES('" + id + "', '" + pro + "', " + num + ", '待付款', '" + custom + "', '" + type + "');";
        int res = this.staff.Does(sql);
        return res;
    }

    public int deleteOrderItem(String id, String itemId) throws SQLException
    {
        String a[] = {"stirng", "string"};
        String b[] = {id, itemId};
        String sql = "Call Delete_OrderItem(?, ?)";
        int res = this.staff.ExcuteDoes(sql, a, b);
        return res;
    }

    public int insertCustomer(String id, String type, String name, String phone) throws SQLException
    {
        String sql = "insert into customer values('"+ id +"',"+ this.GetTypeidByTypename(type) + ",'"+name+"',0,'"+ phone +"')";
        int res = this.staff.Does(sql);
        return res;
    }

    public boolean CanReturn(String id) throws SQLException//根据订单编号判断订单状态是否为已完成
    {
        String sql="select order_zt from orders where order_id = '"+id+"'";
        String [][]ans=this.staff.Search(sql);
        if(!(ans.length>1&&ans[1][0].equals("已完成")))
            return false;
        sql="select return_id from refund where order_id = '"+id+"'";
        ans=this.staff.Search(sql);
        if(ans.length>1)
            return false;//不能重复退款
        return true;
    }

    public String CanCancel(String id) throws SQLException
    {
        String sql="select order_type,order_zt from orders where order_id = '"+id+"'";
        String [][]ans=this.staff.Search(sql);
        if(ans.length<=1||ans[1][1].equals("已完成"))
            return "error";
        return ans[1][0];
    }

    public int cancelGoods(String id,String money,String zt) throws SQLException
    {
        if(zt.equals("未付款"))
        {
            String sql="update orders set order_zt = '已取消' where order_id = '"+id+"'";
            this.staff.Does(sql);
            return 0;
        }
        else if(zt.equals("预付款"))
        {
            String sql="update orders set order_zt = '已取消' where order_id = '"+id+"'";//修改订单表
            this.staff.Does(sql);
            sql="delete from pick where order_id = '"+id+"'";
            this.staff.Does(sql);//修改提货单表
            sql="delete from unpaid where order_id = '"+id+"'";//修改待付款表
            this.staff.Does(sql);
            return 0;
        }
        else if(zt.equals("全款"))
        {
            String sql="update orders set order_zt = '已取消' where order_id = '"+id+"'";//修改订单表
            this.staff.Does(sql);
            sql="delete pick where order_id = '"+id+"'";
            this.staff.Does(sql);//修改提货单表
            return Integer.valueOf(money);
        }
        return 0;
    }

    public int returnGoods(String id,String money,String reason) throws SQLException//执行退货的数据库操作
    {
        String sql="select max(return_id) from refund";
        String[][] num=staff.Search(sql);
        int n=Integer.valueOf(num[1][0])+1;
        String s=String.valueOf(n);
        sql="insert into refund(return_id,order_money,order_id,return_reason,return_state) values('"+s+"',"+money+",'"+id+"','"+reason+"','待退款')";
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

    public String CreateOrderID() throws SQLException
    {
        String sql="select max(order_id) from orders";
        String[][] num=staff.Search(sql);
        int n=Integer.valueOf(num[1][0])+1;
        return String.valueOf(String.format("%05d",n));
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

    public int InsertOrUpdate(String order_id,String Product_name) throws SQLException
    {
        String sql="select * from orders where order_id = '"+order_id+"' and order_product = '"+Product_name+"'";
        String [][]ans=staff.Search(sql);
        if(ans.length==1)
            return 0;//返回0是insert插入操作
        else
            return 1;//返回1是update更新操作
    }

    public void InsertOrders(String order_id,String order_product,String order_num,String order_custom) throws SQLException
    {
        String sql="insert into orders(order_id,order_product,order_num,order_zt,order_custom,order_type) values('"+order_id+"','"+order_product+"',"+order_num+",'待付款','"+order_custom+"','未付款')";
        staff.Does(sql);
    }

    public void UpdateOrders(String order_id,String order_product,String order_num) throws SQLException
    {
        String sql="update orders set order_num = order_num + "+order_num+" where order_id = '"+order_id+"' and order_product = '"+order_product+"'";
        staff.Does(sql);
    }

    public int CheckOrders(String product_id,String product_name) throws SQLException
    {
        String sql="select SUM(product_rm) from product_ck where product_id = '"+ product_id +"'";//仓库查总量
        String [][]ans = staff.Search(sql);
        int sum1=Integer.valueOf(ans[1][0]);
        sql="select order_id from pick";//待提货库查待提货量
        ans=staff.Search(sql);
        if(ans.length>1)
        {
            int num=0;
            for(int i=1;i<ans.length;i++)
            {
                sql="select order_num from orders where order_id = '"+ ans[i][0] +"' and order_product = '"+ product_name +"'";
                String [][]s=staff.Search(sql);
                if(s.length>1)
                {
                    num=num+Integer.valueOf(s[1][0]);
                }
            }
            return sum1-num;
        }
        else
        {
            return sum1;
        }
    }

    public String[][] GouWuChe(String order_id,String customer_id) throws SQLException
    {
        String sql="select customer_tp from customer where customer_id = '"+ customer_id +"'";
        String [][]ans=staff.Search(sql);
        String type=ans[1][0];
        String [][]r=new String[3000][5];
        int t=0;
        sql="select order_product,order_num from orders where order_id = '"+ order_id +"'";
        ans=staff.Search(sql);
        if(ans.length>1)
        {
            for(int i=1;i<ans.length;i++)
            {
                String name=ans[i][0];
                sql="select product_id,product_p"+ type +" from product where product_name = '"+ name +"'";
                String [][]temp=staff.Search(sql);
                String []q=new String[5];//编号，单价，名称，总价，数量
                q[0]=temp[1][0];
                q[1]=temp[1][1];
                q[2]=name;
                q[4]=ans[i][1];
                q[3]=String.valueOf(Integer.valueOf(q[4])*Integer.valueOf(q[1]));
                r[t]=q;
                t++;
            }
            String [][]p=new String[t][5];
            for(int i=0;i<t;i++)
            {
                p[i]=r[i];
            }
            return p;
        }
        return null;
    }

    public void DeleteProduct(String order_id,String product_name) throws SQLException
    {
        String sql="delete from orders where order_id = '"+ order_id +"' and order_product = '"+ product_name +"'";
        staff.Does(sql);
    }

    public void DeleteAllProduct(String order_id) throws SQLException
    {
        String sql="delete from orders where order_id = '"+ order_id +"'";
        staff.Does(sql);
    }
}
