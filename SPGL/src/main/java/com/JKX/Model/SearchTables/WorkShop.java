package com.JKX.Model.SearchTables;

public class WorkShop {
    private String cj_id;
    private String cj_fzr;
    private String cj_productid;
    private String product_name;
    private String cj_num;

    public WorkShop()
    {

    }

    public WorkShop(String cj_id,String cj_fzr,String cj_productid,String product_name,String cj_num)
    {
        this.cj_id=cj_id;
        this.cj_fzr=cj_fzr;
        this.cj_productid=cj_productid;
        this.product_name=product_name;
        this.cj_num=cj_num;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getCj_fzr() {
        return cj_fzr;
    }

    public String getCj_id() {
        return cj_id;
    }

    public String getCj_num() {
        return cj_num;
    }

    public String getCj_productid() {
        return cj_productid;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setCj_fzr(String cj_fzr) {
        this.cj_fzr = cj_fzr;
    }

    public void setCj_id(String cj_id) {
        this.cj_id = cj_id;
    }

    public void setCj_num(String cj_num) {
        this.cj_num = cj_num;
    }

    public void setCj_productid(String cj_productid) {
        this.cj_productid = cj_productid;
    }
}
