package com.tencent.smtt.sdk;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class t implements Runnable {
    final /* synthetic */ s a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public t(s sVar) {
        this.a = sVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        WebView webView;
        WebView webView2;
        WebView webView3;
        webView = this.a.b;
        if (!TbsShareManager.forceLoadX5FromTBSDemo(webView.getContext())) {
            webView2 = this.a.b;
            if (TbsDownloader.needDownload(webView2.getContext(), false)) {
                webView3 = this.a.b;
                TbsDownloader.startDownload(webView3.getContext());
            }
        }
    }
}
