package com.hyphenate.chat.a;

import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.hyphenate.util.EMLog;
import com.hyphenate.util.EasyUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class d {
    private static final String a = "EMMonitorDB";
    private static final int b = 1;
    private static final String c = "monitor.db";
    private static final String e = "apps";
    private static final String f = "appname";
    private static final String g = "create table apps (appname text primary key);";
    private b d;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class a extends ContextWrapper {
        private String dirPath;

        public a(Context context, String str) {
            super(context);
            this.dirPath = str;
        }

        @Override // android.content.ContextWrapper, android.content.Context
        public File getDatabasePath(String str) {
            File file = new File(this.dirPath + File.separator + str);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            return file;
        }

        @Override // android.content.ContextWrapper, android.content.Context
        public SQLiteDatabase openOrCreateDatabase(String str, int i, SQLiteDatabase.CursorFactory cursorFactory) {
            return SQLiteDatabase.openOrCreateDatabase(getDatabasePath(str), cursorFactory);
        }

        @Override // android.content.ContextWrapper, android.content.Context
        public SQLiteDatabase openOrCreateDatabase(String str, int i, SQLiteDatabase.CursorFactory cursorFactory, DatabaseErrorHandler databaseErrorHandler) {
            return SQLiteDatabase.openOrCreateDatabase(getDatabasePath(str).getAbsolutePath(), cursorFactory, databaseErrorHandler);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class b extends SQLiteOpenHelper {
        public b(Context context, String str, int i, String str2) throws HyphenateException {
            super(getCustomContext(context, str2), str, (SQLiteDatabase.CursorFactory) null, i);
        }

        private static a getCustomContext(Context context, String str) throws HyphenateException {
            if (EasyUtils.isSDCardExist()) {
                return new a(context, str);
            }
            throw new HyphenateException("sd card not exist");
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL(d.g);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        }
    }

    public d() {
        this.d = null;
        try {
            this.d = new b(EMClient.getInstance().getContext(), c, 1, Environment.getExternalStorageDirectory() + "/emlibs/libs");
        } catch (Exception e2) {
            EMLog.d(a, e2.getMessage());
        }
    }

    public List<String> a() {
        Cursor rawQuery;
        ArrayList arrayList = new ArrayList();
        try {
            if (!(this.d == null || (rawQuery = this.d.getReadableDatabase().rawQuery("select * from apps", null)) == null)) {
                while (rawQuery.moveToNext()) {
                    arrayList.add(rawQuery.getString(rawQuery.getColumnIndex(f)));
                }
                rawQuery.close();
            }
        } catch (Exception e2) {
            EMLog.e(a, e2.toString());
        }
        return arrayList;
    }

    public void a(String str) {
        try {
            if (this.d != null) {
                SQLiteDatabase writableDatabase = this.d.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put(f, str);
                writableDatabase.replace(e, null, contentValues);
            }
        } catch (Exception e2) {
            EMLog.e(a, e2.toString());
        }
    }

    public void b(String str) {
        try {
            if (this.d != null) {
                this.d.getWritableDatabase().delete(e, "appname = ?", new String[]{str});
            }
        } catch (Exception e2) {
            EMLog.e(a, e2.toString());
        }
    }
}
