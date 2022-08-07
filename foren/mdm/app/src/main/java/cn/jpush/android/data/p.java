package cn.jpush.android.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import cn.jpush.android.util.ac;

/* loaded from: classes.dex */
final class p extends SQLiteOpenHelper {
    private static final String[] z;

    /* JADX WARN: Removed duplicated region for block: B:12:0x0029 A[LOOP:1: B:7:0x0018->B:12:0x0029, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x002d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x001f  */
    static {
        /*
            r2 = 1
            r1 = 0
            r0 = 3
            java.lang.String[] r4 = new java.lang.String[r0]
            java.lang.String r3 = "Wf#V\u0002Gu.Jg3}*&gK}?Rq3^\u001csQ{k\u001frCg]\u001frKpG"
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
                case 0: goto L_0x0056;
                case 1: goto L_0x0059;
                case 2: goto L_0x005c;
                case 3: goto L_0x005f;
                default: goto L_0x001f;
            }
        L_0x001f:
            r11 = 34
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
                case 0: goto L_0x0047;
                case 1: goto L_0x0051;
                default: goto L_0x003d;
            }
        L_0x003d:
            r5[r4] = r3
            java.lang.String r0 = "yD\u0019uJLG\u0018gVzG\u0018oA`\u001a\bd"
            r3 = r0
            r4 = r2
            r5 = r6
            r0 = r1
            goto L_0x000b
        L_0x0047:
            r5[r4] = r3
            r3 = 2
            java.lang.String r0 = "Pf)GvV\u00148G`_qLlRfG\u0004YQgU\u0018oQg]\u000fu\u0002;k\u0005b\u0002Zz8CeVfLVpZy-T{3\u007f)_\u0002Ra8Ik]w>CoVz8&\u000e`@3uMa@3mGj\u0014\u0018cZg\u0014\u0002iV3Z\u0019jN?G\u0018YLv@LrGk@LhMg\u0014\u0002sN\u007f\u0018\u001fr}p[\u0002h}zDLrGk@LhMg\u0014\u0002sN\u007f\u0018\u001fr}\u007f[\u000fgNLP\u0002u\u0002gQ\u0014r\u000e`@3uMfF\u000fc\u0002zZ\u0018cEvFLhMg\u0014\u0002sN\u007f\u0018\u001fr}uU\u0005jGw\u0014\u0005hVvS\tt\u0002}[\u0018&LfX\u0000*Qgk\u0018iVrXLoLgQ\u000bcP3Z\u0003r\u0002}A\u0000j\u000e`@3eMfZ\u0018Y\u00133]\u0002rGtQ\u001e*Qgk\u000fiW}@37} \u0014\u0005hVvS\tt\u000e`@3eMfZ\u0018Y\u0011L\u0005\\&K}@\taGa\u0018\u001fr}p[\u0019hVL\u0005\\&K}@\taGa\u001dW"
            r4 = r3
            r5 = r6
            r3 = r0
            r0 = r2
            goto L_0x000b
        L_0x0051:
            r5[r4] = r3
            cn.jpush.android.data.p.z = r6
            return
        L_0x0056:
            r11 = 19
            goto L_0x0021
        L_0x0059:
            r11 = 52
            goto L_0x0021
        L_0x005c:
            r11 = 108(0x6c, float:1.51E-43)
            goto L_0x0021
        L_0x005f:
            r11 = 6
            goto L_0x0021
        L_0x0061:
            r8 = r1
            goto L_0x002f
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.data.p.<clinit>():void");
    }

    public p(Context context) {
        this(context, z[1], null, 1);
    }

    private p(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, 1);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL(z[2]);
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
