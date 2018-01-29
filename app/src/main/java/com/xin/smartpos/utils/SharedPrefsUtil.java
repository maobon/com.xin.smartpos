package com.xin.smartpos.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by xin on 1/29/18.
 * SharedPreference util
 */

public class SharedPrefsUtil {

    public final static String SETTING = "ACCOUNTNUMBER";    // 账号

    public static void putValue(Context context, String key, String value) {
        SharedPreferences.Editor sp = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();
        sp.putString(key, value);
        sp.apply();
    }

    public static String getValue(Context context, String key, String defValue) {
        SharedPreferences sp = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        String value = sp.getString(key, defValue);
        return value;
    }
}
