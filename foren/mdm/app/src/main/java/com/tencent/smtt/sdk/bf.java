package com.tencent.smtt.sdk;

import android.graphics.Picture;
import android.webkit.WebView;
import com.tencent.smtt.sdk.WebView;

/* loaded from: classes2.dex */
class bf implements WebView.PictureListener {
    final /* synthetic */ WebView.PictureListener a;
    final /* synthetic */ WebView b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bf(WebView webView, WebView.PictureListener pictureListener) {
        this.b = webView;
        this.a = pictureListener;
    }

    @Override // android.webkit.WebView.PictureListener
    public void onNewPicture(android.webkit.WebView webView, Picture picture) {
        this.b.a(webView);
        this.a.onNewPicture(this.b, picture);
    }
}
