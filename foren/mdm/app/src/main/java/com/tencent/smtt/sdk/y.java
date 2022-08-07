package com.tencent.smtt.sdk;

import android.content.Intent;
import android.webkit.WebChromeClient;
import com.tencent.smtt.sdk.WebChromeClient;

/* loaded from: classes2.dex */
class y extends WebChromeClient.FileChooserParams {
    final /* synthetic */ WebChromeClient.FileChooserParams a;
    final /* synthetic */ SystemWebChromeClient b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public y(SystemWebChromeClient systemWebChromeClient, WebChromeClient.FileChooserParams fileChooserParams) {
        this.b = systemWebChromeClient;
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
