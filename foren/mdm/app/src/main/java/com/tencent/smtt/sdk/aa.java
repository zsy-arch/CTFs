package com.tencent.smtt.sdk;

/* loaded from: classes2.dex */
class aa implements Runnable {
    final /* synthetic */ z a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public aa(z zVar) {
        this.a = zVar;
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
