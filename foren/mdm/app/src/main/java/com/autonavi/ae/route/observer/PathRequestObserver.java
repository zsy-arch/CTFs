package com.autonavi.ae.route.observer;

/* loaded from: classes.dex */
public interface PathRequestObserver {
    public static final int EDecodeing = 1;
    public static final int EErrorData = 2;
    public static final int EErrorHttp = 3;
    public static final int EErrorNone = 0;
    public static final int EErrorUnknow = 4;

    void onPathChanged(int i);
}
