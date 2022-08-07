package com.tencent.smtt.utils;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;
import com.tencent.smtt.sdk.WebView;

/* loaded from: classes2.dex */
class g implements Runnable {
    final /* synthetic */ f a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public g(f fVar) {
        this.a = fVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        Looper looper;
        Toast.makeText(this.a.b, "下载成功", 0).show();
        this.a.c.setVisibility(4);
        e eVar = this.a.f;
        String str = this.a.d;
        WebView webView = this.a.a;
        Context context = this.a.b;
        looper = e.c;
        eVar.a(str, webView, context, looper);
    }
}
