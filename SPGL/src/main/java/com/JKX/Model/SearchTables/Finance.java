package com.JKX.Model.SearchTables;

public class Finance {
    private String finance_id;
    private String finance_user;
    private String finance_mn;
    private String finance_time;
    private String finance_inf;

    public Finance()
    {

    }

    public  Finance(String finance_id,String finance_user,String finance_mn,String finance_time,String finance_inf)
    {
        this.finance_id=finance_id;
        this.finance_user=finance_user;
        this.finance_mn=finance_mn;
        this.finance_time=finance_time;
        this.finance_inf=finance_inf;
    }

    public String getFinance_id() {
        return finance_id;
    }

    public String getFinance_inf() {
        return finance_inf;
    }

    public String getFinance_mn() {
        return finance_mn;
    }

    public String getFinance_time() {
        return finance_time;
    }

    public String getFinance_user() {
        return finance_user;
    }

    public void setFinance_id(String finance_id) {
        this.finance_id = finance_id;
    }

    public void setFinance_inf(String finance_inf) {
        this.finance_inf = finance_inf;
    }

    public void setFinance_mn(String finance_mn) {
        this.finance_mn = finance_mn;
    }

    public void setFinance_time(String finance_time) {
        this.finance_time = finance_time;
    }

    public void setFinance_user(String finance_user) {
        this.finance_user = finance_user;
    }
}
