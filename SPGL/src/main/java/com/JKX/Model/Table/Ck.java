package com.JKX.Model.Table;

public class Ck {
    private String ck_id;
    private String ck_pos;

    public Ck(String ck_id, String ck_pos)
    {
        this.ck_id = ck_id;
        this.ck_pos = ck_pos;
    }


    public void setCk_id(String ck_id) {
        this.ck_id = ck_id;
    }

    public void setCk_pos(String ck_pos) {
        this.ck_pos = ck_pos;
    }

    public String getCk_id() { return this.ck_id; }

    public String getCk_pos()
    {
        return this.ck_pos;
    }

}
