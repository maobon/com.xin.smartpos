package com.xin.smartpos.utils;


import okhttp3.MediaType;

/**
 * Created by xin on 1/29/18.
 * 常量
 */

public class Constants {

    public static final boolean USE_DEBUG = false;

    /**
     * POS机扫码支付URL
     */
    public static final String PAY_SCAN_WATCH_QR_CODE = "https://192.168.6.202:7443/WatchCustomerPay/app/offlinePay";
    /**
     * okHttp请求JSON类型
     */
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");


    // Baidu Map
    /**
     * GPS定位结果，GPS定位成功
     */
    public static final int GPS_LOCATION_SUCCESS = 61;
    /**
     * 无法获取有效定位依据，定位失败，请检查运营商网络或者WiFi网络是否正常开启，尝试重新请求定位
     */
    public static final int LOCATION_FAILED = 62;
    /**
     * 网络异常，没有成功向服务器发起请求，请确认当前测试手机网络是否通畅，尝试重新请求定位
     */
    public static final int NETWORK_ERROR = 63;

    /**
     * 离线定位结果。通过requestOfflineLocaiton调用时对应的返回结果
     */
    public static final int OFFLINE_LOCATION = 66;
    /**
     * 网络连接失败时，查找本地离线定位时对应的返回结果
     */
    public static final int NETWORK_ERROR_CHECK_OFFLINE_LOCATION = 68;
    /**
     * 网络定位结果，网络定位成功
     */
    public static final int NETWORK_LOCATION_SUCCESS = 161;
    /**
     * 请求串密文解析失败，一般是由于客户端SO文件加载失败造成，请严格参照开发指南或demo开发，放入对应SO文件
     */
    public static final int PARSE_FAILED = 162;
    /**
     * 服务端定位失败，请您检查是否禁用获取位置信息权限，尝试重新请求定位
     */
    public static final int SERVER_LOCATION_FAILED = 167;
    /**
     * AK不存在或者非法，请按照说明文档重新申请AK
     */
    public static final int AK_INVALID = 505;
}
