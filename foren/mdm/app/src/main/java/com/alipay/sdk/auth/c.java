package com.alipay.sdk.auth;

import android.webkit.WebView;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class c implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ AuthActivity b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(AuthActivity authActivity, String str) {
        this.b = authActivity;
        this.a = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        WebView webView;
        try {
            webView = this.b.c;
            webView.loadUrl("javascript:" + this.a);
        } catch (Exception e) {
        }
    }
}
