package com.xin.smartpos.bean;


/**
 * 请求状态的实体类
 *
 * @author 朱志强
 * @created at 2017/11/16 下午3:11
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
