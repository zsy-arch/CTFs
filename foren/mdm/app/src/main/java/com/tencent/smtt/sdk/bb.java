package com.tencent.smtt.sdk;

import android.graphics.Bitmap;
import android.webkit.WebIconDatabase;
import com.tencent.smtt.sdk.WebIconDatabase;

/* loaded from: classes2.dex */
class bb implements WebIconDatabase.IconListener {
    final /* synthetic */ WebIconDatabase.a a;
    final /* synthetic */ WebIconDatabase b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bb(WebIconDatabase webIconDatabase, WebIconDatabase.a aVar) {
        this.b = webIconDatabase;
        this.a = aVar;
    }

    @Override // android.webkit.WebIconDatabase.IconListener
    public void onReceivedIcon(String str, Bitmap bitmap) {
        this.a.a(str, bitmap);
    }
}
