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
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
