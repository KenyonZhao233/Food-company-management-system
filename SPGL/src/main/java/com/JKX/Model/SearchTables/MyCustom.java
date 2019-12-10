package com.JKX.Model.SearchTables;
import java.util.PrimitiveIterator;
public class MyCustom {
    private String custom_id;
    private String custom_tp_name;
    private String custom_name;
    private String custom_wg;
    private String custom_tele;

    public MyCustom()
    {

    }

    public MyCustom(String custom_id,String custom_tp_name,String custom_name,String custom_wg,String custom_tele)
    {
        this.custom_id=custom_id;
        this.custom_tp_name=custom_tp_name;
        this.custom_name=custom_name;
        this.custom_wg=custom_wg;
        this.custom_tele=custom_tele;
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

    public String getCustom_tp_name() {
        return custom_tp_name;
    }

    public String getCustom_wg() {
        return custom_wg;
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

    public void setCustom_tp_name(String custom_tp_name) {
        this.custom_tp_name = custom_tp_name;
    }

    public void setCustom_wg(String custom_wg) {
        this.custom_wg = custom_wg;
    }
}
