package com.tencent.smtt.sdk;

import com.tencent.smtt.export.external.interfaces.WebResourceError;

/* loaded from: classes2.dex */
class ab extends WebResourceError {
    final /* synthetic */ android.webkit.WebResourceError a;
    final /* synthetic */ z b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ab(z zVar, android.webkit.WebResourceError webResourceError) {
        this.b = zVar;
        this.a = webResourceError;
    }

    @Override // com.tencent.smtt.export.external.interfaces.WebResourceError
    public CharSequence getDescription() {
        return this.a.getDescription();
    }

    @Override // com.tencent.smtt.export.external.interfaces.WebResourceError
    public int getErrorCode() {
        return this.a.getErrorCode();
    }
}
