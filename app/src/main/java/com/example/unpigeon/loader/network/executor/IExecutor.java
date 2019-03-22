package com.example.unpigeon.loader.network.executor;

import com.example.unpigeon.loader.network.ICallback;
import com.example.unpigeon.loader.network.RequestParams;

public interface IExecutor {
    //perform get
    void doGet(RequestParams requestParams, ICallback iCallback);

    //perform post(key-value)
    void doPost(RequestParams requestParams, ICallback iCallback);

    //perform post(json)
    void doJsonPost(RequestParams requestParams, ICallback iCallback);

    //upload file
    void doUploadFile(RequestParams requestParams, ICallback iCallback);

    //download file
    void doDownloadFile(RequestParams requestParams, ICallback iCallback);

    //cancel
    void cancel(Object tag);

    //cancel all
    void cancelAll();
}
