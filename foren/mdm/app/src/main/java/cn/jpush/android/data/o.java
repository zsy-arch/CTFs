package cn.jpush.android.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import cn.jpush.android.util.ac;

/* loaded from: classes.dex */
public final class o {
    public static final String[] a;
    private static o e;
    private static Object f;
    private static final String[] z;
    private Context b;
    private p c;
    private SQLiteDatabase d;

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0025, code lost:
        if (r5 != 0) goto L_0x002b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0027, code lost:
        r5 = r1;
        r8 = r6;
        r6 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x002b, code lost:
        r5 = r5;
        r1 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002d, code lost:
        if (r5 > r6) goto L_0x0012;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x002f, code lost:
        r1 = new java.lang.String(r1).intern();
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0038, code lost:
        switch(r0) {
            case 0: goto L_0x0043;
            case 1: goto L_0x004b;
            case 2: goto L_0x0053;
            case 3: goto L_0x005b;
            case 4: goto L_0x0063;
            case 5: goto L_0x006b;
            case 6: goto L_0x0073;
            case 7: goto L_0x007c;
            case 8: goto L_0x0086;
            case 9: goto L_0x0091;
            case 10: goto L_0x009c;
            case 11: goto L_0x00a7;
            case 12: goto L_0x00b2;
            case 13: goto L_0x00bd;
            case 14: goto L_0x00c8;
            case 15: goto L_0x00d3;
            case 16: goto L_0x00de;
            case 17: goto L_0x00e9;
            case 18: goto L_0x00f4;
            case 19: goto L_0x00ff;
            case 20: goto L_0x0128;
            default: goto L_0x003b;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003b, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "\n\f\u001b8l\u000b\f\u001b f\u0000Ec";
        r0 = 0;
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0043, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "\n\f\u001b?l\r\u0019(";
        r0 = 1;
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004b, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "\n\u001d(.`\rX\u0017\u001eNQ";
        r0 = 2;
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0053, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\n\f\u001b-b\u0010\u0014!/";
        r0 = 3;
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005b, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "PX\"9l\u0014X.;v\n\u0010\u001b8w\u0018\f-8w\u0010\u001b7";
        r0 = 4;
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0063, code lost:
        r3[r2] = r1;
        r2 = 6;
        r1 = "\n\u001d(.`\rXnke\u000b\u0017)ki\t\r7#\\\n\f%?j\n\f-(pY\u000f,.q\u001cX7?\\\u001f\u0019-'f\u001dXzk3YX+9g\u001c\nd)zY\u000b0\u0014e\u0018\u0011(.gY\u001c!8`Y\u0014-&j\rXw";
        r0 = 5;
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006b, code lost:
        r3[r2] = r1;
        r2 = 7;
        r1 = "\n\u001d(.`\rX\u0007\u0004V7,l8w&\u000b+9w&\u0013!2*Y\u001e6$nY\u00124>p\u0011'7?b\r\u00117?j\u001a\u000bd<k\u001c\n!kp\r'7$q\r'/.zD_";
        r0 = 6;
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0073, code lost:
        r3[r2] = r1;
        r2 = '\b';
        r1 = "\u0016\b!%#\u0016\b!%T\u000b\u00110*a\u0015\u001d\u0000*w\u0018\u001a%8fY\u001e%\"oY\u001d~";
        r0 = 7;
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x007c, code lost:
        r3[r2] = r1;
        r2 = '\t';
        r1 = "\n\f\u001b%f\r";
        r0 = '\b';
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0086, code lost:
        r3[r2] = r1;
        r2 = '\n';
        r1 = "\n\f\u001b(l\f\u00160\u00140&It";
        r0 = '\t';
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0091, code lost:
        r3[r2] = r1;
        r2 = 11;
        r1 = "\n\f\u001b8l\u000b\f\u001b f\u0000";
        r0 = '\n';
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x009c, code lost:
        r3[r2] = r1;
        r2 = '\f';
        r1 = "\n\f\u001b'l\u001a\u0019(\u0014g\u0017\u000b";
        r0 = 11;
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00a7, code lost:
        r3[r2] = r1;
        r2 = '\r';
        r1 = "\n\f\u001b(l\f\u00160\u00142I";
        r0 = '\f';
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00b2, code lost:
        r3[r2] = r1;
        r2 = 14;
        r1 = "\n\f\u001b(l\u0017\u0016\u001b\"s";
        r0 = '\r';
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00bd, code lost:
        r3[r2] = r1;
        r2 = 15;
        r1 = "\n\f\u001b8l\f\n'.";
        r0 = 14;
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00c8, code lost:
        r3[r2] = r1;
        r2 = 16;
        r1 = "\n\f\u001b(l\f\u00160\u00142";
        r0 = 15;
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00d3, code lost:
        r3[r2] = r1;
        r2 = 17;
        r1 = "\n\f\u001b(l\f\u00160\u00142&K";
        r0 = 16;
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00de, code lost:
        r3[r2] = r1;
        r2 = 18;
        r1 = "\u0016\b!%#+\u001d%/b\u001b\u0014!\u000fb\r\u0019\u0006*p\u001cX\"*j\u0015X!q";
        r0 = 17;
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00e9, code lost:
        r3[r2] = r1;
        r2 = 19;
        r1 = "\u001d\u001d(.w\u001cX\"9l\u0014X.;v\n\u0010\u001b8w\u0018\f-8w\u0010\u001b7";
        r0 = 18;
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00f4, code lost:
        r3[r2] = r1;
        r2 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
        r1 = "\n\u001d(.`\rXnke\u000b\u0017)ki\t\r7#\\\n\f%?j\n\f-(pY\u000f,.q\u001cX7?\\\r\u00170*oYFd{#\u0018\u0016 kp\r'\"*j\u0015\u001d k>YHdkl\u000b\u001c!9#\u001b\u0001d8w&\f+?b\u0015X .p\u001aX(\"n\u0010\fdx";
        r0 = 19;
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00ff, code lost:
        r3[r2] = r1;
        cn.jpush.android.data.o.z = r4;
        r3 = new java.lang.String[12];
        r3[0] = cn.jpush.android.data.o.z[11];
        r2 = 1;
        r1 = "&\u0011 ";
        r0 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
        r4 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0118, code lost:
        r9 = 'y';
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x011c, code lost:
        r9 = 'x';
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0120, code lost:
        r9 = 'D';
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0124, code lost:
        r9 = 'K';
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0128, code lost:
        r3[r2] = r1;
        r4[2] = cn.jpush.android.data.o.z[9];
        r4[3] = cn.jpush.android.data.o.z[14];
        r4[4] = cn.jpush.android.data.o.z[12];
        r4[5] = cn.jpush.android.data.o.z[15];
        r4[6] = cn.jpush.android.data.o.z[4];
        r4[7] = cn.jpush.android.data.o.z[2];
        r4[8] = cn.jpush.android.data.o.z[16];
        r4[9] = cn.jpush.android.data.o.z[17];
        r4[10] = cn.jpush.android.data.o.z[10];
        r4[11] = cn.jpush.android.data.o.z[13];
        cn.jpush.android.data.o.a = r4;
        cn.jpush.android.data.o.f = new java.lang.Object();
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x018f, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0010, code lost:
        if (r5 <= 1) goto L_0x0012;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0012, code lost:
        r8 = r6;
        r5 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0017, code lost:
        r10 = r5[r6];
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x001b, code lost:
        switch((r8 % 5)) {
            case 0: goto L_0x0118;
            case 1: goto L_0x011c;
            case 2: goto L_0x0120;
            case 3: goto L_0x0124;
            default: goto L_0x001e;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
        r9 = 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x001f, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            Method dump skipped, instructions count: 458
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.data.o.<clinit>():void");
    }

    private o(Context context) {
        this.b = context;
        this.c = new p(context);
    }

    public static o a(Context context) {
        synchronized (f) {
            if (e == null) {
                e = new o(context);
            }
        }
        return e;
    }

    private synchronized boolean e() {
        boolean z2;
        z2 = false;
        try {
            this.d = this.c.getWritableDatabase();
            z2 = true;
        } catch (Exception e2) {
            new StringBuilder(z[8]).append(e2.getMessage());
            ac.e();
        }
        return z2;
    }

    private synchronized boolean f() {
        boolean z2;
        z2 = false;
        try {
            this.d = this.c.getReadableDatabase();
            z2 = true;
        } catch (Exception e2) {
            new StringBuilder(z[18]).append(e2.getMessage());
            ac.e();
        }
        return z2;
    }

    public final synchronized long a(String str, String str2, String str3, String str4, String str5, int i, int i2, int i3, int i4, int i5, int i6) {
        long j;
        j = 0;
        ContentValues contentValues = new ContentValues();
        contentValues.put(z[11], str);
        contentValues.put(z[9], str2);
        contentValues.put(z[14], str3);
        contentValues.put(z[12], str4);
        contentValues.put(z[15], str5);
        contentValues.put(z[4], Integer.valueOf(i));
        contentValues.put(z[2], (Integer) 1);
        contentValues.put(z[16], Integer.valueOf(i3));
        contentValues.put(z[17], Integer.valueOf(i4));
        contentValues.put(z[10], Integer.valueOf(i5));
        contentValues.put(z[13], (Integer) 0);
        try {
            j = this.d.insert(z[0], null, contentValues);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return j;
    }

    public final synchronized q a(Cursor cursor) {
        q qVar;
        if (cursor != null) {
            if (cursor.getCount() != 0) {
                qVar = new q();
                try {
                    qVar.a(cursor.getString(1));
                    qVar.b(cursor.getString(2));
                    qVar.c(cursor.getString(3));
                    qVar.d(cursor.getString(4));
                    qVar.e(cursor.getString(5));
                    qVar.a(cursor.getInt(6));
                    qVar.b(cursor.getInt(7));
                    qVar.c(cursor.getInt(8));
                    qVar.d(cursor.getInt(9));
                    qVar.e(cursor.getInt(10));
                    qVar.f(cursor.getInt(11));
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                qVar.toString();
                ac.c();
            }
        }
        ac.b();
        qVar = null;
        return qVar;
    }

    public final synchronized void a() {
        if (this.d != null) {
            this.d.close();
        }
    }

    public final synchronized boolean a(String str) {
        Cursor cursor = null;
        boolean z2 = false;
        synchronized (this) {
            try {
                cursor = this.d.rawQuery(z[7] + str + "'", null);
                if (cursor != null) {
                    cursor.moveToFirst();
                    if (cursor.getInt(0) != 0) {
                        z2 = true;
                    }
                }
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        return z2;
    }

    public final synchronized boolean a(boolean z2) {
        return z2 ? e() : f();
    }

    public final synchronized int b(boolean z2) {
        Cursor cursor = null;
        int i = 0;
        synchronized (this) {
            String str = z[4];
            if (z2) {
                str = z[2];
            }
            try {
                cursor = this.d.rawQuery(z[3] + str + z[5], null);
                if (cursor != null) {
                    cursor.moveToFirst();
                    i = cursor.getInt(0);
                }
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        return i;
    }

    public final synchronized Cursor b(String str) {
        Exception e2;
        Cursor cursor = null;
        synchronized (this) {
            try {
                cursor = this.d.query(true, z[0], a, z[1] + str + "'", null, null, null, null, null);
                if (cursor != null) {
                    try {
                        cursor.moveToFirst();
                    } catch (Exception e3) {
                        e2 = e3;
                        e2.printStackTrace();
                        return cursor;
                    }
                }
            } catch (Exception e4) {
                e2 = e4;
            }
        }
        return cursor;
    }

    public final synchronized void b() {
        try {
            this.d.execSQL(z[19]);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public final synchronized boolean b(String str, String str2, String str3, String str4, String str5, int i, int i2, int i3, int i4, int i5, int i6) {
        boolean z2;
        String str6 = z[1] + str + "'";
        ContentValues contentValues = new ContentValues();
        contentValues.put(z[11], str);
        contentValues.put(z[9], str2);
        contentValues.put(z[14], str3);
        contentValues.put(z[12], str4);
        contentValues.put(z[15], str5);
        contentValues.put(z[4], Integer.valueOf(i));
        contentValues.put(z[2], Integer.valueOf(i2));
        contentValues.put(z[16], Integer.valueOf(i3));
        contentValues.put(z[17], Integer.valueOf(i4));
        contentValues.put(z[10], Integer.valueOf(i5));
        contentValues.put(z[13], Integer.valueOf(i6));
        z2 = false;
        try {
            z2 = this.d.update(z[0], contentValues, str6, null) > 0;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return z2;
    }

    public final synchronized Cursor c() {
        Exception e2;
        Cursor cursor = null;
        synchronized (this) {
            try {
                cursor = this.d.rawQuery(z[6], null);
                if (cursor != null) {
                    try {
                        cursor.moveToFirst();
                    } catch (Exception e3) {
                        e2 = e3;
                        e2.printStackTrace();
                        return cursor;
                    }
                }
            } catch (Exception e4) {
                e2 = e4;
            }
        }
        return cursor;
    }

    public final synchronized Cursor d() {
        Exception e2;
        Cursor cursor = null;
        synchronized (this) {
            try {
                cursor = this.d.rawQuery(z[20], null);
                if (cursor != null) {
                    try {
                        cursor.moveToFirst();
                    } catch (Exception e3) {
                        e2 = e3;
                        e2.printStackTrace();
                        return cursor;
                    }
                }
            } catch (Exception e4) {
                e2 = e4;
            }
        }
        return cursor;
    }
}
