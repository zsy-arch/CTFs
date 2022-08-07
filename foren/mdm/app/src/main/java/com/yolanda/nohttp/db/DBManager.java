package com.yolanda.nohttp.db;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.db.DBId;
import java.util.List;

/* loaded from: classes2.dex */
public abstract class DBManager<T extends DBId> {
    private static final boolean DEBUG = false;
    private SQLiteOpenHelper disk;

    public abstract List<T> get(String str);

    protected abstract String getTableName();

    public abstract long replace(T t);

    public DBManager(SQLiteOpenHelper disk) {
        this.disk = disk;
    }

    protected final SQLiteDatabase openReader() {
        return this.disk.getReadableDatabase();
    }

    protected final SQLiteDatabase openWriter() {
        return this.disk.getWritableDatabase();
    }

    protected final void readFinish(SQLiteDatabase execute, Cursor cursor) {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        writeFinish(execute);
    }

    protected final void writeFinish(SQLiteDatabase execute) {
        if (execute != null && execute.isOpen()) {
            execute.close();
        }
    }

    public final int count() {
        return countColumn(Field.ID);
    }

    public final int countColumn(String columnName) {
        return count("SELECT COUNT(" + columnName + ") FROM " + getTableName());
    }

    public final int count(String sql) {
        SQLiteDatabase execute = openReader();
        print(sql);
        Cursor cursor = execute.rawQuery(sql, null);
        int count = 0;
        if (cursor.moveToNext()) {
            count = cursor.getInt(0);
        }
        readFinish(execute, cursor);
        return count;
    }

    public final boolean deleteAll() {
        return delete("1=1");
    }

    public final boolean delete(List<T> ts) {
        StringBuilder where = new StringBuilder(Field.ID).append(" IN(");
        for (T t : ts) {
            long id = t.getId();
            if (id > 0) {
                where.append(',');
                where.append(id);
            }
        }
        where.append(')');
        if (',' == where.charAt(6)) {
            where.deleteCharAt(6);
        }
        return delete(where.toString());
    }

    public final boolean delete(String where) {
        if (TextUtils.isEmpty(where)) {
            return true;
        }
        SQLiteDatabase execute = openWriter();
        boolean result = true;
        try {
            String sql = "DELETE FROM " + getTableName() + " WHERE " + where;
            print(sql);
            execute.execSQL(sql);
        } catch (SQLException e) {
            Logger.e(e);
            result = false;
        }
        writeFinish(execute);
        return result;
    }

    public final List<T> getAll() {
        return getAll("*");
    }

    public final List<T> getAll(String columnName) {
        return get(columnName, null, null, null, null);
    }

    public final List<T> get(String columnName, String where, String orderBy, String limit, String offset) {
        return get(getSelectSql(columnName, where, orderBy, limit, offset));
    }

    private String getSelectSql(String columnName, String where, String orderBy, String limit, String offset) {
        StringBuilder sqlBuild = new StringBuilder("SELECT ").append(columnName).append(" FROM ").append(getTableName());
        if (!TextUtils.isEmpty(where)) {
            sqlBuild.append(" WHERE ");
            sqlBuild.append(where);
        }
        if (!TextUtils.isEmpty(orderBy)) {
            sqlBuild.append(" ORDER BY ");
            sqlBuild.append(orderBy);
        }
        if (!TextUtils.isEmpty(limit)) {
            sqlBuild.append(" LIMIT ");
            sqlBuild.append(limit);
        }
        if (!TextUtils.isEmpty(limit) && !TextUtils.isEmpty(offset)) {
            sqlBuild.append(" OFFSET ");
            sqlBuild.append(offset);
        }
        print(sqlBuild.toString());
        return sqlBuild.toString();
    }

    protected void print(String print) {
    }
}
