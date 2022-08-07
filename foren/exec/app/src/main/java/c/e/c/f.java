package c.e.c;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.FontVariationAxis;
import c.a.a.C;
import c.e.b.a.c;
import c.e.b.a.d;
import com.tencent.smtt.sdk.TbsListener;
import e.a.a.a.a;
import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public class f extends d {

    /* renamed from: a */
    public final Class f782a;

    /* renamed from: b */
    public final Constructor f783b;

    /* renamed from: c */
    public final Method f784c;

    /* renamed from: d */
    public final Method f785d;

    /* renamed from: e */
    public final Method f786e;
    public final Method f;
    public final Method g;

    public f() {
        Method method;
        Method method2;
        Method method3;
        Method method4;
        Method method5;
        Class<?> cls;
        Constructor<?> constructor = null;
        try {
            cls = Class.forName("android.graphics.FontFamily");
            constructor = cls.getConstructor(new Class[0]);
            method3 = a((Class) cls);
            method2 = b((Class) cls);
            method = cls.getMethod("freeze", new Class[0]);
            method5 = cls.getMethod("abortCreation", new Class[0]);
            method4 = c((Class) cls);
        } catch (ClassNotFoundException | NoSuchMethodException e2) {
            StringBuilder a2 = a.a("Unable to collect necessary methods for class ");
            a2.append(e2.getClass().getName());
            a2.toString();
            cls = null;
            method5 = null;
            method4 = null;
            method3 = null;
            method2 = null;
            method = null;
        }
        this.f782a = cls;
        this.f783b = constructor;
        this.f784c = method3;
        this.f785d = method2;
        this.f786e = method;
        this.f = method5;
        this.g = method4;
    }

    public final boolean a() {
        Method method = this.f784c;
        return this.f784c != null;
    }

    public final Object b() {
        try {
            return this.f783b.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e2) {
            throw new RuntimeException(e2);
        }
    }

    public final boolean c(Object obj) {
        try {
            return ((Boolean) this.f786e.invoke(obj, new Object[0])).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw new RuntimeException(e2);
        }
    }

    public final boolean a(Context context, Object obj, String str, int i, int i2, int i3, FontVariationAxis[] fontVariationAxisArr) {
        try {
            return ((Boolean) this.f784c.invoke(obj, context.getAssets(), str, 0, false, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), fontVariationAxisArr)).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw new RuntimeException(e2);
        }
    }

    public Typeface b(Object obj) {
        try {
            Object newInstance = Array.newInstance(this.f782a, 1);
            Array.set(newInstance, 0, obj);
            return (Typeface) this.g.invoke(null, newInstance, -1, -1);
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw new RuntimeException(e2);
        }
    }

    public Method c(Class cls) {
        Class cls2 = Integer.TYPE;
        Method declaredMethod = Typeface.class.getDeclaredMethod("createFromFamiliesWithDefault", Array.newInstance(cls, 1).getClass(), cls2, cls2);
        declaredMethod.setAccessible(true);
        return declaredMethod;
    }

    public final void a(Object obj) {
        try {
            this.f.invoke(obj, new Object[0]);
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw new RuntimeException(e2);
        }
    }

    public Method b(Class cls) {
        Class<?> cls2 = Integer.TYPE;
        return cls.getMethod("addFontFromBuffer", ByteBuffer.class, cls2, FontVariationAxis[].class, cls2, cls2);
    }

    @Override // c.e.c.i
    public Typeface a(Context context, c cVar, Resources resources, int i) {
        if (!a()) {
            d[] dVarArr = cVar.f750a;
            int i2 = (i & 1) == 0 ? TbsListener.ErrorCode.INFO_CODE_BASE : 700;
            boolean z = (i & 2) != 0;
            d dVar = null;
            int i3 = Integer.MAX_VALUE;
            for (d dVar2 : dVarArr) {
                int abs = (Math.abs(dVar2.f752b - i2) * 2) + (dVar2.f753c == z ? 0 : 1);
                if (dVar == null || i3 > abs) {
                    dVar = dVar2;
                    i3 = abs;
                }
            }
            if (dVar == null) {
                return null;
            }
            return c.a(context, resources, dVar.f, dVar.f751a, i);
        }
        Object b2 = b();
        d[] dVarArr2 = cVar.f750a;
        for (d dVar3 : dVarArr2) {
            if (!a(context, b2, dVar3.f751a, dVar3.f755e, dVar3.f752b, dVar3.f753c ? 1 : 0, FontVariationAxis.fromFontVariationSettings(dVar3.f754d))) {
                a(b2);
                return null;
            }
        }
        if (!c(b2)) {
            return null;
        }
        return b(b2);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x004a A[Catch: Throwable -> 0x004e, TRY_ENTER, TRY_LEAVE, TryCatch #6 {IOException -> 0x0057, blocks: (B:8:0x0014, B:11:0x0020, B:14:0x003d, B:23:0x0053, B:24:0x0056, B:22:0x004a), top: B:52:0x0014 }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0053 A[Catch: IOException -> 0x0057, TryCatch #6 {IOException -> 0x0057, blocks: (B:8:0x0014, B:11:0x0020, B:14:0x003d, B:23:0x0053, B:24:0x0056, B:22:0x004a), top: B:52:0x0014 }] */
    @Override // c.e.c.d, c.e.c.i
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.graphics.Typeface a(android.content.Context r12, android.os.CancellationSignal r13, c.e.e.f.b[] r14, int r15) {
        /*
            r11 = this;
            int r0 = r14.length
            r1 = 1
            r2 = 0
            if (r0 >= r1) goto L_0x0006
            return r2
        L_0x0006:
            boolean r0 = r11.a()
            if (r0 != 0) goto L_0x0058
            c.e.e.f$b r14 = r11.a(r14, r15)
            android.content.ContentResolver r12 = r12.getContentResolver()
            android.net.Uri r15 = r14.f806a     // Catch: IOException -> 0x0057
            java.lang.String r0 = "r"
            android.os.ParcelFileDescriptor r12 = r12.openFileDescriptor(r15, r0, r13)     // Catch: IOException -> 0x0057
            if (r12 != 0) goto L_0x0024
            if (r12 == 0) goto L_0x0023
            r12.close()     // Catch: IOException -> 0x0057
        L_0x0023:
            return r2
        L_0x0024:
            android.graphics.Typeface$Builder r13 = new android.graphics.Typeface$Builder     // Catch: Throwable -> 0x0045, all -> 0x0041
            java.io.FileDescriptor r15 = r12.getFileDescriptor()     // Catch: Throwable -> 0x0045, all -> 0x0041
            r13.<init>(r15)     // Catch: Throwable -> 0x0045, all -> 0x0041
            int r15 = r14.f808c     // Catch: Throwable -> 0x0045, all -> 0x0041
            android.graphics.Typeface$Builder r13 = r13.setWeight(r15)     // Catch: Throwable -> 0x0045, all -> 0x0041
            boolean r14 = r14.f809d     // Catch: Throwable -> 0x0045, all -> 0x0041
            android.graphics.Typeface$Builder r13 = r13.setItalic(r14)     // Catch: Throwable -> 0x0045, all -> 0x0041
            android.graphics.Typeface r13 = r13.build()     // Catch: Throwable -> 0x0045, all -> 0x0041
            r12.close()     // Catch: IOException -> 0x0057
            return r13
        L_0x0041:
            r13 = move-exception
            r14 = r13
            r13 = r2
            goto L_0x0048
        L_0x0045:
            r13 = move-exception
            throw r13     // Catch: all -> 0x0047
        L_0x0047:
            r14 = move-exception
        L_0x0048:
            if (r13 == 0) goto L_0x0053
            r12.close()     // Catch: Throwable -> 0x004e
            goto L_0x0056
        L_0x004e:
            r12 = move-exception
            r13.addSuppressed(r12)     // Catch: IOException -> 0x0057
            goto L_0x0056
        L_0x0053:
            r12.close()     // Catch: IOException -> 0x0057
        L_0x0056:
            throw r14     // Catch: IOException -> 0x0057
        L_0x0057:
            return r2
        L_0x0058:
            java.util.Map r12 = c.e.e.f.a(r12, r14, r13)
            java.lang.Object r13 = r11.b()
            int r0 = r14.length
            r3 = 0
            r4 = 0
            r5 = 0
        L_0x0064:
            if (r4 >= r0) goto L_0x00b4
            r6 = r14[r4]
            android.net.Uri r7 = r6.f806a
            java.lang.Object r7 = r12.get(r7)
            java.nio.ByteBuffer r7 = (java.nio.ByteBuffer) r7
            if (r7 != 0) goto L_0x0073
            goto L_0x00a8
        L_0x0073:
            int r5 = r6.f807b
            int r8 = r6.f808c
            boolean r6 = r6.f809d
            java.lang.reflect.Method r9 = r11.f785d     // Catch: IllegalAccessException -> 0x00ad, InvocationTargetException -> 0x00ab
            r10 = 5
            java.lang.Object[] r10 = new java.lang.Object[r10]     // Catch: IllegalAccessException -> 0x00ad, InvocationTargetException -> 0x00ab
            r10[r3] = r7     // Catch: IllegalAccessException -> 0x00ad, InvocationTargetException -> 0x00ab
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch: IllegalAccessException -> 0x00ad, InvocationTargetException -> 0x00ab
            r10[r1] = r5     // Catch: IllegalAccessException -> 0x00ad, InvocationTargetException -> 0x00ab
            r5 = 2
            r10[r5] = r2     // Catch: IllegalAccessException -> 0x00ad, InvocationTargetException -> 0x00ab
            r5 = 3
            java.lang.Integer r7 = java.lang.Integer.valueOf(r8)     // Catch: IllegalAccessException -> 0x00ad, InvocationTargetException -> 0x00ab
            r10[r5] = r7     // Catch: IllegalAccessException -> 0x00ad, InvocationTargetException -> 0x00ab
            r5 = 4
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch: IllegalAccessException -> 0x00ad, InvocationTargetException -> 0x00ab
            r10[r5] = r6     // Catch: IllegalAccessException -> 0x00ad, InvocationTargetException -> 0x00ab
            java.lang.Object r5 = r9.invoke(r13, r10)     // Catch: IllegalAccessException -> 0x00ad, InvocationTargetException -> 0x00ab
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch: IllegalAccessException -> 0x00ad, InvocationTargetException -> 0x00ab
            boolean r5 = r5.booleanValue()     // Catch: IllegalAccessException -> 0x00ad, InvocationTargetException -> 0x00ab
            if (r5 != 0) goto L_0x00a7
            r11.a(r13)
            return r2
        L_0x00a7:
            r5 = 1
        L_0x00a8:
            int r4 = r4 + 1
            goto L_0x0064
        L_0x00ab:
            r12 = move-exception
            goto L_0x00ae
        L_0x00ad:
            r12 = move-exception
        L_0x00ae:
            java.lang.RuntimeException r13 = new java.lang.RuntimeException
            r13.<init>(r12)
            throw r13
        L_0x00b4:
            if (r5 != 0) goto L_0x00ba
            r11.a(r13)
            return r2
        L_0x00ba:
            boolean r12 = r11.c(r13)
            if (r12 != 0) goto L_0x00c1
            return r2
        L_0x00c1:
            android.graphics.Typeface r12 = r11.b(r13)
            android.graphics.Typeface r12 = android.graphics.Typeface.create(r12, r15)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: c.e.c.f.a(android.content.Context, android.os.CancellationSignal, c.e.e.f$b[], int):android.graphics.Typeface");
    }

    @Override // c.e.c.i
    public Typeface a(Context context, Resources resources, int i, String str, int i2) {
        Typeface typeface = null;
        if (!a()) {
            File a2 = C.a(context);
            if (a2 != null) {
                try {
                    if (C.a(a2, resources, i)) {
                        typeface = Typeface.createFromFile(a2.getPath());
                    }
                } catch (RuntimeException unused) {
                } catch (Throwable th) {
                    a2.delete();
                    throw th;
                }
                a2.delete();
            }
            return typeface;
        }
        Object b2 = b();
        if (!a(context, b2, str, 0, -1, -1, null)) {
            a(b2);
            return null;
        } else if (!c(b2)) {
            return null;
        } else {
            return b(b2);
        }
    }

    public Method a(Class cls) {
        Class<?> cls2 = Integer.TYPE;
        return cls.getMethod("addFontFromAssetManager", AssetManager.class, String.class, Integer.TYPE, Boolean.TYPE, cls2, cls2, cls2, FontVariationAxis[].class);
    }
}
