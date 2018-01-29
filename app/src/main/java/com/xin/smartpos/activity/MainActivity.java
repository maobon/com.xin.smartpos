package com.xin.smartpos.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import com.baidu.location.Address;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.xin.smartpos.R;
import com.xin.smartpos.bean.RequestStateJsonBean;
import com.xin.smartpos.utils.AnalysisJson;

import java.lang.ref.WeakReference;


/**
 * Created by xin on 1/29/18.
 * 主界面
 */

public class MainActivity extends AppCompatActivity {

    public static final int REQ_CODE_SCANNER_ACTIVITY = 100;

    private EditText etAmount, etLongitude, etLatitude;
    private ScrollView rootView;

    public LocationClient mLocationClient;
    private MyLocationListener myListener = new MyLocationListener();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initBaiduSDK();

        Toast.makeText(this, "GPS定位中 请稍后", Toast.LENGTH_SHORT).show();
    }

    private void initBaiduSDK() {
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(myListener);

        // Baidu location SDK config
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setCoorType("bd09ll");
        option.setScanSpan(0); // 只定位一次
        option.setOpenGps(true);
        option.setLocationNotify(true);
        option.setIgnoreKillProcess(false);
        option.SetIgnoreCacheException(false);
        option.setWifiCacheTimeOut(5 * 60 * 1000);
        option.setEnableSimulateGps(false);

        mLocationClient.setLocOption(option);

        // 开始定位
        mLocationClient.start();
    }

    private void initViews() {
        rootView = findViewById(R.id.sv_rootview);
        etAmount = findViewById(R.id.et_amount);
        etLatitude = findViewById(R.id.et_latitude);
        etLongitude = findViewById(R.id.et_longitude);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != RESULT_OK || data == null) return;

        if (requestCode == REQ_CODE_SCANNER_ACTIVITY) {
            Bundle bundle = data.getExtras();
            if (bundle == null) {
                return;
            }

            if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                String result = bundle.getString(CodeUtils.RESULT_STRING);

                // TODO 提交给服务端
                // 1. 商户输入价格
                // 2. 扫描二维码的结果
                // 3. POS机当前的GPS坐标

            } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                Toast.makeText(MainActivity.this, "二维码扫描失败", Toast.LENGTH_LONG).show();
            }
        }
    }

    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            Snackbar.make(rootView, "当前位置获取成功", Snackbar.LENGTH_SHORT).show();

            double latitude = location.getLatitude();
            double longitude = location.getLongitude();

            etLatitude.setText(String.valueOf(latitude));
            etLongitude.setText(String.valueOf(longitude));

            float radius = location.getRadius();
            Address address = location.getAddress();

            String coorType = location.getCoorType();
            int errorCode = location.getLocType();
            Log.d("TAG", "coorType= " + coorType + " errorCode= " + errorCode);
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
                Intent intent = new Intent(getApplication(), CaptureActivity.class);
                startActivityForResult(intent, REQ_CODE_SCANNER_ACTIVITY);
                break;
        }
        return true;
    }


    private static class MyHandler extends Handler {
        private WeakReference<Context> reference;
        public MyHandler(Context context) {
            reference = new WeakReference<Context>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String json = (String) msg.obj;
            MainActivity loginActivity = (MainActivity) reference.get();
            if (loginActivity != null) {
                switch (msg.what) {
                    case 10:

                        break;

                    case 11:

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
}
