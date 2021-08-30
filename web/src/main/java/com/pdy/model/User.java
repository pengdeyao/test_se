package com.pdy.model;

import java.util.Date;

/**
 * Created by pengdeyao on 2018/9/21
 */
public class User {

    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }



    @Override
    public String toString() {
        return "User{" +
                "date=" + date +
                '}';
    }
}
