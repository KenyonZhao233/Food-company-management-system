package com.JKX.Model.SearchTables;
import java.util.PrimitiveIterator;
public class ProductSales {
    private String product_name;
    private String product_id;
    private String product_sales;
    private String product_p1;
    private String product_p2;
    private String product_p3;

    public ProductSales()
    {

    }

    public ProductSales(String product_name,String product_id,String product_sales,String product_p1,String product_p2,String product_p3)
    {
        this.product_name=product_name;
        this.product_id=product_id;
        this.product_sales=product_sales;
        this.product_p1=product_p1;
        this.product_p2=product_p2;
        this.product_p3=product_p3;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getProduct_p1() {
        return product_p1;
    }

    public String getProduct_p2() {
        return product_p2;
    }

    public String getProduct_p3() {
        return product_p3;
    }

    public String getProduct_sales() {
        return product_sales;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setProduct_p1(String product_p1) {
        this.product_p1 = product_p1;
    }

    public void setProduct_p2(String product_p2) {
        this.product_p2 = product_p2;
    }

    public void setProduct_p3(String product_p3) {
        this.product_p3 = product_p3;
    }

    public void setProduct_sales(String product_sales) {
        this.product_sales = product_sales;
    }

}
