package com.tencent.smtt.sdk;

import android.webkit.DownloadListener;
import com.tencent.smtt.sdk.a.d;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class be implements DownloadListener {
    final /* synthetic */ DownloadListener a;
    final /* synthetic */ WebView b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public be(WebView webView, DownloadListener downloadListener) {
        this.b = webView;
        this.a = downloadListener;
    }

    @Override // android.webkit.DownloadListener
    public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        if (this.a == null) {
            d.a(this.b.i, str, null, null);
        } else {
            this.a.onDownloadStart(str, str2, str3, str4, j);
        }
    }
}
