package u.aly;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import u.aly.d;

/* compiled from: UMCCDBHelper.java */
/* loaded from: classes2.dex */
class c extends SQLiteOpenHelper {
    private static Context b;
    private String a;

    /* compiled from: UMCCDBHelper.java */
    /* loaded from: classes2.dex */
    private static class a {
        private static final c a = new c(c.b, d.a(c.b), d.c, null, 1);

        private a() {
        }
    }

    public static synchronized c a(Context context) {
        c cVar;
        synchronized (c.class) {
            b = context;
            cVar = a.a;
        }
        return cVar;
    }

    private c(Context context, String str, String str2, SQLiteDatabase.CursorFactory cursorFactory, int i) {
        this(new e(context, str), str2, cursorFactory, i);
    }

    private c(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i) {
        super(context, (str == null || str.equals("")) ? d.c : str, cursorFactory, i);
        b();
    }

    private void b() {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (!a(d.a.a, writableDatabase) || !a(d.a.b, writableDatabase)) {
                c(writableDatabase);
            }
            if (!a(d.c.a, writableDatabase)) {
                b(writableDatabase);
            }
            if (!a(d.b.a, writableDatabase)) {
                a(writableDatabase);
            }
        } catch (Exception e) {
        }
    }

    public boolean a(String str, SQLiteDatabase sQLiteDatabase) {
        Cursor cursor = null;
        boolean z = false;
        if (str != null) {
            try {
                cursor = sQLiteDatabase.rawQuery("select count(*) as c from sqlite_master where type ='table' and name ='" + str.trim() + "' ", null);
                if (cursor.moveToNext()) {
                    if (cursor.getInt(0) > 0) {
                        z = true;
                    }
                }
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Exception e) {
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        }
        return z;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            try {
                sQLiteDatabase.beginTransaction();
                c(sQLiteDatabase);
                b(sQLiteDatabase);
                a(sQLiteDatabase);
                sQLiteDatabase.setTransactionSuccessful();
                sQLiteDatabase.endTransaction();
            } catch (Exception e) {
                e.printStackTrace();
                sQLiteDatabase.endTransaction();
            }
        } catch (Throwable th) {
            sQLiteDatabase.endTransaction();
            throw th;
        }
    }

    private boolean a(SQLiteDatabase sQLiteDatabase) {
        try {
            this.a = "create table if not exists limitedck(Id INTEGER primary key autoincrement, ck TEXT unique)";
            sQLiteDatabase.execSQL(this.a);
            return true;
        } catch (SQLException e) {
            bo.e("create reference table error!");
            return false;
        }
    }

    private boolean b(SQLiteDatabase sQLiteDatabase) {
        try {
            this.a = "create table if not exists system(Id INTEGER primary key autoincrement, key TEXT, timeStamp INTEGER, count INTEGER)";
            sQLiteDatabase.execSQL(this.a);
            return true;
        } catch (SQLException e) {
            bo.e("create system table error!");
            return false;
        }
    }

    private boolean c(SQLiteDatabase sQLiteDatabase) {
        try {
            this.a = "create table if not exists aggregated_cache(Id INTEGER primary key autoincrement, key TEXT, totalTimestamp TEXT, value INTEGER, count INTEGER, label TEXT, timeWindowNum TEXT)";
            sQLiteDatabase.execSQL(this.a);
            this.a = "create table if not exists aggregated(Id INTEGER primary key autoincrement, key TEXT, totalTimestamp TEXT, value INTEGER, count INTEGER, label TEXT, timeWindowNum TEXT)";
            sQLiteDatabase.execSQL(this.a);
            return true;
        } catch (SQLException e) {
            bo.e("create aggregated table error!");
            return false;
        }
    }
}
