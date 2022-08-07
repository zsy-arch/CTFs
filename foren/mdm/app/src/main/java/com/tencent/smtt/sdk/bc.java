package com.tencent.smtt.sdk;

import com.tencent.smtt.export.external.extension.interfaces.IX5WebViewClientExtension;
import com.tencent.smtt.export.external.extension.proxy.X5ProxyWebViewClientExtension;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class bc extends X5ProxyWebViewClientExtension {
    final /* synthetic */ WebView a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public bc(WebView webView, IX5WebViewClientExtension iX5WebViewClientExtension) {
        super(iX5WebViewClientExtension);
        this.a = webView;
    }

    @Override // com.tencent.smtt.export.external.extension.proxy.ProxyWebViewClientExtension, com.tencent.smtt.export.external.extension.interfaces.IX5WebViewClientExtension
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        this.a.onScrollChanged(i3, i4, i, i2);
    }
}
