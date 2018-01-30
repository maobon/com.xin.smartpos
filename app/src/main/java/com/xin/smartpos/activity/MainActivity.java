package com.xin.smartpos.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.google.gson.Gson;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.xin.smartpos.R;
import com.xin.smartpos.entity.QRCodePayment;
import com.xin.smartpos.entity.RequestStateJsonBean;
import com.xin.smartpos.network.HttpsUtils;
import com.xin.smartpos.utils.AnalysisJson;
import com.xin.smartpos.utils.Base64Utils;
import com.xin.smartpos.utils.LoadingBarHelper;

import java.lang.ref.WeakReference;

import static com.xin.smartpos.utils.Constants.AK_INVALID;
import static com.xin.smartpos.utils.Constants.GPS_LOCATION_SUCCESS;
import static com.xin.smartpos.utils.Constants.LOCATION_FAILED;
import static com.xin.smartpos.utils.Constants.NETWORK_ERROR;
import static com.xin.smartpos.utils.Constants.NETWORK_ERROR_CHECK_OFFLINE_LOCATION;
import static com.xin.smartpos.utils.Constants.NETWORK_LOCATION_SUCCESS;
import static com.xin.smartpos.utils.Constants.OFFLINE_LOCATION;
import static com.xin.smartpos.utils.Constants.PARSE_FAILED;
import static com.xin.smartpos.utils.Constants.PAY_SCAN_WATCH_QR_CODE;
import static com.xin.smartpos.utils.Constants.SERVER_LOCATION_FAILED;
import static com.xin.smartpos.utils.Constants.USE_DEBUG;


/**
 * Created by xin on 1/29/18.
 * 主界面
 */

public class MainActivity extends AppCompatActivity {

    public static final int REQ_CODE_SCANNER_ACTIVITY = 200;

    private EditText etAmount, etLongitude, etLatitude;
    private ScrollView rootView;
    private TextView tvDebugUse;

    public LocationClient locationClient;
    private BaiduLocationListener baiduLocationListener = new BaiduLocationListener();
    private NetworkRequestHandler networkRequestHandler;
    // 是否定位成功
    private boolean isLocationSucc = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        networkRequestHandler = new NetworkRequestHandler(this);

