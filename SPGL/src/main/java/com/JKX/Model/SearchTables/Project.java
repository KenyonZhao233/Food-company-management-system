package com.JKX.Model.SearchTables;

public class Project {
    private String produce_id;
    private String produce_type;
    private String produce_wp;
    private String produce_num;
    private String produce_sdate;
    private String produce_edate;
    private String produce_zzr;
    private String produce_fzr;
    private String produce_ddl;

    public Project()
    {

    }

    public Project(String produce_id,String produce_type,String produce_wp,String produce_num,String produce_sdate,String produce_edate,String produce_zzr,String produce_fzr,String produce_ddl)
    {
        this.produce_id=produce_id;
        this.produce_type=produce_type;
        this.produce_wp=produce_wp;
        this.produce_num=produce_num;
        this.produce_sdate=produce_sdate;
        this.produce_edate=produce_edate;
        this.produce_zzr=produce_zzr;
        this.produce_fzr=produce_fzr;
        this.produce_ddl=produce_ddl;
    }

    public String getProduce_ddl() {
        return produce_ddl;
    }

    public String getProduce_edate() {
        return produce_edate;
    }

    public String getProduce_fzr() {
        return produce_fzr;
    }

    public String getProduce_id() {
        return produce_id;
    }

    public String getProduce_num() {
        return produce_num;
    }

    public String getProduce_sdate() {
        return produce_sdate;
    }

    public String getProduce_type() {
        return produce_type;
    }

    public String getProduce_wp() {
        return produce_wp;
    }

    public String getProduce_zzr() {
        return produce_zzr;
    }

    public void setProduce_ddl(String produce_ddl) {
        this.produce_ddl = produce_ddl;
    }

    public void setProduce_edate(String produce_edate) {
        this.produce_edate = produce_edate;
    }

    public void setProduce_fzr(String produce_fzr) {
        this.produce_fzr = produce_fzr;
    }

    public void setProduce_id(String produce_id) {
        this.produce_id = produce_id;
    }

    public void setProduce_num(String produce_num) {
        this.produce_num = produce_num;
    }

    public void setProduce_sdate(String produce_sdate) {
        this.produce_sdate = produce_sdate;
    }

    public void setProduce_type(String produce_type) {
        this.produce_type = produce_type;
    }

    public void setProduce_wp(String produce_wp) {
        this.produce_wp = produce_wp;
    }

    public void setProduce_zzr(String produce_zzr) {
        this.produce_zzr = produce_zzr;
    }
}
