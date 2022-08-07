package com.tencent.smtt.sdk;

import android.graphics.Picture;
import com.tencent.smtt.export.external.interfaces.IX5WebViewBase;
import com.tencent.smtt.sdk.WebView;

/* loaded from: classes2.dex */
class bg implements IX5WebViewBase.PictureListener {
    final /* synthetic */ WebView.PictureListener a;
    final /* synthetic */ WebView b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bg(WebView webView, WebView.PictureListener pictureListener) {
        this.b = webView;
        this.a = pictureListener;
    }

    @Override // com.tencent.smtt.export.external.interfaces.IX5WebViewBase.PictureListener
    public void onNewPicture(IX5WebViewBase iX5WebViewBase, Picture picture, boolean z) {
        this.b.a(iX5WebViewBase);
        this.a.onNewPicture(this.b, picture);
    }

    @Override // com.tencent.smtt.export.external.interfaces.IX5WebViewBase.PictureListener
    public void onNewPictureIfHaveContent(IX5WebViewBase iX5WebViewBase, Picture picture) {
    }
}
