package com.xin.smartpos.entity;


/**
 * Created by xin on 1/29/18.
 * 状态信息
 */
public class RequestStateJsonBean {

    private String result;     //状态
    private String message;    //提示信息
    private int transExCount;  //交易笔数

    public void setTransExCount(int transExCount) {
        this.transExCount = transExCount;
    }

    public int getTransExCount() {

        return transExCount;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }
}
