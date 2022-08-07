package com.tencent.smtt.sdk;

import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.n;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class ag implements n.a {
    final /* synthetic */ boolean a;
    final /* synthetic */ TbsDownloadConfig b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ag(boolean z, TbsDownloadConfig tbsDownloadConfig) {
        this.a = z;
        this.b = tbsDownloadConfig;
    }

    @Override // com.tencent.smtt.utils.n.a
    public void a(int i) {
        TbsLog.i(TbsDownloader.LOGTAG, "[TbsDownloader.sendRequest] httpResponseCode=" + i);
        if (i < 300) {
            return;
        }
        if (this.a) {
            this.b.setDownloadInterruptCode(-107);
        } else {
            this.b.setDownloadInterruptCode(-207);
        }
    }
}
