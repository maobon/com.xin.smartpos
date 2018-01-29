package com.xin.smartpos.utils;

import com.google.gson.Gson;
import com.xin.smartpos.entity.RequestStateJsonBean;

/**
 * 解析json
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
