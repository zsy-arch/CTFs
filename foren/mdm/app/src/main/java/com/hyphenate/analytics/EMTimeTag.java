package com.hyphenate.analytics;

/* loaded from: classes2.dex */
public class EMTimeTag {
    private long oldTime = 0;
    private long timeSpent = 0;

    public void start() {
        this.oldTime = System.currentTimeMillis();
    }

    public long stop() {
        this.timeSpent = System.currentTimeMillis() - this.oldTime;
        return this.timeSpent;
    }

    public long timeSpent() {
        return this.timeSpent;
    }

    public String timeStr() {
        return EMCollector.timeToString(this.timeSpent);
    }
}
