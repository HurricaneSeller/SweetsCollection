package com.example.unpigeon.record;

public interface OnRecording {
    /**
     * 录制中监听
     * @param data 数据
     * @param len 长度
     */
    void onRecording(byte[] data, int len);

    /**
     * 错误监听
     * @param e
     */
    void onError(Exception e);
}

