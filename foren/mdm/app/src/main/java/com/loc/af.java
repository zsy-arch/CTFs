package com.loc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.text.TextUtils;
import com.loc.ak;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: DBOperation.java */
/* loaded from: classes2.dex */
public final class af {
    private static Map<Class<? extends ae>, ae> d = new HashMap();
    private ak a;
    private SQLiteDatabase b;
    private ae c;

    public af(Context context, ae aeVar) {
        try {
            this.a = new ak(context.getApplicationContext(), aeVar.a(), aeVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        this.c = aeVar;
    }

    public af(Context context, ae aeVar, String str) {
        try {
            boolean equals = "mounted".equals(Environment.getExternalStorageState());
            if (!TextUtils.isEmpty(str) && equals) {
                context = new ak.a(context.getApplicationContext(), str);
            }
            this.a = new ak(context, aeVar.a(), aeVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        this.c = aeVar;
    }

    private static ContentValues a(Object obj, ag agVar) {
        ContentValues contentValues = new ContentValues();
        Field[] a = a(obj.getClass(), agVar.b());
        for (Field field : a) {
            field.setAccessible(true);
            Annotation annotation = field.getAnnotation(ah.class);
            if (annotation != null) {
                ah ahVar = (ah) annotation;
                switch (ahVar.b()) {
                    case 1:
                        contentValues.put(ahVar.a(), Short.valueOf(field.getShort(obj)));
                        continue;
                    case 2:
                        try {
                            contentValues.put(ahVar.a(), Integer.valueOf(field.getInt(obj)));
                            continue;
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                            break;
                        }
                    case 3:
                        contentValues.put(ahVar.a(), Float.valueOf(field.getFloat(obj)));
                        continue;
                    case 4:
                        contentValues.put(ahVar.a(), Double.valueOf(field.getDouble(obj)));
                        continue;
                    case 5:
                        contentValues.put(ahVar.a(), Long.valueOf(field.getLong(obj)));
                        continue;
                    case 6:
                        contentValues.put(ahVar.a(), (String) field.get(obj));
                        continue;
                    case 7:
                        contentValues.put(ahVar.a(), (byte[]) field.get(obj));
                        continue;
                }
            }
        }
        return contentValues;
    }

    private SQLiteDatabase a() {
        try {
            if (this.b == null || this.b.isReadOnly()) {
                if (this.b != null) {
                    this.b.close();
                }
                this.b = this.a.getWritableDatabase();
            }
        } catch (Throwable th) {
            w.a(th, "DBOperation", "getWriteDatabase");
        }
        return this.b;
    }

    private SQLiteDatabase a(boolean z) {
        try {
            if (this.b == null) {
                this.b = this.a.getReadableDatabase();
            }
        } catch (Throwable th) {
            if (!z) {
                w.a(th, "DBOperation", "getReadAbleDataBase");
            } else {
                th.printStackTrace();
            }
        }
        return this.b;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static synchronized ae a(Class<? extends ae> cls) throws IllegalAccessException, InstantiationException {
        ae aeVar;
        synchronized (af.class) {
            if (d.get(cls) == null) {
                d.put(cls, cls.newInstance());
            }
            aeVar = d.get(cls);
        }
        return aeVar;
    }

    private static <T> T a(Cursor cursor, Class<T> cls, ag agVar) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Field[] a = a((Class<?>) cls, agVar.b());
        Constructor<T> declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
        declaredConstructor.setAccessible(true);
        T newInstance = declaredConstructor.newInstance(new Object[0]);
        for (Field field : a) {
            field.setAccessible(true);
            Annotation annotation = field.getAnnotation(ah.class);
            if (annotation != null) {
                ah ahVar = (ah) annotation;
                int b = ahVar.b();
                int columnIndex = cursor.getColumnIndex(ahVar.a());
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

    private static <T> String a(ag agVar) {
        if (agVar == null) {
            return null;
        }
        return agVar.a();
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
            }
        }
        return sb.toString();
    }

    private static Field[] a(Class<?> cls, boolean z) {
        if (cls == null) {
            return null;
        }
        return z ? cls.getSuperclass().getDeclaredFields() : cls.getDeclaredFields();
    }

    private static <T> ag b(Class<T> cls) {
        Annotation annotation = cls.getAnnotation(ag.class);
        if (!(annotation != null)) {
            return null;
        }
        return (ag) annotation;
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
    public final <T> java.util.List<T> a(java.lang.String r13, java.lang.Class<T> r14, boolean r15) {
        /*
            Method dump skipped, instructions count: 266
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.af.a(java.lang.String, java.lang.Class, boolean):java.util.List");
    }

    public final <T> void a(T t) {
        b((af) t);
    }

    public final void a(Object obj, String str) {
        synchronized (this.c) {
            List a = a(str, (Class) obj.getClass(), false);
            if (a == null || a.size() == 0) {
                b((af) obj);
            } else {
                a(str, obj, false);
            }
        }
    }

    public final <T> void a(String str, Class<T> cls) {
        synchronized (this.c) {
            String a = a(b((Class) cls));
            if (!TextUtils.isEmpty(a)) {
                this.b = a();
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

    public final <T> void a(String str, Object obj) {
        a(str, obj, false);
    }

    public final <T> void a(String str, Object obj, boolean z) {
        synchronized (this.c) {
            if (obj != null) {
                ag b = b((Class) obj.getClass());
                String a = a(b);
                if (!TextUtils.isEmpty(a)) {
                    ContentValues a2 = a(obj, b);
                    if (a2 != null) {
                        this.b = a();
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

    public final <T> List<T> b(String str, Class<T> cls) {
        return a(str, (Class) cls, false);
    }

    public final <T> void b(T t) {
        ContentValues a;
        synchronized (this.c) {
            this.b = a();
            if (this.b != null) {
                SQLiteDatabase sQLiteDatabase = this.b;
                ag b = b((Class) t.getClass());
                String a2 = a(b);
                if (!(TextUtils.isEmpty(a2) || t == null || sQLiteDatabase == null || (a = a(t, b)) == null)) {
                    sQLiteDatabase.insert(a2, null, a);
                }
                if (this.b != null) {
                    this.b.close();
                    this.b = null;
                }
            }
        }
    }
}
