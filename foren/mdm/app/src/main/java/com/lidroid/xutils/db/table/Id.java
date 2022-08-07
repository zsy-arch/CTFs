package com.lidroid.xutils.db.table;

import com.lidroid.xutils.db.annotation.NoAutoIncrement;
import com.lidroid.xutils.util.LogUtils;
import java.lang.reflect.Field;
import java.util.HashSet;

/* loaded from: classes2.dex */
public class Id extends Column {
    private static final HashSet<String> INTEGER_TYPES = new HashSet<>(2);
    private static final HashSet<String> AUTO_INCREMENT_TYPES = new HashSet<>(4);
    private boolean isAutoIncrementChecked = false;
    private boolean isAutoIncrement = false;
    private String columnFieldClassName = this.columnField.getType().getName();

    /* JADX INFO: Access modifiers changed from: package-private */
    public Id(Class<?> entityType, Field field) {
        super(entityType, field);
    }

    public boolean isAutoIncrement() {
        boolean z = true;
        if (!this.isAutoIncrementChecked) {
            this.isAutoIncrementChecked = true;
            if (this.columnField.getAnnotation(NoAutoIncrement.class) != null || !AUTO_INCREMENT_TYPES.contains(this.columnFieldClassName)) {
                z = false;
            }
            this.isAutoIncrement = z;
        }
        return this.isAutoIncrement;
    }

    public void setAutoIncrementId(Object entity, long value) {
        Object valueOf = Long.valueOf(value);
        if (INTEGER_TYPES.contains(this.columnFieldClassName)) {
            valueOf = Integer.valueOf((int) value);
        }
        if (this.setMethod != null) {
            try {
                this.setMethod.invoke(entity, valueOf);
            } catch (Throwable e) {
                LogUtils.e(e.getMessage(), e);
            }
        } else {
            try {
                this.columnField.setAccessible(true);
                this.columnField.set(entity, valueOf);
            } catch (Throwable e2) {
                LogUtils.e(e2.getMessage(), e2);
            }
        }
    }

    @Override // com.lidroid.xutils.db.table.Column
    public Object getColumnValue(Object entity) {
        Object idValue = super.getColumnValue(entity);
        if (idValue == null) {
            return null;
        }
        if (!isAutoIncrement()) {
            return idValue;
        }
        if (idValue.equals(0) || idValue.equals(0L)) {
            return null;
        }
        return idValue;
    }

    static {
        INTEGER_TYPES.add(Integer.TYPE.getName());
        INTEGER_TYPES.add(Integer.class.getName());
        AUTO_INCREMENT_TYPES.addAll(INTEGER_TYPES);
        AUTO_INCREMENT_TYPES.add(Long.TYPE.getName());
        AUTO_INCREMENT_TYPES.add(Long.class.getName());
    }
}
