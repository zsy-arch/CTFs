package com.alipay.sdk.util;

import android.app.Activity;

/* loaded from: classes.dex */
final class n implements Runnable {
    final /* synthetic */ Activity a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public n(Activity activity) {
        this.a = activity;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.a.finish();
    }
}
