package com.tencent.smtt.sdk;

import android.content.Context;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsDownloader;

/* loaded from: classes2.dex */
final class i implements TbsDownloader.TbsDownloaderCallback {
    final /* synthetic */ Context a;
    final /* synthetic */ QbSdk.PreInitCallback b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public i(Context context, QbSdk.PreInitCallback preInitCallback) {
        this.a = context;
        this.b = preInitCallback;
    }

    @Override // com.tencent.smtt.sdk.TbsDownloader.TbsDownloaderCallback
    public void onNeedDownloadFinish(boolean z, int i) {
        if (TbsShareManager.findCoreForThirdPartyApp(this.a) != 0 || TbsShareManager.getCoreDisabled()) {
            QbSdk.preInit(this.a, this.b);
        } else {
            TbsShareManager.forceToLoadX5ForThirdApp(this.a, false);
        }
    }
}
