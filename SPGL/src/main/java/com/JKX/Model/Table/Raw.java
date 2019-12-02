package com.JKX.Model.Table;

public class Raw {
    private String raw_id;
    private String raw_name;
    private int raw_bzq;
    public float raw_price;

    public Raw(String id, String name, int bzq, float price)
    {
        this.raw_id = id;
        this.raw_name = name;
        this.raw_bzq = bzq;
        this.raw_price = price;
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
}
