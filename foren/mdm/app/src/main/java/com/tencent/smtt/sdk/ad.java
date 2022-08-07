package com.tencent.smtt.sdk;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class ad extends Handler {
    final /* synthetic */ ac a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ad(ac acVar, Looper looper) {
        super(looper);
        this.a = acVar;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        if (message.what == 150) {
            this.a.n();
        }
    }
}
