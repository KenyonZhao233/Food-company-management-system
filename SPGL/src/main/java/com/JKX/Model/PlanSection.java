package com.JKX.Model;

import com.JKX.Controller.ItemController.StaffInformController;
import com.JKX.Model.Table.Plan;
import com.JKX.Model.Table.Production;
import com.JKX.Model.Table.Raw;
import javafx.scene.control.Alert;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

public class PlanSection {
    private Staff staff;
    //可以向其中添加你所需要的相应的属性，在初始化中连接数据库，查询出你所需要的属性并将其初始化;

    public PlanSection(Staff s)
    {
        this.staff = s;
    }

    public Staff getStaff() {
        return staff;
    }

    public Production[] searchCpOnID(String id) throws SQLException
    {
        String[] a = {"string"};
        String[] b = {id};
        String[][] ans;
        ans = staff.ExcuteSearch("Call Search_CpId(?)", a, b);
        Production[] productions = new Production[ans.length - 1];
        for(int i = 1; i < ans.length; i++)
        {
            String[][] ansRaw;
            ansRaw = staff.Search("SELECT raw.*, product_raw.raw_num " +
                                             "FROM product_raw, raw " +
                                             "where product_id = '" + ans[i][0] + "' AND raw.raw_id = product_raw.raw_id " +
                                             "ORDER BY raw.raw_name;");
            Raw[] raws = new Raw[ansRaw.length - 1];
            for(int j = 1; j < ansRaw.length; j++) {
                raws[j - 1] = new Raw(ansRaw[j][0], ansRaw[j][1], Integer.parseInt(ansRaw[j][2]), Float.parseFloat(ansRaw[j][3]), 0, Float.parseFloat(ansRaw[j][4]));
            }
            int rm = this.searchCpRm(ans[i][0]);
            productions[i - 1] = new Production(ans[i][0], ans[i][1], Float.parseFloat(ans[i][2]),Float.parseFloat(ans[i][3]), Float.parseFloat(ans[i][4]),Integer.parseInt(ans[i][5]), rm, raws, 0);
        }
        return productions;
    }

    public Production[] searchCpOnName(String name) throws SQLException
    {
        String[] a = {"string"};
        String[] b = {name};
        String[][] ans;
        ans = staff.ExcuteSearch("Call Search_CpName(?)", a, b);
        Production[] productions = new Production[ans.length - 1];
        for(int i = 1; i < ans.length; i++)
        {
            String[][] ansRaw;
            ansRaw = staff.Search("SELECT raw.*, product_raw.raw_num " +
                    "FROM product_raw, raw " +
                    "where product_id = '" + ans[i][0] + "' AND raw.raw_id = product_raw.raw_id " +
                    "ORDER BY raw.raw_name;");
            Raw[] raws = new Raw[ansRaw.length - 1];
            for(int j = 1; j < ansRaw.length; j++)
                raws[j - 1] = new Raw(ansRaw[j][0], ansRaw[j][1], Integer.parseInt(ansRaw[j][2]), Float.parseFloat(ansRaw[j][3]), 0,Float.parseFloat(ansRaw[j][4]));
            int rm = this.searchCpRm(ans[i][0]);
            productions[i - 1] = new Production(ans[i][0], ans[i][1], Float.parseFloat(ans[i][2]),Float.parseFloat(ans[i][3]), Float.parseFloat(ans[i][4]),Integer.parseInt(ans[i][5]), rm ,raws, 0);
        }
        return productions;
    }

    public int searchCpRm(String cpId) throws SQLException {
        String[] a = {"string"};
        String[] b = {cpId};
        String[] c = {"int"};
        String sql = "Call Search_CpRm(?, ?)";
        String[] ans = this.staff.ExcuteDoesReturn(sql, a, b, c);
        return Integer.parseInt(ans[0]);
    }

