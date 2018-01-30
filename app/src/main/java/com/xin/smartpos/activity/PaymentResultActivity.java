package com.xin.smartpos.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
     */
    public static void startActivity(Context context, boolean paymentStatus) {
        Intent intent = new Intent(context, PaymentResultActivity.class);
        intent.putExtra("status", paymentStatus);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_result);

        boolean status = getIntent().getBooleanExtra("status", false);
        initViews(status);
    }

    private void initViews(boolean status) {
        ivSuccessIcon = findViewById(R.id.iv_succ_icon);
        ivFailedIcon = findViewById(R.id.iv_failed_icon);
        tvSuccess = findViewById(R.id.tv_result_success);
        tvFailed = findViewById(R.id.tv_result_failed);

        if (status) {
            ivSuccessIcon.setVisibility(View.VISIBLE);
            tvSuccess.setVisibility(View.VISIBLE);
        } else {
            ivFailedIcon.setVisibility(View.VISIBLE);
            tvFailed.setVisibility(View.VISIBLE);
        }
    }
}
