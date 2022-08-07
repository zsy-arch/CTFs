package com.tencent.smtt.sdk;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.tencent.smtt.utils.FileUtil;
import com.tencent.smtt.utils.TbsLog;
import e.a.a.a.a;
import java.io.File;

/* loaded from: classes.dex */
public class h {

    /* renamed from: a  reason: collision with root package name */
    public static final String f1356a = CookieManager.LOGTAG;

    /* renamed from: b  reason: collision with root package name */
    public static File f1357b;

    public static File a(Context context) {
        if (f1357b == null && context != null) {
            f1357b = new File(context.getDir("webview", 0), "Cookies");
        }
        if (f1357b == null) {
            StringBuilder a2 = a.a("/data/data/");
            a2.append(context.getPackageName());
            a2.append(File.separator);
            a2.append("app_webview");
            a2.append(File.separator);
            a2.append("Cookies");
            f1357b = new File(a2.toString());
        }
        return f1357b;
    }

    public static String a(SQLiteDatabase sQLiteDatabase, String str) {
        Cursor rawQuery = sQLiteDatabase.rawQuery("select * from " + str, null);
        int count = rawQuery.getCount();
        int columnCount = rawQuery.getColumnCount();
        StringBuilder sb = new StringBuilder();
        sb.append("raws:" + count + ",columns:" + columnCount + "\n");
        if (count <= 0 || !rawQuery.moveToFirst()) {
            return sb.toString();
        }
        do {
            sb.append("\n");
            for (int i = 0; i < columnCount; i++) {
                try {
                    sb.append(rawQuery.getString(i));
                    sb.append(",");
                } catch (Exception unused) {
                }
            }
            sb.append("\n");
        } while (rawQuery.moveToNext());
        return sb.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0031, code lost:
        if (r4.isOpen() == false) goto L_0x0053;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x004e, code lost:
        if (r4.isOpen() == false) goto L_0x0053;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0050, code lost:
        r4.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0053, code lost:
        return r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.ArrayList<java.lang.String> a(android.database.sqlite.SQLiteDatabase r4) {
        /*
            r0 = 0
            if (r4 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.lang.String r2 = "select * from sqlite_master where type='table'"
            android.database.Cursor r0 = r4.rawQuery(r2, r0)     // Catch: Throwable -> 0x0044, all -> 0x0034
            boolean r2 = r0.moveToFirst()     // Catch: Throwable -> 0x0044, all -> 0x0034
            if (r2 == 0) goto L_0x002a
        L_0x0015:
            r2 = 1
            java.lang.String r2 = r0.getString(r2)     // Catch: Throwable -> 0x0044, all -> 0x0034
            r3 = 4
            r0.getString(r3)     // Catch: Throwable -> 0x0044, all -> 0x0034
            r1.add(r2)     // Catch: Throwable -> 0x0044, all -> 0x0034
            a(r4, r2)     // Catch: Throwable -> 0x0044, all -> 0x0034
            boolean r2 = r0.moveToNext()     // Catch: Throwable -> 0x0044, all -> 0x0034
            if (r2 != 0) goto L_0x0015
        L_0x002a:
            r0.close()
            boolean r0 = r4.isOpen()
            if (r0 == 0) goto L_0x0053
            goto L_0x0050
        L_0x0034:
            r1 = move-exception
            if (r0 == 0) goto L_0x003a
            r0.close()
        L_0x003a:
            boolean r0 = r4.isOpen()
            if (r0 == 0) goto L_0x0043
            r4.close()
        L_0x0043:
            throw r1
        L_0x0044:
            if (r0 == 0) goto L_0x004a
            r0.close()
        L_0x004a:
            boolean r0 = r4.isOpen()
            if (r0 == 0) goto L_0x0053
        L_0x0050:
            r4.close()
        L_0x0053:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.h.a(android.database.sqlite.SQLiteDatabase):java.util.ArrayList");
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00e8 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00e9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void a(android.content.Context r9, com.tencent.smtt.sdk.CookieManager.a r10, java.lang.String r11, boolean r12, boolean r13) {
        /*
            Method dump skipped, instructions count: 324
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.h.a(android.content.Context, com.tencent.smtt.sdk.CookieManager$a, java.lang.String, boolean, boolean):void");
    }

    public static boolean b(Context context) {
        if (context == null) {
            return false;
        }
        FileUtil.a(a(context), false);
        return true;
    }

    public static SQLiteDatabase c(Context context) {
        File a2;
        SQLiteDatabase sQLiteDatabase = null;
        if (context == null || (a2 = a(context)) == null) {
            return null;
        }
        try {
            sQLiteDatabase = SQLiteDatabase.openDatabase(a2.getAbsolutePath(), null, 0);
        } catch (Exception unused) {
        }
        if (sQLiteDatabase == null) {
            TbsLog.i(f1356a, "dbPath is not exist!");
        }
        return sQLiteDatabase;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0039, code lost:
        r0 = java.lang.Integer.parseInt(r1.getString(1));
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0050, code lost:
        if (r4.isOpen() != false) goto L_0x0052;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0052, code lost:
        r4.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0079, code lost:
        if (r4.isOpen() != false) goto L_0x0052;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int d(android.content.Context r4) {
        /*
            java.lang.System.currentTimeMillis()
            r0 = 0
            r1 = 0
            android.database.sqlite.SQLiteDatabase r4 = c(r4)     // Catch: Throwable -> 0x006d, all -> 0x005a
            if (r4 != 0) goto L_0x0018
            r0 = -1
            if (r4 == 0) goto L_0x0017
            boolean r1 = r4.isOpen()
            if (r1 == 0) goto L_0x0017
            r4.close()
        L_0x0017:
            return r0
        L_0x0018:
            java.lang.String r2 = "select * from meta"
            android.database.Cursor r1 = r4.rawQuery(r2, r1)     // Catch: Throwable -> 0x0058, all -> 0x0056
            int r2 = r1.getCount()     // Catch: Throwable -> 0x0058, all -> 0x0056
            r1.getColumnCount()     // Catch: Throwable -> 0x0058, all -> 0x0056
            if (r2 <= 0) goto L_0x0049
            boolean r2 = r1.moveToFirst()     // Catch: Throwable -> 0x0058, all -> 0x0056
            if (r2 == 0) goto L_0x0049
        L_0x002d:
            java.lang.String r2 = r1.getString(r0)     // Catch: Throwable -> 0x0058, all -> 0x0056
            java.lang.String r3 = "version"
            boolean r2 = r2.equals(r3)     // Catch: Throwable -> 0x0058, all -> 0x0056
            if (r2 == 0) goto L_0x0043
            r2 = 1
            java.lang.String r2 = r1.getString(r2)     // Catch: Throwable -> 0x0058, all -> 0x0056
            int r0 = java.lang.Integer.parseInt(r2)     // Catch: Throwable -> 0x0058, all -> 0x0056
            goto L_0x0049
        L_0x0043:
            boolean r2 = r1.moveToNext()     // Catch: Throwable -> 0x0058, all -> 0x0056
            if (r2 != 0) goto L_0x002d
        L_0x0049:
            r1.close()
            boolean r1 = r4.isOpen()
            if (r1 == 0) goto L_0x007c
        L_0x0052:
            r4.close()
            goto L_0x007c
        L_0x0056:
            r0 = move-exception
            goto L_0x005c
        L_0x0058:
            goto L_0x006e
        L_0x005a:
            r0 = move-exception
            r4 = r1
        L_0x005c:
            if (r1 == 0) goto L_0x0061
            r1.close()
        L_0x0061:
            if (r4 == 0) goto L_0x006c
            boolean r1 = r4.isOpen()
            if (r1 == 0) goto L_0x006c
            r4.close()
        L_0x006c:
            throw r0
        L_0x006d:
            r4 = r1
        L_0x006e:
            if (r1 == 0) goto L_0x0073
            r1.close()
        L_0x0073:
            if (r4 == 0) goto L_0x007c
            boolean r1 = r4.isOpen()
            if (r1 == 0) goto L_0x007c
            goto L_0x0052
        L_0x007c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.h.d(android.content.Context):int");
    }
}
