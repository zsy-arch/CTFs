package com.tencent.smtt.sdk;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class ar implements Runnable {
    final /* synthetic */ TbsLogReport a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ar(TbsLogReport tbsLogReport) {
        this.a = tbsLogReport;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.b();
    }
}