        initViews();
        initBaiduSDK();
    }

    private void initBaiduSDK() {
        locationClient = new LocationClient(getApplicationContext());
        locationClient.registerLocationListener(baiduLocationListener);

        // Baidu location SDK config
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setCoorType("bd09ll");
        option.setScanSpan(1000);
        option.setOpenGps(true);
        option.setLocationNotify(true);
        option.setIgnoreKillProcess(false);
        option.SetIgnoreCacheException(false);
        option.setWifiCacheTimeOut(5 * 60 * 1000);
        option.setEnableSimulateGps(false);

        locationClient.setLocOption(option);

        // 开始定位
        locationClient.start();
    }

    private void initViews() {
        tvDebugUse = findViewById(R.id.tv_location_result);
        if (USE_DEBUG) {
            tvDebugUse.setVisibility(View.VISIBLE);
        } else {
            tvDebugUse.setVisibility(View.GONE);
        }
        rootView = findViewById(R.id.sv_rootview);
        etAmount = findViewById(R.id.et_amount);
        etLatitude = findViewById(R.id.et_latitude);
        etLongitude = findViewById(R.id.et_longitude);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK || data == null) {
            Snackbar.make(rootView, "支付操作未完成", Snackbar.LENGTH_SHORT).show();
            return;
        }

        if (requestCode == REQ_CODE_SCANNER_ACTIVITY) {
            Bundle bundle = data.getExtras();
            if (bundle == null) {
                return;
            }

            if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                String qrCode = bundle.getString(CodeUtils.RESULT_STRING);
                // debug use
                tvDebugUse.setText(qrCode);

                String payImgBase64 = Base64Utils.encodeToBase64(qrCode);
                // 支付信息
                QRCodePayment qrCodePayment = new QRCodePayment();
                qrCodePayment.setPayImg(payImgBase64);
                qrCodePayment.setMoney(etAmount.getText().toString());
                qrCodePayment.setGpsLon(etLongitude.getText().toString());
                qrCodePayment.setGpsLat(etLatitude.getText().toString());

                Gson gson = new Gson();
                // 请求网络
                HttpsUtils.OkhttpPost(PAY_SCAN_WATCH_QR_CODE, gson.toJson(qrCodePayment), networkRequestHandler, 0);
                LoadingBarHelper.showLoadingBar(MainActivity.this);

            } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                Toast.makeText(MainActivity.this, "二维码扫描失败", Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * 百度定位SDK回调
     */
    public class BaiduLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();

            etLatitude.setText(String.valueOf(latitude));
            etLongitude.setText(String.valueOf(longitude));

            float radius = location.getRadius();
            tvDebugUse.setText(latitude + " // " + longitude + " radius= " + radius);

            int errorCode = location.getLocType();
            parseBaiduMapErrorCode(errorCode);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_nextstep:
                if (!TextUtils.isEmpty(etAmount.getText().toString()) && isLocationSucc) {
                    Intent intent = new Intent(getApplication(), CaptureActivity.class);
                    startActivityForResult(intent, REQ_CODE_SCANNER_ACTIVITY);
                } else {
                    Snackbar.make(rootView, "GPS信息或收款金额错误", Snackbar.LENGTH_SHORT).show();
                }
                break;
        }
        return true;
    }


    /**
     * 处理网络请求
     */
    private static class NetworkRequestHandler extends Handler {

        private WeakReference<Context> reference;

        NetworkRequestHandler(Context context) {
            reference = new WeakReference<Context>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String json = (String) msg.obj;
            MainActivity mainActivity = (MainActivity) reference.get();
            if (mainActivity != null) {
                switch (msg.what) {
                    case 10:
                        LoadingBarHelper.dismissLoadingBar();
                        PaymentResultActivity.startActivity(mainActivity, true);
                        break;

                    case 11:
                        LoadingBarHelper.dismissLoadingBar();
                        PaymentResultActivity.startActivity(mainActivity, false);
                        break;
                }
            }
        }
    }


    /**
     * 获取请求的状态
     *
     * @param json
     * @param loginActivity
     */
    private static void getRequestState(String json, MainActivity loginActivity) {
        RequestStateJsonBean rsjs = AnalysisJson.requestState(json);
        String result = rsjs.getResult();
        String message = rsjs.getMessage();
        if (result.equals("10")) {

        } else if (result.equals("11")) {

        }
    }

    /**
     * 解析百度地图响应
     *
     * @param errorCode 错误码
     */
    private void parseBaiduMapErrorCode(int errorCode) {
        switch (errorCode) {
            case GPS_LOCATION_SUCCESS:
                locationClient.stop();
                isLocationSucc = true;
                Snackbar.make(rootView, "GPS定位成功", Snackbar.LENGTH_SHORT).show();
                break;

            case NETWORK_LOCATION_SUCCESS:
                locationClient.stop();
                isLocationSucc = true;
                Snackbar.make(rootView, "网络定位成功", Snackbar.LENGTH_SHORT).show();
                break;

            case LOCATION_FAILED:
            case NETWORK_ERROR:
            case OFFLINE_LOCATION:
            case NETWORK_ERROR_CHECK_OFFLINE_LOCATION:
            case PARSE_FAILED:
            case SERVER_LOCATION_FAILED:
                locationClient.stop();
                isLocationSucc = false;
                Snackbar.make(rootView, "定位失败", Snackbar.LENGTH_SHORT).show();
                break;

            case AK_INVALID:
                locationClient.stop();
                isLocationSucc = false;
                Snackbar.make(rootView, "AK码错误", Snackbar.LENGTH_SHORT).show();
                break;
        }
    }

}
