package com.example.unpigeon.listen.play;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class PlayService extends Service {
    private MediaPlayer mMediaPlayer;

    public PlayService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        initMediaPlayer();
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void initMediaPlayer() {
        mMediaPlayer = new MediaPlayer();
        URL url = null;
        try {
            url = new URL("wait to be finished");
            String tmp = String.valueOf(url);
            mMediaPlayer.setDataSource(tmp);
            mMediaPlayer.setLooping(false);
//            mMediaPlayer.setOnPreparedListener();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    class MyBinder extends Binder implements IPlayControl {

        @Override
        public void start() {
            mMediaPlayer.start();
        }

        @Override
        public void pause() {
            mMediaPlayer.pause();
        }

        @Override
        public void stop() {
            //pause
            mMediaPlayer.release();
        }
    }
}