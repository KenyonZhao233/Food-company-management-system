package com.JKX.Model.SearchTables;

public class Raw {
    private String raw_id;
    private String raw_name;
    private String bzq;
    private String pri;

    public Raw()
    {

    }

    public Raw(String raw_id,String raw_name,String bzq,String pri)
    {
        this.raw_id=raw_id;
        this.raw_name=raw_name;
        this.bzq=bzq;
        this.pri=pri;
    }

    public String getBzq() {
        return bzq;
    }

    public String getPri() {
        return pri;
    }

    public String getRaw_id() {
        return raw_id;
    }

    public String getRaw_name() {
        return raw_name;
    }

    public void setRaw_name(String raw_name) {
        this.raw_name = raw_name;
    }

    public void setRaw_id(String raw_id) {
        this.raw_id = raw_id;
    }

    public void setBzq(String bzq) {
        this.bzq = bzq;
    }

    public void setPri(String pri) {
        this.pri = pri;
    }
}
