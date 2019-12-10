package com.JKX.Model.SearchTables;

public class Pick {
    private String order_id;
    private String order_type;
    private String custom_id;
    private String custom_name;
    private String custom_tele;
    private String order_date;

    public Pick()
    {

    }

    public Pick(String order_id,String order_type,String custom_id,String custom_name,String custom_tele,String order_date)
    {
        this.order_id=order_id;
        this.order_type=order_type;
        this.custom_id=custom_id;
        this.custom_name=custom_name;
        this.custom_tele=custom_tele;
        this.order_date=order_date;
    }

    public void setCustom_name(String custom_name) {
        this.custom_name = custom_name;
    }

    public void setCustom_id(String custom_id) {
        this.custom_id = custom_id;
    }

    public void setCustom_tele(String custom_tele) {
        this.custom_tele = custom_tele;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public String getCustom_name() {
        return custom_name;
    }

    public String getCustom_id() {
        return custom_id;
    }

    public String getCustom_tele() {
        return custom_tele;
    }

    public String getOrder_id() {
        return order_id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public String getOrder_type() {
        return order_type;
    }
}
