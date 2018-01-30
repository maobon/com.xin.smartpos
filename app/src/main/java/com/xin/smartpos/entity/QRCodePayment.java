package com.xin.smartpos.entity;

/**
 * Created by xin on 1/30/18.
 * 扫码支付
 */

public class QRCodePayment {

    /**
     * POS机扫码 QRCode
     */
    private String payImg;
    /**
     * POS机 商家输入金额
     */
    private String money;
    /**
     * GPS longitude 经度
     */
    private String gpsLon;
    /**
     * GPS latitude 纬度
     */
    private String gpsLat;

    public String getPayImg() {
        return payImg;
    }

    public void setPayImg(String payImg) {
        this.payImg = payImg;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getGpsLon() {
        return gpsLon;
    }

    public void setGpsLon(String gpsLon) {
        this.gpsLon = gpsLon;
    }

    public String getGpsLat() {
        return gpsLat;
    }

    public void setGpsLat(String gpsLat) {
        this.gpsLat = gpsLat;
    }
}
