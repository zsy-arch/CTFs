package com.tencent.smtt.sdk;

import android.os.Bundle;
import com.tencent.smtt.sdk.TbsReaderView;
import com.tencent.smtt.sdk.a.d;

/* loaded from: classes2.dex */
class au implements TbsReaderView.ReaderCallback {
    final /* synthetic */ TbsReaderView a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public au(TbsReaderView tbsReaderView) {
        this.a = tbsReaderView;
    }

    @Override // com.tencent.smtt.sdk.TbsReaderView.ReaderCallback
    public void onCallBackAction(Integer num, Object obj, Object obj2) {
        Bundle bundle = null;
        boolean z = false;
        Bundle bundle2 = obj;
        switch (num.intValue()) {
            case TbsReaderView.ReaderCallback.READER_PDF_LIST /* 5008 */:
                if (d.c(this.a.a)) {
                    String str = "";
                    if (obj != null) {
                        Bundle bundle3 = (Bundle) obj;
                        str = bundle3.getString("docpath");
                        bundle = bundle3;
                    }
                    QbSdk.startQBForDoc(this.a.a, str, 4, 0, "pdf", bundle);
                    this.a.userStatistics(TbsReaderView.READER_STATISTICS_COUNT_PDF_INTO_BROWSER);
                    z = true;
                    bundle2 = obj;
                    break;
                } else {
                    num = Integer.valueOf((int) TbsReaderView.ReaderCallback.INSTALL_QB);
                    String resString = TbsReaderView.getResString(this.a.a, TbsReaderView.ReaderCallback.READER_PLUGIN_RES_PDF_GUIDE);
                    Bundle bundle4 = new Bundle();
                    bundle4.putString("tip", resString);
                    bundle4.putString("statistics", TbsReaderView.READER_STATISTICS_COUNT_PDF_INTO_DOWNLOAD);
                    bundle4.putInt("channel_id", TbsReaderView.READER_CHANNEL_PDF_ID);
                    this.a.userStatistics(TbsReaderView.READER_STATISTICS_COUNT_PDF_INTO_DIALOG);
                    bundle2 = bundle4;
                    break;
                }
            case TbsReaderView.ReaderCallback.READER_PPT_PLAY_MODEL /* 5009 */:
                if (d.c(this.a.a)) {
                    String str2 = "";
                    if (obj != null) {
                        Bundle bundle5 = (Bundle) obj;
                        str2 = bundle5.getString("docpath");
                        bundle = bundle5;
                    }
                    QbSdk.startQBForDoc(this.a.a, str2, 4, 0, "", bundle);
                    this.a.userStatistics(TbsReaderView.READER_STATISTICS_COUNT_PPT_INTO_BROWSER);
                    z = true;
                    bundle2 = obj;
                    break;
                } else {
                    num = Integer.valueOf((int) TbsReaderView.ReaderCallback.INSTALL_QB);
                    String resString2 = TbsReaderView.getResString(this.a.a, TbsReaderView.ReaderCallback.READER_PLUGIN_RES_PPT_GUIDE);
                    Bundle bundle6 = new Bundle();
                    bundle6.putString("tip", resString2);
                    bundle6.putString("statistics", TbsReaderView.READER_STATISTICS_COUNT_PPT_INTO_DOWNLOAD);
                    bundle6.putInt("channel_id", TbsReaderView.READER_CHANNEL_PPT_ID);
                    this.a.userStatistics(TbsReaderView.READER_STATISTICS_COUNT_PPT_INTO_DIALOG);
                    bundle2 = bundle6;
                    break;
                }
            case TbsReaderView.ReaderCallback.READER_TXT_READING_MODEL /* 5010 */:
                if (d.c(this.a.a)) {
                    String str3 = "";
                    if (obj != null) {
                        Bundle bundle7 = (Bundle) obj;
                        str3 = bundle7.getString("docpath");
                        bundle = bundle7;
                    }
                    QbSdk.startQBForDoc(this.a.a, str3, 4, 0, "txt", bundle);
                    z = true;
                    bundle2 = obj;
                    break;
                } else {
                    num = Integer.valueOf((int) TbsReaderView.ReaderCallback.INSTALL_QB);
                    String resString3 = TbsReaderView.getResString(this.a.a, TbsReaderView.ReaderCallback.READER_PLUGIN_RES_TXT_GUIDE);
                    Bundle bundle8 = new Bundle();
                    bundle8.putString("tip", resString3);
                    bundle8.putString("statistics", TbsReaderView.READER_STATISTICS_COUNT_TXT_INTO_DOWNLOAD);
                    bundle8.putInt("channel_id", TbsReaderView.READER_CHANNEL_TXT_ID);
                    this.a.userStatistics(TbsReaderView.READER_STATISTICS_COUNT_TXT_INTO_DIALOG);
                    bundle2 = bundle8;
                    break;
                }
            case TbsReaderView.ReaderCallback.READER_SEARCH_IN_DOCUMENT /* 5026 */:
                if (d.c(this.a.a)) {
                    String str4 = "";
                    if (obj != null) {
                        Bundle bundle9 = (Bundle) obj;
                        str4 = bundle9.getString("docpath");
                        bundle = bundle9;
                    }
                    QbSdk.startQBForDoc(this.a.a, str4, 4, 0, "doc", bundle);
                    this.a.userStatistics(TbsReaderView.READER_STATISTICS_COUNT_DOC_INTO_BROWSER);
                    z = true;
                    bundle2 = obj;
                    break;
                } else {
                    num = Integer.valueOf((int) TbsReaderView.ReaderCallback.INSTALL_QB);
                    String resString4 = TbsReaderView.getResString(this.a.a, TbsReaderView.ReaderCallback.READER_PLUGIN_RES_DOC_GUIDE);
                    Bundle bundle10 = new Bundle();
                    bundle10.putString("tip", resString4);
                    bundle10.putString("statistics", TbsReaderView.READER_STATISTICS_COUNT_DOC_INTO_DOWNLOAD);
                    bundle10.putInt("channel_id", TbsReaderView.READER_CHANNEL_DOC_ID);
                    this.a.userStatistics(TbsReaderView.READER_STATISTICS_COUNT_DOC_INTO_DIALOG);
                    bundle2 = bundle10;
                    break;
                }
            case TbsReaderView.ReaderCallback.READER_PLUGIN_SO_VERSION /* 5030 */:
                if (obj != null) {
                    Bundle bundle11 = (Bundle) obj;
                    TbsReaderView.gReaderPackName = bundle11.getString("name");
                    TbsReaderView.gReaderPackVersion = bundle11.getString("version");
                }
                z = true;
                bundle2 = obj;
                break;
        }
        if (this.a.d != null && !z) {
            this.a.d.onCallBackAction(num, bundle2, obj2);
        }
    }
}
