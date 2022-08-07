package com.baidu.mobstat;

import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import java.io.File;
import java.io.IOException;

/* loaded from: classes.dex */
class ad extends ContextWrapper {
    public ad() {
        super(null);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public File getDatabasePath(String str) {
        if (!"mounted".equals(cl.a())) {
            return null;
        }
        String str2 = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "backups/system";
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(str2 + File.separator + str);
        if (!file2.exists()) {
            try {
                file2.createNewFile();
            } catch (IOException e) {
                bb.b(e);
            }
        }
        if (!file2.exists()) {
            file2 = null;
        }
        return file2;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public SQLiteDatabase openOrCreateDatabase(String str, int i, SQLiteDatabase.CursorFactory cursorFactory) {
        File databasePath = getDatabasePath(str);
        if (databasePath != null) {
            return SQLiteDatabase.openOrCreateDatabase(databasePath, (SQLiteDatabase.CursorFactory) null);
        }
        throw new NullPointerException("db path is null");
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public SQLiteDatabase openOrCreateDatabase(String str, int i, SQLiteDatabase.CursorFactory cursorFactory, DatabaseErrorHandler databaseErrorHandler) {
        File databasePath = getDatabasePath(str);
        if (databasePath != null) {
            return SQLiteDatabase.openOrCreateDatabase(databasePath, (SQLiteDatabase.CursorFactory) null);
        }
        throw new NullPointerException("db path is null");
    }
}
