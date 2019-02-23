package com.example.unpigeon.record;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.baidu.speech.EventManager;
import com.example.unpigeon.R;

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
    }
}
