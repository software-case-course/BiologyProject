package com.sun.biologyproject;

import android.app.Application;

import com.sun.biologyproject.utils.SDKUtil;

import cn.bmob.v3.Bmob;
//import com.tencent.smtt.sdk.QbSdk;

/**
 * Created by SUN on 2017/3/17.
 */
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化Bomb
        Bmob.initialize(this, SDKUtil.BmobApplicationID);
    }
}
