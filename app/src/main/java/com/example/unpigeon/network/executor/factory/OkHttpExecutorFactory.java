package com.example.unpigeon.network.executor.factory;

import android.util.Log;

import com.example.unpigeon.network.NetworkUtils;
import com.example.unpigeon.network.config.OkHttpConfig;
import com.example.unpigeon.network.executor.IExecutor;
import com.example.unpigeon.network.executor.OkHttpExecutor;

import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpExecutorFactory extends ExecutorFactory {
    @Override
    public IExecutor create() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d(NetworkUtils.class.getSimpleName(), message);
            }
        });
        OkHttpConfig config = new OkHttpConfig.Builder().retryOnConnectionFailure(false).connectTimeout(10000)
                .readTimeout(5000).writeTimeout(5000).build();
        return new OkHttpExecutor(config);
    }
}
