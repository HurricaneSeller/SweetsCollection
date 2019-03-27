package com.example.unpigeon.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.unpigeon.R;
import com.example.unpigeon.listen.ListenActivity;
import com.example.unpigeon.repository.local.RecordPieceEntity;
import com.example.unpigeon.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, MainContract.View {
    private static final String TAG = "moanbigking";
    private RecyclerView mTodayRecyclerView;
    private RecyclerView mTotalRecyclerView;
    private Button mShowAllButton;
    private List<RecordPieceEntity> mRecordPieceEntities;
    private MainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        testing();
    }

    private void init() {
        mTodayRecyclerView = findViewById(R.id.main_today_recycler_view);
        mTotalRecyclerView = findViewById(R.id.main_all_recycler_view);
        mShowAllButton = findViewById(R.id.main_all_work_button);
        mShowAllButton.setOnClickListener(this);
        mMainPresenter = new MainPresenter(this, mHandler);
        mMainPresenter.load(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_all_work_button:
                break;
            case R.id.main_record1:
                Intent intent = new Intent(this, ListenActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void testing() {
        mRecordPieceEntities = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("大家新年快乐");
            stringBuilder.append(i);
            RecordPieceEntity recordPieceEntity = new RecordPieceEntity();
            recordPieceEntity.setContent(stringBuilder.toString());
            mRecordPieceEntities.add(recordPieceEntity);
        }
        mTodayRecyclerView.setAdapter(new ShowAdapter(mRecordPieceEntities));
        mTodayRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constant.LOAD_FINISHED:
                    //loaded finished and user can interact with ui
                    break;
                default:
            }
        }
    };

}
