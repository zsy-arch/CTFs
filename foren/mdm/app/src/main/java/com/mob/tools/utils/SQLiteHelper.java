package com.mob.tools.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.hyphenate.util.HanziToPinyin;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class SQLiteHelper {

    /* loaded from: classes2.dex */
    public static class SingleTableDB {
        private SQLiteDatabase db;
        private HashMap<String, Boolean> fieldLimits;
        private LinkedHashMap<String, String> fieldTypes;
        private String name;
        private String path;
        private String primary;
        private boolean primaryAutoincrement;

        private SingleTableDB(String path, String name) {
            this.path = path;
            this.name = name;
            this.fieldTypes = new LinkedHashMap<>();
            this.fieldLimits = new HashMap<>();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void close() {
            if (this.db != null) {
                this.db.close();
                this.db = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String getName() {
            return this.name;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void open() {
            if (this.db == null) {
                this.db = SQLiteDatabase.openOrCreateDatabase(new File(this.path), (SQLiteDatabase.CursorFactory) null);
                Cursor cursor = this.db.rawQuery("select count(*) from sqlite_master where type ='table' and name ='" + this.name + "' ", null);
                boolean shouldCreate = true;
                if (cursor != null) {
                    if (cursor.moveToNext() && cursor.getInt(0) > 0) {
                        shouldCreate = false;
                    }
                    cursor.close();
                }
                if (shouldCreate) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("create table  ").append(this.name).append("(");
                    for (Map.Entry<String, String> ent : this.fieldTypes.entrySet()) {
                        String field = ent.getKey();
                        String type = ent.getValue();
                        boolean notNull = this.fieldLimits.get(field).booleanValue();
                        boolean primary = field.equals(this.primary);
                        boolean autoincrement = primary ? this.primaryAutoincrement : false;
                        sb.append(field).append(HanziToPinyin.Token.SEPARATOR).append(type);
                        sb.append(notNull ? " not null" : "");
                        sb.append(primary ? " primary key" : "");
                        sb.append(autoincrement ? " autoincrement," : ",");
                    }
                    sb.replace(sb.length() - 1, sb.length(), ");");
                    this.db.execSQL(sb.toString());
                }
            }
        }

        public void addField(String fieldName, String fieldType, boolean notNull) {
            if (this.db == null) {
                this.fieldTypes.put(fieldName, fieldType);
                this.fieldLimits.put(fieldName, Boolean.valueOf(notNull));
            }
        }

        public void setPrimary(String fieldName, boolean autoincrement) {
            this.primary = fieldName;
            this.primaryAutoincrement = autoincrement;
        }
    }

    public static void close(SingleTableDB db) {
        db.close();
    }

    public static int delete(SingleTableDB db, String selection, String[] selectionArgs) throws Throwable {
        db.open();
        return db.db.delete(db.getName(), selection, selectionArgs);
    }

    public static void execSQL(SingleTableDB db, String script) throws Throwable {
        db.open();
        db.db.beginTransaction();
        try {
            db.db.execSQL(script);
            db.db.setTransactionSuccessful();
        } finally {
            db.db.endTransaction();
        }
    }

    public static SingleTableDB getDatabase(Context context, String name) {
        return getDatabase(context.getDatabasePath(name).getPath(), name);
    }

    public static SingleTableDB getDatabase(String path, String name) {
        return new SingleTableDB(path, name);
    }

    public static int getSize(SingleTableDB db) throws Throwable {
        db.open();
        int count = 0;
        Cursor c = null;
        try {
            c = db.db.rawQuery("select count(*) from " + db.getName(), null);
            if (c.moveToNext()) {
                count = c.getInt(0);
            }
            return count;
        } finally {
            c.close();
        }
    }

    public static long insert(SingleTableDB db, ContentValues values) throws Throwable {
        db.open();
        return db.db.replace(db.getName(), null, values);
    }

    public static Cursor query(SingleTableDB db, String[] columns, String selection, String[] selectionArgs, String sortOrder) throws Throwable {
        db.open();
        return db.db.query(db.getName(), columns, selection, selectionArgs, null, null, sortOrder);
    }

    public static Cursor rawQuery(SingleTableDB db, String script, String[] selectionArgs) throws Throwable {
        db.open();
        return db.db.rawQuery(script, selectionArgs);
    }

    public static int update(SingleTableDB db, ContentValues values, String selection, String[] selectionArgs) throws Throwable {
        db.open();
        return db.db.update(db.getName(), values, selection, selectionArgs);
    }
}
