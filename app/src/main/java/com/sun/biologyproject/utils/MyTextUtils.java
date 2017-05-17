package com.sun.biologyproject.utils;

import java.util.regex.Pattern;

/**
 * Created by SUN on 2017/5/7.
 */
public class MyTextUtils {
    public static boolean isEmpty(String string){
        if (string == null){
            return true;
        }else {
            return android.text.TextUtils.isEmpty(string);
        }
    }

    /**
     * 判断是否为有效合理的手机号码
     * @param phoneNum
     * @return
     */
    public static boolean isAccountLegitimate(String phoneNum){
        String regex_1 = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";//手机号码
        return Pattern.compile(regex_1).matcher(phoneNum).matches();
    }

    /**
     * 检验密码是否合法
     * @param password
     * @return
     */
    public static boolean isPasswordLegitimate(String password){
        if(password.length() < 6 || password.length() > 20)
            return false;
        return true;
    }

    /**
     * 判断身份证格式是否合法
     * @param idCard
     * @return
     */
    public static boolean isIDCardLegitimate(String idCard){
        String regex_15 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
        String regex_18 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X|x)$";
        return Pattern.compile(regex_15).matcher(idCard).matches() || Pattern.compile(regex_18).matcher(idCard).matches();
    }

}
