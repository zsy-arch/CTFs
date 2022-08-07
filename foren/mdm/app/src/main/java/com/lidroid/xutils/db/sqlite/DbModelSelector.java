package com.lidroid.xutils.db.sqlite;

import android.text.TextUtils;

/* loaded from: classes2.dex */
public class DbModelSelector {
    private String[] columnExpressions;
    private String groupByColumnName;
    private WhereBuilder having;
    private Selector selector;

    private DbModelSelector(Class<?> entityType) {
        this.selector = Selector.from(entityType);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public DbModelSelector(Selector selector, String groupByColumnName) {
        this.selector = selector;
        this.groupByColumnName = groupByColumnName;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public DbModelSelector(Selector selector, String[] columnExpressions) {
        this.selector = selector;
        this.columnExpressions = columnExpressions;
    }

    public static DbModelSelector from(Class<?> entityType) {
        return new DbModelSelector(entityType);
    }

    public DbModelSelector where(WhereBuilder whereBuilder) {
        this.selector.where(whereBuilder);
        return this;
    }

    public DbModelSelector where(String columnName, String op, Object value) {
        this.selector.where(columnName, op, value);
        return this;
    }

    public DbModelSelector and(String columnName, String op, Object value) {
        this.selector.and(columnName, op, value);
        return this;
    }

    public DbModelSelector and(WhereBuilder where) {
        this.selector.and(where);
        return this;
    }

    public DbModelSelector or(String columnName, String op, Object value) {
        this.selector.or(columnName, op, value);
        return this;
    }

    public DbModelSelector or(WhereBuilder where) {
        this.selector.or(where);
        return this;
    }

    public DbModelSelector expr(String expr) {
        this.selector.expr(expr);
        return this;
    }

    public DbModelSelector expr(String columnName, String op, Object value) {
        this.selector.expr(columnName, op, value);
        return this;
    }

    public DbModelSelector groupBy(String columnName) {
        this.groupByColumnName = columnName;
        return this;
    }

    public DbModelSelector having(WhereBuilder whereBuilder) {
        this.having = whereBuilder;
        return this;
    }

    public DbModelSelector select(String... columnExpressions) {
        this.columnExpressions = columnExpressions;
        return this;
    }

    public DbModelSelector orderBy(String columnName) {
        this.selector.orderBy(columnName);
        return this;
    }

    public DbModelSelector orderBy(String columnName, boolean desc) {
        this.selector.orderBy(columnName, desc);
        return this;
    }

    public DbModelSelector limit(int limit) {
        this.selector.limit(limit);
        return this;
    }

    public DbModelSelector offset(int offset) {
        this.selector.offset(offset);
        return this;
    }

    public Class<?> getEntityType() {
        return this.selector.getEntityType();
    }

    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append("SELECT ");
        if (this.columnExpressions != null && this.columnExpressions.length > 0) {
            for (int i = 0; i < this.columnExpressions.length; i++) {
                result.append(this.columnExpressions[i]);
                result.append(",");
            }
            result.deleteCharAt(result.length() - 1);
        } else if (!TextUtils.isEmpty(this.groupByColumnName)) {
            result.append(this.groupByColumnName);
        } else {
            result.append("*");
        }
        result.append(" FROM ").append(this.selector.tableName);
        if (this.selector.whereBuilder != null && this.selector.whereBuilder.getWhereItemSize() > 0) {
            result.append(" WHERE ").append(this.selector.whereBuilder.toString());
        }
        if (!TextUtils.isEmpty(this.groupByColumnName)) {
            result.append(" GROUP BY ").append(this.groupByColumnName);
            if (this.having != null && this.having.getWhereItemSize() > 0) {
                result.append(" HAVING ").append(this.having.toString());
            }
        }
        if (this.selector.orderByList != null) {
            for (int i2 = 0; i2 < this.selector.orderByList.size(); i2++) {
                result.append(" ORDER BY ").append(this.selector.orderByList.get(i2).toString());
            }
        }
        if (this.selector.limit > 0) {
            result.append(" LIMIT ").append(this.selector.limit);
            result.append(" OFFSET ").append(this.selector.offset);
        }
        return result.toString();
    }
}
