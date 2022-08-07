package com.tencent.smtt.sdk;

import android.webkit.ValueCallback;

/* loaded from: classes2.dex */
class u implements ValueCallback<String[]> {
    final /* synthetic */ ValueCallback a;
    final /* synthetic */ SystemWebChromeClient b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public u(SystemWebChromeClient systemWebChromeClient, ValueCallback valueCallback) {
        this.b = systemWebChromeClient;
        this.a = valueCallback;
    }

    /* renamed from: a */
    public void onReceiveValue(String[] strArr) {
        this.a.onReceiveValue(strArr);
    }
}