    public Raw[] searchRawOnName(String name) throws SQLException
    {
        String[] a = {"string"};
        String[] b = {name};
        String[][] ans;
        ans = staff.ExcuteSearch("Call Search_RawName(?)", a, b);
        Raw[] raws = new Raw[ans.length - 1];

        for(int i = 1; i < ans.length; i++)
        {
            float plans = this.searchPlanRawNum(ans[i][0]);
            raws[i - 1] = new Raw(ans[i][0], ans[i][1], Integer.parseInt(ans[i][3]), Float.parseFloat(ans[i][2]), this.searchRawRm(ans[i][0]) - plans);
        }
        return raws;
    }

    public float searchPlanRawNum(String rawId) throws SQLException
    {
        float res;
        String[] a = {"string"};
        String[] b = {rawId};
        String[] c = {"float"};
        String sql = "CALL Search_PlanRaw(?,?)";
        String[] ans = this.staff.ExcuteDoesReturn(sql, a, b, c);
        res = Float.parseFloat(ans[0]);
        return res;
    }

    public String getNewCpId(String head) throws SQLException
    {
        String sql = "SELECT SUBSTR(product.product_id FROM 3) " +
                    "FROM product " +
                    "WHERE SUBSTR(product.product_id FROM 1 FOR 3) = '" + head + "' " +
                    "ORDER BY SUBSTR(product.product_id FROM 3) DESC LIMIT 1;";
        String[][] ans = this.staff.Search(sql);
        if(ans.length == 1)
            return "00000";
        return  String.format("%05d", Integer.parseInt(ans[1][0]) + 1);
    }

    public String getNewRawId(String head) throws SQLException
    {
        String sql = "SELECT SUBSTR(raw.raw_id FROM 3) " +
                "FROM raw " +
                "WHERE SUBSTR(raw.raw_id FROM 1 FOR 3) = '" + head + "' " +
                "ORDER BY SUBSTR(raw.raw_id FROM 3) DESC LIMIT 1;";
        String[][] ans = this.staff.Search(sql);
        if(ans.length == 1)
            return "000000";
        return  String.format("%06d", Integer.parseInt(ans[1][0]) + 1);
    }

    public Raw[] searchRawOnId(String id) throws SQLException
    {
        String[] a = {"string"};
        String[] b = {id};
        String[][] ans;
        ans = staff.ExcuteSearch("Call Search_RawId(?)", a, b);
        Raw[] raws = new Raw[ans.length - 1];
        for(int i = 1; i < ans.length; i++)
        {
            float plans = this.searchPlanRawNum(ans[i][0]);

            raws[i - 1] = new Raw(ans[i][0], ans[i][1], Integer.parseInt(ans[i][3]), Float.parseFloat(ans[i][2]), this.searchRawRm(ans[i][0]) - plans);
        }
        return raws;
    }

    public float searchRawRm(String rawId) throws SQLException {
        String[] a = {"string"};
        String[] b = {rawId};
        String[] c = {"float"};
        String sql = "Call Search_RawRm(?, ?)";
        String[] ans = this.staff.ExcuteDoesReturn(sql, a, b, c);
        return Float.parseFloat(ans[0]);
    }

    public int makePlan(Plan plan) throws SQLException
    {
        //Plan plan = new Plan(planId, planType, production, s_date, e_date, fzr);
        String sql = "INSERT INTO project(project.produce_id, project.produce_type, project.produce_wp, project.produce_num, project.produce_zrr, project.produce_ddl) " +
                     "VALUES ('" + plan.getPlan_id() + "', '" + plan.getPlan_zt() + "', '" + plan.getProduction().getProduction_id() + "', " + String.valueOf(plan.getProduction().getNums()) + " , '" + plan.getFzr() + "', '" + plan.getPlan_ddl() + "')";
        int res = staff.Does(sql);
        return res;
    }

