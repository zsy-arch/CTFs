package com.tencent.smtt.sdk;

import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.n;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class at implements n.a {
    final /* synthetic */ TbsLogReport a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public at(TbsLogReport tbsLogReport) {
        this.a = tbsLogReport;
    }

    @Override // com.tencent.smtt.utils.n.a
    public void a(int i) {
        TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloadStat.reportDownloadStat] onHttpResponseCode:" + i);
        if (i < 300) {
            this.a.g();
        }
    }
}
