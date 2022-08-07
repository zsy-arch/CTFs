package com.autonavi.ae.guide.observer;

/* loaded from: classes.dex */
public interface GSoundPlayObserver {
    boolean isNaviPlaying();

    void onPlayRing(int i);

    void onPlayTTS(String str, int i);
}
