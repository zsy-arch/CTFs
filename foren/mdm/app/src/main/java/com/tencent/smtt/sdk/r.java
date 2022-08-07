package com.tencent.smtt.sdk;

import android.content.Intent;
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;
import com.tencent.smtt.sdk.WebChromeClient;

/* loaded from: classes2.dex */
class r extends WebChromeClient.FileChooserParams {
    final /* synthetic */ IX5WebChromeClient.FileChooserParams a;
    final /* synthetic */ n b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public r(n nVar, IX5WebChromeClient.FileChooserParams fileChooserParams) {
        this.b = nVar;
        this.a = fileChooserParams;
    }

    @Override // com.tencent.smtt.sdk.WebChromeClient.FileChooserParams
    public Intent createIntent() {
        return this.a.createIntent();
    }

    @Override // com.tencent.smtt.sdk.WebChromeClient.FileChooserParams
    public String[] getAcceptTypes() {
        return this.a.getAcceptTypes();
    }

    @Override // com.tencent.smtt.sdk.WebChromeClient.FileChooserParams
    public String getFilenameHint() {
        return this.a.getFilenameHint();
    }

    @Override // com.tencent.smtt.sdk.WebChromeClient.FileChooserParams
    public int getMode() {
        return this.a.getMode();
    }

    @Override // com.tencent.smtt.sdk.WebChromeClient.FileChooserParams
    public CharSequence getTitle() {
        return this.a.getTitle();
    }

    @Override // com.tencent.smtt.sdk.WebChromeClient.FileChooserParams
    public boolean isCaptureEnabled() {
        return this.a.isCaptureEnabled();
    }
}
