package com.tencent.stat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteException;
import android.os.Handler;
import android.os.HandlerThread;
import com.em.ui.EditActivity;
import com.tencent.stat.a.e;
import com.tencent.stat.common.StatLogger;
import com.tencent.stat.common.k;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class n {
    private static StatLogger e = k.b();
    private static n f = null;
    Handler a;
    private w d;
    volatile int b = 0;
    DeviceInfo c = null;
    private HashMap<String, String> g = new HashMap<>();

    private n(Context context) {
        this.a = null;
        try {
            HandlerThread handlerThread = new HandlerThread("StatStore");
            handlerThread.start();
            e.w("Launch store thread:" + handlerThread);
            this.a = new Handler(handlerThread.getLooper());
            Context applicationContext = context.getApplicationContext();
            this.d = new w(applicationContext);
            this.d.getWritableDatabase();
            this.d.getReadableDatabase();
            b(applicationContext);
            c();
            f();
            this.a.post(new o(this));
        } catch (Throwable th) {
            e.e(th);
        }
    }

    public static synchronized n a(Context context) {
        n nVar;
        synchronized (n.class) {
            if (f == null) {
                f = new n(context);
            }
            nVar = f;
        }
        return nVar;
    }

    public static n b() {
        return f;
    }

    public synchronized void b(int i) {
        if (this.b > 0 && i > 0) {
            e.i("Load " + Integer.toString(this.b) + " unsent events");
            ArrayList arrayList = new ArrayList();
            ArrayList<x> arrayList2 = new ArrayList();
            if (i == -1 || i > StatConfig.a()) {
                i = StatConfig.a();
            }
            this.b -= i;
            c(arrayList2, i);
            e.i("Peek " + Integer.toString(arrayList2.size()) + " unsent events.");
            if (!arrayList2.isEmpty()) {
                b(arrayList2, 2);
                for (x xVar : arrayList2) {
                    arrayList.add(xVar.b);
                }
                d.b().b(arrayList, new u(this, arrayList2, i));
            }
        }
    }

    public synchronized void b(e eVar, c cVar) {
        if (StatConfig.getMaxStoreEventCount() > 0) {
            try {
                this.d.getWritableDatabase().beginTransaction();
                if (this.b > StatConfig.getMaxStoreEventCount()) {
                    e.warn("Too many events stored in db.");
                    this.b -= this.d.getWritableDatabase().delete("events", "event_id in (select event_id from events where timestamp in (select min(timestamp) from events) limit 1)", null);
                }
                ContentValues contentValues = new ContentValues();
                String c = k.c(eVar.d());
                contentValues.put(EditActivity.CONTENT, c);
                contentValues.put("send_count", "0");
                contentValues.put("status", Integer.toString(1));
                contentValues.put("timestamp", Long.valueOf(eVar.b()));
                if (this.d.getWritableDatabase().insert("events", null, contentValues) == -1) {
                    e.error("Failed to store event:" + c);
                } else {
                    this.b++;
                    this.d.getWritableDatabase().setTransactionSuccessful();
                    if (cVar != null) {
                        cVar.a();
                    }
                }
                try {
                    this.d.getWritableDatabase().endTransaction();
                } catch (Throwable th) {
                }
            } catch (Throwable th2) {
                e.e(th2);
                try {
                    this.d.getWritableDatabase().endTransaction();
                } catch (Throwable th3) {
                }
            }
        }
    }

    public synchronized void b(b bVar) {
        Cursor cursor;
        long insert;
        boolean z = false;
        Cursor cursor2 = null;
        try {
            synchronized (this) {
                try {
                    String a = bVar.a();
                    String a2 = k.a(a);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(EditActivity.CONTENT, bVar.b.toString());
                    contentValues.put("md5sum", a2);
                    bVar.c = a2;
                    contentValues.put("version", Integer.valueOf(bVar.d));
                    cursor = this.d.getReadableDatabase().query("config", null, null, null, null, null, null);
                    while (true) {
                        try {
                            if (cursor.moveToNext()) {
                                if (cursor.getInt(0) == bVar.a) {
                                    z = true;
                                    break;
                                }
                            } else {
                                break;
                            }
                        } catch (Throwable th) {
                            th = th;
                            e.e(th);
                            if (cursor != null) {
                                cursor.close();
                            }
                        }
                    }
                    if (true == z) {
                        insert = this.d.getWritableDatabase().update("config", contentValues, "type=?", new String[]{Integer.toString(bVar.a)});
                    } else {
                        contentValues.put("type", Integer.valueOf(bVar.a));
                        insert = this.d.getWritableDatabase().insert("config", null, contentValues);
                    }
                    if (insert == -1) {
                        e.e("Failed to store cfg:" + a);
                    } else {
                        e.d("Sucessed to store cfg:" + a);
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (0 != 0) {
                        cursor2.close();
                    }
                    throw th;
                }
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    public synchronized void b(List<x> list) {
        e.i("Delete " + list.size() + " sent events in thread:" + Thread.currentThread());
        this.d.getWritableDatabase().beginTransaction();
        Iterator<x> it = list.iterator();
        while (it.hasNext()) {
            this.b -= this.d.getWritableDatabase().delete("events", "event_id = ?", new String[]{Long.toString(it.next().a)});
        }
        this.d.getWritableDatabase().setTransactionSuccessful();
        this.b = (int) DatabaseUtils.queryNumEntries(this.d.getReadableDatabase(), "events");
        try {
            this.d.getWritableDatabase().endTransaction();
        } catch (SQLiteException e2) {
            e.e((Exception) e2);
        }
    }

    public synchronized void b(List<x> list, int i) {
        e.i("Update " + list.size() + " sending events to status:" + i + " in thread:" + Thread.currentThread());
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", Integer.toString(i));
        this.d.getWritableDatabase().beginTransaction();
        for (x xVar : list) {
            if (xVar.d + 1 > StatConfig.getMaxSendRetryCount()) {
                this.b -= this.d.getWritableDatabase().delete("events", "event_id=?", new String[]{Long.toString(xVar.a)});
            } else {
                contentValues.put("send_count", Integer.valueOf(xVar.d + 1));
                e.i("Update event:" + xVar.a + " for content:" + contentValues);
                int update = this.d.getWritableDatabase().update("events", contentValues, "event_id=?", new String[]{Long.toString(xVar.a)});
                if (update <= 0) {
                    e.e("Failed to update db, error code:" + Integer.toString(update));
                }
            }
        }
        this.d.getWritableDatabase().setTransactionSuccessful();
        this.b = (int) DatabaseUtils.queryNumEntries(this.d.getReadableDatabase(), "events");
        try {
            this.d.getWritableDatabase().endTransaction();
        } catch (SQLiteException e2) {
            e.e((Exception) e2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0060  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void c(java.util.List<com.tencent.stat.x> r11, int r12) {
        /*
            r10 = this;
            r9 = 0
            com.tencent.stat.w r0 = r10.d     // Catch: Throwable -> 0x006a, all -> 0x005d
            android.database.sqlite.SQLiteDatabase r0 = r0.getReadableDatabase()     // Catch: Throwable -> 0x006a, all -> 0x005d
            java.lang.String r1 = "events"
            r2 = 0
            java.lang.String r3 = "status=?"
            r4 = 1
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch: Throwable -> 0x006a, all -> 0x005d
            r5 = 0
            r6 = 1
            java.lang.String r6 = java.lang.Integer.toString(r6)     // Catch: Throwable -> 0x006a, all -> 0x005d
            r4[r5] = r6     // Catch: Throwable -> 0x006a, all -> 0x005d
            r5 = 0
            r6 = 0
            java.lang.String r7 = "event_id"
            java.lang.String r8 = java.lang.Integer.toString(r12)     // Catch: Throwable -> 0x006a, all -> 0x005d
            android.database.Cursor r7 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8)     // Catch: Throwable -> 0x006a, all -> 0x005d
        L_0x0023:
            boolean r0 = r7.moveToNext()     // Catch: Throwable -> 0x004a, all -> 0x0064
            if (r0 == 0) goto L_0x0057
            r0 = 0
            long r2 = r7.getLong(r0)     // Catch: Throwable -> 0x004a, all -> 0x0064
            r0 = 1
            java.lang.String r0 = r7.getString(r0)     // Catch: Throwable -> 0x004a, all -> 0x0064
            java.lang.String r4 = com.tencent.stat.common.k.d(r0)     // Catch: Throwable -> 0x004a, all -> 0x0064
            r0 = 2
            int r5 = r7.getInt(r0)     // Catch: Throwable -> 0x004a, all -> 0x0064
            r0 = 3
            int r6 = r7.getInt(r0)     // Catch: Throwable -> 0x004a, all -> 0x0064
            com.tencent.stat.x r1 = new com.tencent.stat.x     // Catch: Throwable -> 0x004a, all -> 0x0064
            r1.<init>(r2, r4, r5, r6)     // Catch: Throwable -> 0x004a, all -> 0x0064
            r11.add(r1)     // Catch: Throwable -> 0x004a, all -> 0x0064
            goto L_0x0023
        L_0x004a:
            r0 = move-exception
            r1 = r7
        L_0x004c:
            com.tencent.stat.common.StatLogger r2 = com.tencent.stat.n.e     // Catch: all -> 0x0067
            r2.e(r0)     // Catch: all -> 0x0067
            if (r1 == 0) goto L_0x0056
            r1.close()
        L_0x0056:
            return
        L_0x0057:
            if (r7 == 0) goto L_0x0056
            r7.close()
            goto L_0x0056
        L_0x005d:
            r0 = move-exception
        L_0x005e:
            if (r9 == 0) goto L_0x0063
            r9.close()
        L_0x0063:
            throw r0
        L_0x0064:
            r0 = move-exception
            r9 = r7
            goto L_0x005e
        L_0x0067:
            r0 = move-exception
            r9 = r1
            goto L_0x005e
        L_0x006a:
            r0 = move-exception
            r1 = r9
            goto L_0x004c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.stat.n.c(java.util.List, int):void");
    }

    public void e() {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("status", (Integer) 1);
            this.d.getWritableDatabase().update("events", contentValues, "status=?", new String[]{Long.toString(2L)});
            this.b = (int) DatabaseUtils.queryNumEntries(this.d.getReadableDatabase(), "events");
            e.i("Total " + this.b + " unsent events.");
        } catch (Throwable th) {
            e.e(th);
        }
    }

    private void f() {
        Cursor cursor;
        Cursor cursor2;
        try {
            cursor = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            cursor2 = this.d.getReadableDatabase().query("keyvalues", null, null, null, null, null, null);
            while (cursor2.moveToNext()) {
                try {
                    this.g.put(cursor2.getString(0), cursor2.getString(1));
                } catch (Throwable th2) {
                    th = th2;
                    e.e(th);
                    if (cursor2 != null) {
                        cursor2.close();
                        return;
                    }
                    return;
                }
            }
            if (cursor2 != null) {
                cursor2.close();
            }
        } catch (Throwable th3) {
            th = th3;
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    public int a() {
        return this.b;
    }

    public void a(int i) {
        this.a.post(new v(this, i));
    }

    public void a(e eVar, c cVar) {
        if (StatConfig.isEnableStatService()) {
            try {
                if (Thread.currentThread().getId() == this.a.getLooper().getThread().getId()) {
                    b(eVar, cVar);
                } else {
                    this.a.post(new r(this, eVar, cVar));
                }
            } catch (Throwable th) {
                e.e(th);
            }
        }
    }

    public void a(b bVar) {
        if (bVar != null) {
            this.a.post(new s(this, bVar));
        }
    }

    public void a(List<x> list) {
        try {
            if (Thread.currentThread().getId() == this.a.getLooper().getThread().getId()) {
                b(list);
            } else {
                this.a.post(new q(this, list));
            }
        } catch (SQLiteException e2) {
            e.e((Exception) e2);
        }
    }

    public void a(List<x> list, int i) {
        try {
            if (Thread.currentThread().getId() == this.a.getLooper().getThread().getId()) {
                b(list, i);
            } else {
                this.a.post(new p(this, list, i));
            }
        } catch (Throwable th) {
            e.e(th);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:71:0x01da A[Catch: all -> 0x01d3, TRY_ENTER, TryCatch #1 {, blocks: (B:3:0x0001, B:5:0x0007, B:52:0x018a, B:53:0x018d, B:65:0x01cf, B:71:0x01da, B:72:0x01dd), top: B:86:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized com.tencent.stat.DeviceInfo b(android.content.Context r20) {
        /*
            Method dump skipped, instructions count: 510
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.stat.n.b(android.content.Context):com.tencent.stat.DeviceInfo");
    }

    void c() {
        this.a.post(new t(this));
    }
}
