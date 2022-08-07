package com.tencent.smtt.sdk;

import android.net.Uri;
import android.webkit.ValueCallback;

/* loaded from: classes2.dex */
class q implements ValueCallback<Uri[]> {
    final /* synthetic */ ValueCallback a;
    final /* synthetic */ n b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public q(n nVar, ValueCallback valueCallback) {
        this.b = nVar;
        this.a = valueCallback;
    }

    /* renamed from: a */
    public void onReceiveValue(Uri[] uriArr) {
        this.a.onReceiveValue(uriArr);
    }
}
