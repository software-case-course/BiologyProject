package com.sun.biologyproject.bean;

import cn.bmob.v3.BmobUser;

/**
 * Created by SUN on 2017/5/17.
 */
public class User extends BmobUser {

    private String nickName;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
