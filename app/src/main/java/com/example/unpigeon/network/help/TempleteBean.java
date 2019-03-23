package com.example.unpigeon.network.help;

import com.example.unpigeon.network.RequestParams;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public final class TempleteBean {
    public Call mCall;
    public Response mResponse;
    public IOException mIOException;
    public RequestParams mRequestParams;
    public float mProgress;
}
