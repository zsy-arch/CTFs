package com.loc;

import android.database.sqlite.SQLiteDatabase;

/* compiled from: SdCardDBCreator.java */
/* loaded from: classes2.dex */
public class ck implements ae {
    @Override // com.loc.ae
    public final String a() {
        return "alsn.db";
    }

    @Override // com.loc.ae
    public final void a(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS c (_id integer primary key autoincrement, a2 varchar(100), a4 varchar(2000), a3 LONG );");
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS b (_id integer primary key autoincrement, b1 integer );");
        } catch (Throwable th) {
            f.a(th, "SdCardDBCreator", "onCreate");
        }
    }
}
