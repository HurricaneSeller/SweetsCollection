package com.example.unpigeon.loader.network.handler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.example.unpigeon.loader.network.Method;
import com.example.unpigeon.loader.network.NetworkUtils;
import com.example.unpigeon.loader.network.RequestParams;
import com.example.unpigeon.loader.network.call.ICallback;
import com.example.unpigeon.loader.network.help.TempleteBean;

import java.io.IOException;

import okhttp3.Response;

public class MainHandler extends Handler {
    private static final String TAG = "moanbigking";
    public static final int SUCCESS = 200;
    public static final int FAILED = 400;
    public static final int PROGRESS = 300;

    public MainHandler(Looper looper) {
        super(looper);
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SUCCESS:
                onSuccess(msg);
                break;
            case FAILED:
                onFailed(msg);
                break;
            case PROGRESS:
                onProgress(msg);
                break;
        }
    }

    private void onProgress(Message msg) {
        TempleteBean templeteBean = (TempleteBean) msg.obj;
        templeteBean.mRequestParams.getICallback().onProgress(templeteBean.mProgress);
    }

    private void onFailed(Message msg) {
        TempleteBean templeteBean = (TempleteBean) msg.obj;
        RequestParams requestParams = templeteBean.mRequestParams;
        ICallback iCallback = requestParams.getICallback();
        iCallback.onError(templeteBean.mIOException);
        iCallback.onComplete();
    }

    private void onSuccess(Message msg) {
        TempleteBean templeteBean = (TempleteBean) msg.obj;
        RequestParams requestParams = templeteBean.mRequestParams;
        ICallback iCallback = requestParams.getICallback();
        Response response = templeteBean.mResponse;

        if (!response.isSuccessful()) {
            Log.d(TAG, NetworkUtils.class.getSimpleName() + "response is not successfully!" + response.code());
            return;
        }
        if (requestParams.getMethod().equals(Method.DOWNLOAD)) {
            iCallback.onSuccess(requestParams.getTag(), requestParams.getDownloadFilePath());
            iCallback.onComplete();
            return;
        }
        try {
            if (response.body() != null) {
                String result = response.body().string();
                Log.d(TAG, "onSuccess: " + NetworkUtils.class.getSimpleName() + result);
                iCallback.onSuccess(requestParams.getTag(), result);
            }
        } catch (IOException e) {
            e.printStackTrace();
            iCallback.onError(e);
        }
        iCallback.onComplete();
    }

}
