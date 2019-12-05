package com.JKX.Model.Table;

public class CustomType {
    private String type_id;
    private String type_name;
    private float type_bl;

    public CustomType(String type_id, String type_name, float type_bl)
    {
        this.type_id = type_id;
        this.type_name = type_name;
        this.type_bl = type_bl;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public void setType_bl(float type_bl) {
        this.type_bl = type_bl;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public float getType_bl() {
        return type_bl;
    }

    public String getType_id() {
        return type_id;
    }

    public String getType_name() {
        return type_name;
    }
}
