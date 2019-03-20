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
import com.example.unpigeon.utils.BaseAdapter;
import com.example.unpigeon.repository.RecordPieceEntity;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "neverpigeon";
    private RecyclerView mTodayRecyclerView;
    private RecyclerView mTotalRecyclerView;
    private Button mShowAllButton;
    private List<RecordPieceEntity> mRecordPieceEntities;

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
        mShowAllButton = findViewById(R.id.main_all_work_button);
        mShowAllButton.setOnClickListener(this);
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

}
