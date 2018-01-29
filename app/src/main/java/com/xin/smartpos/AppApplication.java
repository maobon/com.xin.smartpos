package com.xin.smartpos;

import android.app.Application;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * Created by xin on 1/29/18.
 * ZXing库初始化
 */

public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ZXingLibrary.initDisplayOpinion(this);
    }
}
