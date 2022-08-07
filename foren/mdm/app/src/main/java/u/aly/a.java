package u.aly;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import u.aly.aw;
import u.aly.d;

/* compiled from: CCSQLManager.java */
/* loaded from: classes2.dex */
public class a {
    public static boolean a(SQLiteDatabase sQLiteDatabase, String str) {
        try {
            sQLiteDatabase.execSQL("drop table if exists " + str);
            return true;
        } catch (SQLException e) {
            bo.e("delete table faild!");
            e.printStackTrace();
            return false;
        }
    }

    public static boolean b(SQLiteDatabase sQLiteDatabase, String str) {
        try {
            if (c(sQLiteDatabase, str) >= 0) {
                sQLiteDatabase.execSQL("delete from " + str);
            }
            return true;
        } catch (SQLException e) {
            bo.e("cleanTableData faild!" + e.toString());
            return false;
        }
    }

    public static int c(SQLiteDatabase sQLiteDatabase, String str) {
        Cursor cursor;
        try {
            cursor = null;
            int i = 0;
            try {
                cursor = sQLiteDatabase.rawQuery("select * from " + str, null);
                i = cursor.getCount();
            } catch (Exception e) {
                bo.e("count error " + e.toString());
                if (cursor != null) {
                    cursor.close();
                }
            }
            return i;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public static boolean a(SQLiteDatabase sQLiteDatabase, Collection<i> collection) {
        try {
            try {
                sQLiteDatabase.beginTransaction();
                if (c(sQLiteDatabase, d.a.b) > 0) {
                    b(sQLiteDatabase, d.a.b);
                }
                for (i iVar : collection) {
                    sQLiteDatabase.insert(d.a.b, null, a(iVar));
                }
                sQLiteDatabase.setTransactionSuccessful();
                sQLiteDatabase.endTransaction();
                return true;
            } catch (SQLException e) {
                bo.e("insert to Aggregated cache table faild!");
                sQLiteDatabase.endTransaction();
                return false;
            }
        } catch (Throwable th) {
            sQLiteDatabase.endTransaction();
            throw th;
        }
    }

    public static boolean a(f fVar, SQLiteDatabase sQLiteDatabase, Collection<i> collection) {
        try {
            try {
                sQLiteDatabase.beginTransaction();
                for (i iVar : collection) {
                    sQLiteDatabase.insert(d.a.a, null, a(iVar));
                }
                sQLiteDatabase.setTransactionSuccessful();
                b(sQLiteDatabase, d.a.b);
                fVar.a("success", false);
                sQLiteDatabase.endTransaction();
                return true;
            } catch (SQLException e) {
                bo.e("insert to Aggregated cache table faild!");
                sQLiteDatabase.endTransaction();
                return false;
            }
        } catch (Throwable th) {
            sQLiteDatabase.endTransaction();
            throw th;
        }
    }

    public static boolean a(SQLiteDatabase sQLiteDatabase, f fVar) {
        boolean z;
        try {
            z = false;
            sQLiteDatabase.beginTransaction();
            if (c(sQLiteDatabase, d.a.b) <= 0) {
                fVar.a("faild", false);
            } else {
                sQLiteDatabase.execSQL("insert into aggregated(key, count, value, totalTimestamp, timeWindowNum, label) select key, count, value, totalTimestamp, timeWindowNum, label from aggregated_cache");
                sQLiteDatabase.setTransactionSuccessful();
                b(sQLiteDatabase, d.a.b);
                fVar.a("success", false);
                sQLiteDatabase.endTransaction();
                z = true;
            }
        } catch (SQLException e) {
            fVar.a(false, false);
            bo.e("cacheToAggregatedTable happen " + e.toString());
        } finally {
            sQLiteDatabase.endTransaction();
        }
        return z;
    }

    private static ContentValues a(i iVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("key", iVar.a());
        contentValues.put("label", iVar.c());
        contentValues.put("count", Long.valueOf(iVar.g()));
        contentValues.put("value", Long.valueOf(iVar.f()));
        contentValues.put(d.a.C0129a.b, Long.valueOf(iVar.e()));
        contentValues.put(d.a.C0129a.f, iVar.h());
        return contentValues;
    }

    public static boolean b(SQLiteDatabase sQLiteDatabase, f fVar) {
        Cursor cursor;
        try {
            cursor = null;
            try {
                HashMap hashMap = new HashMap();
                Cursor rawQuery = sQLiteDatabase.rawQuery("select * from aggregated_cache", null);
                while (rawQuery.moveToNext()) {
                    i iVar = new i();
                    iVar.a(d.a(rawQuery.getString(rawQuery.getColumnIndex("key"))));
                    iVar.b(d.a(rawQuery.getString(rawQuery.getColumnIndex("label"))));
                    iVar.c(rawQuery.getInt(rawQuery.getColumnIndex("count")));
                    iVar.b(rawQuery.getInt(rawQuery.getColumnIndex("value")));
                    iVar.b(rawQuery.getString(rawQuery.getColumnIndex(d.a.C0129a.f)));
                    iVar.a(Long.parseLong(rawQuery.getString(rawQuery.getColumnIndex(d.a.C0129a.b))));
                    hashMap.put(d.a(rawQuery.getString(rawQuery.getColumnIndex("key"))), iVar);
                }
                if (hashMap.size() > 0) {
                    fVar.a(hashMap, false);
                } else {
                    fVar.a("faild", false);
                }
                if (rawQuery != null) {
                    rawQuery.close();
                }
            } catch (SQLException e) {
                fVar.a(false, false);
                bo.e("cacheToMemory happen " + e.toString());
                if (0 != 0) {
                    cursor.close();
                }
            }
            return false;
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    public static void a(SQLiteDatabase sQLiteDatabase, boolean z, f fVar) {
        b(sQLiteDatabase, d.c.a);
        b(sQLiteDatabase, d.a.a);
        if (!z) {
            b(sQLiteDatabase, d.b.a);
            fVar.a("success", false);
        }
    }

    public static void a(SQLiteDatabase sQLiteDatabase, String str, long j, long j2) {
        try {
            int c = c(sQLiteDatabase, d.c.a);
            int c2 = n.a().c();
            if (c < c2) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("key", str);
                contentValues.put(d.c.a.b, Long.valueOf(j2));
                contentValues.put("count", Long.valueOf(j));
                sQLiteDatabase.insert(d.c.a, null, contentValues);
            } else if (c == c2) {
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("key", com.umeng.analytics.a.f49u);
                contentValues2.put(d.c.a.b, Long.valueOf(System.currentTimeMillis()));
                contentValues2.put("count", (Integer) 1);
                sQLiteDatabase.insert(d.c.a, null, contentValues2);
            } else {
                d(sQLiteDatabase, com.umeng.analytics.a.f49u);
            }
        } catch (SQLException e) {
        }
    }

    private static void d(SQLiteDatabase sQLiteDatabase, String str) {
        try {
            sQLiteDatabase.beginTransaction();
            sQLiteDatabase.execSQL("update system set count=count+1 where key like '" + str + "'");
            sQLiteDatabase.setTransactionSuccessful();
            if (sQLiteDatabase != null) {
                sQLiteDatabase.endTransaction();
            }
        } catch (SQLException e) {
            if (sQLiteDatabase != null) {
                sQLiteDatabase.endTransaction();
            }
        } catch (Throwable th) {
            if (sQLiteDatabase != null) {
                sQLiteDatabase.endTransaction();
            }
            throw th;
        }
    }

    public static void a(f fVar, SQLiteDatabase sQLiteDatabase, List<String> list) {
        try {
            sQLiteDatabase.beginTransaction();
            if (c(sQLiteDatabase, d.b.a) > 0) {
                b(sQLiteDatabase, d.b.a);
            }
            for (String str : list) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(d.b.a.a, str);
                sQLiteDatabase.insert(d.b.a, null, contentValues);
            }
            sQLiteDatabase.setTransactionSuccessful();
            fVar.a("success", false);
        } catch (SQLException e) {
            bo.e("insertToLimitCKTable error " + e.toString());
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    public static void a(SQLiteDatabase sQLiteDatabase, Map<String, k> map, f fVar) {
        Cursor cursor;
        try {
            int i = 0;
            cursor = null;
            try {
                k kVar = map.get(com.umeng.analytics.a.r);
                if (kVar != null) {
                    cursor = sQLiteDatabase.rawQuery("select * from system where key=\"__ag_of\"", null);
                    cursor.moveToFirst();
                    long j = 0;
                    while (!cursor.isAfterLast()) {
                        if (cursor.getCount() > 0) {
                            i = cursor.getInt(cursor.getColumnIndex("count"));
                            j = cursor.getLong(cursor.getColumnIndex(d.c.a.b));
                            sQLiteDatabase.execSQL("delete from system where key=\"__ag_of\"");
                        }
                        cursor.moveToNext();
                    }
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("key", kVar.c());
                    contentValues.put("count", Long.valueOf(i == 0 ? kVar.e() : i + kVar.e()));
                    if (j == 0) {
                        j = kVar.d();
                    }
                    contentValues.put(d.c.a.b, Long.valueOf(j));
                    sQLiteDatabase.insert(d.c.a, null, contentValues);
                    fVar.a("success", false);
                    if (cursor != null) {
                        cursor.close();
                    }
                } else if (0 != 0) {
                    cursor.close();
                }
            } catch (SQLException e) {
                bo.e("save to system table error " + e.toString());
                if (cursor != null) {
                    cursor.close();
                }
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public static String a(SQLiteDatabase sQLiteDatabase) {
        Cursor cursor;
        Cursor cursor2;
        SQLException e;
        String str;
        try {
            cursor = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            sQLiteDatabase.beginTransaction();
            if (c(sQLiteDatabase, d.a.b) <= 0) {
                str = String.valueOf(0);
                if (0 != 0) {
                    cursor.close();
                }
                sQLiteDatabase.endTransaction();
            } else {
                cursor2 = sQLiteDatabase.rawQuery("select * from aggregated_cache", null);
                try {
                    if (cursor2.moveToLast()) {
                        str = cursor2.getString(cursor2.getColumnIndex(d.a.C0129a.f));
                    } else {
                        str = null;
                    }
                    try {
                        sQLiteDatabase.setTransactionSuccessful();
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        sQLiteDatabase.endTransaction();
                    } catch (SQLException e2) {
                        e = e2;
                        bo.e("queryLastTimeWindowNumFromCache error " + e.toString());
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        sQLiteDatabase.endTransaction();
                        return str;
                    }
                } catch (SQLException e3) {
                    str = null;
                    e = e3;
                }
            }
        } catch (SQLException e4) {
            cursor2 = null;
            str = null;
            e = e4;
        } catch (Throwable th2) {
            th = th2;
            if (0 != 0) {
                cursor.close();
            }
            sQLiteDatabase.endTransaction();
            throw th;
        }
        return str;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x00b1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.Map<java.lang.String, java.util.List<u.aly.aw.e>> b(android.database.sqlite.SQLiteDatabase r8) {
        /*
            r2 = 0
            java.lang.String r0 = "aggregated"
            int r0 = c(r8, r0)     // Catch: SQLException -> 0x00c8, all -> 0x00c2
            if (r0 <= 0) goto L_0x00bc
            java.lang.String r0 = "select * from aggregated"
            r1 = 0
            android.database.Cursor r3 = r8.rawQuery(r0, r1)     // Catch: SQLException -> 0x00c8, all -> 0x00c2
            java.util.HashMap r1 = new java.util.HashMap     // Catch: SQLException -> 0x0085, all -> 0x00ae
            r1.<init>()     // Catch: SQLException -> 0x0085, all -> 0x00ae
        L_0x0015:
            boolean r0 = r3.moveToNext()     // Catch: SQLException -> 0x0085, all -> 0x00ae
            if (r0 == 0) goto L_0x00b5
            java.lang.String r0 = "key"
            int r0 = r3.getColumnIndex(r0)     // Catch: SQLException -> 0x0085, all -> 0x00ae
            java.lang.String r4 = r3.getString(r0)     // Catch: SQLException -> 0x0085, all -> 0x00ae
            boolean r0 = r1.containsKey(r4)     // Catch: SQLException -> 0x0085, all -> 0x00ae
            if (r0 == 0) goto L_0x00a8
            java.lang.Object r0 = r1.get(r4)     // Catch: SQLException -> 0x0085, all -> 0x00ae
            java.util.List r0 = (java.util.List) r0     // Catch: SQLException -> 0x0085, all -> 0x00ae
            r1.remove(r4)     // Catch: SQLException -> 0x0085, all -> 0x00ae
        L_0x0034:
            u.aly.aw$e r5 = new u.aly.aw$e     // Catch: SQLException -> 0x0085, all -> 0x00ae
            r5.<init>()     // Catch: SQLException -> 0x0085, all -> 0x00ae
            java.lang.String r6 = "label"
            int r6 = r3.getColumnIndex(r6)     // Catch: SQLException -> 0x0085, all -> 0x00ae
            java.lang.String r6 = r3.getString(r6)     // Catch: SQLException -> 0x0085, all -> 0x00ae
            java.util.List r6 = u.aly.d.a(r6)     // Catch: SQLException -> 0x0085, all -> 0x00ae
            r5.e = r6     // Catch: SQLException -> 0x0085, all -> 0x00ae
            java.lang.String r6 = "value"
            int r6 = r3.getColumnIndex(r6)     // Catch: SQLException -> 0x0085, all -> 0x00ae
            long r6 = r3.getLong(r6)     // Catch: SQLException -> 0x0085, all -> 0x00ae
            r5.a = r6     // Catch: SQLException -> 0x0085, all -> 0x00ae
            java.lang.String r6 = "totalTimestamp"
            int r6 = r3.getColumnIndex(r6)     // Catch: SQLException -> 0x0085, all -> 0x00ae
            long r6 = r3.getLong(r6)     // Catch: SQLException -> 0x0085, all -> 0x00ae
            r5.b = r6     // Catch: SQLException -> 0x0085, all -> 0x00ae
            java.lang.String r6 = "timeWindowNum"
            int r6 = r3.getColumnIndex(r6)     // Catch: SQLException -> 0x0085, all -> 0x00ae
            java.lang.String r6 = r3.getString(r6)     // Catch: SQLException -> 0x0085, all -> 0x00ae
            int r6 = java.lang.Integer.parseInt(r6)     // Catch: SQLException -> 0x0085, all -> 0x00ae
            r5.c = r6     // Catch: SQLException -> 0x0085, all -> 0x00ae
            java.lang.String r6 = "count"
            int r6 = r3.getColumnIndex(r6)     // Catch: SQLException -> 0x0085, all -> 0x00ae
            long r6 = r3.getLong(r6)     // Catch: SQLException -> 0x0085, all -> 0x00ae
            int r6 = (int) r6     // Catch: SQLException -> 0x0085, all -> 0x00ae
            r5.d = r6     // Catch: SQLException -> 0x0085, all -> 0x00ae
            r0.add(r5)     // Catch: SQLException -> 0x0085, all -> 0x00ae
            r1.put(r4, r0)     // Catch: SQLException -> 0x0085, all -> 0x00ae
            goto L_0x0015
        L_0x0085:
            r0 = move-exception
            r1 = r3
        L_0x0087:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: all -> 0x00c5
            r3.<init>()     // Catch: all -> 0x00c5
            java.lang.String r4 = "readAllAggregatedDataForUpload error "
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch: all -> 0x00c5
            java.lang.String r0 = r0.toString()     // Catch: all -> 0x00c5
            java.lang.StringBuilder r0 = r3.append(r0)     // Catch: all -> 0x00c5
            java.lang.String r0 = r0.toString()     // Catch: all -> 0x00c5
            u.aly.bo.e(r0)     // Catch: all -> 0x00c5
            if (r1 == 0) goto L_0x00a6
            r1.close()
        L_0x00a6:
            r0 = r2
        L_0x00a7:
            return r0
        L_0x00a8:
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch: SQLException -> 0x0085, all -> 0x00ae
            r0.<init>()     // Catch: SQLException -> 0x0085, all -> 0x00ae
            goto L_0x0034
        L_0x00ae:
            r0 = move-exception
        L_0x00af:
            if (r3 == 0) goto L_0x00b4
            r3.close()
        L_0x00b4:
            throw r0
        L_0x00b5:
            if (r3 == 0) goto L_0x00ba
            r3.close()
        L_0x00ba:
            r0 = r1
            goto L_0x00a7
        L_0x00bc:
            if (r2 == 0) goto L_0x00a6
            r2.close()
            goto L_0x00a6
        L_0x00c2:
            r0 = move-exception
            r3 = r2
            goto L_0x00af
        L_0x00c5:
            r0 = move-exception
            r3 = r1
            goto L_0x00af
        L_0x00c8:
            r0 = move-exception
            r1 = r2
            goto L_0x0087
        */
        throw new UnsupportedOperationException("Method not decompiled: u.aly.a.b(android.database.sqlite.SQLiteDatabase):java.util.Map");
    }

    public static Map<String, List<aw.f>> a(f fVar, SQLiteDatabase sQLiteDatabase) {
        Throwable th;
        Cursor cursor;
        Cursor cursor2;
        Cursor cursor3;
        List arrayList;
        try {
            cursor = null;
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            HashMap hashMap = new HashMap();
            if (c(sQLiteDatabase, d.c.a) > 0) {
                cursor2 = sQLiteDatabase.rawQuery("select * from system", null);
                while (cursor2.moveToNext()) {
                    try {
                        String string = cursor2.getString(cursor2.getColumnIndex("key"));
                        if (hashMap.containsKey(string)) {
                            arrayList = (List) hashMap.get(string);
                            hashMap.remove(string);
                        } else {
                            arrayList = new ArrayList();
                        }
                        aw.f fVar2 = new aw.f();
                        fVar2.b = cursor2.getLong(cursor2.getColumnIndex(d.c.a.b));
                        fVar2.a = (int) cursor2.getLong(cursor2.getColumnIndex("count"));
                        arrayList.add(fVar2);
                        hashMap.put(string, arrayList);
                    } catch (SQLException e) {
                        e = e;
                        fVar.a("faild", false);
                        bo.e("readAllSystemDataForUpload error " + e.toString());
                        if (cursor2 == null) {
                            return null;
                        }
                        cursor2.close();
                        return null;
                    }
                }
                cursor3 = cursor2;
            } else {
                cursor3 = null;
            }
            if (cursor3 != null) {
                cursor3.close();
            }
            return hashMap;
        } catch (SQLException e2) {
            e = e2;
            cursor2 = null;
        } catch (Throwable th3) {
            th = th3;
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x005b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.List<java.lang.String> c(android.database.sqlite.SQLiteDatabase r5) {
        /*
            r1 = 0
            java.lang.String r0 = "limitedck"
            int r0 = c(r5, r0)     // Catch: SQLException -> 0x0061, all -> 0x0057
            if (r0 <= 0) goto L_0x0051
            java.lang.String r0 = "select * from limitedck"
            r2 = 0
            android.database.Cursor r2 = r5.rawQuery(r0, r2)     // Catch: SQLException -> 0x0061, all -> 0x0057
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch: SQLException -> 0x0029, all -> 0x005f
            r0.<init>()     // Catch: SQLException -> 0x0029, all -> 0x005f
        L_0x0015:
            boolean r3 = r2.moveToNext()     // Catch: SQLException -> 0x0029, all -> 0x005f
            if (r3 == 0) goto L_0x004b
            java.lang.String r3 = "ck"
            int r3 = r2.getColumnIndex(r3)     // Catch: SQLException -> 0x0029, all -> 0x005f
            java.lang.String r3 = r2.getString(r3)     // Catch: SQLException -> 0x0029, all -> 0x005f
            r0.add(r3)     // Catch: SQLException -> 0x0029, all -> 0x005f
            goto L_0x0015
        L_0x0029:
            r0 = move-exception
        L_0x002a:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: all -> 0x005f
            r3.<init>()     // Catch: all -> 0x005f
            java.lang.String r4 = "loadLimitCKFromDB error "
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch: all -> 0x005f
            java.lang.String r0 = r0.toString()     // Catch: all -> 0x005f
            java.lang.StringBuilder r0 = r3.append(r0)     // Catch: all -> 0x005f
            java.lang.String r0 = r0.toString()     // Catch: all -> 0x005f
            u.aly.bo.e(r0)     // Catch: all -> 0x005f
            if (r2 == 0) goto L_0x0049
            r2.close()
        L_0x0049:
            r0 = r1
        L_0x004a:
            return r0
        L_0x004b:
            if (r2 == 0) goto L_0x004a
            r2.close()
            goto L_0x004a
        L_0x0051:
            if (r1 == 0) goto L_0x0049
            r1.close()
            goto L_0x0049
        L_0x0057:
            r0 = move-exception
            r2 = r1
        L_0x0059:
            if (r2 == 0) goto L_0x005e
            r2.close()
        L_0x005e:
            throw r0
        L_0x005f:
            r0 = move-exception
            goto L_0x0059
        L_0x0061:
            r0 = move-exception
            r2 = r1
            goto L_0x002a
        */
        throw new UnsupportedOperationException("Method not decompiled: u.aly.a.c(android.database.sqlite.SQLiteDatabase):java.util.List");
    }
}
