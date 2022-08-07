package com.loc;

import android.content.Context;
import dalvik.system.DexFile;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: BaseClassLoader.java */
/* loaded from: classes2.dex */
public abstract class av extends ClassLoader {
    protected final Context a;
    protected final Map<String, Class<?>> b = new HashMap();
    protected DexFile c = null;
    volatile boolean d = true;
    protected s e;
    protected String f;

    public av(Context context, s sVar) {
        super(context.getClassLoader());
        this.a = context;
        this.e = sVar;
    }

    public final boolean a() {
        return this.c != null;
    }

    protected final void b() {
        try {
            synchronized (this.b) {
                this.b.clear();
            }
            if (this.c != null) {
                this.c.close();
            }
        } catch (Throwable th) {
            w.a(th, "BaseClassLoader", "releaseDexFile()");
        }
    }
}
