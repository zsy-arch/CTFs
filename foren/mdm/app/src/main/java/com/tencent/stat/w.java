package com.tencent.stat;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.tencent.stat.common.StatLogger;
import com.tencent.stat.common.k;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class w extends SQLiteOpenHelper {
    public w(Context context) {
        super(context, k.v(context), (SQLiteDatabase.CursorFactory) null, 3);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x005b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void a(android.database.sqlite.SQLiteDatabase r10) {
        /*
            r9 = this;
            r8 = 0
            java.lang.String r1 = "user"
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r0 = r10
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch: Throwable -> 0x0048, all -> 0x0057
            android.content.ContentValues r0 = new android.content.ContentValues     // Catch: Throwable -> 0x0061, all -> 0x005f
            r0.<init>()     // Catch: Throwable -> 0x0061, all -> 0x005f
            boolean r2 = r1.moveToNext()     // Catch: Throwable -> 0x0061, all -> 0x005f
            if (r2 == 0) goto L_0x0033
            r2 = 0
            java.lang.String r8 = r1.getString(r2)     // Catch: Throwable -> 0x0061, all -> 0x005f
            r2 = 1
            r1.getInt(r2)     // Catch: Throwable -> 0x0061, all -> 0x005f
            r2 = 2
            r1.getString(r2)     // Catch: Throwable -> 0x0061, all -> 0x005f
            r2 = 3
            r1.getLong(r2)     // Catch: Throwable -> 0x0061, all -> 0x005f
            java.lang.String r2 = com.tencent.stat.common.k.c(r8)     // Catch: Throwable -> 0x0061, all -> 0x005f
            java.lang.String r3 = "uid"
            r0.put(r3, r2)     // Catch: Throwable -> 0x0061, all -> 0x005f
        L_0x0033:
            if (r8 == 0) goto L_0x0042
            java.lang.String r2 = "user"
            java.lang.String r3 = "uid=?"
            r4 = 1
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch: Throwable -> 0x0061, all -> 0x005f
            r5 = 0
            r4[r5] = r8     // Catch: Throwable -> 0x0061, all -> 0x005f
            r10.update(r2, r0, r3, r4)     // Catch: Throwable -> 0x0061, all -> 0x005f
        L_0x0042:
            if (r1 == 0) goto L_0x0047
            r1.close()
        L_0x0047:
            return
        L_0x0048:
            r0 = move-exception
            r1 = r8
        L_0x004a:
            com.tencent.stat.common.StatLogger r2 = com.tencent.stat.n.d()     // Catch: all -> 0x005f
            r2.e(r0)     // Catch: all -> 0x005f
            if (r1 == 0) goto L_0x0047
            r1.close()
            goto L_0x0047
        L_0x0057:
            r0 = move-exception
            r1 = r8
        L_0x0059:
            if (r1 == 0) goto L_0x005e
            r1.close()
        L_0x005e:
            throw r0
        L_0x005f:
            r0 = move-exception
            goto L_0x0059
        L_0x0061:
            r0 = move-exception
            goto L_0x004a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.stat.w.a(android.database.sqlite.SQLiteDatabase):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x007c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void b(android.database.sqlite.SQLiteDatabase r11) {
        /*
            r10 = this;
            r8 = 0
            java.lang.String r1 = "events"
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r0 = r11
            android.database.Cursor r7 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch: Throwable -> 0x008c, all -> 0x0086
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch: Throwable -> 0x0036, all -> 0x0079
            r0.<init>()     // Catch: Throwable -> 0x0036, all -> 0x0079
        L_0x0013:
            boolean r1 = r7.moveToNext()     // Catch: Throwable -> 0x0036, all -> 0x0079
            if (r1 == 0) goto L_0x0045
            r1 = 0
            long r2 = r7.getLong(r1)     // Catch: Throwable -> 0x0036, all -> 0x0079
            r1 = 1
            java.lang.String r4 = r7.getString(r1)     // Catch: Throwable -> 0x0036, all -> 0x0079
            r1 = 2
            int r5 = r7.getInt(r1)     // Catch: Throwable -> 0x0036, all -> 0x0079
            r1 = 3
            int r6 = r7.getInt(r1)     // Catch: Throwable -> 0x0036, all -> 0x0079
            com.tencent.stat.x r1 = new com.tencent.stat.x     // Catch: Throwable -> 0x0036, all -> 0x0079
            r1.<init>(r2, r4, r5, r6)     // Catch: Throwable -> 0x0036, all -> 0x0079
            r0.add(r1)     // Catch: Throwable -> 0x0036, all -> 0x0079
            goto L_0x0013
        L_0x0036:
            r0 = move-exception
            r1 = r7
        L_0x0038:
            com.tencent.stat.common.StatLogger r2 = com.tencent.stat.n.d()     // Catch: all -> 0x0089
            r2.e(r0)     // Catch: all -> 0x0089
            if (r1 == 0) goto L_0x0044
            r1.close()
        L_0x0044:
            return
        L_0x0045:
            android.content.ContentValues r1 = new android.content.ContentValues     // Catch: Throwable -> 0x0036, all -> 0x0079
            r1.<init>()     // Catch: Throwable -> 0x0036, all -> 0x0079
            java.util.Iterator r2 = r0.iterator()     // Catch: Throwable -> 0x0036, all -> 0x0079
        L_0x004e:
            boolean r0 = r2.hasNext()     // Catch: Throwable -> 0x0036, all -> 0x0079
            if (r0 == 0) goto L_0x0080
            java.lang.Object r0 = r2.next()     // Catch: Throwable -> 0x0036, all -> 0x0079
            com.tencent.stat.x r0 = (com.tencent.stat.x) r0     // Catch: Throwable -> 0x0036, all -> 0x0079
            java.lang.String r3 = "content"
            java.lang.String r4 = r0.b     // Catch: Throwable -> 0x0036, all -> 0x0079
            java.lang.String r4 = com.tencent.stat.common.k.c(r4)     // Catch: Throwable -> 0x0036, all -> 0x0079
            r1.put(r3, r4)     // Catch: Throwable -> 0x0036, all -> 0x0079
            java.lang.String r3 = "events"
            java.lang.String r4 = "event_id=?"
            r5 = 1
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch: Throwable -> 0x0036, all -> 0x0079
            r6 = 0
            long r8 = r0.a     // Catch: Throwable -> 0x0036, all -> 0x0079
            java.lang.String r0 = java.lang.Long.toString(r8)     // Catch: Throwable -> 0x0036, all -> 0x0079
            r5[r6] = r0     // Catch: Throwable -> 0x0036, all -> 0x0079
            r11.update(r3, r1, r4, r5)     // Catch: Throwable -> 0x0036, all -> 0x0079
            goto L_0x004e
        L_0x0079:
            r0 = move-exception
        L_0x007a:
            if (r7 == 0) goto L_0x007f
            r7.close()
        L_0x007f:
            throw r0
        L_0x0080:
            if (r7 == 0) goto L_0x0044
            r7.close()
            goto L_0x0044
        L_0x0086:
            r0 = move-exception
            r7 = r8
            goto L_0x007a
        L_0x0089:
            r0 = move-exception
            r7 = r1
            goto L_0x007a
        L_0x008c:
            r0 = move-exception
            r1 = r8
            goto L_0x0038
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.stat.w.b(android.database.sqlite.SQLiteDatabase):void");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table if not exists events(event_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, content TEXT, status INTEGER, send_count INTEGER, timestamp LONG)");
        sQLiteDatabase.execSQL("create table if not exists user(uid TEXT PRIMARY KEY, user_type INTEGER, app_ver TEXT, ts INTEGER)");
        sQLiteDatabase.execSQL("create table if not exists config(type INTEGER PRIMARY KEY NOT NULL, content TEXT, md5sum TEXT, version INTEGER)");
        sQLiteDatabase.execSQL("create table if not exists keyvalues(key TEXT PRIMARY KEY NOT NULL, value TEXT)");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        StatLogger statLogger;
        statLogger = n.e;
        statLogger.debug("upgrade DB from oldVersion " + i + " to newVersion " + i2);
        if (i == 1) {
            sQLiteDatabase.execSQL("create table if not exists keyvalues(key TEXT PRIMARY KEY NOT NULL, value TEXT)");
            a(sQLiteDatabase);
            b(sQLiteDatabase);
        }
        if (i == 2) {
            a(sQLiteDatabase);
            b(sQLiteDatabase);
        }
    }
}
