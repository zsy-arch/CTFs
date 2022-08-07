package com.tencent.smtt.sdk;

import android.content.Context;
import android.os.Bundle;
import com.tencent.smtt.export.external.DexLoader;

/* loaded from: classes.dex */
public class o {

    /* renamed from: a  reason: collision with root package name */
    public DexLoader f1387a;

    /* renamed from: b  reason: collision with root package name */
    public Object f1388b = null;

    public o(DexLoader dexLoader) {
        this.f1387a = null;
        this.f1387a = dexLoader;
    }

    public Object a(Context context, Object obj, Bundle bundle) {
        DexLoader dexLoader = this.f1387a;
        if (dexLoader != null) {
            this.f1388b = dexLoader.newInstance("com.tencent.tbs.cache.TbsVideoCacheTaskProxy", new Class[]{Context.class, Object.class, Bundle.class}, context, obj, bundle);
        }
        return this.f1388b;
    }

    public void a() {
        DexLoader dexLoader = this.f1387a;
        if (dexLoader != null) {
            dexLoader.invokeMethod(this.f1388b, "com.tencent.tbs.cache.TbsVideoCacheTaskProxy", "pauseTask", new Class[0], new Object[0]);
        }
    }

    public void a(boolean z) {
        DexLoader dexLoader = this.f1387a;
        if (dexLoader != null) {
            dexLoader.invokeMethod(this.f1388b, "com.tencent.tbs.cache.TbsVideoCacheTaskProxy", "removeTask", new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    public void b() {
        DexLoader dexLoader = this.f1387a;
        if (dexLoader != null) {
            dexLoader.invokeMethod(this.f1388b, "com.tencent.tbs.cache.TbsVideoCacheTaskProxy", "resumeTask", new Class[0], new Object[0]);
        }
    }

    public void c() {
        DexLoader dexLoader = this.f1387a;
        if (dexLoader != null) {
            dexLoader.invokeMethod(this.f1388b, "com.tencent.tbs.cache.TbsVideoCacheTaskProxy", "stopTask", new Class[0], new Object[0]);
        }
    }

    public long d() {
        DexLoader dexLoader = this.f1387a;
        if (dexLoader == null) {
            return 0L;
        }
        Object invokeMethod = dexLoader.invokeMethod(this.f1388b, "com.tencent.tbs.cache.TbsVideoCacheTaskProxy", "getContentLength", new Class[0], new Object[0]);
        if (invokeMethod instanceof Long) {
            return ((Long) invokeMethod).longValue();
        }
        return 0L;
    }

    public int e() {
        DexLoader dexLoader = this.f1387a;
        if (dexLoader != null) {
            Object invokeMethod = dexLoader.invokeMethod(this.f1388b, "com.tencent.tbs.cache.TbsVideoCacheTaskProxy", "getDownloadedSize", new Class[0], new Object[0]);
            if (invokeMethod instanceof Integer) {
                return ((Integer) invokeMethod).intValue();
            }
        }
        return 0;
    }

    public int f() {
        DexLoader dexLoader = this.f1387a;
        if (dexLoader != null) {
            Object invokeMethod = dexLoader.invokeMethod(this.f1388b, "com.tencent.tbs.cache.TbsVideoCacheTaskProxy", "getProgress", new Class[0], new Object[0]);
            if (invokeMethod instanceof Integer) {
                return ((Integer) invokeMethod).intValue();
            }
        }
        return 0;
    }
}
