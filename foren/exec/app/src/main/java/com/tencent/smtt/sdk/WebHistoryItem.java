package com.tencent.smtt.sdk;

import android.graphics.Bitmap;
import com.tencent.smtt.export.external.interfaces.IX5WebHistoryItem;

/* loaded from: classes.dex */
public class WebHistoryItem {

    /* renamed from: a  reason: collision with root package name */
    public IX5WebHistoryItem f1281a = null;

    /* renamed from: b  reason: collision with root package name */
    public android.webkit.WebHistoryItem f1282b = null;

    public static WebHistoryItem a(android.webkit.WebHistoryItem webHistoryItem) {
        if (webHistoryItem == null) {
            return null;
        }
        WebHistoryItem webHistoryItem2 = new WebHistoryItem();
        webHistoryItem2.f1282b = webHistoryItem;
        return webHistoryItem2;
    }

    public static WebHistoryItem a(IX5WebHistoryItem iX5WebHistoryItem) {
        if (iX5WebHistoryItem == null) {
            return null;
        }
        WebHistoryItem webHistoryItem = new WebHistoryItem();
        webHistoryItem.f1281a = iX5WebHistoryItem;
        return webHistoryItem;
    }

    public Bitmap getFavicon() {
        IX5WebHistoryItem iX5WebHistoryItem = this.f1281a;
        return iX5WebHistoryItem != null ? iX5WebHistoryItem.getFavicon() : this.f1282b.getFavicon();
    }

    public String getOriginalUrl() {
        IX5WebHistoryItem iX5WebHistoryItem = this.f1281a;
        return iX5WebHistoryItem != null ? iX5WebHistoryItem.getOriginalUrl() : this.f1282b.getOriginalUrl();
    }

    public String getTitle() {
        IX5WebHistoryItem iX5WebHistoryItem = this.f1281a;
        return iX5WebHistoryItem != null ? iX5WebHistoryItem.getTitle() : this.f1282b.getTitle();
    }

    public String getUrl() {
        IX5WebHistoryItem iX5WebHistoryItem = this.f1281a;
        return iX5WebHistoryItem != null ? iX5WebHistoryItem.getUrl() : this.f1282b.getUrl();
    }
}
