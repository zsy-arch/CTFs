package com.litepal.crud;

import android.database.sqlite.SQLiteDatabase;
import com.litepal.util.BaseUtility;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class QueryHandler extends DataHandler {
    /* JADX INFO: Access modifiers changed from: package-private */
    public QueryHandler(SQLiteDatabase db) {
        this.mDatabase = db;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public <T> T onFind(Class<T> modelClass, long id, boolean isEager) {
        List<T> dataList = query(modelClass, null, "id = ?", new String[]{String.valueOf(id)}, null, null, null, null, getForeignKeyAssociations(modelClass.getName(), isEager));
        if (dataList.size() > 0) {
            return dataList.get(0);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public <T> T onFindFirst(Class<T> modelClass, boolean isEager) {
        List<T> dataList = query(modelClass, null, null, null, null, null, "id", "1", getForeignKeyAssociations(modelClass.getName(), isEager));
        if (dataList.size() > 0) {
            return dataList.get(0);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public <T> T onFindLast(Class<T> modelClass, boolean isEager) {
        List<T> dataList = query(modelClass, null, null, null, null, null, "id desc", "1", getForeignKeyAssociations(modelClass.getName(), isEager));
        if (dataList.size() > 0) {
            return dataList.get(0);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public <T> List<T> onFindAll(Class<T> modelClass, boolean isEager, long... ids) {
        if (isAffectAllLines(ids)) {
            return query(modelClass, null, null, null, null, null, "id", null, getForeignKeyAssociations(modelClass.getName(), isEager));
        }
        return query(modelClass, null, getWhereOfIdsWithOr(ids), null, null, null, "id", null, getForeignKeyAssociations(modelClass.getName(), isEager));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public <T> List<T> onFind(Class<T> modelClass, String[] columns, String[] conditions, String orderBy, String limit, boolean isEager) {
        BaseUtility.checkConditionsCorrect(conditions);
        return query(modelClass, columns, getWhereClause(conditions), getWhereArgs(conditions), null, null, orderBy, limit, getForeignKeyAssociations(modelClass.getName(), isEager));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int onCount(String tableName, String[] conditions) {
        return ((Integer) mathQuery(tableName, new String[]{"count(1)"}, conditions, Integer.TYPE)).intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public double onAverage(String tableName, String column, String[] conditions) {
        return ((Double) mathQuery(tableName, new String[]{"avg(" + column + ")"}, conditions, Double.TYPE)).doubleValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public <T> T onMax(String tableName, String column, String[] conditions, Class<T> type) {
        return (T) mathQuery(tableName, new String[]{"max(" + column + ")"}, conditions, type);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public <T> T onMin(String tableName, String column, String[] conditions, Class<T> type) {
        return (T) mathQuery(tableName, new String[]{"min(" + column + ")"}, conditions, type);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public <T> T onSum(String tableName, String column, String[] conditions, Class<T> type) {
        return (T) mathQuery(tableName, new String[]{"sum(" + column + ")"}, conditions, type);
    }
}
