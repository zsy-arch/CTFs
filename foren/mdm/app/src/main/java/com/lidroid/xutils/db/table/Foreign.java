package com.lidroid.xutils.db.table;

import android.database.Cursor;
import com.lidroid.xutils.db.converter.ColumnConverter;
import com.lidroid.xutils.db.converter.ColumnConverterFactory;
import com.lidroid.xutils.db.sqlite.ColumnDbType;
import com.lidroid.xutils.db.sqlite.ForeignLazyLoader;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.util.LogUtils;
import java.lang.reflect.Field;
import java.util.List;

/* loaded from: classes2.dex */
public class Foreign extends Column {
    private final ColumnConverter foreignColumnConverter;
    private final String foreignColumnName;

    public Foreign(Class<?> entityType, Field field) {
        super(entityType, field);
        this.foreignColumnName = ColumnUtils.getForeignColumnNameByField(field);
        this.foreignColumnConverter = ColumnConverterFactory.getColumnConverter(TableUtils.getColumnOrId(getForeignEntityType(), this.foreignColumnName).columnField.getType());
    }

    public String getForeignColumnName() {
        return this.foreignColumnName;
    }

    public Class<?> getForeignEntityType() {
        return ColumnUtils.getForeignEntityType(this);
    }

    @Override // com.lidroid.xutils.db.table.Column
    public void setValue2Entity(Object entity, Cursor cursor, int index) {
        Object fieldValue = this.foreignColumnConverter.getFieldValue(cursor, index);
        if (fieldValue != null) {
            Object value = null;
            Class<?> columnType = this.columnField.getType();
            if (columnType.equals(ForeignLazyLoader.class)) {
                value = new ForeignLazyLoader(this, fieldValue);
            } else if (columnType.equals(List.class)) {
                try {
                    value = new ForeignLazyLoader(this, fieldValue).getAllFromDb();
                } catch (DbException e) {
                    LogUtils.e(e.getMessage(), e);
                }
            } else {
                try {
                    value = new ForeignLazyLoader(this, fieldValue).getFirstFromDb();
                } catch (DbException e2) {
                    LogUtils.e(e2.getMessage(), e2);
                }
            }
            if (this.setMethod != null) {
                try {
                    this.setMethod.invoke(entity, value);
                } catch (Throwable e3) {
                    LogUtils.e(e3.getMessage(), e3);
                }
            } else {
                try {
                    this.columnField.setAccessible(true);
                    this.columnField.set(entity, value);
                } catch (Throwable e4) {
                    LogUtils.e(e4.getMessage(), e4);
                }
            }
        }
    }

    @Override // com.lidroid.xutils.db.table.Column
    public Object getColumnValue(Object entity) {
        Object fieldValue = getFieldValue(entity);
        if (fieldValue == null) {
            return null;
        }
        Class<?> columnType = this.columnField.getType();
        if (columnType.equals(ForeignLazyLoader.class)) {
            return ((ForeignLazyLoader) fieldValue).getColumnValue();
        }
        if (columnType.equals(List.class)) {
            try {
                List<?> foreignEntities = (List) fieldValue;
                if (foreignEntities.size() <= 0) {
                    return null;
                }
                Column column = TableUtils.getColumnOrId(ColumnUtils.getForeignEntityType(this), this.foreignColumnName);
                column.getColumnValue(foreignEntities.get(0));
                Table table = getTable();
                if (table != null && (column instanceof Id)) {
                    for (Object foreignObj : foreignEntities) {
                        if (column.getColumnValue(foreignObj) == null) {
                            table.db.saveOrUpdate(foreignObj);
                        }
                    }
                }
                return column.getColumnValue(foreignEntities.get(0));
            } catch (Throwable e) {
                LogUtils.e(e.getMessage(), e);
                return null;
            }
        } else {
            try {
                Column column2 = TableUtils.getColumnOrId(columnType, this.foreignColumnName);
                Object columnValue = column2.getColumnValue(fieldValue);
                Table table2 = getTable();
                if (table2 != null && columnValue == null && (column2 instanceof Id)) {
                    table2.db.saveOrUpdate(fieldValue);
                }
                return column2.getColumnValue(fieldValue);
            } catch (Throwable e2) {
                LogUtils.e(e2.getMessage(), e2);
                return null;
            }
        }
    }

    @Override // com.lidroid.xutils.db.table.Column
    public ColumnDbType getColumnDbType() {
        return this.foreignColumnConverter.getColumnDbType();
    }

    @Override // com.lidroid.xutils.db.table.Column
    public Object getDefaultValue() {
        return null;
    }
}
