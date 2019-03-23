package com.example.unpigeon.loader.network.utils;

import com.example.unpigeon.loader.network.RequestParams;

import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;

public class OkHttpUtils {
    public static String getUrlWithParams(RequestParams requestParams) {
        String url = requestParams.getUrl();
        StringBuilder stringBuilder = new StringBuilder();
        HashMap<String, String> params = requestParams.getRequestParams();
        if (params == null) return url;
        for (Map.Entry<String, String> next : params.entrySet()) {
            stringBuilder.append(next.getKey()).append("=").append(next.getValue()).append("&");
        }
        return url.endsWith("?") ? url + stringBuilder.toString() : url + "?" + stringBuilder.toString();
    }

    public static FormBody getPostBody(RequestParams requestParams) {
        FormBody.Builder builder = new FormBody.Builder();
        HashMap<String, String> params = requestParams.getRequestParams();
        if (params == null) return builder.build();
        for (Map.Entry<String, String> next : params.entrySet()) {
            builder.addEncoded(next.getKey(), next.getValue());
        }
        return builder.build();
    }

    public static Headers getHeaders(RequestParams requestParams) {
        Headers.Builder builder = new Headers.Builder();
        HashMap<String, String> params = requestParams.getRequestParams();
        if (params == null) return builder.build();
        for (Map.Entry<String, String> next : params.entrySet()) {
            builder.add(next.getKey()).add(next.getValue());
        }
        return builder.build();
    }
}
