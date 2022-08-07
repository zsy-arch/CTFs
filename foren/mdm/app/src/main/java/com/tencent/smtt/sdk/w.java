package com.tencent.smtt.sdk;

import android.net.Uri;
import android.webkit.ValueCallback;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class w implements ValueCallback<Uri> {
    final /* synthetic */ ValueCallback a;
    final /* synthetic */ SystemWebChromeClient b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public w(SystemWebChromeClient systemWebChromeClient, ValueCallback valueCallback) {
        this.b = systemWebChromeClient;
        this.a = valueCallback;
    }

    /* renamed from: a */
    public void onReceiveValue(Uri uri) {
        this.a.onReceiveValue(uri);
    }
}
