package com.yolanda.nohttp.cache;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.db.Field;

/* loaded from: classes2.dex */
class CacheDisk extends SQLiteOpenHelper implements Field {
    public static final String DATA = "data";
    public static final String DB_CACHE_NAME = "_nohttp_cache_db.db";
    public static final int DB_CACHE_VERSION = 1;
    public static final String HEAD = "head";
    public static final String KEY = "key";
    private static final String SQL_CREATE_TABLE = "CREATE TABLE cache_table(_id INTEGER PRIMARY KEY AUTOINCREMENT, key TEXT, head TEXT, data BLOB)";
    private static final String SQL_CREATE_UNIQUE_INDEX = "CREATE UNIQUE INDEX cache_unique_index ON cache_table(\"key\")";
    private static final String SQL_DELETE_TABLE = "DROP TABLE cache_table";
    private static final String SQL_DELETE_UNIQUE_INDEX = "DROP UNIQUE INDEX cache_unique_index";
    public static final String TABLE_NAME = "cache_table";

    public CacheDisk() {
        super(NoHttp.getContext(), DB_CACHE_NAME, (SQLiteDatabase.CursorFactory) null, 1);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        try {
            db.execSQL(SQL_CREATE_TABLE);
            db.execSQL(SQL_CREATE_UNIQUE_INDEX);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion != oldVersion) {
            db.beginTransaction();
            try {
                db.execSQL(SQL_DELETE_TABLE);
                db.execSQL(SQL_DELETE_UNIQUE_INDEX);
                db.execSQL(SQL_CREATE_TABLE);
                db.execSQL(SQL_CREATE_UNIQUE_INDEX);
                db.setTransactionSuccessful();
            } finally {
                db.endTransaction();
            }
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
