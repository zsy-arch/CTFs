package com.autonavi.ae.gmap.maploader;

import com.autonavi.ae.gmap.GLMapEngine;

/* loaded from: classes.dex */
public class MessageDataThread extends Thread {
    private int mDataType;
    private int mDelMode;
    private int mEngineID;
    private GLMapEngine mGLMapEngine;
    private int mRequestMode;
    private int mType;

    public MessageDataThread(int i, GLMapEngine gLMapEngine, int i2, int i3, int i4, int i5) {
        this.mEngineID = i;
        this.mGLMapEngine = gLMapEngine;
        this.mType = i2;
        this.mDelMode = i3;
        this.mRequestMode = i4;
        this.mDataType = i5;
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x00d6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00e2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:79:? A[RETURN, SYNTHETIC] */
    @Override // java.lang.Thread, java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void run() {
        /*
            Method dump skipped, instructions count: 250
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.autonavi.ae.gmap.maploader.MessageDataThread.run():void");
    }
}
