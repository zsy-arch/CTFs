package com.lidroid.xutils.db.converter;

import android.database.Cursor;
import android.text.TextUtils;
import com.lidroid.xutils.db.sqlite.ColumnDbType;
import java.sql.Date;

/* loaded from: classes2.dex */
public class SqlDateColumnConverter implements ColumnConverter<Date> {
    @Override // com.lidroid.xutils.db.converter.ColumnConverter
    public Date getFieldValue(Cursor cursor, int index) {
        if (cursor.isNull(index)) {
            return null;
        }
        return new Date(cursor.getLong(index));
    }

    @Override // com.lidroid.xutils.db.converter.ColumnConverter
    public Date getFieldValue(String fieldStringValue) {
        if (TextUtils.isEmpty(fieldStringValue)) {
            return null;
        }
        return new Date(Long.valueOf(fieldStringValue).longValue());
    }

    public Object fieldValue2ColumnValue(Date fieldValue) {
        if (fieldValue == null) {
            return null;
        }
        return Long.valueOf(fieldValue.getTime());
    }

    @Override // com.lidroid.xutils.db.converter.ColumnConverter
    public ColumnDbType getColumnDbType() {
        return ColumnDbType.INTEGER;
    }
}
