package com.example.unpigeon.record;

import android.os.Bundle;

import com.example.unpigeon.R;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

// TODO: 2/23/19 the recoding activity
/*
i add a baidu library
if u have a better library just change it
how to use :http://ai.baidu.com/docs#/ASR-Android-SDK/top
 */
public class RecordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        hideActionbar();
        switchToRecordFragment();
    }
    private void hideActionbar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }
    private void switchToRecordFragment() {
        RecordFragment recordFragment = new RecordFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack("record-stack");
        transaction.replace(R.id.activity_record_frame_layout, recordFragment);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
