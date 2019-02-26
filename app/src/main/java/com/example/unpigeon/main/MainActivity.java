package com.example.unpigeon.main;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.unpigeon.R;
import com.example.unpigeon.listen.ListenActivity;
import com.example.unpigeon.BaseAdapter;
import com.example.unpigeon.VoicePieceTask;

import java.util.ArrayList;
import java.util.List;

// TODO: 2/23/19 the main activity, click button to change activity
/*
if the network so too heavy may adding a "welcome page" to hide the true loading time, but now it't the
first activity.
(i use english because my android-studio does not support chinese and i cannot fix it...
not even need fragment now, but i use fragment in case of need.
 */
/**
 * look at here first!
 * <p>
 * downloader: save audio in memory // get text online.
 * listen: play the local audio-voice, and when user lets the app run background(such as press "back" long),
 * the playing won't stops.
 * //login: to be done.
 * main:main as you see.
 * network: the uploader to upload the audio.
 * record: the record activity.
 * task: once finish recording an audio, make a task, first save it local, then push it online.
 * if user quit the program before uploading, lose the audio piece, !remind memory leak. the next time the user
 * utils: luan qi ba zao de dong xi.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "neverpigeon";
    private RecyclerView mTodayRecyclerView;
    private RecyclerView mTotalRecyclerView;
    private Button mExtendButton;
    private Button mShowAllButton;
    private ImageButton mTaskButton;
    private ImageButton mRecordButton;
    private List<VoicePieceTask> mVoicePieceTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hideActionBar();
        init();
        testing();
    }

    private void hideActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    private void init() {
        mTodayRecyclerView = findViewById(R.id.main_today_recycler_view);
        mTotalRecyclerView = findViewById(R.id.main_all_recycler_view);
        mExtendButton = findViewById(R.id.main_today_work_extend_button);
        mShowAllButton = findViewById(R.id.main_all_work_button);
        mRecordButton = findViewById(R.id.main_record1);
        mExtendButton.setOnClickListener(this);
        mShowAllButton.setOnClickListener(this);
        mRecordButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_all_work_button:
                break;
            case R.id.main_today_work_extend_button:
                break;
            case R.id.main_record1:
                Intent intent = new Intent(this, ListenActivity.class);
                startActivity(intent);
                break;
        }
    }
    private void testing() {
        mVoicePieceTasks = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("大家新年快乐");
            stringBuilder.append(i);
            VoicePieceTask voicePieceTask = new VoicePieceTask();
            voicePieceTask.setContent(stringBuilder.toString());
            mVoicePieceTasks.add(voicePieceTask);
        }
        mTodayRecyclerView.setAdapter(new BaseAdapter(mVoicePieceTasks));
        mTodayRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
