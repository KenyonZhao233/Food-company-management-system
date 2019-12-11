package com.JKX.Model;

import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class ManageSection {
    private Staff staff;

    public ManageSection(Staff staff)
    {
        this.staff = staff;
    }

    public Staff getStaff() {
        return staff;
    }

    public int Update_User(String preUid, String preBm, String preZw, String bmText, String zwText, String nameText, String sexText, String sfzText) throws SQLException
    {
        String[] a = {"string", "string", "string", "string", "string", "string", "string", "string"};
        String[] b = {preUid, preBm, preZw,bmText, zwText, nameText, sexText, sfzText};
        int nums = this.staff.ExcuteDoes("Call Staff_Update(?, ?, ?, ?, ?, ?, ?, ?)", a, b);
        return nums;
    }

    public int Delete_User(String uidText, String bmText, String zwText) throws SQLException
    {
        String[] a = {"string", "string", "string"};
        String[] b = {uidText, bmText, zwText};
        int nums = this.staff.ExcuteDoes("Call Delete_Staff(?, ?, ?)", a, b);
        return nums;
    }

    public String[] Add_User(String uidAddText, String sectionCombox, String bmm, String nameAddText, String sexAddText, String sfzAddText, String psw) throws SQLException
    {
        String[] a = {"string", "string", "string", "string", "string", "string", "string"};
        String[] b = {uidAddText, sectionCombox, bmm, nameAddText, sexAddText, sfzAddText, psw};
        String[] c = {"int"};
        String[] ans = this.staff.ExcuteDoesReturn("CALL Staff_Add(?, ?, ?, ?, ?, ?, ?, ?)",a, b, c);
        return ans;
    }

    public String[][] Search_Gly(String info) throws SQLException
    {
        String[][] ans;
        String[] b = {""};
        String[] a = {"string"};
        if(info.equals("")) {
            b[0] = "NULL";
        }
        else{
            b[0] = info;
        }
        ans = staff.ExcuteSearch("Call GLY_Search(?)", a, b);
        return ans;
    }

    public String[][] Search_Other(String info, String inform) throws SQLException
    {
        String[][] ans;
        if(info.equals("")) {
            ans = this.staff.Search("select staff.staff_id, staff_bm, staff_zw, staff_name, staff_sfz, staff_sex, staff_date" +
                    " from staff, staff_job" +
                    " where staff.staff_id = staff_job.staff_id and staff_bm in (" + inform + ") and staff_zw in ('业务人员')" +
                    " group by staff.staff_id, staff_bm, staff_zw, staff_name, staff_sfz, staff_sex, staff_date");
        }
        else{
            ans = this.staff.Search("select staff.staff_id, staff_bm, staff_zw, staff_name, staff_sfz, staff_sex, staff_date" +
                    " from staff, staff_job" +
                    " where staff.staff_id = '" + info + "' and staff.staff_id = staff_job.staff_id and staff_bm in (" + inform + ") and staff_zw in ('业务人员')" +
                    " group by staff.staff_id, staff_bm, staff_zw, staff_name, staff_sfz, staff_sex, staff_date");
        }
        return ans;
    }

    public String[][] Simple_Search(String search) throws SQLException
    {
        String[][] ans = this.staff.Search("select * from staff where staff_id = '" + search + "';");
        return ans;
    }

    public int[][] SearchQx(String staffs, int[][] zw) throws SQLException
    {
        int[][] qx = new int[6][8];
        if(zw[1][0] == 1)
        {
            String[][] ans = this.staff.Search("select * from right_finance where staff_id = '" + staffs + "'");
            if(ans.length > 1)
            {
                qx[1][0] = 1;
                for(int i = 1; i <= 4; i++)
                {
                    qx[1][i] = Integer.parseInt(ans[1][i]);
                }
            }
        }
        if(zw[2][0] == 1)
        {
            String[][] ans = this.staff.Search("select * from right_sale where staff_id = '" + staffs + "'");
            if(ans.length > 1)
            {
                qx[2][0] = 1;
                for (int i = 1; i <= 4; i++) {
                    qx[2][i] = Integer.parseInt(ans[1][i]);
                }
            }
        }
        if(zw[3][0] == 1)
        {
            String[][] ans = this.staff.Search("select * from right_end where staff_id = '" + staffs + "'");
            if(ans.length > 1)
            {
                qx[3][0] = 1;
                for(int i = 1; i <= 4; i++)
                {
                    qx[3][i] = Integer.parseInt(ans[1][i]);
                }
            }
        }
        if(zw[4][0] == 1)
        {
            String[][] ans = this.staff.Search("select * from right_raw where staff_id = '" + staffs + "'");
            if(ans.length > 1)
            {
                qx[4][0] = 1;
                for(int i = 1; i <= 3; i++)
                {
                    qx[4][i] = Integer.parseInt(ans[1][i]);
                }
            }
        }
        if(zw[6][0] == 1)
        {
            String[][] ans = this.staff.Search("select * from right_plan where staff_id = '" + staffs + "'");
            if(ans.length > 1)
            {
                qx[5][0] = 1;
                for(int i = 1; i <= 3; i++)
                {
                    qx[5][i] = Integer.parseInt(ans[1][i]);
                }
            }
        }
        return qx;
    }

    public void ChangeQx(String staffs, int[][] qx, int[][] zw) throws SQLException
    {
        if(zw[1][0] == 1 && this.staff.zw[1][2] == 1)
        {
            this.staff.Does("UPDATE right_finance SET right_receive = " + qx[1][1] + ", right_refund = " + qx[1][2] + ", right_in =  " + qx[1][3] + ", right_out = " + qx[1][4] + " WHERE staff_id = '" + staffs + "'");
        }
        if(zw[2][0] == 1 && this.staff.zw[2][2] == 1)
        {
            this.staff.Does("UPDATE right_sale SET right_register = " + qx[2][1] + ", right_create = " + qx[2][2] + ", right_cancel =  " + qx[2][3] + ", right_return = " + qx[2][4] + " WHERE staff_id = '" + staffs + "'");
        }
        if(zw[3][0] == 1 && this.staff.zw[3][2] == 1)
        {
            this.staff.Does("UPDATE right_end SET right_call = " + qx[3][1] + ", right_out = " + qx[3][2] + ", right_in =  " + qx[3][3] + ", right_des = " + qx[3][4] + " WHERE staff_id = '" + staffs + "'");
        }
        if(zw[4][0] == 1 && this.staff.zw[4][2] == 1)
        {
            this.staff.Does("UPDATE right_raw SET right_in = " + qx[4][1] + ", right_des = " + qx[4][2] + ", right_out =  " + qx[4][3] + " WHERE staff_id = '" + staffs + "'");
        }
        if(zw[6][0] == 1 && this.staff.zw[6][2] == 1)
        {
            this.staff.Does("UPDATE right_plan SET right_plans = " + qx[5][1] + ", right_pros = " + qx[5][2] + ", right_raws =  " + qx[5][3] + " WHERE staff_id = '" + staffs + "'");
        }
    }

    public int[][] searchZw(String staffs) throws SQLException
    {
        String userInform[][] = this.staff.Search("select * from staff , staff_job where staff.staff_id = '" + staffs + "' and staff.staff_id = staff_job.staff_id");
        int[][] zw = new int[7][3];
        for(int i  = 1; i < userInform.length; i++)
        {
            int tag = 0;
            if(userInform[i][8].equals("业务人员"))
                tag = 0;
            else if(userInform[i][8].equals("管理者"))
                tag = 1;
            else if(userInform[i][8].equals("管理员"))
                tag = 2;
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
        return zw;
    }
}
