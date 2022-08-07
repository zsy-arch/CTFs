package com.tencent.smtt.sdk;

import com.tencent.smtt.export.external.interfaces.IX5WebBackForwardList;

/* loaded from: classes.dex */
public class WebBackForwardList {

    /* renamed from: a  reason: collision with root package name */
    public IX5WebBackForwardList f1279a = null;

    /* renamed from: b  reason: collision with root package name */
    public android.webkit.WebBackForwardList f1280b = null;

    public static WebBackForwardList a(android.webkit.WebBackForwardList webBackForwardList) {
        if (webBackForwardList == null) {
            return null;
        }
        WebBackForwardList webBackForwardList2 = new WebBackForwardList();
        webBackForwardList2.f1280b = webBackForwardList;
        return webBackForwardList2;
    }

    public static WebBackForwardList a(IX5WebBackForwardList iX5WebBackForwardList) {
        if (iX5WebBackForwardList == null) {
            return null;
        }
        WebBackForwardList webBackForwardList = new WebBackForwardList();
        webBackForwardList.f1279a = iX5WebBackForwardList;
        return webBackForwardList;
    }

    public int getCurrentIndex() {
        IX5WebBackForwardList iX5WebBackForwardList = this.f1279a;
        return iX5WebBackForwardList != null ? iX5WebBackForwardList.getCurrentIndex() : this.f1280b.getCurrentIndex();
    }

    public WebHistoryItem getCurrentItem() {
        IX5WebBackForwardList iX5WebBackForwardList = this.f1279a;
        return iX5WebBackForwardList != null ? WebHistoryItem.a(iX5WebBackForwardList.getCurrentItem()) : WebHistoryItem.a(this.f1280b.getCurrentItem());
    }

    public WebHistoryItem getItemAtIndex(int i) {
        IX5WebBackForwardList iX5WebBackForwardList = this.f1279a;
        return iX5WebBackForwardList != null ? WebHistoryItem.a(iX5WebBackForwardList.getItemAtIndex(i)) : WebHistoryItem.a(this.f1280b.getItemAtIndex(i));
    }

    public int getSize() {
        IX5WebBackForwardList iX5WebBackForwardList = this.f1279a;
        return iX5WebBackForwardList != null ? iX5WebBackForwardList.getSize() : this.f1280b.getSize();
    }
}
