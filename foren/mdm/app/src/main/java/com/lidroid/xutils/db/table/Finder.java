package com.lidroid.xutils.db.table;

import android.database.Cursor;
import com.lidroid.xutils.db.sqlite.ColumnDbType;
import com.lidroid.xutils.db.sqlite.FinderLazyLoader;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.util.LogUtils;
import java.lang.reflect.Field;
import java.util.List;

/* loaded from: classes2.dex */
public class Finder extends Column {
    private final String targetColumnName;
    private final String valueColumnName;

    public Finder(Class<?> entityType, Field field) {
        super(entityType, field);
        com.lidroid.xutils.db.annotation.Finder finder = (com.lidroid.xutils.db.annotation.Finder) field.getAnnotation(com.lidroid.xutils.db.annotation.Finder.class);
        this.valueColumnName = finder.valueColumn();
        this.targetColumnName = finder.targetColumn();
    }

    public Class<?> getTargetEntityType() {
        return ColumnUtils.getFinderTargetEntityType(this);
    }

    public String getTargetColumnName() {
        return this.targetColumnName;
    }

    @Override // com.lidroid.xutils.db.table.Column
    public void setValue2Entity(Object entity, Cursor cursor, int index) {
        Object value = null;
        Class<?> columnType = this.columnField.getType();
        Object finderValue = TableUtils.getColumnOrId(entity.getClass(), this.valueColumnName).getColumnValue(entity);
        if (columnType.equals(FinderLazyLoader.class)) {
            value = new FinderLazyLoader(this, finderValue);
        } else if (columnType.equals(List.class)) {
            try {
                value = new FinderLazyLoader(this, finderValue).getAllFromDb();
            } catch (DbException e) {
                LogUtils.e(e.getMessage(), e);
            }
        } else {
            try {
                value = new FinderLazyLoader(this, finderValue).getFirstFromDb();
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

    @Override // com.lidroid.xutils.db.table.Column
    public Object getColumnValue(Object entity) {
        return null;
    }

    @Override // com.lidroid.xutils.db.table.Column
    public Object getDefaultValue() {
        return null;
    }

    @Override // com.lidroid.xutils.db.table.Column
    public ColumnDbType getColumnDbType() {
        return ColumnDbType.TEXT;
    }
}
