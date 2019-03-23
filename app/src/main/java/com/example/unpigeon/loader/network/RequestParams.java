package com.example.unpigeon.loader.network;

import com.example.unpigeon.loader.network.call.ICallback;

import java.io.File;
import java.nio.charset.Charset;
import java.util.HashMap;

import androidx.annotation.NonNull;

public final class RequestParams {
    private String mUrl;
    private HashMap<String, String> mHeader = new HashMap<>();
    private HashMap<String, String> mRequestParams = new HashMap<>();
    private HashMap<String, File> mFiles = new HashMap<>();
    private Charset mCharset = Charset.forName("UTF-8");
    private Object tag;
    private Method mMethod = Method.GET;
    private String mBody;
    private String mDownloadFilePath;
    private ICallback mICallback;

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public HashMap<String, String> getHeader() {
        return mHeader;
    }

    public void setHeader(HashMap<String, String> header) {
        mHeader = header;
    }

    public HashMap<String, String> getRequestParams() {
        return mRequestParams;
    }

    public void setRequestParams(HashMap<String, String> requestParams) {
        mRequestParams = requestParams;
    }

    public HashMap<String, File> getFiles() {
        return mFiles;
    }

    public void setFiles(HashMap<String, File> files) {
        mFiles = files;
    }

    public Charset getCharset() {
        return mCharset;
    }

    public void setCharset(Charset charset) {
        mCharset = charset;
    }

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

    public String getBody() {
        return mBody;
    }

    public void setBody(String body) {
        mBody = body;
    }

    public String getDownloadFilePath() {
        return mDownloadFilePath;
    }

    public void setDownloadFilePath(String downloadFilePath) {
        mDownloadFilePath = downloadFilePath;
    }

    public ICallback getICallback() {
        return mICallback;
    }

    public Method getMethod() {
        return mMethod;
    }

    public void setMethod(Method method) {
        mMethod = method;
    }

    public void setICallback(ICallback ICallback) {
        mICallback = ICallback;
    }

    public static class Builder {
        private String url;
        private HashMap<String, String> header = new HashMap<>();
        private HashMap<String, String> requestParams = new HashMap<>();
        private HashMap<String, File> files = new HashMap<>();
        private Charset charset = Charset.forName("UTF-8");
        private Object tag;
        private String body;
        private String downloadFilePath;
        private Method mMethod = Method.GET;

        public Builder url(@NonNull String url) {
            this.url = url;
            return this;
        }

        public Builder header(String key, String value) {
            header.put(key, value);
            return this;
        }

        public Builder requestParams(String key, String value) {
            requestParams.put(key, value);
            return this;
        }

        public Builder files(String key, File file) {
            files.put(key, file);
            return this;
        }

        public Builder charset(@NonNull Charset charset) {
            this.charset = charset;
            return this;
        }

        public Builder tag(@NonNull Object tag) {
            this.tag = tag;
            return this;
        }

        public Builder method(@NonNull Method method) {
            this.mMethod = method;
            return this;
        }

        public Builder body(@NonNull String body) {
            this.body = body;
            return this;
        }

        public Builder downloadFilePath(@NonNull String downloadFilePath) {
            this.downloadFilePath = downloadFilePath;
            return this;
        }

        public RequestParams build() {
            RequestParams requestParams = new RequestParams();
            requestParams.mUrl = url;
            requestParams.mBody = body;
            requestParams.mCharset = charset;
            requestParams.mDownloadFilePath = downloadFilePath;
            requestParams.mFiles = files;
            requestParams.tag = tag;
            requestParams.mMethod = mMethod;
            requestParams.mHeader = header;
            return requestParams;
        }
    }
}
