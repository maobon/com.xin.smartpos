package com.xin.smartpos.utils;


import okhttp3.MediaType;

/**
 * 常量的定义
 *
 * @author 朱志强
 * @created at 2017/10/27 上午11:33
 */
public class Constants {

    //登录的接口
    public static final String LOGINURL = "https://192.168.6.202:9443/WatchPay/app/login";
    public static final String LOGOUT = "https://192.168.6.202:9443/WatchPay/app/logout";
    //绑定的接口
    public static final String BINDING = "https://192.168.6.202:9443/WatchPay/app/binding";
    //绑定查询的接口
    public static final String QUERTBINDING = "https://192.168.6.202:9443/WatchPay/app/queryBinding";
    //向手表同步设置的接口
    public static final String GETPARAM = "https://192.168.6.202:9443/WatchPay/app/getParam";
    //修改个人参数设置接口
    public static final String UPDATEPARAM = "https://192.168.6.202:9443/WatchPay/app/updateParam";
    //上传GPS接口
    public static final String SYNCHROMSG = "https://192.168.6.202:9443/WatchPay/app/synchroMsg";
    //查询交易详情接口
    public static final String QUERYTRANSMSG = "https://192.168.6.202:9443/WatchPay/app/queryTransMsg";
    //生成支付码接口
    public static final String GENQR = "https://192.168.6.202:9443/WatchPay/app/genQR";
    //在线扫码支付接口
    public static final String ONLINEPAY = "https://192.168.6.202:9443/WatchPay/app/onlinePay";
    //okhttp请求的类型
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    //解绑接口
    public static final String UNBINDING = "https://192.168.6.202:9443/WatchPay/app/unBinding";
}
