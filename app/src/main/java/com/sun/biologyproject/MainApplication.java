package com.sun.biologyproject;

import android.app.Application;

import com.aitangba.swipeback.ActivityLifecycleHelper;
import com.sun.biologyproject.bean.BiologyBean;
import com.sun.biologyproject.utils.SDKUtil;
import com.sun.biologyproject.utils.SharedUtils;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
//import com.tencent.smtt.sdk.QbSdk;

/**
 * Created by SUN on 2017/3/17.
 */
public class MainApplication extends Application {

    public static List<BiologyBean> collectList = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化Bomb
        Bmob.initialize(this, SDKUtil.BmobApplicationID);
        //初始化界面向右滑动，返回前一个activity
        registerActivityLifecycleCallbacks(ActivityLifecycleHelper.build());

        SharedUtils.writeMyTools(this, "");
    }
}
