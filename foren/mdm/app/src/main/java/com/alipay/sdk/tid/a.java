package com.alipay.sdk.tid;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import com.alipay.sdk.encrypt.b;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* loaded from: classes.dex */
public final class a extends SQLiteOpenHelper {
    private static final String a = "msp.db";
    private static final int b = 1;
    private WeakReference<Context> c;

    public a(Context context) {
        super(context, a, (SQLiteDatabase.CursorFactory) null, 1);
        this.c = new WeakReference<>(context);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table if not exists tb_tid (name text primary key, tid text, key_tid text, dt datetime);");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("drop table if exists tb_tid");
        onCreate(sQLiteDatabase);
    }

    public final void a(String str, String str2, String str3, String str4) {
        SQLiteDatabase sQLiteDatabase = null;
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (a(writableDatabase, str, str2)) {
                a(writableDatabase, str, str2, str3, str4);
            } else {
                writableDatabase.execSQL("insert into tb_tid (name, tid, key_tid, dt) values (?, ?, ?, datetime('now', 'localtime'))", new Object[]{c(str, str2), b.a(1, str3, com.alipay.sdk.util.a.c(this.c.get())), str4});
                Cursor rawQuery = writableDatabase.rawQuery("select name from tb_tid where tid!='' order by dt asc", null);
                if (rawQuery.getCount() <= 14) {
                    rawQuery.close();
                } else {
                    int count = rawQuery.getCount() - 14;
                    String[] strArr = new String[count];
                    if (rawQuery.moveToFirst()) {
                        int i = 0;
                        do {
                            strArr[i] = rawQuery.getString(0);
                            i++;
                            if (!rawQuery.moveToNext()) {
                                break;
                            }
                        } while (count > i);
                    }
                    rawQuery.close();
                    for (int i2 = 0; i2 < count; i2++) {
                        if (!TextUtils.isEmpty(strArr[i2])) {
                            a(writableDatabase, strArr[i2]);
                        }
                    }
                }
            }
            if (writableDatabase != null && writableDatabase.isOpen()) {
                writableDatabase.close();
            }
        } catch (Exception e) {
            if (0 != 0 && sQLiteDatabase.isOpen()) {
                sQLiteDatabase.close();
            }
        } catch (Throwable th) {
            if (0 != 0 && sQLiteDatabase.isOpen()) {
                sQLiteDatabase.close();
            }
            throw th;
        }
    }

    private void d(String str, String str2) {
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = getWritableDatabase();
            a(sQLiteDatabase, str, str2, "", "");
            a(sQLiteDatabase, c(str, str2));
            if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                sQLiteDatabase.close();
            }
        } catch (Exception e) {
            if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                sQLiteDatabase.close();
            }
        } catch (Throwable th) {
            if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                sQLiteDatabase.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:50:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String a(java.lang.String r8, java.lang.String r9) {
        /*
            r7 = this;
            r0 = 0
            java.lang.String r1 = "select tid from tb_tid where name=?"
            android.database.sqlite.SQLiteDatabase r2 = r7.getReadableDatabase()     // Catch: Exception -> 0x0049, all -> 0x005e
            r3 = 1
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch: Exception -> 0x007b, all -> 0x0074
            r4 = 0
            java.lang.String r5 = c(r8, r9)     // Catch: Exception -> 0x007b, all -> 0x0074
            r3[r4] = r5     // Catch: Exception -> 0x007b, all -> 0x0074
            android.database.Cursor r1 = r2.rawQuery(r1, r3)     // Catch: Exception -> 0x007b, all -> 0x0074
            boolean r3 = r1.moveToFirst()     // Catch: Exception -> 0x007e, all -> 0x0079
            if (r3 == 0) goto L_0x0020
            r3 = 0
            java.lang.String r0 = r1.getString(r3)     // Catch: Exception -> 0x007e, all -> 0x0079
        L_0x0020:
            if (r1 == 0) goto L_0x0025
            r1.close()
        L_0x0025:
            if (r2 == 0) goto L_0x0030
            boolean r1 = r2.isOpen()
            if (r1 == 0) goto L_0x0030
            r2.close()
        L_0x0030:
            r1 = r0
        L_0x0031:
            boolean r0 = android.text.TextUtils.isEmpty(r1)
            if (r0 != 0) goto L_0x0048
            java.lang.ref.WeakReference<android.content.Context> r0 = r7.c
            java.lang.Object r0 = r0.get()
            android.content.Context r0 = (android.content.Context) r0
            java.lang.String r0 = com.alipay.sdk.util.a.c(r0)
            r2 = 2
            java.lang.String r1 = com.alipay.sdk.encrypt.b.a(r2, r1, r0)
        L_0x0048:
            return r1
        L_0x0049:
            r1 = move-exception
            r1 = r0
            r2 = r0
        L_0x004c:
            if (r1 == 0) goto L_0x0051
            r1.close()
        L_0x0051:
            if (r2 == 0) goto L_0x005c
            boolean r1 = r2.isOpen()
            if (r1 == 0) goto L_0x005c
            r2.close()
        L_0x005c:
            r1 = r0
            goto L_0x0031
        L_0x005e:
            r1 = move-exception
            r2 = r0
            r6 = r0
            r0 = r1
            r1 = r6
        L_0x0063:
            if (r1 == 0) goto L_0x0068
            r1.close()
        L_0x0068:
            if (r2 == 0) goto L_0x0073
            boolean r1 = r2.isOpen()
            if (r1 == 0) goto L_0x0073
            r2.close()
        L_0x0073:
            throw r0
        L_0x0074:
            r1 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
            goto L_0x0063
        L_0x0079:
            r0 = move-exception
            goto L_0x0063
        L_0x007b:
            r1 = move-exception
            r1 = r0
            goto L_0x004c
        L_0x007e:
            r3 = move-exception
            goto L_0x004c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.tid.a.a(java.lang.String, java.lang.String):java.lang.String");
    }

    private long e(String str, String str2) {
        SQLiteDatabase sQLiteDatabase;
        Throwable th;
        Cursor cursor = null;
        long j = 0;
        try {
            sQLiteDatabase = getReadableDatabase();
            try {
                cursor = sQLiteDatabase.rawQuery("select dt from tb_tid where name=?", new String[]{c(str, str2)});
                if (cursor.moveToFirst()) {
                    j = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(cursor.getString(0)).getTime();
                }
                if (cursor != null) {
                    cursor.close();
                }
                if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                    sQLiteDatabase.close();
                }
            } catch (Exception e) {
                if (cursor != null) {
                    cursor.close();
                }
                if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                    sQLiteDatabase.close();
                }
                return j;
            } catch (Throwable th2) {
                th = th2;
                if (cursor != null) {
                    cursor.close();
                }
                if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                    sQLiteDatabase.close();
                }
                throw th;
            }
        } catch (Exception e2) {
            sQLiteDatabase = null;
        } catch (Throwable th3) {
            th = th3;
            sQLiteDatabase = null;
        }
        return j;
    }

    private List<String> a() {
        SQLiteDatabase sQLiteDatabase;
        SQLiteDatabase sQLiteDatabase2;
        Cursor cursor;
        Throwable th;
        Cursor cursor2 = null;
        ArrayList arrayList = new ArrayList();
        try {
            sQLiteDatabase2 = getReadableDatabase();
            try {
                cursor = sQLiteDatabase2.rawQuery("select tid from tb_tid", null);
                while (cursor.moveToNext()) {
                    try {
                        String string = cursor.getString(0);
                        if (!TextUtils.isEmpty(string)) {
                            arrayList.add(b.a(2, string, com.alipay.sdk.util.a.c(this.c.get())));
                        }
                    } catch (Exception e) {
                        cursor2 = cursor;
                        sQLiteDatabase = sQLiteDatabase2;
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                            sQLiteDatabase.close();
                        }
                        return arrayList;
                    } catch (Throwable th2) {
                        th = th2;
                        if (cursor != null) {
                            cursor.close();
                        }
                        if (sQLiteDatabase2 != null && sQLiteDatabase2.isOpen()) {
                            sQLiteDatabase2.close();
                        }
                        throw th;
                    }
                }
                if (cursor != null) {
                    cursor.close();
                }
                if (sQLiteDatabase2 != null && sQLiteDatabase2.isOpen()) {
                    sQLiteDatabase2.close();
                }
            } catch (Exception e2) {
                sQLiteDatabase = sQLiteDatabase2;
            } catch (Throwable th3) {
                th = th3;
                cursor = null;
            }
        } catch (Exception e3) {
            sQLiteDatabase = null;
        } catch (Throwable th4) {
            sQLiteDatabase2 = null;
            th = th4;
            cursor = null;
        }
        return arrayList;
    }

    public final String b(String str, String str2) {
        SQLiteDatabase sQLiteDatabase;
        Cursor cursor;
        Throwable th;
        String str3 = null;
        try {
            sQLiteDatabase = getReadableDatabase();
            try {
                cursor = sQLiteDatabase.rawQuery("select key_tid from tb_tid where name=?", new String[]{c(str, str2)});
                try {
                    if (cursor.moveToFirst()) {
                        str3 = cursor.getString(0);
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                        sQLiteDatabase.close();
                    }
                } catch (Exception e) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                        sQLiteDatabase.close();
                    }
                    return str3;
                } catch (Throwable th2) {
                    th = th2;
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                        sQLiteDatabase.close();
                    }
                    throw th;
                }
            } catch (Exception e2) {
                cursor = null;
            } catch (Throwable th3) {
                th = th3;
                cursor = null;
            }
        } catch (Exception e3) {
            cursor = null;
            sQLiteDatabase = null;
        } catch (Throwable th4) {
            sQLiteDatabase = null;
            th = th4;
            cursor = null;
        }
        return str3;
    }

    private static boolean a(SQLiteDatabase sQLiteDatabase, String str, String str2) {
        int i;
        Cursor cursor = null;
        try {
            cursor = sQLiteDatabase.rawQuery("select count(*) from tb_tid where name=?", new String[]{c(str, str2)});
            i = cursor.moveToFirst() ? cursor.getInt(0) : 0;
            if (cursor != null) {
                cursor.close();
            }
        } catch (Exception e) {
            if (cursor != null) {
                cursor.close();
            }
            i = 0;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        return i > 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String c(String str, String str2) {
        return str + str2;
    }

    private void b(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, String str4) {
        sQLiteDatabase.execSQL("insert into tb_tid (name, tid, key_tid, dt) values (?, ?, ?, datetime('now', 'localtime'))", new Object[]{c(str, str2), b.a(1, str3, com.alipay.sdk.util.a.c(this.c.get())), str4});
        Cursor rawQuery = sQLiteDatabase.rawQuery("select name from tb_tid where tid!='' order by dt asc", null);
        if (rawQuery.getCount() <= 14) {
            rawQuery.close();
            return;
        }
        int count = rawQuery.getCount() - 14;
        String[] strArr = new String[count];
        if (rawQuery.moveToFirst()) {
            int i = 0;
            do {
                strArr[i] = rawQuery.getString(0);
                i++;
                if (!rawQuery.moveToNext()) {
                    break;
                }
            } while (count > i);
        }
        rawQuery.close();
        for (int i2 = 0; i2 < count; i2++) {
            if (!TextUtils.isEmpty(strArr[i2])) {
                a(sQLiteDatabase, strArr[i2]);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, String str4) {
        sQLiteDatabase.execSQL("update tb_tid set tid=?, key_tid=?, dt=datetime('now', 'localtime') where name=?", new Object[]{b.a(1, str3, com.alipay.sdk.util.a.c(this.c.get())), str4, c(str, str2)});
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(SQLiteDatabase sQLiteDatabase, String str) {
        try {
            sQLiteDatabase.delete("tb_tid", "name=?", new String[]{str});
        } catch (Exception e) {
        }
    }

    private static void a(SQLiteDatabase sQLiteDatabase) {
        Cursor rawQuery = sQLiteDatabase.rawQuery("select name from tb_tid where tid!='' order by dt asc", null);
        if (rawQuery.getCount() <= 14) {
            rawQuery.close();
            return;
        }
        int count = rawQuery.getCount() - 14;
        String[] strArr = new String[count];
        if (rawQuery.moveToFirst()) {
            int i = 0;
            do {
                strArr[i] = rawQuery.getString(0);
                i++;
                if (!rawQuery.moveToNext()) {
                    break;
                }
            } while (count > i);
        }
        rawQuery.close();
        for (int i2 = 0; i2 < count; i2++) {
            if (!TextUtils.isEmpty(strArr[i2])) {
                a(sQLiteDatabase, strArr[i2]);
            }
        }
    }
}
