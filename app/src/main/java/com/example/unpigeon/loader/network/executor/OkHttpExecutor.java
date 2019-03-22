package com.example.unpigeon.loader.network.executor;

import com.example.unpigeon.loader.network.ICallback;
import com.example.unpigeon.loader.network.RequestParams;
import com.example.unpigeon.loader.network.config.OkHttpConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;

public class OkHttpExecutor implements IExecutor {
    public static OkHttpClient sOkHttpClient;
    public static final Map<Object, ArrayList<Call>> calls = new HashMap<>();

    public OkHttpExecutor(OkHttpConfig okHttpConfig) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .retryOnConnectionFailure(okHttpConfig.isRetryOnConnectionFailure())
                .connectTimeout(okHttpConfig.getConnectTimeout(), TimeUnit.MILLISECONDS)
                .readTimeout(okHttpConfig.getReadTimeout(), TimeUnit.MILLISECONDS)
                .writeTimeout(okHttpConfig.getWriteTimeout(), TimeUnit.MILLISECONDS);
        if (okHttpConfig.getCache() != null) {
            builder.cache(okHttpConfig.getCache());
        }
        if (okHttpConfig.getSSLSocketFactory() != null && okHttpConfig.getX509TrustManager() != null) {
            builder.sslSocketFactory(okHttpConfig.getSSLSocketFactory(), okHttpConfig.getX509TrustManager());
        }
        if (okHttpConfig.getInterceptors() != null && okHttpConfig.getInterceptors().size() > 0) {
            for (int i = 0; i < okHttpConfig.getInterceptors().size(); i++) {
                builder.addInterceptor(okHttpConfig.getInterceptors().get(i));
            }
        }
        if (okHttpConfig.getNetworkInterceptors() != null && okHttpConfig.getNetworkInterceptors().size() > 0) {
            for (int i = 0; i < okHttpConfig.getNetworkInterceptors().size(); i++) {
                builder.addNetworkInterceptor(okHttpConfig.getNetworkInterceptors().get(i));
            }
        }
        sOkHttpClient = builder.build();
    }

    @Override
    public void doGet(RequestParams requestParams, ICallback iCallback) {

    }

    @Override
    public void doPost(RequestParams requestParams, ICallback iCallback) {

    }

    @Override
    public void doJsonPost(RequestParams requestParams, ICallback iCallback) {

    }

    @Override
    public void doUploadFile(RequestParams requestParams, ICallback iCallback) {

    }

    @Override
    public void doDownloadFile(RequestParams requestParams, ICallback iCallback) {

    }

    @Override
    public void cancel(Object tag) {

    }

    @Override
    public void cancelAll() {

    }
}
