package com.tencent.smtt.sdk;

import android.graphics.Bitmap;
import com.tencent.smtt.export.external.interfaces.IconListener;
import com.tencent.smtt.sdk.WebIconDatabase;

/* loaded from: classes2.dex */
class ba implements IconListener {
    final /* synthetic */ WebIconDatabase.a a;
    final /* synthetic */ WebIconDatabase b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ba(WebIconDatabase webIconDatabase, WebIconDatabase.a aVar) {
        this.b = webIconDatabase;
        this.a = aVar;
    }

    @Override // com.tencent.smtt.export.external.interfaces.IconListener
    public void onReceivedIcon(String str, Bitmap bitmap) {
        this.a.a(str, bitmap);
    }
}
