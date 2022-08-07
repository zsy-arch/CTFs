package com.tencent.smtt.sdk;

import android.content.Context;
import android.os.Bundle;
import com.tencent.smtt.export.external.DexLoader;

/* loaded from: classes.dex */
public class TbsVideoCacheTask {
    public static final String KEY_VIDEO_CACHE_PARAM_FILENAME = "filename";
    public static final String KEY_VIDEO_CACHE_PARAM_FOLDERPATH = "folderPath";
    public static final String KEY_VIDEO_CACHE_PARAM_HEADER = "header";
    public static final String KEY_VIDEO_CACHE_PARAM_URL = "url";

    /* renamed from: a  reason: collision with root package name */
    public Context f1268a;

    /* renamed from: b  reason: collision with root package name */
    public TbsVideoCacheListener f1269b;

    /* renamed from: e  reason: collision with root package name */
    public String f1272e;
    public String f;

    /* renamed from: c  reason: collision with root package name */
    public boolean f1270c = false;

    /* renamed from: d  reason: collision with root package name */
    public o f1271d = null;
    public Object g = null;

    public TbsVideoCacheTask(Context context, Bundle bundle, TbsVideoCacheListener tbsVideoCacheListener) {
        this.f1268a = null;
        this.f1269b = null;
        this.f1268a = context;
        this.f1269b = tbsVideoCacheListener;
        if (bundle != null) {
            this.f1272e = bundle.getString("taskId");
            this.f = bundle.getString(KEY_VIDEO_CACHE_PARAM_URL);
        }
        a(bundle);
    }

    private void a(Bundle bundle) {
        TbsVideoCacheListener tbsVideoCacheListener;
        String str;
        DexLoader dexLoader;
        if (this.f1271d == null) {
            d.a(true).a(this.f1268a, false, false);
            s a2 = d.a(true).a();
            if (a2 != null) {
                dexLoader = a2.b();
            } else {
                this.f1269b.onVideoDownloadError(this, -1, "init engine error!", null);
                dexLoader = null;
            }
            if (dexLoader != null) {
                this.f1271d = new o(dexLoader);
            } else {
                this.f1269b.onVideoDownloadError(this, -1, "Java dexloader invalid!", null);
            }
        }
        o oVar = this.f1271d;
        if (oVar != null) {
            this.g = oVar.a(this.f1268a, this, bundle);
            if (this.g == null) {
                tbsVideoCacheListener = this.f1269b;
                str = "init task error!";
            } else {
                return;
            }
        } else {
            tbsVideoCacheListener = this.f1269b;
            if (tbsVideoCacheListener != null) {
                str = "init error!";
            } else {
                return;
            }
        }
        tbsVideoCacheListener.onVideoDownloadError(this, -1, str, null);
    }

    public long getContentLength() {
        o oVar = this.f1271d;
        if (oVar != null && this.g != null) {
            return oVar.d();
        }
        TbsVideoCacheListener tbsVideoCacheListener = this.f1269b;
        if (tbsVideoCacheListener == null) {
            return 0L;
        }
        tbsVideoCacheListener.onVideoDownloadError(this, -1, "getContentLength failed, init uncompleted!", null);
        return 0L;
    }

    public int getDownloadedSize() {
        o oVar = this.f1271d;
        if (oVar != null && this.g != null) {
            return oVar.e();
        }
        TbsVideoCacheListener tbsVideoCacheListener = this.f1269b;
        if (tbsVideoCacheListener == null) {
            return 0;
        }
        tbsVideoCacheListener.onVideoDownloadError(this, -1, "getDownloadedSize failed, init uncompleted!", null);
        return 0;
    }

    public int getProgress() {
        o oVar = this.f1271d;
        if (oVar != null && this.g != null) {
            return oVar.f();
        }
        TbsVideoCacheListener tbsVideoCacheListener = this.f1269b;
        if (tbsVideoCacheListener == null) {
            return 0;
        }
        tbsVideoCacheListener.onVideoDownloadError(this, -1, "getProgress failed, init uncompleted!", null);
        return 0;
    }

    public String getTaskID() {
        return this.f1272e;
    }

    public String getTaskUrl() {
        return this.f;
    }

    public void pauseTask() {
        o oVar = this.f1271d;
        if (oVar == null || this.g == null) {
            TbsVideoCacheListener tbsVideoCacheListener = this.f1269b;
            if (tbsVideoCacheListener != null) {
                tbsVideoCacheListener.onVideoDownloadError(this, -1, "pauseTask failed, init uncompleted!", null);
                return;
            }
            return;
        }
        oVar.a();
    }

    public void removeTask(boolean z) {
        o oVar = this.f1271d;
        if (oVar == null || this.g == null) {
            TbsVideoCacheListener tbsVideoCacheListener = this.f1269b;
            if (tbsVideoCacheListener != null) {
                tbsVideoCacheListener.onVideoDownloadError(this, -1, "removeTask failed, init uncompleted!", null);
                return;
            }
            return;
        }
        oVar.a(z);
    }

    public void resumeTask() {
        o oVar = this.f1271d;
        if (oVar == null || this.g == null) {
            TbsVideoCacheListener tbsVideoCacheListener = this.f1269b;
            if (tbsVideoCacheListener != null) {
                tbsVideoCacheListener.onVideoDownloadError(this, -1, "resumeTask failed, init uncompleted!", null);
                return;
            }
            return;
        }
        oVar.b();
    }

    public void stopTask() {
        o oVar = this.f1271d;
        if (oVar == null || this.g == null) {
            TbsVideoCacheListener tbsVideoCacheListener = this.f1269b;
            if (tbsVideoCacheListener != null) {
                tbsVideoCacheListener.onVideoDownloadError(this, -1, "stopTask failed, init uncompleted!", null);
                return;
            }
            return;
        }
        oVar.c();
    }
}
