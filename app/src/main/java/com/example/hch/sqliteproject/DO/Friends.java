package com.example.hch.sqliteproject.DO;

/**
 * Created by hch on 2018-01-31.
 */

public class Friends {
    private int f_id;
    private String f_name;
    private String f_phone;
    private String f_comment;

    public Friends() {
        this.f_id = -1;
        this.f_name = "";
        this.f_phone = "";
        this.f_comment = "";
    }

    public Friends(int f_id, String f_name, String f_phone, String f_comment) {
        this.f_id = f_id;
        this.f_name = f_name;
        this.f_phone = f_phone;
        this.f_comment = f_comment;
    }

    public int getF_id() {
        return f_id;
    }

    public void setF_id(int f_id) {
        this.f_id = f_id;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getF_phone() {
        return f_phone;
    }

    public void setF_phone(String f_phone) {
        this.f_phone = f_phone;
    }

    public String getF_comment() {
        return f_comment;
    }

    public void setF_comment(String f_comment) {
        this.f_comment = f_comment;
    }
}
