package com.amap.api.services.a;

import android.database.sqlite.SQLiteDatabase;

/* compiled from: DynamicFileDBCreator.java */
/* loaded from: classes.dex */
public class cm implements bp {
    private static cm a;

    public static synchronized cm c() {
        cm cmVar;
        synchronized (cm.class) {
            if (a == null) {
                a = new cm();
            }
            cmVar = a;
        }
        return cmVar;
    }

    private cm() {
    }

    @Override // com.amap.api.services.a.bp
    public String a() {
        return "dafile.db";
    }

    @Override // com.amap.api.services.a.bp
    public int b() {
        return 1;
    }

    @Override // com.amap.api.services.a.bp
    public void a(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS file (_id integer primary key autoincrement, sname  varchar(20), fname varchar(100),md varchar(20),version varchar(20),dversion varchar(20),status varchar(20),reservedfield varchar(20));");
        } catch (Throwable th) {
            co.a(th, "DynamicFileDBCreator", "onCreate");
        }
    }

    @Override // com.amap.api.services.a.bp
    public void a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
