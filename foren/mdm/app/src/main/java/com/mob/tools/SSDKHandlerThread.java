package com.mob.tools;

import android.os.Handler;
import android.os.Message;

/* loaded from: classes2.dex */
public abstract class SSDKHandlerThread implements Handler.Callback {
    private static final int MSG_START = -1;
    private static final int MSG_STOP = -2;
    protected final Handler handler;
    private String name;

    public SSDKHandlerThread() {
        MobHandlerThread thread = new MobHandlerThread();
        thread.start();
        this.handler = new Handler(thread.getLooper(), this);
    }

    public SSDKHandlerThread(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message msg) {
        switch (msg.what) {
            case -2:
                onStop(msg);
                return false;
            case -1:
                onStart(msg);
                return false;
            default:
                onMessage(msg);
                return false;
        }
    }

    protected abstract void onMessage(Message message);

    protected void onStart(Message msg) {
    }

    protected void onStop(Message msg) {
    }

    public void startThread() {
        startThread(0, 0, null);
    }

    public void startThread(int arg1, int arg2, Object obj) {
        Message msg = new Message();
        msg.what = -1;
        msg.arg1 = arg1;
        msg.arg2 = arg2;
        msg.obj = obj;
        this.handler.sendMessage(msg);
    }

    public void stopThread() {
        stopThread(0, 0, null);
    }

    public void stopThread(int arg1, int arg2, Object obj) {
        Message msg = new Message();
        msg.what = -2;
        msg.arg1 = arg1;
        msg.arg2 = arg2;
        msg.obj = obj;
        this.handler.sendMessage(msg);
    }
}
