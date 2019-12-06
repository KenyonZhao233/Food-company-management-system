package com.JKX.Model.Table;

public class Custom {
    private String custom_id;
    private CustomType customType;
    private String custom_name;
    private int wg;
    private String custom_phone;

    public Custom()
    {

    }

    public Custom(String custom_id, CustomType customType, String custom_name, int wg, String custom_phone)
    {
        this.custom_id = custom_id;
        this.customType = customType;
        this.custom_name = custom_name;
        this.wg = wg;
        this.custom_phone = custom_phone;
    }

    public CustomType getCustomType() {
        return customType;
    }

    public int getWg() {
        return wg;
    }

    public String getCustom_id() {
        return custom_id;
    }

    public String getCustom_name() {
        return custom_name;
    }

    public String getCustom_phone() {
        return custom_phone;
    }

    public void setCustom_id(String custom_id) {
        this.custom_id = custom_id;
    }

    public void setCustom_name(String custom_name) {
        this.custom_name = custom_name;
    }

    public void setCustom_phone(String custom_phone) {
        this.custom_phone = custom_phone;
    }

    public void setCustomType(CustomType customType) {
        this.customType = customType;
    }

    public void setWg(int wg) {
        this.wg = wg;
    }
}