    public Plan[] searchPlan(String id, String zt) throws SQLException      //存储过程，查询Plan返回计划编号，成品类，计划状态。
    {
        String[] a = {"string", "string"};
        String[] b = {id, zt};
        String[][] ans;
        ans = staff.ExcuteSearch("Call Search_Plan_IdZt(?, ?) ", a, b);
        Plan[] plans = new Plan[ans.length - 1];
        for(int i = 1; i < ans.length; i++)
        {
            Production[] production = this.searchCpOnID(ans[i][2]);
            production[0].setNums(Integer.parseInt(ans[i][3]));
            for(int j = 0; j < production[0].getRaws().length; j++)
            {
                production[0].getRaws()[j].setRaw_num(production[0].getRaws()[j].getRaw_num() * production[0].getNums());
            }
            plans[i - 1] = new Plan(ans[i][0], ans[i][1], production[0], ans[i][4], ans[i][5], ans[i][6], ans[i][7], ans[i][8]);
        }
        return plans;
    }

    public int[] SearchQx() throws SQLException
    {
        String sql = "select * from right_plan where staff_id = '" + this.staff.Uid + "'";
        String[][] ans = this.staff.Search(sql);
        int[] qx = new int[3];
        for(int i = 0; i < 3; i++)
            qx[i] = Integer.parseInt(ans[1][i + 1]);
        return qx;
    }

    public int[] SearchAdair(String id) throws SQLException
    {
        String sql = "SELECT * " +
                     "FROM project_status " +
                     "where project_status.project_id = '" + id + "'";
        String[][] ans = this.staff.Search(sql);
        int[] nums = new int[3];
        for(int i = 0; i < 3; i++) {
            nums[i] = Integer.parseInt(ans[1][i + 1]);
        }
        return nums;
    }

    public Plan[] searchPlan(String id, String sdate, String edate, String zt) throws SQLException      //存储过程，查询Plan返回计划编号，成品类，计划状态。
    {
        String[] a = {"string", "date", "date", "string"};
        String[] b = {id, sdate, edate, zt};
        String[][] ans;
        ans = staff.ExcuteSearch("Call Search_Plan_Date(?, ?, ?, ?) ", a, b);
        Plan[] plans = new Plan[ans.length - 1];
        for(int i = 1; i < ans.length; i++)
        {
            Production[] production = this.searchCpOnID(ans[i][2]);
            production[0].setNums(Integer.parseInt(ans[i][3]));
            plans[i - 1] = new Plan(ans[i][0], ans[i][1], production[0], ans[i][4], ans[i][5], ans[i][6], ans[i][7], ans[i][8]);
        }
        return plans;
    }

    public int changePlanOnnum(String id, String num, String ddl) throws SQLException
    {
        //修改待执行的计划的成品数量
        //直接写个存储过程
        String sql = "UPDATE project " +
                     "SET project.produce_num = " + num + ", " +
                     "project.produce_ddl = '" + ddl + "' " +
                     " WHERE project.produce_id = '" + id + "'";
        int res = staff.Does(sql);
        return res;
    }

    public int deletePlan(String id) throws SQLException    //删除这个编号的
    {
        //让小界面自己解决这个问题，如果是待执行是可以删除的。
        String sql = "DELETE " +
                     "FROM project " +
                     "WHERE project.produce_id = '" + id +  "'";
        int res = staff.Does(sql);
        return res;
    }

    public int confirmPlan(String id) throws SQLException
    {
        //将待审核的计划进行审核，同时修改为已完成， 添加个结束时间
        String[] a = {"string"};
        String[] b = {id};
        String sql = "Call Confirm_Plan(?)";
        int res = staff.ExcuteDoes(sql, a, b);
        return res;
    }

    public int addCp(Production production) throws SQLException
    {
        //构造时先添加成品， 然后再添加原料
        //一个Insert语句
        String a[] = {"string", "string", "float", "float", "float", "int"};
        String b[] = {production.getProduction_id(), production.getProduction_name(), String.valueOf(production.getProduction_p1()), String.valueOf(production.getProduction_p2()), String.valueOf(production.getProduction_p3()), String.valueOf(production.getProduction_bzq())};
        String sql = "Call Insert_Cp(?, ?, ?, ?, ?, ?)";
        int res = staff.ExcuteDoes(sql, a, b);
        return res;
    }

