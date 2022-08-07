package com.alipay.sdk.app;

import android.app.Activity;
import android.webkit.SslErrorHandler;
import com.alipay.sdk.widget.d;

/* loaded from: classes.dex */
public final class c implements Runnable {
    final /* synthetic */ SslErrorHandler a;
    final /* synthetic */ b b;

    public c(b bVar, SslErrorHandler sslErrorHandler) {
        this.b = bVar;
        this.a = sslErrorHandler;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Activity activity;
        activity = this.b.a;
        d.a(activity, "安全警告", "安全连接证书校验无效，将无法保证访问数据的安全性，可能存在风险，请选择是否继续？", "继续", new d(this), "退出", new e(this));
    }
}
