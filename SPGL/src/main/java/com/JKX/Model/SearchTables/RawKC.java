package com.JKX.Model.SearchTables;

public class RawKC {
    private String raw_name;
    private String raw_id;
    private String raw_rm;
    private String raw_in;

    public RawKC()
    {

    }

    public RawKC(String raw_name,String raw_id,String raw_rm,String raw_in)
    {
        this.raw_name=raw_name;
        this.raw_id=raw_id;
        this.raw_rm=raw_rm;
        this.raw_in=raw_in;
    }

    public String getRaw_name() {
        return raw_name;
    }

    public String getRaw_id() {
        return raw_id;
    }

    public String getRaw_in() {
        return raw_in;
    }

    public String getRaw_rm() {
        return raw_rm;
    }

    public void setRaw_id(String raw_id) {
        this.raw_id = raw_id;
    }

    public void setRaw_name(String raw_name) {
        this.raw_name = raw_name;
    }

    public void setRaw_in(String raw_in) {
        this.raw_in = raw_in;
    }

    public void setRaw_rm(String raw_rm) {
        this.raw_rm = raw_rm;
    }
}
