package com.tencent.smtt.sdk;

/* loaded from: classes2.dex */
final class j implements TbsListener {
    @Override // com.tencent.smtt.sdk.TbsListener
    public void onDownloadFinish(int i) {
        TbsListener tbsListener;
        TbsListener tbsListener2;
        TbsListener tbsListener3;
        TbsListener tbsListener4;
        TbsDownloader.a = false;
        tbsListener = QbSdk.y;
        if (tbsListener != null) {
            tbsListener4 = QbSdk.y;
            tbsListener4.onDownloadFinish(i);
        }
        tbsListener2 = QbSdk.z;
        if (tbsListener2 != null) {
            tbsListener3 = QbSdk.z;
            tbsListener3.onDownloadFinish(i);
        }
    }

    @Override // com.tencent.smtt.sdk.TbsListener
    public void onDownloadProgress(int i) {
        TbsListener tbsListener;
        TbsListener tbsListener2;
        TbsListener tbsListener3;
        TbsListener tbsListener4;
        tbsListener = QbSdk.z;
        if (tbsListener != null) {
            tbsListener4 = QbSdk.z;
            tbsListener4.onDownloadProgress(i);
        }
        tbsListener2 = QbSdk.y;
        if (tbsListener2 != null) {
            tbsListener3 = QbSdk.y;
            tbsListener3.onDownloadProgress(i);
        }
    }

    @Override // com.tencent.smtt.sdk.TbsListener
    public void onInstallFinish(int i) {
        TbsListener tbsListener;
        TbsListener tbsListener2;
        TbsListener tbsListener3;
        TbsListener tbsListener4;
        QbSdk.setTBSInstallingStatus(false);
        TbsDownloader.a = false;
        tbsListener = QbSdk.y;
        if (tbsListener != null) {
            tbsListener4 = QbSdk.y;
            tbsListener4.onInstallFinish(i);
        }
        tbsListener2 = QbSdk.z;
        if (tbsListener2 != null) {
            tbsListener3 = QbSdk.z;
            tbsListener3.onInstallFinish(i);
        }
    }
}
