package com.JKX.Model.Table;

import java.util.PrimitiveIterator;

public class Production {
    private String production_id;
    private String production_name;
    private float production_p1;
    private float production_p2;
    private float production_p3;
    private int production_bzq;
    private int production_kc;
    private int nums;
    private Raw[] raws;

    public Production(String id, String name, float p1, float p2, float p3, int bzq, Raw[] raws, int nums)
    {
        this.raws = raws;
        this.production_id = id;
        this.production_name = name;
        this.production_p1 = p1;
        this.production_p2 = p2;
        this.production_p3 = p3;
        this.production_bzq = bzq;
        this.production_kc = 0;
        this.nums = this.nums;
    }

    public Production(String id, String name, float p1, float p2, float p3, int bzq, int kc, Raw[] raws, int nums)
    {
        this.nums = nums;
        this.raws = raws;
        this.production_id = id;
        this.production_name = name;
        this.production_p1 = p1;
        this.production_p2 = p2;
        this.production_p3 = p3;
        this.production_bzq = bzq;
        this.production_kc = kc;
    }

    public void setRaws(Raw[] raws) {
        this.raws = raws;
    }

    public void setProduction_kc(int production_kc) {
        this.production_kc = production_kc;
    }

    public void setProduction_name(String production_name) {
        this.production_name = production_name;
    }

    public void setProduction_id(String production_id) {
        this.production_id = production_id;
    }

    public void setProduction_p1(float production_p1) {
        this.production_p1 = production_p1;
    }

    public void setProduction_p2(float production_p2) {
        this.production_p2 = production_p2;
    }

    public void setProduction_p3(float production_p3) {
        this.production_p3 = production_p3;
    }

    public void setProduction_bzq(int production_bzq) {
        this.production_bzq = production_bzq;
    }

    public void setNums(int nums) {
        this.nums = nums;
    }

    public String getProduction_id() {
        return production_id;
    }

    public String getProduction_name() {
        return production_name;
    }

    public float getProduction_p1() {
        return production_p1;
    }

    public float getProduction_p2() {
        return production_p2;
    }

    public float getProduction_p3() {
        return production_p3;
    }

    public int getProduction_kc() {
        return production_kc;
    }

    public int getProduction_bzq() {
        return production_bzq;
    }

    public int getNums() {
        return nums;
    }

    public Raw[] getRaws() {
        return raws;
    }
}
