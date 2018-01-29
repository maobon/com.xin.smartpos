package com.xin.smartpos.utils;

import com.google.gson.Gson;
import com.xin.smartpos.bean.RequestStateJsonBean;

/**
 * 解析json
 *
 * @author 朱志强
 * @created at 2017/10/27 下午3:16
 */

public class AnalysisJson {
    public static RequestStateJsonBean requestState(String json) {
        Gson gson = new Gson();
        if (json != null || !json.isEmpty()) {
            RequestStateJsonBean rsjb = gson.fromJson(json, RequestStateJsonBean.class);
            if (rsjb != null || !json.isEmpty()) {
                return rsjb;
            }
        }
        return null;
    }
}
