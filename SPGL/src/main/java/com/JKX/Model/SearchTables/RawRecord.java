package com.JKX.Model.SearchTables;

public class RawRecord {
    private String raw_id;
    private String raw_name;
    private String raw_date;
    private String raw_lx;
    private String raw_num;
    private String raw_fzrid;

    public RawRecord()
    {

    }

    public RawRecord(String raw_id,String raw_name,String raw_date,String raw_lx,String raw_num,String raw_fzrid)
    {
        this.raw_id=raw_id;
        this.raw_name=raw_name;
        this.raw_date=raw_date;
        this.raw_lx=raw_lx;
        this.raw_num=raw_num;
        this.raw_fzrid=raw_fzrid;
    }

    public String getRaw_id() {
        return raw_id;
    }

    public String getRaw_name() {
        return raw_name;
    }

    public String getRaw_date() {
        return raw_date;
    }

    public String getRaw_fzrid() {
        return raw_fzrid;
    }

    public String getRaw_lx() {
        return raw_lx;
    }

    public String getRaw_num() {
        return raw_num;
    }

    public void setRaw_name(String raw_name) {
        this.raw_name = raw_name;
    }

    public void setRaw_id(String raw_id) {
        this.raw_id = raw_id;
    }

    public void setRaw_date(String raw_date) {
        this.raw_date = raw_date;
    }

    public void setRaw_fzrid(String raw_fzrid) {
        this.raw_fzrid = raw_fzrid;
    }

    public void setRaw_lx(String raw_lx) {
        this.raw_lx = raw_lx;
    }

    public void setRaw_num(String raw_num) {
        this.raw_num = raw_num;
    }
}
