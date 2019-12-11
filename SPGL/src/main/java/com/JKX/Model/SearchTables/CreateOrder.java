package com.JKX.Model.SearchTables;

public class CreateOrder {
    private String product_id;
    private String product_name;
    private String product_oneprice;
    private String product_num;
    private String product_allprice;

    public  CreateOrder()
    {

    }

    public CreateOrder(String product_id,String product_name,String product_oneprice,String product_num,String product_allprice)
    {
        this.product_id=product_id;
        this.product_name=product_name;
        this.product_oneprice=product_oneprice;
        this.product_num=product_num;
        this.product_allprice=product_allprice;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public void setProduct_num(String product_num) {
        this.product_num = product_num;
    }

    public void setProduct_allprice(String product_allprice) {
        this.product_allprice = product_allprice;
    }

    public void setProduct_oneprice(String product_oneprice) {
        this.product_oneprice = product_oneprice;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getProduct_num() {
        return product_num;
    }

    public String getProduct_allprice() {
        return product_allprice;
    }

    public String getProduct_oneprice() {
        return product_oneprice;
    }
}
