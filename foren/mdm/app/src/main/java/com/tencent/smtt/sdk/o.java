package com.tencent.smtt.sdk;

import android.os.Message;
import com.tencent.smtt.export.external.interfaces.IX5WebViewBase;
import com.tencent.smtt.sdk.WebView;

/* loaded from: classes2.dex */
class o implements Runnable {
    final /* synthetic */ WebView.WebViewTransport a;
    final /* synthetic */ Message b;
    final /* synthetic */ n c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public o(n nVar, WebView.WebViewTransport webViewTransport, Message message) {
        this.c = nVar;
        this.a = webViewTransport;
        this.b = message;
    }

    @Override // java.lang.Runnable
    public void run() {
        WebView webView = this.a.getWebView();
        if (webView != null) {
            ((IX5WebViewBase.WebViewTransport) this.b.obj).setWebView(webView.b());
        }
        this.b.sendToTarget();
    }
}
