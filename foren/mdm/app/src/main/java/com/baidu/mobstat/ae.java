package com.baidu.mobstat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* loaded from: classes.dex */
class ae extends SQLiteOpenHelper {
    private String a;
    private SQLiteDatabase b;

    public ae(Context context, String str) {
        super(context, ".confd", (SQLiteDatabase.CursorFactory) null, 1);
        this.a = str;
    }

    public int a(String str, String[] strArr) {
        return this.b.delete(this.a, str, strArr);
    }

    public long a(String str, ContentValues contentValues) {
        return this.b.insert(this.a, str, contentValues);
    }

    public Cursor a(String[] strArr, String str, String[] strArr2, String str2, String str3, String str4, String str5) {
        return this.b.query(this.a, strArr, str, strArr2, str2, str3, str4, str5);
    }

    public void a(String str) {
        getWritableDatabase().execSQL(str);
    }

    public synchronized boolean a() {
        boolean z = true;
        synchronized (this) {
            if (this.b == null ? true : !this.b.isOpen()) {
                try {
                    this.b = getWritableDatabase();
                } catch (NullPointerException e) {
                    throw new NullPointerException("db path is null");
                }
            }
            if (this.b == null || !this.b.isOpen()) {
                z = false;
            }
        }
        return z;
    }

    public final int b() {
        Cursor cursor = null;
        int i = 0;
        try {
            cursor = this.b.rawQuery("SELECT COUNT(*) FROM " + this.a, null);
            if (cursor != null && cursor.moveToNext()) {
                i = cursor.getInt(0);
            } else if (cursor != null) {
                cursor.close();
            }
            return i;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper, java.lang.AutoCloseable
    public synchronized void close() {
        super.close();
        if (this.b != null) {
            this.b.close();
            this.b = null;
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public synchronized SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase();
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public synchronized SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase();
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        this.b = sQLiteDatabase;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onOpen(SQLiteDatabase sQLiteDatabase) {
        super.onOpen(sQLiteDatabase);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
