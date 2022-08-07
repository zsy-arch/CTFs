package com.tencent.smtt.sdk;

import android.webkit.WebView;
import com.tencent.smtt.export.external.interfaces.IX5WebViewBase;

/* loaded from: classes2.dex */
class bd implements WebView.FindListener {
    final /* synthetic */ IX5WebViewBase.FindListener a;
    final /* synthetic */ WebView b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bd(WebView webView, IX5WebViewBase.FindListener findListener) {
        this.b = webView;
        this.a = findListener;
    }

    @Override // android.webkit.WebView.FindListener
    public void onFindResultReceived(int i, int i2, boolean z) {
        this.a.onFindResultReceived(i, i2, z);
    }
}
