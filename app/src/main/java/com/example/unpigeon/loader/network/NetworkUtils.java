package com.example.unpigeon.loader.network;

import com.example.unpigeon.loader.network.call.ICallback;
import com.example.unpigeon.loader.network.executor.IExecutor;
import com.example.unpigeon.loader.network.executor.factory.ExecutorFactory;

import androidx.annotation.NonNull;

public final class NetworkUtils {
    public static IExecutor sIExecutor;
    private static NetworkUtils INSTANCE;

    /**
     *init network utils
     * only come the state of okhttpfactory, arming to use another factory just extends it.
     * @param executorFactory
     */
    public static void init(ExecutorFactory executorFactory) {
        if (INSTANCE == null) {
            synchronized (NetworkUtils.class) {
                INSTANCE = new NetworkUtils(executorFactory);
            }
        }
    }

    private NetworkUtils(ExecutorFactory executorFactory) {
        sIExecutor = executorFactory.create();
    }

    public static NetworkUtils getInstance() {
        if (INSTANCE == null || sIExecutor == null) {
            throw new RuntimeException("networkutils hasn't init yet.");
        }
        return INSTANCE;
    }

    /**
     * start requesting network
     * switch different method due to different params
     * @param requestParams
     * @param iCallback
     */
    public void doStart(@NonNull RequestParams requestParams,@NonNull ICallback iCallback) {
        requestParams.setICallback(iCallback);
        switch (requestParams.getMethod()) {
            case GET:
                sIExecutor.doGet(requestParams, iCallback);
                break;
            case POST:
                sIExecutor.doPost(requestParams, iCallback);
                break;
            case POST_JSON:
                sIExecutor.doJsonPost(requestParams, iCallback);
                break;
            case UPLOAD:
                sIExecutor.doUploadFile(requestParams, iCallback);
                break;
            case DOWNLOAD:
                sIExecutor.doDownloadFile(requestParams, iCallback);
                break;
        }
    }

    /**
     * cancel some request, may be used when destroy an activity or fragment
     * @param tag
     */
    public void cancelRequest(Object tag) {
        sIExecutor.cancel(tag);
    }

    /**
     * cancel call request
     */
    public void cancelAllRequest() {
        sIExecutor.cancelAll();
    }
}
