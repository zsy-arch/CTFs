package com.litepal.crud;

import com.litepal.tablemanager.Connector;
import com.litepal.util.BaseUtility;
import java.util.List;

/* loaded from: classes2.dex */
public class ClusterQuery {
    String[] mColumns;
    String[] mConditions;
    String mLimit;
    String mOffset;
    String mOrderBy;

    public ClusterQuery select(String... columns) {
        this.mColumns = columns;
        return this;
    }

    public ClusterQuery where(String... conditions) {
        this.mConditions = conditions;
        return this;
    }

    public ClusterQuery order(String column) {
        this.mOrderBy = column;
        return this;
    }

    public ClusterQuery limit(int value) {
        this.mLimit = String.valueOf(value);
        return this;
    }

    public ClusterQuery offset(int value) {
        this.mOffset = String.valueOf(value);
        return this;
    }

    public <T> List<T> find(Class<T> modelClass) {
        return find(modelClass, false);
    }

    public synchronized <T> List<T> find(Class<T> modelClass, boolean isEager) {
        QueryHandler queryHandler;
        String limit;
        queryHandler = new QueryHandler(Connector.getDatabase());
        if (this.mOffset == null) {
            limit = this.mLimit;
        } else {
            if (this.mLimit == null) {
                this.mLimit = "0";
            }
            limit = this.mOffset + "," + this.mLimit;
        }
        return queryHandler.onFind(modelClass, this.mColumns, this.mConditions, this.mOrderBy, limit, isEager);
    }

    public <T> T findFirst(Class<T> modelClass) {
        return (T) findFirst(modelClass, false);
    }

    public <T> T findFirst(Class<T> modelClass, boolean isEager) {
        List<T> list = find(modelClass, isEager);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public <T> T findLast(Class<T> modelClass) {
        return (T) findLast(modelClass, false);
    }

    public <T> T findLast(Class<T> modelClass, boolean isEager) {
        List<T> list = find(modelClass, isEager);
        int size = list.size();
        if (size > 0) {
            return list.get(size - 1);
        }
        return null;
    }

    public synchronized int count(Class<?> modelClass) {
        return count(BaseUtility.changeCase(modelClass.getSimpleName()));
    }

    public synchronized int count(String tableName) {
        return new QueryHandler(Connector.getDatabase()).onCount(tableName, this.mConditions);
    }

    public synchronized double average(Class<?> modelClass, String column) {
        return average(BaseUtility.changeCase(modelClass.getSimpleName()), column);
    }

    public synchronized double average(String tableName, String column) {
        return new QueryHandler(Connector.getDatabase()).onAverage(tableName, column, this.mConditions);
    }

    public synchronized <T> T max(Class<?> modelClass, String columnName, Class<T> columnType) {
        return (T) max(BaseUtility.changeCase(modelClass.getSimpleName()), columnName, columnType);
    }

    public synchronized <T> T max(String tableName, String columnName, Class<T> columnType) {
        return (T) new QueryHandler(Connector.getDatabase()).onMax(tableName, columnName, this.mConditions, columnType);
    }

    public synchronized <T> T min(Class<?> modelClass, String columnName, Class<T> columnType) {
        return (T) min(BaseUtility.changeCase(modelClass.getSimpleName()), columnName, columnType);
    }

    public synchronized <T> T min(String tableName, String columnName, Class<T> columnType) {
        return (T) new QueryHandler(Connector.getDatabase()).onMin(tableName, columnName, this.mConditions, columnType);
    }

    public synchronized <T> T sum(Class<?> modelClass, String columnName, Class<T> columnType) {
        return (T) sum(BaseUtility.changeCase(modelClass.getSimpleName()), columnName, columnType);
    }

    public synchronized <T> T sum(String tableName, String columnName, Class<T> columnType) {
        return (T) new QueryHandler(Connector.getDatabase()).onSum(tableName, columnName, this.mConditions, columnType);
    }
}
