package com.example.unpigeon.record;

import android.content.Context;

import com.BaseRecorder;

import java.io.IOException;

import permissions.dispatcher.PermissionRequest;

public interface RecordContract {
    interface View{
        void setTitle(String title);
        void setContent(String content);
        void showWhyNeedsStoragePermission(final PermissionRequest request);
        void showWhyNeedsAudioPermission(final PermissionRequest request);
        void onStoragePermissionDenied();
        void onAudioPermissionDenied();
        void onClickStorageOnAskAgain();
        void onClickAudioOnAskAgain();
        void popAlertDialog();
        void toast(String text);
        void startWaveView();
        void stopWaveView();
        void setTime(String time);
        void setWaveView(BaseRecorder baseRecorder);
    }
    interface Presenter{
        void setData();
        void startRecord(Context context) throws IOException;
        void stopRecord();
        void createUploadTask(Context context);

    }
}
