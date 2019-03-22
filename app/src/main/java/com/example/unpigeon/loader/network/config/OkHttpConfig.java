package com.example.unpigeon.loader.network.config;

import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

import androidx.annotation.NonNull;
import okhttp3.Cache;
import okhttp3.Interceptor;

public class OkHttpConfig {
    private boolean mRetryOnConnectionFailure;
    private long mConnectTimeout;
    private Cache mCache;
    private long mReadTimeout;
    private long mWriteTimeout;
    private List<Interceptor> mInterceptors = new ArrayList<>();
    private List<Interceptor> mNetworkInterceptors = new ArrayList<>();
    private SSLSocketFactory mSSLSocketFactory;
    private X509TrustManager mX509TrustManager;

    public boolean isRetryOnConnectionFailure() {
        return mRetryOnConnectionFailure;
    }

    public void setRetryOnConnectionFailure(boolean retryOnConnectionFailure) {
        mRetryOnConnectionFailure = retryOnConnectionFailure;
    }

    public long getConnectTimeout() {
        return mConnectTimeout;
    }

    public void setConnectTimeout(long connectTimeout) {
        mConnectTimeout = connectTimeout;
    }

    public Cache getCache() {
        return mCache;
    }

    public void setCache(Cache cache) {
        mCache = cache;
    }

    public long getReadTimeout() {
        return mReadTimeout;
    }

    public void setReadTimeout(long readTimeout) {
        mReadTimeout = readTimeout;
    }

    public long getWriteTimeout() {
        return mWriteTimeout;
    }

    public void setWriteTimeout(long writeTimeout) {
        mWriteTimeout = writeTimeout;
    }

    public List<Interceptor> getInterceptors() {
        return mInterceptors;
    }

    public void setInterceptors(List<Interceptor> interceptors) {
        this.mInterceptors = interceptors;
    }

    public List<Interceptor> getNetworkInterceptors() {
        return mNetworkInterceptors;
    }

    public void setNetworkInterceptors(List<Interceptor> networkInterceptors) {
        this.mNetworkInterceptors = networkInterceptors;
    }

    public SSLSocketFactory getSSLSocketFactory() {
        return mSSLSocketFactory;
    }

    public void setSSLSocketFactory(SSLSocketFactory SSLSocketFactory) {
        this.mSSLSocketFactory = SSLSocketFactory;
    }

    public X509TrustManager getX509TrustManager() {
        return mX509TrustManager;
    }

    public void setX509TrustManager(X509TrustManager x509TrustManager) {
        this.mX509TrustManager = x509TrustManager;
    }

    public static class Builder {
        private boolean mRetryOnConnectionFailure;
        private long mConnectTimeout;
        private Cache mCache;
        private long mReadTimeout;
        private long mWriteTimeout;
        private List<Interceptor> mInterceptors = new ArrayList<>();
        private List<Interceptor> mNetworkInterceptors = new ArrayList<>();
        private SSLSocketFactory mSSLSocketFactory;
        private X509TrustManager mX509TrustManager;

        public Builder retryOnConnectionFailure(boolean retryOnConnectionFailure) {
            this.mRetryOnConnectionFailure = retryOnConnectionFailure;
            return this;
        }

        public Builder connectTimeout(long connectTimeout) {
            this.mConnectTimeout = connectTimeout;
            return this;
        }

        public Builder cache(Cache cache) {
            this.mCache = cache;
            return this;
        }

        public Builder readTimeout(long readTimeout) {
            this.mReadTimeout = readTimeout;
            return this;
        }

        public Builder writeTimeout(long writeTimeout) {
            this.mWriteTimeout = writeTimeout;
            return this;
        }

        public Builder interceptors(List<Interceptor> interceptors) {
            this.mInterceptors = interceptors;
            return this;
        }

        public Builder networkInterceptors(List<Interceptor> networkInterceptors) {
            this.mNetworkInterceptors = networkInterceptors;
            return this;
        }

        public Builder sslSocketFactory(SSLSocketFactory sslSocketFactory) {
            this.mSSLSocketFactory = sslSocketFactory;
            return this;
        }

        public Builder x509TrustManager(X509TrustManager x509TrustManager) {
            this.mX509TrustManager = x509TrustManager;
            return this;
        }

        public OkHttpConfig build() {
            OkHttpConfig okhttpConfig = new OkHttpConfig();
            okhttpConfig.setCache(mCache);
            okhttpConfig.setConnectTimeout(mConnectTimeout);
            okhttpConfig.setInterceptors(mInterceptors);
            okhttpConfig.setNetworkInterceptors(mNetworkInterceptors);
            okhttpConfig.setReadTimeout(mReadTimeout);
            okhttpConfig.setWriteTimeout(mWriteTimeout);
            okhttpConfig.setRetryOnConnectionFailure(mRetryOnConnectionFailure);
            okhttpConfig.setSSLSocketFactory(mSSLSocketFactory);
            okhttpConfig.setX509TrustManager(mX509TrustManager);
            return okhttpConfig;
        }

    }

}
