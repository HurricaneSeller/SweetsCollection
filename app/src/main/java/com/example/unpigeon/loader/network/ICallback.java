package com.example.unpigeon.loader.network;

public interface ICallback {
    void onStart();

    void onComplite();

    void onError(Exception e);

    void onSuccess(Object tag,String result);

    void onProgress(float progress);

}