package com.baidu.mobstat;

import android.content.Context;
import java.util.TimerTask;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class bv extends TimerTask {
    final /* synthetic */ Context a;
    final /* synthetic */ bs b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bv(bs bsVar, Context context) {
        this.b = bsVar;
        this.a = context;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public void run() {
        if (!DataCore.instance().isPartEmpty()) {
            this.b.f(this.a);
        }
    }
}
