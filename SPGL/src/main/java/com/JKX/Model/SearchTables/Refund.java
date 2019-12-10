package com.JKX.Model.SearchTables;

public class Refund {
    private String return_id;
    private String order_money;
    private String order_id;
    private String return_date;
    private String return_reason;
    private String return_state;
    private String custom_id;
    private String custom_name;
    private String custom_tele;

    public Refund()
    {

    }

    public Refund(String return_id,String order_money,String order_id,String return_date,String return_reason,String return_state,String custom_id,String custom_name,String custom_tele)
    {
        this.return_id=return_id;
        this.order_money=order_money;
        this.order_id=order_id;
        this.return_date=return_date;
        this.return_reason=return_reason;
        this.return_state=return_state;
        this.custom_id=custom_id;
        this.custom_name=custom_name;
        this.custom_tele=custom_tele;
    }

    public String getOrder_id() {
        return order_id;
    }

    public String getCustom_tele() {
        return custom_tele;
    }

    public String getCustom_id() {
        return custom_id;
    }

    public String getCustom_name() {
        return custom_name;
    }

    public String getOrder_money() {
        return order_money;
    }

    public String getReturn_date() {
        return return_date;
    }

    public String getReturn_id() {
        return return_id;
    }

    public String getReturn_reason() {
        return return_reason;
    }

    public String getReturn_state() {
        return return_state;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public void setCustom_tele(String custom_tele) {
        this.custom_tele = custom_tele;
    }

    public void setCustom_id(String custom_id) {
        this.custom_id = custom_id;
    }

    public void setCustom_name(String custom_name) {
        this.custom_name = custom_name;
    }

    public void setOrder_money(String order_money) {
        this.order_money = order_money;
    }

    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }

    public void setReturn_id(String return_id) {
        this.return_id = return_id;
    }

    public void setReturn_reason(String return_reason) {
        this.return_reason = return_reason;
    }

    public void setReturn_state(String return_state) {
        this.return_state = return_state;
    }
}
