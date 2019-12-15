package com.JKX.Model.Table;

import org.omg.CORBA.PRIVATE_MEMBER;

public class Projectrec {

    private String planId;

    private String date;

    private int num;

    private String fzr;

    private String zt;

    public Projectrec()
    {

    }

    public Projectrec(String planId, String zt, int num, String fzr, String date)
    {
        this.date = date;
        this.fzr = fzr;
        this.num = num;
        this.zt = zt;
        this.planId = planId;
    }

    public String getFzr() {
        return fzr;
    }

    public int getNum() {
        return num;
    }

    public String getDate() {
        return date;
    }

    public String getPlanId() {
        return planId;
    }

    public String getZt() {
        return zt;
    }

    public void setFzr(String fzr) {
        this.fzr = fzr;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }
}
