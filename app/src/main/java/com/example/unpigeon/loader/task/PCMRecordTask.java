package com.example.unpigeon.loader.task;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;

import com.example.unpigeon.record.OnRecording;

public class PCMRecordTask {
    //默认配置AudioRecord
    private static final int DEFAULT_SOURCE = MediaRecorder.AudioSource.MIC;////麦克风采集
    private static final int DEFAULT_SAMPLE_RATE = 44100;//采样频率
    private static final int DEFAULT_CHANNEL_CONFIG = AudioFormat.CHANNEL_IN_MONO;//单声道
    private static final int DEFAULT_AUDIO_FORMAT = AudioFormat.ENCODING_PCM_16BIT;//输出格式：16位pcm

    private AudioRecord mAudioRecord;//录音机
    private int mMinBufferSize = 2048;//最小缓存数组大小

    private Thread mRecordThread;//录音线程
    private boolean mIsStarted = false;//是否已开启
    private volatile boolean mIsRecording = false;//是否正在录制

    private OnRecording mOnRecording;//录制时的监听
    private long mStartTime;//开始录制时间
    private int mWorkingTime;


    /**
     * 开始录制
     *
     * @return
     */
    public boolean recode() {
        return recode(DEFAULT_SOURCE, DEFAULT_SAMPLE_RATE, DEFAULT_CHANNEL_CONFIG,
                DEFAULT_AUDIO_FORMAT);
    }

    /**
     * 开始录制
     *
     * @return
     */
    public boolean recode(int source, int sampleRate, int channel, int format) {
        if (mIsStarted) {//如果已经开始,返回false
            return false;
        }
        mMinBufferSize = AudioRecord.getMinBufferSize(sampleRate, channel, format);
        mAudioRecord = new AudioRecord(source, sampleRate, channel, format, mMinBufferSize);
        mAudioRecord.startRecording();

        mIsRecording = true;//正在录制
        mRecordThread = new Thread(new RecodeRunnable());
        mRecordThread.start();
        mIsStarted = true;//已开启
        mStartTime = System.currentTimeMillis();//开始时间
        return true;
    }

    /**
     * 停止录制
     */
    public void stopRecode() {
        if (!mIsStarted) {
            return;
        }

        mIsRecording = false;//不在录音
        try {
            mRecordThread.interrupt();
            mRecordThread.join(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (mAudioRecord.getRecordingState() == AudioRecord.RECORDSTATE_RECORDING) {
            mAudioRecord.stop();//状态为录制中，停止
        }

        mAudioRecord.release();//释放资源
        mIsStarted = false;//未开启

        //录制花费时间
        mWorkingTime = (int) ((System.currentTimeMillis() - mStartTime) / 1000);
    }

    public int getWorkingTime() {
        return mWorkingTime;
    }


    public void setOnRecording(OnRecording onRecording) {
        mOnRecording = onRecording;
    }

    public boolean isStarted() {
        return mIsStarted;
    }

    private class RecodeRunnable implements Runnable {
        @Override
        public void run() {
            while (mIsRecording) {//如果正在录制
                byte[] buf = new byte[mMinBufferSize];//缓存字节数组
                int read = mAudioRecord.read(buf, 0, mMinBufferSize);
                if (mOnRecording != null) {
                    if (read > 0) {//有数据，则回调onRecording
                        mOnRecording.onRecording(buf, read);
                    } else {
                        mOnRecording.onError(new RuntimeException("Error When Read"));
                    }
                }
            }
        }
    }
}
