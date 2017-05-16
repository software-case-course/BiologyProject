package com.sun.biologyproject.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by SUN on 2017/5/17.
 */
public class User extends BmobObject {
    private String userPhone;
    private String userName;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
