package com.tencent.smtt.sdk;

import android.content.pm.ApplicationInfo;
import com.tencent.smtt.export.external.interfaces.DownloadListener;
import com.tencent.smtt.sdk.stat.MttLoader;

/* loaded from: classes.dex */
public class b implements DownloadListener {

    /* renamed from: a  reason: collision with root package name */
    public DownloadListener f1327a;

    /* renamed from: b  reason: collision with root package name */
    public WebView f1328b;

    public b(WebView webView, DownloadListener downloadListener, boolean z) {
        this.f1327a = downloadListener;
        this.f1328b = webView;
    }

    @Override // com.tencent.smtt.export.external.interfaces.DownloadListener
    public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        onDownloadStart(str, null, null, str2, str3, str4, j, null, null);
    }

    @Override // com.tencent.smtt.export.external.interfaces.DownloadListener
    public void onDownloadStart(String str, String str2, byte[] bArr, String str3, String str4, String str5, long j, String str6, String str7) {
        DownloadListener downloadListener = this.f1327a;
        if (downloadListener == null) {
            QbSdk.canOpenMimeFileType(this.f1328b.getContext(), str5);
            ApplicationInfo applicationInfo = this.f1328b.getContext().getApplicationInfo();
            if (applicationInfo == null || !TbsConfig.APP_WX.equals(applicationInfo.packageName)) {
                MttLoader.loadUrl(this.f1328b.getContext(), str, null, null);
                return;
            }
            return;
        }
        downloadListener.onDownloadStart(str, str3, str4, str5, j);
    }

    @Override // com.tencent.smtt.export.external.interfaces.DownloadListener
    public void onDownloadVideo(String str, long j, int i) {
    }
}
