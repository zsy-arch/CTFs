package com.superrtc.sdk;

/* loaded from: classes2.dex */
public class Bandwidth {
    long lastValue = 0;
    long lastTime = 0;
    long averageValue = 0;

    public void update(String value) {
        update(Long.valueOf(value).longValue());
    }

    public void update(long value) {
        long now = System.currentTimeMillis();
        long elapsed = now - this.lastTime;
        if (elapsed < 0 || elapsed > 5000) {
            this.lastValue = value;
            this.lastTime = now;
        } else if (elapsed >= 2000) {
            this.averageValue = (1000 * (value - this.lastValue)) / elapsed;
        }
    }

    public long getRate() {
        return this.averageValue;
    }

    public long getBitrate() {
        return this.averageValue * 8;
    }

    public String getBitrateString() {
        return new StringBuilder().append(getBitrate() / 1000).toString();
    }
}
