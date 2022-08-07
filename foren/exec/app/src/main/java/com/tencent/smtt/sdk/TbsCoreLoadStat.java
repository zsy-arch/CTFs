package com.tencent.smtt.sdk;

import android.content.Context;
import com.tencent.smtt.utils.TbsLog;
import java.util.Arrays;

/* loaded from: classes.dex */
public class TbsCoreLoadStat {

    /* renamed from: d */
    public static TbsCoreLoadStat f1202d = null;
    public static volatile int mLoadErrorCode = -1;

    /* renamed from: a */
    public TbsSequenceQueue f1203a = null;

    /* renamed from: b */
    public boolean f1204b = false;

    /* renamed from: c */
    public final int f1205c = 3;

    /* loaded from: classes.dex */
    public class TbsSequenceQueue {

        /* renamed from: b */
        public int f1207b;

        /* renamed from: c */
        public int f1208c;

        /* renamed from: d */
        public int[] f1209d;

        /* renamed from: e */
        public int f1210e;
        public int f;

        public TbsSequenceQueue() {
            TbsCoreLoadStat.this = r1;
            this.f1207b = 10;
            this.f1210e = 0;
            this.f = 0;
            this.f1208c = this.f1207b;
            this.f1209d = new int[this.f1208c];
        }

        public TbsSequenceQueue(int i, int i2) {
            TbsCoreLoadStat.this = r1;
            this.f1207b = 10;
            this.f1210e = 0;
            this.f = 0;
            this.f1208c = i2;
            this.f1209d = new int[this.f1208c];
            this.f1209d[0] = i;
            this.f++;
        }

        public void add(int i) {
            int i2 = this.f;
            if (i2 <= this.f1208c - 1) {
                int[] iArr = this.f1209d;
                this.f = i2 + 1;
                iArr[i2] = i;
                return;
            }
            throw new IndexOutOfBoundsException("sequeue is full");
        }

        public void clear() {
            Arrays.fill(this.f1209d, 0);
            this.f1210e = 0;
            this.f = 0;
        }

        public int element() {
            if (!empty()) {
                return this.f1209d[this.f1210e];
            }
            throw new IndexOutOfBoundsException("sequeue is null");
        }

        public boolean empty() {
            return this.f == this.f1210e;
        }

        public int length() {
            return this.f - this.f1210e;
        }

        public int remove() {
            if (!empty()) {
                int[] iArr = this.f1209d;
                int i = this.f1210e;
                int i2 = iArr[i];
                this.f1210e = i + 1;
                iArr[i] = 0;
                return i2;
            }
            throw new IndexOutOfBoundsException("sequeue is null");
        }

        public String toString() {
            if (empty()) {
                return BuildConfig.FLAVOR;
            }
            StringBuilder sb = new StringBuilder("[");
            for (int i = this.f1210e; i < this.f; i++) {
                sb.append(String.valueOf(this.f1209d[i]) + ",");
            }
            int length = sb.length();
            StringBuilder delete = sb.delete(length - 1, length);
            delete.append("]");
            return delete.toString();
        }
    }

    public static TbsCoreLoadStat getInstance() {
        if (f1202d == null) {
            f1202d = new TbsCoreLoadStat();
        }
        return f1202d;
    }

    public void a() {
        TbsSequenceQueue tbsSequenceQueue = this.f1203a;
        if (tbsSequenceQueue != null) {
            tbsSequenceQueue.clear();
        }
        this.f1204b = false;
    }

    public void a(Context context, int i) {
        a(context, i, null);
        TbsLog.e(TbsListener.tag_load_error, BuildConfig.FLAVOR + i);
    }

    public synchronized void a(Context context, int i, Throwable th) {
        if (mLoadErrorCode == -1) {
            mLoadErrorCode = i;
            TbsLog.addLog(TbsLog.TBSLOG_CODE_SDK_LOAD_ERROR, "code=%d,desc=%s", Integer.valueOf(i), String.valueOf(th));
            if (th != null) {
                TbsLogReport.getInstance(context).setLoadErrorCode(i, th);
            } else {
                TbsLog.e("TbsCoreLoadStat", "setLoadErrorCode :: error is null with errorCode: " + i + "; Check & correct it!");
            }
            return;
        }
        TbsLog.w("TbsCoreLoadStat", "setLoadErrorCode :: error(" + mLoadErrorCode + ") was already reported; " + i + " is duplicated. Try to remove it!");
    }
}
