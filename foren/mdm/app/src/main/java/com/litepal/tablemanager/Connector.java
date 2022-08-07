package com.litepal.tablemanager;

import android.database.sqlite.SQLiteDatabase;
import com.litepal.LitePalApplication;
import com.litepal.exceptions.InvalidAttributesException;
import com.litepal.parser.LitePalAttr;
import com.litepal.parser.LitePalParser;

/* loaded from: classes2.dex */
public class Connector {
    private static LitePalAttr mLitePalAttr;
    private static LitePalOpenHelper mLitePalHelper;

    public static synchronized SQLiteDatabase getWritableDatabase() {
        SQLiteDatabase writableDatabase;
        synchronized (Connector.class) {
            writableDatabase = buildConnection().getWritableDatabase();
        }
        return writableDatabase;
    }

    public static synchronized SQLiteDatabase getReadableDatabase() {
        SQLiteDatabase readableDatabase;
        synchronized (Connector.class) {
            readableDatabase = buildConnection().getReadableDatabase();
        }
        return readableDatabase;
    }

    public static SQLiteDatabase getDatabase() {
        return getWritableDatabase();
    }

    private static LitePalOpenHelper buildConnection() {
        if (mLitePalAttr == null) {
            LitePalParser.parseLitePalConfiguration();
            mLitePalAttr = LitePalAttr.getInstance();
        }
        if (mLitePalAttr.checkSelfValid()) {
            if (mLitePalHelper == null) {
                String dbName = mLitePalAttr.getDbName();
                if ("external".equalsIgnoreCase(mLitePalAttr.getStorage())) {
                    dbName = LitePalApplication.getContext().getExternalFilesDir("") + "/databases/" + dbName;
                }
                mLitePalHelper = new LitePalOpenHelper(dbName, mLitePalAttr.getVersion());
            }
            return mLitePalHelper;
        }
        throw new InvalidAttributesException("Uncaught invalid attributes exception happened");
    }
}
