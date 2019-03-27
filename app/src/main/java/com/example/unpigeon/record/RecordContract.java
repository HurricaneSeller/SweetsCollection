package com.example.unpigeon.record;

import android.content.Context;

import java.io.IOException;
import java.util.ArrayList;

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
        void setView(byte[] data);

        void startView();
        void stopView();
        ArrayList<Short> getDataList();
    }
    interface Presenter{
        void setData();
        void initAudioRecorder();
        void startRecord(Context context) throws IOException;
        void stopRecord();
        void createUploadTask();
    }
}
