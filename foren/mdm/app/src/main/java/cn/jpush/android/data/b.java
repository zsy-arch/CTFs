package cn.jpush.android.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import cn.jpush.android.util.ac;

/* loaded from: classes.dex */
public final class b extends SQLiteOpenHelper {
    public static final String[] a;
    public static final String[] b;
    private static b c;
    private static final String[] z;

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
            case 12: goto L_0x00c6;
            case 13: goto L_0x00d8;
            case 14: goto L_0x00e2;
            case 15: goto L_0x00ec;
            case 16: goto L_0x00f6;
            case 17: goto L_0x0105;
            default: goto L_0x003b;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003b, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "\u001d\u0011vVn\u0014";
        r0 = 0;
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0043, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "\u0011\u0012aVn\u0014_.";
        r0 = 1;
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004b, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "\u0005\u0012}`t\u0004";
        r0 = 2;
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0053, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\u0011\u0012aVn\u0014";
        r0 = 3;
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005b, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "\u001d\u0003xgX\u0019\u0006";
        r0 = 4;
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0063, code lost:
        r3[r2] = r1;
        r2 = 6;
        r1 = "P\u0016yl'\u001e\u0007f_b\u0002\u0011xfiP\u000bb)=P";
        r0 = 5;
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006b, code lost:
        r3[r2] = r1;
        r2 = 7;
        r1 = "\u0014\u0010~y'\u0004\u0003sebP\u0017aen\u0003\u0016";
        r0 = 6;
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0073, code lost:
        r3[r2] = r1;
        r2 = '\b';
        r1 = "$\nt)h\u001c\u0006Glu\u0003\u000b~g'\u0019\u0011+)";
        r0 = 7;
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x007c, code lost:
        r3[r2] = r1;
        r2 = '\t';
        r1 = "\u0014\u0010~y'\u0004\u0003sebP\u0006~~i\u001c\u000bb}";
        r0 = '\b';
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0086, code lost:
        r3[r2] = r1;
        r2 = '\n';
        r1 = "\u0013\u0010ths\u0015Behe\u001c\u00071|w\u001c\u000bb}//\u000bu)n\u001e\u0016tnb\u0002Ba{n\u001d\u0003cp'\u001b\u0007h)f\u0005\u0016~`i\u0013\u0010tdb\u001e\u0016=dt\u0017=xm'\u0004\u0007i}+\u0011\u0012aVn\u0014Bel\u007f\u0004N|hn\u001e=xm'\u0004\u0007i}+P\rglu\u0002\u000bulX\u0019\u00061}b\b\u00168";
        r0 = '\t';
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0091, code lost:
        r3[r2] = r1;
        r2 = 11;
        r1 = "\u0013\u0010ths\u0015Behe\u001c\u00071mh\u0007\f}`t\u0004JN`cP\u000b\u007f}b\u0017\u0007c)w\u0002\u000b|hu\tBzl~P\u0003d}h\u0019\fr{b\u001d\u0007\u007f}+\u001d\u0011vVn\u0014Bel\u007f\u0004Nclw\u0015\u0003eVi\u0005\u000f1`i\u0004\u0007vlu\\\u0011ehu\u0004=aftP\u000b\u007f}b\u0017\u0007c%b\u001e\u0006Nyh\u0003Bxgs\u0015\u0005t{+\u0013\r\u007f}b\u001e\u00161}b\b\u00168";
        r0 = '\n';
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x009c, code lost:
        r3[r2] = r1;
        r2 = '\f';
        r1 = "\u0003\u0007c\u007fn\u0013\u0007?me";
        r0 = 11;
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00a7, code lost:
        r3[r2] = r1;
        cn.jpush.android.data.b.z = r4;
        r3 = new java.lang.String[6];
        r2 = 0;
        r1 = "/\u000bu";
        r0 = '\f';
        r4 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00b6, code lost:
        r9 = 'p';
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00ba, code lost:
        r9 = 'b';
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00be, code lost:
        r9 = 17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00c2, code lost:
        r9 = '\t';
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00c6, code lost:
        r3[r2] = r1;
        r4[1] = cn.jpush.android.data.b.z[1];
        r2 = 2;
        r1 = "\u0002\u0007alf\u0004=\u007f|j";
        r0 = '\r';
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00d8, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "\u0003\u0016p{s/\u0012~z";
        r0 = 14;
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00e2, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\u0015\fuVw\u001f\u0011";
        r0 = 15;
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00ec, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "\u0013\r\u007f}b\u001e\u0016";
        r0 = 16;
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00f6, code lost:
        r3[r2] = r1;
        cn.jpush.android.data.b.a = r4;
        r3 = new java.lang.String[4];
        r2 = 0;
        r1 = "/\u000bu";
        r0 = 17;
        r4 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0105, code lost:
        r3[r2] = r1;
        r4[1] = cn.jpush.android.data.b.z[1];
        r4[2] = cn.jpush.android.data.b.z[4];
        r4[3] = cn.jpush.android.data.b.z[5];
        cn.jpush.android.data.b.b = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0121, code lost:
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
            case 0: goto L_0x00b6;
            case 1: goto L_0x00ba;
            case 2: goto L_0x00be;
            case 3: goto L_0x00c2;
            default: goto L_0x001e;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
        r9 = 7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x001f, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            Method dump skipped, instructions count: 342
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.data.b.<clinit>():void");
    }

    private b(Context context) {
        super(context, z[12], (SQLiteDatabase.CursorFactory) null, 3);
    }

    private static b a(Context context) {
        if (c == null) {
            c = new b(context);
        }
        return c;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x00b3 A[Catch: all -> 0x00b7, TRY_ENTER, TRY_LEAVE, TryCatch #2 {, blocks: (B:17:0x00a9, B:23:0x00b3, B:29:0x00be, B:30:0x00c1), top: B:39:0x000b }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static synchronized java.lang.String a(android.content.Context r16, java.lang.String r17) {
        /*
            Method dump skipped, instructions count: 207
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.data.b.a(android.content.Context, java.lang.String):java.lang.String");
    }

    public static synchronized void a(Context context, c cVar, String str, String str2) {
        synchronized (b.class) {
            a(context, cVar.c, cVar.d, str, str2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x005d A[Catch: all -> 0x009e, TRY_ENTER, TRY_LEAVE, TryCatch #4 {, blocks: (B:10:0x005d, B:17:0x009a, B:23:0x00a5, B:24:0x00a8), top: B:28:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static synchronized void a(android.content.Context r10, java.lang.String r11, java.lang.String r12, java.lang.String r13, java.lang.String r14) {
        /*
            r8 = 0
            java.lang.Class<cn.jpush.android.data.b> r9 = cn.jpush.android.data.b.class
            monitor-enter(r9)
            cn.jpush.android.data.b r0 = a(r10)     // Catch: Exception -> 0x00ab, all -> 0x00a1
            android.database.sqlite.SQLiteDatabase r0 = r0.getWritableDatabase()     // Catch: Exception -> 0x00ab, all -> 0x00a1
            java.lang.String[] r1 = cn.jpush.android.data.b.z     // Catch: Exception -> 0x00ab, all -> 0x00a1
            r2 = 3
            r1 = r1[r2]     // Catch: Exception -> 0x00ab, all -> 0x00a1
            java.lang.String[] r2 = cn.jpush.android.data.b.b     // Catch: Exception -> 0x00ab, all -> 0x00a1
            java.lang.String[] r3 = cn.jpush.android.data.b.z     // Catch: Exception -> 0x00ab, all -> 0x00a1
            r4 = 2
            r3 = r3[r4]     // Catch: Exception -> 0x00ab, all -> 0x00a1
            r4 = 1
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch: Exception -> 0x00ab, all -> 0x00a1
            r5 = 0
            r4[r5] = r13     // Catch: Exception -> 0x00ab, all -> 0x00a1
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch: Exception -> 0x00ab, all -> 0x00a1
            if (r1 == 0) goto L_0x002d
            int r2 = r1.getCount()     // Catch: Exception -> 0x0096, all -> 0x00a9
            if (r2 > 0) goto L_0x0062
        L_0x002d:
            android.content.ContentValues r2 = new android.content.ContentValues     // Catch: Exception -> 0x0096, all -> 0x00a9
            r2.<init>()     // Catch: Exception -> 0x0096, all -> 0x00a9
            java.lang.String[] r3 = cn.jpush.android.data.b.z     // Catch: Exception -> 0x0096, all -> 0x00a9
            r4 = 1
            r3 = r3[r4]     // Catch: Exception -> 0x0096, all -> 0x00a9
            r2.put(r3, r11)     // Catch: Exception -> 0x0096, all -> 0x00a9
            java.lang.String[] r3 = cn.jpush.android.data.b.z     // Catch: Exception -> 0x0096, all -> 0x00a9
            r4 = 4
            r3 = r3[r4]     // Catch: Exception -> 0x0096, all -> 0x00a9
            r2.put(r3, r13)     // Catch: Exception -> 0x0096, all -> 0x00a9
            java.lang.String[] r3 = cn.jpush.android.data.b.z     // Catch: Exception -> 0x0096, all -> 0x00a9
            r4 = 5
            r3 = r3[r4]     // Catch: Exception -> 0x0096, all -> 0x00a9
            r2.put(r3, r14)     // Catch: Exception -> 0x0096, all -> 0x00a9
            java.lang.String[] r3 = cn.jpush.android.data.b.z     // Catch: Exception -> 0x0096, all -> 0x00a9
            r4 = 0
            r3 = r3[r4]     // Catch: Exception -> 0x0096, all -> 0x00a9
            r2.put(r3, r12)     // Catch: Exception -> 0x0096, all -> 0x00a9
            java.lang.String[] r3 = cn.jpush.android.data.b.z     // Catch: Exception -> 0x0096, all -> 0x00a9
            r4 = 3
            r3 = r3[r4]     // Catch: Exception -> 0x0096, all -> 0x00a9
            r4 = 0
            r0.insert(r3, r4, r2)     // Catch: Exception -> 0x0096, all -> 0x00a9
        L_0x005b:
            if (r1 == 0) goto L_0x0060
            r1.close()     // Catch: all -> 0x009e
        L_0x0060:
            monitor-exit(r9)
            return
        L_0x0062:
            r1.moveToFirst()     // Catch: Exception -> 0x0096, all -> 0x00a9
            android.content.ContentValues r2 = new android.content.ContentValues     // Catch: Exception -> 0x0096, all -> 0x00a9
            r2.<init>()     // Catch: Exception -> 0x0096, all -> 0x00a9
            java.lang.String[] r3 = cn.jpush.android.data.b.z     // Catch: Exception -> 0x0096, all -> 0x00a9
            r4 = 1
            r3 = r3[r4]     // Catch: Exception -> 0x0096, all -> 0x00a9
            r2.put(r3, r11)     // Catch: Exception -> 0x0096, all -> 0x00a9
            java.lang.String[] r3 = cn.jpush.android.data.b.z     // Catch: Exception -> 0x0096, all -> 0x00a9
            r4 = 5
            r3 = r3[r4]     // Catch: Exception -> 0x0096, all -> 0x00a9
            r2.put(r3, r14)     // Catch: Exception -> 0x0096, all -> 0x00a9
            java.lang.String[] r3 = cn.jpush.android.data.b.z     // Catch: Exception -> 0x0096, all -> 0x00a9
            r4 = 0
            r3 = r3[r4]     // Catch: Exception -> 0x0096, all -> 0x00a9
            r2.put(r3, r12)     // Catch: Exception -> 0x0096, all -> 0x00a9
            java.lang.String[] r3 = cn.jpush.android.data.b.z     // Catch: Exception -> 0x0096, all -> 0x00a9
            r4 = 3
            r3 = r3[r4]     // Catch: Exception -> 0x0096, all -> 0x00a9
            java.lang.String[] r4 = cn.jpush.android.data.b.z     // Catch: Exception -> 0x0096, all -> 0x00a9
            r5 = 2
            r4 = r4[r5]     // Catch: Exception -> 0x0096, all -> 0x00a9
            r5 = 1
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch: Exception -> 0x0096, all -> 0x00a9
            r6 = 0
            r5[r6] = r13     // Catch: Exception -> 0x0096, all -> 0x00a9
            r0.update(r3, r2, r4, r5)     // Catch: Exception -> 0x0096, all -> 0x00a9
            goto L_0x005b
        L_0x0096:
            r0 = move-exception
            r0 = r1
        L_0x0098:
            if (r0 == 0) goto L_0x0060
            r0.close()     // Catch: all -> 0x009e
            goto L_0x0060
        L_0x009e:
            r0 = move-exception
            monitor-exit(r9)
            throw r0
        L_0x00a1:
            r0 = move-exception
            r1 = r8
        L_0x00a3:
            if (r1 == 0) goto L_0x00a8
            r1.close()     // Catch: all -> 0x009e
        L_0x00a8:
            throw r0     // Catch: all -> 0x009e
        L_0x00a9:
            r0 = move-exception
            goto L_0x00a3
        L_0x00ab:
            r0 = move-exception
            r0 = r8
            goto L_0x0098
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.data.b.a(android.content.Context, java.lang.String, java.lang.String, java.lang.String, java.lang.String):void");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        ac.b();
        sQLiteDatabase.execSQL(z[11]);
        sQLiteDatabase.execSQL(z[10]);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        new StringBuilder(z[8]).append(i).append(z[6]).append(i2);
        ac.b();
        sQLiteDatabase.execSQL(z[9]);
        sQLiteDatabase.execSQL(z[7]);
        onCreate(sQLiteDatabase);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        new StringBuilder(z[8]).append(i).append(z[6]).append(i2);
        ac.b();
        sQLiteDatabase.execSQL(z[9]);
        sQLiteDatabase.execSQL(z[7]);
        onCreate(sQLiteDatabase);
    }
}
