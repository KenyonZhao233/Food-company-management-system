package com.JKX.Model.Table;

public class Raw {
    private String raw_id;
    private String raw_name;
    private int raw_bzq;
    public float raw_price;
    public float raw_kc;
    public float raw_num;

    public Raw(String id, String name, int bzq, float price)
    {
        this.raw_id = id;
        this.raw_name = name;
        this.raw_bzq = bzq;
        this.raw_price = price;
        this.raw_kc = 0;
        this.raw_num = 0;
    }

    public Raw(String id, String name, int bzq, float price, float raw_kc)
    {
        this.raw_id = id;
        this.raw_name = name;
        this.raw_bzq = bzq;
        this.raw_price = price;
        this.raw_kc = 0;
        this.raw_num = 0;
        this.raw_kc = raw_kc;
        this.raw_num = 0;
    }

    public Raw(String id, String name, int bzq, float price, float raw_kc, float raw_num)
    {
        this.raw_id = id;
        this.raw_name = name;
        this.raw_bzq = bzq;
        this.raw_price = price;
        this.raw_kc = 0;
        this.raw_num = 0;
        this.raw_kc = raw_kc;
        this.raw_num = raw_num;
    }

    public void setRaw_bzq(int raw_bzq) {
        this.raw_bzq = raw_bzq;
    }

    public void setRaw_kc(float raw_kc) {
        this.raw_kc = raw_kc;
    }

    public void setRaw_num(float raw_num) {
        this.raw_num = raw_num;
    }

    public void setRaw_price(float raw_price) {
        this.raw_price = raw_price;
    }

    public void setRaw_id(String raw_id) {
        this.raw_id = raw_id;
    }

    public void setRaw_name(String raw_name) {
        this.raw_name = raw_name;
    }

    public String getRaw_id()
    {
        return this.raw_id;
    }

    public String getRaw_name()
    {
        return this.raw_name;
    }

    public int getRaw_bzq()
    {
        return this.raw_bzq;
    }

    public float getRaw_price()
    {
        return this.raw_price;
    }

    public float getRaw_kc() {
        return raw_kc;
    }

    public float getRaw_num() {
        return raw_num;
    }
}
