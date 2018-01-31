package com.xin.smartpos.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.xin.smartpos.R;

/**
 * Created by xin on 1/30/18.
 * 支付结果
 */

public class PaymentResultActivity extends AppCompatActivity {

    private ImageView ivSuccessIcon, ivFailedIcon;
    private TextView tvSuccess, tvFailed;

    /**
     * start PaymentResultActivity
     *
     * @param context       context
     * @param paymentStatus 是否支付成功
     * @param message       服务端响应信息
     */
    public static void startActivity(Context context, boolean paymentStatus, String message) {
        Intent intent = new Intent(context, PaymentResultActivity.class);
        intent.putExtra("status", paymentStatus);
        intent.putExtra("message", message);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_result);

        boolean status = getIntent().getBooleanExtra("status", false);
        String msg = getIntent().getStringExtra("message");

        initViews(status, msg);
    }

    /**
     * @param status  支付状态
     * @param message serverResp
     */
    private void initViews(boolean status, String message) {
        ivSuccessIcon = findViewById(R.id.iv_succ_icon);
        ivFailedIcon = findViewById(R.id.iv_failed_icon);
        tvSuccess = findViewById(R.id.tv_result_success);
        tvFailed = findViewById(R.id.tv_result_failed);

        if (status) {
            ivSuccessIcon.setVisibility(View.VISIBLE);
            tvSuccess.setVisibility(View.VISIBLE);
            tvSuccess.setText(message);
        } else {
            ivFailedIcon.setVisibility(View.VISIBLE);
            tvFailed.setVisibility(View.VISIBLE);
            tvFailed.setText(message);
        }

        Button btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
