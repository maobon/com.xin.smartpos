package com.xin.smartpos.utils;


import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;

/**
 * MD5加密的工具类
 *
 * @author 朱志强
 * @created at 2017/10/26 上午11:14
 */
public class MD5Util {
    public static String encode(String str) {
        try {
            byte[] bytes = null;
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes("UTF-8"));
            bytes = md.digest();

            StringBuilder stringBuilder = new StringBuilder("");
            for (int i = 0; i < bytes.length; i++) {
                int v = bytes[i] & 0xFF;
                String hv = Integer.toHexString(v);
                if (hv.length() < 2) {
                    stringBuilder.append(0);
                }
                stringBuilder.append(hv);
            }
            return stringBuilder.toString();
        } catch (GeneralSecurityException gse) {
            return "";
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
