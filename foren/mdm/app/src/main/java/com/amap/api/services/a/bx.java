package com.amap.api.services.a;

import android.database.sqlite.SQLiteDatabase;

/* compiled from: LogDBCreator.java */
/* loaded from: classes.dex */
public class bx implements bp {
    @Override // com.amap.api.services.a.bp
    public void a(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS a (_id integer primary key autoincrement, a1  varchar(20), a2 varchar(10),a3 varchar(50),a4 varchar(100),a5 varchar(20),a6 integer);");
            sQLiteDatabase.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s (_id integer primary key autoincrement,b1 varchar(40), b2 integer,b3  integer,a1  varchar(20));", "b"));
            sQLiteDatabase.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s (_id integer primary key autoincrement,b1 varchar(40), b2 integer,b3  integer,a1  varchar(20));", "c"));
            sQLiteDatabase.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s (_id integer primary key autoincrement,b1 varchar(40), b2 integer,b3  integer,a1  varchar(20));", "d"));
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS e (_id integer primary key autoincrement,c1 integer,c2 integer,c3 integer);");
        } catch (Throwable th) {
            bh.a(th, "DB", "onCreate");
        }
    }

    @Override // com.amap.api.services.a.bp
    public void a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    @Override // com.amap.api.services.a.bp
    public String a() {
        return "logdb.db";
    }

    @Override // com.amap.api.services.a.bp
    public int b() {
        return 1;
    }
}
