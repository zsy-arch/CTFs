package com.amap.api.col;

import android.content.Context;
import dalvik.system.DexFile;
import java.util.HashMap;
import java.util.Map;

/* compiled from: BaseLoader.java */
/* loaded from: classes.dex */
abstract class hn extends ClassLoader {
    protected final Context a;
    protected final Map<String, Class<?>> b = new HashMap();
    protected DexFile c = null;
    volatile boolean d = true;
    protected gj e;
    protected String f;

    public hn(Context context, gj gjVar, boolean z) {
        super(context.getClassLoader());
        this.a = context;
        this.e = gjVar;
    }

    public boolean a() {
        return this.c != null;
    }

    protected void b() {
        try {
            synchronized (this.b) {
                this.b.clear();
            }
            if (this.c != null) {
                this.c.close();
            }
        } catch (Throwable th) {
            hv.a(th, "BaseLoader", "releaseDexFile()");
        }
    }
}
