package com.sun.biologyproject.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by SUN on 2017/5/4.
 */

public class ToastUtils {

    public static void showShortToast(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void showLongToast(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}
