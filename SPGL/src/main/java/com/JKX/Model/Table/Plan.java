package com.JKX.Model.Table;

import java.security.PublicKey;
import java.util.Date;

public class Plan {
    private String plan_id;
    private String plan_zt;
    private Production production;
    private String plan_sdate;
    private String plan_edate;
    private String plan_ddl;
    private String fzr;
    private String zrr;

    public Plan()
    {

    }

    public Plan(String plan_id, String plan_zt, Production production , String plan_sdate, String plan_edate, String fzr, String zrr, String ddl)
    {
        this.plan_id = plan_id;
        this.plan_zt = plan_zt;
        this.production = production;
        this.plan_sdate = plan_sdate;
        this.plan_edate = plan_edate;
        this.fzr = fzr;
        this.zrr = zrr;
        this.plan_ddl = ddl;
    }

    public String getPlan_ddl() {
        return plan_ddl;
    }

    public String getZrr() {
        return zrr;
    }

    public Production getProduction() {
        return production;
    }

    public String getPlan_edate() {
        return plan_edate;
    }

    public String getPlan_sdate() {
        return plan_sdate;
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

    public void setPlan_sdate(String plan_sdate) {
        this.plan_sdate = plan_sdate;
    }

    public void setPlan_edate(String plan_edate) {
        this.plan_edate = plan_edate;
    }

    public void setPlan_ddl(String plan_ddl) {
        this.plan_ddl = plan_ddl;
    }

    public void setZrr(String zrr) {
        this.zrr = zrr;
    }
}
