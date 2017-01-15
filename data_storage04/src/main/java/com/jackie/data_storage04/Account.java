package com.jackie.data_storage04;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2016/8/28.
 */
public class Account implements Serializable {

    private int id;
    private String lname;
    private String pwd;
    private Date date;

    public Account() {}

    public Account(int id, String lname, String pwd, Date date) {
        this.id = id;
        this.lname = lname;
        this.pwd = pwd;
        this.date = date;
    }

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", lname='" + lname + '\'' +
                ", pwd='" + pwd + '\'' +
                ", date=" + date +
                '}';
    }
}
