package com.JKX.Model.Table;

import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.Date;

public class Order {
    private String order_id;
    private String order_date;
    private String order_type;
    private String order_zt;
    private Custom custom;
    private Production[] productions;

    public Order()
    {

    }

    public Order(String order_id, String order_date, String order_type, String order_zt, Custom custom, Production[] productions)
    {
        this.order_id = order_id;
        this.order_type = order_type;
        this.order_date = order_date;
        this.order_zt = order_zt;
        this.productions = productions;
        this.custom = custom;
    }

    public String getOrder_date() {
        return order_date;
    }

    public Production[] getProductions() {
        return productions;
    }

    public Custom getCustom() {
        return custom;
    }

    public String getOrder_id() {
        return order_id;
    }

    public String getOrder_type() {
        return order_type;
    }

    public String getOrder_zt() {
        return order_zt;
    }

    public void setCustom(Custom custom) {
        this.custom = custom;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public void setOrder_zt(String order_zt) {
        this.order_zt = order_zt;
    }

    public void setProductions(Production[] productions) {
        this.productions = productions;
    }
}
