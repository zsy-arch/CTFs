package com.yolanda.nohttp.cookie;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.db.Field;

/* loaded from: classes2.dex */
class CookieDisk extends SQLiteOpenHelper implements Field {
    public static final String COMMENT = "comment";
    public static final String COMMENT_URL = "comment_url";
    public static final String DB_COOKIE_NAME = "_nohttp_cookies_db.db";
    public static final int DB_COOKIE_VERSION = 1;
    public static final String DISCARD = "discard";
    public static final String DOMAIN = "domain";
    public static final String EXPIRY = "expiry";
    public static final String NAME = "name";
    public static final String PATH = "path";
    public static final String PORT_LIST = "portlist";
    public static final String SECURE = "secure";
    private static final String SQL_CREATE_TABLE = "CREATE TABLE cookies_table(_id INTEGER PRIMARY KEY AUTOINCREMENT, uri TEXT, name TEXT, value TEXT, comment TEXT, comment_url TEXT, discard TEXT, domain TEXT, expiry INTEGER, path TEXT, portlist TEXT, secure TEXT, version INTEGER)";
    private static final String SQL_CREATE_UNIQUE_INDEX = "CREATE UNIQUE INDEX cookie_unique_index ON cookies_table(\"name\", \"domain\", \"path\")";
    private static final String SQL_DELETE_TABLE = "DROP TABLE cookies_table";
    private static final String SQL_DELETE_UNIQUE_INDEX = "DROP UNIQUE INDEX cookie_unique_index";
    public static final String TABLE_NAME = "cookies_table";
    public static final String URI = "uri";
    public static final String VALUE = "value";
    public static final String VERSION = "version";

    public CookieDisk() {
        super(NoHttp.getContext(), DB_COOKIE_NAME, (SQLiteDatabase.CursorFactory) null, 1);
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
