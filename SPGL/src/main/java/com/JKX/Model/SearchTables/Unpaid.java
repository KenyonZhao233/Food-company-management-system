package com.JKX.Model.SearchTables;

public class Unpaid {
    private String order_id;
    private String order_date;
    private String order_mn;
    private String order_cusrom;
    private String customer_name;
    private String customer_tele;

    public Unpaid()
    {

    }

    public Unpaid(String order_id,String order_date,String order_mn,String order_cusrom,String customer_name,String customer_tele)
    {
        this.order_id=order_id;
        this.order_date=order_date;
        this.order_mn=order_mn;
        this.order_cusrom=order_cusrom;
        this.customer_name=customer_name;
        this.customer_tele=customer_tele;
    }

    public String getOrder_date() {
        return order_date;
    }

    public String getOrder_id() {
        return order_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public String getCustomer_tele() {
        return customer_tele;
    }

    public String getOrder_cusrom() {
        return order_cusrom;
    }

    public String getOrder_mn() {
        return order_mn;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public void setCustomer_tele(String customer_tele) {
        this.customer_tele = customer_tele;
    }

    public void setOrder_cusrom(String order_cusrom) {
        this.order_cusrom = order_cusrom;
    }

    public void setOrder_mn(String order_mn) {
        this.order_mn = order_mn;
    }
}
