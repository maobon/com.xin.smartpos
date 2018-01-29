package com.xin.smartpos.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 保存用户的工具类
 *
 * @author 朱志强
 * @created at 2017/10/30 下午4:13
 */

public class SharedPrefsUtil {

    public final static String SETTING = "ACCOUNTNUMBER";    //账号

    public static void putValue(Context context, String key, String value) {
        SharedPreferences.Editor sp = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();
        sp.putString(key, value);
        sp.commit();
    }

    public static String getValue(Context context, String key, String defValue) {
        SharedPreferences sp = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        String value = sp.getString(key, defValue);
        return value;
    }
}
