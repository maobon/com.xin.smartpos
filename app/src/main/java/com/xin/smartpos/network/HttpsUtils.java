package com.xin.smartpos.network;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 封装的支持Https连接的Okhttp
 */

public class HttpsUtils {

    private static MyTrustManager mMyTrustManager;
    private static String requestjson;
    private static final int TIME_OUT = 10;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;
        try {
            mMyTrustManager = new MyTrustManager();
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{mMyTrustManager}, new SecureRandom());
            ssfFactory = sc.getSocketFactory();
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }

        return ssfFactory;
    }

    //实现X509TrustManager接口
    public static class MyTrustManager implements X509TrustManager {
        @SuppressLint("TrustAllX509TrustManager")
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @SuppressLint("TrustAllX509TrustManager")
        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    //实现HostnameVerifier接口
    private static class TrustAllHostnameVerifier implements HostnameVerifier {
        @SuppressLint("BadHostnameVerifier")
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    public static OkHttpClient getTrustAllClient() {
        OkHttpClient.Builder mBuilder = new OkHttpClient.Builder();
        mBuilder.sslSocketFactory(createSSLSocketFactory(), mMyTrustManager)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .hostnameVerifier(new TrustAllHostnameVerifier());
        return mBuilder.build();
    }

    public static String OkhttpPost(String url, String json, final Handler mHandler, final int btnId) {
        final Message msg = new Message();

        RequestBody body = RequestBody.create(JSON, json);

        final Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        getTrustAllClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                msg.what = 11;
                msg.obj = "网络出现异常！";
                mHandler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (btnId == 0) {
                    msg.what = 10;
                    String respon = response.body().string();
                    msg.obj = respon;
                    mHandler.sendMessage(msg);
                } else if (btnId == 1) {
                    msg.what = 12;
                    String respon = response.body().string();
                    msg.obj = respon;
                    mHandler.sendMessage(msg);
                } else if (btnId == 2) {
                    msg.what = 13;
                    String respon = response.body().string();
                    msg.obj = respon;
                    mHandler.sendMessage(msg);
                }

            }
        });
        return requestjson;
    }
}