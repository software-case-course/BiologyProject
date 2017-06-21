package com.sun.biologyproject.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.sun.biologyproject.bean.User;


/**
 * SharedPreference的工具类
 * Created by SUN on 2017/5/17
 */

public class SharedUtils {
    private static String SHARE_TABLE_NAME = "BiologyProject";

    public static final String USER_PHONE = "user_phone";
    public static final String USER_NAME = "user_name";
    public static final String LAST_USER_PHONE = "last_user_phone";
    public static final String BIOLOGY_LIST = "BIOLOGY_LIST";

    public static final String MY_TOOLS = "my_tools";//收集水生生物的工具

    /**
     * 写操作
     * @param context
     * @param value
     * @param tagName
     * @return
     */
    public static boolean writeString(Context context, String value, String tagName){
        if (context == null){
            return false;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARE_TABLE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(tagName, value);
        editor.commit();
        return true;
    }

    public static boolean writeBoolean(Context context, boolean value, String tagName){
        if (context == null){
            return false;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARE_TABLE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(tagName, value);
        editor.commit();
        return true;
    }


    /**
     * 读操作
     * @param context
     * @param tagName
     * @param defaultVal
     * @return
     */
    public static String readString(Context context, String tagName, String defaultVal){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARE_TABLE_NAME, Context.MODE_PRIVATE);
        String value = sharedPreferences.getString(tagName, defaultVal);
        return value;
    }

    public static boolean readBoolean(Context context, String tagName, boolean defaultVal){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARE_TABLE_NAME, Context.MODE_PRIVATE);
        boolean value = sharedPreferences.getBoolean(tagName, defaultVal);
        return value;
    }


    /**
     * 保存最后一次登录的用户的手机号码
     * @param context
     * @param userPhone
     */
    public static void saveLastLoginUserPhone(Context context, String userPhone){
        writeString(context, userPhone, LAST_USER_PHONE);
    }

    /**
     * 获取最后一次登录的用户的手机号码
     * @param context
     * @return
     */
    public static String getLastLoginUserPhone(Context context){
        return readString(context, LAST_USER_PHONE, "");
    }

    public static void writeMyTools(Context context, String tools){
        writeString(context, tools, MY_TOOLS);
    }

    public static String readMyTools(Context context){
        return readString(context, MY_TOOLS, "");
    }

}
