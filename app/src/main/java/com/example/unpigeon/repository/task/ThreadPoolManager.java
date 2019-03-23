package com.example.unpigeon.repository.task;

public class ThreadPoolManager {
    private static ThreadPool mNormalThreadPool;
    private static ThreadPool mUploaderThreadPool;

    public static ThreadPool getmNormalThreadPool() {
        if (mNormalThreadPool == null) {
            synchronized (ThreadPoolManager.class) {
                if (mNormalThreadPool == null) {
                    mNormalThreadPool = new ThreadPool(5, 5);
                }
            }
        }
        return mNormalThreadPool;
    }

    public static ThreadPool getmUploaderThreadPool() {
        if (mUploaderThreadPool == null) {
            synchronized (ThreadPoolManager.class) {
                if (mUploaderThreadPool == null) {
                    mUploaderThreadPool = new ThreadPool(3, 3);
                }
            }
        }
        return mUploaderThreadPool;
    }
}
