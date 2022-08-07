package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Parcelable;
import androidx.versionedparcelable.CustomVersionedParcelable;
import e.a.a.a.a;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;

/* loaded from: classes.dex */
public class IconCompat extends CustomVersionedParcelable {

    /* renamed from: a */
    public static final PorterDuff.Mode f206a = PorterDuff.Mode.SRC_IN;

    /* renamed from: b */
    public int f207b;

    /* renamed from: c */
    public Object f208c;

    /* renamed from: d */
    public byte[] f209d;

    /* renamed from: e */
    public Parcelable f210e;
    public int f;
    public int g;
    public ColorStateList h = null;
    public PorterDuff.Mode i = f206a;
    public String j;

    public int a() {
        int i;
        if (this.f207b == -1 && (i = Build.VERSION.SDK_INT) >= 23) {
            Icon icon = (Icon) this.f208c;
            if (i >= 28) {
                return icon.getResId();
            }
            try {
                return ((Integer) icon.getClass().getMethod("getResId", new Class[0]).invoke(icon, new Object[0])).intValue();
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
                return 0;
            }
        } else if (this.f207b == 2) {
            return this.f;
        } else {
            throw new IllegalStateException(a.a("called getResId() on ", this));
        }
    }

    public String b() {
        int i;
        if (this.f207b == -1 && (i = Build.VERSION.SDK_INT) >= 23) {
            Icon icon = (Icon) this.f208c;
            if (i >= 28) {
                return icon.getResPackage();
            }
            try {
                return (String) icon.getClass().getMethod("getResPackage", new Class[0]).invoke(icon, new Object[0]);
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
                return null;
            }
        } else if (this.f207b == 2) {
            return ((String) this.f208c).split(":", -1)[0];
        } else {
            throw new IllegalStateException(a.a("called getResPackage() on ", this));
        }
    }

