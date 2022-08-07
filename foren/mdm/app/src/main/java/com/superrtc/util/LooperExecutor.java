package com.superrtc.util;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import java.util.concurrent.Executor;

/* loaded from: classes2.dex */
public class LooperExecutor extends Thread implements Executor {
    private static final String TAG = "LooperExecutor";
    private long threadId;
    private final Object looperStartedEvent = new Object();
    private Handler handler = null;
    private boolean running = false;

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        Looper.prepare();
        synchronized (this.looperStartedEvent) {
            Log.d(TAG, "Looper thread started.");
            this.handler = new Handler();
            this.threadId = Thread.currentThread().getId();
            this.looperStartedEvent.notify();
        }
        Looper.loop();
    }

    public synchronized void requestStart() {
        if (!this.running) {
            this.running = true;
            this.handler = null;
            start();
            synchronized (this.looperStartedEvent) {
                while (this.handler == null) {
                    try {
                        this.looperStartedEvent.wait();
                    } catch (InterruptedException e) {
                        Log.e(TAG, "Can not start looper thread");
                        this.running = false;
                    }
                }
            }
        }
    }

    public synchronized void requestStop() {
        if (this.running) {
            this.running = false;
            this.handler.post(new Runnable() { // from class: com.superrtc.util.LooperExecutor.1
                @Override // java.lang.Runnable
                public void run() {
                    Looper.myLooper().quit();
                    Log.d(LooperExecutor.TAG, "Looper thread finished.");
                }
            });
        }
    }

    public boolean checkOnLooperThread() {
        return Thread.currentThread().getId() == this.threadId;
    }

    @Override // java.util.concurrent.Executor
    public synchronized void execute(Runnable runnable) {
        if (!this.running) {
            Log.w(TAG, "Running looper executor without calling requestStart()");
        } else {
            this.handler.post(runnable);
        }
    }
}
