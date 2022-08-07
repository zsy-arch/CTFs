package com.litepal.tablemanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.litepal.LitePalApplication;
import com.litepal.util.SharedUtil;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class LitePalOpenHelper extends SQLiteOpenHelper {
    public static final String TAG = "LitePalHelper";

    LitePalOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LitePalOpenHelper(String dbName, int version) {
        this(LitePalApplication.getContext(), dbName, null, version);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase db) {
        Generator.create(db);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Generator.upgrade(db);
        SharedUtil.updateVersion(newVersion);
    }
}