    public void c() {
        this.i = PorterDuff.Mode.valueOf(this.j);
        int i = this.f207b;
        if (i != -1) {
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        this.f208c = this.f209d;
                        return;
                    } else if (i != 4) {
                        if (i != 5) {
                            return;
                        }
                    }
                }
                this.f208c = new String(this.f209d, Charset.forName("UTF-16"));
                return;
            }
            Parcelable parcelable = this.f210e;
            if (parcelable != null) {
                this.f208c = parcelable;
                return;
            }
            byte[] bArr = this.f209d;
            this.f208c = bArr;
            this.f207b = 3;
            this.f = 0;
            this.g = bArr.length;
            return;
        }
        Parcelable parcelable2 = this.f210e;
        if (parcelable2 != null) {
            this.f208c = parcelable2;
            return;
        }
        throw new IllegalArgumentException("Invalid icon");
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0042, code lost:
        if (r1 != 5) goto L_0x00b1;
     */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00c5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String toString() {
        /*
            r7 = this;
            int r0 = r7.f207b
            r1 = -1
            if (r0 != r1) goto L_0x000c
            java.lang.Object r0 = r7.f208c
            java.lang.String r0 = java.lang.String.valueOf(r0)
            return r0
        L_0x000c:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Icon(typ="
            r0.<init>(r1)
            int r1 = r7.f207b
            r2 = 5
            r3 = 4
            r4 = 3
            r5 = 2
            r6 = 1
            if (r1 == r6) goto L_0x0033
            if (r1 == r5) goto L_0x0030
            if (r1 == r4) goto L_0x002d
            if (r1 == r3) goto L_0x002a
            if (r1 == r2) goto L_0x0027
            java.lang.String r1 = "UNKNOWN"
            goto L_0x0035
        L_0x0027:
            java.lang.String r1 = "BITMAP_MASKABLE"
            goto L_0x0035
        L_0x002a:
            java.lang.String r1 = "URI"
            goto L_0x0035
        L_0x002d:
            java.lang.String r1 = "DATA"
            goto L_0x0035
        L_0x0030:
            java.lang.String r1 = "RESOURCE"
            goto L_0x0035
        L_0x0033:
            java.lang.String r1 = "BITMAP"
        L_0x0035:
            r0.append(r1)
            int r1 = r7.f207b
            if (r1 == r6) goto L_0x0091
            if (r1 == r5) goto L_0x0069
            if (r1 == r4) goto L_0x0050
            if (r1 == r3) goto L_0x0045
            if (r1 == r2) goto L_0x0091
            goto L_0x00b1
        L_0x0045:
            java.lang.String r1 = " uri="
            r0.append(r1)
            java.lang.Object r1 = r7.f208c
            r0.append(r1)
            goto L_0x00b1
        L_0x0050:
            java.lang.String r1 = " len="
            r0.append(r1)
            int r1 = r7.f
            r0.append(r1)
            int r1 = r7.g
            if (r1 == 0) goto L_0x00b1
            java.lang.String r1 = " off="
            r0.append(r1)
            int r1 = r7.g
            r0.append(r1)
            goto L_0x00b1
        L_0x0069:
            java.lang.String r1 = " pkg="
            r0.append(r1)
            java.lang.String r1 = r7.b()
            r0.append(r1)
            java.lang.String r1 = " id="
            r0.append(r1)
            java.lang.Object[] r1 = new java.lang.Object[r6]
            r2 = 0
            int r3 = r7.a()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r1[r2] = r3
            java.lang.String r2 = "0x%08x"
            java.lang.String r1 = java.lang.String.format(r2, r1)
            r0.append(r1)
            goto L_0x00b1
        L_0x0091:
            java.lang.String r1 = " size="
            r0.append(r1)
            java.lang.Object r1 = r7.f208c
            android.graphics.Bitmap r1 = (android.graphics.Bitmap) r1
            int r1 = r1.getWidth()
            r0.append(r1)
            java.lang.String r1 = "x"
            r0.append(r1)
            java.lang.Object r1 = r7.f208c
            android.graphics.Bitmap r1 = (android.graphics.Bitmap) r1
            int r1 = r1.getHeight()
            r0.append(r1)
        L_0x00b1:
            android.content.res.ColorStateList r1 = r7.h
            if (r1 == 0) goto L_0x00bf
            java.lang.String r1 = " tint="
            r0.append(r1)
            android.content.res.ColorStateList r1 = r7.h
            r0.append(r1)
        L_0x00bf:
            android.graphics.PorterDuff$Mode r1 = r7.i
            android.graphics.PorterDuff$Mode r2 = androidx.core.graphics.drawable.IconCompat.f206a
            if (r1 == r2) goto L_0x00cf
            java.lang.String r1 = " mode="
            r0.append(r1)
            android.graphics.PorterDuff$Mode r1 = r7.i
            r0.append(r1)
        L_0x00cf:
            java.lang.String r1 = ")"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.graphics.drawable.IconCompat.toString():java.lang.String");
    }

    public void a(boolean z) {
        this.j = this.i.name();
        int i = this.f207b;
        if (i != -1) {
            if (i != 1) {
                if (i == 2) {
                    this.f209d = ((String) this.f208c).getBytes(Charset.forName("UTF-16"));
                    return;
                } else if (i == 3) {
                    this.f209d = (byte[]) this.f208c;
                    return;
                } else if (i == 4) {
                    this.f209d = this.f208c.toString().getBytes(Charset.forName("UTF-16"));
                    return;
                } else if (i != 5) {
                    return;
                }
            }
            if (z) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ((Bitmap) this.f208c).compress(Bitmap.CompressFormat.PNG, 90, byteArrayOutputStream);
                this.f209d = byteArrayOutputStream.toByteArray();
                return;
            }
            this.f210e = (Parcelable) this.f208c;
        } else if (!z) {
            this.f210e = (Parcelable) this.f208c;
        } else {
            throw new IllegalArgumentException("Can't serialize Icon created with IconCompat#createFromIcon");
        }
    }
}
