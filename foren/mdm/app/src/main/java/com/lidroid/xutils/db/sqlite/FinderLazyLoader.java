package com.lidroid.xutils.db.sqlite;

import com.lidroid.xutils.db.table.ColumnUtils;
import com.lidroid.xutils.db.table.Finder;
import com.lidroid.xutils.db.table.Table;
import com.lidroid.xutils.exception.DbException;
import java.util.List;

/* loaded from: classes2.dex */
public class FinderLazyLoader<T> {
    private final Finder finderColumn;
    private final Object finderValue;

    public FinderLazyLoader(Finder finderColumn, Object value) {
        this.finderColumn = finderColumn;
        this.finderValue = ColumnUtils.convert2DbColumnValueIfNeeded(value);
    }

    public List<T> getAllFromDb() throws DbException {
        Table table = this.finderColumn.getTable();
        if (table != null) {
            return table.db.findAll(Selector.from(this.finderColumn.getTargetEntityType()).where(this.finderColumn.getTargetColumnName(), "=", this.finderValue));
        }
        return null;
    }

    public T getFirstFromDb() throws DbException {
        Table table = this.finderColumn.getTable();
        if (table != null) {
            return (T) table.db.findFirst(Selector.from(this.finderColumn.getTargetEntityType()).where(this.finderColumn.getTargetColumnName(), "=", this.finderValue));
        }
        return null;
    }
}
