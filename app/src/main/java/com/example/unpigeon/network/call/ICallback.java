package com.example.unpigeon.network.call;

public interface ICallback {
    void onStart();

    void onComplete();

    void onError(Exception e);

    void onSuccess(Object tag,String result);

    void onProgress(float progress);

}
