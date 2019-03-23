package com.example.unpigeon.loader.network.executor;

import com.example.unpigeon.loader.network.call.ICallback;
import com.example.unpigeon.loader.network.RequestParams;
import com.example.unpigeon.loader.network.call.OkHttpCallback;
import com.example.unpigeon.loader.network.config.OkHttpConfig;
import com.example.unpigeon.loader.network.help.ProgressRequestBody;
import com.example.unpigeon.loader.network.utils.OkHttpUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

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
        iCallback.onStart();
        Request request = new Request.Builder().url(OkHttpUtils.getUrlWithParams(requestParams)).get()
                .headers(OkHttpUtils.getHeaders(requestParams)).tag(requestParams.getTag()).build();
        Call call = sOkHttpClient.newCall(request);
        call.enqueue(new OkHttpCallback(requestParams));
        cacheCall(requestParams, call);
    }

    private void cacheCall(RequestParams requestParams, Call call) {
        ArrayList<Call> values = calls.get(requestParams.getTag());
        if (values == null) {
            values = new ArrayList<>();
            values.add(call);
            calls.put(requestParams, values);
        } else {
            values.add(call);
        }
    }

    @Override
    public void doPost(RequestParams requestParams, ICallback iCallback) {
        iCallback.onStart();
        Request request =
                new Request.Builder().url(requestParams.getUrl()).headers(OkHttpUtils.getHeaders(requestParams)).tag(requestParams.getTag()).build();
        Call call = sOkHttpClient.newCall(request);
        call.enqueue(new OkHttpCallback(requestParams));
        cacheCall(requestParams, call);
    }

    @Override
    public void doJsonPost(RequestParams requestParams, ICallback iCallback) {
        iCallback.onStart();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),
                requestParams.getBody());
        Request request = new Request.Builder().url(requestParams.getUrl()).post(requestBody)
                .headers(OkHttpUtils.getHeaders(requestParams)).tag(requestParams.getTag()).build();
        Call call = sOkHttpClient.newCall(request);
        call.enqueue(new OkHttpCallback(requestParams));
        cacheCall(requestParams, call);
    }

    @Override
    public void doUploadFile(RequestParams requestParams, ICallback iCallback) {
        iCallback.onStart();
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (requestParams.getFiles() != null && requestParams.getFiles().size() > 0) {
            for (Map.Entry<String, File> next : requestParams.getFiles().entrySet()) {
                builder.addFormDataPart(next.getKey(), next.getValue().getName(), RequestBody.create(MediaType.parse(
                        "image/png"), next.getValue()));
            }
        }
        ProgressRequestBody progressRequestBody = new ProgressRequestBody(builder.build(), requestParams);
        Request request = new Request.Builder().url(requestParams.getUrl()).post(progressRequestBody)
                .headers(OkHttpUtils.getHeaders(requestParams)).tag(requestParams.getTag()).build();
        Call call = sOkHttpClient.newCall(request);
        call.enqueue(new OkHttpCallback(requestParams));
        cacheCall(requestParams, call);
    }

    @Override
    public void doDownloadFile(RequestParams requestParams, ICallback iCallback) {
        iCallback.onStart();
        Request request = new Request.Builder().url(OkHttpUtils.getUrlWithParams(requestParams))
                .get().headers(OkHttpUtils.getHeaders(requestParams)).tag(requestParams.getTag()).build();
        Call call = sOkHttpClient.newCall(request);
        call.enqueue(new OkHttpCallback(requestParams));
        cacheCall(requestParams, call);
    }

    @Override
    public void cancel(Object tag) {
        ArrayList<Call> values = calls.get(tag);
        if (values != null && values.size() > 0) {
            for (int i = 0; i < values.size(); i++) {
                Call call = values.get(i);
                if (!call.isCanceled()) {
                    call.cancel();
                }
                values.remove(call);
            }
        }
    }

    @Override
    public void cancelAll() {
        for (Map.Entry<Object, ArrayList<Call>> next : calls.entrySet()) {
            cancel(next.getKey());
        }
    }
}
