package com.amap.api.services.a;

import android.content.Context;
import dalvik.system.DexFile;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: BaseClassLoader.java */
/* loaded from: classes.dex */
public abstract class cg extends ClassLoader {
    protected final Context a;
    protected final Map<String, Class<?>> b = new HashMap();
    protected DexFile c = null;
    volatile boolean d = true;
    protected be e;
    protected String f;

    public cg(Context context, be beVar, boolean z) {
        super(context.getClassLoader());
        this.a = context;
        this.e = beVar;
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
            co.a(th, "BaseClassLoader", "releaseDexFile()");
        }
    }
}
