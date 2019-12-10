package com.JKX.Model.SearchTables;

public class ProductKuCun {
    private String product_id;
    private String product_name;
    private String product_num;
    private String product_in;

    public ProductKuCun()
    {

    }

    public ProductKuCun(String product_id,String product_name,String product_num,String product_in)
    {
        this.product_id=product_id;
        this.product_name=product_name;
        this.product_num=product_num;
        this.product_in=product_in;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getProduct_in() {
        return product_in;
    }

    public String getProduct_num() {
        return product_num;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public void setProduct_in(String product_in) {
        this.product_in = product_in;
    }

    public void setProduct_num(String product_num) {
        this.product_num = product_num;
    }
}
