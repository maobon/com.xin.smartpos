package com.xin.smartpos.utils;

import android.util.Base64;

import java.io.UnsupportedEncodingException;

/**
 * Created by xin on 1/29/18.
 * Base64 工具类
 */

public class Base64Utils {

    /**
     * String转为Base64格式
     *
     * @param str String
     * @return base64格式的字符串
     */
    public static String encodeToBase64(String str) {
        String result = "";
        if (str != null) {
            try {
                result = new String(Base64.encode(str.getBytes("utf-8"), Base64.NO_WRAP), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * Base64格式转为String
     *
     * @param str base64String
     * @return 原始字符串
     */
    public static String decodeFromBase64(String str) {
        String result = "";
        if (str != null) {
            try {
                result = new String(Base64.decode(str, Base64.NO_WRAP), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
