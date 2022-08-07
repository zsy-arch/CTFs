package com.amap.api.services.a;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* compiled from: DB.java */
/* loaded from: classes.dex */
public class bv extends SQLiteOpenHelper {
    private bp a;

    public bv(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i, bp bpVar) {
        super(context, str, cursorFactory, i);
        this.a = bpVar;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        this.a.a(sQLiteDatabase);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        this.a.a(sQLiteDatabase, i, i2);
    }
}
