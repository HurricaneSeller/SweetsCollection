package com.example.unpigeon.record;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioRecord;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.czt.mp3recorder.MP3Recorder;
import com.example.unpigeon.repository.local.RecordPieceEntity;
import com.example.unpigeon.task.Task;
import com.shuyu.waveview.AudioPlayer;
import com.shuyu.waveview.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.UUID;

class RecordPresenter implements RecordContract.Presenter {
    private static final String TAG = "moanbigking";
    private final RecordContract.View mView;
    private final RecordPieceEntity mRecordPieceEntity;
    //    private AudioRecord mAudioRecord;
    private AudioPlayer mAudioPlayer;
    private MP3Recorder mMP3Recorder;
    private boolean mIsRecord = false;
    private boolean mIsPlay = false;
    private int duration;
    private int curPosition;
    private String filePath;


    RecordPresenter(RecordContract.View view, RecordPieceEntity recordPieceEntity) {
        mView = view;
        mRecordPieceEntity = recordPieceEntity;
//        mAudioRecorder = AudioRecorder.getInstance();
    }

    @Override
    public void setData() {
        mView.setContent(mRecordPieceEntity.getContent());
        mView.setTitle(mRecordPieceEntity.getTitle());
    }

    @Override
    public void initAudioRecorder() {

    }

    @SuppressLint("HandlerLeak")
    @Override
    public void startRecord(final Context context) throws IOException {
        filePath = FileUtils.getAppPath();
        Log.d(TAG, "startRecord: " + filePath);
        File file = new File(filePath);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                mView.toast("创建文件失败");
                return;
            }
        }

        int offset = dip2px(context, 1);
        filePath = FileUtils.getAppPath() + UUID.randomUUID().toString() + ".mp3";
        mMP3Recorder = new MP3Recorder(new File(filePath));
        mAudioPlayer = new AudioPlayer(context, new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case AudioPlayer.HANDLER_CUR_TIME://更新的时间
                        curPosition = (int) msg.obj;
                        Log.d(TAG, "handleMessage: 1" + toTime(curPosition) + " / " + toTime(duration));
//                    playText.setText(toTime(curPosition) + " / " + toTime(duration));
                        break;
                    case AudioPlayer.HANDLER_COMPLETE://播放结束
                        Log.d(TAG, "handleMessage: 2");
//                    playText.setText(" ");
                        mIsPlay = false;
                        break;
                    case AudioPlayer.HANDLER_PREPARED://播放开始
                        duration = (int) msg.obj;
                        Log.d(TAG, "handleMessage: 3" + toTime(curPosition) + " / " + toTime(duration));
//                    playText.setText(toTime(curPosition) + " / " + toTime(duration));
                        break;
                    case AudioPlayer.HANDLER_ERROR://播放错误
                        Log.d(TAG, "handleMessage: 4");
//                    resolveResetPlay();
                        break;

                }
            }
        });
        int size = getScreenWidth(context) / offset;//控件默认的间隔是1
        mMP3Recorder.setErrorHandler(new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == MP3Recorder.ERROR_TYPE) {
                    Toast.makeText(context, "没有麦克风权限", Toast.LENGTH_SHORT).show();
                    resolveError();
                }
            }
        });

        mMP3Recorder.start();
        mView.startView();
        mMP3Recorder.setDataList(mView.getDataList(), size);

        resolveRecordUI();
        mIsRecord = true;
    }

    @Override
    public void stopRecord() {
//        mAudioRecorder.stopRecord();
//        mAudioRecorder.release();
        resolveStopUI();
        if (mMP3Recorder != null && mMP3Recorder.isRecording()) {
            mMP3Recorder.setPause(false);
            mMP3Recorder.stop();
            mView.stopView();
        }
        mIsRecord = false;

        mView.popAlertDialog();
    }

    @Override
    public void createUploadTask() {
        Task task = new Task(mRecordPieceEntity);
        // TODO: 3/24/19 add the left part
    }


    /**
     * 录音异常
     */
    private void resolveError() {
        resolveNormalUI();
        FileUtils.deleteFile(filePath);
        filePath = "";
        if (mMP3Recorder != null && mMP3Recorder.isRecording()) {
            mMP3Recorder.stop();
            mView.stopView();
        }
    }

    private int getScreenWidth(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();// 创建了一张白纸
        windowManager.getDefaultDisplay().getMetrics(outMetrics);// 给白纸设置宽高
        return outMetrics.widthPixels;
    }

    /**
     * 获取屏幕的高度px
     *
     * @param context 上下文
     * @return 屏幕高px
     */
    private int getScreenHeight(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();// 创建了一张白纸
        windowManager.getDefaultDisplay().getMetrics(outMetrics);// 给白纸设置宽高
        return outMetrics.heightPixels;
    }

    private void resolveRecordUI() {
        // TODO: 3/27/19  fix this
//        record.setEnabled(false);
//        recordPause.setEnabled(true);
//        stop.setEnabled(true);
//        play.setEnabled(false);
//        wavePlay.setEnabled(false);
//        reset.setEnabled(false);
    }

    private void resolveNormalUI() {
        // TODO: 3/27/19  fix this
        //        record.setEnabled(true);
//        recordPause.setEnabled(false);
//        stop.setEnabled(false);
//        play.setEnabled(false);
//        wavePlay.setEnabled(false);
//        reset.setEnabled(false);
    }

    private void resolveStopUI() {
//        record.setEnabled(true);
//        stop.setEnabled(false);
//        recordPause.setEnabled(false);
//        play.setEnabled(true);
//        wavePlay.setEnabled(true);
//        reset.setEnabled(true);
    }

    /**
     * dip转为PX
     */
    public static int dip2px(Context context, float dipValue) {
        float fontScale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * fontScale + 0.5f);
    }

    private String toTime(long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");
        String dateString = formatter.format(time);
        return dateString;
    }
}
