package com.example.unpigeon.loader.network.call;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.example.unpigeon.loader.network.Method;
import com.example.unpigeon.loader.network.RequestParams;
import com.example.unpigeon.loader.network.handler.MainHandler;
import com.example.unpigeon.loader.network.help.TempleteBean;
import com.example.unpigeon.loader.network.utils.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import androidx.annotation.NonNull;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class OkHttpCallback implements Callback {
    public static final Handler HANDLER = new MainHandler(Looper.getMainLooper());
    private RequestParams mRequestParams;

    public OkHttpCallback(RequestParams requestParams) {
        mRequestParams = requestParams;
    }

    @Override
    public void onFailure(@NonNull Call call, @NonNull IOException e) {
        e.printStackTrace();
        TempleteBean templeteBean = new TempleteBean();
        templeteBean.mCall = call;
        templeteBean.mIOException = e;
        templeteBean.mRequestParams = mRequestParams;
        Message.obtain(HANDLER, MainHandler.FAILED, templeteBean).sendToTarget();
    }

    @Override
    public void onResponse(@NonNull Call call, @NonNull Response response) {
        TempleteBean templeteBean = new TempleteBean();
        templeteBean.mCall = call;
        templeteBean.mResponse = response;
        templeteBean.mRequestParams = mRequestParams;

        if (mRequestParams.getMethod().equals(Method.DOWNLOAD)) {
            downloadFile(templeteBean, response);
        }
        Message.obtain(HANDLER, MainHandler.SUCCESS, mRequestParams);
    }

    private void downloadFile(TempleteBean templeteBean, @NonNull Response response) {
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        try {
            fileOutputStream = new FileOutputStream(new File(mRequestParams.getDownloadFilePath()));
            if (response.body() == null) {
                throw new IllegalArgumentException("response.body() occurred null-pointer error.");
            }
            long totalSize = response.body().contentLength();
            long tmp = 0;
            inputStream = response.body().byteStream();
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(bytes)) != -1) {
                tmp += len;
                templeteBean.mProgress = tmp * 1.0f / totalSize * 100;
                Message.obtain(HANDLER, MainHandler.PROGRESS, templeteBean).sendToTarget();
                fileOutputStream.write(bytes, 0, len);
                fileOutputStream.flush();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            FileUtils.closeIO(fileOutputStream, inputStream);
        }
    }

}
