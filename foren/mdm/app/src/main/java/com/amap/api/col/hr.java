package com.amap.api.col;

import android.database.sqlite.SQLiteDatabase;

/* compiled from: DynamicFileDBCreator.java */
/* loaded from: classes.dex */
public class hr implements gw {
    private static hr a;

    public static synchronized hr a() {
        hr hrVar;
        synchronized (hr.class) {
            if (a == null) {
                a = new hr();
            }
            hrVar = a;
        }
        return hrVar;
    }

    private hr() {
    }

    @Override // com.amap.api.col.gw
    public String b() {
        return "dafile.db";
    }

    @Override // com.amap.api.col.gw
    public int c() {
        return 1;
    }

    @Override // com.amap.api.col.gw
    public void a(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS file (_id integer primary key autoincrement, sname  varchar(20), fname varchar(100),md varchar(20),version varchar(20),dversion varchar(20),status varchar(20),reservedfield varchar(20));");
        } catch (Throwable th) {
            hv.a(th, "DynamicFileDBCreator", "onCreate");
        }
    }

    @Override // com.amap.api.col.gw
    public void a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
