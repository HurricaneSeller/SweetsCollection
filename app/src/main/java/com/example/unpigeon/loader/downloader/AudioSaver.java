package com.example.unpigeon.loader.downloader;

import android.os.Environment;

import com.example.unpigeon.loader.task.Task;
import com.example.unpigeon.loader.task.ThreadPoolManager;
import com.example.unpigeon.utils.PcmToWavUtil;

import java.io.File;

import androidx.annotation.NonNull;

public class AudioSaver {
    private static AudioSaver INSTANCE;
    private static final String FORMAT_PCM = ".pcm";
    private static final String FORMAT_WAV = ".wav";
    private static final String FORMAT = "/";
    public static final String DEFAULT_PATH = "/invalid";
    private String path = "/invalid";

    private AudioSaver(@NonNull String path) {
        this.path = path;
    }

    public static AudioSaver getInstance(String path) {
        if (INSTANCE == null) {
            synchronized (AudioSaver.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AudioSaver(path);
                }
            }
        }
        return INSTANCE;
    }

    public void save(Task task) {
        String title = task.getTitle();
        String pathName = Environment.getExternalStorageDirectory().getAbsolutePath();
        PcmToWavUtil pcmToWavUtil = new PcmToWavUtil();
        pcmToWavUtil.pcmToWav(pathName + FORMAT + title + FORMAT_PCM, pathName + FORMAT + title + FORMAT_WAV);
        File file = new File(pathName + FORMAT + title + FORMAT_PCM);
        ThreadPoolManager.getmNormalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                // TODO: 3/20/19
            }
        });
    }

}
