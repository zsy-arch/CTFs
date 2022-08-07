package com.umeng.update.net;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import u.upd.n;

/* compiled from: DownloadTaskList.java */
/* loaded from: classes2.dex */
public class b {
    private static final String a = b.class.getName();
    private static final String b = "umeng_download_task_list";
    private static final String c = "UMENG_DATA";
    private static final String d = "cp";
    private static final String e = "url";
    private static final String f = "progress";
    private static final String g = "last_modified";
    private static final String h = "extra";
    private static Context i = null;
    private static final String j = "yyyy-MM-dd HH:mm:ss";
    private a k;

    private b() {
        this.k = new a(i);
    }

    /* compiled from: DownloadTaskList.java */
    /* renamed from: com.umeng.update.net.b$b  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    private static class C0092b {
        public static final b a = new b();

        private C0092b() {
        }
    }

    public static b a(Context context) {
        if (i == null && context == null) {
            throw new NullPointerException();
        }
        if (i == null) {
            i = context;
        }
        return C0092b.a;
    }

    public boolean a(String str, String str2) {
        Exception e2;
        boolean z;
        Cursor query;
        ContentValues contentValues = new ContentValues();
        contentValues.put("cp", str);
        contentValues.put("url", str2);
        contentValues.put("progress", (Integer) 0);
        contentValues.put(g, n.a());
        try {
            query = this.k.getReadableDatabase().query(b, new String[]{"progress"}, "cp=? and url=?", new String[]{str, str2}, null, null, null, "1");
            if (query.getCount() > 0) {
                u.upd.b.c(a, "insert(" + str + ", " + str2 + "):  already exists in the db. Insert is cancelled.");
                z = false;
            } else {
                long insert = this.k.getWritableDatabase().insert(b, null, contentValues);
                boolean z2 = insert != -1;
                try {
                    u.upd.b.c(a, "insert(" + str + ", " + str2 + "): rowid=" + insert);
                    z = z2;
                } catch (Exception e3) {
                    z = z2;
                    e2 = e3;
                    u.upd.b.c(a, "insert(" + str + ", " + str2 + "): " + e2.getMessage(), e2);
                    return z;
                }
            }
        } catch (Exception e4) {
            e2 = e4;
            z = false;
        }
        try {
            query.close();
        } catch (Exception e5) {
            e2 = e5;
            u.upd.b.c(a, "insert(" + str + ", " + str2 + "): " + e2.getMessage(), e2);
            return z;
        }
        return z;
    }

    public void a(String str, String str2, int i2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("progress", Integer.valueOf(i2));
        contentValues.put(g, n.a());
        this.k.getWritableDatabase().update(b, contentValues, "cp=? and url=?", new String[]{str, str2});
        u.upd.b.c(a, "updateProgress(" + str + ", " + str2 + ", " + i2 + ")");
    }

    public void a(String str, String str2, String str3) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(h, str3);
        contentValues.put(g, n.a());
        this.k.getWritableDatabase().update(b, contentValues, "cp=? and url=?", new String[]{str, str2});
        u.upd.b.c(a, "updateExtra(" + str + ", " + str2 + ", " + str3 + ")");
    }

    public int b(String str, String str2) {
        int i2;
        Cursor query = this.k.getReadableDatabase().query(b, new String[]{"progress"}, "cp=? and url=?", new String[]{str, str2}, null, null, null, "1");
        if (query.getCount() > 0) {
            query.moveToFirst();
            i2 = query.getInt(0);
        } else {
            i2 = -1;
        }
        query.close();
        return i2;
    }

    public String c(String str, String str2) {
        String str3 = null;
        Cursor query = this.k.getReadableDatabase().query(b, new String[]{h}, "cp=? and url=?", new String[]{str, str2}, null, null, null, "1");
        if (query.getCount() > 0) {
            query.moveToFirst();
            str3 = query.getString(0);
        }
        query.close();
        return str3;
    }

    public Date d(String str, String str2) {
        Date date = null;
        Cursor query = this.k.getReadableDatabase().query(b, new String[]{g}, "cp=? and url=?", new String[]{str, str2}, null, null, null, null);
        if (query.getCount() > 0) {
            query.moveToFirst();
            String string = query.getString(0);
            u.upd.b.c(a, "getLastModified(" + str + ", " + str2 + "): " + string);
            try {
                date = new SimpleDateFormat(j).parse(string);
            } catch (Exception e2) {
                u.upd.b.c(a, e2.getMessage());
            }
        }
        query.close();
        return date;
    }

    public void e(String str, String str2) {
        this.k.getWritableDatabase().delete(b, "cp=? and url=?", new String[]{str, str2});
        u.upd.b.c(a, "delete(" + str + ", " + str2 + ")");
    }

    public List<String> a(String str) {
        Cursor query = this.k.getReadableDatabase().query(b, new String[]{"url"}, "cp=?", new String[]{str}, null, null, null, "1");
        ArrayList arrayList = new ArrayList();
        query.moveToFirst();
        while (!query.isAfterLast()) {
            arrayList.add(query.getString(0));
            query.moveToNext();
        }
        query.close();
        return arrayList;
    }

    public void a(int i2) {
        try {
            Date date = new Date(new Date().getTime() - (i2 * 1000));
            this.k.getWritableDatabase().execSQL(" DELETE FROM umeng_download_task_list WHERE strftime('yyyy-MM-dd HH:mm:ss', last_modified)<=strftime('yyyy-MM-dd HH:mm:ss', '" + new SimpleDateFormat(j).format(date) + "')");
            u.upd.b.c(a, "clearOverdueTasks(" + i2 + ") remove all tasks before " + new SimpleDateFormat(j).format(date));
        } catch (Exception e2) {
            u.upd.b.b(a, e2.getMessage());
        }
    }

    public void finalize() {
        this.k.close();
    }

    /* compiled from: DownloadTaskList.java */
    /* loaded from: classes2.dex */
    class a extends SQLiteOpenHelper {
        private static final int b = 2;
        private static final String c = "CREATE TABLE umeng_download_task_list (cp TEXT, url TEXT, progress INTEGER, extra TEXT, last_modified TEXT, UNIQUE (cp,url) ON CONFLICT ABORT);";

        a(Context context) {
            super(context, b.c, (SQLiteDatabase.CursorFactory) null, 2);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            u.upd.b.c(b.a, c);
            sQLiteDatabase.execSQL(c);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        }
    }
}