    public int addCpRaws(String id, Raw raw)  throws SQLException  //添加完成品之后，想特定的一个成品添加原料
    {
        String sql = "INSERT INTO product_raw(product_raw.product_id, product_raw.raw_id, product_raw.raw_num) " +
                     "VALUES ('" + id + "', '" + raw.getRaw_id() + "', " + String.valueOf(raw.getRaw_num()) + ");";
        int res = staff.Does(sql);
        return res;
    }

    public int changeCpInform(String id, String name, String p1, String p2, String p3, String bzq) throws SQLException
    {
        String[] a = {"string", "string", "float", "float", "float", "int"};
        String[] b = {id, name, p1, p2, p3, bzq};
        String sql = "Call Change_Cp(?, ?, ?, ?, ?, ?)";
        int res = this.staff.ExcuteDoes(sql, a, b);
        return res;
    }

    public int deleteCpRaws(String id) throws SQLException
    {
        //根据成品编号，删除全部的原料
        String sql = "DELETE FROM product_raw " +
                     "WHERE product_raw.product_id = '" + id + "'";
        int res = staff.Does(sql);
        return res;
    }

    public int deleteCpRaws(String id, Raw raw) throws SQLException
    {
        //根据成品编号，删除相应的原料
        String sql = "DELETE FROM product_raw " +
                     "WHERE product_raw.product_id = '" + id + "' AND product_raw.raw_id = '" + raw.getRaw_id() + "'";
        int res = staff.Does(sql);
        return res;
    }

    public int deleteCp(String id) throws SQLException
    {
        //同样是小界面来解决问题，按编号来删除
        //如果有库存，那么不能删除
        String sql = "DELETE FROM product " +
                     "WHERE product.product_id = '" + id + "'";
        int res = this.staff.Does(sql);
        return res;
    }

    public void changeCp(String id, Raw[] raws) throws SQLException
    {
        //删除该成品的所有原料信息，逐个添加
        this.deleteCpRaws(id);
        for(int i = 0; i < raws.length; i++)
            this.addCpRaws(id, raws[i]);
    }

    public int addRaw(Raw raw) throws SQLException
    {
        String[] a = {"string", "string", "int", "float"};
        String[] b  ={raw.getRaw_id(), raw.getRaw_name(), String.valueOf(raw.getRaw_bzq()), String.valueOf(raw.getRaw_price())};
        String sql = "Call Raw_Add(?, ?, ?, ?)";
        int res = staff.ExcuteDoes(sql, a, b);
        return res;
    }

    public int changeRaw(String id, String name, String bzq, String pri) throws SQLException
    {
        String sql = "UPDATE raw " +
                     "SET raw.raw_name = '" + name + "', " +
                     " raw.raw_bzq = " + bzq + ", " +
                     " raw.raw_pri = " +  pri +
                     " WHERE raw.raw_id = '" + id + "'";
        int res = staff.Does(sql);
        return res;
        //可修改原料的单价， 名称， 保质期
    }

    public int deleteRaw(String id) throws SQLException
    {
        String sql = "DELETE FROM raw " +
                     "WHERE raw.raw_id = '" + id + "'";
        int res = staff.Does(sql);
        return res;
        //同样是小界面来解决问题，按编号来删除
    }

    public HashMap<String, Integer> searchXl(String d1, String d2) throws SQLException
    {
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        String sql = "Call Search_XL(?, ?)";
        String[] a = {"date", "date"};
        String[] b = {d1, d2};
        String[][] ans = this.staff.ExcuteSearch(sql, a, b);
        for (int i = 1; i < Math.min(ans.length, 50); i++)
            hashMap.put(ans[i][0], Integer.valueOf(ans[i][1]));
        return hashMap;
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

    public String GetNumber()
    {
        //生成计划号
        //xx号生成原则：xx + 年（4位）+月（2位）+日（2位）+时（2位）+分（2位）+秒（2位）+3 位随机数
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
            sb.append(buffer.charAt(r.nextInt(range)));
        }
        return sb.toString();
    }
    //接下来开始写成品库所对应的操作
    //查询：写相应的sql语句，用sta中的函数调用select或存储过程，并返回一个二维数组；
    //修改：写相应的sql语句，用sta中的函数调用相应的存储过程，并返回影响的行数；
}
