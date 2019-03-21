package com.example.unpigeon.record;

import android.content.Context;

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
    }
    interface Presenter{
        void setData();
        void startRecord(Context context);
        void stopRecord();
    }
}
