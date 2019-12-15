package com.JKX.Model.SearchTables;

public class ProductRec {
    private String product_date;
    private String product_id;
    private String product_name;
    private String product_lx;
    private String product_num;
    private String product_fzrid;

    public ProductRec()
    {

    }

    public ProductRec(String product_date,String product_id,String product_name,String product_lx,String product_num,String product_fzrid)
    {
        this.product_date=product_date;
        this.product_id=product_id;
        this.product_name=product_name;
        this.product_lx=product_lx;
        this.product_num=product_num;
        this.product_fzrid=product_fzrid;
    }

    public String getProduct_num() {
        return product_num;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getProduct_date() {
        return product_date;
    }

    public String getProduct_fzrid() {
        return product_fzrid;
    }

    public String getProduct_lx() {
        return product_lx;
    }

    public void setProduct_num(String product_num) {
        this.product_num = product_num;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setProduct_date(String product_date) {
        this.product_date = product_date;
    }

    public void setProduct_fzrid(String product_fzrid) {
        this.product_fzrid = product_fzrid;
    }

    public void setProduct_lx(String product_lx) {
        this.product_lx = product_lx;
    }
}
