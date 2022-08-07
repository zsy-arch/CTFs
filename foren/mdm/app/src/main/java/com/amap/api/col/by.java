package com.amap.api.col;

import javax.microedition.khronos.opengles.GL10;

/* compiled from: GLOverlay.java */
/* loaded from: classes.dex */
public abstract class by {
    private k a;

    public abstract int getZIndex();

    public abstract void onDrawFrame(GL10 gl10);

    public void destroy() {
        if (this.a != null) {
        }
    }
}
