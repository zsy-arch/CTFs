package com.cdlinglu.utils.x5WebView;

import android.util.Log;

/* loaded from: classes.dex */
public class TaskRecord {
    private long endingTime;
    private boolean isDisplayResultImmediately;
    private long startTime;
    private String tag;

    public void startTrace(String tag, boolean isDisplayResultImmediately) {
        this.tag = tag;
        this.startTime = System.currentTimeMillis();
        this.isDisplayResultImmediately = isDisplayResultImmediately;
    }

    public void endingTrace(String tag) {
        this.endingTime = System.currentTimeMillis();
        if (this.isDisplayResultImmediately) {
            Log.i("TraceTracker", "tag:" + tag + ": startwith:" + this.startTime + " ending with:" + this.endingTime + "useing time:" + (this.endingTime - this.startTime) + "ms");
        }
    }
}
