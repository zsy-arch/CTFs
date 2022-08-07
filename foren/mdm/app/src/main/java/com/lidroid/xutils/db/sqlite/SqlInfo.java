package com.lidroid.xutils.db.sqlite;

import com.lidroid.xutils.db.table.ColumnUtils;
import java.util.LinkedList;

/* loaded from: classes2.dex */
public class SqlInfo {
    private LinkedList<Object> bindArgs;
    private String sql;

    public SqlInfo() {
    }

    public SqlInfo(String sql) {
        this.sql = sql;
    }

    public SqlInfo(String sql, Object... bindArgs) {
        this.sql = sql;
        addBindArgs(bindArgs);
    }

    public String getSql() {
        return this.sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public LinkedList<Object> getBindArgs() {
        return this.bindArgs;
    }

    public Object[] getBindArgsAsArray() {
        if (this.bindArgs != null) {
            return this.bindArgs.toArray();
        }
        return null;
    }

    public String[] getBindArgsAsStrArray() {
        String obj;
        if (this.bindArgs == null) {
            return null;
        }
        String[] strings = new String[this.bindArgs.size()];
        for (int i = 0; i < this.bindArgs.size(); i++) {
            Object value = this.bindArgs.get(i);
            if (value == null) {
                obj = null;
            } else {
                obj = value.toString();
            }
            strings[i] = obj;
        }
        return strings;
    }

    public void addBindArg(Object arg) {
        if (this.bindArgs == null) {
            this.bindArgs = new LinkedList<>();
        }
        this.bindArgs.add(ColumnUtils.convert2DbColumnValueIfNeeded(arg));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addBindArgWithoutConverter(Object arg) {
        if (this.bindArgs == null) {
            this.bindArgs = new LinkedList<>();
        }
        this.bindArgs.add(arg);
    }

    public void addBindArgs(Object... bindArgs) {
        if (bindArgs != null) {
            for (Object arg : bindArgs) {
                addBindArg(arg);
            }
        }
    }
}
