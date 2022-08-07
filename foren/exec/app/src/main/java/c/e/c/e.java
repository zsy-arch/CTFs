package c.e.c;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.CancellationSignal;
import c.a.a.C;
import c.c.i;
import c.e.e.f;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.List;

/* loaded from: classes.dex */
public class e extends i {

    /* renamed from: a  reason: collision with root package name */
    public static final Class f778a;

    /* renamed from: b  reason: collision with root package name */
    public static final Constructor f779b;

    /* renamed from: c  reason: collision with root package name */
    public static final Method f780c;

    /* renamed from: d  reason: collision with root package name */
    public static final Method f781d;

    static {
        Method method;
        Method method2;
        Class<?> cls;
        Constructor<?> constructor = null;
        try {
            cls = Class.forName("android.graphics.FontFamily");
            constructor = cls.getConstructor(new Class[0]);
            method = cls.getMethod("addFontWeightStyle", ByteBuffer.class, Integer.TYPE, List.class, Integer.TYPE, Boolean.TYPE);
            method2 = Typeface.class.getMethod("createFromFamiliesWithDefault", Array.newInstance(cls, 1).getClass());
        } catch (ClassNotFoundException | NoSuchMethodException e2) {
            e2.getClass().getName();
            cls = null;
            method2 = null;
            method = null;
        }
        f779b = constructor;
        f778a = cls;
        f780c = method;
        f781d = method2;
    }

    public static Object a() {
        try {
            return f779b.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e2) {
            throw new RuntimeException(e2);
        }
    }

    public static boolean a(Object obj, ByteBuffer byteBuffer, int i, int i2, boolean z) {
        try {
            return ((Boolean) f780c.invoke(obj, byteBuffer, Integer.valueOf(i), null, Integer.valueOf(i2), Boolean.valueOf(z))).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw new RuntimeException(e2);
        }
    }

    public static Typeface a(Object obj) {
        try {
            Object newInstance = Array.newInstance(f778a, 1);
            Array.set(newInstance, 0, obj);
            return (Typeface) f781d.invoke(null, newInstance);
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw new RuntimeException(e2);
        }
    }

    @Override // c.e.c.i
    public Typeface a(Context context, CancellationSignal cancellationSignal, f.b[] bVarArr, int i) {
        Object a2 = a();
        i iVar = new i();
        for (f.b bVar : bVarArr) {
            Uri uri = bVar.f806a;
            ByteBuffer byteBuffer = (ByteBuffer) iVar.get(uri);
            if (byteBuffer == null) {
                byteBuffer = C.a(context, cancellationSignal, uri);
                iVar.put(uri, byteBuffer);
            }
            if (!a(a2, byteBuffer, bVar.f807b, bVar.f808c, bVar.f809d)) {
                return null;
            }
        }
        return Typeface.create(a(a2), i);
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0056 A[Catch: IOException -> 0x005a, all -> 0x0071, TryCatch #1 {IOException -> 0x005a, blocks: (B:12:0x0028, B:14:0x003d, B:26:0x0052, B:27:0x0056, B:28:0x0059), top: B:42:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x004c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // c.e.c.i
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.graphics.Typeface a(android.content.Context r17, c.e.b.a.c r18, android.content.res.Resources r19, int r20) {
        /*
            r16 = this;
            java.lang.Object r1 = a()
            r0 = r18
            c.e.b.a.d[] r2 = r0.f750a
            int r3 = r2.length
            r0 = 0
            r4 = 0
        L_0x000b:
            if (r4 >= r3) goto L_0x0076
            r5 = r2[r4]
            int r0 = r5.f
            java.io.File r6 = c.a.a.C.a(r17)
            r7 = 0
            if (r6 != 0) goto L_0x001b
            r8 = r19
            goto L_0x0026
        L_0x001b:
            r8 = r19
            boolean r0 = c.a.a.C.a(r6, r8, r0)     // Catch: all -> 0x0071
            if (r0 != 0) goto L_0x0028
            r6.delete()
        L_0x0026:
            r0 = r7
            goto L_0x005e
        L_0x0028:
            java.io.FileInputStream r9 = new java.io.FileInputStream     // Catch: IOException -> 0x005a, all -> 0x0071
            r9.<init>(r6)     // Catch: IOException -> 0x005a, all -> 0x0071
            java.nio.channels.FileChannel r10 = r9.getChannel()     // Catch: Throwable -> 0x0045, all -> 0x0041
            long r14 = r10.size()     // Catch: Throwable -> 0x0045, all -> 0x0041
            java.nio.channels.FileChannel$MapMode r11 = java.nio.channels.FileChannel.MapMode.READ_ONLY     // Catch: Throwable -> 0x0045, all -> 0x0041
            r12 = 0
            java.nio.MappedByteBuffer r0 = r10.map(r11, r12, r14)     // Catch: Throwable -> 0x0045, all -> 0x0041
            r9.close()     // Catch: IOException -> 0x005a, all -> 0x0071
            goto L_0x005b
        L_0x0041:
            r0 = move-exception
            r11 = r0
            r10 = r7
            goto L_0x004a
        L_0x0045:
            r0 = move-exception
            r10 = r0
            throw r10     // Catch: all -> 0x0048
        L_0x0048:
            r0 = move-exception
            r11 = r0
        L_0x004a:
            if (r10 == 0) goto L_0x0056
            r9.close()     // Catch: Throwable -> 0x0050, all -> 0x0071
            goto L_0x0059
        L_0x0050:
            r0 = move-exception
            r9 = r0
            r10.addSuppressed(r9)     // Catch: IOException -> 0x005a, all -> 0x0071
            goto L_0x0059
        L_0x0056:
            r9.close()     // Catch: IOException -> 0x005a, all -> 0x0071
        L_0x0059:
            throw r11     // Catch: IOException -> 0x005a, all -> 0x0071
        L_0x005a:
            r0 = r7
        L_0x005b:
            r6.delete()
        L_0x005e:
            if (r0 != 0) goto L_0x0061
            return r7
        L_0x0061:
            int r6 = r5.f755e
            int r9 = r5.f752b
            boolean r5 = r5.f753c
            boolean r0 = a(r1, r0, r6, r9, r5)
            if (r0 != 0) goto L_0x006e
            return r7
        L_0x006e:
            int r4 = r4 + 1
            goto L_0x000b
        L_0x0071:
            r0 = move-exception
            r6.delete()
            throw r0
        L_0x0076:
            android.graphics.Typeface r0 = a(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: c.e.c.e.a(android.content.Context, c.e.b.a.c, android.content.res.Resources, int):android.graphics.Typeface");
    }
}
