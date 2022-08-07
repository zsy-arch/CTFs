package com.tencent.smtt.utils;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.utils.e;

/* loaded from: classes2.dex */
public class f implements e.a {
    final /* synthetic */ WebView a;
    final /* synthetic */ Context b;
    final /* synthetic */ RelativeLayout c;
    final /* synthetic */ String d;
    final /* synthetic */ TextView e;
    final /* synthetic */ e f;

    public f(e eVar, WebView webView, Context context, RelativeLayout relativeLayout, String str, TextView textView) {
        this.f = eVar;
        this.a = webView;
        this.b = context;
        this.c = relativeLayout;
        this.d = str;
        this.e = textView;
    }

    @Override // com.tencent.smtt.utils.e.a
    public void a() {
        this.a.post(new g(this));
    }

    @Override // com.tencent.smtt.utils.e.a
    public void a(int i) {
        this.a.post(new h(this, i));
    }

    @Override // com.tencent.smtt.utils.e.a
    public void a(Throwable th) {
        this.a.post(new i(this));
    }
}
