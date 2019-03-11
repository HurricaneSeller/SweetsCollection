package com.example.unpigeon.listen.play;


import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.carlos.voiceline.mylibrary.VoiceLineView;
import com.example.unpigeon.R;
import com.example.unpigeon.listen.ListenContract;
import com.example.unpigeon.listen.list.RecordPiece;

/**
 * A simple {@link Fragment} subclass.
 * <p>
 * background playing:make a remoteview
 * simplified music player
 */
public class PlayFragment extends Fragment implements ListenContract.PlayView, View.OnClickListener {
    private RecordPiece mRecordPiece;
    private String text;
    private PlayPresenter mPlayPresenter;
    private TextView mContentView;
    private TextView mTextView;
    private ImageButton mControlButton;
    private VoiceLineView mVoiceLineView;
    private boolean isPlaying = false;
    private PlayService.MyBinder mMyBinder;
    private MyConn mMyConn;
    private boolean isFirstPressed = true;


    public PlayFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_play, container, false);
        mRecordPiece = getRecordPiece();
        init(root);
        return root;
    }

    private RecordPiece getRecordPiece() {
        return null;
    }

    private void init(View view) {
        if (mRecordPiece == null) {
            mRecordPiece = new RecordPiece();
            mRecordPiece.setName("sample");
            mRecordPiece.setSummary("sample sample sample");
        }
        text = mRecordPiece.getSummary();
        mContentView = view.findViewById(R.id.fra_play_text);
        mTextView = view.findViewById(R.id.fra_play_time);
        mControlButton = view.findViewById(R.id.fra_play_control);
        mVoiceLineView = new VoiceLineView(getContext());
    }

    @Override
    public void setPresenter(PlayPresenter playPresenter) {
        mPlayPresenter = playPresenter;
    }

    @Override
    public void setText() {
        if (text == null || text.equals("")) {
            text = "sorry, there may be some network error.";
        }
        mContentView.setText(text);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fra_play_control:
                if (isFirstPressed) {
                    Intent intent = new Intent(getActivity(), PlayService.class);
                    intent.setAction("play-function");
                    intent.putExtra("binder-initialized", new Bundle());
                    mMyConn = new MyConn();
                    IntentFilter intentFilter = new IntentFilter("play-function");
                    getActivity().bindService(intent, mMyConn, Context.BIND_AUTO_CREATE);
                    getActivity().registerReceiver(mBroadcastReceiver, intentFilter);
                } else {
                    if (isPlaying) {
                        mMyBinder.pause();
                    } else {
                        mMyBinder.start();
                    }
                }
                    break;
        }
    }

    class MyConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMyBinder = (PlayService.MyBinder) service;
            Intent bindInited = new Intent();

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    }

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("play-function".equals(action)) {
                String content = intent.getStringExtra("binder-initialized");
            }
        }
    };
}
