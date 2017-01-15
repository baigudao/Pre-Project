package com.jackie.entity;

import java.util.Date;

/**
 * Created by baigu on 2016/5/19.
 */
public class Account {

    private int id;
    private String lname;
    private String pwd;
    private Date created_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Date getCreated_time() {
        return created_time;
    }

    public void setCreated_time(Date created_time) {
        this.created_time = created_time;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", lname='" + lname + '\'' +
                ", pwd='" + pwd + '\'' +
                ", created_time=" + created_time +
                '}';
    }
}
