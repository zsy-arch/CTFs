package cn.jpush.android.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import cn.jpush.android.util.ac;

/* loaded from: classes.dex */
public final class f {
    public static final String[] a;
    private static final String[] z;
    private Context b;
    private g c;
    private SQLiteDatabase d;

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0026, code lost:
        if (r5 != 0) goto L_0x002c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0028, code lost:
        r5 = r1;
        r8 = r6;
        r6 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x002c, code lost:
        r5 = r5;
        r1 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002e, code lost:
        if (r5 > r6) goto L_0x0012;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0030, code lost:
        r1 = new java.lang.String(r1).intern();
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0039, code lost:
        switch(r0) {
            case 0: goto L_0x0044;
            case 1: goto L_0x004c;
            case 2: goto L_0x0054;
            case 3: goto L_0x005c;
            case 4: goto L_0x0064;
            case 5: goto L_0x006c;
            case 6: goto L_0x0074;
            case 7: goto L_0x007d;
            case 8: goto L_0x0087;
            case 9: goto L_0x0092;
            case 10: goto L_0x009d;
            case 11: goto L_0x00a8;
            case 12: goto L_0x00b3;
            case 13: goto L_0x00be;
            case 14: goto L_0x00c9;
            case 15: goto L_0x00d4;
            case 16: goto L_0x00f4;
            default: goto L_0x003c;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003c, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "\u000e\u001bp\u0012g\u0016\u0007N";
        r0 = 0;
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0044, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "\u000e\u001bp\u001e{_";
        r0 = 1;
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004c, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "\u000e\u001bp\u0005z\u000f\u001aY\u0012";
        r0 = 2;
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0054, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\u000e\u001bp\u0014p\u0017\u001b[";
        r0 = 3;
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005c, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "\u000e\u001bp\u0003m\u000b\u0012H\u0012m=\u0001F\u001az";
        r0 = 4;
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0064, code lost:
        r3[r2] = r1;
        r2 = 6;
        r1 = "\u000e\u001bp\u001e{";
        r0 = 5;
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006c, code lost:
        r3[r2] = r1;
        r2 = 7;
        r1 = "\u000e\u001bp\u0003f\u0012\u0010";
        r0 = 6;
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0074, code lost:
        r3[r2] = r1;
        r2 = '\b';
        r1 = "\u0016*C\u0018|\u0003\u0019A\u0018k\u000b\u0013F\u0014~\u0016\u001c@\u0019";
        r0 = 7;
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x007d, code lost:
        r3[r2] = r1;
        r2 = '\t';
        r1 = "\u000e\u001bp\u0016{\u0006*[\u001er\u0007";
        r0 = '\b';
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0087, code lost:
        r3[r2] = r1;
        r2 = '\n';
        r1 = "B\u0014A\u0013?\u000e\u001bp\u0003m\u000b\u0012H\u0012m=\u0001F\u001az";
        r0 = '\t';
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0092, code lost:
        r3[r2] = r1;
        r2 = 11;
        r1 = "\u000e\u001bp\u0014p\u0017\u001b[J";
        r0 = '\n';
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x009d, code lost:
        r3[r2] = r1;
        r2 = '\f';
        r1 = "B\u0014A\u0013?\u000e\u001bp\u0003f\u0012\u0010";
        r0 = 11;
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00a8, code lost:
        r3[r2] = r1;
        r2 = '\r';
        r1 = "_E";
        r0 = '\f';
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00b3, code lost:
        r3[r2] = r1;
        r2 = 14;
        r1 = "\r\u0005J\u0019?\r\u0005J\u0019H\u0010\u001c[\u0016}\u000e\u0010k\u0016k\u0003\u0017N\u0004zB\u0013N\u001esB\u0010\u0015";
        r0 = '\r';
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00be, code lost:
        r3[r2] = r1;
        r2 = 15;
        r1 = "\u000e\u001bp\u0014p\u0017\u001b[I/B\u0014A\u0013?\u000e\u001bp\u0003m\u000b\u0012H\u0012m=\u0001F\u001az^";
        r0 = 14;
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00c9, code lost:
        r3[r2] = r1;
        r2 = 16;
        r1 = "\u000e\u001bp\u0014p\u0017\u001b[I/";
        r0 = 15;
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00d4, code lost:
        r3[r2] = r1;
        cn.jpush.android.data.f.z = r4;
        r3 = new java.lang.String[8];
        r2 = 0;
        r1 = "=\u001cK";
        r0 = 16;
        r4 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00e4, code lost:
        r9 = 'b';
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00e8, code lost:
        r9 = 'u';
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00ec, code lost:
        r9 = '/';
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00f0, code lost:
        r9 = 'w';
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00f4, code lost:
        r3[r2] = r1;
        r4[1] = cn.jpush.android.data.f.z[6];
        r4[2] = cn.jpush.android.data.f.z[4];
        r4[3] = cn.jpush.android.data.f.z[3];
        r4[4] = cn.jpush.android.data.f.z[7];
        r4[5] = cn.jpush.android.data.f.z[1];
        r4[6] = cn.jpush.android.data.f.z[5];
        r4[7] = cn.jpush.android.data.f.z[9];
        cn.jpush.android.data.f.a = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0131, code lost:
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
            case 0: goto L_0x00e4;
            case 1: goto L_0x00e8;
            case 2: goto L_0x00ec;
            case 3: goto L_0x00f0;
            default: goto L_0x001e;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
        r9 = 31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0020, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            Method dump skipped, instructions count: 356
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.data.f.<clinit>():void");
    }

    public f(Context context) {
        this.b = context;
        this.c = new g(context);
    }

    private synchronized boolean c() {
        boolean z2;
        z2 = false;
        try {
            this.d = this.c.getWritableDatabase();
            z2 = true;
        } catch (Exception e) {
            new StringBuilder(z[14]).append(e.getMessage());
            ac.e();
        }
        return z2;
    }

    private synchronized boolean d() {
        boolean z2 = false;
        synchronized (this) {
            try {
                this.d = this.c.getReadableDatabase();
                z2 = true;
            } catch (Exception e) {
                new StringBuilder(z[0]).append(e.getMessage());
                ac.e();
            }
        }
        return z2;
    }

    public final synchronized long a(long j, int i, int i2, int i3, String str, long j2, long j3) {
        long j4;
        ContentValues contentValues = new ContentValues();
        contentValues.put(z[6], Long.valueOf(j));
        contentValues.put(z[4], (Integer) 1);
        contentValues.put(z[3], (Integer) 0);
        contentValues.put(z[7], (Integer) 0);
        contentValues.put(z[1], str);
        contentValues.put(z[5], Long.valueOf(j2));
        contentValues.put(z[9], Long.valueOf(j3));
        j4 = 0;
        try {
            j4 = this.d.insert(z[8], null, contentValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return j4;
    }

    public final synchronized Cursor a(int i, long j) {
        Exception e;
        Cursor cursor = null;
        synchronized (this) {
            try {
                cursor = this.d.query(true, z[8], a, z[11] + 1 + z[10] + "<" + j, null, null, null, null, null);
                if (cursor != null) {
                    try {
                        cursor.moveToFirst();
                    } catch (Exception e2) {
                        e = e2;
                        e.printStackTrace();
                        return cursor;
                    }
                }
            } catch (Exception e3) {
                e = e3;
            }
        }
        return cursor;
    }

    public final synchronized Cursor a(long j, int i) {
        Cursor cursor = null;
        synchronized (this) {
            try {
                cursor = this.d.query(true, z[8], a, z[2] + j + z[12] + z[13], null, null, null, null, null);
                if (cursor != null) {
                    try {
                        cursor.moveToFirst();
                    } catch (Exception e) {
                    }
                }
            } catch (Exception e2) {
            }
        }
        return cursor;
    }

    public final synchronized Cursor a(long j, long j2) {
        Cursor cursor = null;
        synchronized (this) {
            try {
                cursor = this.d.query(true, z[8], a, z[15] + (300000 + j) + z[10] + ">" + j, null, null, null, null, null);
                if (cursor != null) {
                    try {
                        cursor.moveToFirst();
                    } catch (Exception e) {
                    }
                }
            } catch (Exception e2) {
            }
        }
        return cursor;
    }

    public final synchronized void a() {
        if (this.d != null) {
            this.d.close();
        }
    }

    public final synchronized void a(Cursor cursor, h hVar) {
        if (cursor != null) {
            if (cursor.getCount() != 0) {
                if (hVar == null) {
                    hVar = new h();
                }
                try {
                    hVar.a(cursor.getLong(1));
                    hVar.a(cursor.getInt(2));
                    hVar.b(cursor.getInt(3));
                    hVar.c(cursor.getInt(4));
                    hVar.a(cursor.getString(5));
                    hVar.c(cursor.getLong(6));
                    hVar.b(cursor.getLong(7));
                } catch (Exception e) {
                    e.getStackTrace();
                }
                hVar.toString();
                ac.c();
            }
        }
        ac.b();
    }

    public final synchronized boolean a(boolean z2) {
        return z2 ? c() : d();
    }

    public final synchronized boolean b() {
        boolean z2 = true;
        synchronized (this) {
            try {
                String str = z[16];
                ContentValues contentValues = new ContentValues();
                contentValues.put(z[4], (Integer) 0);
                contentValues.put(z[3], (Integer) 1);
                contentValues.put(z[7], (Integer) 0);
                if (this.d.update(z[8], contentValues, str, null) <= 0) {
                    z2 = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                z2 = false;
            }
        }
        return z2;
    }

    public final synchronized boolean b(long j, int i, int i2, int i3, String str, long j2, long j3) {
        boolean z2;
        try {
            String str2 = z[2] + j;
            ContentValues contentValues = new ContentValues();
            contentValues.put(z[6], Long.valueOf(j));
            contentValues.put(z[4], Integer.valueOf(i));
            contentValues.put(z[3], Integer.valueOf(i2));
            contentValues.put(z[7], (Integer) 0);
            contentValues.put(z[1], str);
            contentValues.put(z[5], Long.valueOf(j2));
            contentValues.put(z[9], Long.valueOf(j3));
            z2 = this.d.update(z[8], contentValues, str2, null) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            z2 = false;
        }
        return z2;
    }
}
