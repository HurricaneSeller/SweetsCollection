package com.example.unpigeon.loader.network.help;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.example.unpigeon.loader.network.RequestParams;
import com.example.unpigeon.loader.network.handler.MainHandler;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Sink;

public class ProgressRequestBody extends RequestBody {
    private final RequestBody mRequestBody;
    private final RequestParams mRequestParams;
    private BufferedSink mBufferedSink;
    private TempleteBean mTempleteBean = new TempleteBean();
    public static final Handler HANDLER = new MainHandler(Looper.getMainLooper());

    public ProgressRequestBody(RequestBody requestBody, RequestParams requestParams) {
        mRequestBody = requestBody;
        mRequestParams = requestParams;
        mTempleteBean.mRequestParams = this.mRequestParams;
    }

    @Override
    public MediaType contentType() {
        return mRequestBody.contentType();
    }

    @Override
    public void writeTo(@NotNull BufferedSink bufferedSink) throws IOException {
        mRequestBody.writeTo(bufferedSink);
        bufferedSink.flush();
    }

    private Sink sink(Sink sink) {
        return new ForwardingSink(sink) {
            private long current;
            private long total;

            @Override
            public void write(Buffer source, long byteCount) throws IOException {
                super.write(source, byteCount);
                if (total == 0) {
                    total = contentLength();
                }
                current += byteCount;
                mTempleteBean.mProgress = current * 1.0f / total * 100;
                Message.obtain(HANDLER, MainHandler.PROGRESS, mTempleteBean).sendToTarget();
            }
        };
    }
}
