package cn.jpush.android.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import cn.jpush.android.util.ac;

/* loaded from: classes.dex */
final class g extends SQLiteOpenHelper {
    private static final String[] z;

    /* JADX WARN: Removed duplicated region for block: B:12:0x0029 A[LOOP:1: B:7:0x0018->B:12:0x0029, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x002d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x001f  */
    static {
        /*
            r2 = 1
            r1 = 0
            r0 = 3
            java.lang.String[] r4 = new java.lang.String[r0]
            java.lang.String r3 = "(d$\u001608w)\nUL\u007f-fU4\u007f8\u0012CLB4*\u007f\u000fW\u0007(\u007f\u0018_\r/s\rB\u0002)~"
            r0 = -1
            r5 = r4
            r6 = r4
            r4 = r1
        L_0x000b:
            char[] r3 = r3.toCharArray()
            int r7 = r3.length
            if (r7 > r2) goto L_0x0061
            r8 = r1
        L_0x0013:
            r9 = r3
            r10 = r8
            r13 = r7
            r7 = r3
            r3 = r13
        L_0x0018:
            char r12 = r7[r8]
            int r11 = r10 % 5
            switch(r11) {
                case 0: goto L_0x0055;
                case 1: goto L_0x0058;
                case 2: goto L_0x005b;
                case 3: goto L_0x005e;
                default: goto L_0x001f;
            }
        L_0x001f:
            r11 = 16
        L_0x0021:
            r11 = r11 ^ r12
            char r11 = (char) r11
            r7[r8] = r11
            int r8 = r10 + 1
            if (r3 != 0) goto L_0x002d
            r7 = r9
            r10 = r8
            r8 = r3
            goto L_0x0018
        L_0x002d:
            r7 = r3
            r3 = r9
        L_0x002f:
            if (r7 > r8) goto L_0x0013
            java.lang.String r7 = new java.lang.String
            r7.<init>(r3)
            java.lang.String r3 = r7.intern()
            switch(r0) {
                case 0: goto L_0x0046;
                case 1: goto L_0x0050;
                default: goto L_0x003d;
            }
        L_0x003d:
            r5[r4] = r3
            java.lang.String r0 = "/d.\u0007D)\u0016?\u0007R sK2O\u0000Y\b'|\u0002Y\u001f/v\u0005U\n2y\u0003XKnO\u0005RK\u000f^8s,\u0003BLf9\u000f]-d2f[)oK\u0007E8y\"\bS>s&\u0003^8\u0016G*~3_\u000ff|\u0003X\ff~\u0003BK(e\u0000ZG*~3U\u00043~\u0018\u0016\u0002(d\tQ\u000e40\u0002Y\u001ff~\u0019Z\u0007j|\u0002i\u0019#}\u0003@\u000efy\u0002B\u000e!u\u001e\u0016\u0005)dLX\u001e*|@Z\u0005\u0019d\u0015F\u000efy\u0002B\u000e!u\u001e\u0016\u0005)dLX\u001e*|@Z\u0005\u0019u\u0014B\u0019'0\u0018S\u001320@Z\u0005\u0019d\u001e_\f!u\u001ei\u001f/}\t\u0016\u0007)~\u000b\u0016G*~3W\u000f\"O\u0018_\u0006#0\u0000Y\u0005!0E\r"
            r3 = r0
            r4 = r2
            r5 = r6
            r0 = r1
            goto L_0x000b
        L_0x0046:
            r5[r4] = r3
            r3 = 2
            java.lang.String r0 = "\u0006F\u001e5x3Z\u0004%q\u0000i\u0005)d\u0005P\u0002%q\u0018_\u0004(>\bT"
            r4 = r3
            r5 = r6
            r3 = r0
            r0 = r2
            goto L_0x000b
        L_0x0050:
            r5[r4] = r3
            cn.jpush.android.data.g.z = r6
            return
        L_0x0055:
            r11 = 108(0x6c, float:1.51E-43)
            goto L_0x0021
        L_0x0058:
            r11 = 54
            goto L_0x0021
        L_0x005b:
            r11 = 107(0x6b, float:1.5E-43)
            goto L_0x0021
        L_0x005e:
            r11 = 70
            goto L_0x0021
        L_0x0061:
            r8 = r1
            goto L_0x002f
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.data.g.<clinit>():void");
    }

    public g(Context context) {
        this(context, z[2], null, 1);
    }

    private g(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, 1);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL(z[1]);
        } catch (Exception e) {
            ac.a();
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL(z[0]);
        onCreate(sQLiteDatabase);
    }
}
