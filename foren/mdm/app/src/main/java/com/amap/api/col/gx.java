package com.amap.api.col;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: DBOperation.java */
/* loaded from: classes.dex */
public class gx {
    private static Map<Class<? extends gw>, gw> d = new HashMap();
    private hc a;
    private SQLiteDatabase b;
    private gw c;

    /* JADX WARN: Multi-variable type inference failed */
    public static synchronized gw a(Class<? extends gw> cls) throws IllegalAccessException, InstantiationException {
        gw gwVar;
        synchronized (gx.class) {
            if (d.get(cls) == null) {
                d.put(cls, cls.newInstance());
            }
            gwVar = d.get(cls);
        }
        return gwVar;
    }

    public gx(Context context, gw gwVar) {
        try {
            this.a = new hc(context.getApplicationContext(), gwVar.b(), null, gwVar.c(), gwVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        this.c = gwVar;
    }

    public static String a(Map<String, String> map) {
        if (map == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (String str : map.keySet()) {
            if (z) {
                sb.append(str).append(" = '").append(map.get(str)).append("'");
                z = false;
            } else {
                sb.append(" and ").append(str).append(" = '").append(map.get(str)).append("'");
                z = z;
            }
        }
        return sb.toString();
    }

    public <T> void a(String str, Class<T> cls) {
        synchronized (this.c) {
            String a = a(b(cls));
            if (!TextUtils.isEmpty(a)) {
                this.b = b(false);
                if (this.b != null) {
                    this.b.delete(a, str, null);
                    if (this.b != null) {
                        this.b.close();
                        this.b = null;
                    }
                }
            }
        }
    }

    public <T> void a(String str, Object obj, boolean z) {
        synchronized (this.c) {
            if (obj != null) {
                gy b = b(obj.getClass());
                String a = a(b);
                if (!TextUtils.isEmpty(a)) {
                    ContentValues a2 = a(obj, b);
                    if (a2 != null) {
                        this.b = b(z);
                        if (this.b != null) {
                            this.b.update(a, a2, str, null);
                            if (this.b != null) {
                                this.b.close();
                                this.b = null;
                            }
                        }
                    }
                }
            }
        }
    }

    public <T> void a(String str, Object obj) {
        a(str, obj, false);
    }

    public void a(Object obj, String str) {
        synchronized (this.c) {
            List b = b(str, obj.getClass());
            if (b == null || b.size() == 0) {
                a((gx) obj);
            } else {
                a(str, obj);
            }
        }
    }

    public <T> void a(T t) {
        a((gx) t, false);
    }

    public <T> void a(T t, boolean z) {
        synchronized (this.c) {
            this.b = b(z);
            if (this.b != null) {
                a(this.b, (SQLiteDatabase) t);
                if (this.b != null) {
                    this.b.close();
                    this.b = null;
                }
            }
        }
    }

    private <T> void a(SQLiteDatabase sQLiteDatabase, T t) {
        ContentValues a;
        gy b = b(t.getClass());
        String a2 = a(b);
        if (!TextUtils.isEmpty(a2) && t != null && sQLiteDatabase != null && (a = a(t, b)) != null) {
            sQLiteDatabase.insert(a2, null, a);
        }
    }

    public <T> void a(List<T> list) {
        synchronized (this.c) {
            if (list != null) {
                if (list.size() != 0) {
                    this.b = b(false);
                    if (this.b != null) {
                        this.b.beginTransaction();
                        for (T t : list) {
                            a(this.b, (SQLiteDatabase) t);
                        }
                        this.b.setTransactionSuccessful();
                        if (this.b.inTransaction()) {
                            this.b.endTransaction();
                        }
                        this.b.close();
                        this.b = null;
                    }
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v14, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r1v0, types: [java.lang.CharSequence, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public <T> java.util.List<T> a(java.lang.String r13, java.lang.Class<T> r14, boolean r15) {
        /*
            Method dump skipped, instructions count: 263
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.gx.a(java.lang.String, java.lang.Class, boolean):java.util.List");
    }

    public <T> List<T> b(String str, Class<T> cls) {
        return a(str, (Class) cls, false);
    }

    private <T> T a(Cursor cursor, Class<T> cls, gy gyVar) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Field[] a = a((Class<?>) cls, gyVar.b());
        Constructor<T> declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
        declaredConstructor.setAccessible(true);
        T newInstance = declaredConstructor.newInstance(new Object[0]);
        for (Field field : a) {
            field.setAccessible(true);
            Annotation annotation = field.getAnnotation(gz.class);
            if (annotation != null) {
                gz gzVar = (gz) annotation;
                int b = gzVar.b();
                int columnIndex = cursor.getColumnIndex(gzVar.a());
                switch (b) {
                    case 1:
                        field.set(newInstance, Short.valueOf(cursor.getShort(columnIndex)));
                        continue;
                    case 2:
                        field.set(newInstance, Integer.valueOf(cursor.getInt(columnIndex)));
                        continue;
                    case 3:
                        field.set(newInstance, Float.valueOf(cursor.getFloat(columnIndex)));
                        continue;
                    case 4:
                        field.set(newInstance, Double.valueOf(cursor.getDouble(columnIndex)));
                        continue;
                    case 5:
                        field.set(newInstance, Long.valueOf(cursor.getLong(columnIndex)));
                        continue;
                    case 6:
                        field.set(newInstance, cursor.getString(columnIndex));
                        continue;
                    case 7:
                        field.set(newInstance, cursor.getBlob(columnIndex));
                        continue;
                }
            }
        }
        return newInstance;
    }

    private void a(Object obj, Field field, ContentValues contentValues) {
        Annotation annotation = field.getAnnotation(gz.class);
        if (annotation != null) {
            gz gzVar = (gz) annotation;
            try {
                switch (gzVar.b()) {
                    case 1:
                        contentValues.put(gzVar.a(), Short.valueOf(field.getShort(obj)));
                        break;
                    case 2:
                        contentValues.put(gzVar.a(), Integer.valueOf(field.getInt(obj)));
                        break;
                    case 3:
                        contentValues.put(gzVar.a(), Float.valueOf(field.getFloat(obj)));
                        break;
                    case 4:
                        contentValues.put(gzVar.a(), Double.valueOf(field.getDouble(obj)));
                        break;
                    case 5:
                        contentValues.put(gzVar.a(), Long.valueOf(field.getLong(obj)));
                        break;
                    case 6:
                        contentValues.put(gzVar.a(), (String) field.get(obj));
                        break;
                    case 7:
                        contentValues.put(gzVar.a(), (byte[]) field.get(obj));
                        break;
                    default:
                        return;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private ContentValues a(Object obj, gy gyVar) {
        ContentValues contentValues = new ContentValues();
        Field[] a = a(obj.getClass(), gyVar.b());
        for (Field field : a) {
            field.setAccessible(true);
            a(obj, field, contentValues);
        }
        return contentValues;
    }

    private Field[] a(Class<?> cls, boolean z) {
        if (cls == null) {
            return null;
        }
        if (z) {
            return cls.getSuperclass().getDeclaredFields();
        }
        return cls.getDeclaredFields();
    }

    private SQLiteDatabase a(boolean z) {
        try {
            if (this.b == null) {
                this.b = this.a.getReadableDatabase();
            }
        } catch (Throwable th) {
            if (!z) {
                go.a(th, "DBOperation", "getReadAbleDataBase");
            } else {
                th.printStackTrace();
            }
        }
        return this.b;
    }

    private SQLiteDatabase b(boolean z) {
        try {
            if (this.b == null || this.b.isReadOnly()) {
                if (this.b != null) {
                    this.b.close();
                }
                this.b = this.a.getWritableDatabase();
            }
        } catch (Throwable th) {
            go.a(th, "DBOperation", "getWriteDatabase");
        }
        return this.b;
    }

    private boolean a(Annotation annotation) {
        return annotation != null;
    }

    private <T> String a(gy gyVar) {
        if (gyVar == null) {
            return null;
        }
        return gyVar.a();
    }

    private <T> gy b(Class<T> cls) {
        Annotation annotation = cls.getAnnotation(gy.class);
        if (!a(annotation)) {
            return null;
        }
        return (gy) annotation;
    }
}
