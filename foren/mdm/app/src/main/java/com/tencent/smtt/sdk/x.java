package com.tencent.smtt.sdk;

import android.net.Uri;
import android.webkit.ValueCallback;

/* loaded from: classes2.dex */
class x implements ValueCallback<Uri[]> {
    final /* synthetic */ ValueCallback a;
    final /* synthetic */ SystemWebChromeClient b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public x(SystemWebChromeClient systemWebChromeClient, ValueCallback valueCallback) {
        this.b = systemWebChromeClient;
        this.a = valueCallback;
    }

    /* renamed from: a */
    public void onReceiveValue(Uri[] uriArr) {
        this.a.onReceiveValue(uriArr);
    }
}
