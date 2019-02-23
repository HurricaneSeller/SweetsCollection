package com.example.unpigeon.main;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.unpigeon.R;

// TODO: 2/23/19 the main activity, click button to change activity
/*
if the network so too heavy may adding a "welcome page" to hide the true loading time, but now it't the
first activity.
(i use english because my android-studio does not support chinese and i cannot fix it...
not even need fragment now, but i use fragment in case of need.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
