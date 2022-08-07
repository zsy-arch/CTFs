package com.amap.api.col;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

/* compiled from: NativeBufferPool.java */
/* loaded from: classes.dex */
public class dm extends eb<a> {
    private a b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: NativeBufferPool.java */
    /* loaded from: classes.dex */
    public static final class a extends ea<a> {
        ByteBuffer a;
        ShortBuffer b;
        FloatBuffer c;
        IntBuffer d;
        int e;

        a() {
        }

        void a(int i) {
            if (i < 32768) {
                i = 32768;
            }
            this.a = ByteBuffer.allocateDirect(i).order(ByteOrder.nativeOrder());
            this.e = i;
            this.b = null;
            this.d = null;
            this.c = null;
        }
    }

    public a a(int i) {
        a aVar;
        a aVar2 = (a) this.a;
        if (aVar2 == null) {
            aVar = new a();
        } else {
            this.a = aVar2.f;
            aVar2.f = null;
            aVar = aVar2;
        }
        if (aVar.e < i) {
            aVar.a(i);
        }
        this.b = (a) ea.a(this.b, aVar);
        return aVar;
    }

    public void a() {
        this.b = b((dm) this.b);
    }

    public ShortBuffer b(int i) {
        a a2 = a(i * 2);
        if (a2.b == null) {
            a2.a.clear();
            a2.b = a2.a.asShortBuffer();
        } else {
            a2.b.clear();
        }
        return a2.b;
    }

    public FloatBuffer c(int i) {
        a a2 = a(i * 4);
        if (a2.c == null) {
            a2.a.clear();
            a2.c = a2.a.asFloatBuffer();
        } else {
            a2.c.clear();
        }
        a2.c.clear();
        return a2.c;
    }
}
