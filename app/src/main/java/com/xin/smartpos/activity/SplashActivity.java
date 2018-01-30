package com.xin.smartpos.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.xin.smartpos.R;

/**
 * Created by xin on 1/29/18.
 * 欢迎界面
 */

public class SplashActivity extends AppCompatActivity {

    private static final int MSG_START_MAIN_ACTIVITY = 100;
    private static final int DELAY_TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (msg.what == MSG_START_MAIN_ACTIVITY) {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                }
                return true;
            }
        });

        handler.sendEmptyMessageDelayed(MSG_START_MAIN_ACTIVITY, DELAY_TIME);
    }
}
