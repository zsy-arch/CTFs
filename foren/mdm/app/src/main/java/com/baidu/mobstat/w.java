package com.baidu.mobstat;

import android.content.ContentValues;
import android.database.Cursor;
import java.io.Closeable;
import java.util.ArrayList;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class w implements Closeable {
    private ae a;

    public w(String str, String str2) {
        ad adVar = new ad();
        this.a = new ae(adVar, str);
        if (adVar.getDatabasePath(".confd") != null) {
            a(str2);
        }
    }

    private void a(String str) {
        this.a.a(str);
    }

    protected long a(ContentValues contentValues) {
        return this.a.a((String) null, contentValues);
    }

    public abstract long a(String str, String str2);

    protected Cursor a(String str, int i, int i2) {
        return this.a.a(null, null, null, null, null, str + " desc", i2 + ", " + i);
    }

    protected Cursor a(String str, String str2, String str3, int i) {
        return this.a.a(null, str + "=? ", new String[]{str2}, null, null, str3 + " desc", i + "");
    }

    public abstract ArrayList<v> a(int i, int i2);

    public synchronized boolean a() {
        boolean z;
        try {
            z = this.a.a();
        } catch (Exception e) {
            bb.b(e);
            z = false;
        }
        return z;
    }

    protected boolean a(long j) {
        return this.a.a("_id=? ", new String[]{new StringBuilder().append(j).append("").toString()}) > 0;
    }

    public int b() {
        return this.a.b();
    }

    public abstract boolean b(long j);

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        try {
            this.a.close();
        } catch (Exception e) {
            bb.b(e);
        }
    }
}
