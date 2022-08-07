package com.loc;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.File;
import java.io.IOException;

/* compiled from: DB.java */
/* loaded from: classes2.dex */
public final class ak extends SQLiteOpenHelper {
    private ae a;

    /* compiled from: DB.java */
    /* loaded from: classes2.dex */
    public static class a extends ContextWrapper {
        private String a;
        private Context b;

        public a(Context context, String str) {
            super(context);
            this.a = str;
            this.b = context;
        }

        @Override // android.content.ContextWrapper, android.content.Context
        public final File getDatabasePath(String str) {
            try {
                String str2 = this.a + "/" + str;
                File file = new File(this.a);
                if (!file.exists()) {
                    file.mkdirs();
                }
                boolean z = false;
                File file2 = new File(str2);
                if (!file2.exists()) {
                    try {
                        z = file2.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    z = true;
                }
                if (z) {
                    return file2;
                }
                return null;
            } catch (Throwable th) {
                th.printStackTrace();
                return null;
            }
        }

        @Override // android.content.ContextWrapper, android.content.Context
        public final SQLiteDatabase openOrCreateDatabase(String str, int i, SQLiteDatabase.CursorFactory cursorFactory) {
            SQLiteDatabase sQLiteDatabase;
            if (getDatabasePath(str) != null) {
                try {
                    sQLiteDatabase = SQLiteDatabase.openOrCreateDatabase(getDatabasePath(str), (SQLiteDatabase.CursorFactory) null);
                } catch (Throwable th) {
                    th.printStackTrace();
                    sQLiteDatabase = null;
                }
                if (sQLiteDatabase != null) {
                    return sQLiteDatabase;
                }
            }
            return SQLiteDatabase.openOrCreateDatabase(this.b.getDatabasePath(str), (SQLiteDatabase.CursorFactory) null);
        }

        @Override // android.content.ContextWrapper, android.content.Context
        public final SQLiteDatabase openOrCreateDatabase(String str, int i, SQLiteDatabase.CursorFactory cursorFactory, DatabaseErrorHandler databaseErrorHandler) {
            SQLiteDatabase sQLiteDatabase;
            if (getDatabasePath(str) != null) {
                try {
                    sQLiteDatabase = SQLiteDatabase.openOrCreateDatabase(getDatabasePath(str), (SQLiteDatabase.CursorFactory) null);
                } catch (Throwable th) {
                    th.printStackTrace();
                    sQLiteDatabase = null;
                }
                if (sQLiteDatabase != null) {
                    return sQLiteDatabase;
                }
            }
            return SQLiteDatabase.openOrCreateDatabase(this.b.getDatabasePath(str), (SQLiteDatabase.CursorFactory) null);
        }
    }

    public ak(Context context, String str, ae aeVar) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, 1);
        this.a = aeVar;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        this.a.a(sQLiteDatabase);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        ae aeVar = this.a;
    }
}
