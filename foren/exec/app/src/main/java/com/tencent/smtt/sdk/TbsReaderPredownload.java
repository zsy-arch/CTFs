package com.tencent.smtt.sdk;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.tencent.smtt.sdk.TbsReaderView;
import java.util.LinkedList;

/* loaded from: classes.dex */
public class TbsReaderPredownload {
    public static final int READER_SO_SUCCESS = 2;
    public static final int READER_WAIT_IN_QUEUE = 3;

    /* renamed from: b  reason: collision with root package name */
    public static final String[] f1250b = {"docx", "pptx", "xlsx", "pdf", "epub", "txt"};
    public ReaderPreDownloadCallback i;

    /* renamed from: a  reason: collision with root package name */
    public Handler f1251a = null;

    /* renamed from: c  reason: collision with root package name */
    public LinkedList<String> f1252c = new LinkedList<>();

    /* renamed from: d  reason: collision with root package name */
    public boolean f1253d = false;

    /* renamed from: e  reason: collision with root package name */
    public ReaderWizard f1254e = null;
    public TbsReaderView.ReaderCallback f = null;
    public Object g = null;
    public Context h = null;
    public String j = BuildConfig.FLAVOR;

    /* loaded from: classes.dex */
    public interface ReaderPreDownloadCallback {
        public static final int NOTIFY_PLUGIN_FAILED = -1;
        public static final int NOTIFY_PLUGIN_SUCCESS = 0;

        void onEvent(String str, int i, boolean z);
    }

    public TbsReaderPredownload(ReaderPreDownloadCallback readerPreDownloadCallback) {
        this.i = null;
        this.i = readerPreDownloadCallback;
        for (String str : f1250b) {
            this.f1252c.add(str);
        }
        a();
    }

    private void b() {
        b(3);
    }

    public void a() {
        this.f1251a = new Handler(Looper.getMainLooper()) { // from class: com.tencent.smtt.sdk.TbsReaderPredownload.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what == 3 && !TbsReaderPredownload.this.f1252c.isEmpty()) {
                    TbsReaderPredownload tbsReaderPredownload = TbsReaderPredownload.this;
                    if (!tbsReaderPredownload.f1253d) {
                        String removeFirst = tbsReaderPredownload.f1252c.removeFirst();
                        TbsReaderPredownload tbsReaderPredownload2 = TbsReaderPredownload.this;
                        tbsReaderPredownload2.j = removeFirst;
                        if (!tbsReaderPredownload2.a(removeFirst)) {
                            TbsReaderPredownload.this.a(-1);
                        }
                    }
                }
            }
        };
    }

    public void a(int i) {
        if (this.i != null) {
            this.i.onEvent(this.j, i, this.f1252c.isEmpty());
        }
    }

    public void a(int i, int i2) {
        this.f1251a.sendMessageDelayed(this.f1251a.obtainMessage(i), i2);
    }

    public boolean a(String str) {
        if (this.g == null || this.f1254e == null || !ReaderWizard.isSupportExt(str)) {
            return false;
        }
        return this.f1254e.checkPlugin(this.g, this.h, str, true);
    }

    public void b(int i) {
        this.f1251a.removeMessages(i);
    }

    public boolean c(int i) {
        return this.f1251a.hasMessages(i);
    }

    public boolean init(Context context) {
        if (context == null) {
            return false;
        }
        this.h = context.getApplicationContext();
        boolean a2 = TbsReaderView.a(context.getApplicationContext());
        this.f = new TbsReaderView.ReaderCallback() { // from class: com.tencent.smtt.sdk.TbsReaderPredownload.1
            @Override // com.tencent.smtt.sdk.TbsReaderView.ReaderCallback
            public void onCallBackAction(Integer num, Object obj, Object obj2) {
                int intValue;
                if (num.intValue() == 5012 && 5014 != (intValue = ((Integer) obj).intValue())) {
                    if (5013 == intValue || intValue == 0) {
                        TbsReaderPredownload.this.a(0);
                    } else {
                        TbsReaderPredownload.this.a(-1);
                    }
                    TbsReaderPredownload tbsReaderPredownload = TbsReaderPredownload.this;
                    tbsReaderPredownload.j = BuildConfig.FLAVOR;
                    tbsReaderPredownload.a(3, 100);
                }
            }
        };
        try {
            if (this.f1254e == null) {
                this.f1254e = new ReaderWizard(this.f);
            }
            if (this.g == null) {
                this.g = this.f1254e.getTbsReader();
            }
            return this.g != null ? this.f1254e.initTbsReader(this.g, context.getApplicationContext()) : a2;
        } catch (NullPointerException unused) {
            return false;
        }
    }

    public void pause() {
        this.f1253d = true;
    }

    public void shutdown() {
        this.i = null;
        this.f1253d = false;
        this.f1252c.clear();
        b(3);
        ReaderWizard readerWizard = this.f1254e;
        if (readerWizard != null) {
            readerWizard.destroy(this.g);
            this.g = null;
        }
        this.h = null;
    }

    public void start(String str) {
        this.f1253d = false;
        b(3);
        this.f1252c.add(str);
        a(3, 100);
    }

    public void startAll() {
        this.f1253d = false;
        if (!false && !c(3)) {
            a(3, 100);
        }
    }
}
