package com.example.unpigeon.loader.network.help;

import com.example.unpigeon.loader.network.RequestParams;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;


/**
 * Created by kimi on 2017/5/5 0005.
 * Email: 24750@163.com
 */

public final class TempleteBean {

    public Call call;
    public Response response;
    public IOException e;
    public RequestParams requestParams;
    public float progress;
}
