package com.tencent.smtt.sdk;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.sdk.TbsDownloader;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.k;
import java.io.FileOutputStream;
import java.nio.channels.FileLock;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class af extends Handler {
    /* JADX INFO: Access modifiers changed from: package-private */
    public af(Looper looper) {
        super(looper);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        Context context;
        Context context2;
        Context context3;
        int f;
        ac acVar;
        Context context4;
        Context context5;
        Context context6;
        Context context7;
        Context context8;
        boolean c;
        Context context9;
        ac acVar2;
        Context context10;
        Context context11;
        Context context12;
        Context context13;
        Context context14;
        Context context15;
        boolean c2;
        Context context16;
        Context context17;
        Context context18;
        boolean z = true;
        switch (message.what) {
            case 100:
                boolean z2 = message.arg1 == 1;
                c2 = TbsDownloader.c(true, false);
                if (message.obj != null && (message.obj instanceof TbsDownloader.TbsDownloaderCallback)) {
                    TbsLog.i(TbsDownloader.LOGTAG, "needDownload-onNeedDownloadFinish needStartDownload=" + c2);
                    if (!c2 || z2) {
                        context18 = TbsDownloader.c;
                        ((TbsDownloader.TbsDownloaderCallback) message.obj).onNeedDownloadFinish(c2, TbsDownloadConfig.getInstance(context18).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0));
                    }
                }
                context16 = TbsDownloader.c;
                if (TbsShareManager.isThirdPartyApp(context16) && c2) {
                    context17 = TbsDownloader.c;
                    TbsDownloader.startDownload(context17);
                    return;
                }
                return;
            case 101:
                FileLock fileLock = null;
                StringBuilder append = new StringBuilder().append("tbs_download_lock_file");
                context6 = TbsDownloader.c;
                String sb = append.append(TbsDownloadConfig.getInstance(context6).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0)).append(".txt").toString();
                context7 = TbsDownloader.c;
                FileOutputStream b = k.b(context7, false, sb);
                if (b != null) {
                    context14 = TbsDownloader.c;
                    fileLock = k.a(context14, b);
                    if (fileLock == null) {
                        TbsLog.i(TbsDownloader.LOGTAG, "file lock locked,wx or qq is downloading");
                        context15 = TbsDownloader.c;
                        TbsDownloadConfig.getInstance(context15).setDownloadInterruptCode(-203);
                        return;
                    }
                } else {
                    context12 = TbsDownloader.c;
                    if (k.a(context12)) {
                        context13 = TbsDownloader.c;
                        TbsDownloadConfig.getInstance(context13).setDownloadInterruptCode(-204);
                        return;
                    }
                }
                if (message.arg1 != 1) {
                    z = false;
                }
                context8 = TbsDownloader.c;
                TbsDownloadConfig instance = TbsDownloadConfig.getInstance(context8);
                c = TbsDownloader.c(false, z);
                if (c) {
                    if (z) {
                        aj a = aj.a();
                        context10 = TbsDownloader.c;
                        context11 = TbsDownloader.c;
                        if (a.a(context10, TbsDownloadConfig.getInstance(context11).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0))) {
                            QbSdk.j.onDownloadFinish(122);
                            instance.setDownloadInterruptCode(-213);
                        }
                    }
                    if (instance.mPreferences.getBoolean(TbsDownloadConfig.TbsConfigKey.KEY_NEEDDOWNLOAD, false)) {
                        context9 = TbsDownloader.c;
                        TbsDownloadConfig.getInstance(context9).setDownloadInterruptCode(-215);
                        acVar2 = TbsDownloader.g;
                        acVar2.a(z);
                    } else {
                        QbSdk.j.onDownloadFinish(110);
                    }
                } else {
                    QbSdk.j.onDownloadFinish(110);
                }
                TbsLog.i(TbsDownloader.LOGTAG, "------freeFileLock called :");
                k.a(fileLock, b);
                return;
            case 102:
                TbsLog.i(TbsDownloader.LOGTAG, "[TbsDownloader.handleMessage] MSG_REPORT_DOWNLOAD_STAT");
                context2 = TbsDownloader.c;
                if (TbsShareManager.isThirdPartyApp(context2)) {
                    context5 = TbsDownloader.c;
                    f = TbsShareManager.a(context5, false);
                } else {
                    aj a2 = aj.a();
                    context3 = TbsDownloader.c;
                    f = a2.f(context3);
                }
                TbsLog.i(TbsDownloader.LOGTAG, "[TbsDownloader.handleMessage] localTbsVersion=" + f);
                acVar = TbsDownloader.g;
                acVar.a(f);
                context4 = TbsDownloader.c;
                TbsLogReport.a(context4).b();
                return;
            case 103:
                TbsLog.i(TbsDownloader.LOGTAG, "[TbsDownloader.handleMessage] MSG_CONTINUEINSTALL_TBSCORE");
                if (message.arg1 == 0) {
                    aj.a().a((Context) message.obj, true);
                    return;
                } else {
                    aj.a().a((Context) message.obj, false);
                    return;
                }
            case 104:
                TbsLog.i(TbsDownloader.LOGTAG, "[TbsDownloader.handleMessage] MSG_UPLOAD_TBSLOG");
                context = TbsDownloader.c;
                TbsLogReport.a(context).a();
                return;
            default:
                return;
        }
    }
}
