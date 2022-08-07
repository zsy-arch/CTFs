package com.tencent.smtt.sdk;

import android.content.Context;
import com.tencent.smtt.sdk.QbSdk;

/* loaded from: classes2.dex */
final class h implements TbsListener {
    final /* synthetic */ Context a;
    final /* synthetic */ QbSdk.PreInitCallback b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public h(Context context, QbSdk.PreInitCallback preInitCallback) {
        this.a = context;
        this.b = preInitCallback;
    }

    @Override // com.tencent.smtt.sdk.TbsListener
    public void onDownloadFinish(int i) {
    }

    @Override // com.tencent.smtt.sdk.TbsListener
    public void onDownloadProgress(int i) {
    }

    @Override // com.tencent.smtt.sdk.TbsListener
    public void onInstallFinish(int i) {
        QbSdk.preInit(this.a, this.b);
    }
}
