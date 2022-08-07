package com.lidroid.xutils.db.table;

import android.text.TextUtils;
import com.lidroid.xutils.DbUtils;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class Table {
    private static final HashMap<String, Table> tableMap = new HashMap<>();
    private boolean checkedDatabase;
    public final HashMap<String, Column> columnMap;
    public final DbUtils db;
    public final HashMap<String, Finder> finderMap = new HashMap<>();
    public final Id id;
    public final String tableName;

    private Table(DbUtils db, Class<?> entityType) {
        this.db = db;
        this.tableName = TableUtils.getTableName(entityType);
        this.id = TableUtils.getId(entityType);
        this.columnMap = TableUtils.getColumnMap(entityType);
        for (Column column : this.columnMap.values()) {
            column.setTable(this);
            if (column instanceof Finder) {
                this.finderMap.put(column.getColumnName(), (Finder) column);
            }
        }
    }

    public static synchronized Table get(DbUtils db, Class<?> entityType) {
        Table table;
        synchronized (Table.class) {
            String tableKey = db.getDaoConfig().getDbName() + "#" + entityType.getName();
            table = tableMap.get(tableKey);
            if (table == null) {
                table = new Table(db, entityType);
                tableMap.put(tableKey, table);
            }
        }
        return table;
    }

    public static synchronized void remove(DbUtils db, Class<?> entityType) {
        synchronized (Table.class) {
            tableMap.remove(db.getDaoConfig().getDbName() + "#" + entityType.getName());
        }
    }

    public static synchronized void remove(DbUtils db, String tableName) {
        synchronized (Table.class) {
            if (tableMap.size() > 0) {
                String key = null;
                for (Map.Entry<String, Table> entry : tableMap.entrySet()) {
                    Table table = entry.getValue();
                    if (table != null && table.tableName.equals(tableName)) {
                        key = entry.getKey();
                        if (key.startsWith(db.getDaoConfig().getDbName() + "#")) {
                            break;
                        }
                    }
                }
                if (TextUtils.isEmpty(key)) {
                    tableMap.remove(key);
                }
            }
        }
    }

    public boolean isCheckedDatabase() {
        return this.checkedDatabase;
    }

    public void setCheckedDatabase(boolean checkedDatabase) {
        this.checkedDatabase = checkedDatabase;
    }
}
