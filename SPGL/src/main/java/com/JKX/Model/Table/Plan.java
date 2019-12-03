package com.JKX.Model.Table;

import java.security.PublicKey;
import java.util.Date;

public class Plan {
    private String plan_id;
    private String plan_zt;
    private Production production;
    private int plan_num;
    private Date plan_sdate;
    private Date plan_edate;
    private String fzr;

    public Plan(String plan_id, String plan_zt, Production productionm , int num, Date plan_sdate, Date plan_edate, String fzr)
    {
        this.plan_id = plan_id;
        this.plan_zt = plan_zt;
        this.plan_num = num;
        this.production = production;
        this.plan_sdate = plan_sdate;
        this.plan_edate = plan_edate;
        this.fzr = fzr;
    }

    public Production getProduction() {
        return production;
    }

    public Date getPlan_edate() {
        return plan_edate;
    }

    public Date getPlan_sdate() {
        return plan_sdate;
    }

    public int getPlan_num() {
        return plan_num;
    }

    public String getFzr() {
        return fzr;
    }

    public String getPlan_id() {
        return plan_id;
    }

    public String getPlan_zt() {
        return plan_zt;
    }

    public void setProduction(Production production) {
        this.production = production;
    }
    public void setPlan_id(String plan_id) {
        this.plan_id = plan_id;
    }

    public void setPlan_zt(String plan_zt) {
        this.plan_zt = plan_zt;
    }

    public void setFzr(String fzr) {
        this.fzr = fzr;
    }

    public void setPlan_edate(Date plan_edate) {
        this.plan_edate = plan_edate;
    }

    public void setPlan_num(int plan_num) {
        this.plan_num = plan_num;
    }

    public void setPlan_sdate(Date plan_sdate) {
        this.plan_sdate = plan_sdate;
    }
}
