package com.example.unpigeon.record;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import com.czt.mp3recorder.MP3Recorder;
import com.example.unpigeon.repository.local.RecordPieceEntity;
import com.example.unpigeon.task.Task;
import com.example.unpigeon.utils.Constant;
import com.shuyu.waveview.AudioPlayer;
import com.shuyu.waveview.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

class RecordPresenter implements RecordContract.Presenter, IControl {
    private static final String TAG = "moanbigking";
    private final RecordContract.View mView;
    private final RecordPieceEntity mRecordPieceEntity;
    private MP3Recorder mMP3Recorder;
    private String filePath;
    private Timer mTimer;
    private TimerTask mTimeTask;
    private TimerTask mViewTask;

    RecordPresenter(RecordContract.View view, RecordPieceEntity recordPieceEntity) {
        mView = view;
        mRecordPieceEntity = recordPieceEntity;
    }

    @Override
    public void setData() {
        mView.setContent(mRecordPieceEntity.getContent());
        mView.setTitle(mRecordPieceEntity.getTitle());
    }

    @SuppressLint("HandlerLeak")
    @Override
    public void startRecord(final Context context) throws IOException {
        createFile();
        mMP3Recorder = new MP3Recorder(new File(filePath));
        mMP3Recorder.setErrorHandler(mHandler);
        mMP3Recorder.start();
        mView.startWaveView();
        mView.toast("开始录音");
        startTimer();
    }

    @Override
    public void startTimer() {
        final long previousTime = System.currentTimeMillis();
        mTimer = new Timer();
        mTimeTask = new TimerTask() {
            @Override
            public void run() {
                Message message = Message.obtain();
                message.what = Constant.REFRESH_TIME;
                message.obj = toTime(System.currentTimeMillis() - previousTime);
                mHandler.sendMessage(message);
            }
        };
        mTimer.schedule(mTimeTask, 0, 1);
        mView.setWaveView(mMP3Recorder);
        mMP3Recorder.setWaveSpeed(600);
        mMP3Recorder.setPause(false);
    }

    @Override
    public void stopTimer() {
        if (mTimer != null) {
            mTimer.cancel();
        }
        if (mTimeTask != null) {
            mTimeTask.cancel();
        }
    }

    @Override
    public void createFile() {
        filePath = FileUtils.getAppPath();
        File file = new File(filePath);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                mView.toast("创建文件失败");
                return;
            }
        }
        filePath = FileUtils.getAppPath() + UUID.randomUUID().toString() + ".mp3";
    }

    @Override
    public void stopRecord() {
        if (mMP3Recorder != null && mMP3Recorder.isRecording()) {
            mMP3Recorder.setPause(false);
            mMP3Recorder.stop();
            mView.stopWaveView();
        }
        stopTimer();

        mView.popAlertDialog();
    }

    @Override
    public void createUploadTask() {
        Task task = new Task(mRecordPieceEntity);

    }




    /**
     * dip to PX
     */
    static int dip2px(Context context, float dipValue) {
        float fontScale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * fontScale + 0.5f);
    }

    private String toTime(long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");
        return formatter.format(time);
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MP3Recorder.ERROR_TYPE:
                    mView.toast("没有麦克风权限");
                    break;
                case Constant.REFRESH_TIME://更新的时间
                    String time = (String) msg.obj;
                    mView.setTime(time);
                    break;
                case Constant.REFRESH_VIEW:
//                    mView.setWaveView(mMP3Recorder);
                    break;
            }
        }
    };
}
