package com.JKX.Model.SearchTables;
import java.util.PrimitiveIterator;
public class OneOrderRecord {
    private String order_id;
    private String order_date;
    private String order_product;
    private String order_num;
    private String order_zt;
    private String order_custom;
    private String order_type;
    private String order_fzr;

    public OneOrderRecord()
    {

    }

    public OneOrderRecord(String order_id,String order_date,String order_product,String order_num,String order_zt,String order_custom,String order_type,String order_fzr)
    {
        this.order_id=order_id;
        this.order_date=order_date;
        this.order_product=order_product;
        this.order_num=order_num;
        this.order_zt=order_zt;
        this.order_custom=order_custom;
        this.order_type=order_type;
        this.order_fzr=order_fzr;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public void setOrder_zt(String order_zt) {
        this.order_zt = order_zt;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public void setOrder_custom(String order_custom) {
        this.order_custom = order_custom;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    public void setOrder_product(String order_product) {
        this.order_product = order_product;
    }

    public void setOrder_fzr(String order_fzr) {
        this.order_fzr = order_fzr;
    }

    public String getOrder_zt() {
        return order_zt;
    }

    public String getOrder_type() {
        return order_type;
    }

    public String getOrder_id() {
        return order_id;
    }

    public String getOrder_custom() {
        return order_custom;
    }

    public String getOrder_date() {
        return order_date;
    }

    public String getOrder_num() {
        return order_num;
    }

    public String getOrder_product() {
        return order_product;
    }

    public String getOrder_fzr() {
        return order_fzr;
    }
}
