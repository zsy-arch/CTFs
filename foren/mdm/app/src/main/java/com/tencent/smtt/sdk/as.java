package com.tencent.smtt.sdk;

import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.n;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class as implements n.a {
    final /* synthetic */ TbsLogReport a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public as(TbsLogReport tbsLogReport) {
        this.a = tbsLogReport;
    }

    @Override // com.tencent.smtt.utils.n.a
    public void a(int i) {
        TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloadStat.reportTbsLog] httpResponseCode=" + i);
    }
}
