package com.example.unpigeon.loader.task;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool {
    private ThreadPoolExecutor mThreadPoolExecutor;
    private int mCorePoolSize;
    private int mMaxPoolSize;

    public ThreadPool(int corePoolSize, int maxPoolSize) {
        mCorePoolSize = corePoolSize;
        mMaxPoolSize = maxPoolSize;
    }

    /**
     * init thread pool
     * double locks
     */
    private void initThreadPoolExecutor() {
        if (mThreadPoolExecutor == null || mThreadPoolExecutor.isShutdown() || mThreadPoolExecutor.isTerminated()) {
            synchronized (ThreadPool.class) {
                if (mThreadPoolExecutor == null || mThreadPoolExecutor.isShutdown() || mThreadPoolExecutor.isTerminated()) {
                    long keepAliveTime = 3000;
                    TimeUnit timeUnit = TimeUnit.MILLISECONDS;
                    BlockingDeque<Runnable> workQueue = new LinkedBlockingDeque<>();
                    ThreadFactory threadFactory = Executors.defaultThreadFactory();
                    RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();
                    mThreadPoolExecutor = new ThreadPoolExecutor(mCorePoolSize, mMaxPoolSize, keepAliveTime, timeUnit
                            , workQueue,
                            threadFactory, handler);
                }
            }
        }
    }

    public void execute(Runnable task) {
        initThreadPoolExecutor();
        mThreadPoolExecutor.execute(task);
    }

    public Future<?> submit(Runnable task) {
        initThreadPoolExecutor();
        return mThreadPoolExecutor.submit(task);
    }

    public void remove(Runnable task) {
        initThreadPoolExecutor();
        mThreadPoolExecutor.remove(task);
    }
}
