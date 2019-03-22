package com.example.unpigeon.loader.network.executor.factory;

import android.util.Log;

import com.example.unpigeon.loader.network.NetworkUtils;
import com.example.unpigeon.loader.network.config.OkHttpConfig;
import com.example.unpigeon.loader.network.executor.IExecutor;
import com.example.unpigeon.loader.network.executor.OkHttpExecutor;

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
