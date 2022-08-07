package com.loc;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.alipay.sdk.sys.a;
import java.io.UnsupportedEncodingException;
import org.json.JSONObject;

/* compiled from: DB.java */
/* loaded from: classes2.dex */
public final class ch {
    private static ch a = null;
    private String b = "2.0.201501131131".replace(".", "");
    private String c = null;

    public static synchronized ch a() {
        ch chVar;
        synchronized (ch.class) {
            if (a == null) {
                a = new ch();
            }
            chVar = a;
        }
        return chVar;
    }

    private boolean a(SQLiteDatabase sQLiteDatabase, String str) {
        Cursor cursor;
        Cursor cursor2 = null;
        boolean z = false;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            try {
                StringBuilder sb = new StringBuilder();
                sb.append("SELECT count(*) as c FROM sqlite_master WHERE type = 'table' AND name = '");
                sb.append(str.trim()).append(this.b).append("' ");
                cursor2 = sQLiteDatabase.rawQuery(sb.toString(), null);
                if (cursor2 != null) {
                    try {
                        if (cursor2.moveToFirst() && cursor2.getInt(0) > 0) {
                            z = true;
                        }
                    } catch (Throwable th) {
                        cursor = cursor2;
                        if (cursor != null) {
                            cursor.close();
                        }
                        return true;
                    }
                }
                sb.delete(0, sb.length());
            } catch (Throwable th2) {
                cursor = cursor2;
            }
        } finally {
            if (cursor2 != null) {
                cursor2.close();
            }
        }
    }

    public final String a(String str, StringBuilder sb, Context context) {
        String str2 = null;
        if (context == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.c == null) {
                this.c = ce.a("MD5", context.getPackageName());
            }
            if (str.contains(a.b)) {
                str = str.substring(0, str.indexOf(a.b));
            }
            String substring = str.substring(str.lastIndexOf("#") + 1);
            if (substring.equals("cgi")) {
                jSONObject.put("cgi", str.substring(0, str.length() - 12));
            } else if (!TextUtils.isEmpty(sb) && sb.indexOf("access") != -1) {
                jSONObject.put("cgi", str.substring(0, str.length() - (substring.length() + 9)));
                String[] split = sb.toString().split(",access");
                jSONObject.put("mmac", split[0].contains("#") ? split[0].substring(split[0].lastIndexOf("#") + 1) : split[0]);
            }
            try {
                str2 = o.a(ce.c(jSONObject.toString().getBytes("UTF-8"), this.c));
                return str2;
            } catch (UnsupportedEncodingException e) {
                return null;
            }
        } catch (Throwable th) {
            return str2;
        }
    }

    public final synchronized void a(Context context) throws Exception {
        SQLiteDatabase sQLiteDatabase;
        SQLiteDatabase sQLiteDatabase2 = null;
        synchronized (this) {
            try {
                if (context != null) {
                    try {
                        sQLiteDatabase = context.openOrCreateDatabase("hmdb", 0, null);
                        try {
                            if (!a(sQLiteDatabase, "hist")) {
                                if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                                    sQLiteDatabase.close();
                                }
                                SQLiteDatabase sQLiteDatabase3 = null;
                                if (0 != 0 && sQLiteDatabase3.isOpen()) {
                                    sQLiteDatabase3.close();
                                }
                            } else {
                                try {
                                    sQLiteDatabase.delete("hist" + this.b, "time<?", new String[]{String.valueOf(cx.a() - com.umeng.analytics.a.j)});
                                } catch (Throwable th) {
                                    f.a(th, "DB", "clearHist");
                                    String message = th.getMessage();
                                    if (!TextUtils.isEmpty(message)) {
                                        message.contains("no such table");
                                    }
                                }
                                if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                                    sQLiteDatabase.close();
                                }
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            f.a(th, "DB", "clearHist p2");
                            if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                                sQLiteDatabase.close();
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        sQLiteDatabase = null;
                    }
                }
            } catch (Throwable th4) {
                th = th4;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:87:0x027d A[Catch: all -> 0x0034, TRY_ENTER, TryCatch #1 {, blocks: (B:17:0x0025, B:19:0x002a, B:21:0x0030, B:56:0x01a3, B:58:0x01a8, B:60:0x01ae, B:73:0x01f7, B:75:0x01fc, B:77:0x0202, B:87:0x027d, B:89:0x0282, B:91:0x0288, B:92:0x028b), top: B:112:0x0006 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final synchronized void a(android.content.Context r10, java.lang.String r11) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 811
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.ch.a(android.content.Context, java.lang.String):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x014f A[Catch: all -> 0x00d8, TRY_ENTER, TryCatch #2 {, blocks: (B:4:0x0003, B:9:0x000d, B:23:0x00c8, B:25:0x00cd, B:27:0x00d3, B:37:0x00fe, B:39:0x0103, B:41:0x0109, B:46:0x014f, B:48:0x0154, B:50:0x015a, B:51:0x015d), top: B:59:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final synchronized void a(android.content.Context r11, java.lang.String r12, java.lang.String r13, long r14) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 359
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.ch.a(android.content.Context, java.lang.String, java.lang.String, long):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0115  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(java.lang.String r10, com.amap.api.location.AMapLocation r11, java.lang.StringBuilder r12, android.content.Context r13) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 303
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.ch.a(java.lang.String, com.amap.api.location.AMapLocation, java.lang.StringBuilder, android.content.Context):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x00c0 A[Catch: all -> 0x0038, TRY_ENTER, TryCatch #4 {, blocks: (B:4:0x0002, B:19:0x0029, B:21:0x002e, B:23:0x0034, B:40:0x00c0, B:42:0x00c5, B:44:0x00cb, B:54:0x00f4, B:56:0x00f9, B:58:0x00ff, B:62:0x0108, B:64:0x010d, B:66:0x0113, B:67:0x0116), top: B:80:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0108 A[Catch: all -> 0x0038, TryCatch #4 {, blocks: (B:4:0x0002, B:19:0x0029, B:21:0x002e, B:23:0x0034, B:40:0x00c0, B:42:0x00c5, B:44:0x00cb, B:54:0x00f4, B:56:0x00f9, B:58:0x00ff, B:62:0x0108, B:64:0x010d, B:66:0x0113, B:67:0x0116), top: B:80:0x0002 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final synchronized void b(android.content.Context r12) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 294
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.ch.b(android.content.Context):void");
    }
}
